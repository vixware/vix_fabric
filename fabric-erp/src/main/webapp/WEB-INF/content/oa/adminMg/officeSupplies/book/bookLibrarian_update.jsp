<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<h1 align="center" style="color: #0000C6;">基础记录</h1>
	<br />
	<div class="left">
		<span style="color: #0000C6;">编码： ${bookRegister.bookCodes}</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">名称： ${bookRegister.bookNames}</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span style="color: #0000C6;">借用/归还日期:<s:date name="bookRegister.createTime" format="yyyy-MM-dd" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">借用/归还人： ${bookRegister.recipientsWho}</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span style="color: #0000C6;">经办人： ${bookRegister.uploadPersonName}</a></span>
	</div>
	<br />
	<div class="source">
		<span>最近 ${bookRegister.borrowedBooksList.size()} 条还书记录</span>
	</div>
	<br />
	<s:iterator value="bookRegister.borrowedBooksList" var="entity" status="s">
		<div class="left">
			<span style="color: #0000C6;">图书编码：${entity.code}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">图书名称：${entity.bookName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">借书卡号：${entity.bookNumber}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="color: #0000C6;">借书人：${entity.recipientsWho}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">归还量：${entity.returnNumber}</span>&nbsp;&nbsp; <span style="color: #0000C6;">归还时间：<s:date name="#entity.startTime" format="yyyy-MM-dd HH:mm:ss" /></span>&nbsp;&nbsp;
			<span style="color: #0000C6;">未还量：${entity.borrowNumber}</span>&nbsp;
		</div>
		<br />
	</s:iterator>
</div>
