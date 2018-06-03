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
			<th width="15%">订单编码</th>
			<th width="30%">主题</th>
			<th width="15%">供应商</th>
			<th width="15%">下单时间</th>
			<th width="15%">备注</th>
			<th width="5%">操作</th>
		</tr>
		<s:iterator value="purchaseOrderList" var="entity" status="s">
			<tr class="">
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.supplierName}</td>
				<td>${entity.createTime}</td>
				<td>${entity.memo}</td>
				<td><a href="#" title="查看" onclick="saveOrUpdatePurchaseOrder('${entity.id}');"><img src="${vix}/common/img/icon_show.gif" /></a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>