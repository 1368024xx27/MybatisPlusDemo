<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--css样式--%>
<link rel="stylesheet" href="css/system.css">

<div class="easyui-layout" fit="true" border="false">

    <%--筛选--%>
    <div data-options="region:'north',collapsed:true,border:false" title="筛选" style="height:110px;">
        <form id="user_list_seach_form" style="border: 0">
            <%--筛选条件--%>
            <div style="margin: 5px 0">
                <div style="padding:0 10px 0 3px;border-right: 1px solid #8aa4af;display: inline-block ">
                    <label for="username_textbox"align="right">用户名：</label>
                    <input name="userName" id="username_textbox">
                </div>
                <div style="padding:0 10px 0 3px;border-right: 1px solid #8aa4af;display: inline-block ">
                    <label for="roles_list_combotree"align="right">角色：</label>
                    <select name="rolesId" id="roles_list_combotree"></select>
                </div>
                <div style="padding:0 10px 0 3px;border-right: 1px solid #8aa4af;display: inline-block ">
                    <label for="user_address_textbox"align="right">地址：</label>
                    <input name="userAddress" id="user_address_textbox"><br/>
                </div>
                <div style="padding:0 10px 0 3px;border-right: 1px solid #8aa4af;display: inline-block ">
                    <lable align="right" for="user_age_start">年龄：</lable>
                    <input name="userAgeStart" type="text" id="user_age_start">
                    <span>至：</span>
                    <input name="userAgeEnd" type="text" id="user_age_end">
                </div>
            </div>
            <%--筛选按钮--%>
            <div style="margin: 5px 0">
                <div style="float: right;margin: 0 100px 0 0">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="userListCleanSearch()">清空</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="userListSearch()">查询</a>
                </div>
            </div>
        </form>
    </div>

    <%--用户列表内容--%>
    <div data-options="region:'center',fit:true,border:false" style="padding-bottom:30px;background:#eee;overflow: hidden;">
        <div id="user_list_datagrid" data-options="region:'center'"></div>
    </div>

    <%--修改用户信息--%>
    <div id="user_edit_window" style="display: none">
        <form id="user_edit_form">
            <div class="row_box" style="display: none">
                <strong class="row_title">用户id：</strong>
                <div class="row_content">
                    <input class="easyui-validatebox easyui-textbox tal" data-options="hidden:true" name="userId"/>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">用户名：</strong>
                <div class="row_content">
                    <input id="user_edit_name_textbox" class="easyui-validatebox tal" name="userName"/>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">用户密码：</strong>
                <div class="row_content">
                    <input id="user_edit_passworld_textbox" class="easyui-validatebox tal" name="password"/>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">确认密码：</strong>
                <div class="row_content">
                    <input id="user_edit_repassword_textbox" class="easyui-validatebox tal" name="repassword"/>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">角色：</strong>
                <div class="row_content">
                    <select id="user_edit_roles_combotree" class="easyui-validatebox tal" name="rolesId"></select>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">年龄：</strong>
                <div class="row_content">
                    <input type="text" id="user_edit_age_numberbox" class="tal" name="userAge">
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">地址：</strong>
                <div class="row_content">
                    <input id="user_edit_address_textbox" class="tal" name="userAddress">
                </div>
            </div>
            <!-- 验证码校验 -->
            <div class="row_box">
                <strong class="row_title">验证码：</strong>
                <div class="row_content">
                    <img class="check_code_show" src="Kaptcha" alt="点击更换" title="点击更换"
                         style="width: 90px;height: 23px;vertical-align: middle;"
                         onclick="changeVerifyCode(this)"/>
                    <input class="check_code easyui-textbox easyui-validatebox" type="text" name="checkCode"
                           data-options="required:true" style="width:60px"/>
                </div>
            </div>
            <%--提示信息的内容框--%>
            <div id="user_edit_message" style="width:160px;height:25px;color:red"></div>
            <%--按钮--%>
            <div style="width: 250px;height: 25px">
                <a id="user_edit_submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-mini-edit'"
                   style="margin: auto 10px;float: right">提交</a>
                <a id="user_edit_close" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'"
                   style="margin: auto 10px;float: right">关闭</a>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="/js/common/commonutil.js"></script>
