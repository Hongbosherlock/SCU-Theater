/**
 * Created by 小兔头 on 2018/6/18.
 */
$(function(){
    //发送验证码
    $(".sendVerifyCode").on("click", function(){
        var number = $("input[name=number]").val();
        $.ajax({
            url: "",//待填
            async : true,
            type: "post",
            dataType: "json",
            data: {"number":number},
            success: function (data) {
                if(data == 'fail'){
                    alert("发送验证码失败");
                    return ;
                }
            }
        });
    })
    //提交
    $(".sub-btn").on("click", function(){
        var data = {};
        data.number = $.trim($("input[name=number]").val());
        data.verifyCode = $.trim($("input[name=verifyCode]").val());
        $.ajax({
            url: getBasePath()+"/admin_register.html",
            async : true,
            type: "post",
            dataType: "json",
            data: data,
            success: function (data) {
                if(data == 'fail'){
                    alert("您输入的验证码有误");
                    return ;
                }
                window.location.href = "html/passwordreset.html";//还未做此页
            }
        });
    })
});
