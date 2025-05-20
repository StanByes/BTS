@extends('layouts.layout')

@section('title', 'Connexion')
@section('card_title', 'Connexion')

@section('content')
    <form method="post">
        @csrf
        <div class="mb-3 form-group">
            <label for="login" class="form-label">Email</label>
            <input id="login" class="form-control" type="email" name="mail" required="required">
        </div>

        <div class="form-group mb-3">
            <label for="password" class="form-label">Mot de passe</label>
            <input id="password" class="form-control" type="password" name="password" required="required">
        </div>

        <div class="mb-2">
            <a class="text-decoration-none" href="/reset_password_query">Mot de passe oublié ?</a>
        </div>

        <div class="d-flex justify-content-center mt-4">
            <button class="btn btn-primary w-75">Valider</button>
        </div>
    </form>
@endsection
