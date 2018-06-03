<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#giftTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#giftTable tr:even").addClass("alt");
</script>
<table id="giftTable">
	<tbody>
		<tr class="alt" style="background: none repeat scroll 0 0 #f1f1f1; border-bottom: 1px solid #dfdfdf; font-weight: 700; line-height: 30px; padding: 0 3px;">
			<th style="text-align: left;">${vv:varView('vix_mdm_item')}</th>
			<th style="text-align: left;">数量</th>
			<th style="text-align: left;">单价</th>
			<th style="text-align: left;">备注</th>
			<th style="text-align: left;">操作</th>
		</tr>
		<s:iterator value="giftList" var="entity" status="s">
			<tr class="">
				<td>${entity.item.name}</td>
				<td>${entity.count}</td>
				<td>${entity.price}</td>
				<td>${entity.memo}</td>
				<td><a href="#" onclick="saveOrUpdatePriceConditionGiftDetail('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deletePriceConditionGiftEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
			</tr>
		</s:iterator>
		<tr class="">
			<td colspan="16">共<s:property value="giftList.size()" />条
			</td>
		</tr>
	</tbody>
</table>