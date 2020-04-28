var app = new Vue({
		el: "#org-main",
		data: {
			username: sessionStorage.getItem("username"),
			avatar: sessionStorage.getItem("avatar"),
			file: null,
			adminFileName: '',
			signFileName: '',
			orgList: [],
			form: {
				id: '',
				name: '',
				mspid: '',
				adminprivatekeyPath: '',
				signedcertPath: ''
			},
			pageNum: 1,
			pageSize: 10,
			pageTotal: 0,
		},
		mounted () {
			this.getOrgList();
			this.page();
		},
	    methods: {
		    getOrgList () {
		      let _this = this;	
		      let data = {
		    		"org": {},
		    		"pageSize": _this.pageSize,
		    		"pageNum": _this.pageNum
		      };
		      let res = pageByOrg(data);
		      if (res.result === true) {
		    	  _this.orgList = res.data.list;
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
	      			    		"org": {},
	      			    		"pageSize": _this.pageSize,
	      			    		"pageNum": page
	      			      };
	      			      let res = pageByOrg(data);
	      			      if (res.result === true) {
	      			    	  _this.orgList = res.data.list;
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
		      let res = saveOrg(this.form);
		      if(res.result == true){
		    	  xtip.alert(message.save_success, 's', {shade:false, title: message.query_reminder, btn:"close", times: 1}); 
		    	  $("#modal").modal('hide');
		    	  _this.getOrgList();
		      }else{
		    	  xtip.alert(message.save_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 2}); 
		      }
		    },
		    handleReset () {
		      this.form = {
    		    id: '',
		        name: '',
		        mspid: '',
		        adminprivatekeyPath: '',
		        signedcertPath: ''
		      }
		      this.adminFileName = '';
		      this.signFileName = '';
		      $('#fileAdmin').value = '';
		      $('#fileSign').value = '';
		    },
		    edit (row) {
	    	  this.form = row;
		      $("#modal").modal('show');
		    },
		    fileAdminImport () {
		      let _this = this;
		      let file = document.getElementById('fileAdmin').files[0];
		      let reader = new FileReader();
		      reader.readAsText(file);
		      reader.onload = function () {
		        _this.form.adminprivatekeyPath = reader.result;
		        _this.adminFileName = file.name;
		      }
		    },
		    fileSignImport () {
		      let _this = this;
		      let file = document.getElementById('fileSign').files[0];
		      let reader = new FileReader();
		      reader.readAsText(file);
		      reader.onload = function () {
		        _this.form.signedcertPath = reader.result;
		        _this.signFileName = file.name;
		      }
		    }
	    } 
	});