<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    //
    protected $table = 'orders';

    protected $primaryKey = 'id';

    protected $fillable = ['source', 'user_id', 'token', 'time', 'num', 'price'];

    public $timestamps = false;
}
