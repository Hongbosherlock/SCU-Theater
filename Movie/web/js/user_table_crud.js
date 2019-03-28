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
    refresh();
});

//清空条件按钮点击事件
$("#btn_clean_search").on("click", function () {
    $('#name').val("");
    $('#startDate2').val("");
    $('#endDate2').val("");
    refresh();
});

//新增按钮点击事件
$("#btn_add").on("click", function () {
    $('#addAndUpdateLabel').text("新增管理员信息");
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

    // $('#txt_createTime').val("");
    $('#txt_account').val("");
    $('#txt_pwd').val("");
    $('#txt_post').val("");
    $('#txt_type').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {

    var user_id=$('#txt_user_id').val(),
        account = $('#txt_account').val(),
        pwd = $('#txt_pwd').val(),
        role = $('#txt_post').val(),
        type = $('#txt_type').val();
       // create_time = $('#txt_createTime').val();
    //验证数据

    if (!account) {
        layer.msg('请填写账号!', {icon: 2, time: 1500});
        return false;
    }

    if (!role) {
        layer.msg('请填写职务!', {icon: 2, time: 1500});
        return false;
    }
    if (!pwd) {
        layer.msg('请填写密码!', {icon: 2, time: 1500});
        return false;
    }

    newadmin={};
    newadmin.account = account;
    newadmin.role = role;
    newadmin.password = pwd;
    $.ajax({
        url: '../admin/addAndUpdateAdmin/' + type,
        method: 'post',
        // contentType: "application/x-www-form-urlencoded",
        data: newadmin,
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

