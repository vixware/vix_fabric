<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name= Url.encode(name);
	name = encodeURI(name);
}
function toAddMetadata(){
	$.ajax({
		  url:'${vix}/system/industryManagementMetaDataAction!goChooseMetaData2.action?tag=choose&chkStyle=checkbox&industryMgtModuleId=${industryMgtModuleId}',
		  //data:{chkStyle:"checkbox",canCheckComp:0},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 860,
					height : 540,
					title:"选择元数据",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
							
								var metaData = "";
								var metaDataName = "";
								var result = returnValue.split(",");
								//debugger;
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										metaData += "," + v[0];
										metaDataName += "," + v[1];
									}
								}
								
								metaData = metaData.substring(1,metaData.length);
								if(metaData!="" && confirm('确定要添加元数据么?')){
									$.post('${vix}/system/industryManagementAction!saveForAddMetaData.action',
										 {
										  'industryMgtModuleId':'${industryMgtModuleId}',
										  'addMetaDataIds': metaData
										},
										function(result){
											showMessage(result);
											setTimeout("", 1000);
											pager("start","${vix}/system/industryManagementAction!goMetaDataList.action?industryMgtModuleId=${industryMgtModuleId}&metadataDisplayName="+name,'industryMgtMetaData');

										}
									 );
									
									
								}
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
	
};


$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	
}

/* function deleteById(id){
	$.ajax({
		  url:'${vix}/system/industryManagementAction!deleteById.action?industryMgtModuleId=${industryMgtModuleId}&id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/common/model/industryManagementAction!goMetaDataList.action?industryMgtModuleId=${industryMgtModuleId}&metadataDisplayName="+name,'industryMgtMetaData');
			});
		  }
	});
} */

function searchForContent(){
	loadName();
	pager("start","${vix}/system/industryManagementAction!goMetaDataList.action?industryMgtModuleId=${industryMgtModuleId}&metadataDisplayName="+name,'industryMgtMetaData');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/system/industryManagementAction!goMetaDataList.action?industryMgtModuleId=${industryMgtModuleId}&metadataDisplayName="+name,'industryMgtMetaData');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#industryMgtMetaDataOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/system/industryManagementAction!goMetaDataList.action?industryMgtModuleId=${industryMgtModuleId}&orderField="+orderField+"&orderBy="+orderBy+"&metadataDisplayName="+name,'industryMgtMetaData');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/industryManagementAction!goMetaDataList.action?industryMgtModuleId=${industryMgtModuleId}&metadataDisplayName="+name,'industryMgtMetaData');
}


function deleteIndustryMgtMetadata(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	//alert(ids);
	if(ids!=""){
		ids = ids.substring(0,ids.length-1);
		//alert(ids);
		
		asyncbox.confirm('确定要删除所选吗?','<s:text name='vix_message'/>',function(action){
			if(action == 'ok'){
				$.ajax({
					  url:'${vix}/system/industryManagementAction!deleteMetaDataById.action',
					  data:{
						  industryMgtModuleId : "${industryMgtModuleId}",
						  deleteMetaDataIds : ids
					  },
					  cache: false,
					  success: function(result){
						  showMessage(result);
						  setTimeout("", 1000);
							pager("start","${vix}/system/industryManagementAction!goMetaDataList.action?industryMgtModuleId=${industryMgtModuleId}&metadataDisplayName="+name,'industryMgtMetaData');
					  }
				});
			}
		});
	}
	
}

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
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" /> 系统管理 </a></li>
				<li><a href="#">平台运营管理 </a></li>
				<li><a href="#">行业管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="toAddMetadata();"><span><s:text name='cmn_add' /></span></a> <a href="#" onclick="deleteIndustryMgtMetadata();"><span><s:text name='cmn_delete' /></span></a> <a href="#" onclick="loadContent('${vix}/system/industryManagementAction!goList.action');"><span>返回</span></a>
		</p>
	</div>
</div>


<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong>
					<i class="close"><a href="#"></a></i>
					<i><a href="#"></a></i>
					<i><a href="#"></a></i>
					<b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_category"/></a></li>
			<li class="fly">
				<a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_category"/></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
				</ul>
			</li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" />
		</div>
		<%-- <div class="search_advanced">
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div> --%>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="industryMgtMetaDataInfo"></b> <s:text name='cmn_to' /> <b class="industryMgtMetaDataTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="industryMgtMetaData" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="industryMgtMetaDataInfo"></b> <s:text name='cmn_to' /> <b class="industryMgtMetaDataTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>