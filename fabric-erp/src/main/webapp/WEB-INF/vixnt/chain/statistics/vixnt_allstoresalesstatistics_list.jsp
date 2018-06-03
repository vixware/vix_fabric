<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 连锁经营管理 <span>> 门店销售额排名 </span> 
			</h1>
		</div>
	</div>
	<section>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
						</span>
						<h2>门店销售额排名</h2>
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
						                data : [${salesDataNames}]
						            }
						        ],
						        grid: { // 控制图的大小，调整下面这些值就可以，
						             x: 175,
						             //x2: 50,
						             //y2: 50// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
						         },
								series : [ {
								"name" : "销售金额",
								"type" : "bar",
								"data" : [ ${salesDataPrices} ]
								} ]
								};
								// 为echarts对象加载数据 
								myChart.setOption(option);
							</script>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>