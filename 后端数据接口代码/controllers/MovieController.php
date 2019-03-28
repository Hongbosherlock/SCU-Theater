<?php

namespace App\Http\Controllers;

use App\Exceptions\ApiException;
use App\Movie;
use Illuminate\Http\Request;
use Log;

class MovieController extends Controller
{
    //

    public function list()
    {
        $m = Movie::get();

        return [
            'code' => 0,
            'message' => '',
            'data' => $m
        ];
    }

    public function get()
    {
        $id = request('id');

        try {
            $m = Movie::findOrFail($id);
        } catch (\Exception $e) {
            throw new ApiException(211);
        }

        return [
            'code' => 0,
            'message' => '',
            'data' => $m
        ];
    }

    public function find()
    {
        $name = request('name');
        $m = Movie::where('name', 'like', '%' . $name . '%')->get();

        return [
            'code' => 0,
            'message' => '',
            'data' => $m
        ];
    }

    public function create()
    {
        $data = request('data');

        $s = Movie::create($data);

        return [
            'code' => 0,
            'message' => '电影添加成功',
            'data' => $s
        ];
    }

    public function update()
    {
//        $id = request('id');
        $data = request('data');

        $m = $this->get()['data'];

        $m->update($data);

        return [
            'code' => 0,
            'message' => '更新成功',
            'data' => $m
        ];
    }

    public function delete()
    {
        $id = request('id');

        $m = $this->get()['data'];

        $m->delete();

        return [
            'code' => 0,
            'message' => '删除成功'
        ];
    }
}
