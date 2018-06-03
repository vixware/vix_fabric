<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$.returnValue = "";
function chkChange(chk,id,name){
	 if(chk.checked){
		 $.returnValue = $.returnValue +","+id+":"+name;
	 }else{
		 $.returnValue = $.returnValue.replace(","+id+":"+name,"");
	 }
} 


var metaDataName = "";
function loadMetaDataName(){
	metaDataName = $("#metaDataName").val();
	metaDataName= Url.encode(metaDataName);
	metaDataName = encodeURI(metaDataName);
}



function searchForSelectEpmContent(){
	loadMetaDataName();
	pager("start","${vix}/system/industryManagementMetaDataAction!goChooseMetaDataList2.action?industryMgtModuleId=${industryMgtModuleId}&tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&metaDataName="+metaDataName,'metaDataChk');
}

loadMetaDataName();
//载入分页列表数据
pager("start","${vix}/system/industryManagementMetaDataAction!goChooseMetaDataList2.action?industryMgtModuleId=${industryMgtModuleId}&tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}","metaDataChk");


function metaDataChkPager(tag,entity){
	loadMetaDataName();
	if(entity == 'metaDataChk'){
		pager(tag,"${vix}/system/industryManagementMetaDataAction!goChooseMetaDataList2.action?industryMgtModuleId=${industryMgtModuleId}&tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&metaDataName="+metaDataName+'&id='+$('#orginialSelectId').val(),entity);
	}
}


//排序 
function orderBy(orderField){
	loadMetaDataName();
	var orderBy = $("#metaDataChkOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/system/industryManagementMetaDataAction!goChooseMetaDataList2.action?industryMgtModuleId=${industryMgtModuleId}&tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&orderField="+orderField+"&orderBy="+orderBy+"&metaDataName="+metaDataName+"&id="+$("#orginialSelectId").val(),'metaDataChk');
}

/* bindSearch();
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
}); */
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b></b>
				</strong>
				<p></p>
			</div>
		</div>
		<!-- <ul>
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li class="fly">
				<a href="#"><img src="img/icon_10.png" alt="" />information</a>
				<ul>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
				</ul>
			</li> 
		</ul> -->
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="metaDataName" value="${metaDataName}"></label>
			<%-- <label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label> --%>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForSelectEpmContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>"
				class="btn" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="switch_btn" id="switch_box"></div>
			<div class="left_content" id="tree_00">
				<div id="empSelect_tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTreeSelectMeta;			
				var settingSelectMeta = {
					async: {
						enable: true,
						url:"${vix}/system/industryManagementMetaDataAction!findTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClickSelectMeta
					}
				};
				function onClickSelectMeta(event, treeId, treeNode, clickFlag) {
					$("#orginialSelectId").val(treeNode.id);
					$("#orginialselectIdTreeId").val(treeNode.tId);
					//debugger;
					//alert(treeNode.tId+ "--" + treeNode.treeType );
					//pager("start","${vix}/common/select/commonSelectEmpAction!goSingleList.action?chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&id="+treeNode.id+"&treeType="+treeNode.treeType,"metaDataChk");
					pager("start","${vix}/system/industryManagementMetaDataAction!goChooseMetaDataList2.action?industryMgtModuleId=${industryMgtModuleId}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&id="+treeNode.id+"&metaDataName="+metaDataName,'metaDataChk');
				}
				zTreeSelectMeta = $.fn.zTree.init($("#empSelect_tree_root"), settingSelectMeta);
				//-->
			</script>
		</div>
		<input type="hidden" id="orginialSelectId" name="orginialSelectId" /> <input type="hidden" id="orginialselectIdTreeId" name="orginialselectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop clearfix">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a> <!-- <ul>
								<li><a href="#">Actions</a></li>
							</ul> --></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="metaDataChkPager('start','metaDataChk');"></a></span> <span><a class="previous" onclick="metaDataChkPager('previous','metaDataChk');"></a></span> <em>(<b class="metaDataChkInfo"></b> <s:text name='cmn_to' /> <b class="metaDataChkTotalCount"></b>)
						</em> <span><a class="next" onclick="metaDataChkPager('next','metaDataChk');"></a></span> <span><a class="end" onclick="metaDataChkPager('end','metaDataChk');"></a></span>
					</div>
				</div>
				<div id="metaDataChk" class="table"></div>
				<div class="pagelist drop clearfix">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a> <%-- <ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul> --%></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="metaDataChkPager('start','metaDataChk');"></a></span> <span><a class="previous" onclick="metaDataChkPager('previous','metaDataChk');"></a></span> <em>(<b class="metaDataChkInfo"></b> <s:text name='cmn_to' /> <b class="metaDataChkTotalCount"></b>)
						</em> <span><a class="next" onclick="metaDataChkPager('next','metaDataChk');"></a></span> <span><a class="end" onclick="metaDataChkPager('end','metaDataChk');"></a></span>
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