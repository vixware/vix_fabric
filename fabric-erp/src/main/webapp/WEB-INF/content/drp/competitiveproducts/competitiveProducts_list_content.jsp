<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/competitiveProductsAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/drp/competitiveProductsAction!goSingleList.action", 'competingProducts');
				}
				});
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

	loadOrderByImage("${vix}", "competingProducts");
</script>
<input type="hidden" id="competingProductsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="competingProductsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="competingProductsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="competingProductsOrderField" value="${pager.orderField}" />
<input type="hidden" id="competingProductsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="competingProductsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th><span style="cursor: pointer;" onclick="orderBy('name');"><s:text name="drp_name" />&nbsp;<img id="competingProductsImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('productEnterprise');"><s:text name="drp_manufacturers" />&nbsp;<img id="competingProductsImg_productEnterprise" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('specification');"><s:text name="drp_specifications" />&nbsp;<img id="competingProductsImg_specification" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('price');"><s:text name="drp_price" />&nbsp;<img id="competingProductsImg_price" src="${vix}/common/img/arrow.gif"></span></th>
			<th><s:text name="cmn_operate" /></th>
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
				<td><a href="#" style="color: gray;">${entity.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.productEnterprise }</a></td>
				<td><a href="#" style="color: gray;">${entity.specification }</a></td>
				<td><a href="#" style="color: gray;">${entity.price }</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="<s:text name='cmn_show'/>" onclick="showOrder('${entity.id}');"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
								</a> </i> <i> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div>
				</td>
			</tr>
		</s:iterator>
		<%
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