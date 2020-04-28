var app = new Vue({
		  el: '#txrecord',
		  data: {
		    fromUser: '',
		    fromAddress: '',
		    toUser: '',
		    toAddress: '',
		    amount: '',
		    status: '',
		    txTime: '',
		    hash: '',
		    currency: {
		    	name: ''
		    }
		  },
		  mounted(){
			  this.getDetail();
		  },
		  methods: {
			  getDetail() {
				  var _this = this;
				  if(detail != null && detail != undefined){
					  var temp = JSON.parse(detail);
					  _this.fromUser = temp.fromUser;
					  _this.fromAddress = temp.fromAddress;
					  _this.toUser = temp.toUser;
					  _this.toAddress = temp.toAddress;
					  
					  _this.amount = temp.amount;
					  _this.status = temp.status;
					  _this.txTime = temp.tranTime;
					  _this.hash = temp.hash;
					  _this.currency = temp.walletCurrency;
				  }
			  },
		  }
		})

