var app = new Vue({
		el: "#orderer-main",
		data: {
			file: null,
			fileName: '',
			ordererList: [],
			form: {
				id: '',
		        name: '',
		        isTls: false,
		        url: 'grpc://',
		        tlsUrl: 'grpcs://',
		        tlscacertsPath: '',
		        grpcoptionsStno: ''
			},
			pageNum: 1,
			pageSize: 10,
			pageTotal: 0,
		},
		mounted () {
			this.getOrdererList();
			this.page();
		},
	    methods: {
		    getOrdererList () {
		      let _this = this;	
		      let data = {
		    		"orderer": {},
		    		"pageSize": _this.pageSize,
		    		"pageNum": _this.pageNum
		      };
		      let res = pageByOrderer(data);
		      if (res.result === true) {
		    	  _this.ordererList = res.data.list;
		    	  _this.pageTotal = res.data.total;
		    	  _this.pageNum = res.data.pageNum;
		    	  _this.pageSize = res.data.pageSize;
		      }else{
		    	  xtip.alert(message.search_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 3});
		      }
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
	      			    		"orderer": {},
	      			    		"pageSize": _this.pageSize,
	      			    		"pageNum": page
	      			      };
	      			      let res = pageByOrderer(data);
	      			      if (res.result === true) {
	      			    	  _this.ordererList = res.data.list;
	      			    	  _this.pageTotal = res.data.total;
	      			    	  _this.pageNum = res.data.pageNum;
	      			    	  _this.pageSize = res.data.pageSize;
	      			      }else{
	      			    	  xtip.alert(message.search_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 3});
	      			      }
	                  }
	      		});
			  },
		    handleSubmit () {
    	      let _this = this;
		      let res = saveOrderer(this.form);
		      if(res.result == true){
		    	  xtip.alert(message.save_success, 's', {shade:false, title: message.query_reminder, btn:"close", times: 1}); 
		    	  $("#modal").modal('hide');
		    	  _this.getOrdererList();
		      }else{
		    	  xtip.alert(message.save_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 2}); 
		      }
		    },
		    handleReset () {
		      this.form = {
	    		    id: '',
			        name: '',
			        isTls: false,
			        url: 'grpc://',
			        tlsUrl: 'grpcs://',
			        tlscacertsPath: '',
			        grpcoptionsStno: ''
		      };
		      this.fileName = '';
		      $('#file').value = '';
		    },
		    edit (row) {
	    	  this.form = row;
	    	  console.log(this.form);
		      $("#modal").modal('show');
		    },
		    fileImport () {
		      let _this = this;
		      let file = $('#file').files[0];
		      let reader = new FileReader();
		      reader.readAsText(file);
		      reader.onload = function () {
		        _this.form.tlscacertsPath = reader.result;
		        _this.fileName = file.name;
		      }
		    }
	    } 
	});