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
	asyncbox.confirm('确定要删除该车辆么?','<s:text name='车辆'/>',function(action){
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
loadOrderByImage("${vix}","car");
</script>
<input type="hidden" id="carTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="carPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="carTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="carOrderField" value="${pager.orderField}" />
<input type="hidden" id="carOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="carInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th>车牌号</th>
			<th style="cursor: pointer;" onclick="orderBy('maintenanceDate');">维护日期&nbsp;<img id="carImg_maintenanceDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="30%" style="cursor: pointer;" onclick="orderBy('maintenanceReason');">维护原因&nbsp;<img id="carImg_maintenanceReason" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('uploadPersonName');">经办人&nbsp;<img id="carImg_uploadPersonName" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('maintenanceCost');">维护费用&nbsp;<img id="carImg_maintenanceCost" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('maintenance');">维护情况&nbsp;<img id="carImg_maintenance" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="状态" /></th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.vehicleRequest.plateNumber}</span></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.maintenanceDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td><span style="color: gray;">${entity.maintenanceReason}</span></td>
				<td><span style="color: gray;">${entity.uploadPersonName}</span></td>
				<td><span style="color: gray;">${entity.maintenanceCost}元</span></td>
				<td><span style="color: gray;">${entity.maintenance}</span></td>
				<td><s:if test="%{#entity.vehicleRequest.carSituation == 3}">
						<span style="color: DarkRed;">维修中</span>
					</s:if> <s:elseif test="%{#entity.vehicleRequest.carSituation == 0}">
						<span style="color: DarkGreen;">已经修好</span>
					</s:elseif></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<s:if test="%{#entity.vehicleRequest.carSituation == 3}">
							<a href="#" onclick="saveOrUpdateReturn('${entity.id}');"> <span style="color: red;">维修</span>
							</a>
						</s:if>
						<s:elseif test="%{#entity.vehicleRequest.carSituation == 0 }">
							<a href="#"> <span style="color: DarkRed;">完成</span>
							</a>
						</s:elseif>
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
			</tr>
		</c:forEach>
	</tbody>
</table>