<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
 
function selectCount(){
	var selectCount = 0;
	$.each($("input[name='subSalesOrderChkId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCount1").html(selectCount);
	$("#selectCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='subSalesOrderChkIds']").attr("checked", false);
	}else{
		$("input[name='subSalesOrderChkIds']").attr("checked", true);
	}
}
 
loadOrderByImage("${vix}","subSalesOrder");
</script>
<input type="hidden" id="subSalesOrderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="subSalesOrderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="subSalesOrderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="subSalesOrderOrderField" value="${pager.orderField}" />
<input type="hidden" id="subSalesOrderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="subSalesOrderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">选择</th>
			<th onclick="orderBy('id');" width="6%"><s:text name="cmn_id" />&nbsp;<img id="subSalesOrderImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('billDate');" width="10%">单据日期&nbsp;<img id="subSalesOrderImg_billDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="30%">客户名称&nbsp;<img id="subSalesOrderImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('title');" width="25%">报价主题&nbsp;<img id="subSalesOrderImg_title" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('bizType');" width="10%">业务类型&nbsp;<img id="subSalesOrderImg_bizType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('formType');" width="10%">单据类型&nbsp;<img id="subSalesOrderImg_formType" src="${vix}/common/img/arrow.gif">
			</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<s:if test="chooseType == 'multi'">
					<td><input id="subSalesOrderChkId" name="subSalesOrderChkId" value="${entity.id}" type="checkbox" onchange="chkChange(this,'${entity.id}',${entity.customerAccount.id})" /></td>
				</s:if>
				<s:else>
					<td><input id="subSalesOrderChkId" name="subSalesOrderChkId" value="${entity.id}" type="radio" onchange="chkChange(this,'${entity.id}','${entity.title}');" /></td>
				</s:else>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td><s:property value="formatDateToString(#entity.billDate)" /></td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.title}</td>
				<td>${entity.bizType}</td>
				<td>${entity.formType}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>