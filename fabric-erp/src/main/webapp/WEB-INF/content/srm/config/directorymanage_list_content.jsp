<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="srm_item_grid" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>' fillblank="1">
	<thead>
		<tr class="alt">
			<th>编号</th>
			<th>${vv:varView("vix_mdm_item")}名称</th>
			<th>类型</th>
			<th>规格型号</th>
			<th>数量</th>
			<th>单价</th>
			<th>供应商</th>
			<th style="width: 65px; text-align: center;"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr id="item_${entity.id}">
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.itemType}</td>
				<td>${entity.specification}</td>
				<td></td>
				<td>${entity.price}</td>
				<td>${entity.supplierName}</td>
				<td style="padding-top: 2px;"><a href="javascript:void(0);" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="javascript:void(0);" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a> <!-- 
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png">
						</span>
						<div class="popup" style="display: none; top: -7px;">
							<strong>
								<b></b> 
							</strong>
							<p >
							</p>
						</div>
					</div>
 --></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<script type="text/javascript">
$(function(){
});

 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

</script>