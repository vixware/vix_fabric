<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该客户关怀么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/crm/service/customerCareAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
						loadCustomerCare();
					});
				  }
			});
		}
	});
}
</script>
<s:iterator value="pager.resultList" var="entity" status="s">
	<a href="###" onclick="saveOrUpdateCustomerCare('${entity.id}');" title="${entity.memo}"> ${entity.subject} </a>
	${entity.customerAccount.name}
	<br />
</s:iterator>