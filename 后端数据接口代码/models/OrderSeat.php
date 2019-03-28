<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class OrderSeat extends Model
{
    //
    protected $table = 'order_seats';

    protected $primaryKey = 'id';

    protected $fillable = ['schedule_id', 'seat_id', 'order_id'];

    public $timestamps = false;
}
