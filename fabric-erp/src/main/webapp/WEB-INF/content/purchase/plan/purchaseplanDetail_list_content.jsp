<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<table class="list">
	<tbody>
		<tr class="alt" style="background: none repeat scroll 0 0 #f1f1f1; border-bottom: 1px solid #dfdfdf; font-weight: 700; line-height: 30px; padding: 0 3px;">
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
			<th style="text-align: left;">计划编码</th>
			<th style="text-align: left;">主题</th>
			<th style="text-align: left;">审批人</th>
			<th style="text-align: left;">审批时间</th>
			<th style="text-align: left;">状态</th>
			<th style="text-align: left;" width="5%">操作</th>
		</tr>
		<s:iterator value="orderList" var="entity" status="s">
			<tr class="">
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.purchasePlanCode}</td>
				<td>${entity.name}</td>
				<td>${entity.approver}</td>
				<td>${entity.purchasePlanCode}</td>
				<td>${entity.status}</td>
				<td><a href="#" onclick="goPrintDelivery('${entity.id}');"> <img src="${vix}/common/img/print_delivery.png" />
				</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>