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

//查询按钮点击事件
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
    $('#addAndUpdateLabel').text("新增订单信息");
    $('#txt_type').val("add");
    $('#txt_id').attr("disabled",false);
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
});

//删除按钮点击事件
$("#btn_delete").on("click", function () {
    var datas = $table.bootstrapTable('getSelections');
    if (datas.length < 1) {
        layer.alert('请选择一条或多条数据进行删除！', {icon: 2});
    } else {
        var id = [];
        for (var i = 0; i < datas.length; i++) {
            id.push(datas[i].id);
        }
        delData(id, "batch");
    }
});

//刷新按钮点击事件
$("#btn_refresh").on("click", function () {
    refresh();
});

//清空按钮点击事件
$("#btn_clean").on("click", function () {
    var id = [];
    $.ajax({
        url: '../user/api/deleteUser/clean',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: {id: id},
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

//初始化按钮点击事件
$("#btn_init").on("click", function () {
    $.ajax({
        url: '../user/api/initTable/' + 1000,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
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

//切换视图按钮点击事件
$("#btn_toggleview").on("click", function () {
    $table.bootstrapTable('toggleView');
});

//显隐分页按钮点击事件
$("#btn_togglepage").on("click", function () {
    $table.bootstrapTable('togglePagination');
});

//跳转按钮点击事件
$("#btn_selectpage").on("click", function () {
    var pageNum = 3;
    $table.bootstrapTable('selectPage', pageNum);
});

//清除弹窗原数据
$("#addAndUpdate").on("hidden.bs.modal", function () {

    $('#txt_createTime').val("");
    $('#txt_id').val("");
    $('#txt_name').val("");
    $('#txt_pwd').val("");
    $('#txt_dept').val("");
    $('#txt_post').val("");
    $('#txt_phone').val("");
    $('#txt_type').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {

    var user_id=$('#txt_user_id').val(),
       id = $('#txt_id').val(),
        name = $('#txt_name').val(),
        pwd = $('#txt_pwd').val(),
        dept = $('#txt_dept').val(),
        phone = $('#txt_phone').val(),
        post = $('#txt_post').val(),
        type = $('#txt_type').val();
       // create_time = $('#txt_createTime').val();
    //验证数据
    if (!id) {
        layer.msg('请填写编号!', {icon: 2, time: 1500});
        return false;
    }
    if (!name) {
        layer.msg('请填写名字!', {icon: 2, time: 1500});
        return false;
    }
    if (!pwd) {
        layer.msg('请填写密码!', {icon: 2, time: 1500});
        return false;
    }
    if (!post) {
        layer.msg('请填写职务!', {icon: 2, time: 1500});
        return false;
    }
    if (!phone) {
        layer.msg('请填写手机!', {icon: 2, time: 1500});
        return false;
    }

    $.ajax({
        url: '../user/api/addAndUpdateUser/' + type,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: {
            id:user_id,
          //  createTime:create_time,
            userId: id,
            userName: name,
            userDept: dept,
            userPassword: pwd,
            userPost: post,
            userPhone: phone

        },
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
    $('#txt_id').val(row.userId);
    $('#txt_id').attr("disabled","disabled");
    $('#txt_name').val(row.userName);
    $('#txt_pwd').val(row.userPassword);
    $('#txt_dept').val(row.userDept);
    $('#txt_post').val(row.userPost);
    $('#txt_phone').val(row.userPhone);
   // $('#txt_createTime').val(row.createTime);
    $('#txt_type').val("update");
    $('#addAndUpdateLabel').text("修改订单信息");

    //显示模态窗口
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
}

//tr中删除按钮点击事件
function delData(id, type) {
    layer.confirm('确定要删除订单编号为' + id + '数据?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '../ticket/deleteTicket.do',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            //阻止深度序列化，向后台传送数组
            traditional: true,
            data: {id: id.toString()},
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
}