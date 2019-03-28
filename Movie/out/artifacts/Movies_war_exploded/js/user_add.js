/**
 * Created by 小兔头 on 2018/6/5.
 */
(function () {
    $('#user_add').click(function () {
        var addData=$("#addForm").serialize();

        // ajax请求后台
        $.ajax({
            url: "userAdd",
            type: "post",
            data: addData,
            success: function(data){
                if(data.value=="true") {
                    alert(data.info);
                    $("#info").text(data.info);
                    window.location.href = "index_v5.html";
                }
                else{
                    alert(data.info);
                }
            },
            error:function () {
                alert("eeeor");
            }
        });

    });
})(jQuery);


