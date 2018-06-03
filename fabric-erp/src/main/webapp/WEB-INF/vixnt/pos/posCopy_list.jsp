<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<html lang="en-us">
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/font-awesome.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-production-plugins.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-production.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-skins.min.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-rtl.min.css"></link>
<link rel="shortcut icon" href="${nvix}/vixntcommon/base/img/favicon/favicon.ico" type="image/x-icon"></link>
<link rel="icon" href="${nvix}/vixntcommon/base/img/favicon/favicon.ico" type="image/x-icon"></link>
<link rel="apple-touch-icon" href="${nvix}/vixntcommon/base/img/splash/sptouch-icon-iphone.png"></link>
<link rel="apple-touch-icon" sizes="76x76" href="${nvix}/vixntcommon/base/img/splash/touch-icon-ipad.png"></link>
<link rel="apple-touch-icon" sizes="120x120" href="${nvix}/vixntcommon/base/img/splash/touch-icon-iphone-retina.png"></link>
<link rel="apple-touch-icon" sizes="152x152" href="${nvix}/vixntcommon/base/img/splash/touch-icon-ipad-retina.png"></link>
<link rel="apple-touch-startup-image" href="${nvix}/vixntcommon/base/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)"></link>
<link rel="apple-touch-startup-image" href="${nvix}/vixntcommon/base/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)"></link>
<link rel="apple-touch-startup-image" href="${nvix}/vixntcommon/base/img/splash/iphone.png" media="screen and (max-device-width: 320px)"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/zTreeStyle.css"></link>
<%-- <link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/demo.min.css"></link> --%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/your_style.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/plugin/validengine/css/validationEngine.jquery.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/template/css/statistics.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/newstyle.css"></link>

<!-- FAVICONS -->
<link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

