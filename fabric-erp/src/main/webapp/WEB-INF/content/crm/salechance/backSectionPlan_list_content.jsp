<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
function deleteEntity(id){
	asyncbox.confirm('确定要删除该回款计划么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","backSectionPlan");
</script>
<input type="hidden" id="backSectionPlanTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="backSectionPlanPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="backSectionPlanTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="backSectionPlanOrderField" value="${pager.orderField}" />
<input type="hidden" id="backSectionPlanOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="backSectionPlanInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th onclick="orderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="backSectionPlanImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('amount');" width="10%">金额&nbsp;<img id="backSectionPlanImg_amount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('backSectionPlanDate');" width="10%">计划回款日期&nbsp;<img id="backSectionPlanImg_backSectionPlanDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="15%">客户&nbsp;<img id="backSectionPlanImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('stageNumber');" width="10%">期次&nbsp;<img id="backSectionPlanImg_stageNumber" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('backSectionPlanStatus');" width="15%">是否回款&nbsp;<img id="backSectionPlanImg_backSectionPlanStatus" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('owner.name');" width="15%">所有人&nbsp;<img id="backSectionPlanImg_owner" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.amount}</td>
				<td><s:property value="formatDateToString(#entity.backSectionPlanDate)" /></td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.stageNumber}</td>
				<td><s:if test="#entity.backSectionPlanStatus == 0">
						未回
					</s:if> <s:elseif test="#entity.backSectionPlanStatus == 1">
						已回
					</s:elseif></td>
				<td>${entity.owner.name}</td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
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
			</tr>
		</c:forEach>
	</tbody>
</table>