<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该品牌么?','<s:text name='vix_message'/>',function(action){
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

/** 载入数据列排序图标 */
loadOrderByImage("${vix}","sample");
</script>
<input type="hidden" id="sampleTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="samplePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="sampleTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="sampleOrderField" value="${pager.orderField}" />
<input type="hidden" id="sampleOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="sampleInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th style="cursor: pointer;" onclick="orderBy('productName');">名称&nbsp;<img id="sampleImg_productName" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('specificationsModel');">规格型号&nbsp;<img id="sampleImg_specificationsModel" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('measurementUnit');">计量单位&nbsp;<img id="sampleImg_measurementUnit" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('sampleCycle');">留样周期&nbsp;<img id="sampleImg_sampleCycle" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('minimumSamplesQuantity');">最少留样量&nbsp;<img id="sampleImg_minimumSamplesQuantity" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('samplesDate');">留样时间&nbsp;<img id="sampleImg_samplesDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="cmn_operate" /></th>
			<% int count = 0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.productName}</a></td>
					<td><a href="#" style="color: gray;">${entity.specificationsModel}</a></td>
					<td><a href="#" style="color: gray;">${entity.measurementUnit}</a></td>
					<td><a href="#" style="color: gray;">${entity.sampleCycle}</a></td>
					<td><a href="#" style="color: gray;">${entity.minimumSamplesQuantity}</a></td>
					<td><a href="#" style="color: gray;"> <fmt:formatDate value="${entity.samplesDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
					</a></td>
					<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='修改'/>"> <img src="${vix}/common/img/icon_edit.png" />
					</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='删除'/>"> <img src="${vix}/common/img/icon_12.png" />
					</a></td>
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
				</tr>
			</c:forEach>
	</tbody>
</table>