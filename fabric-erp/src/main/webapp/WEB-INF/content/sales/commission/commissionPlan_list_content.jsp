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
	asyncbox.confirm('确定要删除该佣金方案么?','<s:text name='vix_message'/>',function(action){
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
</script>
<input type="hidden" id="commissionPlanTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="commissionPlanPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="commissionPlanTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="commissionPlanOrderField" value="${pager.orderField}" />
<input type="hidden" id="commissionPlanOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="commissionPlanInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="10%">
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
			<th width="10%">编号</th>
			<th width="20%">销售组织</th>
			<th width="20%">计划编码</th>
			<th width="30%">计划名称</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td><span style="color: gray;">${entity.saleOrg.fs }</span></td>
				<td><span style="color: gray;">${entity.cpCode }</span></td>
				<td><span style="color: gray;">${entity.cpName }</span></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#" onclick="deleteEntity('${entity.id}');"></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a></i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
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
			</tr>
		</c:forEach>
	</tbody>
</table>