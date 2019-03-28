<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

trait SMSTrait
{
    //
    public function sendSMS($mobile, $template, $params)
    {
        try {
            $ssender = new \Qcloud\Sms\SmsSingleSender(1400000000, "xxxxxxxx");
            $result = $ssender->sendWithParam("86", $mobile, $template,
                $params, "SCUPLUS", "", "");
            $rsp = json_decode($result);
/*
            $sms = SMS::insert([
                'stuid' => $stuid,
                'mobile' => $mobile,
                'template' => $template,
                'param' => implode(',', $params),
                'time' => time(),
                'result' => $rsp->result,
                'errmsg' => $rsp->errmsg,
                'sid' => $rsp->sid,
                'fee' => $rsp->fee
            ]);*/
        } catch(\Exception $e) {
            return $e;
        }
    }
}
