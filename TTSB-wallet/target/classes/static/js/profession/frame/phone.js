var app = new Vue({
		el: "#phone",
		data: {
			username: sessionStorage.getItem("username"),
			avatar: sessionStorage.getItem("avatar")
		}
	});