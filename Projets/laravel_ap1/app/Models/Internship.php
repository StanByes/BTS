<?php

namespace App\Models;

use Eloquent;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Casts\Attribute;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Support\Carbon;

/**
 * 
 *
 * @property int $id
 * @property int $intern_id
 * @property int $supervisor_id
 * @property string $start_at
 * @property string $end_at
 * @property string $day_start_at
 * @property string $day_end_at
 * @property Carbon|null $created_at
 * @property Carbon|null $updated_at
 * @method static Builder<static>|Internship newModelQuery()
 * @method static Builder<static>|Internship newQuery()
 * @method static Builder<static>|Internship query()
 * @method static Builder<static>|Internship whereCreatedAt($value)
 * @method static Builder<static>|Internship whereDayEndAt($value)
 * @method static Builder<static>|Internship whereDayStartAt($value)
 * @method static Builder<static>|Internship whereEndAt($value)
 * @method static Builder<static>|Internship whereId($value)
 * @method static Builder<static>|Internship whereInternId($value)
 * @method static Builder<static>|Internship whereStartAt($value)
 * @method static Builder<static>|Internship whereSupervisorId($value)
 * @method static Builder<static>|Internship whereUpdatedAt($value)
 * @property-read User $intern
 * @property-read User $supervisor
 * @property mixed $end_start_at
 * @mixin Eloquent
 */
class Internship extends Model
{
    protected $fillable = [
        'start_at',
        'end_at',
        'day_start_at',
        'day_end_at'
    ];

    protected $casts = [
        'start_at' => 'date',
        'end_at' => 'date',
        'day_start_at' => 'datetime',
        'day_end_at' => 'datetime'
    ];

    public function intern(): BelongsTo
    {
        return $this->belongsTo(User::class, 'intern_id');
    }

    public function supervisor(): BelongsTo
    {
        return $this->belongsTo(User::class, 'supervisor_id');
    }
}
