<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/23
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EasyUI</title>
    <link rel="stylesheet" href="css/system.css">
    <link rel="stylesheet" type="text/css" href="css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="myResource/jquery-easyui-1.7.0/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="myResource/jquery-easyui-1.7.0/themes/icon.css"/>

    <script type="text/javascript" src="myResource/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="myResource/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="myResource/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="myResource/carhartl-jquery-cookie-v1.4.1/jquery.cookie.js"></script>

    <script type="text/javascript" src="js/common/commonutil.js"></script>

</head>
<body >

<%--主页--%>
<div id="admin_layout">

    <%--顶部页面--%>
    <div class="header" data-options="region:'north',split:true,height:70" style="overflow: hidden;background: url('../myResource/images/bg_header.jpg') bottom repeat-x;">
        <div class="header-left">
            <h1>EasyUI</h1>
        </div>
        <div class="header-right">
            <p><strong class="easyui-tooltip" title="2条未读消息">admin</strong>，欢迎您！</p>
            <p>
                <a href="#">网站首页</a>|
                <a href="#">支持论坛</a>|
                <a href="#">帮助中心</a>|
                <a href="#">安全退出</a>
            </p>
        </div>
    </div>

    <%--底部页面,collapsed:true"--%>
    <div data-options="region:'south',title:'South Title',split:true,height:70">
        &copy; 2019
    </div>

    <%--右边页面,collapsed:true--%>
    <div data-options="region:'east',title:'East Title',split:true,width:100"></div>

    <%--左边页面--%>
    <div data-options="region:'west',split:true,title:'导航菜单',width:130">
        <div class="easyui-accordion" data-options="border:false,fit:true">
            <div title="用户管理" data-options="iconCls:'icon-application-cascade'" style="padding:5px;">
                <ul id="user_manager_menus"></ul>
            </div>
        </div>
    </div>

    <%--中间页面--%>
    <div data-options="region:'center',split:true" style="background:#eee;overflow: hidden;">
        <div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="主页" style="border: 0px;"></div>
        </div>
    </div>

</div>

<script>
    var adminLayout = $("#admin_layout");
    var userManagerMenus = $('#user_manager_menus');
    var tabs = $('#tabs');

    /*主页面*/
    adminLayout.layout({
        border:false,
        fit:true
    });

    /*用户管理菜单栏*/
    userManagerMenus.tree({
        data: [{
            text:'用户列表',
            iconCls:'icon-users',
            attributes:{
                url:'user-list'
            }
        },{
            text:'新增用户',
            iconCls:'icon-user-add',
            attributes: {
                url:'user-add'
            }
        },{
            text:'角色管理',
            iconCls:'icon-user-group',
            attributes:{
                url:'roles-manager'
            }
        }],

        /*点击菜单栏，跳到响应的页面*/
        onClick: function (node) {
            if (userManagerMenus.tree("isLeaf", node.target)) {
                if (tabs.tabs('exists', node.text)) {
                    tabs.tabs("select", node.text);
                } else {
                    tabs.tabs('add', {
                        title: node.text,
                        closable: true,
                        fit: true,
                        border: false,
                        bodyCls: "content",
                        href: node.attributes.url
                    });
                }
            }
        }
    });

</script>

</body>
</html>
