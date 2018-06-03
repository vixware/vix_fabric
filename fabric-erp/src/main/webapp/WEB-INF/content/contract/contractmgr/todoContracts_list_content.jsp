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
	asyncbox.confirm('确定要删除该活动计划么?','提示信息',function(action){
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
			<th width="50">
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
			<th style="cursor: pointer;" onclick="orderBy('contractName');"><s:text name="合同名称" />&nbsp;<img id="categoryImg_contractName" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('contractType');"><s:text name="ctm_type" />&nbsp;<img id="categoryImg_contractType" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('projectName');"><s:text name="项目名称" />&nbsp;<img id="categoryImg_projectName" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('totalAmount');"><s:text name="ctm_amount" />&nbsp;<img id="categoryImg_totalAmount" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('operator');"><s:text name="ctm_agent" />&nbsp;<img id="categoryImg_operator" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('approval');"><s:text name="ctm_for" />&nbsp;<img id="categoryImg_approval" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('signDate');"><s:text name="ctm_signing_date" />&nbsp;<img id="categoryImg_signDate" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('isPublish');"><s:text name="状态" />&nbsp;<img id="categoryImg_isPublish" src="${vix}/common/img/arrow.gif"></th>
			<th width="80"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.contractName}</span></td>
				<td><a href="#" style="color: gray;"> <s:if test="%{#entity.contractType == 1}">采购合同</s:if> <s:elseif test="%{#entity.contractType == 2}">采购框架协议</s:elseif> <s:elseif test="%{#entity.contractType == 3}">销售合同</s:elseif> <s:elseif test="%{#entity.contractType == 4}">销售框架协议</s:elseif> <s:elseif test="%{#entity.contractType == 5}">项目合同</s:elseif>
				</a></td>
				<td><span style="color: gray;">${entity.projectName}</span></td>
				<td><a href="#" style="color: gray;">${entity.totalAmount}</a></td>
				<td><a href="#" style="color: gray;">${entity.operator}</a></td>
				<td><a href="#" style="color: gray;">${entity.approval}</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.signDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td id="isPublish_${entity.id}"><s:if test="%{#entity.isPublish == 0}">
						<span style="color: red;">待办</span>
					</s:if></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<s:if test="%{#entity.contractType==1 }">
							<a href="#" onclick="saveOrUpdate1('${entity.id}',${entity.contractType},${pager.pageNo});"> <span>查看</span>
							</a>
						</s:if>
						<s:elseif test="%{#entity.contractType==2 }">
							<a href="#" onclick="saveOrUpdate2('${entity.id}',${entity.contractType},${pager.pageNo});"> <span>查看</span>
							</a>
						</s:elseif>
						<s:elseif test="%{#entity.contractType==3 }">
							<a href="#" onclick="saveOrUpdate3('${entity.id}',${entity.contractType},${pager.pageNo});"> <span>查看</span>
							</a>
						</s:elseif>
						<s:elseif test="%{#entity.contractType==4 }">
							<a href="#" onclick="saveOrUpdate4('${entity.id}',${entity.contractType},${pager.pageNo});"> <span>查看</span>
							</a>
						</s:elseif>
						<s:elseif test="%{#entity.contractType==5 }">
							<a href="#" onclick="saveOrUpdate5('${entity.id}',${entity.contractType},${pager.pageNo});"> <span>查看</span>
							</a>
						</s:elseif>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>