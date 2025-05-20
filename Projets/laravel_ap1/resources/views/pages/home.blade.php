@extends('layouts.layout')

@section('title', 'Accueil')
@section('card_title', 'Espace ' . auth()->user()->role->display_name)
@section('card_size', 'w-75')

@section('content')
    @include('pages.home.' . auth()->user()->role->name)
@endsection
