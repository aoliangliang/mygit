// 导航点击切换
function switchNav() {
    if($(window).width()>768){
        if($('.left-nav').width()==0){
            $('.left-nav').css('width','26%')
            $('.right-side').css('width','74%')
        }
        else{
            $('.left-nav').css('width','0')
            $('.right-side').css('width','100%')
        }
    }
    else{
        document.body.style.overflow='auto'
        $('.nav-coat-phone').css('display','none')
            $('.left-nav').css('width','100%')
            $('.right-side').css('width','0%')
    }
}
// 手机导航切换
function phoneSwitchNav() {
    $('.left-nav').css('width','0')
    $('.right-side').css('width','100%')
}
// 手机导航列表
function switchPhoneNav(){
    if($('.nav-coat-phone').css('display')=='block'){
    	$('html').css('overflow','auto');
        $('.nav-coat-phone').fadeOut(100).slideUp(300);
    }
    else{
       	$('html').css('overflow','hidden');
        $('.nav-coat-phone').fadeIn(100).slideDown(300);
    }
}
// 打开搜索
function searchSwitch(){
    document.body.style.overflow='hidden'
    if ($('.mask').hasClass('maskUp')) {
        $('.mask').removeClass('maskUp')
        $('.search-coat').removeClass('search-coat-up')
    }
    else{
        $('.mask').addClass('maskUp')
        $('.search-coat').addClass('search-coat-up')
    }
}
// 关闭遮罩层
function closeMask(){
    document.body.style.overflow='auto'
    $('.mask').removeClass('maskUp')
    $('.search-coat').removeClass('search-coat-up')
}
// 筛选切换
function filterSwitch(){
    if($('.tasks-filter2').css('display')=='block'){
        $('.tasks-filter2').css('display','none')
        $('.tasks-filter').css('opacity','0')
    }
    if($('.tasks-filter').css('display')=='block'){
        $('.tasks-filter').css('display','none')
        $('.tasks-filter2').css('opacity','0')
    }
    else{
        $('.tasks-filter').css('display','block');
        $('.tasks-filter').css('opacity','1')
    }
    
}
// 筛选切换2
function filterSwitch2(){
    if($('.tasks-filter').css('display')=='block'){
        $('.tasks-filter').css('display','none')
        $('.tasks-filter').css('opacity','0')
    }
    if($('.tasks-filter2').css('display')=='block'){
        $('.tasks-filter2').css('display','none')
        $('.tasks-filter2').css('opacity','0')
    }
    else{
        $('.tasks-filter2').css('display','block');
        $('.tasks-filter2').css('opacity','1')
    }
}
// 筛选切换2
// function filterSwitch3(){
//     if($(this).css('display')=='block'){
//         $(this).css('display','none')
//         $(this).css('opacity','0')
//     }
//     else{
//         $(this).css('display','block');
//         $(this).css('opacity','1')
//     }
// }
$(function(){
	var linkOne=window.location.href.substring(window.location.href.lastIndexOf("\/") + 1,window.location.href.length)
	if(linkOne=='index'||linkOne=='index#'){
		$(".option-block").removeClass('active');
		$(".option-block").eq(0).addClass('active')
	}
	else if(linkOne=='currency'){
		$(".option-block").removeClass('active');
		$(".option-block").eq(1).addClass('active')
	}
	else if(linkOne=='asset'){
		$(".option-block").removeClass('active');
		$(".option-block").eq(2).addClass('active')
	}
	else if(linkOne=='personal'){
		$(".option-block").removeClass('active');
		$(".option-block").eq(3).addClass('active')
	}
	else if(linkOne=='txrecord'){
		$(".option-block").removeClass('active');
		$(".option-block").eq(4).addClass('active')
	}
	else if(linkOne=='transfer'){
		$(".option-block").removeClass('active');
		$(".option-block").eq(3).addClass('active')
	}
	$('.back-right').click(function () {
		 window.history.go(-1);
    })
//	$("body").click(function(event){
//		if($(".tasks-filter3").css('display')=='block'){
//			$(".tasks-filter3").css('display','none');
//			$(".tasks-filter3").css('opacity','0');
//		}
//		$(".fa-ellipsis-h").click(function(){
//	        if($(this).find(".tasks-filter3").css('display')=='none'){
//	            $(this).find(".tasks-filter3").css('display','block');
//	            $(this).find(".tasks-filter3").css('opacity','1');
//	            var op=$(".tasks-filter3").not($(this).find(".tasks-filter3"))
//	            op.css('display','none');
//	            op.css('opacity','0');
//	        }
//	        else{
//	            $(this).find(".tasks-filter3").css('display','none');
//	            $(this).find(".tasks-filter3").css('opacity','0');
//	        }
//	    });
//	});
})