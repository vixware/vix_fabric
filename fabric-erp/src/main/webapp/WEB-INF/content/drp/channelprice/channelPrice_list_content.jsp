<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadOrderByImage("${vix}", "channelprice");
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/channelPriceAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 300);
					pager("start", "${vix}/drp/channelPriceAction!goSingleList.action", 'channelprice');
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
</script>
<input type="hidden" id="channelpriceTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="channelpricePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="channelpriceTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="channelpriceOrderField" value="${pager.orderField}" />
<input type="hidden" id="channelpriceOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="channelpriceInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
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
		<th><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="channelpriceImg_code" src="${vix}/common/img/arrow.gif"></span></th>
		<th><span style="cursor: pointer;" onclick="orderBy('name');">主题&nbsp;<img id="channelpriceImg_name" src="${vix}/common/img/arrow.gif"></span></th>
		<th><span style="cursor: pointer;" onclick="orderBy('channelDistributor.name');">供应商&nbsp;<img id="channelpriceImg_channelDistributor" src="${vix}/common/img/arrow.gif"></span></th>
		<th><span style="cursor: pointer;" onclick="orderBy('name');">商品&nbsp;<img id="channelpriceImg_name" src="${vix}/common/img/arrow.gif"></span></th>
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
			<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
			<td>${entity.code}</td>
			<td>${entity.name}</td>
			<td>${entity.channelDistributor.name}</td>
			<td>${entity.item.name}</td>
			<td><a href="#" title="修改" onclick="saveOrUpdate('${entity.id}','${entity.channelDistributor.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
			</a> <a href="#" title="删除" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
			</a></td>
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
</table>