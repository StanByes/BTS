<?php

namespace App\Models\Scopes;

use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Scope;

class ActivePasswordQuery implements Scope
{
    public function apply(Builder $builder, Model $model): void
    {
        $builder->where('active', '=', true)->where('unavailable_at', '>', 'NOW()');
    }
}
