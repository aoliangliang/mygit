function postJson(url, requestData){
	let result = {};
	$.ajax({
	      url: url,
	      type: "post",
	      dataType: 'json',
	      contentType: "application/json;charset=utf-8",
          data: JSON.stringify(requestData),
          async:false,
	      success: function (res) {
	    	 result = res;
		  }, 
	      error: function(res){
	    	  result = res;
	      }
   });
	
   return result;	
}

function postNoParam(url){
	let result = {};
	$.ajax({
	      url: url,
	      type: "post",
	      dataType: 'json',
          async:false,
	      success: function (res) {
	    	 result = res;
		  }, 
	      error: function(res){
	    	  result = res;
	      }
   });
	
   return result;	
}

function postData(url, requestData){
	let result;
	$.ajax({
	      url: url,
	      type: "post",
	      dataType: 'json',
          data: requestData,
          async:false,
	      success: function (res) {
	    	 result = res;
		  }, 
	      error: function(res){
	    	  result = res;
	      }
   });
	
   return result;	
}


function getJson(url, requestData){
	let result;
	$.ajax({
	      url: url,
	      type: "get",
	      dataType: 'json',
	      async:false,
	      contentType: "application/json;charset=utf-8",
          data: JSON.stringify(requestData),
	      success: function (res) {
	    	 result = res;
		  }, 
	      error: function(res){
	    	  result = res;
	      }
   });
	
   return result;	
}

function getNoParam(url){
	let result;
	$.ajax({
	      url: url,
	      type: "get",
	      dataType: 'json',
	      async:false,
	      success: function (res) {
	    	 result = res;
		  }, 
	      error: function(res){
	    	  result = res;
	      }
   });
	
   return result;	
}

function getData(url, requestData){
	let result;
	$.ajax({
	      url: url,
	      type: "get",
	      dataType: 'json',
	      async:false,
          data: requestData,
	      success: function (res) {
	    		 result = res;
		  }, 
	      error: function(res){
	    	  result = res;
	      }
   });
	
   return result;	
}

