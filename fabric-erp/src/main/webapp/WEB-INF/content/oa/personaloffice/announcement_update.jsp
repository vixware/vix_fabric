<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<h1 align="center">${announcementNotification.title}</h1>
	<div class="source">
		<span>开始时间:<s:date name="announcementNotification.activeStartDate" format="yyyy-MM-dd" /></span> <span>结束时间:<s:date name="announcementNotification.activeEndDate" format="yyyy-MM-dd" /></span> <span>发布对象： ${announcementNotification.pubNames}</span>
	</div>
	<div id="content">
		<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp;${announcementNotification.content}</font>
	</div>
	<div class="clearfix">
		<div class="right_menu">
			<ul>
				<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />公告通知附件</a></li>
			</ul>
		</div>
		<table class="list">
			<tbody>
				<tr class="alt">
					<th>编号</th>
					<th>文档名称</th>
					<th>文档类型</th>
					<th>上传时间</th>
					<th>上传者</th>
					<th>操作</th>
				</tr>
				<s:iterator value="noticeUploaderList" var="entity" status="s">
					<tr>
						<td>${entity.id}</td>
						<td>${entity.title}</td>
						<td>${entity.fileType}</td>
						<td>${entity.uploadTime}</td>
						<td>${entity.uploadPersonName}</td>
						<td>
							<div class="untitled" style="position: static; display: inline;">
								<a href="${vix}/oa/announcementNotificationAction!downloadEqDocument.action?id=${entity.id}">下载</a>
							</div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>
