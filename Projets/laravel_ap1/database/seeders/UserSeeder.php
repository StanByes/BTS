<?php

namespace Database\Seeders;

use App\Enum\RoleEnum;
use App\Models\Role;
use Database\Factories\UserFactory;
use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        UserFactory::new()->create(['role_id' => Role::where('name', '=', RoleEnum::INTERN->value)->first()->id]);
        UserFactory::new()->create(['role_id' => Role::where('name', '=', RoleEnum::SUPERVISOR->value)->first()->id]);
        UserFactory::new()->create(['role_id' => Role::where('name', '=', RoleEnum::GESTION->value)->first()->id]);
    }
}
