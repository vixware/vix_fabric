<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<h1 align="center" style="color: #0000C6;">基础记录</h1>
	<br />
	<div class="left">
		<span style="color: #0000C6;">编码： ${officeSuppliesRegister.encoding}</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">名称： ${officeSuppliesRegister.theme}</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span style="color: #0000C6;">借用/归还日期:<s:date name="officeSuppliesRegister.createTime" format="yyyy-MM-dd" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">借用/归还人： ${officeSuppliesRegister.recipientsWho}</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span style="color: #0000C6;">经办人： ${officeSuppliesRegister.uploadPersonName}</a></span>
	</div>
	<br />
	<div class="source">
		<span>最近 ${officeSuppliesRegister.officeList.size()} 条归还借用记录</span>
	</div>
	<br />
	<s:iterator value="officeSuppliesRegister.officeList" var="entity" status="s">
		<div class="left">
			<span style="color: #0000C6;">办公用品编码：${entity.model}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">办公用品名称：${entity.officeName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">工号：${entity.code}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="color: #0000C6;">归还人：${entity.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">归还量：${entity.returnNumber}</span>&nbsp;&nbsp; <span style="color: #0000C6;">归还时间：<s:date name="#entity.startTime" format="yyyy-MM-dd HH:mm:ss" /></span>&nbsp;&nbsp; <span
				style="color: #0000C6;">未还量：${entity.borrowNumber}</span>&nbsp;
		</div>
		<br />
	</s:iterator>
</div>
