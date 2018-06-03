<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	 function allOverview(){
			$('#other').highcharts({
			chart : {
				type : 'spline'
			},
			title : {
				text : '订单流量'
			},
			xAxis : {
				tickInterval: ${ tickInterval },
				categories :${ dayList },
				labels:{
	                // x:45,//调节x偏移
	                //y:-35,//调节y偏移
	                //rotation:-35//调节倾斜角度偏移
	             }
			},
			yAxis : {
			//tickInterval: 20000,  //自定义刻度    
			//max:10000,//纵轴的最大值  
			min : 0,
			title : {
				text : '金额(元)',
				rotation :0,
			    x: -20
			},
			labels : {
				formatter : function() {
					return this.value 
				}
			}
			},
			plotOptions : {
				spline : {
					marker : {
					radius : 4,
					lineColor : '#FF0000',
					lineWidth : 1
					}
				}
			},
			series : [{
			color: '#FF0000',
			name : '金额',
			data :${ personList }
			}]
			});	
			$('#other1').highcharts({
				chart : {
					type : 'column'
				},
				title : {
					text : '商品销售'
				},
				xAxis : {
					categories : ${ nameList }
				},
				yAxis : {
				min : 0,
				title : {
					text : '销售额',
					rotation :0,
					x: -20
				}
				},
				tooltip : {
					formatter : function() {
						return '<b>' + this.x + '</b><br/>' + this.series.name + ': ' + this.y + '<br/>' + 'Total: ' + this.point.stackTotal;
					}
				},
				series : [ {
				name : '元',
				data : ${ salesAmountList }
				} ]
				});	
			
			 $('#main').highcharts({
		            chart: {
		                type: 'bar'
		            },
		            title: {
		                text: 'TOP10商品销售图'
		            },
		            xAxis: {
		                categories:  ${ nameList },
		                title: {
		                    text: null
		                }
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: '元',
		                    align: 'high'
		                }
		            },
		            tooltip: {
		                valueSuffix: ' 元'
		            },
		            credits: {
		                enabled: false
		            },
		            series: [{
		            	name : '销售额',
		            	color: '#00EC00',
		                data:  ${ salesAmountList }
		            }]
		        }); 
			
			 $('#main1').highcharts({
		            chart: {
		                type: 'bar'
		            },
		            title: {
		                text: 'TOP10商品销售图'
		            },
		            xAxis: {
		                categories:  ${ nameList },
		                title: {
		                    text: null
		                }
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: '元',
		                    align: 'high'
		                }
		            },
		            tooltip: {
		                valueSuffix: ' 元'
		            },
		            credits: {
		                enabled: false
		            },
		            series: [{
		            	name : '销售额',
		            	color: '#00EC00',
		                data:  ${ salesAmountList }
		            }]
			    });
	 }
	$(function() {
		allOverview();
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/remind.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">订单中心</a></li>
				<li><a href="#">订单监控</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="order_table">
			店铺选择：
			<s:select id="channelDistributorId" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" value="" multiple="false" theme="simple">
			</s:select>
		</div>
		<div class="order_monitor clearfix">
			<div class="order_m_right">
				<h2>订单数据</h2>
				<div class="order_monitor clearfix">
					<div class="order_m_left">
						<div id="other"></div>
					</div>
					<div class="order_m_right">
						<div id="other1"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="order_m2 clearfix">
			<div class="order_m2_r">
				<h2>订单流量监控</h2>
				<div class="order_monitor clearfix">
					<div class="order_m_left">
						<div id="main"></div>
					</div>
					<div class="order_m_right">
						<div id="main1"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>

<style>
.order_monitor {
	padding: 15px;
}

.order_monitor h2 span, .order_box h2 span {
	font-size: .7em;
	margin: 0 0 0 15px;
	font-weight: lighter;
}

.order_m_left {
	float: left;
	width: 50%;
	display: inline;
	margin-right: 10px;
}

.order_m2_l ul {
	padding: 10px 0;
}

.order_m2_l ul li {
	float: left;
	font-size: 1.2em;
	width: 50%;
	line-height: 2em;
	margin: 10px 0;
	text-align: center;
	border: #ddd solid 1px;
	border-left: none;
	border-right: none;
	background: #eee;
}

.order_m2_l ul li strong {
	font-weight: bold;
}

.order_m2_l ul li.oml_first {
	width: 100%;
}

.order_m_right {
	overflow: hidden;
}

.order_m2 {
	padding: 15px;
}

.order_m2_l {
	float: left;
	display: inline;
	width: 350px;
	margin-right: 10px;
}

.order_m2_l table {
	line-height: 2em;
	font-size: 1.2em;
	margin: 35px 0 0;
	text-align: center;
}

.order_m2_l table strong {
	font-weight: bold;
}

.order_m2_r {
	overflow: hidden;
}
</style>