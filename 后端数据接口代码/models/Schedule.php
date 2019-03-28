<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Schedule extends Model
{
    //

    protected $table = 'schedules';

    protected $primaryKey = 'id';

    protected $fillable = ['movie_id', 'date', 'start_time', 'room_id', 'price'];

    public $timestamps = false;
}
