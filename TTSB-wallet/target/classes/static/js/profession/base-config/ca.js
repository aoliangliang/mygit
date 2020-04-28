var app = new Vue({
		el: "#ca-main",
		data: {
			file: null,
			fileName: '',
			caList: [],
			orgList: [],
			form: {
				id: '',
		        name: '',
		        url: 'https://',
		        httpoptionsVerify: false,
		        tlscacertsPath: '',
		        registrarEnrollid: '',
		        registrarEnrollsecret: '',
		        orgId: ''
			},
			pageNum: 1,
			pageSize: 10,
			pageTotal: 0,
		},
		mounted () {
			this.getCaList();
			this.page();
		    this.getAllOrg();
		},
	    methods: {
		    getCaList () {
		      let _this = this;	
		      let data = {
		    		"ca": {},
		    		"pageSize": _this.pageSize,
		    		"pageNum": _this.pageNum
		      };
		      let res = pageByCa(data);
		      if (res.result === true) {
		    	  _this.caList = res.data.list;
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
	      			    		"ca": {},
	      			    		"pageSize": _this.pageSize,
	      			    		"pageNum": page
	      			      };
	      			      let res = pageByCa(data);
	      			      if (res.result === true) {
	      			    	  _this.caList = res.data.list;
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
		      let res = saveCa(this.form);
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
			        url: 'https://',
			        httpoptionsVerify: false,
			        tlscacertsPath: '',
			        registrarEnrollid: '',
			        registrarEnrollsecret: '',
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