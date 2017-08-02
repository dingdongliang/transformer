
<script type="text/javascript">
    $('#postList').tree({
        url: '/manage/users/getPostList',
        animate: true,
        checkbox: true,
        idField: 'id',
        textField: 'text',
        onlyLeafCheck: true,
        onLoadSuccess: function (node, data) {
            //这里使用ajax处理选中用户已经存在的权限
            $.ajax({
                url: "/manage/users/getUserPostByUsedId",
                data: {
                    'userId': $("#dg").datagrid('getSelected').userId
                },
                method: "POST",
                dataType: "JSON",
                success: function (msg) {
                    //判断是否有权限
                    if (msg.length != 0) {
                        //循环选中包含的权限
                        $.each(msg, function (i, e) {
                            var node = $("#postList").tree('find', e.postId);
                            if (node) {
                                $("#postList").tree('check', node.target);
                            }
                        });
                    }
                },
                error: function () {
                    //获取失败，取消所有的选中
                    $("#postList").tree('unselectAll');
                    $.messager.show({
                        title: "提示",
                        msg: "预设岗位失败!",
                        timeout: 1000 * 2
                    });
                }
            });
        }
    });

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" style="padding: 10px;">
        <ul id="postList"/>
    </div>
</div>
