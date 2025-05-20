<h1>Informations</h1>
<h2>Nombre total de CR : {{ $totalReports }}</h2>

<hr>

<h2>Liste des élèves ({{ count($interns) }})</h2>
<table class="table table-responsive">
    <thead>
    <tr>
        <th>#</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Mail</th>
        <th>Nombre de CR</th>
    </tr>
    </thead>

    <tbody>
    @foreach ($interns as $user)
        <tr>
            <td>{{ $user->id }}</td>
            <td>{{ $user->firstname }}</td>
            <td>{{ $user->surname }}</td>
            <td>{{ $user->mail }}</td>
            <td>{{ count($user->reports) }}</td>
        </tr>
    @endforeach
    </tbody>
</table>

<hr>

<h2>Liste des maîtres de stage ({{ count($supervisors) }})</h2>
<table class="table table-responsive">
    <thead>
    <tr>
        <th>#</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Mail</th>
    </tr>
    </thead>

    <tbody>
    @foreach ($supervisors as $user)
        <tr>
            <td>{{ $user->id }}</td>
            <td>{{ $user->firstname }}</td>
            <td>{{ $user->surname }}</td>
            <td>{{ $user->mail }}</td>
        </tr>
    @endforeach
    </tbody>
</table>
