<?php
namespace App\Controllers;

use App\Models\InternshipModel;
use App\Models\ReportModel;

class HomeController extends AppController
{
    public static function home(): void
    {
        $user = self::getUser();

        $internships = [];
        $reports = array();

        if ($user->getRole()->getName() == "supervisor") {
            $internships = InternshipModel::getAllInternsBySupervisor($user);
            foreach ($internships as $internship) {
                $reports = array_merge($reports, ReportModel::getReportsByUser($internship->getIntern()));
            }
        } else {
            $reports = ReportModel::getReportsByUser($user);
        }

        self::render("home", compact("reports", "internships"));
    }
}
