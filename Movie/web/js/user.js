(function ($) {
    var user = {};

    function searchUser() {
        var id = document.getElementById("userId").value;
        // ajax请求后台
        $.ajax({
            url: "../user/getuserbyid.do?id=" + id,
            type: "post",
            data: {},
            success: function (data) {
                if (data.flag == false) {
                    $('#userInfo').val("没有查询到编号是" + id + "的用户");
                    return;
                }
                if (data.flag == true) {
                    user = data.user;
                    var info = "编号：" + data.user.id + "\n"
                        + "账号：" + data.user.account + "\n"
                        + "用户名：" + data.user.name;
                    // $('#userInfo').val(data.user.name);
                    $('#userInfo').val(info);
                    $('#userName').val(user.name);
                    return;
                }
            }
        });
    }

    function changeUserName() {
        //    判断user是否为空对象
        if (JSON.stringify(user) == "{}") {
        alert("user is null, 先点击查询按钮获取用户信息");
        return
        }
        var name = document.getElementById("userName").value;
        if (name.length > 1 && user.name != name) {
            user.name = name;
            $.ajax({
                url: "../user/changename.do",
                type: "post",
                data: user,
                success: function (data) {
                    if (data.flag == false) {
                        alert("修改昵称失败");
                        return;
                    }
                    if (data.flag == true) {
                        alert("修改昵称成功");
                        return;
                    }
                }
            });
        }
        else {
            alert("昵称有误？ 没有修改？")
        }
    }

    /** 页面onload事件 */
    function init() {
        $('#searchUser').click(function () {
            searchUser();
        });
        $('#changeName').click(function () {
            changeUserName();
        });
    }

    $(init);
})(jQuery);
