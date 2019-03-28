<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.web.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>支付</title>

    <link rel="dns-prefetch" href="//p0.meituan.net">
    <link rel="dns-prefetch" href="//p1.meituan.net">
    <link rel="dns-prefetch" href="//ms0.meituan.net">
    <link rel="dns-prefetch" href="//ms1.meituan.net">
    <link rel="dns-prefetch" href="//analytics.meituan.com">
    <link rel="dns-prefetch" href="//report.meituan.com">
    <link rel="dns-prefetch" href="//frep.meituan.com">


    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta http-equiv="cleartype" content="yes">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">

    <meta name="HandheldFriendly" content="true">
    <meta name="format-detection" content="email=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <script async="" src="../js/meitu/mta.min.js"></script><script>"use strict";!function(){var i=0<arguments.length&&void 0!==arguments[0]?arguments[0]:"_Owl_",n=window;n[i]||(n[i]={isRunning:!1,isReady:!1,preTasks:[],dataSet:[],use:function(i,t){this.isReady&&n.Owl&&n.Owl[i](t),this.preTasks.push({api:i,data:[t]})},add:function(i){this.dataSet.push(i)},run:function(){var t=this;if(!this.isRunning){this.isRunning=!0;var i=n.onerror;n.onerror=function(){this.isReady||this.add({type:"jsError",data:arguments}),i&&i.apply(n,arguments)}.bind(this),(n.addEventListener||n.attachEvent)("error",function(i){t.isReady||t.add({type:"resError",data:[i]})},!0)}}},n[i].run())}();</script>
    <script>
        cid = "c_wmvmtivj";
        ci = 59;
        window.system = {"user":{"id":504001033,"token":"Cu9dQfO0xdF-0zQ_NZJEZ2NaimIAAAAAfwcAAGMETVBKwZPCl5yIpa4tQa2qUbVGfe94aQTD5iWhpTdkPpzcIdrVwCb8UHbRW-EHTw","username":"cpX509580302","profile":{"age":"25","assistAwardInfo":{},"avatarType":255,"avatarurl":"https://img.meituan.net/avatar/b43019558460e8977bf8c10c6457e0573724.jpg","birthday":-2240553957000,"currentExp":0,"gender":1,"id":504001033,"juryLevel":0,"maoyanAge":"2","nextTitle":"","nickName":"cpX509580302","nickNameStatus":0,"registerTime":1467977210000,"roleType":0,"signatureStatus":0,"ticketCount":0,"title":"青铜会员","topicCount":0,"totalExp":130,"uid":1141710399,"userLevel":1,"userNextLevel":2,"username":"cpX509580302","vipType":0,"visitorCount":0}},"uid":504001033,"cinemaId":16399,"mobilePhone":"18161010975","orderId":"3523303386"};

        window.openPlatform = '';
        window.openPlatformSub = '';

    </script>
    <link rel="stylesheet" href="../css/title.css">
    <link rel="stylesheet" href="//ms0.meituan.net/mywww/common.4b838ec3.css">
    <link rel="stylesheet" href="//ms0.meituan.net/mywww/order-confirm.598bbaa8.css">
    <script crossorigin="anonymous" src="../js/meitu/stat.74891044.js"></script><script async="" defer="" charset="utf-8" src="//analytics.meituan.com/source/stable/web.js"></script>
    <script>if(window.devicePixelRatio >= 2) { document.write('<link rel="stylesheet" href="//ms0.meituan.net/mywww/image-2x.8ba7074d.css"/>') }</script><link rel="stylesheet" href="//ms0.meituan.net/mywww/image-2x.8ba7074d.css">

    <meta name="lx:autopv" content="off"><meta name="lx:appnm" content="movie"><meta name="lx:category" content="movie">
    <script src="../js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript">
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
        var id = getUrlParam("id");
        var name = getUrlParam('name');
        name = decodeURI(name);
        var month = getUrlParam('month');
        var date = getUrlParam('date');
        var time= getUrlParam('time');
        var price =getUrlParam('price');
        var total = getUrlParam('price');
        var aloneprice =getUrlParam('aloneprice');
        var movietype=getUrlParam('movietype');
        movietype = decodeURI(movietype);
        var language=getUrlParam('language');
        language = decodeURI(language);
        var movielength=getUrlParam('movielength');
        movielength = decodeURI(movielength);
        var seat = getUrlParam('seat');
        var str=getUrlParam('seat');
        var hall=getUrlParam('hall');
        hall = decodeURI(hall);
        var arrseat = seat.split(",");
         // console.log(hall);

        var arr = new Array();  //先声明一维
        for(var k=0;k<arrseat.length/2-1;k++){    //一维长度为i,i为变量，可以根据实际情况改变
            arr[k]=new Array();  //声明二维，每一个一维数组里面的一个元素都是一个数组；
            for(var j=0;j<2;j++){   //一维数组里面每个元素数组可以包含的数量p，p也是一个变量；
                arr[k][j]="";    //这里将变量初始化，我这边统一初始化为空，后面在用所需的值覆盖里面的值
            }
        }
        // console.log(arr);
        for(var i=0,j=0;j<arrseat.length-1;i++,j+=2){
            arr[i][0]=arrseat[j];
            arr[i][1]=arrseat[j+1];
            // console.log(arr[i][0]);
            // console.log(arr[i][1]);
        }
        // console.log(arr);

        var  seatarr = getUrlParam('seatarr').split(",");
        console.log(seatarr);


        $(function(){
            $("td.movie-name").prepend(name);
            $("td.showtime").prepend(date+" "+time);
            var eachcount=0;
            $.each(arrseat,function(i,item){
                if(eachcount<arrseat.length-1){
                    $(".seats").append("<div><span class=''><i>"+arrseat[eachcount]+"</i>排<i>"+arrseat[eachcount+1]+"</i>座</span></div>");
                }
                eachcount+=2;
            });
            $(".price").prepend(price);
        });
        // console.log(arr);
    </script>
