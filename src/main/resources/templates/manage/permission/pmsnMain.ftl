<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
<#include '../include.ftl'>
    <script type="text/javascript">
        var $dg;
        var $grid;
        var typedata = [{"type": "M", "typeName": "菜单"}, {"type": "O", "typeName": "操作"}];
        $(function () {
            $dg = $("#dg");
            $grid = $dg.treegrid({
                width: 'auto',
                height: $(this).height() - 17,
                url: "/manage/menu/findAllMenuList",
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                idField: 'pmsnId',
                treeField: 'pmsnName',
                frozenColumns: [[{
                    title: '菜单名称',
                    field: 'pmsnName',
                    editor: {
                        type: 'validatebox',
                        options: {
                            required: true
                        }
                    },
                    width: parseInt($(this).width() * 0.13),
                    formatter: function (value) {
                        return '<span style="color:red">' + value + '</span>';
                    }
                }]],
                columns: [[{
                    field: 'prntName',
                    title: '父菜单名称',
                    width: parseInt($(this).width() * 0.08),
                    align: 'center'
                }, {
                    field: 'sort',
                    title: '排序编码',
                    width: parseInt($(this).width() * 0.06),
                    editor: {
                        type: 'numberbox'
                    },
                    align: 'center'
                }, {
                    field: 'isDefault',
                    title: '是否默认',
                    align: 'center',
                    width: parseInt($(this).width() * 0.07),
                    formatter: function (value, row) {
                        if ("Y" == row.isDefault) return "<font color=green>是<font>";
                        else return "<font color=red>否<font>";
                    },
                }, {
                    field: 'pmsnUrl',
                    title: '菜单路径',
                    width: parseInt($(this).width() * 0.20),
                    align: 'left',
                    editor: {
                        type: 'validatebox',
                        options: {
                            required: true
                        }
                    }
                }, {
                    field: 'pmsnCode',
                    title: '菜单编码',
                    width: parseInt($(this).width() * 0.08),
                    align: 'left',
                    editor: {
                        type: 'validatebox',
                        options: {
                            required: true
                        }
                    }
                }, {
                    field: 'pmsnType',
                    title: '菜单类型',
                    width: parseInt($(this).width() * 0.07),
                    align: 'center',
                    formatter: function (value, row) {
                        if ("M" == row.pmsnType) return "<font color=green>菜单<font>";
                        else return "<font color=red>操作<font>";
                    },
                    editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'type',
                            textField: 'typeName',
                            data: typedata,
                            required: true
                        }
                    }
                }, {
                    field: 'isUsed',
                    title: '是否启用',
                    width: parseInt($(this).width() * 0.07),
                    align: 'center',
                    formatter: function (value, row) {
                        if ("Y" == row.isUsed) return "<font color=green>是<font>";
                        else return "<font color=red>否<font>";
                    },
                    editor: {
                        type: 'checkbox',
                        options: {
                            on: 'Y',
                            off: 'N'
                        }
                    }
                }, {
                    field: 'pmsnDesc',
                    title: '菜单描述',
                    width: parseInt($(this).width() * 0.34),
                    align: 'left',
                    editor: "text"
                }]],
                toolbar: '#tb',
                onDblClickRow: menuEdit
            });

            $("#addFunc").click(function () {
                var row = $dg.treegrid('getSelected');
                if (row) {
                    if (row.pmsnType == "O") {
                        $.messager.alert('错误提示', '该操作没有下层!', 'error');
                    } else {
                        $.modalDialog({
                            title: "添加菜单",
                            width: 600,
                            height: 400,
                            href: "/manage/menu/menuEditDlg",
                            onLoad: function () {
                                if (row) {
                                    var f = $.modalDialog.handler.find("#form");
                                    f.form("load", {
                                        //这里的两个参数对应pmsnEdit.jsp中的控件id
                                        "prntId": row.pmsnId,
                                        "prntName": row.pmsnName
                                    });
                                }
                            },
                            buttons: [{
                                text: '保存',
                                iconCls: 'icon-yes',
                                handler: function () {
                                    $.modalDialog.openner = $grid; //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
                                    var f = $.modalDialog.handler.find("#form");
                                    f.submit();
                                }
                            },
                                {
                                    text: '取消',
                                    iconCls: 'icon-no',
                                    handler: function () {
                                        $.modalDialog.handler.dialog('destroy');
                                        $.modalDialog.handler = undefined;
                                    }
                                }]
                        });
                    }
                } else {
                    $.modalDialog({
                        title: "添加菜单",
                        width: 600,
                        height: 400,
                        href: "/manage/menu/menuEditDlg",
                        onLoad: function () {
                            if (row) {
                                var f = $.modalDialog.handler.find("#form");
                                f.form("load", {
                                    "prntId": row.pmsnId,
                                    "prntName": row.pmsnName
                                });
                            }
                        },
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
                        }]
                    });
                }
            });

            $("#updateFunc").click(function () {
                menuEdit();
            });

            $("#delFunc").click(function () {
                var node = $dg.treegrid('getSelected');
                if (node) {
                    $.messager.confirm("提示", "确定要删除记录吗?",
                            function (result) {
                                if (result) {
                                    var request = $.ajax({
                                        url: "/manage/menu/delMenu",
                                        data: {
                                            'id': node.pmsnId
                                        },
                                        method: "POST",
                                        dataType: "JSON"
                                    });

                                    request.done(function (rsp) {
                                        alert(JSON.stringify(rsp));
                                        if (rsp.code == 200) {
                                            $dg.treegrid('remove', node.pmsnId);
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

        function menuEdit() {
            var row = $dg.treegrid('getSelected');
            if (row) {
                $.modalDialog({
                    title: "编辑菜单",
                    width: 600,
                    height: 400,
                    href: "/manage/menu/menuEditDlg",
                    buttons: [{
                        text: '编辑',
                        iconCls: 'icon-yes',
                        handler: function () {
                            $.modalDialog.openner = $grid; //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
                            var f = $.modalDialog.handler.find("#form");
                            f.submit();
                        }
                    },
                        {
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
        }
    </script>
</head>
<body>
<div class="rightinfo">
    <div id="tb" class="easyui-layout">
        <ul class="toolbar">
            <shiro:hasPermission name="menuAdd">
                <li id="addFunc"><span><img
                        src="/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="menuEdit">
                <li id="updateFunc"><span><img
                        src="/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="menuDel">
                <li id="delFunc"><span><img
                        src="/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="tablelist" id="dg" title="菜单"></table>

    <div class="tip">
        <div class="tiptop">
            <span>提示信息</span><a></a>
        </div>
        <div class="tipinfo">
            <span><img src="/images/ticon.png"/></span>
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