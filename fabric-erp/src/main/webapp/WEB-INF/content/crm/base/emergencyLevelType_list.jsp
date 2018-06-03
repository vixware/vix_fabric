<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function saveOrUpdateContactPersonType(){
	var params = "";
	for(var i=1;i<9;i++){
		var id = $("#emergencyLevelTypeId_"+i).val();
		var radio = '0';
		if($("#emergencyLevelTypeRadio_"+i).attr('checked')){
			radio = '1';
		}
		var checkbox = '0';
		if($("#emergencyLevelTypeCheckbox_"+i).attr('checked')){
			checkbox = '1';
		}
		var name = $("#emergencyLevelTypeName_"+i).val();
		var memo = $("#emergencyLevelTypeMemo_"+i).val();
		if(!(id == '' && name == '' && memo == '' && radio == '0' && checkbox == '0')){
			if(id == ''){
				id = "Placeholder";
			}
			if(name == ''){
				name = "Placeholder";
			}
			if(memo == ''){
				memo = "Placeholder";
			}
			params += id+":"+radio+":"+checkbox+":"+name+":"+memo+",";
		}
	}
	$.post('${vix}/crm/base/emergencyLevelTypeAction!saveOrUpdate.action',
		 {'data':params},
		function(result){
			asyncbox.success(result,"<s:text name='vix_message'/>");
			pager("start","${vix}/crm/base/emergencyLevelTypeAction!goListContent.action?name="+name,'emergencyLevelType');
		}
	 ); 
};
//载入分页列表数据
pager("start","${vix}/crm/base/emergencyLevelTypeAction!goListContent.action?name="+name,'emergencyLevelType');

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
</script>
<div class="sub_menu">
	<h2>客户投诉紧急程度</h2>
</div>
<div class="content">
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div id="emergencyLevelType" class="table"></div>
			</div>
			<div id="search_bar" style="margin-bottom: 100px;">
				<p style="text-align: center;">
					<label><input type="button" value="保存" class="btn" onclick="saveOrUpdateContactPersonType();" /></label>
				</p>
			</div>
		</div>
		<!-- right -->
	</div>
</div>