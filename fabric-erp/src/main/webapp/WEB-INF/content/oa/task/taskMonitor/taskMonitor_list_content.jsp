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
	asyncbox.confirm('确定要删除任务么?','提示信息',function(action){
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

function taskView(id){
	$.ajax({
		  url:'${vix}/oa/taskDefineAction!goTaskView.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}
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
			<%-- <th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all"/></a></li>
							<li><a href="#"><s:text name="cmn_other"/></a></li>
							<li><a href="#"><s:text name="cmn_close"/></a></li>
						</ul>
					</div>
				</div>
			</th> --%>
			<th width="360"><s:text name="任务名称" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
			<th width="150"><s:text name="执行人/部门" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
			<th width="80"><s:text name="开始时间" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
			<th width="80"><s:text name="结束时间" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
			<th width="50"><s:text name="进度" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
			<th width="50"><s:text name="状态" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
			<th width="98"><s:text name="oa_operating" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
			<% int count = 0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<%-- <td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()"/></td> --%>
					<td><a href="#" style="color: gray;">${entity.questName}</a></td>
					<td><a href="#" style="color: gray;">${entity.executiveAgency}</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.startTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.endTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td id="schedule_${entity.id}"><s:if test="%{#entity.schedule <= 50}">
							<span style="color: red;">${entity.schedule}%</span>
						</s:if> <s:elseif test="%{#entity.schedule >= 51 && #entity.schedule <=80 }">
							<span style="color: orange;">${entity.schedule}%</span>
						</s:elseif> <s:elseif test="%{#entity.schedule >= 81 && #entity.schedule <=99 }">
							<span style="color: blue;">${entity.schedule}%</span>
						</s:elseif> <s:elseif test="%{#entity.schedule == 100}">
							<span style="color: green;">${entity.schedule}%</span>
						</s:elseif></td>
					<td id="isPublish_${entity.id}"><s:if test="%{#entity.isPublish == 1}">终止</s:if> <s:elseif test="%{#entity.isPublish == 0}">生效</s:elseif></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<a href="#" onclick="taskView('${entity.id}');">任务过程查看</a>
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
				</tr>
			</c:forEach>
	</tbody>
</table>