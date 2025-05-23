<?php
namespace App\Controllers;

use App\Models\InternshipModel;
use App\Models\ReportModel;
use App\Models\UserModel;

class HomeController extends AppController
{
    public static function home(): void
    {
        $user = self::getUser();

        $internships = [];
        $reports = array();
        $specificUser = false;

        $users = array();
        $totalReports = 0;
        $reportsCountByUsers = array();

        $withoutReportCount = 0;

        if ($user->getRole()->getName() == "supervisor") {
            $internships = InternshipModel::getAllInternsBySupervisor($user);
            if (empty($_GET["user"])) {
                foreach ($internships as $internship) {
                    $reports = array_merge($reports, ReportModel::getReportsByUser($internship->getIntern()));
                }
            } else {
                $specificUser = true;
                $searchedUser = UserModel::getUserById($_GET["user"]);
                if (!isset($searchedUser)) {
                    self::flash(true, "Utilisateur inconnu");
                    header("Location: ./");
                    die();
                }

                $reports = ReportModel::getReportsByUser($searchedUser);
            }
            $withoutReportCount = UserModel::getWithoutReportBySupervisor($user);
        } elseif ($user->getRole()->getName() == "gestion") {
            $users = UserModel::getAllUsers();
            $totalReports = ReportModel::getReportsCount();

            foreach ($users as $user) {
                $reportsCountByUsers[$user->getId()] = ReportModel::getReportsCount($user);
            }
        } else {
            $reports = ReportModel::getReportsByUser($user);
        }

        usort($reports, function ($a, $b) {
            return $a->getDate() < $b->getDate();
        });

        self::render("home", compact(
            "reports",
            "internships",
            "specificUser",
            "users",
            "totalReports",
            "reportsCountByUsers",
            "withoutReportCount"
        ));
    }
}
