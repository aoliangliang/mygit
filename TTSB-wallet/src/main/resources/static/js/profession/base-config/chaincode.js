var app = new Vue({
		el: "#chaincode-main",
		data: {
			chaincodeList: [],
			pageNum: 1,
			pageSize: 10,
			pageTotal: 0,
		},
		mounted () {
			this.getChaincodeList();
			this.page();
		},
	    methods: {
	    	getChaincodeList () {
		      let _this = this;	
		      let data = {
		    		"chaincode": {},
		    		"pageSize": _this.pageSize,
		    		"pageNum": _this.pageNum
		      };
		      let res = pageByChaincode(data);
		      if (res.result === true) {
		    	  _this.chaincodeList = res.data.list;
		    	  _this.pageTotal = res.data.total;
		    	  _this.pageNum = res.data.pageNum;
		    	  _this.pageSize = res.data.pageSize;
		      }else{
		    	  xtip.alert(message.search_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 3});
		      }
		      console.log(_this.chaincodeList);
		    },
		    page: function(){
				  let _this = this;
				  $('#pageLimit').bootstrapPaginator({    
	      		    currentPage: _this.pageNum , 
	      		    totalPages: _this.totalPages,
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
	      			    		"chaincode": {},
	      			    		"pageSize": _this.pageSize,
	      			    		"pageNum": page
	      			      };
	      			      let res = pageByChaincode(data);
	      			      if (res.result === true) {
	      			    	  _this.chaincodeList = res.data.list;
	      			    	  _this.pageTotal = res.data.total;
	      			    	  _this.pageNum = res.data.pageNum;
	      			    	  _this.pageSize = res.data.pageSize;
	      			      }else{
	      			    	  xtip.alert(message.search_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 3});
	      			      }
	                  }
	      		});
			  }
	    } 
	});