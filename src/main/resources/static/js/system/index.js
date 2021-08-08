$(function () {
    //添加新的Tab页
    $("#navmenu").on("click", "a[data-url]", function (e) {
        e.preventDefault();
        var tabTitle = $(this).text();
        var tabUrl = $(this).data("url");

        if ($("#tt").tabs("exists", tabTitle)) { //判断该Tab页是否已经存在
            $("#tt").tabs("select", tabTitle);
        } else {
            $("#tt").tabs("add", {
                title: tabTitle,
                href: tabUrl,
                closable: true
            });
        }
        $("#navmenu .active").removeClass("active");
        $(this).parent().addClass("active");
    });

    //解决闪屏的问题
    window.setTimeout(function () {
        $("#layout").css("visibility", "visible");
    }, 800);
    //系统退出
    $("#signout").click(function () {
        $.ajax({
            type: "get",
            url: "/system/signout",
            success: function (data) {
                if (data == "true") {
                    $(location).attr('href', "/login");
                }
            }
        })
    });
    //打开修改密码对话框
    $("#changepassword").click(function () {
        $('#changpwddlg').dialog("open");
    });
    //提交修改密码
    $("#submitpwdbtn").click(function () {
        $('#changpwddlg').dialog("close");
        $.ajax({
            type: "post",
            url: "/system/changepassword",
            data: "oldPassword=" + $("#oldpassword").val() + "&newpassword=" + $("#pwd").val(),
            success: function (data) {
                if (data == "true") {
                    $.messager.alert('提示', '成功修改密码',"info",function () {
                        window.location.reload();
                    });
                } else {
                    $.messager.alert('提示', '密码修改失败',"warning",function () {
                        window.location.reload();
                    });
                }
            }
        });

    });
    //取消修改密码
    $("#cancelpwdbtn").click(function () {
        $("#oldpassword").val("");
        $("#newpassword").val(" ");
        $('#changpwddlg').dialog("close");
        window.location.reload();
    })

});