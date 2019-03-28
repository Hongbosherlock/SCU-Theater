/**
 * Created by 小兔头 on 2018/6/5.
 */
(function () {
    $('#register').click(function () {
        var registerData=$("#registerForm").serialize();

            // ajax请求后台
            $.ajax({
                url: "../user/addUser.do",//此后更改为对应controller里的url
                type: "post",
                data: registerData,
                success: function (data) {
                    console.log(data);
                    data = JSON.parse(data);
                    if(data.flag==true){
                        alert("注册成功！将返回登录界面");
                        window.location.href = "../index.jsp";
                    }else if(data.flag==false){
                        // alert(data.info)
                        alert("注册失败！");
                    }else {
                        alert("系统异常,请联系管理员!");
                    }
                    $('#loginSmall').text("注册成功");

                    // window.location.href = "/index.jsp";
                }

            });

    });
})(jQuery);

