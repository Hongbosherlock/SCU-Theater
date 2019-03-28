<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Milon\Barcode\DNS2D;

class QRController extends Controller
{
    //
    public function get($code)
    {
        echo "<div align=\"center\">" . DNS2D::getBarcodeHTML(hexdec($code), "QRCODE") . "</div>";
    }
}
