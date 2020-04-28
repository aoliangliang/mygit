var app = new Vue({
		  el: '#txrecord',
		  data: {
		    txList: [],
		    pageNum: 1,
			pageSize: 5,
			pageTotal: 0
		  },
		  mounted(){
			  this.init();
			  if(this.pageTotal > 0){
				  this.page();
			  }
		  },
		  methods: {
			  init() {
				  var _this = this;
				  var data = {
							"pageNum":_this.pageNum,
							"pageSize":_this.pageSize
				  	}
				  	let res = getTxRecordAsync(data,false);
				  	_this.assemResData(res);
			  },
			  searchCurrency() {
				  	var _this = this;
				  	var data = {
							"pageNum":_this.pageNum,
							"pageSize":_this.pageSize
				  	}
				  	let res = getTxRecordAsync(data,true);
				  	_this.assemResData(res);
			  },
			  page: function(){
				  let _this = this;
				  $('#pageLimit').bootstrapPaginator({    
	      		    currentPage: _this.pageNum, 
	      		    totalPages: _this.pageTotal,
	      		    size:"normal",    
	      		    bootstrapMajorVersion: 3,    
	      		    alignment:"right",    
	      		    numberOfPages:4,    
	      		    itemTexts: function (type, page, current) {        
	      		        switch (type) {            
	      		        case "first": return "首页";            
	      		        case "prev": return "上一页";            
	      		        case "next": return "下一页";            
	      		        case "last": return "末页";            
	      		        case "page": return page;
	      		        }
	      		    },
	      		    onPageClicked: function (event, originalEvent, type, page) {
	      		    	let data = {
	      			    		"pageSize": _this.pageSize,
	      			    		"pageNum": page
	      			      };
	      			      let res = getTxRecordAsync(data,false);
	      			    _this.assemResData(res);
	                  }
	      		});
			  },
			  returnStatusName(status){
				  let statusName = "";
				  let className = "label-info";
				  switch(status){
					  case "0":
						  statusName = "身处队列";
						  break;
					  case "1":
						  statusName = "已经发送";
						  className = "label-warning";
						  break;
					  case "2":
						  statusName = "交易成功";
						  className = "label-success";
						  break;
					  case "3":
						  statusName = "无需发送";
						  className = "label-default";
						  break;
					  case "4":
						  statusName = "交易过期"
						  className = "label-danger";
				  }
				  return statusName;
			  },
			  returnStatusClass(status){
				  let className = "label-info";
				  switch(status){
					  case "0":
						  break;
					  case "1":
						  className = "label-warning";
						  break;
					  case "2":
						  className = "label-success";
						  break;
					  case "3":
						  className = "label-default";
						  break;
					  case "4":
						  className = "label-danger";
				  }
				  return className;
			  },
			  detail(id) {
				 window.location.href=rootUrl+"/bsTx/detail?id="+id;
			  },
			  assemResData(res) {
				  var _this = this;
				  if (res.result == true) {
					  _this.txList = res.data.list;
					  _this.pageNum = res.data.pageNum;
					  _this.pageSize = res.data.pageSize;
					  _this.pageTotal = res.data.pages;
				  }else{
					  xtip.alert(message.search_fail, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
				  }
			  }
		  }
		})
		$(document).ready(function() {
			
		});

function getTxRecordAsync(data,flag){
	  let res;
	  $.ajax({
			type:"get",
			url: urls.txRecordPage,
			dataType: "json",  
			async:flag,
			data: data,
			success:function(result){
				res = result;
			},
		});
	  return res;
}