<link rel="apple-touch-icon" href="img/splash/sptouch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="76x76" href="img/splash/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="120x120" href="img/splash/touch-icon-iphone-retina.png">
<link rel="apple-touch-icon" sizes="152x152" href="img/splash/touch-icon-ipad-retina.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="apple-touch-startup-image" href="img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image" href="img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image" href="img/splash/iphone.png" media="screen and (max-device-width: 320px)">
</head>
<body class="">
	<div id="main" role="main" style="margin-left: 0px;">

		<!-- MAIN CONTENT -->
		<div id="content">

			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class=row>
							<div class="col-sm-4 pad-l pad-r" style="height: 660px; position: relative;">
								<div class="tool-header">
									<img src="img/icon-pos/photo.jpg">
									<div class="tool-header-msg">
										<div>店长：刘能</div>
										<div>UI生活馆（线上超市）</div>
									</div>
									<div class="tool-header-msg">
										<div>营业员：赵四</div>
										<div>会员：李防潮</div>
									</div>
								</div>
								<div class="tool-search">
									<input value="扫描商品条形码或输入商品编号">
									<div class="tool-search-box">
										<i class="icon-search"></i>
									</div>
								</div>
								<div class="tool-goods">
									<div class="pro-head">
										<div class="pro-head-msg">序号</div>
										<div class="pro-head-msg">数量</div>
										<div class="pro-head-msg">原价</div>
										<div class="pro-head-msg">单价</div>
										<div class="pro-head-msg">小计</div>
										<div class="pro-head-msg">优惠</div>
									</div>
									<dl>
										<dt>1</dt>
										<dd class="first-dd">
											<div class="left">百事可乐</div>
											<div class="right">(12336546682523)</div>
										</dd>
										<dd class="second-dd">
											<div class="pro-con-msg">2</div>
											<div class="pro-con-msg">￥3.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥3.00</div>
										</dd>
									</dl>
									<dl class="cur">
										<dt>2</dt>
										<dd class="first-dd">
											<div class="left">百事可乐</div>
											<div class="right">(12336546682523)</div>
										</dd>
										<dd class="second-dd">
											<div class="pro-con-msg">2</div>
											<div class="pro-con-msg">￥3.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥3.00</div>
										</dd>
									</dl>
									<dl class="cur">
										<dt>3</dt>
										<dd class="first-dd">
											<div class="left">百事可乐</div>
											<div class="right">(12336546682523)</div>
										</dd>
										<dd class="second-dd">
											<div class="pro-con-msg">2</div>
											<div class="pro-con-msg">￥3.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥3.00</div>
										</dd>
									</dl>
									<dl class="cur">
										<dt>4</dt>
										<dd class="first-dd">
											<div class="left">百事可乐</div>
											<div class="right">(12336546682523)</div>
										</dd>
										<dd class="second-dd">
											<div class="pro-con-msg">2</div>
											<div class="pro-con-msg">￥3.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥3.00</div>
										</dd>
									</dl>
									<dl class="cur">
										<dt>5</dt>
										<dd class="first-dd">
											<div class="left">百事可乐</div>
											<div class="right">(12336546682523)</div>
										</dd>
										<dd class="second-dd">
											<div class="pro-con-msg">2</div>
											<div class="pro-con-msg">￥3.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥0.50</div>
											<div class="pro-con-msg">￥3.00</div>
										</dd>
									</dl>
								</div>
								<div class="tool-footer">
									<div class="tool-js">
										<div class="tool-js-msg">
											数量：<span>10</span>
										</div>
										<div class="tool-js-msg">
											金额：<span>￥10.00</span>
										</div>
										<div class="tool-js-msg">
											优惠：<span>￥65.00</span>
										</div>
									</div>
									<div class="tool-bar">
										<i class="icon-down "></i>
									</div>
									<div class="tool-count">
										<div class="tool-count-l">
											<div class="js-btn">7</div>
											<div class="js-btn">8</div>
											<div class="js-btn">9</div>
											<div class="js-btn">退格</div>
											<div class="js-btn">4</div>
											<div class="js-btn">5</div>
											<div class="js-btn">6</div>
											<div class="js-btn">清空</div>
											<div class="js-btn">1</div>
											<div class="js-btn">2</div>
											<div class="js-btn">3</div>
											<div class="js-btn">取消</div>
											<div class="js-btn">0</div>
											<div class="js-btn">00</div>
											<div class="js-btn">.</div>
											<div class="js-btn">确定</div>
										</div>
										<div class="tool-count-r">
											<div class="jiesuan">结算</div>
											<div class="yingyep">营业员</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-1 tool-pos">
								<div class="tool-lists">
									<i class="icon-sort"></i>
								</div>
								<div class="tool-list">
									<i class="icon-add"></i>
								</div>
								<div class="tool-list">
									<i class="icon-jian"></i>
								</div>
								<div class="tool-list">删除</div>
								<div class="tool-list">挂单</div>
								<div class="tool-list">优惠</div>
								<div class="tool-list">退货</div>
								<div class="tool-list">赠送</div>
								<div class="tool-list">会员</div>
								<div class="tool-list">
									<i class="icon-set"></i>
								</div>
								<div class="tool-list">
									<i class="icon-close"></i>
								</div>
							</div>
							<div class="col-sm-7 tool-content pad-l" style="height: 660px;">
								<!-- 第一行切换 -->
								<div class="tool-first-tabs">
									
									<div class="tab-left-more">
										<i></i>
									</div>
									<div class="tool-first-tab-1">
											<div class="cur">饮料</div>
											<div>推荐</div>
											<div>干果</div>
											<div>奶类</div>
											<div>蔬菜</div>
											<div>饮料</div>
											
									</div>
									<div class="tool-first-tab-1s" style="display:none;">
											<div>饮料</div>
											<div>推荐</div>
											<div>干果</div>
											<div>奶类</div>
											<div>蔬菜</div>
											<div>饮料</div>
											<div>饮料</div>
											<div>推荐</div>
											<div>干果</div>
											<div>奶类</div>
											<div>蔬菜</div>
											<div>饮料</div>
									</div>
								</div>
								<!-- 第二行切换 -->
								<div class="tool-second-tabs">
									
									<div class="tab-left-more">
										<i></i>
									</div>
									<div class="tool-second-tabs_s">
										<!-- 分类一 -->
										<div class="tool-second-tab-1sa cur">
											<div class="tool-second-tab-1 ">
												<div class="cur">
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
											<div class="tool-second-tab-1s" style="display:none;">
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
										</div>
										
										<!-- 分类二-->
										<div class="tool-second-tab-1sa" >
											<div class="tool-second-tab-1">
												<div class="cur">
													<a href="javascript:void(0);">热销产品2</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
											<div class="tool-second-tab-1s" style="display:none;">
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
										</div>
										
										<!-- 分类三 -->
										<div class="tool-second-tab-1sa" >
											<div class="tool-second-tab-1">
												<div class="cur">
													<a href="javascript:void(0);">热销产品3</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
											<div class="tool-second-tab-1s" style="display:none;">
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
										</div>
										
										<!-- 分类四 -->
										<div class="tool-second-tab-1sa" >
											<div class="tool-second-tab-1">
												<div class="cur">
													<a href="javascript:void(0);">热销产品4</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
											<div class="tool-second-tab-1s" style="display:none;">
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
										</div>
										
										<!-- 分类五 -->
										<div class="tool-second-tab-1sa" >
											<div class="tool-second-tab-1">
												<div class="cur">
													<a href="javascript:void(0);">热销产品5</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
											<div class="tool-second-tab-1s" style="display:none;">
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
										</div>
										
										<!-- 分类六 -->
										<div class="tool-second-tab-1sa" >
											<div class="tool-second-tab-1">
												<div class="cur">
													<a href="javascript:void(0);">热销产品6</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
											<div class="tool-second-tab-1s" style="display:none;">
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">热销产品1</a>
												</div>
												<div>
													<a href="javascript:void(0);">收银换购</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
												<div>
													<a href="javascript:void(0);">自定义分类</a>
												</div>
											</div>
										</div>
										
										<!-- 分类七 -->
										
										<!-- 分类八 -->
										
										<!-- 分类九 -->
										
									 
									</div>

								</div>
								<!-- 内容  -->
								<div class="tool-pro-lists">
									<div class="tool-content-main cur">
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐(111)</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
									</div>

									<!-- 商品二 -->
									<div class="tool-content-main">
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐(222)</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
									</div>
									<!-- 商品三 -->
									<div class="tool-content-main">
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐(333)</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
									</div>

									<!-- 商品四 -->
									<div class="tool-content-main">
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐(444)</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
									</div>
									<!-- 商品五 -->
									<div class="tool-content-main">
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐(555)</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
									</div>
									<!-- 商品6 -->
									<div class="tool-content-main">
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐(666)</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>

										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
										<div class="tool-content-main-box">
											<div class="box-text">154987563211百事可乐</div>
											<div class="box-price">￥4.5</div>
										</div>
									</div>
								</div>

								<div class="content-tab-btn">
									<div class="left-btn">
										<i></i>
									</div>
									<div class="right-btn">
										<i></i>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

		</div>
		<!-- END MAIN CONTENT -->

	</div>
	<!-- END MAIN PANEL -->

	<!-- PAGE FOOTER -->
	<div class="page-footer">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<span class="txt-color-white">SmartAdmin 1.5 <span class="hidden-xs"> - Web Application Framework</span> © 2014-2015
				</span>
			</div>
		</div>
	</div>
	
