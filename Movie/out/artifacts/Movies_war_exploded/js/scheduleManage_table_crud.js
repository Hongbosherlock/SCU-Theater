/**
 * Created by zxk175 on 16/12/3.
 */
var items;
$.ajax({
    url: '../movie/getAllMovie.do',
    method: 'post',
    data: {},
    async: false,
    //阻止深度序列化，向后台传送数组
    traditional: true,
    success: function (data) {
        console.log(data);
        var data  =JSON.parse(data);
        if (data.flag == false) {
            $('#movieInfo').val("没有查询到编号是" + id + "的电影啊");
            return;
        }
        if (data.flag == true) {
            items =data.allMovie;

            return;
        }
    }
});
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
    $('#addAndUpdateLabel').text("新增场次信息");
    $('#txt_type').val("add");
    $('#txt_id').attr("disabled",false);
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#startTime'
        ,type: 'time'
        ,format: 'H:M'
    });
    laydate.render({
        elem: '#endTime'
        ,type: 'time'
        ,format: 'H:M'
    });
    $.each(items, function (i, item) {
        $('#movie').append($('<option>', {
            value: item.id,
            text : item.name
        }));
    });
    // $.ajax({
    //     url: '../movie/getAllMovie.do',
    //     method: 'post',
    //     data: {},
    //     async: false,
    //     //阻止深度序列化，向后台传送数组
    //     traditional: true,
    //     success: function (data) {
    //         console.log(data);
    //         var data  =JSON.parse(data);
    //         if (data.flag == false) {
    //             $('#movieInfo').val("没有查询到编号是" + id + "的电影啊");
    //             return;
    //         }
    //         if (data.flag == true) {
    //             items =data.allMovie;
    //             $.each(items, function (i, item) {
    //                 $('#movie').append($('<option>', {
    //                     value: item.id,
    //                     text : item.name
    //                 }));
    //             });
    //             return;
    //         }
    //     }
    // })
});

//删除按钮点击事件
$("#btn_delete").on("click", function () {
    var datas = $table.bootstrapTable('getSelections');
    if (datas.length < 1) {
        layer.alert('请选择一条或多条数据进行删除！', {icon: 2});
    } else {
        var id = [];
        for (var i = 0; i < datas.length; i++) {
            id.push(datas[i].userId);
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

    $('#txt_price').val("");
    $('#txt_type').val("");
    $("#movie").empty(); //清空select
    $('#date').val("");
    $('#startTime').val("");
    // $('#endTime').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {
    var  select_movie=$("#movie option:selected"),
    select_room =$('#room option:selected') ;

    var s_id=$('#txt_sid').val(),
       mid = select_movie.val(),
        rid =select_room.val(),
        date = $('#date').val(),
        startTime = $('#startTime').val(),
        // endTime = $('#endTime').val(),
         price= $('#txt_price').val(),
        type = $('#txt_type').val();
       // create_time = $('#txt_createTime').val();
    //验证数据
    if (!date) {
        layer.msg('请选择日期!', {icon: 2, time: 1500});
        return false;
    }
    if (!mid) {
        layer.msg('请选择电影', {icon: 2, time: 1500});
        return false;
    }
    if (!rid) {
        layer.msg('请选择厅号!', {icon: 2, time: 1500});
        return false;
    }
    if (!startTime) {
        layer.msg('请填写开始时间!', {icon: 2, time: 1500});
        return false;
    }

    if (!price) {
        layer.msg('请填写价格!', {icon: 2, time: 1500});
        return false;
    }

    newSchedule = {};
    newSchedule.movie_id=mid;
    newSchedule.room_id = rid;
    newSchedule.date = date;
    newSchedule.price = price;
    newSchedule.start_time = startTime;
    // newSchedule.end_time = endTime;
    console.log(newSchedule);
    $.ajax({
        url: '../schedule/addAndUpdateSchedule/' + type,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        dataType:"json",
        data: newSchedule,
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
    $('#txt_sid').val(row.id);
    // $("#movie option:selected").val(row.movie_id);
    // $("#room option:selected").val(row.room_id);
    $('#txt_price').val(row.price);
    $('#startTime').val(row.start_time);
    // $('#endTime').val(row.end_time);
    $('#date').val(row.date);
    $('#movie').val(row.movie_id);
    $('#room').val(row.room_id);
    // $('#txt_createTime').val(row.createTime);
    $('#txt_type').val("update");
    $('#addAndUpdateLabel').text("修改场次信息");
    $("#movie").empty(); //清空select
    console.log(items);
    $.each(items, function (i, item) {
        $('#movie').append($('<option>', {
            value: item.id,
            text : item.name
        }));
    });
    $('#movie').val(row.movie_id);
    //显示模态窗口
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
}

//tr中删除按钮点击事件
function delData(id, type) {
    layer.confirm('确定要删除场次编号为' + id + '数据?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '../schedule/deleteSchedule.do',
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