</head>
<body>


<div class="header">
    <div class="header-inner">
        <!--<a href="" class="logo" data-act="icon-click"></a>-->
        <a  href="" class="ooo" data-act="icon-click"></a><a class="ooo2"></a>
        <div class="city-container" data-val="{currentcityid:59 }">
            <div class="city-selected">
                <div class="city-name">
                    成都
                    <span class="caret"></span>
                </div>
            </div>
        </div>


        <div class="nav">
            <ul class="navbar">

            </ul>
        </div>

        <div class="user-info">
            <div class="user-avatar has-login">
                <img src="../images/avatar.jpg">
                <span class="caret"></span>
                <ul class="user-menu">
                    <li class="text login-name" title="cpX509580302"><a href="../jsp/user_profile.jsp">基本信息</a></li>
                    <li class="text"><a href="javascript:void 0" class="J-logout" data-act="logout-click"/><a href="../index.jsp">退出登录</a></li>
                </ul>
            </div>
        </div>

    </div>
</div>
<div class="header-placeholder"></div>


<div class="container" id="app">
    <div class="order-progress-bar">
        <div class="step first done">
            <span class="step-num">1</span>
            <div class="bar"></div>
            <span class="step-text">选择影片场次</span>
        </div>
        <div class="step done">
            <span class="step-num">2</span>
            <div class="bar"></div>
            <span class="step-text">选择座位</span>
        </div>
        <div class="step done">
            <span class="step-num">3</span>
            <div class="bar"></div>
            <span class="step-text">14分钟内付款</span>
        </div>
        <div class="step last ">
            <span class="step-num">4</span>
            <div class="bar"></div>
            <span class="step-text">影院取票观影</span>
        </div>
    </div>



    <p class="warning">请仔细核对场次信息，出票后将<span class="attention">无法退票和改签</span></p>

    <table class="order-table">
        <thead>
        <tr>
            <th>影片</th>
            <th>时间</th>
            <th>影院</th>
            <th>座位</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="movie-name"></td>
            <td class="showtime"></td>
            <td class="cinema-name">SCU影城(川大店)</td>
            <td>
                <span class="hall"></span>
                <div class="seats">
                </div>
            </td>
        </tr>
        </tbody>
    </table>


    <div class="right">
        <div class="price-wrapper">
            <span>实际支付 :</span>
            <span class="price"></span>
        </div>
        <div>
            <button type="button" onclick="jump()" class="pay-btn" data-order-id="3523303386" data-act="pay-click" data-bid="b_u30afks6" >确认支付</button>
        </div>
    </div>

    <div class="modal-container" style="display:none">
        <div class="modal">
            <span class="icon"></span>

            <p class="tip">支付超时，该订单已失效，请重新购买</p>

            <div class="ok-btn btn">我知道了</div>
        </div>
    </div>

