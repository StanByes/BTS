<?php

namespace App\Models;

use App\Entities\Role;

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

    public static function getRoleByName($name): Role | null
    {
        $q = "SELECT " . self::map(["id", "name", "display_name"]) . " FROM `roles` WHERE `name` = ?";
        $rows = self::executeSelect($q, [$name]);
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
