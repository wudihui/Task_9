<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/18
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta charset="UTF-8">
<title>注册/登录</title>
<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
    function sendCode(obj){
        var phone = document.getElementById("phone");
        var value = phone.value.trim();
        if(value && value.length == 11){
            $.ajax({
                cache : false,
                url : "sendCode.html",
                data : {phone : value}
            });
            // 1分钟内禁止点击
            for (var i = 1; i <= 60; i++) {
                // 1秒后显示
                window.setTimeout("updateTime(" + (60 - i) + ")", i * 1000);
            }
        }else{
            alert("请输入正确的手机号码");
            phone.focus();

        }
    }

    function updateTime(i){
        // setTimeout传多个参数到function有点麻烦，只能重新获取对象
        var obj = document.getElementById("validationCode");
        if(i > 0){
            obj.innerHTML  = "距离下次获取还有" + i + "秒";
            obj.disabled = true;
        }else{
            obj.innerHTML = "获取验证码";
            obj.disabled = false;
        }
    }

    function checkTime(){
        var sendCodeTime = <%=(Long)session.getAttribute("SEND_CODE_TIME")%>;
        if(sendCodeTime){
            var nowTime = new Date().getTime();
            var flag = Math.floor((nowTime - sendCodeTime)/1000);
            if(flag < 60){
                var end = 60 - flag;
                // 进页面马上开始，选i为0
                for (var i = 0; i <= end; i++) {
                    window.setTimeout("updateTime(" + (end - i) + ")", i * 1000);
                }
            }
        }
    }

    function doValidation(){
        if(validateFormValidateor.form()){
            $("#validateForm").ajaxSubmit({
                success:function(data){
                    if(data == "success") {
                        alert("验证成功");
                    }else{
                        alert("验证失败");
                    }
                }
            });
        }
    }

    var validateFormValidateor;
    $().ready(function(){
        validateFormValidateor = $("#validateForm").validate({
            rules:{
                phone:{
                    required:true,
                    number:true,
                    minlength:11
                },
                code:"required"
            },
            messages:{
                phone:{
                    required: "请输入手机号码",
                    number: "只能输入数字",
                    minlength: "手机号码为11位"
                }
            }
        });
    });
</script>

