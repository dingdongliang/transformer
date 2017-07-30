
<script type="text/javascript" charset="utf-8">
    var centerTabs;
    var tabsMenu;
    $(function () {
        tabsMenu = $('#tabsMenu').menu({
            onClick: function (item) {
                var curTabTitle = $(this).data('tabTitle');
                var type = $(item.target).attr('type');
                if (type === 'refresh') {
                    refreshTab(curTabTitle);
                    return;
                }
                if (type === 'close') {
                    var t = centerTabs.tabs('getTab', curTabTitle);
                    if (t.panel('options').closable) {
                        centerTabs.tabs('close', curTabTitle)
                    }
                    return;
                }
                var allTabs = centerTabs.tabs('tabs');
                var closeTabsTitle = [];
                $.each(allTabs,
                        function () {
                            var opt = $(this).panel('options');
                            if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                                closeTabsTitle.push(opt.title)
                            } else if (opt.closable && type === 'closeAll') {
                                closeTabsTitle.push(opt.title)
                            }
                        });
                for (var i = 0; i < closeTabsTitle.length; i++) {
                    centerTabs.tabs('close', closeTabsTitle[i])
                }
            }
        });
        centerTabs = $('#centerTabs').tabs({
            tools: [{
                iconCls: 'icon-reload',
                handler: function () {
                    var href = $('#centerTabs').tabs('getSelected').panel('options').href;
                    if (href) {
                        var index = $('#centerTabs').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
                        $('#centerTabs').tabs('getTab', index).panel('refresh')
                    } else {
                        var panel = $('#centerTabs').tabs('getSelected').panel('panel');
                        var frame = panel.find('iframe');
                        try {
                            if (frame.length > 0) {
                                for (var i = 0; i < frame.length; i++) {
                                    frame[i].contentWindow.document.write('');
                                    frame[i].contentWindow.close();
                                    frame[i].src = frame[i].src
                                }
                                if ($.browser.msie) {
                                    CollectGarbage()
                                }
                            }
                        } catch (e) {
                        }
                    }
                }
            },
                {
                    iconCls: 'icon-no',
                    handler: function () {
                        var index = $('#centerTabs').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
                        var tab = $('#centerTabs').tabs('getTab', index);
                        if (tab.panel('options').closable) {
                            $('#centerTabs').tabs('close', index)
                        } else {
                            $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭', 'error')
                        }
                    }
                },
                {
                    iconCls: 'icon-color',
                    handler: function () {
                        $('#theme').menu({
                            onClick: function (item) {
                                var cookiesColor1 = jqueryUtil.cookies.get("cookiesColor");
                                if (cookiesColor1 != item.id) {
                                    jqueryUtil.cookies.set("cookiesColor", item.id, 30);
                                    jqueryUtil.chgSkin(item.id, cookiesColor1)
                                }
                            }
                        });
                        $('#theme').menu('show', {
                            left: '91%',
                            top: 97
                        })
                    }
                }],
            fit: true,
            border: false,
            onContextMenu: function (e, title) {
                e.preventDefault();
                tabsMenu.menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data('tabTitle', title)
            }
        })
    });

    function addTab(node) {
        var nodes = node.split("||");
        if (centerTabs.tabs('exists', nodes[0])) {
            centerTabs.tabs('select', nodes[0])
        } else {
            centerTabs.tabs('add', {
                title: nodes[0],
                closable: true,
                iconCls: nodes[1],
                content: "<iframe src=" + nodes[2] + " frameborder=\"0\" style=\"border:0;width:100%;height:99.4%;\"></iframe>",
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        refreshTab(nodes[0])
                    }
                }]
            })
        }
    }
    function refreshTab(title) {
        var tab = centerTabs.tabs('getTab', title);
        centerTabs.tabs('update', {
            tab: tab,
            options: tab.panel('options')
        })
    }
</script>
<div id="centerTabs">
    <div iconCls="icon-home" title="首页" border="false"
         style="overflow: hidden;">
        <div class="mainindex">
            <div class="welinfo">
				<span><img src="/images/sun.png"
                           alt="天气"/></span> <b>${currUser}早上好，欢迎使用信息管理系统</b>(dyenigma@163.com) <a
                    href="#">帐号设置</a>
            </div>
            <div class="welinfo">
				<span><img src="/images/time.png"
                           alt="时间"/></span> <i>您上次登录的时间：2013-10-09 15:22</i> （不是您登录的？<a href="#">请点这里</a>）
            </div>
            <div class="xline"></div>
            <div class="box"></div>
            <div class="welinfo">
				<span><img src="/images/dp.png"
                           alt="提醒"/></span> <b>信息管理系统介绍</b>
            </div>
            <ul class="infolist">
                <li><span>JDK版本:1.8</span></li>
                <li><span>主体技术:SpringBoot+MyBatis(通用插件and分页插件)+Shiro+EasyUI+Druid(多数据源+读写分离)
                    +swagger+HTML5/CSS3+Quartz</span></li>
                <li><span>Shiro提供给您细分到按钮的权限管理，更安全，更可靠</span></li>
                <li><span>多种权限支持模型：权限-角色-岗位-用户；权限-项目-用户；权限-角色-用户；权限-用户</span></li>
                <li><span>SpringBoot提供简约开发、swagger自动生成API文档</span></li>
                <li><span>EasyUI统一的后台管理页面风格</span></li>
                <li><span>Druid强大的数据库管理工具，在监控、可扩展性、稳定性和性能方面都有明显的优势</span></li>
                <li><span>Mybatis灵活使用的数据库操作纽带</span></li>
                <li><span>HTML5+css3.0界面UI，美观漂亮时尚、前沿</span></li>
                <li><span>Quartz定时任务处理</span></li>
                <li><span>待添加功能：redis、ES、消息处理</span></li>
            </ul>
        </div>
    </div>
</div>
<div id="tabsMenu" style="width: 120px; display: none;">
    <div type="refresh">刷新</div>
    <div class="menu-sep"></div>
    <div type="close">关闭</div>
    <div type="closeOther">关闭其他</div>
    <div type="closeAll">关闭所有</div>
</div>
<div id="theme" class="easyui-menu" style="width: 120px; display: none">
    <div id="default" data-options="iconCls:'icon-save'">default</div>
    <div id="black">black</div>
    <div id="bootstrap" data-options="iconCls:'icon-save'">bootstrap</div>
    <div id="gray" data-options="iconCls:'icon-save'">gray</div>
    <div id="metro" data-options="iconCls:'icon-save'">metro</div>
</div>