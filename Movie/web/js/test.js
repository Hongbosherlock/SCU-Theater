/**
 * Created by 小兔头 on 2018/6/9.
 */
(function () {
    $('#startDate2').datetimepicker({
        language:"zh-CN", //汉化
        todayBtn : "true",  //显示今天按钮
        autoclose : true,   //选择日期后自动关闭日期选择框
        todayHighlight : true,   //当天高亮显示
        format: 'yyyy-mm-dd hh:ii:ss',
        startView: 2,
        showSecond:1,
        minuteStep:1,
        showMeridian: 1,
        pickerPosition: "bottom-left",
         startDate:new Date(new Date()-1000 * 60 * 60 * 24 * 365),  //只显示一年的日期365天
         endDate : new Date()
    }).on('click',function(e){
        $("#startDate2").datetimepicker("setEndDate", $("#endDate2").val());
    });
    $('#endDate2').datetimepicker({
        language:"zh-CN",
        todayBtn : "true",
        autoclose : true,
        todayHighlight : true,
        format: 'yyyy-mm-dd hh:ii:ss',
        startView: 2,
        minuteStep:1,
        showSecond:1,
        showMeridian: 1,
        pickerPosition: "bottom-left",
         startDate:new Date(new Date()-1000 * 60 * 60 * 24 * 365),
        endDate : new Date()
    }).on('click',function(e){
        $("#endDate2").datetimepicker("setStartDate", $("#startDate2").val());
    });

})(jQuery);