<script src="${nvix}/vixntcommon/base/js/libs/jquery-2.1.1.min.js" type="text/javascript"></script>

<script src="${nvix}/vixntcommon/base/js/libs/jquery-ui-1.10.3.min.js" type="text/javascript"></script>

<!-- IMPORTANT: APP CONFIG -->
<script src="${nvix}/vixntcommon/base/js/app.config.js" type="text/javascript"></script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
<script src="${nvix}/vixntcommon/base/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js" type="text/javascript"></script>

<!-- BOOTSTRAP JS -->
<script src="${nvix}/vixntcommon/base/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>

<!-- CUSTOM NOTIFICATION -->
<script src="${nvix}/vixntcommon/base/js/notification/SmartNotification.min.js" type="text/javascript"></script>

<!-- JARVIS WIDGETS -->
<script src="${nvix}/vixntcommon/base/js/smartwidgets/jarvis.widget.min.js" type="text/javascript"></script>

<!-- EASY PIE CHARTS -->
<script src="${nvix}/vixntcommon/base/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js" type="text/javascript"></script>

<!-- SPARKLINES -->
<script src="${nvix}/vixntcommon/base/js/plugin/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>

<!-- JQUERY VALIDATE -->
<script src="${nvix}/vixntcommon/base/js/plugin/jquery-validate/jquery.validate.min.js" type="text/javascript"></script>

