<!DOCTYPE html>
<html>
<head>

    <title>问答列表查看</title>
<#include '../include.ftl'>
    <script type="text/javascript">
        $(function () {
            $("#dg").datagrid({
                width: 'auto',
                height: $(this).height() - 17,
                url: "/manage/qstn/findAllByPage",
                pagination: true,
                animate: true,
                rownumbers: true,
                fitColumns: true,
                striped: true,
                border: true,
                idField: 'qstnId',
                singleSelect: true,
                columns: [[{
                    field: 'qstnTitle',
                    title: '问题标题',
                    width: 260,
                    align: 'center'
                }, {
                    field: 'qstnKey',
                    title: '关键字',
                    width: 170,
                    align: 'center'
                }, {
                    field: 'status',
                    title: '问题状态',
                    width: 50,
                    align: 'center'
                }, {
                    field: 'creater',
                    title: '录入人',
                    width: 70,
                    align: 'center'
                }, {
                    field: 'created',
                    title: '录入日期',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row) {
                        return moment(value).format('YYYY-MM-DD hh:mm:ss a');
                    }
                }]],
                toolbar: '#tb',
                onLoadSuccess: function (data) {
                    if (data == null) {
                        $.messager.show({
                            title: "提示",
                            msg: "没有找到符合条件的信息",
                            timeout: 1000 * 2
                        });
                    }
                },
                onLoadError: function () {
                    $.messager.show({
                        title: "提示",
                        msg: "没有找到符合条件的信息",
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
            <shiro:hasPermission name="delQA">
                <li id="delQA"><span><img
                        src="/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="tablelist" id="dg" title="问答列表"></table>
</div>
</body>
</html>