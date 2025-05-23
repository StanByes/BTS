<?php

namespace Database\Factories;

use App\Enum\RoleEnum;
use App\Models\Role;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Facades\Hash;

/**
 * @extends Factory<User>
 */
class UserFactory extends Factory
{
    /**
     * The current password being used by the factory.
     */
    protected static ?string $password;

    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'firstname' => fake()->firstName(),
            'surname' => fake()->lastName(),
            'login' => fake()->unique()->userName(),
            'mail' => fake()->unique()->safeEmail(),
            'password' => static::$password ??= Hash::make('password')
        ];
    }
}
