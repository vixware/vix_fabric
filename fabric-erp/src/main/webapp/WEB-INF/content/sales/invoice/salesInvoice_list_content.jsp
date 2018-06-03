<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该发票么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkCategoryId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkCategoryId']").attr("checked", true);
		}else{
			$("input[name='chkCategoryId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkCategoryId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCategoryCount1").html(selectCount);
	$("#selectCategoryCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkCategoryIds']").attr("checked", false);
	}else{
		$("input[name='chkCategoryIds']").attr("checked", true);
	}
}

$(".untitled").hover(
	function () {
		$(this).css({ "position": "relative"});
		$(this).children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $(this).offset();
		if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
  	},
	function () {
		$(this).css({ "position": "static"});
		$(this).children('.popup').css({ "display": "none"});
 	}
);
$(".close").click(
	function () {
		$(this).parent().parent().css({ "display": "none"});
	}
);
</script>
<input type="hidden" id="salesInvoiceTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesInvoicePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesInvoiceTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesInvoiceOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesInvoiceOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesInvoiceInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="10%"><s:text name="pur_invoice_number" /></th>
			<th width="15%"><s:text name="pur_invoices_theme" /></th>
			<th width="20%">客户名称</th>
			<th width="10%"><s:text name="pur_invoice_type" /></th>
			<th width="10%"><s:text name="pur_invoiced_amount" /></th>
			<th width="10%"><s:text name="pur_billing_date" /></th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.code }</span></td>
				<td><span style="color: gray;">${entity.name }</span></td>
				<td><span style="color: gray;">${entity.customerAccount.name}</span></td>
				<td><span style="color: gray;"> <s:if test="#entity.invoiceType == 1">
							销售普通发票
						</s:if> <s:elseif test="#entity.invoiceType == 2">
							销售专用发票
						</s:elseif> <s:elseif test="#entity.invoiceType == 3">
							红字普通销售发票
						</s:elseif> <s:elseif test="#entity.invoiceType == 4">
							红字专用销售发票
						</s:elseif> <s:else>
							未知类型
						</s:else>
				</span></td>
				<td><span style="color: gray;">${entity.amount }</span></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.createTime }" type="both" pattern="yyyy-MM-dd" /> </span></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a></i> <i><a href="###" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"><img src="${vix}/common/img/icon_12.png" /></a></i> <i><a href="###" onclick="saveOrUpdate(${entity.id},$('#parentId').val());" title="<s:text name='cmn_update'/>"></a></i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div>
				</td>
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