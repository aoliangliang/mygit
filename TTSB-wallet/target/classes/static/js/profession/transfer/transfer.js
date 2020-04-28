var app = new Vue({
		el: "#transfer",
		data: {
			currencyId: '',
			fromAddress:'',
			balance: '',
			balanceShow: false,
			toUsername: '',
			toAddress: '',
			password: '',
			amount: '',
			currencyList: [],
			fromAddressList: []
		},
		mounted: function(){
			this.getCurrencyAddress();
			this.getCurrencyList();
		},
		methods:{
			getCurrencyAddress() {
				if(currencyId != null && currencyId != ''){
					this.currencyId = currencyId;
				}
				if(addressList != null && addressList != undefined){
					this.fromAddressList = addressList;
				}
			},
			getCurrencyList: function() {
				var _this = this;
				$.ajax({
					type:"get",
					url: urls.currencyList,
					dataType: "json",  
					async:true,
					success:function(result){
						if(result.result == true){
							_this.currencyList = result.data;
						}else{
							xtip.alert('查询出错', 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
						}
					}
				});
			},
			transfer() {
				let _this = this;
				if(_this.currencyId == ''){
					xtip.msg('请选择币种类型',{icon:'e'})
					return;
				}
				if(_this.fromAddress == ''){
					xtip.msg('请选择币种地址',{icon:'e'})
					return;
				}
				if(_this.toUsername == ''){
					xtip.msg('请填写转入的用户名',{icon:'e'})
					return;
				}
				if(_this.toAddress == ''){
					xtip.msg('请填写转入用户的地址',{icon:'e'})
					return;
				}
				if(_this.amount == ''){
					xtip.msg('请输入转账金额',{icon:'e'})
					return;
				}
				xtip.confirm('确定要转账吗?',function(){
					loadid = xtip.load();
					$.ajax({
						type:"post",
						url: urls.transfer,
						data: {
							"currencyId": _this.currencyId,
							"fromAddress":_this.fromAddress,
//							"password": _this.password,
							"toUser": _this.toUsername,
							"toAddress":_this.toAddress,
							"amount":_this.amount,
						},
						dataType: "json",  
						success:function(result){
							xtip.close(loadid);
							if(result.result == true){
								_this.currencyId = '';
								_this.fromAddress = '';
								_this.toUsername = '';
								_this.toAddress = '';
								_this.amount = '';
								_this.balanceShow = false;
								xtip.alert('转账需要一定时间，请稍候.','h');
							}else{
								xtip.alert(result.message, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
							}
						},
						error:function(data) {
							xtip.close(loadid);
						}
					});
				},{icon:'a'})
			},
			getAddress(){
				loadid = xtip.load();
				let _this = this;
				$.ajax({
					type:"get",
					url: urls.getAddressByCurrencyType,
					data: {
						"currencyId": _this.currencyId,
					},
					dataType: "json",  
					success:function(result){
						xtip.close(loadid);
						if(result.result == true){
							_this.fromAddressList = result.data;
						}else{
							xtip.alert(result.message, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
						}
					}
				});
			},
			getBalanceByCurrencyAddress(){
				var _this = this;
				var currencyId = _this.currencyId;
				var fromAddress = _this.fromAddress;
				if(currencyId == null || currencyId == '' || fromAddress == null || fromAddress == ''){
					_this.balanceShow = false;
				}else{
					loadid = xtip.load();
					$.ajax({
						type:"get",
						url: urls.getBalanceByCurrencyAddress,
						data: {
							"currencyId": _this.currencyId,
							"fromAddress": _this.fromAddress
						},
						dataType: "json",  
						success:function(result){
							xtip.close(loadid);
							if(result.result == true){
								_this.balanceShow = true;
								_this.balance = result.data;
							}else{
								_this.toAddress = '';
								xtip.alert(result.message, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
							}
						}
					});
				}
			},
			getAddressByUsername() {
				let _this = this;
				var username = _this.toUsername;
				if(username == null || username == ''){
					xtip.msg('请填写转入用户',{icon:'w'})
				}else{
					loadid = xtip.load();
					$.ajax({
						type:"get",
						url: urls.getAddressByUsername,
						data: {
							"currencyId": _this.currencyId,
							"username": _this.toUsername
						},
						dataType: "json",  
						success:function(result){
							xtip.close(loadid);
							if(result.result == true){
								_this.toAddress = result.data;
							}else{
								_this.toAddress = '';
								xtip.alert(result.message, 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
							}
						}
					});
				}
			}
		}
	});