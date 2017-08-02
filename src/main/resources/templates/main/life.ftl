<!DOCTYPE HTML>
<html lang="zh-CN">
<head>


    <title>4Tiro-学习生活</title>
    <meta name="keywords" content="Java,git,spring,mvc,mybatis,oracle,mysql,shiro"/>
    <meta name="description" content=""/>
<#include '../manage/include.ftl'>
</head>

<body>
<#include 'head.ftl'>
<!--content start-->
<div id="content">
    <!--left-->
    <div class="left" id="about_me">
        <div class="weizi">
            <div class="wz_text">
                当前位置：<a href="#">首页</a>>
                <h1>关于我</h1>
            </div>
        </div>
        <div class="about_content">博主是一个草根站长，喜欢研究web前端技术和SEO技术。</div>
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
