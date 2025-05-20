<?php

namespace App\Models;

use Eloquent;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Support\Carbon;

/**
 * 
 *
 * @property int $id
 * @property int $creator_id
 * @property string $title
 * @property string $content
 * @property Carbon|null $created_at
 * @property Carbon|null $updated_at
 * @method static Builder<static>|Report newModelQuery()
 * @method static Builder<static>|Report newQuery()
 * @method static Builder<static>|Report query()
 * @method static Builder<static>|Report whereContent($value)
 * @method static Builder<static>|Report whereCreatedAt($value)
 * @method static Builder<static>|Report whereCreatorId($value)
 * @method static Builder<static>|Report whereId($value)
 * @method static Builder<static>|Report whereTitle($value)
 * @method static Builder<static>|Report whereUpdatedAt($value)
 * @property-read User|null $user
 * @property Carbon $date
 * @property-read \App\Models\User $creator
 * @method static Builder<static>|Report whereDate($value)
 * @mixin Eloquent
 */
class Report extends Model
{
    protected $casts = [
        'date' => 'date',
        'created_at' => 'datetime'
    ];

    protected $fillable = ['title', 'content', 'date'];

    public function creator(): BelongsTo
    {
        return $this->belongsTo(User::class, 'creator_id', 'id');
    }
}
