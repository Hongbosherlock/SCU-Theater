<?php

namespace App\Http\Controllers;

use App\Exceptions\ApiException;
use App\Movie;
use App\OrderSeat;
use App\Schedule;
use App\Seat;
use Illuminate\Http\Request;

class ScheduleController extends Controller
{
    //
    use ResourceTrait;

    public function get()
    {
        $id = request('id');

        try {
            $schedule = Schedule::findOrFail($id);
        } catch (\Exception $e) {
            throw new ApiException(311);
        }

        $movie = Movie::find($schedule->movie_id);

        $seats = Seat::where('room_id', $schedule->room_id)->get()->keyBy('id')->toArray();
        $orderSeat = OrderSeat::where('schedule_id', $schedule->id)->get();

        foreach ($seats as $k2 => $s) {
            $seats[$k2]['occupied'] = 0;
        }

        foreach ($orderSeat as $os) {
            $seats[$os['seat_id']]['occupied'] = 1;
        }

        $schedule['end_time'] = $this->getEndTime($schedule['date'], $schedule['start_time'], $movie['duration']);
        $schedule['seat_map'] = $this->matchSeat($schedule->room_id, $seats);
        $schedule['seat_count'] = count($orderSeat) . ' / ' . count($seats);
        $schedule['seats'] = array_values($seats);


        return [
            'code' => 0,
            'message' => '',
            'data' => $schedule
        ];
    }

    public function create()
    {
        $data = request('data');

        $s = Schedule::create($data);

        return [
            'code' => 0,
            'message' => '场次添加成功',
            'data' => $s
        ];
    }

    public function delete()
    {
        $id = request('id');

        $m = Schedule::find($id);

        $m->delete();

        return [
            'code' => 0,
            'message' => '删除成功'
        ];
    }

    public function listRaw()
    {
        $sch = Schedule::get();

        $movie = Movie::findMany($sch->pluck('movie_id')->all())->keyBy('id')->toArray();

        foreach ($sch as $k => $s)
        {
            $sch[$k]['movie_name'] = $movie[$s['movie_id']]['name'];
            $sch[$k]['end_time'] = $this->getEndTime($s['date'], $s['start_time'], $movie[$s['movie_id']]['duration']);
        }

        return [
            'code' => 0,
            'message' => '',
            'data' => $sch
        ];
    }

    public function list()
    {
        $sch = Schedule::get();

        $movie = Movie::findMany($sch->pluck('movie_id')->all())->keyBy('id')->toArray();

        foreach ($sch as $k => $s)
        {
            $sch[$k]['end_time'] = $this->getEndTime($s['date'], $s['start_time'], $movie[$s['movie_id']]['duration']);
        }

        $sch = $sch->groupBy('movie_id');

        foreach ($sch as $k => $s)
        {
            $sch[$k] = $sch[$k]->groupBy('date')->all();
        }

        $data = [];

        foreach ($sch as $k => $dates) {
            $moviedate = [];

            foreach ($dates as $k2 => $d) {
                $moviedate[] = [
                    'date' => $k2,
                    'table' => $d
                ];
            }

            $data[] = array_merge($movie[$k], [
                'moviedate' => $moviedate
            ]);
        }

        return [
            'code' => 0,
            'message' => '',
            'data' => $data
        ];
    }

    public function getEndTime($date, $startTime, $duration)
    {
        return date('H:i', strtotime($date . ' ' . $startTime) + $duration * 60);
    }
}
