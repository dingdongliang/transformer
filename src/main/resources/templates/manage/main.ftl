<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>4Tiro后台管理界面</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/icon.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false"
     style="height: 88px; overflow: hidden;background:url(/images/topbg.png) repeat-x;"
     href="/manage/mTop"></div>
<div data-options="region:'west',split:true,title:'导航菜单'" style="width: 220px;" href="/manage/mLeft"></div>
<div data-options="region:'south',border:false" style="height: 16px !important" href="/manage/mFoot"></div>
<div data-options="region:'center',plain:true" style="overflow: hidden; border: 0px" href="/manage/mCenter"></div>
</body>
</html>
