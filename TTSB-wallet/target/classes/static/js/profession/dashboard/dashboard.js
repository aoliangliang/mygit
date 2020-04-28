var app = new Vue({
		el: "#dashboard",
		data: {
			name:'',
			xData: [],
			yData: []
		},
		mounted: function(){
			this.loadName();
//			this.initData();
		},
		methods:{
			loadName: function() {
				var _this = this;
				_this.name = name;
			},
			initData() {
				var _this = this;
				$.ajax({
					type:"get",
					url: urls.txRecordByDate,
					dataType: "json",  
					success:function(result){
						if(result.result == true){
							var data = result.data;
							for(var key in data){
								_this.xData.push(key);
								_this.yData.push(data[key]);
								_this.initChart();
							}
						}else{
							xtip.alert('查询出错', 'e', {shade:false, title:"服务错误", btn:"关闭", times: 3});
						}
					}
				});
			},
			initChart() {
				 var _this = this; 
				 var myChart = echarts.init(document.getElementById('main'));

			        // 指定图表的配置项和数据
			        var option = {
			            title: {
			                text: '系统交易量'
			            },
			            tooltip: {},
			            legend: {
			                data:['交易量']
			            },
			            xAxis: {
			                data: _this.xData
			            },
			            yAxis: {},
			            series: [{
			                name: '交易量',
			                type: 'bar',
			                data: _this.yData
			            }]
			        };
			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
			}
		}
	});