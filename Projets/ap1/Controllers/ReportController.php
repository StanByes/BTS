<?php

namespace App\Controllers;

use App\Entities\Report;
use App\Models\ReportModel;
use DateTime;

class ReportController extends AppController
{
    public static function create(): void
    {
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $title = strip_tags($_POST["title"]);
            $content = strip_tags($_POST['content']);

            if (empty($title) || empty($content)) {
                // TODO : Send flash message
                return;
            }

            ReportModel::createReport(new Report(null, self::getUser(), $title, $content, new DateTime()));
            header("Location: ./");
            die();
        }
    }
}
