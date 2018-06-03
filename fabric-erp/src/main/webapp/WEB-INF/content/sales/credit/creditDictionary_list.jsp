<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/system/page/taglibs.jsp"%>
<script type="text/javascript">
function saveOrUpdateCrmDictionary(){
	var params = "";
	for(var i=1;i<9;i++){
		var id = $("#creditDictionaryId_"+i).val();
		var value = $("#creditDictionaryValue_"+i).val();
		var memo = $("#creditDictionaryMemo_"+i).val();
		if(!(id == '' && value == '' && memo == '')){
			if(id == ''){
				id = "Placeholder";
			}
			if(value == ''){
				value = "Placeholder";
			}
			if(memo == ''){
				memo = "Placeholder";
			}
			params += id+":"+value+":"+memo+",";
		}
	}
	$.post('${vix}/sales/credit/creditDictionaryAction/saveOrUpdate.action',
		 {'data':params,'dicType':$("#dicType").val()},
		function(result){
			asyncbox.success(result,"<spring:message code='snow_message'/>",function(){
				pager("start","${vix}/sales/credit/creditDictionaryAction/goSingleList.action?dicType=${dicType}&dicCount=${dicCount}",'creditDictionary');
			});
		}
	 ); 
};
//载入分页列表数据
pager("start","${vix}/sales/credit/creditDictionaryAction/goSingleList.action?dicType=${dicType}&dicCount=${dicCount}",'creditDictionary');
</script>
<div id="switch"></div>
<input type="hidden" id="dicType" value="${dicType}" />
<div class="search">
	<div id="search_bar">
		<hr />
	</div>
	<div class="table">
		<p id="table_top">
			<a id="add" href="#"></a>
		</p>
		<div id="creditDictionary"></div>
	</div>
	<div id="search_bar">
		<p style="text-align: center;">
			<label><input type="button" value="保存" class="btn" onclick="saveOrUpdateCrmDictionary();" /></label>
		</p>
		<hr />
	</div>
</div>