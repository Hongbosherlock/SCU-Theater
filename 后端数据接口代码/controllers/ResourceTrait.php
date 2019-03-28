<?php

namespace App\Http\Controllers;

use App\Room;
use Illuminate\Http\Request;

trait ResourceTrait
{
    //
    public function matchSeat($room_id, $seats)
    {
        foreach ($seats as $k => $v) {
            $seats[$k]['occupied'] = 0;
        }

        $room = Room::find($room_id);

        $seatmap = [];

        for ($i = 0; $i < $room['row']; $i++) {
            for ($j = 0; $j < $room['col']; $j++) {
                $seatmap[$i][$j] = '_';
            }
        }

        foreach ($seats as $s) {
            $seatmap[$s['row'] - 1][$s['col'] - 1] = 'a';
        }

        foreach ($seatmap as $k => $v) {
            $seatmap[$k] = implode('', $v);
        }

        return $seatmap;
    }
}
