var app = new Vue({
		  el: '#currency',
		  data: {
		    name: '',
		    symbol: '',
		    total: '',
		    status: '',
		    addressList: [],
		    totalBalance:'',
		    scale: 0
		  },
		  mounted(){
			  this.getDetail();
		  },
		  methods: {
			  getDetail() {
				  var _this = this;
				  if(detail != null && detail != undefined){
					  _this.name = detail.name;
					  _this.symbol = detail.symbol;
					  _this.total = detail.total;
					  _this.status = detail.status;
				  }
				  if(addressList != null && addressList != undefined){
					  _this.addressList = addressList;
				  }
				  if(totalBalance != null && totalBalance != undefined){
					  _this.totalBalance = totalBalance;
				  }
				  if(_this.total != '' && _this.totalBalance !=''){
					  _this.scale = _this.formatScale(_this.totalBalance/_this.total);
				  }
			  },
			  formatScale(value){
				  if (value != undefined && value != null) {
		               // 转为百分比展示,如数据为0.16666666666666666这个数据转换成16.67%，以及如果是0.5这种转换成50.00%格式。
		               const scale = ((Math.round((value * 10000)))/100.00).toFixed(2) + '%';
		               return scale;
		           }  
			  },
			  changeDefaultAddress(id){
				  xtip.confirm('确定要设置吗',function(){
					  loadid = xtip.load();
					  $.ajax({
							type:"post",
							url: urls.updateDefaultAddress,
							dataType: "json",
							data:{
								"accountCurrencyId":id
							},
							success:function(result){
								xtip.close(loadid);
								if(result.result == true){
									window.location.href=rootUrl + "/currency/myInfoDetail?id="+result.data;
								}else{
									xtip.alert(result.message, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
								}
							},
							error:function(data) {
								xtip.close(loadid);
							}
						});
				  },{icon:'a'});
			  }
		  }
		})

