<?php

namespace App\Models;

use App\Models\Scopes\ActivePasswordQuery;
use Illuminate\Database\Eloquent\Attributes\ScopedBy;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

#[ScopedBy(ActivePasswordQuery::class)]
/**
 *
 *
 * @property int $id
 * @property int $user_id
 * @property string $code
 * @property int $active
 * @property string $unavailable_at
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static Builder<static>|ResetPasswordQuery newModelQuery()
 * @method static Builder<static>|ResetPasswordQuery newQuery()
 * @method static Builder<static>|ResetPasswordQuery query()
 * @method static Builder<static>|ResetPasswordQuery whereActive($value)
 * @method static Builder<static>|ResetPasswordQuery whereCode($value)
 * @method static Builder<static>|ResetPasswordQuery whereCreatedAt($value)
 * @method static Builder<static>|ResetPasswordQuery whereId($value)
 * @method static Builder<static>|ResetPasswordQuery whereUnavailableAt($value)
 * @method static Builder<static>|ResetPasswordQuery whereUpdatedAt($value)
 * @method static Builder<static>|ResetPasswordQuery whereUserId($value)
 * @property-read \App\Models\User $user
 * @mixin \Eloquent
 */

#[ScopedBy([ActivePasswordQuery::class])]
class ResetPasswordQuery extends Model
{
    protected $fillable = ['code', 'active'];

    public function user(): BelongsTo
    {
        return $this->belongsTo(User::class);
    }

    public static function clearUnavailableQueries(): void
    {
        ResetPasswordQuery::where('unavailable', '=', 0)->orWhere('unavailable_at', '<', now())->delete();
    }
}
