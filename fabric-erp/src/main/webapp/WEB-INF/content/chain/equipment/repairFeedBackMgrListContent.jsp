暂时不用了


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="mmx_equipment_grid_table" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>'>
	<thead>
		<tr class="alt">
			<th sColumn="mendian">门店</th>
			<th sColumn="eqTitle">设备</th>
			<th sColumn="repairLevel">维修级别</th>
			<th sColumn="repairStatus">维修状态</th>
			<th width="50">附件</th>
			<th sColumn="hopeRepairBefore" width="100">期望完成期限</th>
			<th sColumn="repairTimeStart" width="100">开始日期</th>
			<th sColumn="repairTimeEnd" width="100">结束日期</th>
			<th style="width: 70px; text-align: center;"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr id="rr_${entity.id }">
				<td>${entity.mendian}</td>
				<td>${entity.eqTitle}</td>
				<td><s:if test="#entity.repairLevel==0">普通</s:if> <s:elseif test="#entity.repairLevel==1">优先</s:elseif> <s:elseif test="#entity.repairLevel==2">
						<spa style="color:red;">紧急</spa>
					</s:elseif> <s:elseif test="#entity.repairLevel==-1">低级</s:elseif></td>
				<td><s:if test="#entity.repairStatus==0">维修申请</s:if> <s:elseif test="#entity.repairStatus==1">正在维修</s:elseif> <s:elseif test="#entity.repairStatus==2">维修完毕</s:elseif> <s:elseif test="#entity.repairStatus==3">放弃维修</s:elseif></td>
				<td><s:if test="#entity.docFileName!=null">
						<a href="javascript:downloadDoc('${entity.id}')">下载</a>
					</s:if></td>
				<td><s:date name="#entity.hopeRepairBefore" format="yyyy-MM-dd" /></td>
				<td><s:date name="#entity.repairTimeStart" format="yyyy-MM-dd" /></td>
				<td><s:date name="#entity.repairTimeEnd" format="yyyy-MM-dd" /></td>
				<td style="padding-top: 2px;"><a href="javascript:void(0);" onclick="repairFeedback('${entity.id}');" title="<s:text name='cmn_delete'/>"> 提交反馈 </a></td>
			</tr>
		</s:iterator>
		<s:iterator begin="pager.currentPageSize" end="pager.pageSize-1" step="1">
			<tr>
				<td colspan="9">&nbsp;</td>
			</tr>
		</s:iterator>
	</tbody>
</table>


<script type="text/javascript">
$(function(){
	//$('#mmx_equipment_grid_table').addGridDropdownAction('操作1','javascript:void(0)');
});
 

function repairFeedback(id){
	var title = '维修反馈';
	var url = '${vix}/chain/mmxEquipmentAction!repairFeedBackMgrEdit.action?id='+id;
	var newPageId = _tabShow(title,url,'_p_repairfeedback');
}
</script>