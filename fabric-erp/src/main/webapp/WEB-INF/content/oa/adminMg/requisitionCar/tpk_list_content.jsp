<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"> 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该车辆油耗信息么?','<s:text name='车辆油耗'/>',function(action){
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
loadOrderByImage("${vix}","tpk");
</script>
<input type="hidden" id="tpkTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="tpkPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="tpkTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="tpkOrderField" value="${pager.orderField}" />
<input type="hidden" id="tpkOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="tpkInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th style="cursor: pointer;" onclick="orderBy('carname');">司机姓名&nbsp;<img id="tpkImg_carname" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('mileage');">里程数（公里）&nbsp;<img id="tpkImg_mileage" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('guzzling');">耗油数（升）&nbsp;<img id="tpkImg_guzzling" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('guzzlingNumber');">耗油（升/公里）&nbsp;<img id="tpkImg_guzzlingNumber" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td> --%>
				<td><span style="color: gray;">${entity.vehicleRequest.model}</span></td>
				<td><span style="color: gray;">${entity.carname }</span></td>
				<td><span style="color: gray;">${entity.mileage }</span></td>
				<td><span style="color: gray;">${entity.guzzling }</span></td>
				<td><span style="color: gray;">${entity.guzzlingNumber }</span></td>
				<td>
					<div class="untitled">
						<s:if test="tag != 'choose'">
							<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='修改'/>"> <img src="${vix}/common/img/icon_19.gif" />
							</a>
						</s:if>
						<a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
						</a>
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