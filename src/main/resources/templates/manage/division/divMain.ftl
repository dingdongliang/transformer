<!DOCTYPE html>
<html>
<head>

    <title>部门管理</title>
<#include '../include.ftl'>
    <script type="text/javascript">
        var $dg;
        var $grid;
        $(function () {
            $dg = $("#dg");
            $grid = $dg.treegrid();
            $('#coList').tree({
                url: '/manage/comp/getAllCo',
                loadFilter: function (rows) {
                    return convert(rows);
                },
                animate: true,
                onLoadSuccess: function (node, data) {
                    if (data.length > 0)
                        loadDiv(data[0].id);
                },
                onClick: function (node) {
                    loadDiv(node.id);
                }
            });


            $("#addOrgan").click(function () {

                var data = $("#coList").tree('getSelected');

                if (data == null) {
                    $.messager.show({
                        title: "提示",
                        msg: "请先选择要添加部门的公司!",
                        timeout: 1000 * 2
                    });
                    return;
                } else {
                    var row = $dg.treegrid('getSelected');
                    $.modalDialog({
                        title: "添加部门",
                        width: 600,
                        height: 400,
                        href: "/manage/organ/organEditDlg/" + data.id,
                        onLoad: function () {
                            if (row) {
                                var f = $.modalDialog.handler.find("#form");
                                f.form("load", {
                                    "prntId": row.divId,
                                    "coId": data.id,
                                    "coName": data.text
                                });
                            } else {
                                var f = $.modalDialog.handler.find("#form");
                                f.form("load", {
                                    "coId": data.id,
                                    "coName": data.text
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
                }
            });


            $("#updateOrgan").click(function () {
                var row = $dg.treegrid('getSelected');
                var data = $("#coList").tree('getSelected');
                if (row) {
                    $.modalDialog({
                        title: "编辑部门",
                        width: 600,
                        height: 400,
                        href: "/manage/organ/organEditDlg/" + data.id,
                        onLoad: function () {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", row);
                        },
                        buttons: [{
                            text: '编辑',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $grid; //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
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

            $("#delOrgan").click(function () {
                var node = $dg.treegrid('getSelected');
                if (node) {
                    $.messager.confirm("提示", "确定要删除记录吗?",
                            function (result) {
                                if (result) {
                                    var request = $.ajax({
                                        url: "/manage/organ/delOrgan",
                                        data: {
                                            'id': node.divId
                                        },
                                        method: "POST",
                                        dataType: "JSON"
                                    });

                                    request.done(function (rsp) {
                                        if (rsp.code == 200) {
                                            $dg.treegrid('remove', node.divId);
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

        function loadDiv(id) {
            $('#dg').treegrid({
                height: 503,
                url: '/manage/organ/findDivByCoId/' + id,
                idField: 'divId',
                treeField: 'divName',
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: false
            });
        }

        //easyUI-tree:格式化简单格式的JSON数据
        function convert(rows) {
            function exists(rows, prntId) {
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].coId == prntId) return true;
                }
                return false;
            }

            var nodes = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (!exists(rows, row.prntId)) {
                    nodes.push({
                        id: row.coId,
                        text: row.coName,
                        iconCls: row.iconCls
                    });
                }
            }

            var toDo = [];
            for (var i = 0; i < nodes.length; i++) {
                toDo.push(nodes[i]);
            }
            while (toDo.length) {
                var node = toDo.shift();
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i];
                    if (row.prntId == node.id) {
                        var child = {id: row.coId, text: row.coName, iconCls: row.iconCls};
                        if (node.children) {
                            node.children.push(child);
                        } else {
                            node.children = [child];
                        }
                        toDo.push(child);
                    }
                }
            }
            return nodes;
        }

    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:220px;background-color: #eff8fc">
    <ul id="coList"/>
</div>
<div data-options="region:'center'" class="rightinfo">
    <div class="easyui-layout" id="tb">
        <ul class="toolbar">
            <shiro:hasPermission name="divAdd">
                <li id="addOrgan"><span><img
                        src="/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="divEdit">
                <li id="updateOrgan"><span><img
                        src="/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="divDel">
                <li id="delOrgan"><span><img
                        src="/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table title="部门信息" class="easyui-treegrid" id="dg" data-options="toolbar: '#tb'">
        <thead>
        <tr>
            <th data-options="field:'divName'" width="10%" align="center">部门名称</th>
            <th data-options="field:'coName'" width="15%" align="center">上级部门</th>
            <th data-options="field:'manager'" width="25%" align="center">负责人</th>
            <th data-options="field:'divPhone'" width="10%" align="center">部门电话</th>
            <th data-options="field:'divAdr'" width="15%" align="center">部门地址</th>
            <th data-options="field:'divDesc'" width="23%" align="center">部门描述</th>
        </tr>
        </thead>
    </table>

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