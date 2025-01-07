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
            $date = strip_tags($_POST["date"]);

            if (empty($title) || empty($content) || empty($date)) {
                self::flash(true, "Veuillez remplir tous les champs");
                header("Location: ./");
                die();
            }

            $date = DateTime::createFromFormat("Y-m-d", $date);
            $report = new Report(null, self::getUser(), $title, $content, $date, new DateTime());
            ReportModel::createReport($report);
            self::flash(false, "Rapport créé avec succès");

            header("Location: ./");
            die();
        }
    }
}
