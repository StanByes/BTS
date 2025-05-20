<?php

namespace App\Mail;

use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Mail\Mailable;
use Illuminate\Mail\Mailables\Address;
use Illuminate\Mail\Mailables\Content;
use Illuminate\Mail\Mailables\Envelope;
use Illuminate\Queue\SerializesModels;

class ResetPasswordQuery extends Mailable
{
    use Queueable, SerializesModels;

    public function __construct(public \App\Models\ResetPasswordQuery $resetPasswordQuery) {}

    public function envelope(): Envelope
    {
        return new Envelope(
            subject: 'Réinitialisation de mot de passe',
        );
    }

    public function content(): Content
    {
        return new Content(
            view: 'mail.users.reset_password_query',
        );
    }

    public function attachments(): array
    {
        return [];
    }
}
