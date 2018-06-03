<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#caTable table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#caTable table tr:even").addClass("alt");
function radioCheck(id,name){
	$.returnValue = id+":"+name;
}
</script>
<input type="hidden" id="crmGiftTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="crmGiftPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="crmGiftTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="crmGiftOrderField" value="${pager.orderField}" />
<input type="hidden" id="crmGiftOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="crmGiftInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="caTable" class="list">
	<tbody>
		<tr class="alt">
			<th width="10%">选择</th>
			<th onclick="orderBy('id');" width="10%">编号&nbsp; <s:if test="%{pager.orderField == 'id' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'id' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('name');" width="15%">名称&nbsp; <s:if test="%{pager.orderField == 'name' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'name' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('buyCount');" width="10%">数量&nbsp; <s:if test="%{pager.orderField == 'buyCount' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'buyCount' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('stockNumber');" width="10%">库存数量&nbsp; <s:if test="%{pager.orderField == 'stockNumber' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'stockNumber' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('giftCompany');" width="15%">礼品厂商&nbsp; <s:if test="%{pager.orderField == 'giftCompany' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'giftCompany' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('contactPerson');" width="10%">接洽人&nbsp; <s:if test="%{pager.orderField == 'contactPerson' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'contactPerson' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('contactStyle');" width="10%">联系方式&nbsp; <s:if test="%{pager.orderField == 'contactStyle' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'contactStyle' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="radio" onclick="radioCheck('${entity.id}','${entity.name}');" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td>${entity.buyCount}</td>
				<td>${entity.stockNumber}</td>
				<td>${entity.giftCompany}</td>
				<td>${entity.contactPerson}</td>
				<td>${entity.contactStyle}</td>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>