<?php

namespace App\Models;

use App\Entities\ResetPasswordQuery;
use DateMalformedStringException;
use DateTime;
use http\Exception\RuntimeException;

class ResetPasswordQueryModel extends BaseModel
{
    private static string $table = "reset_password_queries";

    public static function getQueryByCode($code): ResetPasswordQuery | null
    {
        $atr = self::map(["id", "user_id", "code", "active", "unavailable_at"]);
        $condition = "`code` = ? AND CURRENT_TIMESTAMP() < `unavailable_at` AND `active` = 1";
        $q = "SELECT " . $atr . " FROM `". self::$table . "` WHERE " . $condition;

        $rows = self::executeSelect($q, [$code]);
        if (count($rows) == 0) {
            return null;
        }

        try {
            return self::createObject($rows[0]);
        } catch (DateMalformedStringException $exception) {
            throw new RuntimeException($exception);
        }
    }

    public static function createQuery($user, $code): bool
    {
        $atr = self::map(["user_id", "code", "active", "unavailable_at"]);
        $unavailable = "DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 1 HOUR)";

        $q = "INSERT INTO `" . self::$table . "`(" . $atr . ") VALUES (?, ?, 1, " . $unavailable . ")";
        echo $q;
        $prepare = self::getConnection()->prepare($q);

        $user_id = $user->getId();
        $prepare->bindParam(1, $user_id);
        $prepare->bindParam(2, $code);

        return $prepare->execute();
    }

    public static function unavailableQuery($query): bool
    {
        $q = "UPDATE `reset_password_queries` SET `active` = 0 WHERE `id` = ?";
        $prepare = self::getConnection()->prepare($q);
        $query_id = $query->getId();
        $prepare->bindParam(1, $query_id);

        return $prepare->execute();
    }

    public static function cleanOldQueries(): bool
    {
        $q = "DELETE FROM `reset_password_queries` WHERE CURRENT_TIMESTAMP() > `unavailable_at`";
        $prepare = self::getConnection()->prepare($q);

        return $prepare->execute();
    }

    /**
     * @throws DateMalformedStringException
     */
    protected static function createObject($data): ResetPasswordQuery
    {
        return new ResetPasswordQuery(
            $data["id"],
            UserModel::getUserById($data["user_id"]),
            $data["code"],
            (bool) $data["active"],
            new DateTime($data["unavailable_at"])
        );
    }
}
