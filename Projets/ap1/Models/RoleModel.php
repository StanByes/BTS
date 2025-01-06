<?php

namespace App\Models;

use App\Entities\Role;
use PDO;

class RoleModel extends BaseModel
{
    public static function getRoleById($id): Role | null
    {
        $q = "SELECT " . self::map(["id", "name", "display_name"]) . " FROM `roles` WHERE `id` = ?";
        $rows = self::executeSelect($q, [$id]);
        if (count($rows) == 0) {
            return null;
        }

        return self::createObject($rows[0]);
    }

    protected static function createObject($data): Role
    {
        return new Role(
            $data["id"],
            $data["name"],
            $data["display_name"]
        );
    }
}
