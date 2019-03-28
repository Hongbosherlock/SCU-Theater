/**
 * Created by zxk175 on 16/12/3.
 */

$("#btn_print").on("click", function () {
    var printData = $('.bootstrap-table').parent().html();
    window.document.body.innerHTML = printData;
    // 开始打印
    window.print();
    window.location.reload(true);
});

//刷新页面
function refresh() {
    $table.bootstrapTable('refresh');
}

//查询按钮点击事件//////////////////////////此处原来只有refresh()，后做修改，查询完怎么返回来还没写可能要写成迭代吧
$("#btn_search").on("click", function () {
    // var phone= $('#phone').val("");
    //
    // // ajax请求后台
    // $.ajax({
    //     url: "../user/getuserbyid.do?id=" + id,
    //     type: "post",
    //     data: phone,
    //     success: function (data) {
    //         if (data.flag == false) {
    //             $('#userInfo').val("没有查询到符合条件的用户");
    //             return;
    //         }
    //         if (data.flag == true) {
    //             user = data.user;
    //             var info = "编号：" + data.user.id + "\n"
    //                 + "账号：" + data.user.account + "\n"
    //                 + "用户名：" + data.user.name;
    //             // $('#userInfo').val(data.user.name);
    //             $('#userInfo').val(info);
    //             $('#userName').val(user.name);
    //             return;
    //         }
    //     }
    // });
    refresh();
});

//清空条件按钮点击事件
$("#btn_clean_search").on("click", function () {
    $('#phone').val("");
    // $('#startDate2').val("");
    // $('#endDate2').val("");
    refresh();
});

//新增按钮点击事件
$("#btn_add").on("click", function () {
    $('#addAndUpdateLabel').text("新增会员信息");
    $('#txt_type').val("add");
    $('#txt_id').attr("disabled",false);
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
});


//刷新按钮点击事件
$("#btn_refresh").on("click", function () {
    refresh();
});



//切换视图按钮点击事件
$("#btn_toggleview").on("click", function () {
    $table.bootstrapTable('toggleView');
});

//显隐分页按钮点击事件
$("#btn_togglepage").on("click", function () {
    $table.bootstrapTable('togglePagination');
});


//清除弹窗原数据
$("#addAndUpdate").on("hidden.bs.modal", function () {

    $('#txt_phone').val("");
    $('#txt_name').val("");
    $('#txt_pwd').val("");
    $('#txt_point').val("")
    $('#txt_discount').val("");
    $('#txt_type').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {

    var user_id=$('#txt_user_id').val(),
        phone = $('#txt_phone').val(),
        name = $('#txt_name').val(),
        pwd = $('#txt_pwd').val(),
        point = $('#txt_point').val("")
        discount = $('#txt_discount').val(),
        type = $('#txt_type').val();
    // create_time = $('#txt_createTime').val();
    //验证数据
    if (!phone) {
        layer.msg('请填写手机号!', {icon: 2, time: 1500});
        return false;
    }
    if (!pwd) {
        layer.msg('请填写密码!', {icon: 2, time: 1500});
        return false;
    }
    if (!name) {
        layer.msg('请填写昵称!', {icon: 2, time: 1500});
        return false;
    }
    if (!point) {
        layer.msg('请填写积分!', {icon: 2, time: 1500});
        return false;
    }
    if (!discount) {
        layer.msg('请填写折扣!', {icon: 2, time: 1500});
        return false;
    }
    newuser={};
    newuser.mobile=phone;
    newuser.name = name;
    newuser.password = pwd;
    newuser.id=user_id;
    newuser.point = point;
    newuser.discount = discount;


    $.ajax({
        url: '../user/addAndUpdateUser/' + type,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: newuser,
        //阻止深度序列化，向后台传送数组
        traditional: true,
        success: function (msg) {
            if (msg.success) {
                layer.msg(msg.msg, {icon: 1, time: 1500});
            } else {
                layer.msg(msg.msg, {icon: 2, time: 1500});
            }
            refresh();
        }
    })
});

//tr中编辑按钮点击事件
function editData(row) {
    //向模态框中传值
    $('#txt_user_id').val(row.id);
    $('#txt_name').val(row.name);
    $('#txt_pwd').val(row.password);
    $('#txt_point').val(row.points);
    $('#txt_discount').val(row.discount);
    $('#txt_phone').val(row.mobile);
    // $('#txt_createTime').val(row.createTime);
    $('#txt_type').val("update");
    $('#addAndUpdateLabel').text("修改用户信息");

    //显示模态窗口
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
}

