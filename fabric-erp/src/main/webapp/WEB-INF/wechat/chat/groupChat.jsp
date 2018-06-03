<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<html>
<head>
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script src="${vix}/wechatcommon/js/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业群聊</title>
</head>
<body>
	<section class="groupChat">
	    <dl class="history_list1">
	        <dt> <img src="${vix}/wechatcommon/images/history_list1_pic.jpg"/></dt>
	        <dd>
	            <span><a href="#"><img src="${vix}/wechatcommon/images/history_list1_icon1.png"/><b>附近订单</b></a></span>
	            <span><a href="#"><img src="${vix}/wechatcommon/images/history_list1_icon2.png"/><b>我的关注</b></a></span>
	            <span><a href="#"><img src="${vix}/wechatcommon/images/history_list1_icon3.png"/><b>我的收藏</b></a></span>
	            <span><a href="#"><img src="${vix}/wechatcommon/images/history_list1_icon4.png"/><b style="border-right:0;">商务缓存</b></a></span>
	        </dd>
	    </dl>
	    <div class="tab_menu">
	        <ul>
	            <li class="selected"><a href="#">基本资料</a> </li>
	            <li><a href="#">联系人</a> </li>
	            <li><a href="#">商机</a> </li>
	            <li><a href="#">拜访</a> </li>
	        </ul>
	    </div>
	    <div class="tab_con">
	        <div class="common groChat_list2_con">
	            <dl>
	                <dt><span>客户名称：</span><strong>广东道一</strong></dt>
	                <dt><span>区域：</span><strong>广东省 惠州市</strong></dt>
	                <dt><span>地址：</span><strong>广东省 惠州市</strong></dt>
	                <dt><span>行业：</span><strong>计算机服务（系统、数据服务、维修）</strong></dt>
	                <dt><span>联系电话：</span><strong>18523632563</strong></dt>
	                <dt><span>网址：</span><strong>http//www.do1.com.cn</strong></dt>
	                <dd>广东道一信息技术股份有限公司</dd>
	            </dl>
	        </div>
	        <div class="common groChat_list2_con">
	            <dl>
	                <dt><span>客户名称：</span><strong>广东道一</strong></dt>
	                <dt><span>区域：</span><strong>广东省 惠州市</strong></dt>
	                <dt><span>地址：</span><strong>广东省 惠州市</strong></dt>
	                <dt><span>行业：</span><strong>计算机服务（系统、数据服务、维修）</strong></dt>
	                <dd>广东道一信息技术股份有限公司</dd>
	            </dl>
	        </div>
	        <div class="common groChat_list2_con">
	            <dl>
	                <dt><span>地址：</span><strong>广东省 惠州市</strong></dt>
	                <dt><span>行业：</span><strong>计算机服务（系统、数据服务、维修）</strong></dt>
	                <dt><span>联系电话：</span><strong>18523632563</strong></dt>
	                <dt><span>网址：</span><strong>http//www.do1.com.cn</strong></dt>
	                <dd>广东道一信息技术股份有限公司</dd>
	            </dl>
	        </div>
	        <div class="common groChat_list2_con">
	            <dl>
	                <dt><span>联系电话：</span><strong>18523632563</strong></dt>
	                <dt><span>网址：</span><strong>http//www.do1.com.cn</strong></dt>
	                <dd>广东道一信息技术股份有限公司</dd>
	            </dl>
	        </div>
	    </div>
	    <div class="newMeet_list3">
	        <h2>负责人（可查看、修改资料）</h2>
	        <ul>
	            <li><a href="#"><img src="${vix}/wechatcommon/images/newMeet_pic1.jpg"></a></li>
	            <li><a href="#"><img src="${vix}/wechatcommon/images/newMeet_pic1.jpg"></a></li>
	        </ul>
	    </div>
	</section>
	<div style="height: 80px;clear: both;"></div>
	<div class="chat_bot">
	    <dl>
	        <dt>
	            <span><a href="#" class="chat_icon"><img src="images/chat_fot_icon3.png"></a></span>
	            <b><input type="text" class="chat_text"></b>
	            <strong><a href="#">发表</a> </strong>
	        </dt>
	        <dd class="chat_more">
	            <a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon1.png"><span>电话</span></a>
	            <a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon2.png"><span>位置</span></a>
	            <a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon3.png"><span>图片</span></a>
	            <a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon4.png"><span>视频聊天</span></a>
	        </dd>
	    </dl>
	</div>
	
	<!-----操作导航---->
	<div class="leftSlider">
	    <ul>
	        <li><a href="#"><img src="${vix}/wechatcommon/images/slider_icon1.png"></a> </li>
	        <li><a href="#"><img src="${vix}/wechatcommon/images/slider_icon2.png"></a> </li>
	        <li><a href="#"><img src="${vix}/wechatcommon/images/slider_icon3.png"></a> </li>
	    </ul>
	</div>
</body>
</html>