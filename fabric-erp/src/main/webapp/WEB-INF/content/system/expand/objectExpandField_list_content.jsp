<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#objectExpandFieldTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#objectExpandFieldTable tr:even").addClass("alt");
</script>
<input type="hidden" id="objectExpandFieldTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="objectExpandFieldPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="objectExpandFieldTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="objectExpandFieldOrderField" value="${pager.orderField}" />
<input type="hidden" id="objectExpandFieldOrderBy" value="${pager.orderBy}" />
<table id="objectExpandFieldTable" class="list" style="width: 1340px;">
	<tr>
		<th width="80px"><span style="cursor: pointer;" onclick="orderBy('id');">编号&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('displayName');">显示名称&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="120px"><span style="cursor: pointer;" onclick="orderBy('objectExpand.name');">对象类型&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="220px"><span style="cursor: pointer;" onclick="orderBy('expandTableName');">扩展表&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('fieldName');">列名&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="160px"><span style="cursor: pointer;" onclick="orderBy('fieldType');">列类型&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('length');">长度&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="160px"><span style="cursor: pointer;" onclick="orderBy('defaultValue');">默认值&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('isRequired');">是否必填&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('isShow');">是否显示&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('orderNumber');">顺序&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="objectExpandFieldList" var="entity" status="s">
		<% count++; %>
		<tr style="text-align: right;">
			<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
			<td>${entity.displayName}</td>
			<td>${entity.objectExpand.name}</td>
			<td>${entity.expandTableName}</td>
			<td>${entity.fieldName}</td>
			<td><s:if test="#entity.fieldType == 'date'">
					日期
				</s:if> <s:elseif test="#entity.fieldType == 'text'">
					文本
				</s:elseif> <s:elseif test="#entity.fieldType == 'radio'">
					单选
				</s:elseif> <s:elseif test="#entity.fieldType == 'checkbox'">
					多选
				</s:elseif> <s:elseif test="#entity.fieldType == 'select'">
					下拉列表
				</s:elseif></td>
			<td>${entity.length}</td>
			<td>${entity.defaultValue}</td>
			<td><s:if test="#entity.isRequired == 0">
					否
				</s:if> <s:elseif test="#entity.isRequired == 1">
					是
				</s:elseif> <s:else>
					否
				</s:else></td>
			<td><s:if test="#entity.isShow == 0">
					否
				</s:if> <s:elseif test="#entity.isShow == 1">
					是
				</s:elseif> <s:else>
					否
				</s:else></td>
			<td>${entity.orderNumber}</td>
			<td align="center"><a href="#" onclick="saveOrUpdateDetail('${entity.id}');" title="修改"> <img src="${vix}/common/img/icon_edit.png" />
			</a> <a href="#" onclick="deleteExpandField(this,'${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
			</a></td>
		</tr>
	</s:iterator>
	<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
		com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
		count = pager.getPageSize() - count;
		request.setAttribute("count",count);
	%>
	<c:forEach begin="1" end="${count-6}">
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
		</tr>
	</c:forEach>
</table>
