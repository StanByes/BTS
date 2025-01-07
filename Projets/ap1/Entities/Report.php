<?php

namespace App\Entities;

use DateTime;

class Report
{
    private int | null $id;
    private User $creator;
    private string $title;
    private string $content;
    private DateTime $createdAt;

    public function __construct($id, $creator, $title, $content, $createdAt)
    {
        $this->id = $id;
        $this->creator = $creator;
        $this->title = $title;
        $this->content = $content;
        $this->createdAt = $createdAt;
    }

    public function getId(): int
    {
        return $this->id;
    }

    public function getCreator(): User
    {
        return $this->creator;
    }

    public function getTitle(): string
    {
        return $this->title;
    }

    public function getContent(): string
    {
        return $this->content;
    }

    public function getCreatedAt(): DateTime
    {
        return $this->createdAt;
    }
}
