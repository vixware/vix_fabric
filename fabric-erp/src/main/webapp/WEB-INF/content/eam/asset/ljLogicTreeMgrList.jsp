<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<!-- 列表区  -->
<table class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>'>
	<thead>
		<tr class="alt">
			<th style="width: 55px; text-align: center;">业务</th>
			<th><s:text name="eam_equipmentcode" /></th>
			<th><s:text name="eam_devicename" /></th>
			<th>备注</th>
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
									<li><a href="javascript:void(0);" onclick="_tabShow('功能1','${vix}/eam/accountManageAction!eqMove.action?id=${entity.id}','_p_eq_move')" href="javascript:void(0);" title="">功能1</a></li>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能2','${vix}/eam/accountManageAction!eqRepair.action?id=${entity.id}','_p_eq_repair')" href="javascript:void(0);" title="">功能2</a></li>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能3','${vix}/eam/accountManageAction!eqRemove.action?id=${entity.id}','_p_eq_remove')" href="javascript:void(0);" title="">功能3</a></li>
								</ul></li>
						</ul>
					</div>
				</td>
				<td><span style="color: gray;">${entity.eqCode}</span></td>
				<td><span style="color: gray;">${entity.eqName}</span></td>
				<td><span style="color: gray;">${entity.memo}</span></td>
				</td>
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
			</tr>
		</s:iterator>
	</tbody>
</table>

<script type="text/javascript">
</script>