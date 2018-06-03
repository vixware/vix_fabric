<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/competitionChannelAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/drp/competitionChannelAction!goSingleList.action", 'channelDistributor');
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
			<th><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="channelDistributorImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('name');"><s:text name="drp_name" />&nbsp;<img id="channelDistributorImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('type');"><s:text name="drp_channel_type" />&nbsp;<img id="channelDistributorImg_type" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('artificialPerson');">联系人&nbsp;<img id="channelDistributorImg_artificialPerson" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('telephone');"><s:text name="drp_telephone" />&nbsp;<img id="channelDistributorImg_telephone" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('email');"><s:text name="drp_mailbox" />&nbsp;<img id="channelDistributorImg_email" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;">${entity.code }</a></td>
				<td><a href="#" style="color: gray;">${entity.name }</a></td>
				<td><a href="#" style="color: gray;"> <s:if test="%{#entity.type==1}">渠道商</s:if> <s:elseif test="%{#entity.type==2}">经销商</s:elseif> <s:elseif test="%{#entity.type==3}">代理商</s:elseif> <s:elseif test="%{#entity.type==4}">门店</s:elseif> <s:elseif test="%{#entity.type==5}">竞争渠道</s:elseif>
				</a></td>
				<td><a href="#" style="color: gray;">${entity.artificialPerson }</a></td>
				<td><a href="#" style="color: gray;">${entity.telephone }</a></td>
				<td><a href="#" style="color: gray;">${entity.email }</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" title="<s:text name='cmn_show'/>"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
								</a></i> <i><a href="#" onclick="saveOrUpdateCustomerAndSalesDistribution('${entity.id}');" title="客户与销售"> <img src="${vix}/common/img/icon_edit.png" />
								</a></i> <i><a href="#" onclick="saveOrUpdateMarketingActivity('${entity.id}');" title="市场活动"> <img src="${vix}/common/img/icon_edit.png" />
								</a></i> <i><a href="#" onclick="saveOrUpdateProductInformation('${entity.id}');" title="产品投放 "> <img src="${vix}/common/img/icon_edit.png" />
								</a></i> <i><a href="#" onclick="saveOrUpdateAdvertisingCampaign('${entity.id}');" title="广告活动"> <img src="${vix}/common/img/icon_edit.png" />
								</a></i> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
								</a></i> <b>${entity.name}</b>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>