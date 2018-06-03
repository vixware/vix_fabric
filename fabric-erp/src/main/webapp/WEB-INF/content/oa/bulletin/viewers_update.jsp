<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

</script>
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="180">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<!-- <th width="5%">编号</th> -->
			<th width="36%">公告标题</th>
			<th width="20%">查看人</th>
			<th width="10%">查看状态</th>
			<th width="26%">查看时间</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.announcementNotification.title }</span></td>
				<td><span style="color: gray;">${entity.employee.name}</span></td>
				<td><a href="#" style="color: gray;"> <s:if test="%{#entity.isPublish == 0}">未读</s:if> <s:elseif test="%{#entity.isPublish == 1}">已读</s:elseif>
				</a></td>
				<td><span style="color: gray;"><s:date name="#entity.createTime" format="yyyy-MM-dd HH:mm:ss" /></span></td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
