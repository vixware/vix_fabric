<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/storeInformationAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/drp/storeInformationAction!goSingleList.action", 'channelDistributor');
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
	/** 载入数据列排序图标 */
	loadOrderByImage("${vix}", "channelDistributor");
</script>
<input type="hidden" id="channelDistributorTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="channelDistributorPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="channelDistributorTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="channelDistributorOrderField" value="${pager.orderField}" />
<input type="hidden" id="channelDistributorOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="channelDistributorInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="15%" style="cursor: pointer;" onclick="orderBy('code');"><s:text name="drp_store_coding" />&nbsp;<img id="channelDistributorImg_code" src="${vix}/common/img/arrow.gif"></th>
			<th width="25%" style="cursor: pointer;" onclick="orderBy('name');"><s:text name="drp_name" />&nbsp;<img id="channelDistributorImg_name" src="${vix}/common/img/arrow.gif"></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('telephone');"><s:text name="drp_telephone" />&nbsp;<img id="channelDistributorImg_telephone" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('level');"><s:text name="drp_store_level" />&nbsp;<img id="channelDistributorImg_level" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><span style="cursor: pointer;" onclick="orderBy('status');"><s:text name="drp_state" />&nbsp;<img id="channelDistributorImg_status" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;">${entity.code }</a></td>
				<td><a href="#" style="color: gray;">${entity.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.telephone}</a></td>
				<td><a href="#" style="color: gray;">${entity.level }</a></td>
				<td><a href="#" style="color: gray;">${entity.status }</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
								</a> </i> <i><a href="#" title="<s:text name='cmn_show'/>" onclick="showOrder('${entity.id}');"></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
								</a> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
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
			</tr>
		</c:forEach>
	</tbody>
</table>