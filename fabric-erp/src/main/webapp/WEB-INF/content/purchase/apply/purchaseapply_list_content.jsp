<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="srm_supplier_grid" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>' fillblank="1">
	<thead>
		<tr class="alt">
			<th width="15%" sColumn="code">编码</th>
			<th width="20%" sColumn="name"><s:text name="pur_theme" /></th>
			<th width="20%" sColumn="purchaseOrg"><s:text name="pur_department" /></th>
			<th width="20%" sColumn="purchasePerson"><s:text name="pur_clerk" /></th>
			<th width="20%" sColumn="createTime"><s:text name="pur_application_time" /></th>
			<th width="5%"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr id="purchaseapply_${entity.id}">
				<td><span style="color: gray;">${entity.code }</span></td>
				<td><span style="color: gray;">${entity.name }</span></td>
				<td><span style="color: gray;">${entity.purchaseOrg }</span></td>
				<td><span style="color: gray;">${entity.purchasePerson }</span></td>
				<td><span style="color: gray;"><s:date name="#entity.createTime" format="yyyy-MM-dd" /></span></td>
				<td style="padding-top: 2px;">
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" />
								</a></i> <i><a href="#" title="查看" onclick="goShowPurchaseApply('${entity.id}');"><img src="${vix}/common/img/icon_show.gif" /></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="编辑"><img src="${vix}/common/img/icon_edit.png" /></a> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

