<?php

namespace App\Http\Controllers;

use App\Exceptions\ApiException;
use App\Movie;
use App\Order;
use App\OrderSeat;
use App\Schedule;
use App\Seat;
use App\Vip;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class OrderController extends Controller
{
    //
    use SMSTrait;

    public function getToken()
    {
        while (1) {
            $token = rand(100000000000, 999999999999);
            try {
                $ord = Order::where('token', $token)->firstOrFail();
            } catch (\Exception $e) {
                return $token;
            }
        }
    }

    public function create()
    {
        $data = request('data');

        $transaction = DB::transaction(function () use ($data, &$seatName, &$ord) {
            $ord = Order::create([
                'token' => $this->getToken(),
                'time' => time(),
                'source' => $data['source'],
                'user_id' => $data['user_id'],
                'num' => count($data['seat_id']),
                'price' => $data['price'],
            ]);

            $seatName = '';

            foreach ($data['seat_id'] as $sid) {
                $seatName .= ',' . Seat::find($sid)->name;
                $ors = OrderSeat::firstOrCreate([
                    'schedule_id' => $data['schedule_id'],
                    'seat_id' => $sid
                ], [
                    'order_id' => $ord->id
                ]);

                throw_if(! $ors->wasRecentlyCreated, new ApiException(421));
            }
        });

        if($data['source'] == 1) {
            $vip = Vip::find($data['user_id']);
            $schedule = Schedule::find($data['schedule_id']);
            $movie = Movie::find($schedule->movie_id);

            $this->sendSMS($vip->mobile, 248371, [
                $vip->name,
                $schedule->date . ' ' . $schedule->start_time . ' ' .
                '《' . $movie->name . '》 ' . $schedule->room_id . '号厅 ' .
                substr($seatName, 1) . ' 共' . $ord->num . '张票',
                substr($ord->token, 0, 6) . ' ' . substr($ord->token, -6),
                dechex($ord->token) . ' '
            ]);
        }

        return [
            'code' => 0,
            'message' => '创建成功',
            'data' => $ord
        ];
    }

    public function get()
    {
        $id = request('id');
        $token = request('token');

        try {
            if (! is_null($id)) {
                $o = Order::findOrFail($id);
            } elseif (! is_null($token)) {
                $o = Order::where('token', $token)->firstOrFail();
            } else {
                throw new \Exception;
            }
        } catch (\Exception $e){
            throw new ApiException(411);
        }

        $order_seats = OrderSeat::where('order_id', $o->id)->get();

        $seats_id = $order_seats->pluck('seat_id');

        $seat = Seat::findMany($seats_id);

        $schedule_id = $order_seats->pluck('schedule_id');
        $schedule = Schedule::findMany($schedule_id);

        return[
            'code' => 0,
            'message' => '',
            'data' => [
                'order' => $o,
                'seats_info' => $seat,
                'schedule_info' => $schedule
            ]
        ];
    }

    public function list()
    {
        $m = Order::get();

        return[
            'code' => 0,
            'message' =>'',
            'data' => $m
        ];
    }

    public function delete()
    {
        $id = request('id');

        $m = Order::find($id);

        $m->delete();

        $order_seats = OrderSeat::where('order_id', $id)->get();

        foreach ($order_seats as $one){
            $one->delete();
        }

        return[
            'code' => 0,
            'message' => '删除成功'
        ];
    }
}
