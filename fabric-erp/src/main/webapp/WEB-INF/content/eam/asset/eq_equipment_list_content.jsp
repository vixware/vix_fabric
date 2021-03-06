<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="equipment_grid_table" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>'>
	<thead>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" class="grid_check_all"> </label>
						<ul>
							<li><a href="#">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th style="width: 55px; text-align: center;">业务</th>
			<th sColumn="eqCode"><s:text name="eam_equipmentcode" /></th>
			<th sColumn="eqName"><s:text name="eam_devicename" /></th>
			<th sColumn="eqPlaceCode"><s:text name="eam_positionnumber" /></th>
			<th><s:text name="eam_parentdevicename" /></th>
			<th><s:text name="eam_suppliername" /></th>
			<th style="width: 65px; text-align: center;"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><input value="${entity.id}" type="checkbox" class="grid_check" /></td>
				<!--  	<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>-->
				<td>
					<div class="pagelist drop">
						<ul>
							<li class="tp"><a href="#">业务</a>
								<ul>
									<li><a onclick="_tabShow('设备移动','${vix}/eam/accountManageAction!eqMove.action?id=${entity.id}','_p_eq_move')" href="javascript:void(0);" title="" href="javascript:void(0);">移动</a></li>
									<li><a onclick="_tabShow('设备修理','${vix}/eam/accountManageAction!eqRepair.action?id=${entity.id}','_p_eq_repair')" href="javascript:void(0);" title="" href="javascript:void(0);">修理</a></li>
									<li><a onclick="_tabShow('设备拆除','${vix}/eam/accountManageAction!eqRemove.action?id=${entity.id}','_p_eq_remove')" href="javascript:void(0);" title="" href="javascript:void(0);">拆除</a></li>
									<li><a onclick="_tabShow('设备维修记录','${vix}/eam/accountManageAction!eqRepairRecord.action?id=${entity.id}','_p_eq_repair_record')" href="javascript:void(0);" title="" href="javascript:void(0);">维修记录</a></li>
									<li><a onclick="_tabShow('设备活动记录','${vix}/eam/accountManageAction!eqMoveRecord.action?id=${entity.id}','_p_eq_move_record')" href="javascript:void(0);" title="" href="javascript:void(0);">活动记录</a></li>
								</ul></li>
						</ul>
					</div>
				</td>
				<td><span style="color: gray;">${entity.eqCode}</span></td>
				<td><span style="color: gray;">${entity.eqName}</span></td>
				<td><span style="color: gray;">${entity.eqPlaceCode}</span></td>
				<td><span style="color: gray;">${entity.parentEqname}</span></td>
				<td><span style="color: gray;">${entity.supplyName}</span></td>
				<td style="padding-top: 2px;"><a href="javascript:void(0);" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="javascript:void(0);" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate(${entity.id},$('#parentId').val());" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
							</strong>
							<p></p>
						</div>
					</div></td>
			</tr>
		</s:iterator>
		<s:iterator begin="pager.currentPageSize" end="pager.pageSize-1" step="1">
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
		</s:iterator>
	</tbody>
</table>


<script type="text/javascript">
$(function(){
	$('#equipment_grid_table').addGridDropdownAction('操作1','javascript:void(0)');
});
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
</script>