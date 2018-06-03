<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 库存管理 <span>> 库存报表</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
			</span>
			<h2>库存报表</h2>
			<div class="widget-toolbar">
				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
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
					<h5>商品入库数量前十</h5>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="domMain" style="height: 400px"></div>
						<script type="text/javascript">
								var myChart = echarts.init(document.getElementById('domMain'));

								var option = {
								tooltip : {
									show : true
								},
								legend : {
									data : [ '入库数量' ]
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
								color : ['#d2691e'],
								series : [ {
								"name" : "入库数量",
								"type" : "bar",
								"data" : [ ${itemQuantitys} ]
								} ]
								};
								// 为echarts对象加载数据 
								myChart.setOption(option);
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
						<div id="salesitem" style="height: 400px"></div>
						<script type="text/javascript">
								var item = echarts.init(document.getElementById('salesitem'));

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
						                data : [${salesPriceItemNames}]
						            }
						        ],
						        grid: { // 控制图的大小，调整下面这些值就可以，
						             x: 175,
						             //x2: 50,
						             //y2: 50// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
						         },
								color : ['#1e90ff'],
								series : [ {
								"name" : "销售金额",
								"type" : "bar",
								"data" : [ ${salesItemPrices} ]
								} ]
								};
								// 为echarts对象加载数据 
								item.setOption(option);
							</script>
					</div>
				</div>
			</article>
			<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<header>
					<h5>商品入库金额前十</h5>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="item" style="height: 400px"></div>
						<script type="text/javascript">
								var item = echarts.init(document.getElementById('item'));

								var option1 = {
								tooltip : {
									show : true
								},
								legend : {
									data : [ '入库金额' ]
								},
								xAxis : [ {
								type : 'category',
								data : [ ${itemNames2} ]
								} ],
								yAxis : [ {
									type : 'value'
								} ],
								color : ['#4b0082'],
								series : [ {
								"name" : "入库金额",
								"type" : "bar",
								"data" : [ ${itemPrices} ]
								} ]
								};
								// 为echarts对象加载数据 
								item.setOption(option1);
							</script>
					</div>
				</div>
			</article>
			<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<header>
					<h5>商品销售数量前十</h5>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="outitemprice" style="height: 400px"></div>
						<script type="text/javascript">
								var outitemprice = echarts.init(document.getElementById('outitemprice'));

								var option1 = {
								tooltip : {
									show : true
								},
								legend : {
									data : [ '销售数量' ]
								},
								xAxis : [ {
								type : 'category',
								data : [ ${salesQuantityItemNames} ]
								} ],
								yAxis : [ {
									type : 'value'
								} ],
								color : ['#1e90ff'],
								series : [ {
								"name" : "销售数量",
								"type" : "bar",
								"data" : [ ${itemSalesQuantitys} ]
								} ]
								};
								// 为echarts对象加载数据 
								outitemprice.setOption(option1);
							</script>
					</div>
				</div>
			</article>
		</div>
	</div>
</div>
<script type="text/javascript">
  function goList(dateType) {
	loadContent('', '${nvix}/nvixnt/vixntInventoryStatisticsAction!goList.action?dateType=' + dateType);
  };
</script>