$.ajaxSetup({
	contentType:"application/x-www-form-urlencoded;charset=utf-8",
	complete: function(XMLHttpRequest, textStatus){
		let res = XMLHttpRequest.responseText;
		let jsonData = JSON.parse(res);
		if(jsonData.result == false){
			if(jsonData.code == "401"){
				 xtip.alert(message.outTime_tip, 'e', {shade:false, title: message.login_outTime, btn:"close", times: 2});
		    	 setTimeout(function(){
		    		 window.location.href = urls.login;
		    	 }, 2000);
			}else{
				 xtip.alert(jsonData.message, 'e', {shade:false, title: message.error_tip, btn:"close", times: 2});
	        }
		}
	},
	statusCode: {
		 401: function() {
			 xtip.alert(message.outTime_tip, 'e', {shade:false, title: message.login_outTime, btn:"close", times: 2});
	    	 setTimeout(function(){
	    		 window.location.href = urls.login;
	    	 }, 2000);
	     },
	     403: function() {
	    	 xtip.alert(message.access_tip, 'e', {shade:false, title: message.access_demined, btn:"close", times: 2});
	     },
	     404: function() {
	    	 window.location.href = urls.pageNotExist;
	     },
	     500: function(){
	    	 window.location.href = urls.accessError;
	     }
	   }
});