<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Vip extends Model
{
    //

    protected $table = 'vips';

    protected $primaryKey = 'id';

    protected $fillable = ['mobile', 'points', 'discount', 'password', 'name'];

    protected $hidden = ['password'];

    public $timestamps = false;
}
