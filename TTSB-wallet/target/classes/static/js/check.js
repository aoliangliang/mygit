$('input[name="username"]').blur(checkEmail);
$('input[name="password"]').blur(checkWord);
$('input[name="repassword"]').blur(checkWord2);
$('input[name="name"]').blur(checkNull);
$('input[name="phone"]').blur(checkNull);
function checkClick() {
    var closePop=$('.prompt').fadeOut(200);
    // 邮箱校验
    var mailcheck=/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/;
    // 密码校验
    var passwordcheck=/^[a-zA-Z0-9]{6,18}$/;
    
    var tail=setTimeout(() => {
        $('.prompt').fadeOut(500);
    }, 4000);
    if($('input[name="username"]').length>0){
        if($('input[name="username"]').val()==""||$('input[name="username"]').val()==undefined){
            $('input[name="username"]').parent().find('.prompt').fadeIn(500);
            $('input[name="username"]').parent().find('.prompt').text('请填写用户名！');
            return false;
        }
        else if(!mailcheck.test($('input[name="username"]').val())){
            $('input[name="username"]').parent().find('.prompt').fadeIn(500);
            $('input[name="username"]').parent().find('.prompt').text('用户名必须为正确的邮箱！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
    if($('input[name="password"]').length>0){
        if($('input[name="password"]').val()==""||$('input[name="password"]').val()==undefined){
            $('input[name="password"]').parent().find('.prompt').fadeIn(500);
            $('input[name="password"]').parent().find('.prompt').text('请填写密码！');
            return false;
        }
        else if(!passwordcheck.test($('input[name="password"]').val())){
            $('input[name="password"]').parent().find('.prompt').fadeIn(500);
            $('input[name="password"]').parent().find('.prompt').text('密码必须为6-18位的数字或字母！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
    if($('input[name="repassword"]').length>0){
        if($('input[name="password"]').val()!=$('input[name="repassword"]').val()){
            $('input[name="repassword"]').parent().find('.prompt').fadeIn(500);
            $('input[name="repassword"]').parent().find('.prompt').text('请确认两次秘密是否一致！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
    if($('input[name="name"]').length>0){
        if($('input[name="name"]').val()==""||$('input[name="name"]').val()==undefined){
            $('input[name="name"]').parent().find('.prompt').fadeIn(500);
            $('input[name="name"]').parent().find('.prompt').text('请填写姓名！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
    if($('input[name="phone"]').length>0){
        if($('input[name="phone"]').val()==""||$('input[name="phone"]').val()==undefined){
            $('input[name="phone"]').parent().find('.prompt').fadeIn(500);
            $('input[name="phone"]').parent().find('.prompt').text('请填写手机号码！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
}
// 邮箱账号校验
function checkEmail() {
    var closePop=$('.prompt').fadeOut(200);
    // 邮箱校验
    var mailcheck=/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/;
    var tail=setTimeout(() => {
        $('.prompt').fadeOut(500);
    }, 4000);
    if($('input[name="username"]').length>0){
        if($('input[name="username"]').val()==""||$('input[name="username"]').val()==undefined){
            $('input[name="username"]').parent().find('.prompt').fadeIn(500);
            $('input[name="username"]').parent().find('.prompt').text('请填写用户名！');
            return false;
        }
        else if(!mailcheck.test($('input[name="username"]').val())){
            $('input[name="username"]').parent().find('.prompt').fadeIn(500);
            $('input[name="username"]').parent().find('.prompt').text('用户名必须为正确的邮箱！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
}
// 数字密码校验
function checkWord() {
    var closePop=$('.prompt').fadeOut(200);
    var passwordcheck=/^[a-zA-Z0-9]{6,18}$/;
    var tail=setTimeout(() => {
        $('.prompt').fadeOut(500);
    }, 4000);
    if($('input[name="password"]').length>0){
        if($('input[name="password"]').val()==""||$('input[name="password"]').val()==undefined){
            $('input[name="password"]').parent().find('.prompt').fadeIn(500);
            $('input[name="password"]').parent().find('.prompt').text('请填写密码！');
            return false;
        }
        else if(!passwordcheck.test($('input[name="password"]').val())){
            $('input[name="password"]').parent().find('.prompt').fadeIn(500);
            $('input[name="password"]').parent().find('.prompt').text('密码必须为6-18位的数字或字母！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
}
// 重复密码校验
function checkWord2() {
    var closePop=$('.prompt').fadeOut(200);
    var tail=setTimeout(() => {
        $('.prompt').fadeOut(500);
    }, 4000);
    if($('input[name="repassword"]').length>0){
        if($('input[name="password"]').val()!=$('input[name="repassword"]').val()){
            $('input[name="repassword"]').parent().find('.prompt').fadeIn(500);
            $('input[name="repassword"]').parent().find('.prompt').text('请确认两次密码是否一致！');
            return false;
        }
        else{
            closePop;
        }
        tail;
    }
}

function checkNull() {
    var closePop=$('.prompt').fadeOut(200);
    var tail=setTimeout(() => {
        $('.prompt').fadeOut(500);
    }, 4000);
    if(this.name=="name"){
        if($('input[name="name"]').val()==""||$('input[name="name"]').val()==undefined){
            $('input[name="name"]').parent().find('.prompt').fadeIn(500);
            $('input[name="name"]').parent().find('.prompt').text('请填写名称！');
            return false;
        }
        else{
            closePop;
        }
    }
    else if(this.name=="phone"){
        if($('input[name="phone"]').val()==""||$('input[name="phone"]').val()==undefined){
            $('input[name="phone"]').parent().find('.prompt').fadeIn(500);
            $('input[name="phone"]').parent().find('.prompt').text('请填写手机号！');
            return false;
        }
        else{
            closePop;
        }
    }
        tail;
}