<?php

namespace Database\Seeders;

use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Env;

class DatabaseSeeder extends Seeder
{
    public function run(): void
    {
        $seeders = [RoleSeeder::class];
        if (env('APP_ENV') === 'development') {
            $seeders[] = UserSeeder::class;
        }

        $this->call($seeders);
    }
}
