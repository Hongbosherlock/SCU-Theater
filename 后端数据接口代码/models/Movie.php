<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Movie extends Model
{
    //
    protected $table = 'movies';

    protected $primaryKey = 'id';

    protected $fillable = ['name', 'duration', 'description', 'type', 'star'];

    public $timestamps = false;
}
