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
	.data-proportion,.general-overview,.data-analysis,.data-area,.usage,.national-distribution{
		width:100%;
		/* height:400px; */
		border:2px solid #10246A;
		border-radius:5px;
		box-shadow:3px 3px 4px #10246A inset;
		color:#fff;
		margin-bottom:10px;
		position:relative;	
		padding:10px 20px;
	}
	.usage-content .usage-list{
		float:left;
		width:33%;
	}
	.data-analysis >.table-striped,.data-analysis >.table-striped >tbody >tr >td,.data-analysis >.table-striped >thead >tr >th{
		border:2px solid #113467;
	}
	.data-analysis >.table-striped >tbody >tr:nth-child(even){
		background-color:#113467;
	}
	.data-analysis >.table-striped >tbody >tr:nth-child(odd){
		background-color:transparent;
	}
	.data-analysis >.table-striped >thead >tr{
		background-color:#113467;
		background-image:none;
	}
	
	
	
	
	
	
	
	
	.title-text{
		color:#fcfcfc;
		font-size:16px;
	}
	.line-div{
		border-top:1px solid #4f4f4f;
		margin:20px 0;
	}
</style>
</head>
<body class="big-data">
	<div class="big-data-header">
		<div class="tab-page">数据占比</div><span class="line-line"></span>
		<div class="tab-page">数据面积图</div><span class="line-line"></span>
		<div class="tab-page">系统使用情况</div><span class="line-line"></span>
		
		<div class="tab-page-main">宁海旅游运营监控平台</div>
		<div class="tab-page">全国分布图</div><span class="line-line"></span>
		<div class="tab-page">数据分析</div><span class="line-line"></span>
		<div class="tab-page">整体概览</div><span class="line-line"></span>
		
	</div>
	<div class="big-data-content">
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<div class="data-proportion">
					<p class="title-text">数据占比</p>
					<div id="chart-container"></div>
					<div class="line-div"></div>
					<div id="chart-container2"></div>
				</div>
				
			</div>
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4" style="padding-left:0;padding-right:0;">
				<div class="general-overview" id="chart-container4"></div>
			</div>
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<div class="data-analysis">
					<p class="title-text">数据分析</p>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>分析数据详细标题名称</th>
								<th>标题名称</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>数据标题名称</td>
								<td>23%</td>
							</tr>
							<tr>
								<td>数据标题名称</td>
								<td>58%</td>
							</tr>
							<tr>
								<td>数据标题名称</td>
								<td>35%</td>
							</tr>
							<tr>
								<td>数据标题名称</td>
								<td>42%</td>
							</tr>
							<tr>
								<td>数据名称标题</td>
								<td>22%</td>
							</tr>
							<tr>
								<td>数据标题名称</td>
								<td>44%</td>
							</tr>
							<tr>
								<td>数据名称标题</td>
								<td>33%</td>
							</tr>
							<tr>
								<td>数据标题名称</td>
								<td>53%</td>
							</tr>
							<tr>
								<td>数据标题名称</td>
								<td>26%</td>
							</tr>
						</tbody>
					</table>
					<p style="text-align:right">数据更新时间：2018-01-04</p>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<div class="data-area">
					<p class="title-text">数据面积图</p>
					<div id="chart-container3"></div>
				</div>
				
			</div>
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4" style="padding-left:0;padding-right:0;">
				<div class="usage">
					<p class="title-text">系统使用情况</p>
					<div class="row">
						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="easy-pie-chart txt-color-pinkDark easyPieChart" data-percent="46" data-pie-size="160">
									<span class="percent percent-sign txt-color-pinkDark font-lg semi-bold">46</span>
							</div> 
							<div style="text-align:center;">硬件1的使用率</div>
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="easy-pie-chart txt-color-pinkDark easyPieChart" data-percent="46" data-pie-size="160">
								<span class="percent percent-sign txt-color-pinkDark font-lg semi-bold">46</span>
							</div> 
							<div style="text-align:center;">硬件2的使用率</div>
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="easy-pie-chart txt-color-pinkDark easyPieChart" data-percent="46" data-pie-size="160">
								<span class="percent percent-sign txt-color-pinkDark font-lg semi-bold">46</span>
							</div> 
							<div style="text-align:center;">硬件3的使用率</div>
						</div>
					</div>
					<div class="noticeBox" style="margin-top:10px;">
						<p>注意事项：</p>
						<p>1.当硬件1的使用效率超过<span style="color:#F8BC02;">&nbsp;80%&nbsp;</span>的时候，采取这样的方法去控制</p>
						<p>2.当硬件2的使用效率超过施去控制<span style="color:#09B7EA">&nbsp;80%&nbsp;</span>的时候，采取这样的方法去控制</p>
						<p>3.当硬件1的使用效率超过<span style="color:#E42889">&nbsp;80%&nbsp;</span>的时候，采取这样的方法去控制</p>
					</div>
				</div>
				
			</div>
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<div class="national-distribution">
					<p class="title-text">全国分布图</p>
					<div class="row">
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
							<div style="height:30px;"></div>
							<p>山西省太原市：</p>
							<p>数据一</p>
							<p>全国占有比为<span style="color:#E42889">&nbsp;23%</span></p>
							<p>数据二</p>
							<p>全国占有比为<span style="color:#E42889">&nbsp;33%</span></p>
							<p>数据三</p>
							<p>全国占有比为<span style="color:#E42889">&nbsp;29%</span></p>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
							<div id="chart-container5"></div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			pageSetUp();
			
		});
	</script>
	<script>
		/* 饼图 */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'pie2d',
		        renderAt: 'chart-container',
		        width: '100%',
		        height: '200',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Letters Used in a Novel",
		                "formatnumberscale": "0",
		                "showBorder": "0",
		                "showLegend": "1",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "theme" : "fint",
		                "showPercentValues":"1",
		                "showPercentInToolTip":"0",
		                //Setting legend to appear on right side
		                "legendPosition" : "right",
		                //Caption for legend
		                "legendCaption" : "Alphabet Used: ",
		                //Customization for legend scroll bar cosmetics
		                "legendScrollBgColor": "#cccccc",
		                "legendScrollBarColor": "#999999"
		            },
		                "data": [{
		                    "label": "数据1",
		                    "value": "91852"
		                },{
		                "label": "数据2",
		                    "value": "51852"
		            }, {
		                "label": "数据3",
		                    "value": "88168"
		            }, {
		                "label": "数据4",
		                    "value": "73897"
		            }, {
		                "label": "数据5",
		                    "value": "93933"
		            }, {
		                "label": "数据6",
		                    "value": "19289"
		            }, ]
		        }
		    });
		
		    revenueChart.render();
		});
		/* 空心饼图 */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'doughnut2d',
		        renderAt: 'chart-container2',
		        width: '100%',
		        height: '200',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Split of Revenue by Product Categories",
		                "subCaption": "Los Angeles Topanga - Last month",
		                "numberPrefix": "$",                
		                "startingAngle": "310",
		                "decimals": "0",    
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "defaultCenterLabel": "Total revenue: $60K",
		                "centerLabel": "Revenue from $label: $value",                
		                "theme": "fint"                
		            },
		            "data": [{
		                "label": "Food",
		                "value": "28504"
		            }, {
		                "label": "Apparels",
		                "value": "14633"
		            }, {
		                "label": "Electronics",
		                "value": "10507"
		            }, {
		                "label": "Household",
		                "value": "4910"
		            }]
		        }
		    }).render();
		});
		/* 折线面积图表 */
		FusionCharts.ready(function () {
			    var siteTrafficChart = new FusionCharts({
			        type: 'inversemsarea',
			        renderAt: 'chart-container3',
			        width: '100%',
			        height: '300',
			        dataFormat: 'json',
			        dataSource: {
			            "chart": {
			                "caption": "Daily bounce rate",                
			                "subCaption": "Last week",
			                "xAxisName": "Day",
			                "yAxisName": "Percentage",
			                "numberSuffix": "%",
			                "showBorder": "0",
			                "paletteColors": "#0075c2,#1aaf5d",
			               /*  "bgColor": "#ffffff",   */
			                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
			                "canvasBgColor":"#101535",	
				              "canvasBgAlpha":"80",
			                'usePlotGradientColor': "0",
			                "plotFillAlpha": "50",
			                "showCanvasBorder": "0",                                                                
			                "LegendShadow": "0",
			                "legendBorderAlpha": "0",                
			                "showXAxisLine": "1",                
			                "axisLineAlpha": "40",
			                "divlineColor": "#999999",
			                "divlineThickness": "1",
			                "divLineIsDashed": "1",
			                "divLineDashLen": "1",
			                "divLineGapLen": "1",
			                "showAlternateHgridColor": "0",
			                "showValues": "0",
			                "baseFontColor": "#333333",
			                "baseFont": "Helvetica Neue,Arial",
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
			                        }]
			                }],
			            "dataset": [
			                {
			                    "seriesname": "food.hsm.com",                
			                    "data": [
			                        {
			                            "value": "27"
			                        },
			                        {
			                            "value": "22"
			                        },
			                        {
			                            "value": "25"
			                        },
			                        {
			                            "value": "27"
			                        },
			                        {
			                            "value": "21"
			                        },
			                        {
			                            "value": "29"
			                        },
			                        {
			                            "value": "22"
			                        }
			                    ]
			                },
			                {
			                    "seriesname": "cloth.hsm.com",                
			                    "data": [
			                        {
			                            "value": "42"
			                        },
			                        {
			                            "value": "38"
			                        },
			                        {
			                            "value": "39"
			                        },
			                        {
			                            "value": "36"
			                        },
			                        {
			                            "value": "43"
			                        },
			                        {
			                            "value": "44"
			                        },
			                        {
			                            "value": "35"
			                        }
			                    ]
			                }                               
			            ]
			        }
			    }).render();
			});
		
			/* 柱形图 */
			FusionCharts.ready(function() {
			    var revenueChart = new FusionCharts({
			        type: 'column2d',
			        renderAt: 'chart-container4',
			        width: '100%',
			        height: '470',
			        dataFormat: 'json',
			        dataSource: {
			            "chart": {
			                "caption": "Monthly Revenue",
			                "subCaption": "Last year",
			                "xAxisName": "Month",
			                "canvasBgColor":"#101535",	
				              "canvasBgAlpha":"80",
				              "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
			                "yAxisName": "Amount (In USD)",
			                "numberPrefix": "$",
			                "theme": "fint"
			            },
			            "data": [
			                {
			                    "label": "Jan",
			                    "value": "420000",
			                    //Using color attribute to specifically add color to each data plot
			                    "color": "#008ee4"
			                },
			                {
			                    "label": "Feb",
			                    "value": "810000",
			                    "color": "#008ee4"
			                },
			                {
			                    "label": "Mar",
			                    "value": "720000",
			                    "color": "#008ee4"
			                },
			                {
			                    "label": "Apr",
			                    "value": "550000",
			                    "color": "#9b59b6"
			                },
			                {
			                    "label": "May",
			                    "value": "910000",
			                    "color": "#9b59b6"
			                },
			                {
			                    "label": "Jun",
			                    "value": "510000",
			                    "color": "#9b59b6"
			                },
			                {
			                    "label": "Jul",
			                    "value": "680000",
			                    "color": "#6baa01"
			                },
			                {
			                    "label": "Aug",
			                    "value": "620000",
			                    "color": "#6baa01"
			                },
			                {
			                    "label": "Sep",
			                    "value": "610000",
			                    "color": "#6baa01"
			                },
			                {
			                    "label": "Oct",
			                    "value": "490000",
			                    "color": "#e44a00"
			                },
			                {
			                    "label": "Nov",
			                    "value": "900000",
			                    "color": "#e44a00"
			                },
			                {
			                    "label": "Dec",
			                    "value": "730000",
			                    "color": "#e44a00"
			                }
			            ]
			        }
			    }).render();
			
			});
			/* 地图 */
			FusionCharts.ready(function () {
			    var populationMap = new FusionCharts({
			        type: 'maps/world',
			        renderAt: 'chart-container5',
			        width: '100%',
			        height: '300',
			        dataFormat: 'json',
			        dataSource: {
			            "chart": {
			                "caption": "World Population Density", 
			                "subcaption": "Number of people per Square Mile",
			                "theme": "fint",
			                "formatNumberScale":"0",
			                "showLabels": "1",
			                "nullEntityColor": "#C2C2D6",
			                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
			                "nullEntityAlpha":"50",
			                "hoverOnNull": "0",
			                "useSNameInLabels" : "0",
			                "entityFillColor" : "#A8A8A8",
			                "entityFillHoverColor" : "#E5E5E9"
			            },
			            "colorrange": {
			                "startlabel": "Low",
			                "endlabel": "High",
			                "code": "#e44a00",
			               "minvalue": "0",
			                "gradient": "1",
			                "color": [
			                    {
			                        "maxvalue": "60",
			                        "displayvalue": "Average",
			                        "code": "#f8bd19"
			                    },
			                    {
			                        "maxvalue": "300",
			                        "code": "#6baa01"
			                    }
			                ]
			            },
			            
			            "data": [
			                {
			                    "id": "NA",
			                    "displayValue": "North America",
			                    "value": "57.2",
			                    "link": "newchart-json-NA"
			                },
			                  {
			                    "id": "SA",
			                    "value": "57.1",
			                    "showLabel" : "1",
			                },
			                {
			                    "id": "AS",
			                    "value": "247",
			                    "showLabel" : "1",
			                },
			                {
			                    "id": "EU",
			                    "value": "188.5",
			                    "showLabel" : "1",
			                },
			                {
			                    "id": "AF",
			                    "value": "87.2",
			                    "showLabel" : "1",
			                },
			                {
			                    "id": "AU",
			                    "value": "8.32",
			                    "showLabel" : "1",
			                }
			
			            ],
			              "linkeddata": [{
			                "id": "NA",
			                "linkedchart": {
			
			            "chart": {
			                "caption": "US Population Density by State",
			                "subcaption": "Number of people per Square Mile",
			                "entityFillHoverColor": "#cccccc",
			                "showLabels": "1",
			                "entityFillColor" : "#A8A8A8",
			                "entityFillHoverColor" : "#E5E5E9",
			                "theme": "fint",
			                "showBorder" : "1",
			                "bordercolor": "#FFFFFF",
			                "entityborderThickness": "3"
			                
			            },
			           "colorrange": {
			                "startlabel": "Low",
			                "endlabel": "High",
			                "code": "#e44a00",
			               "minvalue": "0",
			                "gradient": "1",
			                "color": [
			                    {
			                        "maxvalue": "150",
			                        "displayvalue": "Average",
			                        "code": "#f8bd19"
			                    },
			                    {
			                        "maxvalue": "600",
			                        "code": "#6baa01"
			                    }
			                ]
			            },
			           
			               "data": [
			        {
			            "id": "HI",
			            "value": "99"
			        },
			        {
			            "id": "DC",
			            "value": "99"
			        },
			        {
			            "id": "MD",
			            "value": "90"
			        },
			        {
			            "id": "DE",
			            "value": "96"
			        },
			        {
			            "id": "RI",
			            "value": "90"
			        },
			        {
			            "id": "WA",
			            "value": "228"
			        },
			        {
			            "id": "OR",
			            "value": "105"
			        },
			        {
			            "id": "CA",
			            "value": "221"
			        },
			        {
			            "id": "AK",
			            "value": "260"
			        },
			        {
			            "id": "ID",
			            "value": "396"
			        },
			        {
			            "id": "NV",
			            "value": "169"
			        },
			        {
			            "id": "AZ",
			            "value": "435"
			        },
			        {
			            "id": "MT",
			            "value": "445"
			        },
			        {
			            "id": "WY",
			            "value": "455"
			        },
			        {
			            "id": "UT",
			            "value": "227"
			        },
			        {
			            "id": "CO",
			            "value": "214"
			        },
			        {
			            "id": "NM",
			            "value": "196"
			        },
			        {
			            "id": "ND",
			            "value": "331"
			        },
			        {
			            "id": "SD",
			            "value": "374"
			        },
			        {
			            "id": "NE",
			            "value": "329"
			        },
			        {
			            "id": "KS",
			            "value": "231"
			        },
			        {
			            "id": "OK",
			            "value": "150"
			        },
			        {
			            "id": "TX",
			            "value": "202"
			        },
			        {
			            "id": "MN",
			            "value": "137"
			        },
			        {
			            "id": "IA",
			            "value": "143"
			        },
			        {
			            "id": "MO",
			            "value": "424"
			        },
			        {
			            "id": "AR",
			            "value": "485"
			        },
			        {
			            "id": "LA",
			            "value": "124"
			        },
			        {
			            "id": "WI",
			            "value": "181"
			        },
			        {
			            "id": "IL",
			            "value": "120"
			        },
			        {
			            "id": "KY",
			            "value": "309"
			        },
			        {
			            "id": "TN",
			            "value": "250"
			        },
			        {
			            "id": "MS",
			            "value": "351"
			        },
			        {
			            "id": "AL",
			            "value": "271"
			        },
			        {
			            "id": "GA",
			            "value": "124"
			        },
			        {
			            "id": "MI",
			            "value": "120"
			        },
			        {
			            "id": "IN",
			            "value": "205"
			        },
			        {
			            "id": "OH",
			            "value": "476"
			        },
			        {
			            "id": "PA",
			            "value": "445"
			        },
			        {
			            "id": "NY",
			            "value": "369"
			        },
			        {
			            "id": "VT",
			            "value": "322"
			        },
			        {
			            "id": "NH",
			            "value": "216"
			        },
			        {
			            "id": "ME",
			            "value": "404"
			        },
			        {
			            "id": "MA",
			            "value": "165"
			        },
			        {
			            "id": "CT",
			            "value": "316"
			        },
			        {
			            "id": "NJ",
			            "value": "553"
			        },
			        {
			            "id": "WV",
			            "value": "560"
			        },
			        {
			            "id": "VA",
			            "value": "565"
			        },
			        {
			            "id": "NC",
			            "value": "534"
			        },
			        {
			            "id": "SC",
			            "value": "503"
			        },
			        {
			            "id": "FL",
			            "value": "503"
			        }
			    ]
			        
			                    
			               }
			                  
			              }]
			        }
			
			    });
			        populationMap.configureLink({
			        "type": "maps/usa"}, 0); 
			    populationMap.render();    
			
			});
			
	</script>
</body>	
</html>