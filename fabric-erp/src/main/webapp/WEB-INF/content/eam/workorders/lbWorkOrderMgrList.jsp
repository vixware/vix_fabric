<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 列表区  -->
<table id="workorder_grid_table" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>'>
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th style="width: 55px; text-align: center;">业务</th>
			<th>工单编号</th>
			<th>工单名称</th>
			<th>负责人</th>
			<th>联系电话</th>
			<th>期望开始时间</th>
			<th>期望结束时间</th>
			<th style="width: 65px; text-align: center;"><s:text name="cmn_operate" /></th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<!--  	<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>-->
				<td>
					<div class="pagelist drop">
						<ul>
							<li class="tp"><a href="#">业务</a>
								<ul>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能1','${vix}/eam/accountManageAction!eqMove.action?id=${entity.id}','_p_eq_move')" href="javascript:void(0);" title="">功能1</a></li>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能2','${vix}/eam/accountManageAction!eqRepair.action?id=${entity.id}','_p_eq_repair')" href="javascript:void(0);" title="">功能2</a></li>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能3','${vix}/eam/accountManageAction!eqRemove.action?id=${entity.id}','_p_eq_remove')" href="javascript:void(0);" title="">功能3</a></li>
								</ul></li>
						</ul>
					</div>
				</td>
				<td><span style="color: gray;">${entity.wocode}</span></td>
				<td><span style="color: gray;">${entity.woname}</span></td>
				<td><span style="color: gray;">${entity.responsiblePerson}</span></td>
				<td><span style="color: gray;">${entity.phone}</span></td>
				<td><span style="color: gray;"><fmt:formatDate value='${entity.hopeStartTime}' type='both' pattern='yyyy-MM-dd' /></span></td>
				<td><span style="color: gray;"><fmt:formatDate value='${entity.hopeEndTime}' type='both' pattern='yyyy-MM-dd' /></span></td>
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
				<td>&nbsp;</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<script type="text/javascript">
$(function(){
});

function deleteEntity(id){
	asyncbox.confirm('确认要删除该数据?','提示信息',function(action){
		if(action == 'ok'){
			//deleteById(id);
		}
	});
}
</script>