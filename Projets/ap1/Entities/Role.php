<?php

namespace App\Entities;

class Role
{
    private int $id;
    private string $name;
    private string $displayName;

    public function __construct($id, $name, $displayName)
    {
        $this->id = $id;
        $this->name = $name;
        $this->displayName = $displayName;
    }

    public function getId(): int
    {
        return $this->id;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function getDisplayName(): string
    {
        return $this->displayName;
    }
}
