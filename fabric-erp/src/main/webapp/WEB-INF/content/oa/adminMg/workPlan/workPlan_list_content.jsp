<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("workPlan tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("workPlan tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
   
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
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
loadOrderByImage("${vix}","workLog");
</script>
<input type="hidden" id="workLogTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="workLogPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="workLogTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="workLogOrderField" value="${pager.orderField}" />
<input type="hidden" id="workLogOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="workLogInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="workPlan" class="list">
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
			<th style="cursor: pointer;" onclick="orderBy('proposalTitle');"><s:text name="oa_plan_name" />&nbsp;<img id="workLogImg_proposalTitle" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('initiateTime');"><s:text name="oa_start_time" />&nbsp;<img id="workLogImg_initiateTime" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('overTime');"><s:text name="oa_cut-off_time" />&nbsp;<img id="workLogImg_overTime" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="oa_program_categories" /></th>
			<th style="cursor: pointer;" onclick="orderBy('bizOrgNames');"><s:text name="发布范围" />&nbsp;<img id="workLogImg_bizOrgNames" src="${vix}/common/img/arrow.gif"></th>
			<%-- <th style="cursor: pointer;" onclick="orderBy('bizOrgNames1');">
				<s:text name="oa_person_in_charge"/>&nbsp;<img id="workLogImg_bizOrgNames2" src="${vix}/common/img/arrow.gif">
			</th> --%>
			<th><s:text name="进度" /></th>
			<th><s:text name="oa_state" /></th>
			<th><s:text name="oa_operating" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><a href="#" style="color: gray;">${entity.proposalTitle}</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.initiateTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.overTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td id="workPlan_${entity.id}"><s:if test="%{#entity.workPlan == 0}">今日计划</s:if> <s:elseif test="%{#entity.workPlan == 1}">本周计划</s:elseif> <s:elseif test="%{#entity.workPlan == 2}">本月计划</s:elseif></td>
				<td><a href="#" style="color: gray;">${entity.bizOrgNames}</a></td>
				<%-- <td><a href="#" style="color: gray;">${entity.bizOrgNames1}</a></td> --%>
				<%-- <td><a href="#" style="color: gray;"> ${entity.progressLog.percent}</a></td> --%>
				<td>12%</td>
				<td><a href="#" style="color: gray;"> <s:if test="%{#entity.isPublish == 0}">进行中</s:if> <s:elseif test="%{#entity.isPublish == 1}">暂停</s:elseif>
				</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<a href="javascript:void(0);" onclick="lookPostilOrPlan('${entity.id}');">查看</a>
					</div>
				</td>
				<%-- <td>					
					<div class="untitled" style="position: static;display: inline;">
						<s:if test="tag != 'choose'">
						<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());">进度日志</a>
						</s:if>
					</div>
				</td> --%>
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