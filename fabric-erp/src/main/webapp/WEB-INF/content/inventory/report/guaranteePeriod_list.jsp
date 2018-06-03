<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script>
	$(function () {
		$ ('#container').highcharts ({
		chart : {
			type : 'column'
		} ,
		title : {
			text : 'Stacked column chart'
		} ,
		xAxis : {
			categories : [
			'Apples' , 'Oranges' , 'Pears' , 'Grapes' , 'Bananas'
			]
		} ,
		yAxis : {
		min : 0 ,
		title : {
			text : 'Total fruit consumption'
		} ,
		stackLabels : {
		enabled : true ,
		style : {
		fontWeight : 'bold' ,
		color : (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		}
		}
		} ,
		legend : {
		align : 'right' ,
		x : - 70 ,
		verticalAlign : 'top' ,
		y : 20 ,
		floating : true ,
		backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white' ,
		borderColor : '#CCC' ,
		borderWidth : 1 ,
		shadow : false
		} ,
		tooltip : {
			formatter : function (){
				return '<b>' + this.x + '</b><br/>' + this.series.name + ': ' + this.y + '<br/>' + 'Total: ' + this.point.stackTotal;
			}
		} ,
		plotOptions : {
			column : {
			stacking : 'normal' ,
			dataLabels : {
			enabled : true ,
			color : (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
			}
			}
		} ,
		series : [
		{
		name : 'John' ,
		data : [
		5 , 3 , 4 , 7 , 2
		]
		} , {
		name : 'Jane' ,
		data : [
		2 , 2 , 3 , 2 , 1
		]
		} , {
		name : 'Joe' ,
		data : [
		3 , 4 , 4 , 2 , 5
		]
		}
		]
		});
    $('#container2').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Historic World Population by Region'
        },
        subtitle: {
            text: 'Source: Wikipedia.org'
        },
        xAxis: {
            categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Population (millions)',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' millions'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'Year 1800',
            data: [107, 31, 635, 203, 2]
        }, {
            name: 'Year 1900',
            data: [133, 156, 947, 408, 6]
        }, {
            name: 'Year 2008',
            data: [973, 914, 4054, 732, 34]
        }]
    });
    $('#container3').highcharts({
	    chart: {
	        polar: true
	    },
	    title: {
	        text: 'Highcharts Polar Chart'
	    },
	    pane: {
	        startAngle: 0,
	        endAngle: 360
	    },
	    xAxis: {
	        tickInterval: 45,
	        min: 0,
	        max: 360,
	        labels: {
	        	formatter: function () {
	        		return this.value + '°';
	        	}
	        }
	    },
	    yAxis: {
	        min: 0
	    },
	    plotOptions: {
	        series: {
	            pointStart: 0,
	            pointInterval: 45
	        },
	        column: {
	            pointPadding: 0,
	            groupPadding: 0
	        }
	    },
	    series: [{
	        type: 'column',
	        name: 'Column',
	        data: [8, 7, 6, 5, 4, 3, 2, 1],
	        pointPlacement: 'between'
	    }, {
	        type: 'line',
	        name: 'Line',
	        data: [1, 2, 3, 4, 5, 6, 7, 8]
	    }, {
	        type: 'area',
	        name: 'Area',
	        data: [1, 8, 2, 7, 3, 6, 4, 5]
	    }]
	});
    $('#container1').highcharts({
	    chart: {
	        type: 'gauge',
	        plotBackgroundColor: null,
	        plotBackgroundImage: null,
	        plotBorderWidth: 0,
	        plotShadow: false
	    },
	    
	    title: {
	        text: 'Speedometer'
	    },
	    
	    pane: {
	        startAngle: -150,
	        endAngle: 150,
	        background: [{
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#FFF'],
	                    [1, '#333']
	                ]
	            },
	            borderWidth: 0,
	            outerRadius: '109%'
	        }, {
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#333'],
	                    [1, '#FFF']
	                ]
	            },
	            borderWidth: 1,
	            outerRadius: '107%'
	        }, {
	        }, {
	            backgroundColor: '#DDD',
	            borderWidth: 0,
	            outerRadius: '105%',
	            innerRadius: '103%'
	        }]
	    },
	       
	    yAxis: {
	        min: 0,
	        max: 200,
	        
	        minorTickInterval: 'auto',
	        minorTickWidth: 1,
	        minorTickLength: 10,
	        minorTickPosition: 'inside',
	        minorTickColor: '#666',
	
	        tickPixelInterval: 30,
	        tickWidth: 2,
	        tickPosition: 'inside',
	        tickLength: 10,
	        tickColor: '#666',
	        labels: {
	            step: 2,
	            rotation: 'auto'
	        },
	        title: {
	            text: 'km/h'
	        },
	        plotBands: [{
	            from: 0,
	            to: 120,
	            color: '#55BF3B' // green
	        }, {
	            from: 120,
	            to: 160,
	            color: '#DDDF0D' // yellow
	        }, {
	            from: 160,
	            to: 200,
	            color: '#DF5353' // red
	        }]        
	    },
	
	    series: [{
	        name: 'Speed',
	        data: [80],
	        tooltip: {
	            valueSuffix: ' km/h'
	        }
	    }]
	
	}, 
	function (chart) {
		if (!chart.renderer.forExport) {
		    setInterval(function () {
		        var point = chart.series[0].points[0],
		            newVal,
		            inc = Math.round((Math.random() - 0.5) * 20);
		        
		        newVal = point.y + inc;
		        if (newVal < 0 || newVal > 200) {
		            newVal = point.y - inc;
		        }
		        
		        point.update(newVal);
		        
		    }, 3000);
		};
	});
});
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />Print</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />Help</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">报表</a></li>
				<li><a href="#">库存账 </a></li>
				<li><a href="#">现存量查询 </a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div class="addbox" style="margin-top: 0">
					<div class="addtitle">
						<span class="l"><img width="16" height="16" style="vertical-align: middle" src="img/win_btn_03.png">&nbsp;分类</span>
					</div>
					<div class="np_clist lineh30" id="np1">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(11,1,'a',event)">现存量查询</a></li>
							<li><a href="#" onclick="javascript:tab(11,2,'a',event)">出入库流水账</a></li>
							<li><a href="#" onclick="javascript:tab(11,3,'a',event)">库存台账</a></li>
							<li><a href="#" onclick="javascript:tab(11,4,'a',event)">代管账</a></li>
							<li><a href="#" onclick="javascript:tab(11,5,'a',event)">不合格品备查簿</a></li>
							<li><a href="#" onclick="javascript:tab(11,6,'a',event)">呆滞积压备查簿</a></li>
							<li><a href="#" onclick="javascript:tab(11,7,'a',event)">供货单位收发存汇总表</a></li>
							<li><a href="#" onclick="javascript:tab(11,8,'a',event)">入库跟踪表</a></li>
							<li><a href="#" onclick="javascript:tab(11,9,'a',event)">订单预留历史记录查询</a></li>
						</ul>
					</div>
					<div class="np_clist lineh30" id="np2" style="display: none;">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(10,1,'b',event)">货位卡片</a></li>
							<li><a href="#" onclick="javascript:tab(10,2,'b',event)">货位汇总表</a></li>
						</ul>
					</div>
					<div class="np_clist lineh30" id="np3" style="display: none;">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(10,1,'c',event)">库存展望</a></li>
							<li><a href="#" onclick="javascript:tab(10,2,'c',event)">收发存汇总表</a></li>
							<li><a href="#" onclick="javascript:tab(10,3,'c',event)">存货分布表</a></li>
							<li><a href="#" onclick="javascript:tab(10,4,'c',event)">业务类型汇总表</a></li>
							<li><a href="#" onclick="javascript:tab(10,5,'c',event)">限额领料汇总表</a></li>
						</ul>
					</div>
					<div class="np_clist lineh30" id="np4" style="display: none;">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(10,1,'d',event)">安全库存预警</a></li>
							<li><a href="#" onclick="javascript:tab(10,2,'d',event)">超储存货查询</a></li>
							<li><a href="#" onclick="javascript:tab(10,3,'d',event)">短缺存货查询</a></li>
							<li><a href="#" onclick="javascript:tab(10,4,'d',event)">呆滞积压存货分销</a></li>
							<li><a href="#" onclick="javascript:tab(10,5,'d',event)">库龄分销</a></li>
							<li><a href="#" onclick="javascript:tab(10,6,'d',event)">缺料表</a></li>
						</ul>
					</div>
					<div class="np_clist lineh30" id="np5" style="display: none;">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(10,1,'e',event)">ROP采购计划资金预算</a></li>
							<li><a href="#" onclick="javascript:tab(10,2,'e',event)">ROP采购计划执行情况</a></li>
						</ul>
					</div>
				</div>
				<div class="addbox">
					<div class="addtitle">
						<span class="l"><img width="16" height="16" style="vertical-align: middle" src="img/win_btn_01.png">&nbsp;库存报表</span>
					</div>
					<ul class="navmenu">
						<li><a href="#" onclick="javascript:tab(14,1,'np',event)">库存账</a></li>
						<li><a href="#" onclick="javascript:tab(14,2,'np',event)">货位账</a></li>
						<li><a href="#" onclick="javascript:tab(14,3,'np',event)">统计表</a></li>
						<li><a href="#" onclick="javascript:tab(14,4,'np',event)">储备分析</a></li>
						<li><a href="#" onclick="javascript:tab(14,5,'np',event)">ROP采购计划报表</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" id="a1" style="border: 0;">
				<p>
					<b>现存量查询</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
				<div id="container" style="height: 400px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a2" style="border: 0; display: none;">
				<p>
					<b>出入库流水账</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
				<div id="container2" style="min-width: 450px; max-width: 600px; height: 400px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a3" style="border: 0; display: none;">
				<p>
					<b>库存台账</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a4" style="border: 0; display: none;">
				<p>
					<b>代管账</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a5" style="border: 0; display: none;">
				<p>
					<b>不合格品备查簿</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a6" style="border: 0; display: none;">
				<p>
					<b>呆滞积压备查簿</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a7" style="border: 0; display: none;">
				<p>
					<b>供货单位收发存汇总表</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a8" style="border: 0; display: none;">
				<p>
					<b>入库跟踪表</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a9" style="border: 0; display: none;">
				<p>
					<b>订单预留历史记录查询</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="b1" style="border: 0; display: none;">
				<p>
					<b>货位卡片</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
				<div id="container" style="height: 400px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="b2" style="border: 0; display: none;">
				<p>
					<b>货位汇总表</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3">1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /> </a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option>--请选择--</option>
							</select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
				<div id="container" style="height: 400px; margin: 0 auto"></div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>