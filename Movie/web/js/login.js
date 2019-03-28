/**
 * Created by 小兔头 on 2018/6/4.
 */
(function () {
    $('#loginBtn').click(function () {

        var userPhone = $('#phone').val();
        var password = $('#password').val();
        var loginData = {};
        loginData.mobile=userPhone;
        loginData.password=password;
        console.log("login");
        // ajax请求后台
        $.ajax({
            url: "/ssm/user/login.do",
            type: "post",
            data: loginData,
            success: function (data) {
                console.log(data);
                data = JSON.parse(data);
                if(data.flag==true){
                    window.location.href = "./html/user_buy.html";
                }else if(data.flag==false){
                    alert("账号或密码错误!");
                }else {
                    alert("系统异常,请联系管理员!");
                }
            }
        });
    });

    $('#login').click(function () {

        var account = $('#account').val();
        var password = $('#password').val();
        var role =$("#select option:selected").val();
        var loginData = {};
        loginData.account=account;
        loginData.password=password;
        loginData.role=role;
        console.log("login");
        // console.log(loginData);
        // ajax请求后台
        $.ajax({
            url: "../admin/login.do",
            type: "post",
            data: loginData,
            success: function (data) {
                console.log(data);
                data = JSON.parse(data);
                var admin=data.admin;
                // console.log(admin);
                if(data.flag==true){
                    if (admin.role==true)
                    window.location.href = "../html/admin.html";
                    else
                        window.location.href = "../html/solder_showVip.html";
                }else if(data.flag==false){
                    alert("账号或密码错误!");
                }else {
                    alert("系统异常,请联系管理员!");
                }
            }
        });
    });

})(jQuery);
