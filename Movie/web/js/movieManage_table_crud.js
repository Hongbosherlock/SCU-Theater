/**
 * Created by xhb on 18/12/3.
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
    $('#addAndUpdateLabel').text("新增电影信息");
    $('#txt_type').val("add");
    // $('#txt_id').attr("disabled",false);
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

    $('#txt_createTime').val("");
    $('#txt_type').val("");
    $('#txt_name').val("");
    $('#txt_star').val("");
    $('#txt_mtype').val("");
    $('#txt_description').val("");
    $('#txt_duration').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {

     var id=$('#txt_movie_id').val(),
        name = $('#txt_name').val(),
        star = $('#txt_star').val(),
        mtype = $('#txt_mtype').val();
        description = $('#txt_description').val(),
        duration = $('#txt_duration').val()
        type = $('#txt_type').val();
        // create_time = $('#txt_createTime').val();
    //验证数据
    if (!name) {
        layer.msg('请填写电影名称!', {icon: 2, time: 1500});
        return false;
    }
    if (!duration) {
        layer.msg('请填写电影时长!', {icon: 2, time: 1500});
        return false;
    }
    if (!description) {
        layer.msg('请填写电影简介!', {icon: 2, time: 1500});
        return false;
    }
    if (!mtype) {
        layer.msg('请填写电影类型!', {icon: 2, time: 1500});
        return false;
    }
    if (!star) {
        layer.msg('请填写电影主演!', {icon: 2, time: 1500});
        return false;
    }

    newMovie = {};
    newMovie.name = name;
    newMovie.duration=duration;
    newMovie.description =description;
    newMovie.star = star;
    newMovie.type = mtype;
    newMovie.id = id;

    $.ajax({
        url: '../movie/addAndUpdateMovie/' + type,
        method: 'post',
        // contentType: "application/json",
        data: newMovie,
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
    $('#txt_movie_id').val(row.id);
    $('#txt_name').val(row.name);
    $('#txt_star').val(row.star);
    $('#txt_mtype').val(row.type);
    $('#txt_description').val(row.description);
    $('#txt_duration').val(row.duration);
   // $('#txt_createTime').val(row.createTime);
    $('#txt_type').val("update");
    $('#addAndUpdateLabel').text("修改电影信息");

    //显示模态窗口
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
}

//tr中删除按钮点击事件
function delData(id, type) {
    layer.confirm('确定要删除电影编号为' + id + '数据?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '../movie/deleteMovie.do',
            method: 'post',
            // contentType: "application/x-www-form-urlencoded",
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