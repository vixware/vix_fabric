<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">


function deleteEntity(id){
	asyncbox.confirm('确定要删除该销售计划么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","salePlan");
</script>
<input type="hidden" id="salePlanTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salePlanPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salePlanTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salePlanOrderField" value="${pager.orderField}" />
<input type="hidden" id="salePlanOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salePlanInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="8%"><s:text name="cmn_id" />&nbsp;<img id="salePlanImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('code');" width="8%">编码&nbsp;<img id="salePlanImg_code" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('name');" width="12%">名称&nbsp;<img id="salePlanImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('specifications');" width="10%">规格&nbsp;<img id="salePlanImg_specifications" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('amount');" width="8%">数量&nbsp;<img id="salePlanImg_amount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('time');" width="5%">年&nbsp;<img id="salePlanImg_time" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('time');" width="5%">月&nbsp;<img id="salePlanImg_time" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('departmet.fs');" width="8%">部门&nbsp;<img id="salePlanImg_departmet" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('salesMan.name');" width="8%">计划人&nbsp;<img id="salePlanImg_salesMan" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('time');" width="10%">日期&nbsp;<img id="salePlanImg_time" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('status');" width="8%">状态&nbsp;<img id="salePlanImg_status" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.specifications}</td>
				<td>${entity.amount}</td>
				<td>${fn:substring(entity.time, 0,4)}</td>
				<td>${fn:substring(entity.time, 5,7)}</td>
				<td>${entity.departmet.fs}</td>
				<td>${entity.salesMan.name}</td>
				<td><s:property value="formatDateToString(#entity.time)" /></td>
				<td><s:if test="#entity.status == 0">
						废弃
					</s:if> <s:elseif test="#entity.status == 1">
						正常
					</s:elseif></td>
				<td><i><a href="#" onclick="showOrder('${entity.id}');" title="<s:text name='查看'/>"><img src="${vix}/common/img/icon_19.gif" /></a></i> <a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>