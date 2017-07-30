<script type="text/javascript">
    $(function () {

        $("#menuAccordion").accordion({
            animate: true, fit: true, border: false
        });

        var request = $.ajax({
            url: "getUsersMenu",
            method: "POST",
            dataType: "JSON"
        });

        //这里的msg格式，在不同的JQuery版本，体现方式不同
        request.done(function (msg) {
            var menuList = "<dl class=\"leftmenu\">";
            $.each(msg, function (i, e) {
                menuList += "<dd><div class=\"title\"><span style=\"height: 16px;\"><img style=\"padding-bottom: 5px;\" src=\"/css/icons/" + e.iconCls + ".png\"/></span>" + e.name + "</div><ul class=\"menuson\">";
                if (e.child && e.child.length > 0) {
                    $.each(e.child, function (ci, ce) {
                        var effort = ce.name + "||" + ce.iconCls + "||" + ce.url;
                        menuList += "<li><div class=\"childMenu\"><cite></cite><a href=\"javascript:void(0)\" target=\"rightFrame\" onclick=\"addTab('" + effort + "');return false;\">" + ce.name + "</a><i></i></div></li>";
                    });
                }
                menuList += "</ul></dd>";
            });
            menuList += "</dl>";

            $("#menuAccordion").html(menuList);

            //二级菜单委托绑定点击事件
            $('#menuAccordion').on('click', 'dl dd ul li .childMenu', function () {
                var $parent = $(this).parent();
                $(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
                $parent.addClass("active");

                if ($parent.hasClass("open")) {
                    $parent.removeClass("open").find('.sub-menus').hide();
                } else {
                    $parent.addClass("open").find('.sub-menus').show();
                }
            });

            //一级菜单委托绑定点击事件
            $('#menuAccordion').on("click", "dl dd .title", function () {
                var $ul = $(this).next('ul');
                $('dd').find('.menuson').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('.menuson').slideUp();
                } else {
                    $(this).next('.menuson').slideDown();
                }
            });
        });

        request.fail(function (jqXHR, textStatus) {
            $.messager.alert("提示", "获取菜单出错,请重新登陆!");
        });
    });
</script>
<style type="text/css">
    #menuAccordion a.l-btn span span {
        display: inline-block;
        height: 14px;
        line-height: 14px;
        margin: 0px 0px 0px 10px;
        padding: 0px 0px 0px 10px;
        vertical-align: baseline;
        width: 178px;
    }

    #menuAccordion a.l-btn span span {
        background-position: left center;
        padding: 0px 0px 0px 20px;
    }

    #menuAccordion {
        padding: 5px;
        overflow: auto;
    }

    #menuAccordion span:focus {
        outline: none;
    }
</style>
<div id="menuAccordion" style="background:#f0f9fd;"></div>