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

    public static function edit(): void
    {
        $reportId = $_GET["report_id"];
        if (!isset($reportId)) {
            self::flash(true, "Erreur : Ressource introuvable");
            header("Location: ./");
            die();
        }

        $report = ReportModel::getReportById($reportId);
        if ($report->getCreator()->getId() != self::getUser()->getId()) {
            self::flash(true, "Erreur : Accès refusé");
            header("Location: ./");
            die();
        }

        self::render("reports/edit", compact("report"));
    }

    public static function update(): void
    {
        $reportId = $_GET["report_id"];
        if (!isset($reportId)) {
            self::flash(true, "Erreur : Ressource introuvable");
            header("Location: ./");
            die();
        }

        $report = ReportModel::getReportById($reportId);
        if ($report->getCreator()->getId() != self::getUser()->getId()) {
            self::flash(true, "Erreur : Accès refusé");
            header("Location: ./");
            die();
        }

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
            $report->setTitle($title);
            $report->setContent($content);
            $report->setDate($date);

            if (!ReportModel::updateReport($report)) {
                self::flash(true, "Une erreur est survenue");
                header("Location: ./action?edit_report&report_id=$reportId");
                die();
            }

            self::flash(false, "Rapport édité avec succès");
            header("Location: ./");
            die();
        }
    }
}
