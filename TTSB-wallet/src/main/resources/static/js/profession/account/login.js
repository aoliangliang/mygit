
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    
    /*
        Form validation
    */
//    $('.login-form input[type="text"], .login-form input[type="password"]').on('focus', function() {
//    	$(this).removeClass('input-error');
//    });
    
});

function login(){
//	let flag = true;
//	$('.login-form').find('input[type="text"], input[type="password"]').each(function(){
//		if( $(this).val() == "" ) {
//			e.preventDefault();
//			$(this).addClass('input-error');
//			flag = false;
//		}
//		else {
//			$(this).removeClass('input-error');
//		}
//	});
	var result = checkClick();
	if(result != false){
		loadid = xtip.load();
    	$.ajax({
			type:"post",
			url: urls.loginUrl,
			dataType: "json",  
			async:true,
			data: $("#loginForm").serialize(),
			success:function(result){
				xtip.close(loadid);
				if(result.result == true){
					window.location.href= urls.index;
				}else{
					xtip.alert('登录失败', 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
				}
			},
			error:function(data) {
				xtip.close(loadid);
			}
		});
	}
}
