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
	asyncbox.confirm('确定要删除该会议设备么?','提示信息',function(action){
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
			<th width="160" style="cursor: pointer;" onclick="orderBy('deviceCode');">设备编号&nbsp;<img id="categoryImg_deviceCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('deviceName');">设备名称&nbsp;<img id="categoryImg_deviceName" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="设备状态" /></th>
			<th><s:text name="同类设备名称" /></th>
			<th style="cursor: pointer;" onclick="orderBy('deviceDescribe');">设备描述&nbsp;<img id="categoryImg_deviceDescribe" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="120"><s:text name="cmn_operate" /></th>
			<% int count = 0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.deviceCode}</a></td>
					<td><a href="#" style="color: gray;">${entity.deviceName}</a></td>
					<%-- <td><a href="#" style="color: gray;">${entity.meetingRequest.name}</a></td> --%>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.deviceStatus==0}">可用</s:if> <s:elseif test="%{#entity.deviceStatus==1}">不可用</s:elseif>
					</a></td>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.similarEquipment==0}">没有</s:if> <s:elseif test="%{#entity.similarEquipment==1}">有</s:elseif>
					</a></td>
					<td><a href="#" style="color: gray;">${entity.deviceDescribe}</a></td>
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
					<!-- <td>&nbsp;</td> -->
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