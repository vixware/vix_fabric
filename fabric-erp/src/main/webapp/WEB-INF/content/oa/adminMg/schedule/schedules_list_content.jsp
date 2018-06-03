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
	asyncbox.confirm('确定要删除该日程么?','<s:text name='日程'/>',function(action){
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
loadOrderByImage("${vix}","voting");
</script>
<input type="hidden" id="votingTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="votingPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="votingTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="votingOrderField" value="${pager.orderField}" />
<input type="hidden" id="votingOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="votingInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
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
			<th width="30%" style="cursor: pointer;" onclick="orderBy('name');">主题&nbsp;<img id="votingImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="40%" style="cursor: pointer;" onclick="orderBy('calendarsContent');">内容&nbsp;<img id="votingImg_calendarsContent" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('startTime');">开始日期&nbsp;<img id="votingImg_startTime" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('endTime');">结束日期&nbsp;<img id="votingImg_endTime" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="7%"><s:text name="类型" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.scheduleName }</span></td>
				<td><span style="color: gray;">${entity.calendarsContent }</span></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.startTime }" type="both" pattern="yyyy-MM-dd" /></span></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.endTime }" type="both" pattern="yyyy-MM-dd" /></span></td>
				<td><s:if test="#entity.view == 'month'">月期事物</s:if> <s:elseif test="#entity.view == 'agendaWeek'">周期事物</s:elseif> <s:elseif test="#entity.view == 'agendaDay'">日常事物</s:elseif></td>
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