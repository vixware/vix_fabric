<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 门店管理 <span>> 门店数据统计 </span> <span>> 门店销售统计</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>统计报表</h2>
			<div class="widget-toolbar">
				<div class="btn-group">
					<button class="btn dropdown-toggle btn-primary btn-xs" data-toggle="dropdown">
						选择时段 <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" onclick="goList('week');">一周</a></li>
						<li><a href="javascript:void(0);" onclick="goList('month');">一月</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<header>
					<h5>客户消费金额前十</h5>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="customerMain" style="height: 400px"></div>
						<script type="text/javascript">
								var customerMain = echarts.init(document.getElementById('customerMain'));

								var option = {
								tooltip : {
									show : true
								},
								legend : {
									data : [ '消费金额' ]
								},
								xAxis : [
						            {
						                type : 'value'
						            }
						        ],
						        yAxis : [
						            {
						                type : 'category',
						                data : [${customerNames}]
						            }
						        ],
						        grid: { // 控制图的大小，调整下面这些值就可以，
						             x: 75,
						             x2: 50,
						             y2: 50// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
						         },
								color : ['#1e90ff'],
								series : [ {
								"name" : "消费金额",
								"type" : "bar",
								"data" : [ ${customerPrices} ]
								} ]
								};
								// 为echarts对象加载数据 
								customerMain.setOption(option);
							</script>
					</div>
				</div>
			</article>
			<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<header>
					<h5>商品销售金额前十</h5>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="item" style="height: 400px"></div>
						<script type="text/javascript">
								var item = echarts.init(document.getElementById('item'));

								var option = {
								tooltip : {
									show : true
								},
								legend : {
									data : [ '销售金额' ]
								},
								xAxis : [
						            {
						                type : 'value'
						            }
						        ],
						        yAxis : [
						            {
						                type : 'category',
						                data : [${itemNames}]
						            }
						        ],
						        grid: { // 控制图的大小，调整下面这些值就可以，
						             x: 175,
						             x2: 50,
						             y2: 50// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
						         },
								color : ['#1e90ff'],
								series : [ {
								"name" : "销售金额",
								"type" : "bar",
								"data" : [ ${itemPrices} ]
								} ]
								};
								item.setOption(option);
							</script>
					</div>
				</div>
			</article>
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<header>
					<h5>门店日销售金额</h5>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="dayItem" style="height: 400px"></div>
						<script type="text/javascript">
								var dayItem = echarts.init(document.getElementById('dayItem'));

								var option1 = {
								tooltip : {
									show : true
								},
								legend : {
									data : [ '销售金额' ]
								},
								xAxis : [ {
								type : 'category',
								data : [ ${dayItemNames} ]
								} ],
								yAxis : [ {
									type : 'value'
								} ],
						        grid: { // 控制图的大小，调整下面这些值就可以，
						             x: 50,
						             x2: 50,
						             y2: 50// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
						         },
								color : ['#1e90ff'],
								series : [ {
								"name" : "销售金额",
								"type" : "line",
								"data" : [ ${dayItemPrices} ]
								} ]
								};
								dayItem.setOption(option1);
							</script>
					</div>
				</div>
			</article>
		</div>
	</div>
</div>
<script type="text/javascript">
	function goList(dateType) {
		loadContent('', '${nvix}/nvixnt/vixntStoreSalesStatisticsAction!goList.action?dateType=' + dateType);
	};
</script>