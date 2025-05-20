@foreach ($reports as $report)
    <div class="card mb-3">
        <div class="card-header">
            <div class="d-flex justify-content-between">
                <h4 class="text-center"><?= $report->title ?></h4>
                @if(auth()->user()->role->name == "supervisor")
                    <p class="text-center">
                        De <?= $report->creator->complete_name ?>
                        posté le <?= formatDateTime($report->created_at) ?>
                    </p>
                @else
                    <p class="text-center">Posté le <?= formatDateTime($report->created_at) ?></p>
                @endif
            </div>
        </div>
        <div class="card-body">
            <label class="form-label" for="content">Contenu du rapport :</label>
            <textarea disabled id="content" class="form-control" rows="3"><?= $report->content ?>
                    </textarea>
            <p class="mt-2"><strong>Date : </strong> <?= formatDate($report->date) ?></p>
        </div>
    </div>
@endforeach
