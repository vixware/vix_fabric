<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	var eqId = $('#eqId').val();
	_pad_all_loadPage('${vix}/eam/accountManageAction!eqBxxxPager.action?eqId='+eqId,'eq_bxxx_grid_div');
	
});

function lvyouGridSearch(){
	var param = {'shuhao':$('#b_shuhao').val()
				,'shuming':$('#b_shuming').val()
				,'leibie1':$('#b_leibie1').val()};
	_pad_grid_search('lvyou_grid_div',param);
	return false;
}

function editEntity(id){
	var eqId = $('#eqId').val();
	var popWinId = 'bxxx_eq_'+eqId;
	var params = "eqId="+eqId
	if(id && id>0)
		params = params + '&id='+id;

	$.ajax({
		url:'${vix}/eam/accountManageAction!eqBxxxEdit.action',
		data:params,
		cache: false,
		success: function(html){
			asyncbox.open({
				id:popWinId,
				modal:true,
				width : 780,
				height :370,
				title:"编辑保修信息",
				html:html,
				callback : function(action){
					if(action == 'ok'){
						if($('#bxxxForm').length>0){
							if(saveEqBxxx()){
								
							}else{
								return false;
							}
						}
					}else if(action=='cancel'){
					}
				},
				btnsbar : $.btn.OKCANCEL
			});
		}
	});
}
</script>
<div class="right_btn">
	<span><a href="javascript:editEntity();"><img src="${vix}/common/css/images/icons/add.png" alt="" />添加</a></span>
</div>
<div id="eq_bxxx_grid_div"></div>