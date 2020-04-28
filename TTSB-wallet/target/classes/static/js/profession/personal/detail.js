var app = new Vue({
		  el: '#currency',
		  data: {
			username: '',
		    name: '',
		    address: '',
		    balance: ''
		  },
		  mounted(){
			  this.getDetail();
		  },
		  methods: {
			  getDetail() {
				  this.username = username;
				  this.name = name;
				  this.address = address;
				  this.getBalance();
			  },
			  getBalance: function() {
					let _this = this;
					$.ajax({
						type:"get",
						url: urls.balance,
						dataType: "json",  
						async:true,
						success:function(result){
							if(result.result == true){
								_this.balance = result.data;
							}else{
								xtip.alert('查询余额失败', 'e', {shade:false, title:"Service Error", btn:"close", times: 3});
							}
						}
					});
				},
			  formatScale(value){
				  if (value != undefined && value != null) {
		               // 转为百分比展示,如数据为0.16666666666666666这个数据转换成16.67%，以及如果是0.5这种转换成50.00%格式。
		               const scale = ((Math.round((value * 10000)))/100.00).toFixed(2) + '%';
		               return scale;
		           }  
			  },
		  }
		})

