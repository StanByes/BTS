<?php
namespace App\Models;

use App\Entities\User;

class UserModel extends BaseModel
{
    private static array $atr = ["id", "firstname", "surname", "login", "mail", "password", "role_id"];

    public static function getUserById($id): User | null
    {
        $query = "SELECT " . self::map(self::$atr) . " FROM `users` WHERE `id` = ?";
        $rows = self::executeSelect($query, [$id]);
        if (count($rows) == 0) {
            return null;
        }

        return self::createObject($rows[0]);
    }

    public static function getUser($login, $use_mail): User | null
    {
        $query = "SELECT " . self::map(self::$atr) . " FROM `users` WHERE `" . ($use_mail ? 'mail' : 'login') . "` = ?";
        $rows = self::executeSelect($query, [$login]);
        if (count($rows) == 0) {
            return null;
        }

        return self::createObject($rows[0]);
    }

    public static function changePassword($user, $password): bool
    {
        $q = "UPDATE `users` SET `password` = ? WHERE `id` = ?";
        $prepare = self::getConnection()->prepare($q);
        $prepare->bindParam(1, $password);
        $user_id = $user->getId();
        $prepare->bindParam(2, $user_id);

        return $prepare->execute();
    }

    protected static function createObject($data): User
    {
        return new User(
            $data["id"],
            $data["firstname"],
            $data["surname"],
            $data["login"],
            $data["mail"],
            $data["password"],
            RoleModel::getRoleById($data["role_id"])
        );
    }

    public static function verifyPassword($password, $hashed_password): bool
    {
        return password_verify($password, $hashed_password);
    }
}
