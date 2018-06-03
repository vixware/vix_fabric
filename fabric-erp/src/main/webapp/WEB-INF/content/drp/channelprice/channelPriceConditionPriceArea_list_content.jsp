<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<table class="list">
	<tbody>
		<tr class="alt" style="background: none repeat scroll 0 0 #f1f1f1; border-bottom: 1px solid #dfdfdf; font-weight: 700; line-height: 30px; padding: 0 3px;">
			<th style="text-align: left;">开始价格</th>
			<th style="text-align: left;">结束价格</th>
			<th style="text-align: left;">返款</th>
			<th style="text-align: left;">价格</th>
			<th style="text-align: left;">折扣</th>
			<th style="text-align: left;">备注</th>
			<th style="text-align: left;" width="5%">操作</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="channelPriceConditionPriceAreaList" var="entity" status="s">
			<%
				count++;
			%>

			<tr class="">
				<td>${entity.startTotalAmount}</td>
				<td>${entity.endTotalAmount}</td>
				<td>${entity.refund}</td>
				<td>${entity.price}</td>
				<td>${entity.discount}</td>
				<td>${entity.memo}</td>
				<td><a href="#" onclick="saveOrUpdatePriceConditionDetail('${entity.id}','price');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deletePriceConditionEntity('${entity.id}','price');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
			</tr>
		</s:iterator>
		<%
			/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count", count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>