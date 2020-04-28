function isArray(array){
		if(array != undefined && array != null && $.isArray(array) && array.length > 0){
			return true;
		}
		return false;
	
}

function strIsNull(str){
	if(str == '' || str == null || str == undefined){
		return true;
	}
	return false;
	
}

function isString(str){
	if(str && typeof(str) == 'string'){
		return true;
	}
	return false;
}

function formatTime(time){
	  let a = String(time);
	  if(a.length === 10){
		  return moment.unix(time).format("YYYY-MM-DD HH:mm:ss");
	  }else{
          	return moment(time).format("YYYY-MM-DD HH:mm:ss");
	  }
  }

function formatDate(time){
	  let a = String(time);
	  if(a.length === 10){
		  return moment.unix(time).format("YYYY-MM-DD");
	  }else{
        	return moment(time).format("YYYY-MM-DD");
	  }
}

function formatTimeYMD(time){
	let a = String(time);
	  if(a.length === 10){
		  return moment.unix(time).format("YY/MM/DD");
	  }else{
	          	return moment(time).format("YY/MM/DD");
	  }
  }

function formatTimeHour(time){
	let a = String(time);
	  if(a.length === 10){
		  return moment.unix(time).format("HH:mm");
	  }else{
	          	return moment(time).format("HH:mm");
	  }
  }

function formatTimeMin(time){
	let a = String(time);
	  if(a.length === 10){
		  return moment.unix(time).format("mm:ss");
	  }else{
	          	return moment(time).format("mm:ss");
	  }
} 