
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>


    <title>4Tiro-问道</title>
    <meta name="keywords" content="Java,git,spring,mvc,mybatis,oracle,mysql,shiro"/>
    <meta name="description" content=""/>
<#include '../manage/include.ftl'>
</head>

<body>
<#include 'head.ftl'>
<!--content start-->
<div id="content">
    <!--left-->
    <div class="left" id="guestbook">
        <div class="weizi">
            <div class="wz_text">
                当前位置：<a href="#">首页</a>>
                <h1>留言板</h1>
            </div>
        </div>
        <div class="news_content">
            <div class="news_top">
                <h1>浅谈：html5和html的区别</h1>
                <p>
                    <span class="left sj">时间:2014-8-9</span><span class="left fl">分类:学无止境</span>
                    <span class="left author">DyEnigma</span>
                </p>
                <div class="clear"></div>
            </div>
            <div class="news_text">
                <p>统一项目开发人员的编码风格。主要包括：设置Code Templates、Eclipse formatter.</p>
                <p>修改eclipse中的$(user)变量</p>
                <p>在eclipse.ini中添加 -vmargs -Duser.name=your name
                    记得一定要在-vmargs之后，否则无效。 保存重启Eclipse即可。</p>
            </div>
        </div>
    </div>
    <!--end left -->
    <#include 'right.ftl'>
    <div class="clear"></div>
</div>
<!--content end-->
<#include 'foot.ftl'>
<script type="text/javascript">
    jQuery(".lanmubox").slide({
        easing: "easeOutBounce",
        delayTime: 400
    });
</script>
</body>
</html>

