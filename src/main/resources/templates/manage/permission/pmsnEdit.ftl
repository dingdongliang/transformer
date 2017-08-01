<script type="text/javascript">
    /**
     * Created by dyenigma on 2016/3/14.
     */
    $(function () {
        $("#prntId").combotree({
            onSelect: function (node) {
                $("#prntName").val(node.text);
            }
        });
        $("#iconCls").combobox({
            data: $.iconData,
            formatter: function (v) {
                return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
            }
        });
        var tempId = $("#tempId").val();
        if (tempId == "M") {
            $("#prntId").combotree("disable");
        }
        $("#form").form({
            url: "/manage/menu/saveOrUpdateMenu",
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
                var obj = eval("(" + result + ")");
                if (obj.code == 200) {
                    $.modalDialog.openner.treegrid('reload');
                    $.modalDialog.handler.dialog('close');
                    $.messager.show({
                        title: obj.title,
                        msg: obj.message,
                        timeout: 1000 * 2
                    });
                } else {
                    $.messager.show({
                        title: obj.title,
                        msg: obj.message,
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
        line-height: 16px;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
        transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
    }

    .easyui-combobox, .easyui-combotree, .easyui-textbox {
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
        <input name="tempId" id="tempId" type="hidden" value=""/>
        <form id="form" method="post">
            <fieldset>
                <legend><img src="/images/fromedit.png" style="margin-bottom: -3px;"/> 菜单编辑
                </legend>
                <input name="pmsnId" id="pmsnId" type="hidden"/>
                <input name="status" id="status" type="hidden"/>
                <input name="state" id="state" type="hidden"/>
                <table>
                    <tr>
                        <th>菜单名称</th>
                        <td><input name="pmsnName" id="pmsnName" placeholder="请输入菜单名称"
                                   class="easyui-textbox easyui-validatebox"
                                   type="text" required="required"/></td>
                        <th>父菜单名称</th>
                        <td>
                            <input name="prntId" id="prntId" class="easyui-combotree"
                                   data-options="url:'/manage/menu/findSuperMenu',method:'get'">
                            <input name="prntName" id="prntName" type="hidden"/></td>
                    </tr>
                    <tr>
                        <th>排序编码</th>
                        <td><input name="sort" id="sort" type="text" class="easyui-textbox easyui-validatebox"/></td>
                        <th>菜单图标</th>
                        <td><input id="iconCls" class="easyui-combobox" name="iconCls" type="text"/></td>
                    </tr>
                    <tr>
                        <th>菜单路径</th>
                        <td><input id="pmsnUrl" name="pmsnUrl" type="text" class="easyui-textbox easyui-validatebox"
                                   value="javascript:void(0);" required="required"/></td>
                        <th>菜单编码</th>
                        <td><input id="pmsnCode" name="pmsnCode" type="text" class="easyui-textbox easyui-validatebox"
                                   required="required"/></td>
                    </tr>
                    <tr>
                        <th>菜单类型</th>
                        <td>
                            <select id="pmsnType" class="easyui-combobox" name="pmsnType"
                                    data-options="required:true">
                                <option value="M">菜单</option>
                                <option value="O">操作</option>
                            </select>
                        </td>
                        <th>是否启用</th>
                        <td><select id="isUsed" class="easyui-combobox" name="isUsed"
                                    data-options="required:true">
                            <option value="Y">是</option>
                            <option value="N">否</option>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <th>默认权限</th>
                        <td><select id="isDefault" class="easyui-combobox" name="isDefault"
                                    data-options="required:true">
                            <option value="Y">是</option>
                            <option value="N" selected="true">否</option>
                        </select>
                        </td>
                        <th></th>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <th>描述</th>
                        <td colspan="3"><textarea class="easyui-textbox" name="pmsnDesc"
                                                  style="width: 435px;height: 100px;"></textarea></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
