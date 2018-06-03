<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function chooseAll(chk) {
		if (null == chk) {
			$("input[name='chkCategoryId']").attr("checked", true);
		} else {
			if ($(chk).attr('checked')) {
				$("input[name='chkCategoryId']").attr("checked", true);
			} else {
				$("input[name='chkCategoryId']").attr("checked", false);
			}
		}
		selectCount();
	}

	function selectCount() {
		var selectCount = 0;
		$.each($("input[name='chkCategoryId']"), function(i, n) {
			if ($(n).attr('checked')) {
				selectCount++;
			}
		});
		$("#selectCategoryCount1").html(selectCount);
		$("#selectCategoryCount2").html(selectCount);
		if (selectCount == 0) {
			$("input[name='chkCategoryIds']").attr("checked", false);
		} else {
			$("input[name='chkCategoryIds']").attr("checked", true);
		}
	}
	loadOrderByImage("${vix}", "postageRecords");
</script>
<input type="hidden" id="postageRecordsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="postageRecordsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="postageRecordsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="postageRecordsOrderField" value="${pager.orderField}" />
<input type="hidden" id="postageRecordsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="postageRecordsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('order.outerId');">订单编码&nbsp;<img id="postageRecordsImg_order" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('order.shippingNo');">快递单编码&nbsp;<img id="postageRecordsImg_order" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('logistics.name');">快递公司&nbsp;<img id="postageRecordsImg_logistics" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('weight');">包裹重量&nbsp;<img id="postageRecordsImg_weight" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('expressFee');">费用&nbsp;<img id="postageRecordsImg_expressFee" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('createTime');">取件时间&nbsp;<img id="postageRecordsImg_createTime" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td>${entity.order.outerId}</td>
				<td>${entity.order.shippingNo}</td>
				<td>${entity.logistics.name}</td>
				<td>${entity.weight}(克)</td>
				<td>${entity.expressFee}(元)</td>
				<td>${entity.createTime}</td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="查看"></a> </i> <i><a href="#" onclick="goSaveOrUpdate('${entity.id}',$('#selectId').val());" title="修改"></a></i> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="删除"></i> <b>${entity.id}</b>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>