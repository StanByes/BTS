<?php

namespace App\Entities;

class Internship
{
    private User $intern;
    private User $supervisor;

    public function __construct($intern, $supervisor)
    {
        $this->intern = $intern;
        $this->supervisor = $supervisor;
    }

    public function getIntern(): User
    {
        return $this->intern;
    }
}
