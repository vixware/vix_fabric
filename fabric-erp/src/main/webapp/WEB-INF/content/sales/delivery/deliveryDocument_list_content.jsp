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
	asyncbox.confirm('确定要删除该发货单么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkDeliveryDocumentId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkDeliveryDocumentId']").attr("checked", true);
		}else{
			$("input[name='chkDeliveryDocumentId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkDeliveryDocumentId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCategoryCount1").html(selectCount);
	$("#selectCategoryCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkDeliveryDocumentIds']").attr("checked", false);
	}else{
		$("input[name='chkDeliveryDocumentIds']").attr("checked", true);
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
<input type="hidden" id="deliveryDocumentTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="deliveryDocumentPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="deliveryDocumentTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="deliveryDocumentOrderField" value="${pager.orderField}" />
<input type="hidden" id="deliveryDocumentOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="deliveryDocumentInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="10%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkDeliveryDocumentIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="15%"><s:text name="sa_invoicenumber" /></th>
			<th width="15%"><s:text name="sa_customername" /></th>
			<th width="10%"><s:text name="sa_typeofservice" /></th>
			<th width="10%">金额</th>
			<th width="10%"><s:text name="cmn_clerk" /></th>
			<th width="15%"><s:text name="sa_deliverydate" /></th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkDeliveryDocumentId" name="chkDeliveryDocumentId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<!--  	<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>-->
				<td><span style="color: gray;">${entity.ddCode }</span></td>
				<td><span style="color: gray;">${entity.customerAccount.name }</span></td>
				<td><span style="color: gray;">${entity.salesBusinessType.name }</span></td>
				<td><span style="color: gray;">${entity.amount }</span></td>
				<td><span style="color: gray;">${entity.salePerson.name}</span></td>
				<td width="90"><span style="color: gray;"><fmt:formatDate value="${entity.shippedDate}" type="both" pattern="yyyy-MM-dd" /></span></td>
				<td><a href="#" title="<s:text name='cmn_show'/>" onclick="showDelivery('${entity.id}');"> <img src="${vix}/common/img/icon_19.gif" />
				</a> <a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
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