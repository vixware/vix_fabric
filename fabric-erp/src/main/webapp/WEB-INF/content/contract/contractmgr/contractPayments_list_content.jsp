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
	asyncbox.confirm('确定要删除么?','<s:text name='合同付款'/>',function(action){
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
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","brand");
</script>
<input type="hidden" id="brandTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="brandPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="brandTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="brandOrderField" value="${pager.orderField}" />
<input type="hidden" id="brandOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="brandInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="所有" /></a></li>
							<li><a href="#"><s:text name="其他" /></a></li>
							<li><a href="#"><s:text name="关闭" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="100" style="cursor: pointer;" onclick="orderBy('contractCode');"><s:text name="合同编码" />&nbsp;<img id="brandImg_contractCode" src="${vix}/common/img/arrow.gif"></th>
			<th width="100" style="cursor: pointer;" onclick="orderBy('contractName');"><s:text name="ctm_contract_name" />&nbsp;<img id="brandImg_contractName" src="${vix}/common/img/arrow.gif"></th>
			<th width="170" style="cursor: pointer;" onclick="orderBy('beneficiaryName');"><s:text name="ctm_collection_unit_name" />&nbsp;<img id="brandImg_beneficiaryName" src="${vix}/common/img/arrow.gif"></th>
			<th width="170" style="cursor: pointer;" onclick="orderBy('beneficiaryAccount');"><s:text name="ctm_collection_unit_account" />&nbsp;<img id="brandImg_beneficiaryAccount" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('paymentAmount');"><s:text name="ctm_payment_amount" />&nbsp;<img id="brandImg_paymentAmount" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('paymentTime');"><s:text name="ctm_payment_time" />&nbsp;<img id="brandImg_paymentTime" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('paymentPeople');"><s:text name="ctm_payer" />&nbsp;<img id="brandImg_paymentPeople" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('status');"><s:text name="ctm_state" />&nbsp;<img id="brandImg_status" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>	 --%>
					<td><a href="#" style="color: gray;">${entity.contractCode}</a></td>
					<td><a href="#" style="color: gray;">${entity.contractName}</a></td>
					<td><a href="#" style="color: gray;">${entity.beneficiaryName}</a></td>
					<td><a href="#" style="color: gray;">${entity.beneficiaryAccount}</a></td>
					<td><a href="#" style="color: gray;">${entity.paymentAmount}</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.paymentTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
					<td><a href="#" style="color: gray;">${entity.paymentPeople}</a></td>
					<td><a href="#" style="color: gray;">${entity.status}</a></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<span><img alt="" src="img/icon_untitled.png"></span>
							<div class="popup" style="display: none; top: -7px;">
								<strong> <i class="close"><a href="#" onclick="deleteEntity('${entity.id}');" title="删除"></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a></i> <b>${entity.contractCode}</b>
								</strong>
								<p>${entity.contractName}</p>
							</div>
						</div>
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
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
	</tbody>
</table>