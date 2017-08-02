<!DOCTYPE html>
<html>
<head>
    <title>日志管理</title>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="/css/icon.css">
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <script type="text/javascript" src="/js/jqueryUtil.js"></script>
    <script type="text/javascript">
        var $dg;
        var $grid;
        $(function () {
            $dg = $("#dg");
            $grid = $dg.datagrid({
                url: "logs/logsAction!findLogsAllList.action",
                width: 'auto',
                height: $(this).height() - 85,
                pagination: true,
                rownumbers: true,
                border: true,
                striped: true,
                singleSelect: true,
                columns: [[{field: 'name', title: '操作用户', width: parseInt($(this).width() * 0.1)},
                    {field: 'ip', title: 'IP地址', width: parseInt($(this).width() * 0.1)},
                    {field: 'mac', title: '物理地址', width: parseInt($(this).width() * 0.1)},
                    {field: 'logDate', title: '日志日期', width: parseInt($(this).width() * 0.1)},
                    {
                        field: 'type',
                        title: '日志类型',
                        width: parseInt($(this).width() * 0.1),
                        align: 'left',
                        formatter: function (value, row) {
                            if ("1" == row.type)
                                return "<font color=green>安全日志<font>";
                            else
                                return "<font color=red>操作日志<font>";
                        }
                    },
                    {field: 'eventName', title: '操作名称', width: parseInt($(this).width() * 0.1), align: 'left'},
                    {field: 'objectId', title: '模型ID', width: parseInt($(this).width() * 0.1), align: 'left'},
                    {field: 'eventRecord', title: '操作描述', width: parseInt($(this).width() * 0.2), align: 'left'}
                ]], toolbar: '#tb'
            });
            //搜索框
            $("#searchbox").searchbox({
                menu: "#mm",
                prompt: '模糊查询',
                searcher: function (value, name) {
                    var str = "{\"searchName\":\"" + name + "\",\"searchValue\":\"" + value + "\"}";
                    var obj = eval('(' + str + ')');
                    $dg.datagrid('reload', obj);
                }

            });
        });

        //删除
        function delRows() {
            var row = $dg.datagrid('getSelected');
            if (row) {
                var rowIndex = $dg.datagrid('getRowIndex', row);
                $dg.datagrid('deleteRow', rowIndex);
                $.ajax({
                    url: "logs/logsAction!delLogs.action",
                    data: "logId=" + row.logId,
                    success: function (rsp) {
                        parent.$.messager.show({
                            title: rsp.title,
                            msg: rsp.message,
                            timeout: 1000 * 2
                        });
                    }
                });
            } else {
                parent.$.messager.show({
                    title: "提示",
                    msg: "请选择一行记录!",
                    timeout: 1000 * 2
                });
            }
        }
        //弹窗修改
        function updRowsOpenDlg() {
            var row = $dg.datagrid('getSelected');
            if (row) {
                parent.$.modalDialog({
                    title: '编辑日志',
                    width: 600,
                    height: 400,
                    href: "jsp/logs/logsEdit.ftl",
                    onLoad: function () {
                        var f = parent.$.modalDialog.handler.find("#form");
                        f.form("load", row);
                    },
                    buttons: [{
                        text: '编辑',
                        iconCls: 'icon-ok',
                        handler: function () {
                            parent.$.modalDialog.openner = $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                            var f = parent.$.modalDialog.handler.find("#form");
                            f.submit();
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            parent.$.modalDialog.handler.dialog('destroy');
                            parent.$.modalDialog.handler = undefined;
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
        }
        //弹窗增加公司
        function addRowsOpenDlg() {
            parent.$.modalDialog({
                title: '添加日志',
                width: 600,
                height: 400,
                href: "jsp/logs/logsEdit.ftl",
                buttons: [{
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        parent.$.modalDialog.openner = $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find("#form");
                        f.submit();
                    }
                }, {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        parent.$.modalDialog.handler.dialog('destroy');
                        parent.$.modalDialog.handler = undefined;
                    }
                }
                ]
            });
        }

        //高级搜索 删除 row
        function tbCompanySearchRemove(curr) {
            $(curr).closest('tr').remove();
        }
        //高级查询
        function tbsCompanySearch() {
            jqueryUtil.gradeSearch($dg, "#tbCompanySearchFm", "jsp/company/companySearchDlg.jsp");
        }
    </script>
</head>
<body>
<div data-options="region:'center',border : false">
    <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
        <span class="badge">提示</span>
        <p>
            在此你可以对<span class="label-info"><strong>日志</strong></span>进行编辑!
        </p>
    </div>
    <div id="tb" style="padding:2px 0">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="padding-left:2px">
                    <shiro:hasPermission name="logAdd">
                        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"
                           onclick="addRowsOpenDlg();">添加</a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="logEdit">
                        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                           onclick="updRowsOpenDlg();">编辑</a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="logDel">
                        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                           onclick="delRows();">删除</a>
                    </shiro:hasPermission>
                </td>
                <td style="padding-left:2px">
                    <input id="searchbox" type="text"/>
                </td>
                <!--  <td style="padding-left:2px">
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="tbsCompanySearch();">高级查询</a>
                </td>-->
            </tr>
        </table>
    </div>
    <div id="mm">
        <div name="name">日志名称</div>
        <div name="logDate">创建日期</div>
        <div name="type">类型</div>
        <div name="eventName">操作名称</div>
        <div name="eventRecord">操作描述</div>
    </div>
    <table id="dg" title="日志管理"></table>
</div>
</body>
</html>
