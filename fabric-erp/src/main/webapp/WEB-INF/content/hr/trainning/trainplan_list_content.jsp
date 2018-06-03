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
			<th style="cursor: pointer;" onclick="orderBy('applicationName');">计划主题&nbsp;<img id="categoryImg_applicationName" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('orgUnitAndLeadingOfficial');">负责部门或负责人&nbsp;<img id="categoryImg_orgUnitAndLeadingOfficial" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('planLevel');">计划级别&nbsp;<img id="categoryImg_planLevel" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('planningNature');">计划性质&nbsp;<img id="categoryImg_planningNature" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('planType');">计划类型&nbsp;<img id="categoryImg_planType" src="${vix}/common/img/arrow.gif">
			</th>
			<th>提出计划时间</th>
			<th>计划状态</th>
			<th width="40"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.applicationName}</span></td>
				<td><span style="color: gray;">${entity.orgUnitAndLeadingOfficial}</span></td>
				<td width="70"><span style="color: gray;"> <c:if test="${null == entity.planLevel }">
							无级别
						</c:if> <c:if test="${entity.planLevel == '1' }">
							新
						</c:if> <c:if test="${entity.planLevel == '2' }">
							低
						</c:if> <c:if test="${entity.planLevel == '3' }">
							中
						</c:if> <c:if test="${entity.planLevel == '4' }">
							高
						</c:if>
				</span></td>
				<td width="70"><span style="color: gray;"> <c:if test="${null == entity.planType }">
							无计划
						</c:if> <c:if test="${entity.planType == '1' }">
							短期计划
						</c:if> <c:if test="${entity.planType == '2' }">
							中期计划
						</c:if> <c:if test="${entity.planType == '3' }">
							长期计划
						</c:if>
				</span></td>
				<td width="70"><span style="color: gray;"> <c:if test="${null == entity.planningNature }">
							无性质
						</c:if> <c:if test="${entity.planningNature == '1' }">
							非定向
						</c:if> <c:if test="${entity.planningNature == '2' }">
							定向
						</c:if>
				</span></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.proposedTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td width="70"><span style="color: gray;"> <c:if test="${null == entity.planStatus }">
							无状态
						</c:if> <c:if test="${entity.planStatus == '1' }">
							执行
						</c:if> <c:if test="${entity.planStatus == '2' }">
							未执行
						</c:if>
				</span></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#" onclick="deleteEntity('${entity.id}');"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}',$('#parentId').val());" title="<s:text name='查看修改'/>"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}',$('#parentId').val());" title="<s:text name='cmn_update'/>"></a></i> <!-- 小i标题 -->
								<b>${entity.applicationName}</b>
							</strong>
							<!-- 小i内容 -->
							<p>${entity.remarks}</p>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>