<?php

namespace App\Exceptions;

use Exception;

class ApiException extends Exception
{
    //
    function __construct($code = -1)
    {
        $this->code = $code;
        $this->definition = [
            -1 => '未知错误',
            111 => '用户/管理员不存在',
            112 => '密码错误',
            113 => '账号（mobile / account）已存在',
            211 => '电影不存在',
            311 => '未找到任何场次',
            411 => '未找到订单',
            421 => '座位已被占用',
        ];
        parent::__construct($code);
    }

    public function render()
    {
        return [
            'code' => $this->code,
            'message' => $this->definition[$this->code]
        ];
    }
}
