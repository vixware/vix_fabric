<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="eq_bxxx_grid_table" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>'>
	<thead>
		<tr class="alt">
			<th style="width: 55px; text-align: center;">业务</th>
			<th>保修项目</th>
			<th>设备序列号</th>
			<th>保修合同编号</th>
			<th>保修类型</th>
			<th>保修负责单位</th>
			<th>收费方式</th>
			<th style="width: 65px; text-align: center;"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
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
				<td><span style="color: gray;">${entity.installObj}</span></td>
				<td><span style="color: gray;">${entity.serialNo}</span></td>
				<td><span style="color: gray;">${entity.contractSn}</span></td>
				<td><span style="color: gray;">${entity.warrantyMode}</span></td>
				<td><span style="color: gray;">${entity.warrantyBy}</span></td>
				<td><span style="color: gray;">${entity.feeMode}</span></td>
				<td style="padding-top: 2px;"><a href="javascript:void(0);" onclick="editEntity('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="javascript:void(0);" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <b>${entity.installObj}</b>
							</strong>
							<p></p>
						</div>
					</div></td>
			</tr>
		</s:iterator>
		<s:iterator begin="pager.currentPageSize" end="pager.pageSize-1" step="1">
			<tr>
				<td>&nbsp;</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
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