<?php

namespace Database\Seeders;

use App\Models\Role;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class RoleSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Role::insert([
            ['name' => 'intern', 'display_name' => 'Stagiaire', 'created_at' => now(), 'updated_at' => now()],
            ['name' => 'supervisor', 'display_name' => 'Maitre de stage', 'created_at' => now(), 'updated_at' => now()],
            ['name' => 'gestion', 'display_name' => 'Gestionnaire', 'created_at' => now(), 'updated_at' => now()]
        ]);
    }
}
