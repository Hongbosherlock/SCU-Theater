<?php

namespace App\Http\Controllers;

use App\Exceptions\ApiException;
use App\Vip;
use Illuminate\Http\Request;

class VipController extends Controller
{
    //
    use SMSTrait;
    public function login()
    {
        $mobile = request('mobile');
        $password = request('password');

        try {
            $v = Vip::where('mobile', $mobile)->firstOrFail();
        } catch (\Exception $e) {
            throw new ApiException(111);
        }

        if (decrypt($v['password']) != $password) {
            throw new ApiException(112);
        }

        return [
            'code' => 0,
            'message' => '登录成功',
            'data' => $v
        ];
    }

    public function create()
    {
        $mobile = request('mobile');
        $password = request('password');
        $name = request('name');

        $vip = Vip::firstOrCreate([
            'mobile' => $mobile
        ], [
            'password' => encrypt($password),
            'name' => $name
        ]);

        throw_if(! $vip->wasRecentlyCreated, new ApiException(113));

        $this->sendSMS($mobile, 248354, [$name]);

        return [
            'code' => 0,
            'message' => '创建成功',
            'data' => $vip
        ];
    }

    public function list()
    {
        $v = Vip::get();

        return [
            'code' => 0,
            'message' => '',
            'data' => $v
        ];
    }

    public function get()
    {
        $id = request('id');
        $mobile = request('mobile');

        try {
            if (! is_null($id)) {
                $v = Vip::findOrFail($id);
            } elseif (! is_null($mobile)) {
                $v = Vip::where('mobile', $mobile)->firstOrFail();
            } else {
                throw new \Exception;
            }
        } catch (\Exception $e) {
            throw new ApiException(111);
        }

        return [
            'code' => 0,
            'message' => '',
            'data' => $v
        ];
    }

    public function update()
    {
        $data = request('data');

        if (isset($data['mobile'])) {
            unset($data['mobile']);
        }

        $v = $this->get()['data'];

        $v->update($data);

        return [
            'code' => 0,
            'message' => '更新成功',
            'data' => $v
        ];
    }
}
