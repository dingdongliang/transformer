<!DOCTYPE html>
<html>
<head>
    <title>BUG管理</title>
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
                url: " ",
                width: 'auto',
                height: $(this).height() - 85,
                pagination: true,
                rownumbers: true,
                border: true,
                striped: true,
                singleSelect: true,
                columns: [[{field: 'bugName', title: 'Bug名称', width: parseInt($(this).width() * 0.2)},
                    {field: 'attachmentUrl', title: 'BUG简要', width: parseInt($(this).width() * 0.2)},
                    {field: 'description', title: 'Bug附件及描述', width: parseInt($(this).width() * 0.5)}
                ]], toolbar: '#tb'
            });
            //搜索框
            $("#searchbox").searchbox({
                menu: "#mm",
                prompt: '模糊查询',
                searcher: function (value, name) {
                    var str = "{\"searchName\":\"" + name + "\",\"searchValue\":\"" + value + "\"}";
                    var obj = eval('(' + str + ')');
                    //alert(obj)
                    $dg.datagrid('reload', $.parseJSON(str));
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
                    url: "bug/bugAction!delBug.action",
                    data: "bugId=" + row.bugId,
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
                    title: '编辑BUG',
                    width: 600,
                    height: 400,
                    href: " ",
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
        //弹窗增加
        function addRowsOpenDlg() {
            parent.$.modalDialog({
                title: '添加BUG',
                width: 600,
                height: 400,
                href: " ",
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
    </script>
</head>
<body>
<div data-options="region:'center',border : false">
    <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
        <span class="badge">提示</span>
        <p>
            在此你可以对<span class="label-info"><strong>bug</strong></span>进行管理!建议上传压缩包。直接上传图片会导致预览变形！可以直接把文件拖入编辑器中,完成上传！最大上传文件为25M!
        </p>
    </div>
    <div id="tb" style="padding:2px 0">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="padding-left:2px">
                    <shiro:hasPermission name="bugAdd">
                        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"
                           onclick="addRowsOpenDlg();">添加</a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="bugEdit">
                        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                           onclick="updRowsOpenDlg();">编辑</a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="bugDel">
                        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                           onclick="delRows();">删除</a>
                    </shiro:hasPermission>
                </td>
                <td style="padding-left:2px">
                    <input id="searchbox" type="text"/>
                </td>
            </tr>
        </table>
    </div>
    <div id="mm">
        <div name="bugName">BUG名称</div>
        <div name="attachmentUrl">BUG简要</div>
        <div name="description">BUG附件描述</div>
    </div>
    <table id="dg" title="BUG管理"></table>
</div>
</body>
</html>

