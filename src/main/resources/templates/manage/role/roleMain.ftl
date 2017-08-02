<!DOCTYPE HTML>
<html>
<head>
    <title>权限编辑</title>
<#include '../include.ftl'>
    <script type="text/javascript">
        var $role;
        var $function;
        var $grid;
        $(function () {
            $("#panel").panel({
                width: 'auto',
                height: $(this).height()
            });

            $role = $("#role");
            $function = $("#function");

            $grid = $role.datagrid({
                url: "/manage/role/allRoleByPage",
                width: 'auto',
                height: $(this).height() - 2,
                pagination: true,
                border: true,
                idField: 'roleId',
                striped: true,
                singleSelect: true,
                columns: [[{
                    field: 'roleName',
                    title: '角色名称',
                    width: parseInt($(this).width() * 0.1),
                    align: 'center',
                    editor: {type: 'validatebox', options: {required: true}}
                }, {
                    field: 'sort',
                    title: '排序',
                    width: parseInt($(this).width() * 0.05),
                    align: 'center',
                    editor: "numberbox"
                }, {
                    field: 'roleDesc',
                    title: '角色描述',
                    width: parseInt($(this).width() * 0.22),
                    align: 'center',
                    editor: "text"
                }]], toolbar: '#tbRole',
                onDblClickRow: function (rowIndex, rowData) {
                    $.ajax({
                        url: "/manage/role/getRolePermission",
                        data: {
                            'roleId': rowData.roleId
                        },
                        method: "POST",
                        dataType: "JSON",
                        success: function (msg) {
                            //初始化权限，取消所有的选中
                            $function.treegrid('unselectAll');
                            //判断是否有权限
                            if (msg.length != 0) {
                                //循环选中包含的权限
                                $.each(msg, function (i, e) {
                                    $function.treegrid('select', e.pmsnId);
                                });
                            } else {
                                $.messager.show({
                                    title: "提示",
                                    msg: "该角色暂无权限!",
                                    timeout: 1000 * 2
                                });
                            }
                        },
                        error: function () {
                            //获取失败，取消所有的选中
                            $function.treegrid('unselectAll');
                            $.messager.show({
                                title: "提示",
                                msg: "获取权限失败!",
                                timeout: 1000 * 2
                            });
                        }
                    });
                }
            });

            $function.treegrid({
                width: 'auto',
                height: $(this).height() - 2,
                url: "/manage/menu/findAllRoleMenu",
                rownumbers: true,
                idField: 'id',
                treeField: 'name',
                singleSelect: false,
                deepCascadeCheck: true,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'name', title: '菜单名称', width: parseInt($(this).width() * 0.15)},
                    {field: 'myid', title: '菜单编码', width: parseInt($(this).width() * 0.07), align: 'center'},
                    {
                        field: 'type', title: '菜单类型', width: parseInt($(this).width() * 0.05), align: 'center',
                        formatter: function (value, row) {
                            if ("M" == row.type)
                                return "<font color=blue>菜单<font>";
                            else
                                return "<font color=red>操作<font>";
                        }
                    }, {
                        field: 'ifUsed', title: '是否启用', width: parseInt($(this).width() * 0.05), align: 'center',
                        formatter: function (value, row) {
                            if ("Y" == row.ifUsed)
                                return "<font color=green>是<font>";
                            else
                                return "<font color=red>否<font>";
                        }
                    }, {field: 'description', title: '菜单描述', width: parseInt($(this).width() * 0.23), align: 'left'}
                ]], toolbar: '#tb', onClickRow: function (row) {
                    $function.treegrid('cascadeCheck', {
                        id: row.id,
                        deepCascade: true //深度级联
                    });
                }
            });

            //添加角色
            $("#roleAdd").click(function () {
                $.modalDialog({
                    title: "添加角色",
                    width: 600,
                    height: 400,
                    href: "/manage/role/roleEditDlg",
                    buttons: [{
                        text: '保存',
                        iconCls: 'icon-yes',
                        handler: function () {
                            $.modalDialog.openner = $grid;
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
                    }
                    ]
                });
            });

            //修改角色
            $("#roleEdit").click(function () {
                var row = $role.datagrid('getSelected');
                if (row) {
                    $.modalDialog({
                        title: "编辑角色",
                        width: 600,
                        height: 400,
                        href: "/manage/role/roleEditDlg",
                        onLoad: function () {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", row);
                        },
                        buttons: [{
                            text: '编辑',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $grid;
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
                        }
                        ]
                    });
                } else {
                    $.messager.show({
                        title: "提示",
                        msg: "请选择一行记录!",
                        timeout: 1000 * 2
                    });
                }
            });

            $("#roleDefault").click(function () {
                var selectionRole = $role.datagrid('getSelected');
                $.ajax({
                    url: "/manage/role/setDefaultRole",
                    data: {
                        roleId: selectionRole.roleId
                    },
                    success: function (msg) {
                        $.messager.show({
                            title: msg.title,
                            msg: msg.message,
                            timeout: 1000 * 2
                        });
                    }
                });
            });

            //删除角色
            $("#roleDel").click(function () {
                var row = $role.datagrid('getSelected');
                if (row) {
                    var rowIndex = $role.datagrid('getRowIndex', row);
                    $.messager.confirm("提示", "确定要删除记录吗?",
                            function (result) {
                                if (result) {
                                    $.ajax({
                                        url: "/manage/role/delRole",
                                        data: {
                                            "roleId": row.roleId
                                        },
                                        success: function (rsp) {
                                            if (rsp.code == 200) {
                                                $role.datagrid('deleteRow', rowIndex);
                                            }
                                            $.messager.show({
                                                title: rsp.title,
                                                msg: rsp.message,
                                                timeout: 1000 * 2
                                            });
                                        },
                                        error: function () {
                                            $.messager.show({
                                                title: "提示",
                                                msg: "提交错误了！",
                                                timeout: 1000 * 2
                                            });
                                        }
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

            //保存角色权限记录
            $("#roleSave").click(function () {
                var selections = $function.treegrid('getSelections');
                var selectionRole = $role.datagrid('getSelected');
                var checkedIds = [];
                $.each(selections, function (i, e) {
                    checkedIds.push(e.id);
                });
                if (selectionRole) {
                    $.ajax({
                        url: "/manage/role/savePermission",
                        data: {
                            //这里的数组必须转化成字符串形式
                            allCheck: checkedIds.length == 0 ? "" : checkedIds.join(","),
                            roleId: selectionRole.roleId
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
                } else {
                    $.messager.show({
                        title: "提示",
                        msg: "请选择角色！",
                        timeout: 1000 * 2
                    });
                }
            });

            //展开全部权限菜单
            $("#expandAll").click(function () {
                var node = $function.treegrid('getSelected');
                if (node) {
                    $function.treegrid('expandAll', node.id);
                } else {
                    $function.treegrid('expandAll');
                }
            });

            //收起全部权限菜单
            $("#collapseAll").click(function () {
                var node = $function.treegrid('getSelected');
                if (node) {
                    $function.treegrid('collapseAll', node.id);
                } else {
                    $function.treegrid('collapseAll');
                }
            });

            //刷新权限菜单
            $("#refresh").click(function () {
                $function.treegrid('reload');
            });
        });
    </script>
</head>
<body>
<div id="panel" data-options="border:false">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:true,border:true" style="width:43%;">
            <div id="tbRole" class="easyui-layout">
                <ul class="toolbar">
                    <shiro:hasPermission name="roleAdd">
                        <li id="roleAdd"><span><img
                                src="/images/t01.png"/></span>添加
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="roleEdit">
                        <li id="roleEdit"><span><img
                                src="/images/t02.png"/></span>修改
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="roleDel">
                        <li id="roleDel"><span><img
                                src="/images/t03.png"/></span>删除
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="rolePmsn">
                        <li id="roleSave"><span><img
                                src="/images/save.png"/></span>权限保存
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="roleDefault">
                        <li id="roleDefault"><span><img
                                src="/images/save.png"/></span>默认权限
                        </li>
                    </shiro:hasPermission>
                </ul>
            </div>
            <table id="role" title="角色"></table>
        </div>
        <div data-options="region:'center',border:true">
            <div id="tb" class="easyui-layout">
                <ul class="toolbar" style="padding: 6px 2px;">
                    <li id="expandAll"><span><img
                            src="/images/open.png"/></span>展开
                    </li>
                    <li id="collapseAll"><span><img
                            src="/images/close.png"/></span>收缩
                    </li>
                    <li id="refresh"><span><img
                            src="/images/refresh.png"/></span>刷新
                    </li>
                </ul>
            </div>
            <table id="function" title="菜单"></table>
        </div>
    </div>
</div>
</body>
</html>