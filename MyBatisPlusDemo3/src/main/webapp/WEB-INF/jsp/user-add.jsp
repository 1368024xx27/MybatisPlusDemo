<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/26
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--css样式--%>
<link rel="stylesheet" href="css/system.css">
<style>

</style>

<div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'center',fit:true,border:false" style="overflow: hidden">
        <form id="user_add_form" method="post">
            <div class="row_box">
                <strong class="row_title">用户名：</strong>
                <div class="row_content">
                    <input id="user_add_name_textbox" class="easyui-validatebox tal" name="userName"/>
                    <span id="user_add_name_message" class="message"></span>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">用户密码：</strong>
                <div class="row_content">
                    <input id="user_add_password_passwordbox" class="easyui-validatebox tal" name="password"/>
                    <div id="user_add_password_message" class="message"></div>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">确认密码：</strong>
                <div class="row_content">
                    <input id="user_add_repassword_passwordbox" class="easyui-validatebox tal" name="repassword"/>
                    <div id="user_add_repassword_message" class="message"></div>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">角色：</strong>
                <div class="row_content">
                    <select id="user_add_roles_combotree" class="easyui-validatebox tal" name="rolesId"></select>
                    <div id="user_add_roles_message" class="message"></div>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">年龄：</strong>
                <div class="row_content">
                    <input type="text" id="user_add_age_numberbox" class="tal" name="userAge">
                    <div id="user_add_age_message" class="message"></div>
                </div>
            </div>
            <div class="row_box">
                <strong class="row_title">地址：</strong>
                <div class="row_content">
                    <input id="user_add_address_textbox" class="tal" name="userAddress">
                    <div id="user_add_address_message" class="message"></div>
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
                    <div id="check_code_message" class="message"></div>
                </div>
            </div>
            <%--提示信息的内容框--%>
            <div id="user_add_message" style="width:160px;height:25px;color:red"></div>
            <%--按钮--%>
            <div style="width: 250px;height: 25px">
                <a id="user_add_submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                   style="margin: auto 10px;float: right">提交</a>
                <a id="user_add_reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no'"
                   style="margin: auto 10px;float: right">重置</a>
            </div>
        </form>
    </div>
</div>

<script>
    $(function () {
        var userAddNameTextbox = $("#user_add_name_textbox");
        var userAddPasswordPasswordbox = $("#user_add_password_passwordbox");
        var userAddRepasswordPasswordbox = $("#user_add_repassword_passwordbox");
        var userAddRolesCombotree = $("#user_add_roles_combotree");
        var userAddAgeNumberbox = $("#user_add_age_numberbox");
        var userAddAddressTextbox = $("#user_add_address_textbox");
        var userAddForm = $("#user_add_form");
        var userAddSubmit = $("#user_add_submit");
        var userAddReset = $("#user_add_reset");
        var userAddNameMessage = $("#user_add_name_message");
        var userAddPasswordMessage = $("#user_add_password_message");
        var userAddRepasswordMessage = $("#user_add_repassword_message");
        var userAddRolesMessage = $("#user_add_roles_message");
        var userAddAgeMessage = $("#user_add_age_message");
        var userAddAddressMessage = $("#user_add_address_message");
        var userAddMessage = $("#user_add_message");


        /*用户名*/
        userAddNameTextbox.textbox({
            width: 160,
            height: 25,
            required: true,
            prompt: '请输入你的昵称',
            type: 'text'
        });
        userAddNameTextbox.textbox('textbox').bind({ focusout: function () {
                console.info("输入的用户名为："+userAddNameTextbox.textbox('getValue'));
                var uname = userAddNameTextbox.textbox('getValue');
            if(uname!=''){
                if(remote['http://localhost:8080/user/hasUser','username']){
                    userAddNameMessage.html('<img class="icon-ok" style="color: green"></img>');
                    return true;
                }else{
                    $("#user_add_name_message").val("用户已存在！");
                }
            }else{
                $("#user_add_name_message").val("用户名不能为空！");
            }
            return false;
        }});

       /*用户密码*/
      userAddPasswordPasswordbox.passwordbox({
            width: 160,
            height: 25,
            required: true,
            inputEvents: $.extend({}, $.fn.passwordbox.defaults.inputEvents, {
                keypress: function(e){
                    var char = String.fromCharCode(e.which);
                    $('#viewer').html(char).fadeIn(200, function(){
                        $(this).fadeOut();
                    });
                }
            })
        });
        /*确认密码*/
        userAddRepasswordPasswordbox.passwordbox({
            width: 160,
            height: 25,
            required: true,
            inputEvents: $.extend({}, $.fn.passwordbox.defaults.inputEvents, {
                keypress: function(e){
                    var char = String.fromCharCode(e.which);
                    $('#viewer').html(char).fadeIn(200, function(){
                        $(this).fadeOut();
                    });
                }
            })
        });
        /*角色下拉框*/
        userAddRolesCombotree.combotree({
            width: 160,
            height: 25,
            editable: false,
            required: true,
            url: 'roles/rolesList',
            method: 'get',
            valueField: 'rolesId',
            textField: 'rolesName'
        });
        /*年龄*/
        userAddAgeNumberbox.numberbox({
            min: 1,
            max: 120,
            width: 160,
            height: 25,
            precision: 0
        });
        /*用户地址*/
        userAddAddressTextbox.textbox({
            width: 160,
            height: 25,
            prompt: '请输入你的地址',
            type: 'text'
        });

        userAddNameTextbox.click(function () {
            alert( "点击");
        })


        /*用户新增表单提交*/
        userAddForm.form({
            url: '/user/userAdd',

            onSubmit: function () {
                /*两次密码不一致*/
                userAddRepasswordPasswordbox.bind('focusout',function () {
                    if(userAddPasswordPasswordbox.val()!=userAddRepasswordPasswordbox.val()){
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
        userAddSubmit.bind('click',function () {
            userAddForm.submit();
        });
        /*点击重置按钮*/
        userAddReset.bind('click',function () {
            /*userAddForm.find("input").val("");*/
            userAddForm.form('clear');
            userAddMessage.val('');
        });

    });
</script>



























