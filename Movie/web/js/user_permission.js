(function () {
    //是否显示用户增加·
    $.ajax({
        url: "PermissionManager/hasPermission?permissionExpression=user_add",
        type: "post",
        success: function (data) {
            if (data.info == "allow") {
                $("#btn_add").show();
            } else {
                $("#btn_add").hide();
            }
        }
    });
    $.ajax({
        url: "PermissionManager/hasPermission?permissionExpression=user_delete",
        type: "post",
        success: function (data) {
            if (data.info == "allow") {
                $("#btn_delete").show();
                $("#remove").show();
            } else {
                $("#btn_delete").hide();
                $("#remove").hide();
                $('#users').bootstrapTable('hideColumn', 'operate');

            }
        }
    });
    $.ajax({
        url: "PermissionManager/hasPermission?permissionExpression=user_modify",
        type: "post",
        success: function (data) {
            if (data.info == "allow") {
                $("#edit").show();
            } else {
                $("#edit").hide();
                $('#users').bootstrapTable('hideColumn', 'operate');
            }
        }
    });
    $.ajax({
        url: "PermissionManager/hasPermission?permissionExpression=user_select",
        type: "post",
        success: function (data) {
            if (data.info == "allow") {
                $("#search").show();
            } else {
                $("#search").hide();
            }
        }
    });


})(jQuery);