<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="srm_supplier_grid" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>' fillblank="1">
	<thead>
		<tr class="alt">
			<th>编码</th>
			<th>名称</th>
			<th><s:text name="srm_contact_information" /></th>
			<th>联系人</th>
			<th><s:text name="pur_type" /></th>
			<th><s:text name="cmn_mode" /></th>
			<th style="width: 65px; text-align: center;"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr id="supplier_${entity.id}">
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.telephone}</td>
				<td>${entity.contacts}</td>
				<td><span style="color: gray;"> <c:if test="${null == entity.type }">
						</c:if> <c:if test="${entity.type == 'GENERAL' }">
							普通供应商
						</c:if> <c:if test="${entity.type == 'AGREEMENT' }">
							协议供应商
						</c:if> <c:if test="${entity.type == 'OUTSOURCING' }">
							委外供应商
						</c:if>
				</span></td>
				<td><span style="color: gray;"> <c:if test="${null == entity.status }">
						</c:if> <c:if test="${entity.status == '1' }">
							待建档
						</c:if> <c:if test="${entity.status == '2' }">
							待评估
						</c:if> <c:if test="${entity.status == '3' }">
							正式
						</c:if> <c:if test="${entity.status == '4' }">
							无效
						</c:if>
				</span></td>
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
	asyncbox.confirm('确定要删除?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({ url:"${vix}/srm/${usedAction}!deleteById.action"
				,data:"id="+id
				,dataType:"text"
				,success:function(data){
				    if(data=='success'){
				    	showMessage('操作完毕');
				    	$('#supplier_'+id).remove();
				    }else{
				    	showErrorMessage('操作失败');
				    }
		    }});
		}
	});
}

</script>