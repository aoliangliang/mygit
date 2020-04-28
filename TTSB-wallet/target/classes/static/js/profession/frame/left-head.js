var app = new Vue({
		el: "#left-head",
		data: {
			username:'',
			address: '',
			balance: 0,
			avatar: ''
		},
		mounted: function(){
			this.loadUserInfo();
		},
		methods:{
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
			loadUserInfo: function() {
				var _this = this;
				_this.username = username;
				_this.address = address;
				_this.avatar = avatar;
				_this.getBalance();
			}
		}
	});