<?php

namespace App\Models;

use App\Entities\Internship;

class InternshipModel extends BaseModel
{
    public static function getAllInternsBySupervisor($supervisor): array
    {
        $atr = ["intern_id", "supervisor_id"];
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
        return new Internship(
            UserModel::getUserById($data["intern_id"]),
            UserModel::getUserById($data["supervisor_id"])
        );
    }
}
