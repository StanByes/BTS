@extends('layouts.layout')

@section('title', 'Réinitialisation de mot de passe')
@section('card_title', 'Réinitialisation de mot de passe')

@section('content')
    <form method="post">
        @csrf
        <div class="form-group mb-3">
            <label class="form-label" for="password">Nouveau mot de passe :</label>
            <input id="password" class="form-control" name="password" type="password" required="required">
        </div>

        <div class="d-flex justify-content-center mt-4">
            <button class="btn btn-primary w-75">Valider</button>
        </div>
    </form>
@endsection

<?php
$content = ob_get_clean();
?>
