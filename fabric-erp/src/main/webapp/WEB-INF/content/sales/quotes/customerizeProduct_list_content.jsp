<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#customerizeProductTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#customerizeProductTable tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteCustomerizeProductEntity(id){
	asyncbox.confirm('确定要删除该需求方案么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/sales/quotes/customerizeProductAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						pager("start","${vix}/sales/quotes/customerizeProductAction!goListContent.action?projectQuotationSchemeId="+$("#id").val(),'customerizeProduct');
					});
				  }
			});
		}
	});
}

function chooseCPAll(chk){
	if(null == chk){
		$("input[name='chkcpId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkcpId']").attr("checked", true);
		}else{
			$("input[name='chkcpId']").attr("checked", false);
		}
	}
	selectCPCount();
}

function selectCPCount(){
	var selectCount = 0;
	$.each($("input[name='chkcpId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCountCustomerizeProduct1").html(selectCount);
	$("#selectCountCustomerizeProduct2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkcpIds']").attr("checked", false);
	}else{
		$("input[name='chkcpIds']").attr("checked", true);
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
loadOrderByImage("${vix}","customerizeProduct");
</script>
<input type="hidden" id="customerizeProductTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerizeProductPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerizeProductTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerizeProductOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerizeProductOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerizeProductInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="customerizeProductTable" class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkcpIds" onchange="chooseCPAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseCPAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="customerizeProductOrderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="customerizeProductImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="customerizeProductOrderBy('name');" width="10%">主题&nbsp;<img id="customerizeProductImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="customerizeProductOrderBy('billDate');" width="10%">单据日期&nbsp;<img id="customerizeProductImg_billDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="customerizeProductOrderBy('requirementType');" width="15%">需求类型&nbsp;<img id="customerizeProductImg_requirementType" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkcpId" name="chkcpId" value="${entity.id}" type="checkbox" onchange="selectCPCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td><s:property value="formatDateToString(#entity.billDate)" /></td>
				<td>${entity.requirementType}</td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteCustomerizeProductEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
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
			</tr>
		</c:forEach>
	</tbody>
</table>