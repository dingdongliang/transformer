/**
 * Created by dyenigma on 2016/5/6.
 */
//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉字
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字'
    },
    //移动手机号码验证
    mobile: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不准确.'
    },
    //国内邮编验证
    zipCode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.'
    },
    //用户账号验证(只能包括 _ 数字 字母)
    account: {//param的值为[]中值
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    },
    //数字
    number: {
        validator: function (value) {
            var reg = /^[0-9]*$/;
            return reg.test(value);
        },
        message: '只能是数字'
    },
})