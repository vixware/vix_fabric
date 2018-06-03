<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#subSalesQuotation tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#subSalesQuotation tr:even").addClass("alt");
function showQuotation(id){
	$.ajax({
		url:'${vix}/sales/quotes/salesQuotationAction!showSalesQuotation.action?id='+id,
		cache: false,
		success: function(html){
			asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"销售报价单",
					html:html,
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<input type="hidden" id="subSalesQuotationTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="subSalesQuotationPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="subSalesQuotationTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="subSalesQuotationOrderField" value="${pager.orderField}" />
<input type="hidden" id="subSalesQuotationOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="subSalesQuotationInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list" id="subSalesQuotation">
	<tbody>
		<tr class="alt">
			<th onclick="orderBy('id');" width="8%"><s:text name="cmn_id" />&nbsp;<img id="subSalesQuotationImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('billDate');" width="10%">单据日期&nbsp;<img id="subSalesQuotationImg_billDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('salesMan.id');" width="12%">报价人&nbsp;<img id="salesQuotationImg_salesMan" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="15%">客户名称&nbsp;<img id="subSalesQuotationImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('quoteSubject');" width="25%">报价主题&nbsp;<img id="subSalesQuotationImg_quoteSubject" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('bizType');" width="10%">业务类型&nbsp;<img id="subSalesQuotationImg_bizType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('formType');" width="10%">单据类型&nbsp;<img id="subSalesQuotationImg_formType" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">查看</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td><s:property value="formatDateToString(#entity.billDate)" /></td>
				<td>${entity.salesMan.name}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.quoteSubject}</td>
				<td>${entity.bizType}</td>
				<td>${entity.formType}</td>
				<td><a href="#" onclick="showQuotation('${entity.id}');"><img src="${vix}/common/img/icon_19.gif" /></a></td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			count = 8 - count;
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