<?php

namespace App\Entities;

use DateTime;

class Internship
{
    private User $intern;
    private User $supervisor;
    private DateTime $startAt;
    private DateTime $endAt;
    private DateTime $dayStartAt;
    private DateTime $dayEndAt;

    public function __construct($intern, $supervisor, $startAt, $endAt, $dayStartAt, $dayEndAt)
    {
        $this->intern = $intern;
        $this->supervisor = $supervisor;
        $this->startAt = $startAt;
        $this->endAt = $endAt;
        $this->dayStartAt = $dayStartAt;
        $this->dayEndAt = $dayEndAt;
    }

    public function getIntern(): User
    {
        return $this->intern;
    }

    public function getSupervisor(): User
    {
        return $this->supervisor;
    }

    public function getStartAt(): DateTime
    {
        return $this->startAt;
    }

    public function getEndAt(): DateTime
    {
        return $this->endAt;
    }

    public function getDayStartAt(): DateTime
    {
        return $this->dayStartAt;
    }

    public function getDayEndAt(): DateTime
    {
        return $this->dayEndAt;
    }
}
