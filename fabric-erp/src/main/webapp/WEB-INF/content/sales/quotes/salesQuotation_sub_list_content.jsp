<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#subSalesQuotation tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#subSalesQuotation tr:even").addClass("alt");

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkSubSqId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkSubSqId']").attr("checked", true);
		}else{
			$("input[name='chkSubSqId']").attr("checked", false);
		}
	}
	selectCount();
}
$.returnValue = "";
function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkSubSqId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
			$.returnValue = $.returnValue + "," + $(n).val();
		}else{
			 $.returnValue = $.returnValue.replace(","+$(n).val(),"");
		 }
	});
	$("#selectCount1").html(selectCount);
	$("#selectCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkSubSqIds']").attr("checked", false);
	}else{
		$("input[name='chkSubSqIds']").attr("checked", true);
	}
}
 
loadOrderByImage("${vix}","subSalesQuotation");
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
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkSubSqIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="6%"><s:text name="cmn_id" />&nbsp;<img id="subSalesQuotationImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('billDate');" width="10%">单据日期&nbsp;<img id="subSalesQuotationImg_billDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="30%">客户名称&nbsp;<img id="subSalesQuotationImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('quoteSubject');" width="25%">报价主题&nbsp;<img id="subSalesQuotationImg_quoteSubject" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('bizType');" width="10%">业务类型&nbsp;<img id="subSalesQuotationImg_bizType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('formType');" width="10%">单据类型&nbsp;<img id="subSalesQuotationImg_formType" src="${vix}/common/img/arrow.gif">
			</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkSubSqId" name="chkSubSqId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td><s:property value="formatDateToString(#entity.billDate)" /></td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.quoteSubject}</td>
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