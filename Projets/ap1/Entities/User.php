<?php

namespace App\Entities;

enum FrontDesign: string {
    case STANDARD = "STANDARD";
    case DARK = "DARK";
}

class User
{
    private int $id;
    private string $firstname;
    private string $surname;
    private string $login;
    private string $mail;
    private string $password;
    private bool $status;
    private Role $role;
    private FrontDesign $frontDesign;

    public function __construct($id, $firstname, $surname, $login, $mail, $password, $status, $role, $frontDesign)
    {
        $this->id = $id;
        $this->firstname = $firstname;
        $this->surname = $surname;
        $this->login = $login;
        $this->mail = $mail;
        $this->password = $password;
        $this->status = $status;
	$this->role = $role;
	$this->frontDesign = $frontDesign;
    }

    public function getId(): int
    {
        return $this->id;
    }

    public function getFirstname(): string
    {
        return $this->firstname;
    }

    public function getSurname(): string
    {
        return $this->surname;
    }

    public function getCompleteName(): string
    {
        return $this->firstname . " " . $this->surname;
    }

    public function getLogin(): string
    {
        return $this->login;
    }

    public function getMail(): string
    {
        return $this->mail;
    }

    public function getRole(): Role
    {
        return $this->role;
    }

    public function getPassword(): string
    {
        return $this->password;
    }

    public function isActive(): bool
    {
        return $this->status == 1;
    }

    public function getFrontDesign(): FrontDesign
    {
	return $this->frontDesign;
    }

    public function setFrontDesign($frontDesign): void
    {
	$this->frontDesign = $frontDesign;
    }
}
