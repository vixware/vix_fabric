<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="purchase_return_grid" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>' fillblank="1">
	<thead>
		<tr class="alt">
			<th width="" sColumn="code"><s:text name="pur_return_number" /></th>
			<th width="" sColumn="name"><s:text name="pur_theme" /></th>
			<th width="100">金额</th>
			<th width="100" sColumn="requireDepartment"><s:text name="pur_department" /></th>
			<th width="" sColumn="supplierName"><s:text name="pur_suppliers" /></th>
			<th width="100" sColumn="purchasePerson"><s:text name="cmn_clerk" /></th>
			<th width="90" sColumn="deliveryDate"><s:text name="pur_return_date" /></th>
			<th width="60"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr id="purchasereturn_${entity.id}">
				<td><span style="color: gray;">${entity.code }</span></td>
				<td><span style="color: gray;">${entity.name }</span></td>
				<td><span style="color: gray;">${entity.totalAmount }</span></td>
				<td><span style="color: gray;">${entity.requireDepartment }</span></td>
				<td><span style="color: gray;">${entity.supplierName }</span></td>
				<td><span style="color: gray;">${entity.purchasePerson }</span></td>
				<td><span style="color: gray;"><s:date name="#entity.deliveryDate" format="yyyy-MM-dd" /></span></td>
				<td style="padding-top: 2px;">
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" />
								</a></i> <i><a href="#" title="查看" onclick="goShowPurchaseReturn('${entity.id}');"><img src="${vix}/common/img/icon_show.gif" /></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="编辑"><img src="${vix}/common/img/icon_edit.png" /></a> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>