@extends('layouts.layout')

@section('title', 'Demande de réinitialisation de mot de passe')
@section('card_title', 'Demande de réinitialisation de mot de passe')

@section('content')
    <form method="post">
        @csrf
        <div class="form-group mb-3">
            <label class="form-label" for="mail">Votre addresse mail :</label>
            <input id="mail" class="form-control" name="mail" type="email" required="required">
        </div>

        <div class="d-flex justify-content-center mt-4">
            <button class="btn btn-primary w-75">Valider</button>
        </div>
    </form>
@endsection
