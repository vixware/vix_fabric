<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#specificationDetailTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#specificationDetailTable tr:even").addClass("alt");
</script>
<table id="specificationDetailTable" class="list">
	<tr>
		<th width="10%" style="text-align: left;"><span style="cursor: pointer;" onclick="orderBy('id');">编号&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="20%" style="text-align: left;"><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="20%" style="text-align: left;"><span style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="40%" style="text-align: left;"><span style="cursor: pointer;" onclick="orderBy('memo');">备注&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="10%" style="text-align: left;">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="specificationDetailList" var="entity" status="s">
		<% count++; %>
		<tr>
			<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
			<td>${entity.code}</td>
			<td>${entity.name}</td>
			<td>${entity.memo}</td>
			<td align="center"><a href="#" onclick="saveOrUpdateDetail('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
			</a> <a href="#" onclick="deleteSpecificationDetail(this,${entity.id});"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
			</a></td>
		</tr>
	</s:iterator>
	<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
		com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
		count = pager.getPageSize() - count;
		request.setAttribute("count",count);
	%>
	<c:forEach begin="1" end="${count-6}">
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
</table>