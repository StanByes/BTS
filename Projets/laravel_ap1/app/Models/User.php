<?php

namespace App\Models;

use App\Enum\RoleEnum;
use Eloquent;
use Illuminate\Contracts\Mail\Mailable;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Casts\Attribute;
use Illuminate\Database\Eloquent\Collection;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\HasOne;
use Illuminate\Support\Carbon;

/**
 *
 *
 * @property int $id
 * @property string $firstname
 * @property string $surname
 * @property string $login
 * @property string $mail
 * @property string $password
 * @property int $role_id
 * @property Carbon|null $created_at
 * @property Carbon|null $updated_at
 * @property-read Role $role
 * @method static Builder<static>|User newModelQuery()
 * @method static Builder<static>|User newQuery()
 * @method static Builder<static>|User query()
 * @method static Builder<static>|User whereCreatedAt($value)
 * @method static Builder<static>|User whereFirstname($value)
 * @method static Builder<static>|User whereId($value)
 * @method static Builder<static>|User whereLogin($value)
 * @method static Builder<static>|User whereMail($value)
 * @method static Builder<static>|User wherePassword($value)
 * @method static Builder<static>|User whereRoleId($value)
 * @method static Builder<static>|User whereSurname($value)
 * @method static Builder<static>|User whereUpdatedAt($value)
 * @property-read Collection<int, Report> $reports
 * @property-read int|null $reports_count
 * @property-read Internship|null $internship
 * @property-read Collection<int, Internship> $interships
 * @property-read int|null $interships_count
 * @property-read mixed $complete_name
 * @property-read Collection<int, Internship> $internships
 * @property-read int|null $internships_count
 * @property-read \App\Models\ResetPasswordQuery|null $resetPasswordQuery
 * @mixin Eloquent
 */
class User extends \Illuminate\Foundation\Auth\User
{
    protected $fillable = ['firstname', 'surname', 'mail'];

    public function role(): BelongsTo
    {
        return $this->belongsTo(Role::class);
    }

    public function reports(): HasMany
    {
        return $this->hasMany(Report::class, 'creator_id');
    }

    public function internship(): HasOne
    {
        return $this->hasOne(Internship::class, 'intern_id');
    }

    public function internships(): HasMany
    {
        return $this->hasMany(Internship::class, 'supervisor_id');
    }

    public function resetPasswordQuery(): HasOne
    {
        return $this->hasOne(ResetPasswordQuery::class);
    }

    protected function completeName(): Attribute
    {
        return Attribute::make(
            get: fn (mixed $value, array $attributes) => $attributes['firstname'] . " " . $attributes['surname']
        );
    }

    public static function getByRole(RoleEnum $role): Builder
    {
        return self::whereHas('role', function ($query) use ($role) {
            $query->where('name', $role);
        });
    }
}
