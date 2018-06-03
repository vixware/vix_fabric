<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该条数据吗?', '<s:text name='vix_message'/>', function(action) {
			if (action == 'ok') {
				deleteById(id);
			}
		});
	}
	function chooseAll(chk) {
		if (null == chk) {
			$("input[name='chkId']").attr("checked", true);
		} else {
			if ($(chk).attr('checked')) {
				$("input[name='chkId']").attr("checked", true);
			} else {
				$("input[name='chkId']").attr("checked", false);
			}
		}
		selectCount();
	}

	function selectCount() {
		var selectCount = 0;
		$.each($("input[name='chkId']"), function(i, n) {
			if ($(n).attr('checked')) {
				selectCount++;
			}
		});
		$("#selectCount1").html(selectCount);
		$("#selectCount2").html(selectCount);
		if (selectCount == 0) {
			$("input[name='chkIds']").attr("checked", false);
		} else {
			$("input[name='chkIds']").attr("checked", true);
		}
	}
	loadOrderByImage("${vix}", "wimTransvouch");
</script>
<input type="hidden" id="wimTransvouchTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="wimTransvouchPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="wimTransvouchTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="wimTransvouchOrderField" value="${pager.orderField}" />
<input type="hidden" id="wimTransvouchOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="wimTransvouchInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /> </a></li>
							<li><a href="#"><s:text name="cmn_other" /> </a></li>
							<li><a href="#"><s:text name="cmn_close" /> </a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('itemcode');">商品编码&nbsp;<img id="wimTransvouchImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="30%"><span style="cursor: pointer;" onclick="orderBy('itemname');">商品名称&nbsp;<img id="wimTransvouchImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('unitcost');">单价&nbsp;<img id="wimTransvouchImg_outInvWarehouse" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('quantity');">数量&nbsp;<img id="wimTransvouchImg_inInvWarehouse" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('price');">金额&nbsp;<img id="wimTransvouchImg_tvdate" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('flag');">类型&nbsp;<img id="wimTransvouchImg_tvdate" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('createTime');">日期&nbsp;<img id="wimTransvouchImg_status" src="${vix}/common/img/arrow.gif"></span></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><a href="#" style="color: gray;">${entity.itemcode }</a></td>
				<td><a href="#" style="color: gray;">${entity.itemname }</a></td>
				<td><a href="#" style="color: gray;">${entity.unitcost }</a></td>
				<td><a href="#" style="color: gray;">${entity.quantity }</a></td>
				<td><a href="#" style="color: gray;">${entity.price }</a></td>
				<td><a href="#" style="color: gray;"><s:if test="%{#entity.flag==1}">入库</s:if> <s:elseif test="%{#entity.flag==2}">出库</s:elseif></a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>