<ul class="d-flex justify-content-around nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#reports">Rapports des stagiaires</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#create">Liste de vos stagiaires</a>
    </li>
</ul>

<div class="tab-content mt-5">
    <div class="tab-pane container active" id="reports">
        @isset($specificUser)
            <div class="d-flex justify-content-center">
                <a href="./" class="btn btn-danger mb-4">Revenir à tous les rapports</a>
            </div>

            @include('components.reports_list', ['reports' => $specificUser->reports])
        @else
            @include('components.reports_list', ['reports' => auth()->user()->internships[0]->intern->reports])
        @endisset
    </div>

    <div class="tab-pane container fade" id="create">
        @foreach (auth()->user()->internships as $internship)
            @php
                $intern = $internship->intern;
            @endphp
            <div class="card mb-3">
                <div class="card-header" data-bs-toggle="collapse" data-bs-target="#intern_{{ $intern->id }}">
                    <div class="d-flex justify-content-center">
                        <h4 class="text-center">{{ $intern->complete_name }}</h4>
                    </div>
                </div>
                <div class="card-body collapse" id="intern_{{ $intern->id }}">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h3 class="mb-4">Informations du stagiaire</h3>
                            <p><strong>Nom :</strong> {{ $intern->surname }}</p>
                            <p><strong>Prénom :</strong> {{ $intern->firstname }}</p>
                            <p><strong>Login :</strong> {{ $intern->login }}</p>
                            <p><strong>Adresse Mail :</strong> {{ $intern->mail }}</p>
                        </div>
                        <a class="btn btn-primary h-25" href="?user={{ $intern->id }}">Voir les rapports</a>
                    </div>

                    <hr>

                    <h3 class="mb-4">Informations du stage</h3>
                    <p><strong>Date de début :</strong> <?= formatDate($internship->start_at) ?></p>
                    <p><strong>Date de fin :</strong> <?= formatDate($internship->end_at) ?></p>
                    <p>
                        <strong>Heure d'arrivée journalière:</strong>
                        <?= $internship->day_start_at !== null
                            ? formatTime($internship->day_start_at)
                            : "Non défini" ?>
                    </p>
                    <p>
                        <strong>Heure de départ journalière :</strong>
                        <?= $internship->day_end_at !== null
                            ? formatTime($internship->day_end_at)
                            : "Non défini" ?>
                    </p>
                </div>
            </div>
        @endforeach
    </div>
</div>
