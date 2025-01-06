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

    public function getName()
    {
        return $this->name;
    }

    public function getDisplayName()
    {
        return $this->displayName;
    }
}
