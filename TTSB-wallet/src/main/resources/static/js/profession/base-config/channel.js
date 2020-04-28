var app = new Vue({
		el: "#channel-main",
		data: {
			channelId: '',
			peerId: '',
			channelList: [],
			peerList: [],
	        peers: [],
			form: {
				id: '',
		        name: ''
			},
			pageNum: 1,
			pageSize: 10,
			pageTotal: 0,
		},
		mounted () {
			this.getChannelList();
			this.page();
		    this.getPeers();
		},
	    methods: {
	    	getChannelList () {
		      let _this = this;	
		      let data = {
		    		"channel": {},
		    		"pageSize": _this.pageSize,
		    		"pageNum": _this.pageNum
		      };
		      let res = pageByChannel(data);
		      if (res.result === true) {
		    	  _this.channelList = res.data.list;
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
	      			    		"channel": {},
	      			    		"pageSize": _this.pageSize,
	      			    		"pageNum": page
	      			      };
	      			      let res = pageByChannel(data);
	      			      if (res.result === true) {
	      			    	  _this.channelList = res.data.list;
	      			    	  _this.pageTotal = res.data.total;
	      			    	  _this.pageNum = res.data.pageNum;
	      			    	  _this.pageSize = res.data.pageSize;
	      			      }else{
	      			    	  xtip.alert(message.search_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 3});
	      			      }
	                  }
	      		});
			  },
			  getPeers () {
			      let _this = this;
				  let res = findAllPeer();
			      if(res.result == true){
			    	  _this.peers = res.data;
			      }
			  },
			  getPeerByChannelId (channelId) {
			      let data = {
			    		  "channelId": channelId
			      }
			      let _this = this;
			      let res = findPeerByChannelId(data);
			      if (res.result === true) {
			          _this.peerList = res.data;
			        }
			    },
		      detail (id) {
			        $("#modal-joined").modal('show');
			        this.getPeerByChannelId(id);
		      },
		      edit (id){
		    	  $("#modal-edit").modal('show');
		    	  this.getPeerByChannelId(id);
		    	  this.channelId = id;
		      },
	          isInPeerList (peer) {
	            let flag = false;
	            let _this = this;
	            for (let item of _this.peerList) {
	              if (item.name === peer.name) {
	                flag = true;
	              }
	            }
	            return flag;
	          },
	          addPeer (peerId) {
	        	  let _this = this;
	        	  let data = {
	        			  "channelId": _this.channelId,
	        			  "peerId": peerId
	        	  };
	        	  let res = addPeerOfChannel(data);
	        	  if(res.result == true){
	        		  xtip.alert(message.add_success, 's', {shade:false, title: message.query_reminder, btn:"close", times: 2});
	        		  _this.getPeerByChannelId(this.channelId);
	        	  }else{
	        		  xtip.alert(message.add_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 2});
	        	  }
	          },
              removePeer (peerId) {
	        	  let _this = this;
	        	  let data = {
	        			  "channelId": _this.channelId,
	        			  "peerId": peerId
	        	  };
	        	  let res = deletePeerOfChannel(data);
	        	  if(res.result == true){
	        		  _this.getPeerByChannelId(this.channelId);
	        		  xtip.alert(message.delete_success, 's', {shade:false, title: message.query_reminder, btn:"close", times: 2});
	        	  }else{
	        		  xtip.alert(message.delete_fail, 'e', {shade:false, title: message.query_reminder, btn:"close", times: 2});
	        	  }
             }
    	} 
	});