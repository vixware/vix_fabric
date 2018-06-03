<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<html lang="en-us">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/font-awesome.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-production-plugins.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-production.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-skins.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-rtl.min.css"></link>
<!-- fusioncharts -->
<script src="${nvix}/vixntcommon/base/js/libs/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/libs/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${nvix}/vixntcommon/base/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="${nvix}/vixntcommon/base/js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
<style>
	html{
		position: relative;
		background-color:#051C47;
	}
	.big-data{
		position: absolute;
	    top: 0;
	    bottom: 0;
	    left: 0;
	    right: 0;
	    height: 100%;
	    background-image:url(${nvix}/vixntcommon/base/img/images/background-img.jpg);
	    background-size: cover;
	    background-repeat: no-repeat;
	    background-position: center;
	    background-color:#051C47;
	}
	.big-data-header{
		display:flex;
		display:-webkit-flex;
		height:44px;
		line-height:44px;
		flex-direction: row;
		justify-content: center;
	}
	.big-data-header .tab-page{
		flex:1;
		display: -webkit-flex;
    	display: -ms-flexbox;
    	display:flex;
    	justify-content: center;
    	height: 100%;
    	color:#51a7ff;
    	cursor:pointer;
    	font-weight:700;
	}
	.line-line{
		display:block;
		border-right: 1px solid #34353c;
    	height: 30px;
    	margin-top:7px;
	}
	.big-data-header .tab-page-main{
		flex:3;
		display: -webkit-flex;
    	display: -ms-flexbox;
    	display:flex;
    	justify-content: center;
    	color:#fff;
    	font-size:16px;
    	font-weight:700;
    	background:url(${nvix}/vixntcommon/base/img/images/title.png) center center ;
    	background-size:100% 100%;
	}
	.big-data-content{
		padding:5px;
	}
	.left-bottom{
		display:inline-block;
		width:12px;
		height:12px;
		background:url(${nvix}/vixntcommon/base/img/images/left-bottom.png);
		background-size:12px;
		position:absolute;
		bottom:-3px;
		left:-3px;
	}
	.right-bottom{
		display:inline-block;
		width:12px;
		height:12px;
		background:url(${nvix}/vixntcommon/base/img/images/right-bottom.png);
		background-size:12px;
		position:absolute;
		bottom:-3px;
		right:-3px;
	}
	.left-top{
		display:inline-block;
		width:12px;
		height:12px;
		background:url(${nvix}/vixntcommon/base/img/images/left-top.png);
		background-size:12px;
		position:absolute;
		top:-3px;
		left:-3px;
	}
	.right-top{
		display:inline-block;
		width:12px;
		height:12px;
		background:url(${nvix}/vixntcommon/base/img/images/right-top.png);
		background-size:12px;
		position:absolute;
		top:-3px;
		right:-3px;
	}
	.weather{
		/* height:160px; */
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;	
		padding:10px 20px;
	}
	.tourist{
		/* height:220px; */
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;
		padding:10px 20px;
	}
	.marketing{
		/* height:460px; */
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		position:relative;
		padding:10px 20px;
	}
	.dataMain{
		/* height:630px; */
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;
		padding:10px 20px;
	}
	.boutRout{
		height:258px;
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;
		padding:10px 20px;
	}
	.travelling{
		/* height:220px; */
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;
		padding:10px 20px;
	}
	.industry{
		/* height:307px; */
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;
		padding:10px 20px;
	}
	.resources{
		/* height:220px; */
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;
		padding:10px 20px;
	}
	.sentiment{
		height:185px; 
		width:100%;
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		position:relative;
		padding:10px 20px;
	}
	.title-text{
		color:#51a7ff;
	}
