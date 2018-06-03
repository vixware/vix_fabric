<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#projectQuotationSchemeItemTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#projectQuotationSchemeItemTable tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteProjectQuotationSchemeItemEntity(id){
	asyncbox.confirm('确定要删除该销售报价单明细么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/sales/quotes/projectQuotationSchemeItemAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						pager("start","${vix}/sales/quotes/projectQuotationSchemeItemAction!goListContent.action?projectQuotationSchemeId="+$("#id").val(),'projectQuotationSchemeItem');
					});
				  }
			});
		}
	});
}

function choosePQSIAll(chk){
	if(null == chk){
		$("input[name='chkpqsiId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkpqsiId']").attr("checked", true);
		}else{
			$("input[name='chkpqsiId']").attr("checked", false);
		}
	}
	selectPQSICount();
}

function selectPQSICount(){
	var selectCount = 0;
	$.each($("input[name='chkpqsiId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectProjectQuotationSchemeItemCount1").html(selectCount);
	$("#selectProjectQuotationSchemeItemCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkpqsiIds']").attr("checked", false);
	}else{
		$("input[name='chkpqsiIds']").attr("checked", true);
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
loadOrderByImage("${vix}","projectQuotationSchemeItem");
</script>
<input type="hidden" id="projectQuotationSchemeItemTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="projectQuotationSchemeItemPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="projectQuotationSchemeItemTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="projectQuotationSchemeItemOrderField" value="${pager.orderField}" />
<input type="hidden" id="projectQuotationSchemeItemOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="projectQuotationSchemeItemInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="projectQuotationSchemeItemTable" class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkpqsiIds" onchange="choosePQSIAll(this)"></label>
						<ul>
							<li><a href="#" onclick="choosePQSIAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="projectQuotationSchemeItemOrderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="projectQuotationSchemeItemImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="projectQuotationSchemeItemOrderBy('name');" width="20%">主题&nbsp;<img id="projectQuotationSchemeItemImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="projectQuotationSchemeItemOrderBy('item.name');" width="10%">${vv:varView('vix_mdm_item')}&nbsp;<img id="projectQuotationSchemeItemImg_item" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="projectQuotationSchemeItemOrderBy('quotationCharger.name');" width="10%">负责人&nbsp;<img id="projectQuotationSchemeItemImg_quotationCharger" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="projectQuotationSchemeItemOrderBy('amount');" width="10%">数量&nbsp;<img id="projectQuotationSchemeItemImg_amount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="projectQuotationSchemeItemOrderBy('unit');" width="10%">单位&nbsp;<img id="projectQuotationSchemeItemImg_unit" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="projectQuotationSchemeItemOrderBy('price');" width="10%">价格&nbsp;<img id="projectQuotationSchemeItemImg_price" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkpqsiId" name="chkpqsiId" value="${entity.id}" type="checkbox" onchange="selectPQSICount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td>${entity.item.name}</td>
				<td>${entity.quotationCharger.name}</td>
				<td>${entity.amount}</td>
				<td>${entity.unit}</td>
				<td>${entity.price}</td>
				<td><a href="#" onclick="saveOrUpdateProjectQuotationSchemeItem('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteProjectQuotationSchemeItemEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>