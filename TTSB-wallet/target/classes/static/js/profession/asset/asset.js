var app = new Vue({
		  el: '#currency',
		  data: {
		    currencyList: [],
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
				  	let res = getCurrencyAsync(data,false);
				  	_this.assemResData(res);
			  },
			  searchCurrency() {
				  	var _this = this;
				  	var data = {
							"pageNum":_this.pageNum,
							"pageSize":_this.pageSize
				  	}
				  	let res = getCurrencyAsync(data,true);
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
	      			      let res = getCurrencyAsync(data,false);
	      			    _this.assemResData(res);
	                  }
	      		});
			  },
			  toTransfer(id) {
				  loadid = xtip.load();
				  $.ajax({
						type:"get",
						url: urls.beforeTransfer,
						dataType: "json",
						data:{
							"id":id
						},
						success:function(result){
							xtip.close(loadid);
							if(result.result == true){
								window.location.href=rootUrl + "/currency/toTransfer?id="+id;
							}else{
								xtip.alert(result.message, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
							}
						},
						error:function(data) {
							xtip.close(loadid);
						}
					});
			  },
			  detail(id) {
				  window.location.href=rootUrl + "/currency/assetDetail?id="+id;
			  },
			  assemResData(res) {
				  var _this = this;
				  if (res.result == true) {
					  _this.currencyList = res.data.list;
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

function getCurrencyAsync(data,flag){
	  let res;
	  $.ajax({
			type:"get",
			url: urls.findCurrencyByAccount,
			dataType: "json",  
			async:flag,
			data: data,
			success:function(result){
				res = result;
			},
		});
	  return res;
}

