<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该行动历史么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/crm/project/actionHistoryAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
						loadActionHistory();
					});
				  }
			});
		}
	});
}
</script>
<s:iterator value="pager.resultList" var="entity" status="s">
	<tr class="alt">
		<td height="30px" colspan="2"><a href="###" onclick="saveOrUpdateActionHistory('${entity.id}');" title="${entity.description}">${entity.subject}</a> &nbsp;&nbsp;&nbsp;&nbsp;${entity.customerAccount.name} &nbsp;&nbsp;&nbsp;&nbsp;${entity.description}<br></td>
	</tr>
</s:iterator>
