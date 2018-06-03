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
	asyncbox.confirm('确定要删除么?','<s:text name='删除会议'/>',function(action){
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
			<th style="cursor: pointer;" onclick="orderBy('meetingName');">名称&nbsp;<img id="brandImg_meetingName" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('capacity');">可容纳人数&nbsp;<img id="brandImg_capacity" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('equipment');">设备情况&nbsp;<img id="brandImg_equipment" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="35%" style="cursor: pointer;" onclick="orderBy('description');">会议室描述&nbsp;<img id="brandImg_description" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('createTime');">创建时间&nbsp;<img id="brandImg_createTime" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('allowedStartTime');">可申请开始时间&nbsp;<img id="brandImg_allowedStartTime" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('allowedEndTime');">可申请结束时间&nbsp;<img id="brandImg_allowedEndTime" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="状态" /></th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.meetingName}</span></td>
				<td><span style="color: gray;">${entity.capacity}</span></td>
				<td><span style="color: gray;">${entity.equipment}</span></td>
				<td><span style="color: gray;">${entity.description}</span></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.allowedStartTime}" type="both" pattern="HH:mm:ss" /></a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.allowedEndTime}" type="both" pattern="HH:mm:ss" /></a></td>
				<td id="meetingRoomStatus_${entity.id}"><s:if test="%{#entity.meetingRoomStatus == 0}">
						<span style="color: DarkGreen;">空闲</span>
					</s:if> <s:elseif test="%{#entity.meetingRoomStatus == 1}">
						<span style="color: Blue;">占用</span>
					</s:elseif></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<a href="#" onclick="saveOrUpdate('${entity.id}');">修改</a> <a href="#" onclick="deleteEntity('${entity.id}');">删除</a>
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