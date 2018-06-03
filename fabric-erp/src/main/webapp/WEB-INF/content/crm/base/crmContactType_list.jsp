<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function saveOrUpdate(){
	var params = "";
	for(var i=1;i<9;i++){
		var id = $("#crmContactTypeId_"+i).val();
		var radio = '0';
		if($("#crmContactTypeRadio_"+i).attr('checked')){
			radio = '1';
		}
		var checkbox = '0';
		if($("#crmContactTypeCheckbox_"+i).attr('checked')){
			checkbox = '1';
		}
		var name = $("#crmContactTypeName_"+i).val();
		var memo = $("#crmContactTypeMemo_"+i).val();
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
	$.post('${vix}/crm/base/crmContactTypeAction!saveOrUpdate.action',
		 {'data':params},
		function(result){
			asyncbox.success(result,"<s:text name='vix_message'/>");
			pager("start","${vix}/crm/base/crmContactTypeAction!goList.action?name="+name,'crmContactType');
		}
	 ); 
};
//载入分页列表数据
pager("start","${vix}/crm/base/crmContactTypeAction!goListContent.action?name="+name,'crmContactType');

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
	<h2>联系人类型</h2>
</div>
<div class="content">
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div id="crmContactType" class="table"></div>
			</div>
			<div id="search_bar" style="margin-bottom: 100px;">
				<p style="text-align: center;">
					<label><input type="button" value="保存" class="btn" onclick="saveOrUpdate();" /></label>
				</p>
			</div>
		</div>
		<!-- right -->
	</div>
</div>