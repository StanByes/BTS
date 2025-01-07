<?php

namespace App\Entities;

use DateTime;

class ResetPasswordQuery
{
    private int $id;
    private User $user;
    private string $code;
    private bool $active;
    private DateTime $unavailableAt;

    public function __construct($id, $user, $code, $active, $unavailableAt)
    {
        $this->id = $id;
        $this->user = $user;
        $this->code = $code;
        $this->active = $active;
        $this->unavailableAt = $unavailableAt;
    }

    public function getId(): int
    {
        return $this->id;
    }

    public function getUser(): User
    {
        return $this->user;
    }

    public function getCode(): string
    {
        return $this->code;
    }

    public function isActive(): bool
    {
        return $this->active;
    }

    public function getUnavailableAt(): DateTime
    {
        return $this->unavailableAt;
    }
}