<!-- JQUERY MASKED INPUT -->
<script src="${nvix}/vixntcommon/base/js/plugin/masked-input/jquery.maskedinput.min.js" type="text/javascript"></script>
<!-- JQUERY SELECT2 INPUT -->
<script src="${nvix}/vixntcommon/base/js/plugin/select2/select2.min.js" type="text/javascript"></script>
<!-- JQUERY UI + Bootstrap Slider -->
<script src="${nvix}/vixntcommon/base/js/plugin/bootstrap-slider/bootstrap-slider.min.js" type="text/javascript"></script>
<!-- browser msie issue fix -->
<script src="${nvix}/vixntcommon/base/js/plugin/msie-fix/jquery.mb.browser.min.js" type="text/javascript"></script>
<!-- FastClick: For mobile devices -->
<script src="${nvix}/vixntcommon/base/js/plugin/fastclick/fastclick.min.js" type="text/javascript"></script>
<!-- Demo purpose only -->
<%-- <script src="${nvix}/vixntcommon/base/js/demo.min.js" type="text/javascript"></script> --%>
<!-- MAIN APP JS FILE -->
<script src="${nvix}/vixntcommon/base/js/app.min.js" type="text/javascript"></script>
<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
<!-- Voice command : plugin -->
<script src="${nvix}/vixntcommon/base/js/speech/voicecommand.min.js" type="text/javascript"></script>
<!-- SmartChat UI : plugin -->
<script src="${nvix}/vixntcommon/base/js/smart-chat-ui/smart.chat.ui.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/smart-chat-ui/smart.chat.manager.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/ztree/jquery.ztree.all.min.js" type="text/javascript"></script>
<!-- PAGE RELATED PLUGIN(S) -->
<script src="${nvix}/vixntcommon/base/js/plugin/superbox/superbox.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/summernote/summernote.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/moment/moment.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/fullcalendar/jquery.fullcalendar.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/fullcalendar/zh-cn.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/datatables/dataTables.colVis.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/datatables/dataTables.tableTools.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/datatable-responsive/datatables.responsive.min.js" type="text/javascript"></script>
<%-- <script src="${nvix}/vixntcommon/base/js/demo.min.js" type="text/javascript"></script> --%>
<script src="${nvix}/vixntcommon/base/js/jquery.jnotify.js" type="text/javascript" ></script>
<script src="${nvix}/vixntcommon/base/js/plugin/layer/layer.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/jquery.vixerp.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/AsyncBox.v1.4.5.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/bootstrap-tags/bootstrap-tagsinput.min.js"></script>
<!-- å¨å±JS -->
<script src="${nvix}/vixntcommon/common/base/public.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/common/base/base.js" type="text/javascript"></script>
<script src="${nvix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="${nvix}/plugin/validengine/js/jquery.validationEngine-zh_CN.js" type="text/javascript"></script>
<script src="${nvix}/plugin/validengine/js/jquery.validationEngine.min.js" type="text/javascript"></script>
<!-- ä¸­æè½¬ç  -->
<script src="${nvix}/vixntcommon/common/urlencode.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/LodopFuncs.js" type="text/javascript"></script>

