var app = new Vue({
		el: "#orderer-main",
		data: {
			file: null,
			fileName: '',
			peerList: [],
			orgList: [],
			form: {
				id: '',
		        name: '',
		        isTls: false,
		        url: 'grpc://',
		        tlsUrl: 'grpcs://',
		        tlscacertsPath: '',
		        grpcoptionsStno: '',
		        eventUrl: '',
		        orgId: ''
			},
			pageNum: 1,
			pageSize: 10,
			pageTotal: 0,
		},
		mounted () {
			this.getPeerList();
			this.page();
			this.getAllOrg();
		},
	    methods: {
		    getPeerList () {
		      let _this = this;	
		      let data = {
		    		"peer": {},
		    		"pageSize": _this.pageSize,
		    		"pageNum": _this.pageNum
		      };
		      let res = pageByPeer(data);
		      if (res.result === true) {
		    	  _this.peerList = res.data.list;
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
	      			    		"peer": {},
	      			    		"pageSize": _this.pageSize,
	      			    		"pageNum": page
	      			      };
	      			      let res = pageByPeer(data);
	      			      if (res.result === true) {
	      			    	  _this.peerList = res.data.list;
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
		      let res = savePeer(this.form);
		      if(res.result == true){
		    	  xtip.alert(message.save_success, 's', {shade:false, title: message.query_reminder, btn:"close", times: 1}); 
		    	  $("#modal").modal('hide');
		    	  _this.getPeerList();
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
			        grpcoptionsStno: '',
			        eventUrl: '',
			        orgId: ''
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
		    },
		    getAllOrg () {
		    	let _this = this;
		    	let res = findAllOrg();
		    	if(res.result == true){
		    		_this.orgList = res.data;
		    	}
		    	console.log(_this.orgList);
	        },
	        returnOrgName (orgId) {
	            let name = '';
	            let _this = this;
	            for (var org in _this.orgList) {
	              if (_this.orgList[org].id === orgId) {
	                name = _this.orgList[org].name;
	              }
	            }
	            return name;
            }
	    } 
	});