<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该职员么?','提示信息',function(action){
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
		<th onclick="orderBy('id');" width="10%">编号</th>
		<th onclick="orderBy('code');" width="8%">编码</th>
		<th onclick="orderBy('name');" width="10%">名称</th>
		<th onclick="orderBy('birthday');" width="6%">生日</th>
		<th onclick="orderBy('gender');" width="5%">性别</th>
		<th onclick="orderBy('isMarriage');" width="5%">婚否</th>
		<th onclick="orderBy('graduation');" width="10%">院校</th>
		<th onclick="orderBy('professional');" width="10%">专业</th>
		<th onclick="orderBy('currentResidence');" width="10%">住址</th>
		<th onclick="orderBy('national');" width="10%">民族</th>
		<th onclick="orderBy('isDemission');" width="8%">是否离职</th>
		<th width="8%">操作</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
			<td>${entity.code}</td>
			<td>${entity.name}</td>
			<td><s:property value="formatDateToString(#entity.birthdayString)" /></td>
			<td><s:if test="#entity.gender == 0">
					女
				</s:if> <s:elseif test="#entity.gender == 1">
					男
				</s:elseif></td>
			<td><s:if test="#entity.isMarriage == 0">
					未婚
				</s:if> <s:elseif test="#entity.isMarriage == 1">
					已婚
				</s:elseif></td>
			<td>${entity.graduation}</td>
			<td>${entity.professional}</td>
			<td>${entity.currentResidence}</td>
			<td>${entity.national}</td>
			<td><s:if test="#entity.isDemission == 0">
					在职
				</s:if> <s:elseif test="#entity.isDemission == 1">
					离职
				</s:elseif></td>
			<td align="center"><a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_01.png" alt="修改" />
			</a> <a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_02.png" alt="删除" />
			</a></td>
		</tr>
	</s:iterator>
</table>