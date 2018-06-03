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
<script type="text/javascript" src="${nvix}/vixntcommon/base/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="${nvix}/vixntcommon/base/js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
<script src="${nvix}/vixntcommon/base/js/libs/jquery-2.1.1.min.js" type="text/javascript"></script>

<script src="${nvix}/vixntcommon/base/js/libs/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
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
		<div class="tab-page-main">北京智慧城市运营中心</div>
	</div>
	<div class="big-data-content">
		<div class="row">
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
				<div class="weather">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;2016年总人口</p>
					<div class="total-population">
						<div class="row">
							<div class="col-xs-5 text-right"><img src="${nvix}/vixntcommon/base/img/images/zongrenkou.png"></div>
							<div class="col-xs-7"><span style="color:#D8C624;font-size:40px;">4758</span>万人</div>
						</div>
						<div style="border-top:1px solid #a4a4a4;margin:25px 0;"></div>
						<div class="row" style="margin-top:20px;">
							<div class="col-xs-6">
								<div class="col-xs-5"><img src="${nvix}/vixntcommon/base/img/images/icon-man.png" width="100%"></div>
								<div class="col-xs-7">
									<p class="border-bt padding-bt margin-bt"><span style="color:#36B1D6;font-size:22px;">4758</span>万人</p>
									<p><span style="color:#36B1D6;font-size:22px;">80</span>%</p>
								</div>
							</div>
							<div class="col-xs-6">
								
								<div class="col-xs-7">
									<p class="border-bt padding-bt margin-bt"><span style="color:#D84D4C;font-size:22px;">4758</span>万人</p>
									<p><span style="color:#D84D4C;font-size:22px;">80</span>%</p>
								</div>
								<div class="col-xs-5"><img src="${nvix}/vixntcommon/base/img/images/icon-woman.png" width="100%"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="tourist">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;2016年人口年龄变化</p>
					<div id="chart-container1"></div>
				</div>
				<div class="marketing">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;2016年各年龄段人口分布</p>
					<div id="chart-container2"></div>
				</div>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="padding-left:0;padding-right:0;">
				<div class="dataMain">	
					<!-- 2012年 -->
					<div class="population-str" style="display:none;">
						<p class="title-text">2012年人口结构分析</p>
						<div class="row">
							<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
								<div id="chart-container5-1"></div>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
								<div style="height:30px;"></div>
								<p class="title-population">2012年总人口排名TOP10</p>
								<div class="row">
									<div class="col-xs-4">
										
									</div>
									<div class="col-xs-8">
										<img src="${nvix}/vixntcommon/base/img/images/icon-man.png" width=30px height=30px>
										<img src="${nvix}/vixntcommon/base/img/images/icon-woman.png" width=30px height=30px>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										乌鲁木齐
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 40%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 30%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										深圳市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 45%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										广州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 35%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										福州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 30%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										苏州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										杭州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 25%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 17%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										天津市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										上海市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 19%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										北京市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 40%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										吉林市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>	
					
					<!-- 2013年 -->
					<div class="population-str" style="display:none;">
						<p class="title-text">2013年人口结构分析</p>
						<div class="row">
							<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
								<div id="chart-container5-2"></div>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
								<div style="height:30px;"></div>
								<p class="title-population">2013年总人口排名TOP10</p>
								<div class="row">
									<div class="col-xs-4">
										
									</div>
									<div class="col-xs-8">
										<img src="${nvix}/vixntcommon/base/img/images/icon-man.png" width=30px height=30px>
										<img src="${nvix}/vixntcommon/base/img/images/icon-woman.png" width=30px height=30px>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										乌鲁木齐
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 40%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 30%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										深圳市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 45%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										广州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 35%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										福州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 30%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										苏州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										杭州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 25%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 17%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										天津市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										上海市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 19%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										北京市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 40%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										吉林市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 2014年 -->
					<div class="population-str" style="display:none;">
						<p class="title-text">2014年人口结构分析</p>
						<div class="row">
							<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
								<div id="chart-container5-3"></div>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
								<div style="height:30px;"></div>
								<p class="title-population">2014年总人口排名TOP10</p>
								<div class="row">
									<div class="col-xs-4">
										
									</div>
									<div class="col-xs-8">
										<img src="${nvix}/vixntcommon/base/img/images/icon-man.png" width=30px height=30px>
										<img src="${nvix}/vixntcommon/base/img/images/icon-woman.png" width=30px height=30px>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										乌鲁木齐
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 40%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 30%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										深圳市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 45%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										广州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 35%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										福州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 30%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										苏州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										杭州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 25%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 17%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										天津市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										上海市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 19%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										北京市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 40%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										吉林市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 2015年 -->
					<div class="population-str" style="display:none;">
						<p class="title-text">2015年人口结构分析</p>
						<div class="row">
							<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
								<div id="chart-container5-4"></div>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
								<div style="height:30px;"></div>
								<p class="title-population">2015年总人口排名TOP10</p>
								<div class="row">
									<div class="col-xs-4">
										
									</div>
									<div class="col-xs-8">
										<img src="${nvix}/vixntcommon/base/img/images/icon-man.png" width=30px height=30px>
										<img src="${nvix}/vixntcommon/base/img/images/icon-woman.png" width=30px height=30px>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										乌鲁木齐
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 40%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 30%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										深圳市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 45%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										广州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 35%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										福州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 30%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										苏州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										杭州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 25%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 17%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										天津市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										上海市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 19%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										北京市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 40%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										吉林市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 2016年 -->
					<div class="population-str">
						<p class="title-text">2016年人口结构分析</p>
						<div class="row">
							<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
								<div id="chart-container5-5"></div>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
								<div style="height:30px;"></div>
								<p class="title-population">2016年总人口排名TOP10</p>
								<div class="row">
									<div class="col-xs-4">
										
									</div>
									<div class="col-xs-8">
										<img src="${nvix}/vixntcommon/base/img/images/icon-man.png" width=30px height=30px>
										<img src="${nvix}/vixntcommon/base/img/images/icon-woman.png" width=30px height=30px>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										乌鲁木齐
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 40%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 30%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										深圳市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 45%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										广州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 35%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										福州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 30%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										苏州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										杭州市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 25%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 17%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										天津市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										上海市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 28%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 19%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										北京市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 40%"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 text-right">
										吉林市
									</div>
									<div class="col-xs-8">
										<div class="progress progress-xs" style="margin-top:8px;margin-bottom:8px;">
											<div class="progress-bar bg-color-red1" style="width: 50%"></div>
											<div class="progress-bar bg-color-blue1" style="width: 20%"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-years">
						<span class="tab-year">2012年</span>
						<span class="tab-year">2013年</span>
						<span class="tab-year">2014年</span>
						<span class="tab-year">2015年</span>
						<span class="tab-year cur">2016年</span>
					</div>
					<span class="left-bottom"></span>
					<span class="left-top"></span>
					<span class="right-bottom"></span>
					<span class="right-top"></span>
				</div>
				<div class="childAge">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;2016年各镇人口总数年龄段分析</p>
					<div id="chart-container3"></div>
				</div>
				
			</div>
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
				<div class="industry">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;近5年人口规则变化</p>
					<div id="chart-container4"></div>
				</div>
				<div class="resources">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;近5年人口规模变化（按年龄）</p>
					<div id="chart-container5"></div>
				</div>
				<div class="sentiment">
					<p class="title-text"><i class="fa fa-angle-double-right"></i>&nbsp;&nbsp;近5年新生婴儿变化</p>
					<div id="chart-container6"></div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
		
		/*****************************************************************/
		/* 人口年龄变化 */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'stackedbar2d',
		        renderAt: 'chart-container1',
		        width: '100%',
		        height: '265',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Product-wise quarterly revenue in current year",
		                "subCaption": "Harry's SuperMart",
		                "xAxisname": "Quarter",
		                "yAxisName": "Revenue (In USD)",
		                "numberPrefix": "$",
		                "paletteColors": "#0075c2,#1aaf5d",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "showBorder": "0",
		                "canvasBgColor":"#101535",	
			            "canvasBgAlpha":"80",
		                "showCanvasBorder": "0",
		                "usePlotGradientColor": "0",
		                "plotBorderAlpha": "10",
		                "legendBorderAlpha": "0",
		                "legendShadow": "0",
		                "valueFontColor": "#ffffff",                
		                "showXAxisLine": "1",
		                "xAxisLineColor": "#999999",
		                "divlineColor": "#999999",               
		                "divLineIsDashed": "1",
		                "showAlternateVGridColor": "0",
		                "subcaptionFontBold": "0",
		                "subcaptionFontSize": "14",
		                "showHoverEffect":"1"
		                
		            },
		            "categories": [
		                {
		                    "category": [
		                        {
		                            "label": "Q1"
		                        },
		                        {
		                            "label": "Q2"
		                        },
		                        {
		                            "label": "Q3"
		                        },
		                        {
		                            "label": "Q4"
		                        }
		                    ]
		                }
		            ],
		            "dataset": [
		                {
		                    "seriesname": "Food Products",
		                    "data": [
		                        {
		                            "value": "121000"
		                        },
		                        {
		                            "value": "135000"
		                        },
		                        {
		                            "value": "123500"
		                        },
		                        {
		                            "value": "145000"
		                        }
		                    ]
		                },
		                {
		                    "seriesname": "Non-Food Products",
		                    "data": [
		                        {
		                            "value": "131400"
		                        },
		                        {
		                            "value": "154800"
		                        },
		                        {
		                            "value": "98300"
		                        },
		                        {
		                            "value": "131800"
		                        }
		                    ]
		                }
		            ]
		        }
		    }).render();    
		});
		
		/* 各年龄段人口分布 */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'pie2d',
		        renderAt: 'chart-container2',
		        width: '100%',
		        height: '265',
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
		                    "label": "14岁以下",
		                    "value": "91852"
		                },{
		                "label": "14-65岁",
		                    "value": "51852"
		            }, {
		                "label": "65岁以上",
		                    "value": "88168"
		            }]
		        }
		    });
		
		    revenueChart.render();
		});
		/* 各镇人口总数年龄段分析 */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'stackedcolumn2dline',
		        renderAt: 'chart-container3',
		        width: '100%',
		        height: '300',
		        dataFormat: 'json',
		        dataSource: {            
		            "chart": {
		                "caption": "Product-wise quarterly revenue Vs profit in last year",
		                "subCaption": "Harry's SuperMart",
		                "xAxisname": "Quarter",
		                "yAxisName": "Revenue (In USD)",
		                "numberPrefix": "$",
		                "paletteColors": "#0075c2,#1aaf5d,#f2c500",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "canvasBgColor":"#101535",	
			            "canvasBgAlpha":"80",
			            "showBorder": "0",             
		                "showCanvasBorder": "0",
		                "usePlotGradientColor": "0",
		                "plotBorderAlpha": "10",
		                "legendBorderAlpha": "0",
		                "legendShadow": "0",
		                "legendBgAlpha": "0",
		                "valueFontColor": "#ffffff",               
		                "showXAxisLine": "1",
		                "xAxisLineColor": "#999999",
		                "divlineColor": "#999999",               
		                "divLineIsDashed": "1",
		                "showAlternateHGridColor": "0",
		                "subcaptionFontBold": "0",
		                "subcaptionFontSize": "14",
		                "showHoverEffect":"1"
		            },
		            "categories": [
		                {
		                    "category": [
		                        {
		                            "label": "晋州镇"
		                        },
		                        {
		                            "label": "兴安镇"
		                        },
		                        {
		                            "label": "南津镇"
		                        },
		                        {
		                            "label": "桃源镇"
		                        },
		                        {
		                            "label": "马屿镇"
		                        },
		                        {
		                            "label": "上庄镇"
		                        },
		                        {
		                            "label": "栾城镇"
		                        },
		                        {
		                            "label": "上碑镇"
		                        },
		                        {
		                            "label": "冶河镇"
		                        },
		                        {
		                            "label": "灵寿镇"
		                        },
		                        {
		                            "label": "北苏镇"
		                        }
		                    ]
		                }
		            ],
		            "dataset": [
		                {
		                    "seriesname": "Food Products",
		                    "data": [
		                        {
		                            "value": "110000"
		                        },
		                        {
		                            "value": "150000"
		                        },
		                        {
		                            "value": "135000"
		                        },
		                        {
		                            "value": "90000"
		                        },
		                        {
		                            "value": "80000"
		                        },
		                        {
		                            "value": "150000"
		                        },
		                        {
		                            "value": "128000"
		                        },
		                        {
		                            "value": "130000"
		                        },
		                        {
		                            "value": "140000"
		                        },
		                        {
		                            "value": "100000"
		                        },
		                        {
		                            "value": "90000"
		                        }
		                    ]
		                },
		                {
		                    "seriesname": "Non-Food Products",
		                    "data": [
		                        {
		                            "value": "114000"
		                        },
		                        {
		                            "value": "148000"
		                        },
		                        {
		                            "value": "83000"
		                        },
		                        {
		                            "value": "118000"
		                        },
		                        {
		                            "value": "130400"
		                        },
		                        {
		                            "value": "150700"
		                        },
		                        {
		                            "value": "140000"
		                        },
		                        {
		                            "value": "120900"
		                        },
		                        {
		                            "value": "170000"
		                        },
		                        {
		                            "value": "140800"
		                        },
		                        {
		                            "value": "150000"
		                        }
		                    ]
		                },
		                {
		                    "seriesname": "Profit",
		                    "renderAs": "line",
		                    "showValues": "0",
		                    "data": [
		                        {
		                            "value": "24000"
		                        },
		                        {
		                            "value": "45000"
		                        },
		                        {
		                            "value": "23000"
		                        },
		                        {
		                            "value": "38000"
		                        },
		                        {
		                            "value": "90000"
		                        },
		                        {
		                            "value": "80900"
		                        },
		                        {
		                            "value": "79000"
		                        },
		                        {
		                            "value": "90000"
		                        },
		                        {
		                            "value": "100000"
		                        },
		                        {
		                            "value": "130000"
		                        },
		                        {
		                            "value": "150000"
		                        }
		                    ]
		                }
		            ]
		        }
		    }).render();    
		});
		
		/* 近5年人口变化 */
		FusionCharts.ready(function () {
		    var siteTrafficChart = new FusionCharts({
		        type: 'inversemsarea',
		        renderAt: 'chart-container4',
		        width: '100%',
		        height: '240',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Daily bounce rate",                
		                "subCaption": "Last week",
		                "xAxisName": "Day",
		                "yAxisName": "Percentage",
		                "numberSuffix": "%",
		                "showBorder": "0",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "canvasBgColor":"#101535",	
			            "canvasBgAlpha":"80",
		                "paletteColors": "#0075c2,#1aaf5d",
		                "bgColor": "#ffffff",                
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
		/* 近5年人口规模变化年龄 */
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'stackedcolumn2d',
		        renderAt: 'chart-container5',
		        width: '100%',
		        height: '240',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Revenue split by product category",
		                "subCaption": "For current year",
		                "xAxisname": "Quarter",
		                "canvasBgColor":"#101535",	
			            "canvasBgAlpha":"80",
		                "yAxisName": "Revenues (In USD)",
		                "showSum": "1",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "numberPrefix": "$",
		                "theme": "fint"
		            },
		
		            "categories": [{
		                "category": [{
		                    "label": "2012年"
		                }, {
		                    "label": "2013年"
		                }, {
		                    "label": "2014年"
		                }, {
		                    "label": "2015年"
		                }, {
		                    "label": "2016年"
		                }]
		            }],
		
		            "dataset": [{
		                "seriesname": "0-14岁",
		                    "data": [{
		                    "value": "11000"
		                }, {
		                    "value": "15000"
		                }, {
		                    "value": "13500"
		                }, {
		                    "value": "15000"
		                }]
		            }, {
		                "seriesname": "14-65岁",
		                    "data": [{
		                    "value": "11400"
		                }, {
		                    "value": "14800"
		                }, {
		                    "value": "8300"
		                }, {
		                    "value": "11800"
		                }, {
		                    "value": "11800"
		                }]
		            }, {
		                "seriesname": "65岁以上",
	                    "data": [{
	                    "value": "11400"
	                }, {
	                    "value": "14800"
	                }, {
	                    "value": "8300"
	                }, {
	                    "value": "11800"
	                }, {
	                    "value": "11800"
	                }]
	            }]
		        }
		    });
		
		    revenueChart.render();
		});
		
		/* 近5年新生婴儿变化 */
		FusionCharts.ready(function () {
		    var hourlySalesChart = new FusionCharts({
		        type: 'multiaxisline',
		        renderAt: 'chart-container6',
		        width: '100%',
		        height: '240',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": "Revenue Analysis",
		                "subcaption": "Last 12 weeks",
		                "xaxisname": "Week of Year",
		                "bgImage":"${nvix}/vixntcommon/base/img/images/background-img.jpg",
		                "canvasBgColor":"#101535",	
			            "canvasBgAlpha":"80",
		                "showvalues": "0",
		                "theme" : "fint"
		            },
		            "categories": [
		                {
		                    "category": [
		                        { "label": "2012" },
		                        { "label": "2013" }, 
		                        { "label": "2014" }, 
		                        { "label": "2015" }, 
		                        { "label": "2016" }, 
		                        
		                    ]
		                }
		            ],
		            "axis": [
		                {
		                    "title": "女孩",
		                    "titlepos": "left",
		                    "tickwidth": "10",
		                    "numberPrefix": "$",
		                    "divlineisdashed": "1",
		                    "dataset": [
		                        {
		                            "seriesname": "女孩",
		                            "linethickness": "3",
		                            "data": [
		                                { "value": "137500" }, 
		                                { "value": "124350" }, 
		                                { "value": "156700" }, 
		                                { "value": "131450" },
		                                { "value": "208300" },  
		                            ]
		                        }
		                    ]
		                }, {
		                    "title": "男孩",
		                    "axisonleft": "0",
		                    "titlepos": "right",
		                    "numdivlines": "8",
		                    "tickwidth": "10",
		                    "divlineisdashed": "1",
		                    "dataset": [
		                        {
		                            "seriesname": "男孩",
		                            "data": [
		                                { "value": "22567" }, 
		                                { "value": "21348" }, 
		                                { "value": "24846" }, 
		                                { "value": "19237" }, 
		                                { "value": "20672" },  
		                            ]
		                        }
		                    ]
		                } 
		            ]
		        }
		    }).render();
		    
		});
		
		/* 人口结构分析 */
		FusionCharts.ready(function () {
			    var populationMap = new FusionCharts({
			        type: 'maps/world',
			        renderAt: 'chart-container5-5',
			        width: '100%',
			        height: '450',
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
		/* 5-4 */
		FusionCharts.ready(function () {
			    var populationMap = new FusionCharts({
			        type: 'maps/world',
			        renderAt: 'chart-container5-4',
			        width: '100%',
			        height: '450',
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
		/* 5-3 */
		FusionCharts.ready(function () {
			    var populationMap = new FusionCharts({
			        type: 'maps/world',
			        renderAt: 'chart-container5-3',
			        width: '100%',
			        height: '450',
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
		/* 5-2 */
		FusionCharts.ready(function () {
			    var populationMap = new FusionCharts({
			        type: 'maps/world',
			        renderAt: 'chart-container5-2',
			        width: '100%',
			        height: '450',
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
		/* 5-1 */
		FusionCharts.ready(function () {
			    var populationMap = new FusionCharts({
			        type: 'maps/world',
			        renderAt: 'chart-container5-1',
			        width: '100%',
			        height: '450',
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
	<script>
		$(function(){
			var tab_year=$(".tab-years .tab-year");
			tab_year.click(function(){
				var tab_year_index=tab_year.index(this);
				console.log(tab_year_index);
				$(this).addClass("cur").siblings().removeClass("cur");
				$(".dataMain .population-str").eq(tab_year_index).show().siblings(".population-str").hide();
			})
		})
	</script>
</body>
</html>