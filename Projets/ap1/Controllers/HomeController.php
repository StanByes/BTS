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
        } else {
            $reports = ReportModel::getReportsByUser($user);
        }

        self::render("home", compact("reports", "internships", "specificUser"));
    }
}
