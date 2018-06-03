<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该机构么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
</script>
<input type="hidden" id="totalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="pageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="totalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="orderField" value="${pager.orderField}" />
<input type="hidden" id="orderBy" value="${pager.orderBy}" />
<table id="table">
	<tr>
		<th onclick="orderBy('id');" width="5%">编号</th>
		<th onclick="orderBy('orgCode');" width="10%">机构编码</th>
		<th onclick="orderBy('orgName');" width="20%">机构名称</th>
		<th onclick="orderBy('address');" width="14%">地址</th>
		<th onclick="orderBy('employeeId');" width="8%">负责人</th>
		<th onclick="orderBy('taxNumber');" width="8%">税号</th>
		<th onclick="orderBy('homeCurrency');" width="8%">本币</th>
		<th onclick="orderBy('homeCurrencyCode');" width="8%">本币代码</th>
		<th onclick="orderBy('memo');" width="10%">备注</th>
		<th width="9%">操作</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
			<td>${entity.orgCode}</td>
			<td>${entity.orgName}</td>
			<td>${entity.address}</td>
			<td><s:property value="getEmployee(#entity.employeeId)" /></td>
			<td>${entity.taxNumber}</td>
			<td>${entity.homeCurrency}</td>
			<td>${entity.homeCurrencyCode}</td>
			<td>${entity.memo}</td>
			<td align="center"><a href="#" onclick="saveOrUpdate('${entity.id}');" title="修改"> <img src="${vix}/common/img/icon_01.png" />
			</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_02.png" />
			</a></td>
		</tr>
	</s:iterator>
</table>