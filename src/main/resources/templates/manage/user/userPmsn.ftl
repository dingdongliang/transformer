
<script type="text/javascript">
    $("#function").treegrid({
        height: 326,
        url: "/manage/menu/findAllRoleMenu",
        rownumbers: true,
        idField: 'id',
        treeField: 'name',
        singleSelect: false,
        deepCascadeCheck: true,
        border: false,
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
            }, {field: 'description', title: '菜单描述', width: parseInt($(this).width() * 0.13), align: 'left'}
        ]], onClickRow: function (row) {
            $("#function").treegrid('cascadeCheck', {
                id: row.id,
                deepCascade: true //深度级联
            });
        },
        onLoadSuccess: function (node, data) {
            //这里使用ajax处理选中用户已经存在的权限
            $.ajax({
                url: "/manage/users/getUserPmsnByUsedId",
                data: {
                    'userId': $("#dg").datagrid('getSelected').userId
                },
                method: "POST",
                dataType: "JSON",
                success: function (msg) {
                    //初始化权限，取消所有的选中
                    $("#function").treegrid('unselectAll');
                    //判断是否有权限
                    if (msg.length != 0) {
                        //循环选中包含的权限
                        $.each(msg, function (i, e) {
                            $("#function").treegrid('select', e.pmsnId);
                        });
                    }
                },
                error: function () {
                    //获取失败，取消所有的选中
                    $("#function").treegrid('unselectAll');
                    $.messager.show({
                        title: "提示",
                        msg: "预设权限失败!",
                        timeout: 1000 * 2
                    });
                }
            });
        }
    });

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <table id="function"></table>
</div>
