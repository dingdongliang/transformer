
<script type="text/javascript">
    $(function () {

        $("#userList").datalist({
            url: '/manage/users/findAllUserList',
            method: 'get',
            checkbox: true,
            idField: 'userId',
            textField: 'userName',
            singleSelect: false,
            onLoadSuccess: function (node, data) {

                //这里使用ajax处理选中用户已经存在的权限
                $.ajax({
                    url: "/manage/project/getPrjUserByPrjId",
                    data: {
                        'prjId': $("#dg").treegrid('getSelected').prjId
                    },
                    method: "POST",
                    dataType: "JSON",
                    success: function (msg) {
                        //初始化权限，取消所有的选中
                        $("#userList").datalist('unselectAll');
                        //判断是否有权限
                        if (msg.length != 0) {
                            //循环选中包含的权限
                            $.each(msg, function (i, e) {
                                $("#userList").datalist('selectRecord', e.userId);
                            });
                        }
                    },
                    error: function () {
                        //获取失败，取消所有的选中
                        $("#userList").datalist('unselectAll');
                        $.messager.show({
                            title: "提示",
                            msg: "该项目组目前还没有成员!",
                            timeout: 1000 * 2
                        });
                    }
                });
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
        <input name="prjId" id="prjId" type="hidden"/>
        <div style="width:570px;height:280px" id="userList">
        </div>
    </div>
</div>