<!-- ææ¬ç¼è¾å¨ -->
<script src="${nvix}/vixntcommon/base/js/plugin/ckeditor/ckeditor.js"></script>
<!-- è¡¨åéªè¯ -->
<script src="${nvix}/vixntcommon/base/js/plugin/jquery-form/jquery-form.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${nvix}/vixntcommon/base/js/moment.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/jqPaginator.js"></script>
<!-- PAGE RELATED PLUGIN(S) -->
<script src="${nvix}/vixntcommon/base/js/plugin/summernote/summernote.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/markdown/markdown.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/markdown/to-markdown.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/markdown/bootstrap-markdown.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/knob/jquery.knob.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/flot/jquery.flot.fillbetween.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/flot/jquery.flot.orderBar.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/flot/jquery.flot.pie.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/flot/jquery.flot.time.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/flot/jquery.flot.tooltip.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/chartjs/chart.min.js"></script>
</body>
</html>
<script>
			$(function(){
				//菜单一的切换
			    var tab_menu_li = $('.tool-first-tab-1 div');
			    tab_menu_li.click(function(){
			    	var index = $(this).index();
			        $(this).addClass('cur').siblings().removeClass('cur');
			        $('.tool-second-tabs .tool-second-tabs_s .tool-second-tab-1sa').eq(index).addClass('cur').siblings().removeClass('cur');
			        $('.tool-first-tab-1s div.cur').removeClass('cur');
			    });
			  //菜单二的切换
			    $('.tool-second-tabs_s').on('click','.tool-second-tab-1sa.cur .tool-second-tab-1 div',function(){
			    	var index = $(this).index(); 
			    	$(this).addClass('cur').siblings().removeClass('cur');
			    	$('.tool-pro-lists .tool-content-main').eq(index).addClass('cur').siblings().removeClass('cur');
			    	$('.tool-second-tab-1s div.cur').removeClass('cur');
			    	
			    });
			  //下拉隐藏计算键盘
			  $('.tool-bar').on('click','i',function(){
				  if($(this).hasClass('up')){
					  $('.tool-count').slideDown();
					  $(this).removeClass('up');
				  }else{
					  $('.tool-count').slideUp();
					  $(this).addClass('up');
				  }
			  });
			  //左切换
			  	//1.左按钮鼠标进入
			  $('.left-btn i').mouseover(function(){
				  if($('.tool-pro-lists .tool-content-main.cur').index()==0){}else{$(this).addClass('cur'); } 
			  });
			  	//2.左按钮点击
			  $('.left-btn i').click(function(){
				  if($('.tool-pro-lists .tool-content-main.cur').index()==0){
					 
				  }else{
					  $('.tool-pro-lists .tool-content-main.cur').removeClass('cur').prev().addClass('cur');
				  }
			  });
			  	//3.右按钮鼠标移出
			  $('.left-btn i').mouseout(function(){
				  $(this).removeClass('cur');
			  });
			  //右切换
			  	//1.右按钮鼠标进入
			  $('.right-btn i').mouseover(function(){
				  if($('.tool-pro-lists .tool-content-main.cur').index()==5){}else{$(this).addClass('cur'); }
			  })
			   //2.右按钮鼠标移出
			  $('.right-btn i').mouseout(function(){
				  $(this).removeClass('cur');
			  })
			  	//3.右按钮点击
			  $('.right-btn i').click(function(){
				  if($('.tool-pro-lists .tool-content-main.cur').index()==5){
					 
				  }else{
					  $('.tool-pro-lists .tool-content-main.cur').removeClass('cur').next().addClass('cur');
				  }
			  });	
			  //展开选项卡
			  	//1.第一选项卡
			  $('.tool-first-tabs .tab-left-more').click(function(){
				  $('.tool-first-tab-1s').toggle();
			  });
			  $('.tool-first-tab-1s').on('click','div',function(){  
				  $(this).addClass('cur').siblings().removeClass('cur');
				  $('.tool-first-tab-1 div.cur').removeClass('cur');
			  });
			  //2.第二选项卡
			  $('.tool-second-tabs .tab-left-more').click(function(){
				  $('.tool-second-tab-1sa.cur .tool-second-tab-1s').toggle();
			  });
			  $('.tool-second-tab-1s').on('click','div',function(){  
				  $(this).addClass('cur').siblings().removeClass('cur');
				  $('.tool-second-tab-1 div.cur').removeClass('cur');
			  });
			});
		</script>