var app = new Vue({
		el: "#top-nav",
		data: {
			name:'',
			avatar:''
		},
		mounted: function(){
			this.loadUserInfo();
		},
		methods:{
			loadUserInfo: function() {
				var _this = this;
				_this.name = name;
				_this.avatar = avatar;
			}
		}
	});