<script type="text/javascript">
    $(function () {

        $("#form").form({
            url: "/manage/organ/saveOrUpdateOrgan",
            onSubmit: function () {
                $.messager.progress({
                    title: '提示',
                    text: '数据处理中，请稍后....'
                });
                var isValid = $(this).form('validate');
                if (!isValid) {
                    $.messager.progress('close');
                }
                return isValid;
            },
            success: function (result) {
                $.messager.progress('close');
                result = eval("(" + result + ")");
                if (result.code == 200) {
                    $.modalDialog.openner.treegrid('reload');
                    $.modalDialog.handler.dialog('close');
                    $.messager.show({
                        title: result.title,
                        msg: result.message,
                        timeout: 1000 * 2
                    });
                } else {
                    $.messager.show({
                        title: result.title,
                        msg: result.message,
                        timeout: 1000 * 2
                    });
                }
            }
        });
    });
</script>
<style>
    .easyui-textbox {
        height: 18px;
        width: 170px;
        line-height: 16px;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
        transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
    }

    .easyui-combobox {
        height: 18px;
        width: 170px;
    }

    textarea:focus, input[type="text"]:focus {
        border-color: rgba(82, 168, 236, 0.8);
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);
        outline: 0 none;
    }

    table {
        background-color: transparent;
        border-collapse: collapse;
        border-spacing: 0;
        max-width: 100%;
    }

    fieldset {
        border: 0 none;
        margin: 0;
        padding: 0;
    }

    legend {
        -moz-border-bottom-colors: none;
        -moz-border-left-colors: none;
        -moz-border-right-colors: none;
        -moz-border-top-colors: none;
        border-image: none;
        border: 0 none #E5E5E5;
        border-bottom: 1px solid;
        color: #999999;
        line-height: 20px;
        display: block;
        margin-bottom: 10px;
        padding: 0;
        width: 100%;
    }

    input, textarea {
        font-weight: normal;
    }

    table, th, td {
        text-align: left;
        padding: 6px;
    }
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
        <form id="form" method="post">
            <fieldset>
                <legend><img src="/images/fromedit.png" style="margin-bottom: -3px;"/> 部门编辑
                </legend>
                <input name="divId" id="divId" type="hidden"/>
                <input name="status" id="status" type="hidden"/>
                <input name="iconCls" id="iconCls" type="hidden"/>
                <table>
                    <tr>
                        <th>部门名称</th>
                        <td><input name="divName" id="divName" placeholder="请输入部门名称"
                                   class="easyui-textbox easyui-validatebox" type="text" data-options="required:true"/>
                        </td>
                        <th>负责人</th>
                        <td><input id="manager" name="manager" type="text" class="easyui-combobox"
                                   url="/manage/users/findAllUserList" valueField="userId" textField="userName"/></td>
                    </tr>
                    <tr>
                        <th>联系电话</th>
                        <td><input name="divPhone" id="divPhone" type="text" class="easyui-textbox easyui-validatebox"/>
                        </td>
                        <th>所属公司</th>
                        <td><input id="coId" name="coId" type="hidden"/>
                            <input id="coName" name="coName" type="text" data-options="readonly:true"
                                   class="easyui-textbox easyui-validatebox"/>
                        </td>
                    </tr>
                    <tr>
                        <th>地址</th>
                        <td colspan="3"><input id="divAdr" name="divAdr" type="text" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <th>描述</th>
                        <td colspan="3"><textarea class="easyui-textbox" name="divDesc"
                                                  style="width: 420px;height: 100px;"></textarea></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
