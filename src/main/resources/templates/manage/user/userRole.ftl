<script type="text/javascript">
    $("#roleList").datalist({
        url: '/manage/role/findAllRoleList',
        method: 'get',
        checkbox: true,
        idField: 'roleId',
        textField: 'roleName',
        singleSelect: false,
        border: false,
        onLoadSuccess: function (node, data) {
            //这里使用ajax处理选中用户已经存在的权限
            $.ajax({
                url: "/manage/users/getUserRoleByUsedId",
                data: {
                    'userId': $("#dg").datagrid('getSelected').userId
                },
                method: "POST",
                dataType: "JSON",
                success: function (msg) {
                    //初始化权限，取消所有的选中
                    $("#roleList").datalist('unselectAll');
                    //判断是否有权限
                    if (msg.length != 0) {
                        //循环选中包含的权限
                        $.each(msg, function (i, e) {
                            $("#roleList").datalist('selectRecord', e.roleId);
                        });
                    }
                },
                error: function () {
                    //获取失败，取消所有的选中
                    $("#roleList").datalist('unselectAll');
                    $.messager.show({
                        title: "提示",
                        msg: "预设角色失败!",
                        timeout: 1000 * 2
                    });
                }
            });
        }
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div id="roleList"></div>
</div>
