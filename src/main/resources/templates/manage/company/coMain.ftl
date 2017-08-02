<!DOCTYPE html>
<html>
<head>
    <title>公司管理</title>
    <#include '../include.ftl'>
    <script type="text/javascript">

        $(function () {

            $("#dg").treegrid({
                url: "/manage/comp/findAllCoList",
                width: 'auto',
                height: $(this).height() - 17,
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                idField: 'coId',
                treeField: 'coName'
            });

            //弹窗增加公司
            $("#addComp").click(function () {
                var row = $("#dg").treegrid('getSelected');
                $.modalDialog({
                    title: '添加公司',
                    width: 600,
                    height: 430,
                    href: '/manage/comp/companyEditDlg',
                    onLoad: function () {
                        if (row) {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", {
                                //这里的两个参数对应pmsnEdit.jsp中的控件id
                                "prntId": row.coId,
                                "prntName": row.coName
                            });
                        }
                    },
                    buttons: [{
                        text: '保存',
                        iconCls: 'icon-yes',
                        handler: function () {
                            $.modalDialog.openner = $("#dg").treegrid();
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
            });

            //弹窗修改公司
            $("#updateComp").click(function () {
                var row = $("#dg").treegrid('getSelected');
                if (row) {
                    $.modalDialog({
                        title: '编辑公司',
                        width: 600,
                        height: 400,
                        href: "/manage/comp/companyEditDlg",
                        onLoad: function () {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", row);
                        },
                        buttons: [{
                            text: '编辑',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $("#dg").treegrid();
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
                    parent.$.messager.show({
                        title: "提示",
                        msg: "请选择一行记录!",
                        timeout: 1000 * 2
                    });
                }
            });

            $("#delComp").click(function () {
                var row = $("#dg").treegrid('getSelected');
                if (row) {
                    $.messager.confirm("提示", "确定要删除记录吗?", function (r) {
                        if (r) {
                            $.ajax({
                                url: "/manage/comp/delComp",
                                data: {
                                    'coId': row.coId
                                },
                                success: function (rsp) {
                                    if (rsp.code==200) {
                                        $("#dg").treegrid('remove', row.coId);
                                    }
                                    $.messager.show({
                                        title: rsp.title,
                                        msg: rsp.message,
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

            $("#toExcel").click(function () {
                var row = $("#dg").treegrid('getSelected');
                if (row) {
                    window.location.href = "/manage/comp/excelExport/" + row.coId;
                } else {
                    $.messager.show({
                        title: "提示",
                        msg: "暂无导出数据!",
                        timeout: 1000 * 2
                    });
                }
            });
        });
    </script>
</head>
<body>
<div class="rightinfo">
    <div id="tb" class="easyui-layout">
        <ul class="toolbar">
            <shiro:hasPermission name="coAdd">
                <li id="addComp"><span><img
                        src="/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="coEdit">
                <li id="updateComp"><span><img
                        src="/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="coDel">
                <li id="delComp"><span><img
                        src="/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
            <li id="toExcel">
            <span><img
                    src="/images/excel.png"/></span>导出Excel
            </li>
        </ul>
    </div>
    <table id="dg" title="公司管理" class="easyui-treegrid" data-options="toolbar: '#tb'">
        <thead>
        <tr>
            <th data-options="field:'coName',width:150,align:'center'">公司名称</th>
            <th data-options="field:'coPhone',width:100,align:'center'">公司电话</th>
            <th data-options="field:'prntName',width:150,align:'center'">上级公司</th>
            <th data-options="field:'coFax',width:100,align:'center'">传真</th>
            <th data-options="field:'coAdr',width:200,align:'center'">公司地址</th>
            <th data-options="field:'coZip',width:80,align:'center'">邮政编码</th>
            <th data-options="field:'coEmail',width:100,align:'center'">公司邮箱</th>
            <th data-options="field:'contact',width:80,align:'center'">联系人</th>
            <th data-options="field:'coDesc',width:200,align:'center'">描述</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>

