<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8">
    <title>欢迎登录后台管理系统</title>
    <link href="/css/style.css" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript"
            src="/js/jquery.js"></script>
    <style>
        body {
            background: #1c77ac url(/images/light.png) no-repeat center top;
            overflow: hidden;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            $('.loginbox').css({
                'position': 'absolute',
                'left': ($(window).width() - 692) / 2
            });

            $(window).resize(function () {
                $('.loginbox').css({
                    'position': 'absolute',
                    'left': ($(window).width() - 692) / 2
                });
            })

            $('#kaptchaImage').click(
                    function () {//生成验证码
                        $(this).hide().attr('src', '/captcha?' + Math.floor(Math.random() * 100))
                                .fadeIn();
                    });
        });
    </script>
</head>
<body>
<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>
<form action="enter" method="POST">
    <div class="loginbody">
        <span class="systemlogo"></span>

        <div class="loginbox loginbox2">
            <ul>
                <li><input name="account" type="text" class="loginuser"
                           value="system"/></li>
                <li><input name="password" type="password" class="loginpwd"
                           value="system"/></li>
                <li class="yzm"><span><input name="verifyCode"
                                             type="text" value="验证码" onclick="JavaScript:this.value=''"/></span>
                    <cite><img
                            src="captcha" width="114" height="46"
                            id="kaptchaImage" style="margin-bottom: -3px"/></cite></li>
                <li><input type="submit" class="loginbtn" value="登录"/><label><input
                        name="" type="checkbox" value="" checked="checked"/>记住密码</label><label><a
                        href="#">忘记密码？</a></label></li>
            </ul>
        </div>
    </div>
</form>
<div class="loginbm">Copyright © 2015 www.4Tiro.com belongs to
    DyEnigma. All Rights Reserved.
</div>
</body>
</html>