<script>
    $(function () {
        var userListDatagrid = $("#user_list_datagrid");
        var rolesListCombotree = $("#roles_list_combotree");
        var usernameTextbox = $("#username_textbox");
        var userAddressTextbox = $("#user_address_textbox");
        var userAgeStart = $("#user_age_start");
        var userAgeEnd = $("#user_age_end");
        var userListSeachForm = $("#user_list_seach_form");
        var userEditWindow = $("#user_edit_window");
        var userEditForm = $("#user_edit_form");

        /*用户列表展示*/
        userListDatagrid.datagrid({
            fit: true,
            fitColumns: true,
            height: 'auto',
            border: false,
            rownumbers: true,
            pagination: true,
            pagePosition: 'bottom',
            loadMsg: '数据加载中...',
            striped: true,//条纹化，将奇偶行显示不同的颜色
            pageNumber: 1,
            pageSize: 5,
            select: false,
            pageList: [5, 10, 15, 20, 25, 30],
            url: 'user/userList',
            method: 'get',
            columns: [[
                {field:'ck',width:10,checkbox:true},
                {field: 'userId', title: 'id', width: 100, resizable: true, sortable: true},
                {field: 'userName', title: '姓名', width: 100, resizable: true, sortable: true},
                {field: 'password', title: '密码', width: 100, resizable: true},
                {field: 'rolesId', title: '角色', width: 100, resizable: true,hidden: true},
                {field: 'rolesName', title: '角色', width: 100, resizable: true},
                {field: 'userAge', title: '年龄', width: 100, resizable: true, sortable: true},
                {field: 'userAddress', title: '地址', width: 100, resizable: true}
            ]],
            toolbar: [{
                iconCls: 'icon-add',
                text: '新增',
                handler: function () {
                    $(".tree-title:contains('新增用户')").parent().click();
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '修改',
                handler: function () {
                    var ids = getSelectionsIds();
                    if(ids.length == 0){
                        $.messager.alert('提示','必须选择一个用户才能编辑!');
                        return ;
                    }
                    if(ids.indexOf(',') > 0){
                        $.messager.alert('提示','只能选择一个用户!');
                        return ;
                    }

                    var data = userListDatagrid.datagrid("getSelections")[0];
                    console.info(data);

                    userEditWindow.show().window({
                        width:300,
                        height:350,
                        title:'修改用户',
                        modal:true,
                        minimizable:false,
                        maximizable:false,
                        closable:true,
                        resizable:false,
                        draggable:true,
                        shadow:true,
                        inline:true
                    }).window("open");

                    userEditWindow.window("onOpen",function () {
                        alert("window打开之后");
                        var data = userListDatagrid.datagrid('getSelections')[0];
                        console.info("data:"+data);
                        userEditForm.form("load",data);
                    });

                }
            }, '-', {
                iconCls: 'icon-remove',
                text: '删除',
                handler: function () {
                    alert('help')
                }
            }],
            rowStyler:function (rowIndex,rowData) {
                if(rowIndex%2==0){
                    return 'background-color:rgb(230,230,230);';
                }
            }
        });

        /*用户查询的用户名字关键字*/
        usernameTextbox.textbox({
            width: 120,
            height: 25,
            prompt: '请输入用户名',
            type: 'text'
        });
        /*用户查询的角色下拉框*/
        rolesListCombotree.combotree({
            width: 120,
            height: 25,
            editable: false,
            url: '/roles/rolesList',
            method: 'get',
            valueField: 'rolesId',
            textField: 'rolesName'
        });
        /*用户查询的用户地址关键字*/
        userAddressTextbox.textbox({
            width: 120,
            height: 25,
            prompt: '请输入用户地址',
            type: 'text'
        });
        /*用于查询的用户开始年龄*/
        userAgeStart.numberbox({
            min:1,
            max:120,
            width: 50,
            height: 25,
            precision:0
        });
        /*用于查询的用户截止年龄*/
        userAgeEnd.numberbox({
            min:1,
            max:120,
            width: 50,
            height: 25,
            precision:0
        });
        /*查询提交*/
        userListSearch = function () {
            userListDatagrid.datagrid('load', serializeObject(userListSeachForm));
        }
        /*查询清除*/
        userListCleanSearch = function () {
            userListDatagrid.datagrid('load', {});
            userListSeachForm.form('clear');
        }

        /*获取选择的用户的id*/
        function getSelectionsIds(){
            var sels = userListDatagrid.datagrid("getSelections");
            var ids = [];
            for(var i in sels){
                ids.push(sels[i].userId);
            }
            ids = ids.join(",");
            return ids;
        }


        /*用户名*/
        $("#user_edit_name_textbox").textbox({
            width: 160,
            height: 25,
            required: true,
            prompt: '请输入你的昵称',
            type: 'text'
        });
        /*用户密码*/
        $("#user_edit_passworld_textbox").textbox({
            width: 160,
            height: 25,
            required: true,
            type: 'password'
        });
        /*确认密码*/
        $("#user_edit_repassword_textbox").textbox({
            width: 160,
            height: 25,
            type: 'password'
        });
        /*角色下拉框*/
        $("#user_edit_roles_combobox").combobox({
            width: 160,
            height: 25,
            editable: false,
            required: true,
            url: '/roles/rolesList',
            method: 'get',
            valueField: 'rolesId',
            textField: 'rolesName'
        });
        /*年龄*/
        $("#user_edit_age_numberbox").numberbox({
            min: 1,
            max: 120,
            width: 160,
            height: 25,
            precision: 0
        });
        /*用户地址*/
        $("#user_edit_address_textbox").textbox({
            width: 160,
            height: 25,
            prompt: '请输入你的地址',
            type: 'text'
        });

        /*用户新增表单提交*/
        $("#user_edit_form").form({
            url: '/user/userEdit',
            onSubmit: function () {
                /*两次密码不一致*/
                userAddRepasswordTextbox.bind('focusout',function () {
                    if(userAddPassworldTextbox.val()!=userAddRepasswordTextbox.val()){
                        userAddMessage.val("两次的密码不一致！");
                        return false;
                    }
                });


            },
            success: function (data) {
                console.info("data:"+data);
                /*更新验证码*/
                $('.check_code_show').click();
                var data = eval('(' + data + ')');
                if(data.success){
                    $.messager.show({
                        title:'提示',
                        msg:'提交成功！',
                        timeout:2000,
                        showType:'slide'
                    });
                    userAddReset.click();
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:'提交失败！',
                        timeout:2000,
                        showType:'slide'
                    });
                    userAddMessage.val(data.msg);
                }
            }
        });
        /*点击表单提交*/
        $("#user_edit_submit").bind('click',function () {
            userAddForm.submit();
        });
        /*点击表单关闭*/
        $("#user_edit_close").bind('click',function () {
            userEditWindow.window("close");
            userListDatagrid.datagrid("clearChecked");
            userListDatagrid.datagrid("clearSelections");
        });


    });
</script>
