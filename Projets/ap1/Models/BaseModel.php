<?php
namespace App\Models;

use http\Exception\RuntimeException;
use PDO;

abstract class BaseModel
{
    public static function getConnection(): \PDO
    {
        $config = require CONFIG_PATH . "/databases.php";
        return new PDO("mysql:host=".$config["host"].";dbname=".$config["name"], $config["user"], $config["password"]);
    }

    public static function map($attributes): string
    {
        return join(", ", array_map(function ($atr) {
            return "`$atr`";
        }, $attributes));
    }

    protected static function executeSelect($query, $selectors = []): array
    {
        $q = self::getConnection()->prepare($query);
        if (count($selectors) != 0) {
            for ($i = 0; $i < count($selectors); $i++) {
                $q->bindParam($i + 1, $selectors[$i]);
            }
        }

        if ($q->execute()) {
            $result = array();
            while ($data = $q->fetch(PDO::FETCH_ASSOC)) {
                if (!empty($data)) {
                    $result[] = $data;
                }
            }

            return $result;
        } else {
            echo "Erreur SQL : " . $q->errorInfo()[0];
            return [];
        }
    }

    protected static function createObject($data)
    {
    }
}
