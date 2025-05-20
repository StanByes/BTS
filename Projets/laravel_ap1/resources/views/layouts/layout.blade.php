<html lang="fr">
<head>
    <title>@yield('title')</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <header>
        @auth
            <nav class="nav d-flex justify-content-end">
                <a class="btn btn-close rounded bg-danger m-2" href="./logout"></a>
            </nav>
        @endauth

        @if (session('flash'))
            <div class="bg-<?= session('flash')['error'] ? "danger" : "success" ?> w-75 z-1 p-2 mt-2 mx-auto rounded">
                <h4 class="text-center">{{ session('flash')['message'] }}</h4>
            </div>
        @endif

        @if ($errors->any())
            <div class="alert alert-danger">
                <ul>
                    @foreach ($errors->all() as $error)
                        <li>{{ $error }}</li>
                    @endforeach
                </ul>
            </div>
        @endif
    </header>
    <main class="mt-4">
        <div class="container-fluid">
            <div class="card mx-auto @yield('card_size', 'w-50')">
                <div class="card-header">
                    <h4 class="ms-2 text-center">@yield('card_title')</h4>
                </div>

                <div class="card-body">
                    @yield('content')
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
