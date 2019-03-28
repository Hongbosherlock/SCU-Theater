<?php

namespace App\Http\Controllers;

use App\Admin;
use App\Exceptions\ApiException;
use Illuminate\Http\Request;

class AdminController extends Controller
{
    //
    public function login()
    {
        $account = request('account');
        $password = request('password');

        try {
            $a = Admin::where('account', $account)->firstOrFail();
        } catch (\Exception $e) {
            throw new ApiException(111);
        }

        if ($a['password'] != $password || ) {
            throw new ApiException(112);
        }

        return [
            'code' => 0,
            'message' => '登录成功',
            'data' => $a
        ];
    }

    public function list()
    {
        $a = Admin::get();

        return [
            'code' => 0,
            'message' => '',
            'data' => $a
        ];
    }

    public function get()
    {
        $id = request('id');

        try {
            $a = Admin::findOrFail($id);
        } catch (\Exception $e) {
            throw new ApiException(111);
        }

        return [
            'code' => 0,
            'message' => '',
            'data' => $a
        ];
    }

    public function create()
    {
        $role = request('role');
        $account = request('account');
        $password = request('password');

        $admin = Admin::firstOrCreate([
            'account' => $account
        ], [
            'password' => $password,
            'role' => $role
        ]);

        throw_if(! $admin->wasRecentlyCreated, new ApiException(113));

        return [
            'code' => 0,
            'message' => '创建成功',
            'data' => $admin
        ];
    }
}
