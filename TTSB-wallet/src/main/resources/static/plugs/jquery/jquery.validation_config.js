$(function() {
   
  // set min/max date ---------------------
  var dtToday = new Date();
  var month = dtToday.getMonth() + 1; // jan=0; feb=1 .......
  var day = dtToday.getDate();
  var yearMin = dtToday.getFullYear() - 130;
  var yearMax = dtToday.getFullYear() - 0;
  if(month < 10)
    month = '0' + month.toString();
  if(day < 10)
    day = '0' + day.toString();
  var minDate = yearMin + '-' + month + '-' + day;
  var maxDate = yearMax + '-' + month + '-' + day;
  //for BirthDay

  // set validation timing ---------------------
  $('form input, form select, form textarea').click(function() {
    $(this).valid();
  }); 
  
  //default messages ---------------------
  $.extend($.validator.messages, {
    required: "必填项",
    remote: "请更正此字段",
    email: "请输入有效的电子邮件地址",
    url: "请输入有效的网址",
    date: "请输入有效日期",
    dateISO: "请输入有效日期（ISO）",
    number: "请输入一个有效的数字",
    digits: "仅限输入数字",
    creditcard: "请输入有效的信用卡号",
    equalTo: "请再次输入相同的值",
    extension: "请输入包含有效扩展名的值",
    maxlength: $.validator.format("请输入不超过{0}字符"),
    minlength: $.validator.format("请至少输入{0} 字符"),
    rangelength: $.validator.format("请输入介于{0}个字符和{1}个字符之间的值"),
    range: $.validator.format("请输入介于{0}和{1}之间的值"),
    step: $.validator.format("请输入{0}的倍数"),
    max: $.validator.format("请输入一个小于{0}的值"),
    min: $.validator.format("请输入一个大于{0}的值"),
  });
 
  // call validate.js ---------------------
  $("form").validate({
    errorElement: "em",
    ignore: [],
    errorClass: 'invalid',
    errorPlacement: function(label, element) {
      if (element.is(':radio, :checkbox')) {
        label.appendTo(element.parent().parent('div'));
      }
      else {
        label.insertAfter(element.parent("div"));
      }
    },
    focusInvalid: false,
    invalidHandler: function(form, validator) {
      var headerHight = 90;
      if (!validator.numberOfInvalids())
        return;
      $('html, body').animate({
        scrollTop: $(validator.errorList[0].element).offset().top-headerHight
      }, 800);
    },
    rules: {
    },
    messages: {
    }
  });
  
  // check date format ---------------------
  $.validator.addMethod("dateCheck", function(value, element) {
    r = value.match(/^(\d{4})[\/\-](\d{1,2})[\/\-](\d{1,2})$/);
    if(!r) { return false; }
    if(r[2] < 1 || r[2] > 12 || r[3] < 1) { return false; }
    if(r[2] == 2) { return r[3] <= (r[1] % 4 == 0 && r[1] % 100 != 0 || r[1] % 400 == 0 ? 29 : 28); }
    return r[3] <= (r[2] == 4 || r[2] == 6 || r[2] == 9 || r[2] == 11 ? 30 : 31);
  });
  
  // check mynumber ---------------------
  $.validator.addMethod("myNumCheck", function(value, element) {
    return /^\d{12}$/.test(value);
  });
  
  // check zip code format ---------------------
  $.validator.addMethod("zipCheck", function(value, element) {
    return /^\d{7}$/.test(value);
  });
  
  // Decimal point and Numbers ---------------------
   $.validator.addMethod("dnCheck", function(value, element) {
     return /^[0-9]+(\.[0-9]+)?$/.test(value);  
   });
  
  // check min age ---------------------
  $.validator.addMethod("minAge", function(value, element) {
    if(this.optional(element)) {
      return true;
    }
    var dateOfBirth = value;
    var arr_dateText = dateOfBirth.split("-");
    year = arr_dateText[0];
    month = arr_dateText[1];
    day = arr_dateText[2];
    var mydate = new Date();
    mydate.setFullYear(year, month - 1, day);
    var maxDate = new Date();
    maxDate.setYear(maxDate.getYear() - 20);
    if(maxDate < mydate) {
      return false;
    }
    return true;
  }, '二十歳以上の方が対象です');
  
  
  // input experenices year edit
  $.validator.addMethod("yearEdit", function(value, element) {
	 /* var _checkbox = $(this).parent().parent().find('input[name="investmentExperienceEdit"]').eq(0);*/
	  if(!this.optional(element)) {
	      return true;
	    }
	});
  
//check positive Integer---------------------
  $.validator.addMethod("positiveInteger", function(value, element) {
    return /^[1-9]\d*$/.test(value);
  });
  
});
