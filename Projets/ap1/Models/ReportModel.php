<?php

namespace App\Models;

use App\Entities\Report;
use DateMalformedStringException;
use DateTime;
use http\Exception\RuntimeException;

class ReportModel extends BaseModel
{
    public static function getReportsByUser($user): array | null
    {
        $atr = ["id", "creator_id", "title", "content", "date", "created_at"];
        $q = "SELECT " . self::map($atr) . " FROM `reports` WHERE `creator_id` = ?";
        $rows = self::executeSelect($q, [$user->getId()]);

        $result = array();
        foreach ($rows as $row) {
            try {
                $result[] = self::createObject($row);
            } catch (DateMalformedStringException $exception) {
                throw new RuntimeException($exception);
            }
        }

        return $result;
    }

    public static function createReport($report): bool
    {
        $atr = ["creator_id", "title", "content", "date", "created_at"];
        $q = "INSERT INTO `reports`(" . self::map($atr) . ") VALUES (?,?,?,?,NOW())";

        $prepare = self::getConnection()->prepare($q);
        $prepare->bindParam(1, $report->getCreator()->getId());
        $prepare->bindParam(2, $report->getTitle());
        $prepare->bindParam(3, $report->getContent());
        $prepare->bindParam(4, $report->getDate()->format("Y-m-d"));

        return $prepare->execute();
    }

    /**
     * @throws DateMalformedStringException
     */
    protected static function createObject($data): Report
    {
        return new Report(
            $data["id"],
            UserModel::getUserById($data["creator_id"]),
            $data["title"],
            $data["content"],
            DateTime::createFromFormat("Y-m-d", $data["date"]),
            new DateTime($data["created_at"])
        );
    }
}
