<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
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
	asyncbox.confirm('确定要删除该部门么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
</script>
<input type="hidden" id="departmentTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="departmentPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="departmentTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="departmentOrderField" value="${pager.orderField}" />
<input type="hidden" id="departmentOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="departmentInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" value="" name=""></label>
						<ul>
							<li><a href="#">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');">编号&nbsp;<img src="img/arrow.gif"></th>
			<th onclick="orderBy('code');">编码&nbsp;<img src="img/arrow.gif"></th>
			<th onclick="orderBy('name');">名称&nbsp;<img src="img/arrow.gif"></th>
			<th onclick="orderBy('telephone');">电话&nbsp;<img src="img/arrow.gif"></th>
			<th onclick="orderBy('employeeId');">负责人</th>
			<th onclick="orderBy('parentDepartment.id');">上级部门&nbsp;<img src="img/arrow.gif"></th>
			<th onclick="orderBy('fax');">传真&nbsp;<img src="img/arrow.gif"></th>
			<th onclick="orderBy('email');">邮件&nbsp;<img src="img/arrow.gif"></th>
			<th onclick="orderBy('memo');">备注&nbsp;<img src="img/arrow.gif"></th>
			<th>操作</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><input type="checkbox" value="" name=""></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.telephone}</td>
				<td><s:property value="getEmployee(#entity.employeeId)" /></td>
				<td>${entity.parentDepartment.name}</td>
				<td>${entity.fax}</td>
				<td>${entity.email}</td>
				<td>${entity.memo}</td>
				<td align="center"><a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
				</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>