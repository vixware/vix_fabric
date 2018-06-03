<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function saveOrUpdateBulletinCategory(){
	var params = "";
	for(var i=1;i<9;i++){
		var id = $("#innerBulletinCategoryId_"+i).val();
		var radio = '0';
		if($("#innerBulletinCategoryRadio_"+i).attr('checked')){
			radio = '1';
		}
		var checkbox = '0';
		if($("#innerBulletinCategoryCheckbox_"+i).attr('checked')){
			checkbox = '1';
		}
		var name = $("#innerBulletinCategoryName_"+i).val();
		var memo = $("#innerBulletinCategoryMemo_"+i).val();
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
	$.post('${vix}/crm/workbench/innerBulletinCategoryAction!saveOrUpdate.action',
		 {'data':params},
		function(result){
			asyncbox.success(result,"<s:text name='vix_message'/>");
		}
	 ); 
};
//载入分页列表数据
pager("start","${vix}/crm/workbench/innerBulletinCategoryAction!goListContent.action?name="+name,'innerBulletinCategory');

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');

</script>
<div class="sub_menu">
	<h2>内部公告分类</h2>
</div>
<div class="content">
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div id="innerBulletinCategory" class="table"></div>
			</div>
			<div id="search_bar" style="margin-bottom: 100px;">
				<p style="text-align: center;">
					<label><input type="button" value="保存" class="btn" onclick="saveOrUpdateBulletinCategory();" /></label>
				</p>
			</div>
		</div>
		<!-- right -->
	</div>
</div>