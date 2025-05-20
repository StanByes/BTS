<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('internships', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('intern_id')->nullable(false);
            $table->unsignedBigInteger('supervisor_id')->nullable(false);
            $table->date('start_at')->nullable(false);
            $table->date('end_at')->nullable(false);
            $table->time('day_start_at')->nullable();
            $table->time('day_end_at')->nullable();
            $table->timestamps();

            $table->foreign('intern_id')->references('id')->on('users');
            $table->foreign('supervisor_id')->references('id')->on('users');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('internships');
    }
};
