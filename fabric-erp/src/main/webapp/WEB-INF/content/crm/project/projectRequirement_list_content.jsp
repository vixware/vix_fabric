<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该项目需求么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","projectRequirement");
</script>
<input type="hidden" id="projectRequirementTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="projectRequirementPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="projectRequirementTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="projectRequirementOrderField" value="${pager.orderField}" />
<input type="hidden" id="projectRequirementOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="projectRequirementInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th onclick="orderBy('id');" width="10%" style="cursor: pointer;"><s:text name="cmn_id" />&nbsp;<img id="projectRequirementImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('subject');" width="20%" style="cursor: pointer;">主题&nbsp;<img id="projectRequirementImg_subject" src="${vix}/common/img/arrow.gif" style="cursor: pointer;">
			</th>
			<th onclick="orderBy('crmProject.name');" width="15%" style="cursor: pointer;">项目&nbsp;<img id="projectRequirementImg_crmProject" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="15%" style="cursor: pointer;">客户&nbsp;<img id="projectRequirementImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('provider.name');" width="10%" style="cursor: pointer;">提供人&nbsp;<img id="projectRequirementImg_provider" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('recordDate');" width="10%" style="cursor: pointer;">日期&nbsp;<img id="projectRequirementImg_recordDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.subject}</td>
				<td>${entity.crmProject.subject}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.provider.name}</td>
				<td><s:property value='formatDateToString(#entity.recordDate)' /></td>
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