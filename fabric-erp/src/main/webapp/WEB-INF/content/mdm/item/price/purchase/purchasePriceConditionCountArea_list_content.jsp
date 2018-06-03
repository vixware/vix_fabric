<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
</script>
<table>
	<tbody>
		<tr class="alt" style="background: none repeat scroll 0 0 #f1f1f1; border-bottom: 1px solid #dfdfdf; font-weight: 700; line-height: 30px; padding: 0 3px;">
			<th style="text-align: left;">条件类型</th>
			<th style="text-align: left;">采购区域</th>
			<th style="text-align: left;">客户分类</th>
			<th style="text-align: left;">${vv:varView('vix_mdm_item')}分类</th>
			<th style="text-align: left;">客户组</th>
			<th style="text-align: left;">${vv:varView('vix_mdm_item')}组</th>
			<th style="text-align: left;">客户</th>
			<th style="text-align: left;">${vv:varView('vix_mdm_item')}编码</th>
			<th style="text-align: left;">${vv:varView('vix_mdm_item')}</th>
			<th style="text-align: left;">分销商</th>
			<th style="text-align: left;">供应商</th>
			<th style="text-align: left;">开始数量</th>
			<th style="text-align: left;">结束数量</th>
			<th style="text-align: left;">单位</th>
			<th style="text-align: left;">价格</th>
			<th style="text-align: left;">折扣</th>
			<th style="text-align: left;">备注</th>
			<th style="text-align: left;">操作</th>
		</tr>
		<s:iterator value="pccaList" var="entity" status="s">
			<tr class="">
				<td><s:if test="#entity.conditionType == 'purchaseFrameworkAgreement'">
						<s:text name="getText('mdm_purchaseFrameworkAgreement')" />
					</s:if> <s:if test="#entity.conditionType == 'saleFrameworkAgreement'">
						<s:text name="getText('mdm_saleFrameworkAgreement')" />
					</s:if> <s:if test="#entity.conditionType == 'customerAccountCategory'">
						<s:text name="getText('mdm_customerAccountCategory')" />
					</s:if> <s:if test="#entity.conditionType == 'itemCategory'">
						<s:text name="getText('mdm_itemCategory')" />
					</s:if> <s:if test="#entity.conditionType == 'itemGroup'">
						<s:text name="getText('mdm_itemGroup')" />
					</s:if> <s:if test="#entity.conditionType == 'channelDistributor'">
						<s:text name="getText('mdm_channelDistributor')" />
					</s:if> <s:if test="#entity.conditionType == 'customerAccount'">
						<s:text name="getText('mdm_customerAccount')" />
					</s:if> <s:if test="#entity.conditionType == 'item'">
						<s:text name="getText('mdm_item')" />
					</s:if> <s:if test="#entity.conditionType == 'supplier'">
						<s:text name="getText('mdm_supplier')" />
					</s:if> <s:if test="#entity.conditionType == 'customerAccountGroup'">
						<s:text name="getText('mdm_customerAccountGroup')" />
					</s:if> <s:if test="#entity.conditionType == 'customerAccountAndItem'">
						<s:text name="getText('mdm_customerAccountAndItem')" />
					</s:if></td>
				<td><s:if test="#entity.regional == null">
			    		全
			    	</s:if> <s:else>
			    		${entity.regional.name}
			    	</s:else></td>
				<td>${entity.customerAccountCategory.name}</td>
				<td>${entity.itemCatalog.name}</td>
				<td>${entity.customerAccountGroup.name}</td>
				<td>${entity.itemGroup.name}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.item.code}</td>
				<td>${entity.item.name}</td>
				<td>${entity.channelDistributor.name}</td>
				<td>${entity.supplier.name}</td>
				<td>${entity.startCount}</td>
				<td>${entity.endCount}</td>
				<td>${entity.unit}</td>
				<td>${entity.price}</td>
				<td>${entity.discount}</td>
				<td>${entity.memo}</td>
				<td><a href="#" onclick="saveOrUpdatePriceConditionDetail(${entity.id},'count');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deletePriceConditionEntity(${entity.id},'count');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
			</tr>
		</s:iterator>
		<tr class="">
			<td colspan="18">共<s:property value="pccaList.size()" />条
			</td>
		</tr>
	</tbody>
</table>