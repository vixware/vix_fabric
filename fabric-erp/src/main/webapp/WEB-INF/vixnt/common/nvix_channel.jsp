<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="" href="#"><i class="fa fa-lg fa-fw fa-chain"></i> <span class="menu-item-parent">渠道管理</span></a>
	<ul>
		<li><a id="" href="#">基础数据管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/channel/vixntChannelAction!goList.action');"> 渠道列表 </a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/channel/vixntChannelItemAction!goList.action');">渠道商品管理</a></li>
			</ul></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/channel/vixntChannelOrderAction!goList.action');">渠道订单管理</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!goList.action');">渠道项目报备</a></li>
		<li><a href="#" id="" onclick="">渠道销售统计表</a></li>
	</ul></li>