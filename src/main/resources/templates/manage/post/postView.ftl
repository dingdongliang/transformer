<!DOCTYPE html>
<html>
<head>

    <title>岗位用户查看</title>
<#include '../include.ftl'>
    <script type="text/javascript">

        $(function () {
            $('#postList').tree({
                url: '/manage/users/getPostList',
                animate: true,
                onClick: function (node) {
                    var type = node.pid;
                    var url = "";
                    switch (type) {
                        case "POST":
                            url = "/manage/users/findUserByPost/" + node.id;
                            break;
                        case "DIV":
                            url = "/manage/users/findUserByDiv/" + node.id;
                            break;
                        default:
                            url = "/manage/users/findUserByCo/" + node.id;
                    }
                    getUserList(url);
                }
            });

            getUserList("/manage/users/allUserByPage");
        });

        function getUserList(url) {
            $("#dg").datagrid({
                width: 'auto',
                height: $(this).height() - 2,
                url: url,
                pagination: true,
                animate: true,
                rownumbers: true,
                fitColumns: true,
                striped: true,
                border: true,
                idField: 'userId',
                singleSelect: true,
                columns: [[
                    {
                        field: 'userId',
                        title: '用户序号',
                        width: parseInt($(this).width() * 0.05),
                        align: 'center',
                        editor: "text"
                    }, {
                        field: 'userName',
                        title: '用户名称',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'account',
                        title: '用户账号',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'userPhone',
                        title: '联系电话',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: {type: 'validatebox', options: {required: true}}
                    },
                    {
                        field: 'userEmail',
                        title: '邮箱',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: {type: 'validatebox', options: {required: true, validType: 'email'}}
                    },
                    {
                        field: 'prevIp',
                        title: '登录IP',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'userDesc',
                        title: '用户描述',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    }
                ]], toolbar: '#tb',
                onLoadSuccess: function (data) {
                    if (data == null) {
                        $.messager.show({
                            title: "提示",
                            msg: "没有找到符合条件的用户",
                            timeout: 1000 * 2
                        });
                    }
                },
                onLoadError: function () {
                    $.messager.show({
                        title: "提示",
                        msg: "没有找到符合条件的用户",
                        timeout: 1000 * 2
                    });
                }
            });
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:250px;background-color: #eff8fc">
    <ul id="postList"/>
</div>
<div data-options="region:'center'" class="rightinfo">
    <table class="tablelist" id="dg" title="用户"></table>
</div>
</body>
</html>