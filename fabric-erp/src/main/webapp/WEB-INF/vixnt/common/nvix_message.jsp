<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a href="#"><i class="fa fa-lg fa-fw fa-envelope"></i> <span class="menu-item-parent">短信管理</span></a>
	<ul>
		<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/messageApiSetAction!goList.action');">接口配置</a></li>
		<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/messageCostRulesAction!goList.action');">短信计费规则管理</a></li>
		<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/message/messageTemplateAction!goList.action');">短信模板配置</a></li>
		<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/message/messageSendRecordAction!goList.action');">短信发送记录</a></li>
	</ul>
</li>
