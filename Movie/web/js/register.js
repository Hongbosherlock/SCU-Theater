/**
 * Created by gnz on 2018/11/27.
 */
var Register = function() {
    var handleRegister = function() {
        $('#register').click(function () {
            if ($('.m-t').validate().form()) {
                var phone = $('#userPhone').val();
                var name = $('#userName').val();
                // var sex = $('input:radio[name="sex"]:checked').val();
                var role =$("#select option:selected").val;
                var password = $('#confirm_password').val();
                var registerData = {};
                registerData.phone=phone;
                registerData.name=name;
                // registerData.sex=sex;
                registerData.role=role;
                registerData.password=password;
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
                            window.location.href = "../html/user_login.html";
                        }else if(data.flag==false){
                            // alert(data.info)
                            alert("注册失败！");
                        }else {
                            alert("系统异常,请联系管理员!");
                        }
                    }
                });
            }
            return false;
        });
    }
    return {
        //main function to initiate the module
        init: function() {
            handleRegister();
        }
    };
}(jQuery);


// (function () {
//     $('#register').click(function () {
//         var registerData=$("#registerForm").serialize();
//
//         // ajax请求后台
//         $.ajax({
//             url: "regist",
//             type: "post",
//             data: registerData,
//             success: function (data) {
//                 console.log(data);
//                 $('#loginSmall').text("注册成功");
//
//                 // window.location.href = "/index.jsp";
//             }
//         });
//
//     });
// })(jQuery);