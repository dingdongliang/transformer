
<script type="text/javascript">
    $(function () {
        $("#prntId").combotree({
            onSelect: function (node) {
                $("#prntName").val(node.text);
            }
        });


        $("#form").form({
            url: "/manage/comp/saveOrUpdateComp",
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
                result = $.parseJSON(result);
                if (result.code==200) {
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

    .easyui-combotree {
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
        <form id="form" method="POST">
            <legend><img src="/images/fromedit.png" style="margin-bottom: -3px;"/> 公司编辑</legend>
            <input name="coId" id="coId" type="hidden"/>
            <input name="status" id="status" type="hidden"/>
            <input name="state" id="state" type="hidden"/>
            <table>
                <tr>
                    <th>公司名称</th>
                    <td><input name="coName" id="coName" class="easyui-textbox easyui-validatebox"
                               required="required" type="text"/></td>
                    <th>负责人</th>
                    <td><input id="manager" name="manager" type="text" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <th>上级公司</th>
                    <td>
                        <input name="prntId" id="prntId" class="easyui-combotree"
                               data-options="url:'manage/comp/findSuperComp',method:'get'">
                        <input name="prntName" id="prntName" type="hidden"/></td>
                    </td>
                    <th>排序</th>
                    <td><input id="sort" name="sort" type="text" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <th>公司地址</th>
                    <td><input id="coAdr" name="coAdr" type="text"
                               class="easyui-textbox"/></td>
                    <th>邮编</th>
                    <td><input id="coZip" name="coZip" type="text"
                               class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <th>联系电话</th>
                    <td><input name="coPhone" id="coPhone" type="text" class="easyui-textbox"/></td>
                    <th>联系人</th>
                    <td><input id="contact" name="contact" type="text" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <th>电子邮箱</th>
                    <td><input id="coEmail" name="coEmail" type="text" class="easyui-textbox easyui-validatebox"
                               data-options="validType:'email'"/></td>
                    <th>传真</th>
                    <td><input name="coFax" id="coFax" type="text" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <th>开户行</th>
                    <td><input id="bank" name="bank" type="text" class="easyui-textbox"/></td>
                    <th>开户账号</th>
                    <td><input id="bankAcct" name="bankAcct" type="text"
                               class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <th>描述</th>
                    <td colspan="3"><textarea class="easyui-textbox" name="coDesc" data-options="multiline:true"
                                              style="width: 420px;height: 60px;"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>

