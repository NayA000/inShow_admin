$(function(){
    //提交表单
    $('#submit_btn').click(function(){
        var l_index = layer.load('登录中...');
        if($('#account').val() == ''){
            layer.msg('请输入账号！',1);	
            $('#account').focus();
        }else if($('#password').val() == ''){
            layer.msg('请输入密码！',1);
            $('#password').focus();
        }else if($('#verifyDiv').css('display') != 'none' && $('#j_captcha').val() == ''){
            layer.msg('请输入验证码！',1);
            $('#j_captcha').focus();
        }else{
            //ajax提交表单，#login_form为表单的ID。 如：$('#login_form').ajaxSubmit(function(data) { ... });
        	console.log("----------------")
            $.ajax({
                url:"http://localhost:8080/adminLogin",
                type:"post",
                data: {
                    username :$("#account").val(),
                    password :$("#password").val(),
                    //"verifycode":$("#j_captcha").val()
                },
                dataType:"json",
                complete:function(){
					layer.close(l_index);
				},
				success:function(result){
					if(result.status == 200){//登录成功
						layer.msg('登录成功  正在为您跳转...',3,10);
						//layer.alert("----");
						
						window.location.href = "http://localhost:8080/inShowAdmin";
					}else{//登录失败
						
						layer.alert(result.msg);
						//isNeedVerify();
					}
				},
				error:function(){
					layer.alert("登录超时，请稍后重试！");
				}
			});
			
		}
	});
});