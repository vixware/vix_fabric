<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="purchase_charts_grid" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>' fillblank="1">
	<thead>
		<tr class="alt">
			<th width="80">时间</th>
			<th width=""><s:if test="dataTarget=='item'">商品</s:if> <s:elseif test="dataTarget=='supplier'">供应商</s:elseif></th>
			<th>采购次数</th>
			<th width="">采购数量</th>
			<th width="">货款</th>
			<th width="">税金</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><span style="color: gray;">${entity.item_date }</span></td>
				<td><span style="color: gray;">${entity.item_name }</span></td>
				<td><span style="color: gray;">${entity.tatol_count }</span></td>
				<td><span style="color: gray;">${entity.tatol_amount }</span></td>
				<td><span style="color: gray;">${entity.tatol_fee }</span></td>
				<td><span style="color: gray;">${entity.tatol_tax }</span></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<script type="text/javascript">
$(function(){
});
</script>