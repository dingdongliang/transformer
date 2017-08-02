<!DOCTYPE html>
<html>
<head>

    <title>岗位管理</title>
<#include '../include.ftl'>
    <script type="text/javascript">
        $(function () {

            $('#coList').tree({
                url: '/manage/post/getCoDivList',
                animate: true,
                onLoadSuccess: function (node, data) {
                    if (data.length > 0)
                        loadPost(data[0].id);
                },
                onClick: function (node) {
                    loadPost(node.id);
                }
            });

            $("#postAdd").click(function () {
                var data = $("#coList").tree('getSelected');
                if (data == null || data.pid != "DIV") {
                    $.messager.show({
                        title: "提示",
                        msg: "请选择要添加岗位的部门!",
                        timeout: 1000 * 2
                    });
                } else {
                    $.modalDialog({
                        title: "添加岗位",
                        width: 600,
                        height: 300,
                        href: "/manage/post/postEdit",
                        onLoad: function () {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", {
                                "divId": data.id,
                            });
                        },
                        buttons: [{
                            text: '保存',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $("#dg").treegrid(); //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
                                var f = $.modalDialog.handler.find("#form");
                                f.submit();
                            }
                        }, {
                            text: '取消',
                            iconCls: 'icon-no',
                            handler: function () {
                                $.modalDialog.handler.dialog('destroy');
                                $.modalDialog.handler = undefined;
                            }
                        }]
                    });
                }
            });

            $("#postEdit").click(function () {
                var row = $("#dg").treegrid('getSelected');
                if (row) {
                    $.modalDialog({
                        title: "编辑岗位",
                        width: 600,
                        height: 300,
                        href: "/manage/post/postEdit",
                        onLoad: function () {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", row);
                        },
                        buttons: [{
                            text: '保存',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $("#dg").treegrid(); //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
                                var f = $.modalDialog.handler.find("#form");
                                f.submit();
                            }
                        }, {
                            text: '取消',
                            iconCls: 'icon-no',
                            handler: function () {
                                $.modalDialog.handler.dialog('destroy');
                                $.modalDialog.handler = undefined;
                            }
                        }]
                    });
                } else {
                    $.messager.show({
                        title: "提示",
                        msg: "请选择一行记录!",
                        timeout: 1000 * 2
                    });
                }
            });

            $("#postRole").click(function () {
                var node = $("#dg").treegrid('getSelected');
                if (node) {
                    $.modalDialog({
                        title: "关联角色设定",
                        width: 600,
                        height: 400,
                        href: "/manage/post/toSetRole",
                        buttons: [{
                            text: '保存',
                            iconCls: 'icon-yes',
                            handler: function () {
                                var f = $.modalDialog.handler.find("#roleList")
                                var selections = f.datalist('getSelections');
                                var checkedIds = [];
                                $.each(selections, function (i, e) {
                                    checkedIds.push(e.roleId);
                                });
                                $.ajax({
                                    url: "/manage/post/savePostRole",
                                    data: {
                                        //这里的数组必须转化成字符串形式
                                        allCheck: checkedIds.length == 0 ? "" : checkedIds.join(","),
                                        postId: node.postId
                                    },
                                    type: "POST",
                                    dataType: "JSON",
                                    success: function (rsp) {
                                        $.messager.show({
                                            title: rsp.title,
                                            msg: rsp.message,
                                            timeout: 1000 * 2
                                        });
                                    },
                                    error: function () {
                                        $.messager.show({
                                            title: "提示",
                                            msg: "分配失败！",
                                            timeout: 1000 * 2
                                        });
                                    }
                                });
                                $.modalDialog.handler.dialog('destroy');
                                $.modalDialog.handler = undefined;
                            }
                        }, {
                            text: '取消',
                            iconCls: 'icon-no',
                            handler: function () {
                                $.modalDialog.handler.dialog('destroy');
                                $.modalDialog.handler = undefined;
                            }
                        }]
                    });
                } else {
                    $.messager.show({
                        title: "提示",
                        msg: "请选择要一个岗位!",
                        timeout: 1000 * 2
                    });
                }
            });

            $("#postDel").click(function () {
                var node = $("#dg").treegrid('getSelected');
                if (node) {
                    $.messager.confirm("提示", "确定要删除记录吗?",
                            function (result) {
                                if (result) {
                                    var request = $.ajax({
                                        url: "/manage/post/delPost",
                                        data: {
                                            'id': node.postId
                                        },
                                        method: "POST",
                                        dataType: "JSON"
                                    });

                                    request.done(function (rsp) {
                                        if (rsp.code==200) {
                                            $("#dg").treegrid('remove', node.postId);
                                        }
                                        $.messager.show({
                                            title: rsp.title,
                                            msg: rsp.message,
                                            timeout: 1000 * 2
                                        });
                                    });

                                    request.fail(function () {
                                        $.messager.show({
                                            title: "提示",
                                            msg: "提交错误了！",
                                            timeout: 1000 * 2
                                        });
                                    });
                                }
                            });
                } else {
                    $.messager.show({
                        title: "提示",
                        msg: "请选择一行记录!",
                        timeout: 1000 * 2
                    });
                }
            });

        });

        function loadPost(id) {
            $('#dg').treegrid({
                height: 503,
                url: '/manage/post/findPostByDiv/' + id,
                idField: 'postId',
                treeField: 'postName',
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: false,
                columns: [[
                    {
                        field: 'postId',
                        title: '岗位ID',
                        width: parseInt($(this).width() * 0.05),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'postName',
                        title: '岗位名称',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'postDesc',
                        title: '岗位描述',
                        width: parseInt($(this).width() * 0.12),
                        editor: "text"
                    }
                ]], toolbar: '#tb'
            });
            $("#dg").treegrid('unselectAll');
        }

    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:250px;background-color: #eff8fc">
    <ul id="coList"/>
</div>
<div data-options="region:'center'" class="rightinfo">
    <div class="easyui-layout" id="tb">
        <ul class="toolbar">
            <shiro:hasPermission name="postAdd">
                <li id="postAdd"><span><img
                        src="/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="postEdit">
                <li id="postEdit"><span><img
                        src="/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="postDel">
                <li id="postDel"><span><img
                        src="/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="postRole">
                <li id="postRole"><span><img
                        src="/images/save.png"/></span>授权
                </li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table title="岗位信息" class="easyui-treegrid" id="dg"></table>
    <div class="tip">
        <div class="tiptop">
            <span>提示信息</span><a></a>
        </div>
        <div class="tipinfo">
            <span><img src="//images/ticon.png"/></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>
        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定"/>&nbsp; <input
                name="" type="button" class="cancel" value="取消"/>
        </div>
    </div>
</div>
</body>
</html>