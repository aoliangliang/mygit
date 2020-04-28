
jQuery(document).ready(function() {
	
//	$("#registerForm").validate({
//        rules: {
//            username: {
//                required: true,
//                email: true
//            },
//            password: {
//                required: true,
//                minlength: 6,
//                maxlength: 18
//            },
//            repassword: {
//                required: true,
//                minlength: 6,
//                maxlength: 18,
//                equalTo: "#password"
//            },
//        },
//        messages: {
//            username: {
//                required: "请输入用户名",
//                email: "用户名必须为邮箱"
//            },
//            password: {
//                required: "请输入密码",
//                minlength: "密码长度不能小于 6 个字符"，
//                maxlength: "密码长度不能大于 18 个字符"
//            },
//            repassword: {
//                required: "请确认密码",
//                minlength: "密码长度不能小于 6 个字符"，
//                maxlength: "密码长度不能大于 18 个字符"
//                equalTo: "两次密码输入不一致"
//            }
//        },
//        submitHandler: function(form) {
////            $(form).ajaxSubmit(); //用Jquery Form的函数
//        	loadid = xtip.load();
//        	$.ajax({
//    			type:"post",
//    			url: urls.register,
//    			dataType: "json",  
//    			async:false,
//    			data: $("#registerForm").serialize(),
//    			success:function(result){
//    				xtip.close(loadid);
//    				if(result.result == true){
//    					window.location.href= urls.login;
//    				}
//    			},
//    			error:function(data) {
//    				xtip.close(loadid);
//    			}
//    		});
//        }
//    });
	
    /*
        Fullscreen background
    */
    
    /*
        Form validation
    */
//    $('.register-form input[type="email"], .register-form input[type="password"]').on('focus', function() {
//    	$(this).removeClass('input-error');
//    });
    
});

function register(){
//	var username = $("#username").val();
//	var password = $("#password").val();
//	var repassword = $("#repassword").val();
//	var name = $("#name").val();
//	var phone = $("#phone").val();
//	if(username == null || username == ''){
//		xtip.msg("用户名不能为空", {icon:'e',type:'w'});
//		return;
//	}
//	if(password == null || password == ''){
//		xtip.msg("密码不能为空", {icon:'e',type:'w'});
//		return;
//	}
//	if(repassword == null || repassword == ''){
//		xtip.msg("确认密码不能为空", {icon:'e',type:'w'});
//		return;
//	}
//	if(password != repassword){
//		xtip.msg("两次输入密码不一致",{icon:'e',type:'w'});
//		return;
//	}
//	if(name == null || name == ''){
//		xtip.msg("姓名不能为空",{icon:'e',type:'w'});
//		return;
//	}
//	if(phone == null || phone == ''){
//		xtip.msg("手机号码不能为空",{icon:'e',type:'w'});
//		return;
//	}
	var result = checkClick();
	if(result != false){
		loadid = xtip.load();
		$.ajax({
			type:"post",
			url: urls.register,
			dataType: "json",  
//			async:false,
			data: $("#registerForm").serialize(),
			success:function(result){
				xtip.close(loadid);
				if(result.result == true){
					window.location.href= urls.loginPage;
				}else{
					xtip.alert(result.message, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
				}
			},
			error:function(data) {
				xtip.close(loadid);
			}
		});
	}
	
}
