<?php

namespace App\Models;

use App\Entities\Internship;
use DateTime;
use PDO;

class InternshipModel extends BaseModel
{
    private static array $atr = ["intern_id", "supervisor_id", "start_at", "end_at", "day_start_at", "day_end_at"];

    public static function getAllInternsBySupervisor($supervisor): array
    {
        $query = "SELECT " . self::map(self::$atr) . " FROM `internships` WHERE `supervisor_id` = ?";
        $rows = self::executeSelect($query, [$supervisor->getId()]);

        $result = array();
        foreach ($rows as $row) {
            $result[] = self::createObject($row);
        }

        return $result;
    }
    public static function getInternshipByUser($user): Internship | null
    {
        $query = "SELECT " . self::map(self::$atr) . " FROM `internships` WHERE `intern_id` = ?";
        $rows = self::executeSelect($query, [$user->getId()]);
        if (count($rows) == 0) {
            return null;
        }

        return self::createObject($rows[0]);
    }

    public static function updateInternship($user, $startAt, $endAt, $dayStartAt, $dayEndAt): bool
    {
        $q = "UPDATE `internships` SET `start_at` = ?, `end_at` = ?, `day_start_at` = ?, `day_end_at` = ?
                     WHERE `intern_id` = ?";
        $prepare = self::getConnection()->prepare($q);
        $prepare->bindParam(1, $startAt);
        $prepare->bindParam(2, $endAt);

        $dayStartAt = $dayStartAt ?? null;
        $dayEndAt = $dayEndAt ?? null;

        $prepare->bindParam(3, $dayStartAt, $dayStartAt == null ? PDO::PARAM_NULL : PDO::PARAM_STR);
        $prepare->bindParam(4, $dayEndAt, $dayEndAt == null ? PDO::PARAM_NULL : PDO::PARAM_STR);

        $userId = $user->getId();
        $prepare->bindParam(5, $userId);

        return $prepare->execute();
    }

    protected static function createObject($data): Internship
    {
        $dateFormat = "Y-m-d";
        $timeFormat = "H:i:s";

        return new Internship(
            UserModel::getUserById($data["intern_id"]),
            UserModel::getUserById($data["supervisor_id"]),
            DateTime::createFromFormat($dateFormat, $data["start_at"]),
            DateTime::createFromFormat($dateFormat, $data["end_at"]),
            empty($data["day_start_at"]) ? null : DateTime::createFromFormat($timeFormat, $data["day_start_at"]),
            empty($data["day_end_at"]) ? null : DateTime::createFromFormat($timeFormat, $data["day_end_at"]),
        );
    }
}
