<?php

namespace App\Models;

use App\Entities\Internship;
use DateTime;

class InternshipModel extends BaseModel
{
    public static function getAllInternsBySupervisor($supervisor): array
    {
        $atr = ["intern_id", "supervisor_id", "start_at", "end_at", "day_start_at", "day_end_at"];
        $query = "SELECT " . self::map($atr) . " FROM `internships` WHERE `supervisor_id` = ?";
        $rows = self::executeSelect($query, [$supervisor->getId()]);

        $result = array();
        foreach ($rows as $row) {
            $result[] = self::createObject($row);
        }

        return $result;
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
            DateTime::createFromFormat($timeFormat, $data["day_start_at"]),
            DateTime::createFromFormat($timeFormat, $data["day_end_at"]),
        );
    }
}
