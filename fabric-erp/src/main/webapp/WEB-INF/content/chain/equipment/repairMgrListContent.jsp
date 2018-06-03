<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="mmx_equipment_grid_table_${archivePage}" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>'>
	<thead>
		<tr class="alt">
			<th sColumn="mendian">门店</th>
			<th sColumn="eqTitle">设备</th>
			<th sColumn="repairLevel">维修级别</th>
			<th width="50">附件</th>
			<th sColumn="hopeRepairBefore" width="100">期望完成期限</th>
			<th width="20"></th>
			<th style="width: 65px; text-align: center;"><s:text name="cmn_operate" /></th>
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
				<td><s:if test="#entity.docFileName!=null">
						<a href="javascript:downloadDoc('${entity.id}')">下载</a>
					</s:if></td>
				<td><s:date name="#entity.hopeRepairBefore" format="yyyy-MM-dd" /></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <b>维修说明</b>
							</strong>
							<p>
								<s:property value="#entity.note" />
							</p>
						</div>
					</div>
				</td>
				<td style="padding-top: 2px;"><s:if test="feedbackPage==1">
						<a href="javascript:void(0);" onclick="repairFeedback('${entity.id}');" title="<s:text name='cmn_delete'/>"> 提交反馈 </a>
					</s:if> <s:elseif test="archivePage>0">
						<s:if test="#entity.isArchive==0">
							<a href="javascript:void(0);" onclick="repairFeedback('${entity.id}');" title="<s:text name='cmn_delete'/>"> 反馈 </a>
							<a href="javascript:void(0);" onclick="archiveRepairRecord('${entity.id}');" title="<s:text name='cmn_delete'/>"> 完工 </a>
						</s:if>
						<s:else>
						</s:else>
					</s:elseif> <s:else>
						<a href="javascript:void(0);" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
						</a>
						<a href="javascript:void(0);" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
						</a>
					</s:else></td>
			</tr>
		</s:iterator>
		<s:iterator begin="pager.currentPageSize" end="pager.pageSize-1" step="1">
			<tr>
				<td colspan="7">&nbsp;</td>
			</tr>
		</s:iterator>
	</tbody>
</table>


<script type="text/javascript">
$(function(){
	//$('#mmx_equipment_grid_table_${archivePage}').addGridDropdownAction('操作1','javascript:void(0)');
});
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除数据?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({ url:"${vix}/chain/mmxEquipmentAction!deleteRepairRecord.action"
				,data:"id="+id
				,dataType:"text"
				,success:function(data){
				    if(data=='success'){
				    	$('#rr_'+id).remove();
				    	showMessage('数据删除完毕');
				    }else{
				    	showErrorMessage('操作失败');
				    }
		    }});
		}
	});
}

function downloadDoc(id){
	var url = "${vix}/chain/mmxEquipmentAction!downloadRepairDoc.action?id="+id;
	window.open(url);
}

function repairFeedback(id){
	var title = '维修反馈';
	var url = '${vix}/chain/mmxEquipmentAction!repairFeedBackMgrEdit.action?id='+id;
	var newPageId = _tabShow(title,url,'_p_repairfeedback');
}

function archiveRepairRecord(id){
	asyncbox.confirm('确定修改记录状态为以完成?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({ url:"${vix}/chain/mmxEquipmentAction!archiveRepairRecord.action"
				,data:"id="+id
				,dataType:"text"
				,success:function(data){
				    if(data=='success'){
				    	_pad_grid_loadPage('tab_home');
				    	_pad_grid_loadPage('tab_home2');
				    	showMessage('操作完毕');
				    }else{
				    	showErrorMessage('操作失败');
				    }
		    }});
		}
	});
}
</script>