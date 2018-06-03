<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/membershipCardmanagementAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/drp/membershipCardmanagementAction!goSingleList.action", 'shipCard');
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
	loadOrderByImage("${vix}", "shipCard");
</script>
<input type="hidden" id="shipCardTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="shipCardPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="shipCardTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="shipCardOrderField" value="${pager.orderField}" />
<input type="hidden" id="shipCardOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="shipCardInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('vipcardid');"><s:text name="drp_card_number" />&nbsp;<img id="shipCardImg_vipcardid" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('balance_amount');">余额&nbsp;<img id="shipCardImg_balance_amount" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('vipTypeName');">会员卡类型&nbsp;<img id="shipCardImg_vipTypeName" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('vipChannel');">开卡来源&nbsp;<img id="shipCardImg_vipChannel" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('isOpenCard');">是否开卡&nbsp;<img id="shipCardImg_isOpenCard" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('opencarddate');">发卡日期&nbsp;<img id="shipCardImg_opencarddate" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;">${entity.vipcardid }</a></td>
				<td><a href="#" style="color: gray;">${entity.balance_amount }</a></td>
				<td><a href="#" style="color: gray;">${entity.vipTypeName }</a></td>
				<td><a href="#" style="color: gray;"><s:if test="%{#entity.customerAccount.vipChannel==31}">微信</s:if> <s:elseif test="%{#entity.customerAccount.vipChannel=='90'}">总部</s:elseif></a></td>
				<td><a href="#" style="color: gray;"><s:if test="%{#entity.isOpenCard==1}">未开卡</s:if> <s:elseif test="%{#entity.isOpenCard==2}">已开卡</s:elseif></a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.opencarddate }" pattern="yyyy-MM-dd" /></a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong><i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i> <i><a href="#" onclick="memberShipCardForbidden('${entity.id}');" title="禁用"> <img src="${vix}/common/img/icon_show.gif" />
								</a></i><i><a href="#" onclick="memberShipCardForbiddenUp('${entity.id}');" title="启用"> <img src="${vix}/common/img/icon_show.gif" />
								</a></i><i><a href="#" title="续费" onclick="memberShipCardFeesForRenewal('${entity.id}');"><img src="${vix}/common/img/icon_show.gif" /></a> </i><i><a href="#" title="转账" onclick="saveOrUpdateMemberShipCardTransfers('${entity.id}');"><img src="${vix}/common/img/icon_show.gif" /></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');"
									title="修改"><img src="${vix}/common/img/icon_edit.png" /></a> </i><i><a href="#" onclick="changeCard('${entity.id}');" title="换卡"><img src="${vix}/common/img/icon_edit.png" /></a> </i> <b>${entity.code}</b> </strong>
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