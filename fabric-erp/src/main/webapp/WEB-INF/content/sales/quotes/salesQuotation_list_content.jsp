<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该销售报价单么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkId']").attr("checked", true);
		}else{
			$("input[name='chkId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCount1").html(selectCount);
	$("#selectCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkIds']").attr("checked", false);
	}else{
		$("input[name='chkIds']").attr("checked", true);
	}
}


loadOrderByImage("${vix}","salesQuotation");
</script>
<input type="hidden" id="salesQuotationTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesQuotationPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesQuotationTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesQuotationOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesQuotationOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesQuotationInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="salesQuotationImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('quoteSubject');" width="20%">报价主题&nbsp;<img id="salesQuotationImg_quoteSubject" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="20%">客户名称&nbsp;<img id="salesQuotationImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('formType');" width="10%">单据类型&nbsp;<img id="salesQuotationImg_formType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('salesMan.id');" width="10%">报价人&nbsp;<img id="salesQuotationImg_salesMan" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('billDate');" width="10%">单据日期&nbsp;<img id="salesQuotationImg_billDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.quoteSubject}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.formType}</td>
				<td>${entity.salesMan.name}</td>
				<td><s:property value="formatDateToString(#entity.billDate)" /></td>
				<td><i><a href="#" onclick="showOrder('${entity.id}');" title="<s:text name='update'/>"><img src="${vix}/common/img/icon_19.gif" /></a></i> <a href="#" onclick="saveOrUpdate('${entity.id}','new');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
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