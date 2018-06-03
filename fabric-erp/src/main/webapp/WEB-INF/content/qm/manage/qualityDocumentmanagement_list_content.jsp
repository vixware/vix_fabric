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
	asyncbox.confirm('确定要删除该品牌么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","category");
</script>
<input type="hidden" id="categoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="categoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="categoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="categoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="categoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="categoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th style="cursor: pointer;" onclick="orderBy('serialNumber');"><s:text name="qm_sort_no" />&nbsp;<img id="categoryImg_serialNumber" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('documentCode');"><s:text name="qm_document_encoding" />&nbsp;<img id="categoryImg_documentCode" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('documentName');"><s:text name="qm_document_name" />&nbsp;<img id="categoryImg_documentName" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="qm_document_author" /></th>
			<th><s:text name="qm_document_category" /></th>
			<th><s:text name="qm_approve_date" /></th>
			<th><s:text name="qm_distribution_date" /></th>
			<th onclick="orderBy('status');" width="10%"><span style="cursor: pointer;"> <s:text name="qm_whether_controlled" />&nbsp; <s:if test="%{pager.orderField == 'status' && pager.orderBy == 'asc' }">
					</s:if> <s:elseif test="%{pager.orderField == 'status' && pager.orderBy == 'desc' }">
					</s:elseif> <s:else>
					</s:else>
			</span></th>
			<th><s:text name="qm_send_departments" /></th>
			<th><s:text name="cmn_operate" /></th>
			<% int count = 0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.serialNumber}</a></td>
					<td><a href="#" style="color: gray;">${entity.documentCode}</a></td>
					<td><a href="#" style="color: gray;">${entity.documentName}</a></td>
					<td><a href="#" style="color: gray;">${entity.documentAuthor}</a></td>
					<td><a href="#" style="color: gray;">${entity.documentCategory}</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.approvalDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.issueDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td><s:if test="#entity.status == 0">
						是
					</s:if> <s:elseif test="#entity.status == 1">
						否
					</s:elseif></td>
					<td><a href="#" style="color: gray;">${entity.despatchDepartment}</a></td>
					<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="修改"> <img src="${vix}/common/img/icon_edit.png" />
					</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
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
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
	</tbody>
</table>