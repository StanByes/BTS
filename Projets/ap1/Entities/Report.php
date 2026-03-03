<?php

namespace App\Entities;

use DateTime;

class Report
{
    private int | null $id;
    private User $creator;
    private string $title;
    private string $content;
    private int | null $note;
    private DateTime $date;
    private DateTime $createdAt;

    public function __construct($id, $creator, $title, $content, $note, $date, $createdAt)
    {
        $this->id = $id;
        $this->creator = $creator;
        $this->title = $title;
        $this->content = $content;
        $this->note = $note;
        $this->date = $date;
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

    public function getNote(): int | null
    {
        return $this->note;
    }

    public function getDate(): DateTime
    {
        return $this->date;
    }

    public function getCreatedAt(): DateTime
    {
        return $this->createdAt;
    }

    public function setTitle($title): void
    {
        $this->title = $title;
    }

    public function setContent($content): void
    {
        $this->content = $content;
    }

    public function setNote($note): void
    {
        $this->note = $note;
    }

    public function setDate($date): void
    {
        $this->date = $date;
    }
}
