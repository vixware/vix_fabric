<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该条数据吗?', '<s:text name='vix_message'/>', function(action) {
			if (action == 'ok') {
				deleteById(id);
			}
		});
	}
	function deleteById(id) {
		$.ajax({
		url : '${vix}/business/itemcatsDownloadAction!deleteById.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/business/itemcatsDownloadAction!goSingleList.action?1=1", 'goodsType');
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
	loadOrderByImage("${vix}", "goodsType");
</script>
<input type="hidden" id="goodsTypeTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="goodsTypePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="goodsTypeTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="goodsTypeOrderField" value="${pager.orderField}" />
<input type="hidden" id="goodsTypeOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="goodsTypeInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th onclick="orderBy('id');" width="10%"><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="outBoundImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="75%">名称</th>
			<th width="5%">来源商城</th>
			<!-- <th>类型属性</th> -->
			<!-- <th>类型规格</th> -->
			<th width="5%"><s:text name="cmn_operate" /></th>
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
				<td><a href="#" style="color: gray;">${entity.typeId }</a></td>
				<td><a href="#" style="color: gray;">${entity.typeName }</a></td>
				<td><a href="#" style="color: gray;"><s:if test="%{#entity.channelTypeId==1}">淘宝</s:if> <s:elseif test="%{#entity.channelTypeId==2}">拍拍</s:elseif> <s:elseif test="%{#entity.channelTypeId==3}">京东</s:elseif></a></td>
				<%-- <td><a href="#" style="color: gray;">${entity.typeProps }</a></td> --%>
				<%-- <td><a href="#" style="color: gray;">${entity.typeSpec }</a></td> --%>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.typeId}</b>
							</strong>
							<p>${entity.typeName}</p>
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
			</tr>
		</c:forEach>
	</tbody>
</table>