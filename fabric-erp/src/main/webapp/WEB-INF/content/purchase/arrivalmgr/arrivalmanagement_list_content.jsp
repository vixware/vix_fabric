<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="purchase_order_grid" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>' fillblank="1">
	<thead>
		<tr class="alt">
			<th width="" sColumn="code">编码</th>
			<th width="" sColumn="name"><s:text name="pur_theme" /></th>
			<th width="" sColumn="supplierName"><s:text name="pur_suppliers" /></th>
			<th width="100" sColumn="purchasePerson">采购人</th>
			<th width="90" sColumn="deliveryDate">交货日期</th>
			<th width="60"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr id="purchasearrival_${entity.id}">
				<td><span style="color: gray;">${entity.code }</span></td>
				<td><span style="color: gray;">${entity.name }</span></td>
				<td><span style="color: gray;">${entity.supplierName }</span></td>
				<td><span style="color: gray;">${entity.purchasePerson }</span></td>
				<td><span style="color: gray;"><s:date name="#entity.deliveryDate" format="yyyy-MM-dd" /></span></td>
				<td style="padding-top: 2px;">
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" />
								</a></i> <i><a href="#" title="查看" onclick="goShowPurchaseArrival('${entity.id}');"><img src="${vix}/common/img/icon_show.gif" /></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="编辑"><img src="${vix}/common/img/icon_edit.png" /></a> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

