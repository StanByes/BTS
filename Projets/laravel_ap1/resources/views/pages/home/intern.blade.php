<ul class="d-flex justify-content-around nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#reports">Mes Rapports</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#create">Ajouter un rapport</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#profil">Modifier mon profil</a>
    </li>
</ul>

<div class="tab-content mt-5">
    <div class="tab-pane container active" id="reports">
        @include('components.reports_list', ['reports' => auth()->user()->reports])
    </div>

    <div class="tab-pane container fade" id="create">
        <form method="post" action="/reports/">
            @csrf
            <div class="form-group mb-3">
                <label class="form-label" for="title">Titre :</label>
                <input type="text" class="form-control" name="title" id="title" required="required">
            </div>

            <div class="form-group mb-3">
                <label class="form-label" for="content">Contenu :</label>
                <textarea class="form-control" name="content" id="content" rows="3" required="required"></textarea>
            </div>

            <div class="form-group mb-3">
                <label class="form-label" for="date">Date :</label>
                <input class="form-control" name="date" id="date" type="date" required="required">
            </div>

            <div class="d-flex justify-content-center mb-4">
                <button class="btn btn-success w-75">Valider</button>
            </div>
        </form>
    </div>

    <div class="tab-pane container fade" id="profil">
        <form method="post" action="/users/{{ auth()->user()->id }}">
            @method('PUT')
            @csrf

            <div class="row">
                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="surname">Nom :</label>
                    <input class="form-control" name="surname" id="surname" required="required"
                           value="{{ auth()->user()->surname }}">
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="firstname">Prénom :</label>
                    <input class="form-control" name="firstname" id="firstname" required="required"
                           value="{{ auth()->user()->firstname }}">
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="mail">Adresse mail :</label>
                    <input type="email" class="form-control" name="mail" id="mail" required="required"
                           value="{{ auth()->user()->mail }}">
                </div>
            </div>

            <hr>

            <div class="row">
                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="start_at">Date de début de stage :</label>
                    <input type="date" class="form-control" name="start_at" id="start_at" required="required"
                           value="{{ formatDateForElement(auth()->user()->internship->start_at) }}">
                </div>

                <div class="form-group col-6">
                    <label class="form-label" for="end_at">Date de fin de stage :</label>
                    <input type="date" class="form-control" name="end_at" id="end_at" required="required"
                           value="{{ formatDateForElement(auth()->user()->internship->end_at) }}">
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="day_start_at">Heure de début de la journée :</label>
                    <input type="time" class="form-control" name="day_start_at" id="day_start_at"
                        {{ auth()->user()->internship->day_start_at !== null
                            ? "value=" . formatTimeForElement(auth()->user()->internship->day_start_at, false)
                            : "" }}>
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="day_end_at">Heure de fin de la journée :</label>
                    <input type="time" class="form-control" name="day_end_at" id="day_end_at"
                        {{ auth()->user()->internship->day_end_at !== null
                            ? "value=" . formatTimeForElement(auth()->user()->internship->day_end_at, false)
                            : "" }}>
                </div>

                <div class="d-flex justify-content-center mt-4">
                    <button class="btn btn-success w-75">Valider</button>
                </div>
            </div>
        </form>
    </div>
</div>