</style>
</head>
<body class="big-data">
	<div class="big-data-header">
		<div class="tab-page">客流量监测</div><span class="line-line"></span>
		<div class="tab-page">产业监测</div><span class="line-line"></span>
		<div class="tab-page">游客画像</div><span class="line-line"></span>
		<div class="tab-page">自驾游分析</div><span></span>
		<div class="tab-page-main">XX公司运行监控平台</div>
		<div class="tab-page">精品路线</div><span class="line-line"></span>
		<div class="tab-page">营销决策</div><span class="line-line"></span>
		<div class="tab-page">舆情分析</div><span class="line-line"></span>
		<div class="tab-page">景区监控</div><span></span>
	</div>
	<div class="big-data-content">
		<div class="row">
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
				<div class="weather">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;实时车流量</p>
					<div id="chart-container1"></div>
					<span class="left-bottom"></span>
					<span class="left-top"></span>
					<span class="right-bottom"></span>
					<span class="right-top"></span>
				</div>
				<div class="tourist">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;各卡口车流量</p>
					<div id="chart-container2"></div>
					<span class="left-bottom"></span>
					<span class="left-top"></span>
					<span class="right-bottom"></span>
					<span class="right-top"></span>
				</div>
				<div class="marketing">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;停车场数据</p>
					<div class="boutRout-box" style="margin-top:15px;">
						乌鲁木齐&nbsp;&nbsp;12365</br>
						<div class="progress progress-xs">
							<div class="progress-bar bg-color-blue" role="progressbar" style="width: 60%;"></div>
						</div>
						齐齐哈尔&nbsp;&nbsp;12365</br>
						<div class="progress progress-xs">
							<div class="progress-bar bg-color-blue" role="progressbar" style="width: 50%;"></div>
						</div>
						平顶山&nbsp;&nbsp;12365</br>
						<div class="progress progress-xs">
							<div class="progress-bar bg-color-blue" role="progressbar" style="width: 40%;"></div>
						</div>
					</div>
					<span class="left-bottom"></span>
					<span class="left-top"></span>
					<span class="right-bottom"></span>
					<span class="right-top"></span>
				</div>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="padding-left:0;padding-right:0;">
				<!-- <div class="dataMain">
					<div id="chartContainer"></div>
				</div> -->
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<div class="travelling">
							<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;分时顾客量</p>
							<div id="chart-container3" ></div>
							<span class="left-bottom"></span>
							<span class="left-top"></span>
							<span class="right-bottom"></span>
							<span class="right-top"></span>
						</div>
						<div class="travelling">
							<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;顾客来源</p>
							<div id="chart-container4" ></div>
							<span class="left-bottom"></span>
							<span class="left-top"></span>
							<span class="right-bottom"></span>
							<span class="right-top"></span>
						</div>
						<div class="travelling">
							<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;门票销售</p>
							<div id="chart-container5" ></div>
							<span class="left-bottom"></span>
							<span class="left-top"></span>
							<span class="right-bottom"></span>
							<span class="right-top"></span>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<div class="travelling">
							<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;实时酒店入住率</p>
							<div id="chart-container6" ></div>
							<span class="left-bottom"></span>
							<span class="left-top"></span>
							<span class="right-bottom"></span>
							<span class="right-top"></span>
						</div>
						<div class="boutRout">
							<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;精品路线分析</p>
							<div class="chat-body no-padding profile-message">
								<ul>
									<li class="message" style="margin:0;padding:10px 15px;">
										<img src="${nvix}/vixntcommon/base/img/images/touxiang.png" class="" alt="sunny"> 
										<span class="message-text"> 
											<a href="javascript:void(0);" class="username double-line">今日热门线路</a>
											<span>乌鲁木齐-齐齐哈尔-平顶山</span>
										</span>
									</li>
								</ul>
							</div>
							<div class="boutRout-box" style="margin-top:15px;">
								乌鲁木齐&nbsp;&nbsp;12365</br>
								<div class="progress progress-xs">
									<div class="progress-bar bg-color-blue" role="progressbar" style="width: 60%;"></div>
								</div>
								齐齐哈尔&nbsp;&nbsp;12365</br>
								<div class="progress progress-xs">
									<div class="progress-bar bg-color-blue" role="progressbar" style="width: 50%;"></div>
								</div>
								平顶山&nbsp;&nbsp;12365</br>
								<div class="progress progress-xs">
									<div class="progress-bar bg-color-blue" role="progressbar" style="width: 40%;"></div>
								</div>
							</div>
							<span class="left-bottom"></span>
							<span class="left-top"></span>
							<span class="right-bottom"></span>
							<span class="right-top"></span>
						</div>
						<div class="travelling">
							<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;实时酒店入住率</p>
							<div class="row">
								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
									<div class="easy-pie-chart txt-color-greenLight easyPieChart" data-percent="56" data-pie-size="110">
										<span class="percent percent-sign txt-color-greenLight font-md">56</span>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
									<div class="easy-pie-chart txt-color-greenLight easyPieChart" data-percent="56" data-pie-size="110">
										<span class="percent percent-sign txt-color-greenLight font-md">56</span>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
									<div class="easy-pie-chart txt-color-greenLight easyPieChart" data-percent="56" data-pie-size="110">
										<span class="percent percent-sign txt-color-greenLight font-md">56</span>
									</div>
								</div>
							</div>
							<div class="noticeBox" style="margin-top:10px;">
								<p>注意事项：</p>
								<p>1.当硬件1的使用效率超过<span style="color:#F8BC02;">&nbsp;80%&nbsp;</span>的时候，采取这样的方法去控制</p>
								<p>2.当硬件2的使用效率超过施去控制<span style="color:#09B7EA">&nbsp;80%&nbsp;</span>的时候，采取这样的方法去控制</p>
								<p>3.当硬件1的使用效率超过<span style="color:#E42889">&nbsp;80%&nbsp;</span>的时候，采取这样的方法去控制</p>
							</div>
							<span class="left-bottom"></span>
							<span class="left-top"></span>
							<span class="right-bottom"></span>
							<span class="right-top"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
				<div class="industry">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;应急分析</p>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<div class="chat-body no-padding profile-message">
								<ul>
									<li class="message" style="margin:0;padding:10px 15px;">
										<img src="${nvix}/vixntcommon/base/img/images/touxiang.png" class="" alt="sunny"> 
										<span class="message-text"> 
											<a href="javascript:void(0);" class="username double-line">产业单位数据 </a>
											<span style="font-size:22px;">1620</span>个
										</span>
									</li>
								</ul>
							</div>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<div class="chat-body no-padding profile-message">
								<ul>
									<li class="message" style="margin:0;padding:10px 15px;">
										<img src="${nvix}/vixntcommon/base/img/images/touxiang.png" class="" alt="sunny"> 
										<span class="message-text"> 
											<a href="javascript:void(0);" class="username double-line">产业单位总产值 </a>
											<span style="font-size:22px;">16200</span>万元
										</span>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<p style="margin:25px 0;">单位数量与产值分布报表</p>
					<div class="table-responsive" >
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td>单位</td>
									<td>杭州</td>
									<td>上海</td>
									<td>北京</td>
									<td>天津</td>
									<td>重庆</td>
								</tr>
								<tr>
									<td>数量</td>
									<td>51</td>
									<td>156</td>
									<td>123</td>
									<td>520</td>
									<td>121</td>
								</tr>
								<tr>
									<td>产值（万元）</td>
									<td>5621</td>
									<td>1254</td>
									<td>2654</td>
									<td>2389</td>
									<td>3594</td>
								</tr>
							</tbody>
						</table>
					</div>
					<span class="left-bottom"></span>
					<span class="left-top"></span>
					<span class="right-bottom"></span>
					<span class="right-top"></span>
				</div>
				<div class="resources">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;实时环境监测</p>
					<div id="chart-container7"></div>
					<span class="left-bottom"></span>
					<span class="left-top"></span>
					<span class="right-bottom"></span>
					<span class="right-top"></span>
				</div>
				<div class="sentiment">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;搜索热度排名</p>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<div class="chat-body no-padding profile-message">
								<ul>
									<li class="message" style="margin:0;padding:10px 15px;">
										<img src="${nvix}/vixntcommon/base/img/images/touxiang.png" class="" alt="sunny"> 
										<span class="message-text"> 
											<a href="javascript:void(0);" class="username double-line">产业单位数据 </a>
											<span style="font-size:22px;">1620</span>个
										</span>
									</li>
								</ul>
							</div>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<div class="chat-body no-padding profile-message">
								<ul>
									<li class="message" style="margin:0;padding:10px 15px;">
										<img src="${nvix}/vixntcommon/base/img/images/touxiang.png" class="" alt="sunny"> 
										<span class="message-text"> 
											<a href="javascript:void(0);" class="username double-line">产业单位总产值 </a>
											<span style="font-size:22px;">16200</span>万元
										</span>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="progress progress-lg" style="margin-top:20px;">
						<div class="progress-bar bg-color-red1" style="width: 80%"></div>
						<div class="progress-bar bg-color-blue1" style="width: 60%"></div>
						<div class="progress-bar bg-color-green1" style="width: 40%"></div>
					</div>
					<span class="left-bottom"></span>
					<span class="left-top"></span>
					<span class="right-bottom"></span>
					<span class="right-top"></span>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	/* 2.各卡车流量 */
	 FusionCharts.ready(function () {
		    var vstrChart = new FusionCharts({
		        type: 'mscolumn2d',
		        renderAt: 'chart-container1',
		        width: '100%',
		        height: '255',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {                
		                "caption": "Website Visitors",
		                "subCaption": "Last week Vs This week",
		                "xAxisName": "Day",
		                "yAxisName": "No. of Visitors",
		                "theme": "fint",          
		                "canvasBgColor":"#101535",	
			              "canvasBgAlpha":"80",
			              "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "divLineColor":"#6699cc",
		                "divLineAlpha":"60",
		                "divLineIsDashed" : "0"
		            },            
		            "categories": [
		                {
		                    "category": [
		                        {
		                            "label": "Mon"
		                        }, 
		                        {
		                            "label": "Tue"
		                        }, 
		                        {
		                            "label": "Wed"
		                        }, 
		                        {
		                            "label": "Thu"
		                        }, 
		                        {
		                            "label": "Fri"
		                        }, 
		                        {
		                            "label": "Sat"
		                        }, 
		                        {
		                            "label": "Sun"
		                        }
		                    ]
		                }
		            ],            
		            "dataset": [
		                {
		                    "seriesname": "Last Week",
		                    "data": [
		                        {
		                            "value": "13000"
		                        }, 
		                        {
		                            "value": "14500"
		                        }, 
		                        {
		                            "value": "13500"
		                        }, 
		                        {
		                            "value": "15000"
		                        }, 
		                        {
		                            "value": "15500"
		                        }, 
		                        {
		                            "value": "17650"
		                        }, 
		                        {
		                            "value": "19500"
		                        }
		                    ]
		                }, {
		                    "seriesname": "This Week",
		                    "data": [
		                        {
		                            "value": "15400"
		                        }, 
		                        {
		                            "value": "16800"
		                        }, 
		                        {
		                            "value": "18800"
		                        }, 
		                        {
		                            "value": "22400"
		                        }, 
		                        {
		                            "value": "23800"
		                        }, 
		                        {
		                            "value": "25800"
		                        }, 
		                        {
		                            "value": "30800"
		                        }
		                    ]
		                }
		            ]
		        }
		    }).render();
		});
	/* 柱形 */
		FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "column2d",
		        "renderAt": "chart-container2",
		        "width": "100%",
		        "height": "210",
		        "dataFormat": "json",
		        "dataSource": {
		          "chart": {
		        	  /* "bgColor": "#101535",
		              "bgAlpha": "100", */
		              "canvasBgColor":"#101535",	
		              "canvasBgAlpha":"80",
		              "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		              "caption": "Monthly revenue for last year",
		              "subCaption": "Harry's SuperMart",
		              "xAxisName": "Month",
		              "yAxisName": "Revenues (In USD)",
		              "theme": "fint"
		           },
		          "data": [
		              {
		                 "label": "Jan",
		                 "value": "420000"
		              },
		              {
		                 "label": "Feb",
		                 "value": "810000"
		              },
		              {
		                 "label": "Mar",
		                 "value": "720000"
		              },
		              {
		                 "label": "Apr",
		                 "value": "550000"
		              },
		              {
		                 "label": "May",
		                 "value": "910000"
		              },
		              {
		                 "label": "Jun",
		                 "value": "510000"
		              },
		              {
		                 "label": "Jul",
		                 "value": "680000"
		              },
		              {
		                 "label": "Aug",
		                 "value": "620000"
		              },
		              {
		                 "label": "Sep",
		                 "value": "610000"
		              },
		              {
		                 "label": "Oct",
		                 "value": "490000"
		              },
		              {
		                 "label": "Nov",
		                 "value": "900000"
		              },
		              {
		                 "label": "Dec",
		                 "value": "730000"
		              }
		           ]
		        }
		    });
	
		    revenueChart.render();
		})
		
		/* 1.实时车流量 */
		FusionCharts.ready(function () {
		    var visitChart = new FusionCharts({
		        type: 'msline',
		        renderAt: 'chart-container3',
		        width: '100%',
		        height: '255',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Number of visitors last week",
		                "subCaption": "Bakersfield Central vs Los Angeles Topanga",
		                "captionFontSize": "14",
		                "subcaptionFontSize": "14",
		                "subcaptionFontBold": "0",
		                "paletteColors": "#0075c2,#1aaf5d",
		               /*  "bgColor": "#101535",
		                "bgAlpha": "100", */
		                "canvasBgColor":"#101535",
		                "canvasBgAlpha":"80",
		                "rectBgColor":"#101535",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "showBorder": "0",
		                "showShadow": "0",
		                "showCanvasBorder": "0",
		                "usePlotGradientColor": "0",
		                "legendBorderAlpha": "0",
		                "legendShadow": "0",
		                "showAxisLines": "0",
		                "showAlternateHGridColor": "0",
		                "divlineThickness": "1",
		                "divLineIsDashed": "1",
		                "divLineDashLen": "1",
		                "divLineGapLen": "1",
		                "xAxisName": "Day",
		                "showValues": "0"               
		            },
		            "categories": [
		                {
		                    "category": [
		                        { "label": "Mon" }, 
		                        { "label": "Tue" }, 
		                        { "label": "Wed" },
		                        {
		                            "vline": "true",
		                            "lineposition": "0",
		                            "color": "#6baa01",
		                            "labelHAlign": "center",
		                            "labelPosition": "0",
		                            "label": "National holiday",
		                            "dashed":"1"
		                        },
		                        { "label": "Thu" }, 
		                        { "label": "Fri" }, 
		                        { "label": "Sat" }, 
		                        { "label": "Sun" }
		                    ]
		                }
		            ],
		            "dataset": [
		                {
		                    "seriesname": "Bakersfield Central",
		                    "data": [
		                        { "value": "15123" }, 
		                        { "value": "14233" }, 
		                        { "value": "25507" }, 
		                        { "value": "9110" }, 
		                        { "value": "15529" }, 
		                        { "value": "20803" }, 
		                        { "value": "19202" }
		                    ]
		                }, 
		                {
		                    "seriesname": "Los Angeles Topanga",
		                    "data": [
		                        { "value": "13400" }, 
		                        { "value": "12800" }, 
		                        { "value": "22800" }, 
		                        { "value": "12400" }, 
		                        { "value": "15800" }, 
		                        { "value": "19800" }, 
		                        { "value": "21800" }
		                    ]
		                }
		            ], 
		            "trendlines": [
		                {
		                    "line": [
		                        {
		                            "startvalue": "17022",
		                            "color": "#6baa01",
		                            "valueOnRight": "1",
		                            "displayvalue": "Average"
		                        }
		                    ]
		                }
		            ]
		        }
		    }).render();
		});
	
	
		/* 环形 */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'doughnut3d',
		        renderAt: 'chart-container4',
		        width: '100%',
		        height: '210',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Split of Revenue by Product Categories",
		                "subCaption": "Last year",
		                "numberPrefix": "$",
		                "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
		                /* "bgColor": "#101535",
		                "bgAlpha": "100", */
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "showBorder": "0",
		                "use3DLighting": "0",
		                "showShadow": "0",
		                "enableSmartLabels": "0",
		                "startingAngle": "310",
		                "showLabels": "0",
		                "showPercentValues": "1",
		                "showLegend": "1",
		                "legendShadow": "0",
		                "legendBorderAlpha": "0",                                
		                "decimals": "0",
		                "captionFontSize": "14",
		                "subcaptionFontSize": "14",
		                "subcaptionFontBold": "0",
		                "toolTipColor": "#ffffff",
		                "toolTipBorderThickness": "0",
		                "toolTipBgColor": "#000000",
		                "toolTipBgAlpha": "80",
		                "toolTipBorderRadius": "2",
		                "toolTipPadding": "5",
		            },
		            "data": [
		                {
		                    "label": "Food",
		                    "value": "28504"
		                }, 
		                {
		                    "label": "Apparels",
		                    "value": "14633"
		                }, 
		                {
		                    "label": "Electronics",
		                    "value": "10507"
		                }, 
		                {
		                    "label": "Household",
		                    "value": "4910"
		                }
		            ]
		        }
		    }).render();
		});
		/* 门店销售 */
		FusionCharts.ready(function () {
		    var vstrChart = new FusionCharts({
		        type: 'msline',
		        renderAt: 'chart-container5',
		        width: '100%',
		        height: '220',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Website Visitors",
		                "subCaption": "Last week Vs This week",
		                "xAxisName": "Day",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
	                    "canvasBgColor":"#101535",
		                "canvasBgAlpha":"80",
		                "yAxisName": "No. of Visitors",                
		                "theme": "fint",   
		                "showValues" : "0",
		                "divLineIsDashed": "0",
		                "divlineAlpha" : "50"
		            },            
		            "categories": [
		                {
		                    "category": [
		                        {
		                            "label": "Mon"
		                        }, 
		                        {
		                            "label": "Tue"
		                        }, 
		                        {
		                            "label": "Wed"
		                        }, 
		                        {
		                            "label": "Thu"
		                        }, 
		                        {
		                            "label": "Fri"
		                        }, 
		                        {
		                            "label": "Sat"
		                        }, 
		                        {
		                            "label": "Sun"
		                        }
		                    ]
		                }
		            ],
		            
		            "dataset": [
		                {
		                    "seriesname": "Last Week",
		                    "data": [
		                        {
		                            "value": "13000"
		                        }, 
		                        {
		                            "value": "14500"
		                        }, 
		                        {
		                            "value": "13500"
		                        }, 
		                        {
		                            "value": "15000"
		                        }, 
		                        {
		                            "value": "15500"
		                        }, 
		                        {
		                            "value": "17650"
		                        }, 
		                        {
		                            "value": "19500"
		                        }
		                    ]
		                }, 
		                {
		                    "seriesname": "This Week",
		                    "data": [
		                        {
		                            "value": "15400"
		                        }, 
		                        {
		                            "value": "16800"
		                        }, 
		                        {
		                            "value": "18800"
		                        }, 
		                        {
		                            "value": "22400"
		                        }, 
		                        {
		                            "value": "23800"
		                        }, 
		                        {
		                            "value": "25800"
		                        }, 
		                        {
		                            "value": "30800"
		                        }
		                    ]
		                }
		            ]
		        }
		    }).render();
		});
		/*  */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'doughnut2d',
		        renderAt: 'chart-container6',
		        width: '100%',
		        height: '255',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Split of Revenue by Product Categories",
		                "subCaption": "Last year",
		                "numberPrefix": "$",
		                "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
	                    "canvasBgColor":"#101535",
		                "canvasBgAlpha":"80",
		                "showBorder": "0",
		                "use3DLighting": "0",
		                "showShadow": "0",
		                "enableSmartLabels": "0",
		                "startingAngle": "310",
		                "showLabels": "0",
		                "showPercentValues": "1",
		                "showLegend": "1",
		                "legendShadow": "0",
		                "legendBorderAlpha": "0",
		                "defaultCenterLabel": "Total revenue: $64.08K",
		                "centerLabel": "Revenue from $label: $value",
		                "centerLabelBold": "1",
		                "showTooltip": "0",
		                "decimals": "0",
		                "captionFontSize": "14",
		                "subcaptionFontSize": "14",
		                "subcaptionFontBold": "0"
		            },
		            "data": [
		                {
		                    "label": "Food",
		                    "value": "28504"
		                }, 
		                {
		                    "label": "Apparels",
		                    "value": "14633"
		                }, 
		                {
		                    "label": "Electronics",
		                    "value": "10507"
		                }, 
		                {
		                    "label": "Household",
		                    "value": "4910"
		                }
		            ]
		        }
		    }).render();
		});
		/*  */
		FusionCharts.ready(function() {
		    var revenueChart = new FusionCharts({
		        type: 'stackedcolumn2d',
		        renderAt: 'chart-container7',
		        width: '100%',
		        height: '210',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "theme": "fint",
		                "caption": "Revenue split by product category",
		                "subCaption": "For current year",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
	                    "canvasBgColor":"#101535",
		                "canvasBgAlpha":"80",
		                "xAxisname": "Quarter",
		                "yAxisName": "Revenues (In USD)",
		                "showSum": "1",
		                "numberPrefix": "$"
		            },
		            "categories": [
		                {
		                    "category": [
		                        {"label": "Q1"},
		                        {"label": "Q2"},
		                        {"label": "Q3"},
		                        {"label": "Q4"}
		                    ]
		                }
		            ],
		            "dataset": [
		                {
		                    "seriesname": "Food Products",
		                    "data": [
		                        {"value": "11000"},
		                        {"value": "15000"},
		                        {"value": "13500"},
		                        {"value": "15000"}
		                    ]
		                }, 
		                {
		                    "seriesname": "Non-Food Products",
		                    "data": [
		                        {"value": "11400"},
		                        {"value": "14800"},
		                        {"value": "8300"},
		                        {"value": "11800"}
		                    ]
		                }
		            ]
		        }
		    }).render();
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			pageSetUp();
			
		});
	</script>
</body>	
</html>