</div>


<style type="text/css">#yddContainer{display:block;font-family:Microsoft YaHei;position:relative;width:100%;height:100%;top:-4px;left:-4px;font-size:12px;border:1px solid}#yddTop{display:block;height:22px}#yddTopBorderlr{display:block;position:static;height:17px;padding:2px 28px;line-height:17px;font-size:12px;color:#5079bb;font-weight:bold;border-style:none solid;border-width:1px}#yddTopBorderlr .ydd-sp{position:absolute;top:2px;height:0;overflow:hidden}.ydd-icon{left:5px;width:17px;padding:0px 0px 0px 0px;padding-top:17px;background-position:-16px -44px}.ydd-close{right:5px;width:16px;padding-top:16px;background-position:left -44px}#yddKeyTitle{float:left;text-decoration:none}#yddMiddle{display:block;margin-bottom:10px}.ydd-tabs{display:block;margin:5px 0;padding:0 5px;height:18px;border-bottom:1px solid}.ydd-tab{display:block;float:left;height:18px;margin:0 5px -1px 0;padding:0 4px;line-height:18px;border:1px solid;border-bottom:none}.ydd-trans-container{display:block;line-height:160%}.ydd-trans-container a{text-decoration:none;}#yddBottom{position:absolute;bottom:0;left:0;width:100%;height:22px;line-height:22px;overflow:hidden;background-position:left -22px}.ydd-padding010{padding:0 10px}#yddWrapper{color:#252525;z-index:10001;background:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ab20.png);}#yddContainer{background:#fff;border-color:#4b7598}#yddTopBorderlr{border-color:#f0f8fc}#yddWrapper .ydd-sp{background-image:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ydd-sprite.png)}#yddWrapper a,#yddWrapper a:hover,#yddWrapper a:visited{color:#50799b}#yddWrapper .ydd-tabs{color:#959595}.ydd-tabs,.ydd-tab{background:#fff;border-color:#d5e7f3}#yddBottom{color:#363636}#yddWrapper{min-width:250px;max-width:400px;}</style>
<script  language="JavaScript">

    var user_id="<%=((User)request.getSession().getAttribute("loginUser")).getId()%>";
    function jump(){
    // var jsonObj = new Object();
    //     $.fn.stringifyArray = function(array) {
    //         return JSON.stringify(array)
    //     };
    //
    //     $.fn.parseArray = function(array) {
    //         return JSON.parse(array)
    //     };
    //
    //     var s = $.fn.stringifyArray(seatarr);
        seatarr = Array.from(seatarr);

        var Data = {};
        Data.source = 1;
        Data.user_id =user_id;
        Data.schedule_id =id ;
        Data.seat_id=seatarr;
        Data.price = price;

        console.log(Data);
        $.ajax({
            url: "../ticket/addTicket.do",//此后更改为对应controller里的url
            type: "post",
            dataType:"json",
            contentType: "application/json; charset=utf-8",//此处不能省略
            data: JSON.stringify(Data),
            success: function (data) {
                console.log(data);
                if(data.flag===true){
                    window.location.href="../html/ticket.html?&name="+encodeURI(encodeURI(name))+"&month="+month+"&date="+date+"&time="+time+"&hall="+encodeURI(encodeURI(hall))+"&aloneprice="+aloneprice+"&price="+total+"&movielength="+encodeURI(encodeURI(movielength))+"&movietype="+encodeURI(encodeURI(movietype))+"&language="+encodeURI(encodeURI(language))+"&seat="+str;
                    ///////注意此处要把上面那句windo.location移过来！！！！！
                }else if(data.flag===false){
                    alert("座位已被占用!，请重新选择")
                    window.location.href="../html/choose_seat.html?&name="+encodeURI(encodeURI(name))+"&date="+date+"&time="+time+"&hall="+encodeURI(encodeURI(hall))+"&price="+aloneprice+"&movielength="+encodeURI(encodeURI(movielength))+"&movietype="+encodeURI(encodeURI(movietype))+"&language="+encodeURI(encodeURI(language))+"&id="+id;
                }else {
                    alert("系统异常,请联系管理员!");
                }
            }
        });


    }
</script>
<div class="footer" style="visibility: visible;">


    <p>成都SCU文化传媒有限公司</p>
</div>
</body>
</html>