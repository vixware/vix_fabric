<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkCategoryId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkCategoryId']").attr("checked", true);
		}else{
			$("input[name='chkCategoryId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkCategoryId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCategoryCount1").html(selectCount);
	$("#selectCategoryCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkCategoryIds']").attr("checked", false);
	}else{
		$("input[name='chkCategoryIds']").attr("checked", true);
	}
}

</script>
<input type="hidden" id="areaExpensesStandardsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="areaExpensesStandardsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="areaExpensesStandardsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="areaExpensesStandardsOrderField" value="${pager.orderField}" />
<input type="hidden" id="areaExpensesStandardsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="areaExpensesStandardsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name='all' /> </a></li>
							<li><a href="#"><s:text name='other' /> </a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="15%">编码&nbsp;</th>
			<th onclick="orderBy('status');" width="15%">主题&nbsp;</th>
			<th onclick="orderBy('status');" width="15%">职位&nbsp;</th>
			<th onclick="orderBy('memo');" width="15%">报销标准&nbsp;</th>
			<th width="5%">操作</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.code}</td>
				<td>${entity.standardName}</td>
				<td>${entity.orgPosition.posName}</td>
				<td>${entity.reimbursementAmount}</td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="查看"></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="修改"></a></i> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="删除"></i> <b>${entity.id}</b>
							</strong>
							<p>${entity.code}</p>
						</div>
					</div>
				</td>
			</tr>
		</s:iterator>
		<%
			/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count", count);
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