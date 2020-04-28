var app = new Vue({
		  el: '#currency',
		  data: {
		    name: '',
		    symbol: '',
		    total: '',
		    status: '',
		    createTime: ''
		  },
		  mounted(){
			  this.getDetail();
		  },
		  methods: {
			  getDetail() {
				  var _this = this;
				  if(detail != null && detail != undefined){
					  var temp = JSON.parse(detail);
					  _this.name = temp.name;
					  _this.symbol = temp.symbol;
					  _this.total = temp.total;
					  _this.status = temp.status;
					  _this.createTime = temp.createTime;
				  }
			  }
		  }
		})

