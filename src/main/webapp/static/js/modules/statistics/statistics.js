
/**
 * 微信用户输入词云
 * @param ecDom
 * @return
 */
function impurityWordCloud(ecDom){
	$.post(ctx+'/admin/tj/impurityWordCloud',function(d){
		if(d.success){
			var data=[],word;
			for(var wi in d.data){
				word = d.data[wi];
				data.push({
					name: word.word,
	                value: word.wordNum,
	                itemStyle: createRandomItemStyle()
				});
			}
			option = {
					noDataLoadingOption:{
						effect:"dynamicLine",
						text:"暂无微信用户输入"
						//'spin' | 'bar' | 'ring' | 'whirling' | 'dynamicLine' | 'bubble'
					},
					title: { text: '微信用户输入' },
					tooltip: { show: true },
					series: [{
						name: '微信用户输入',
						type: 'wordCloud',
						size: ['100%', '100%'],
						textRotation : [0, 45, 90, -45],
						textPadding: 0,
						autoSize: {
							enable: true,
							minSize: 14
						},
						data: data
				}]
			};
			ecDom.setOption(option); 
		}
	});
}

/**
 * 访问量统计
 * @param ecDom
 * @param params
 * @return
 */
function getAccessNumByDate(ecDom,params){
	 $.post(ctx+"/admin/tj/getAccessNumByDate",{},function(d){
			if(d.success){
				var xAxis=[],pv=[],uv=[],access;
				for(var i in d.data){
					access = d.data[i];
					xAxis.push(access.today);
					pv.push(access.pv);
					uv.push(access.uv);
					}
	                var option = {
	                	title : {
		                    text: '访问统计'
             		},
	                    tooltip: {
	                        show: true
	                    },
	                    legend: {
	                        data:["pv","uv"]
	                    },
	                    xAxis : [
	                        {
	                            type : 'category',
	                            data : xAxis
	                        }
	                    ],
	                    yAxis : [
	                        {
	                            type : 'value'
	                        }
	                    ],
	                    series : [
	                        {
	                            "name":"pv",
	                           	"type":"line",
	                            "data":pv
	                        },
	                        {
	                            "name":"uv",
	                           	"type":"line",
	                            "data":uv
	                        }
	                    ]
	                };
	                ecDom.setOption(option); 
				}
		    });
}
/**
 * 在线人数
 */
function online(ecDom){
	var option = {
			noDataLoadingOption:{
				text:"暂无在线人数",
				effect:"spin"
			},
        	title : {
                text: '在线人数',
                subtext: ""
        	},
            tooltip: {
                show: true
            },
            toolbox: {
                show : true,
                feature : {
                    restore : {show: true},
                }
            },
            legend: {
                data:["游客","web","微信"]
            },
            xAxis : [
                {
                    type : 'category',
                    data:(function (){
                        var now = new Date();
                        var res = [];
                        var len = 5;
                        while (len--) {
                            res.push(now.format("hh:mm:ss"));
                            now = new Date(now - 3000);
                        }
                        return res;
                    })()
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"游客",
                   	"type":"bar",
                   	"data":[0,0,0,0,0]
                       	
                },
                {
                    "name":"web",
                   	"type":"bar",
                	"data":[0,0,0,0,0]
                },{
                    "name":"微信",
                   	"type":"bar",
                	"data":[0,0,0,0,0]
                }
            ]
        };

        // 为echarts对象加载数据 
	ecDom.setOption(option); 
        
        (function refreshData(){
        	$.post(ctx+"/admin/tj/getActive.action",function(d){
        		if(d.success){
                    // 动态数据接口 addData
                    var currentTime= new Date(d.data.currentTime).format("hh:mm:ss");
                    ecDom.setOption({
	                    	title : {
    		                    text: currentTime+'在线人数',
    		                    subtext: d.msg
	                		}
	                    }). addData([
                        [
                            0,        // 系列索引
                            d.data.guest, // 新增数据
                            true,     // 新增数据是否从队列头部插入
                            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
                        ],
                        [
                          1,        // 系列索引
                          d.data.web, // 新增数据
                          true,     // 新增数据是否从队列头部插入
                          false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
                    	 ],
                    	 [
                          2,        // 系列索引
                          d.data.wx, // 新增数据
                          true,     // 新增数据是否从队列头部插入
                          false,     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
                          currentTime
                    	 ]
                    ]);
                    setTimeout(refreshData,3000);
        			}
        	});
         })()
}

/**
 * 随机颜色
 */
function createRandomItemStyle() {
    return {
        normal: {
            color: 'rgb(' + [
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160)
            ].join(',') + ')'
        }
    };
}
