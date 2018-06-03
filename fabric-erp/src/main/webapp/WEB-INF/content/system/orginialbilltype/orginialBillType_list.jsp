<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
/* 新增分类 */
function saveOrUpdateBillsCategory(id,treeType){
	$.ajax({
		  url:'${vix}/system/orginialBillTypeAction!goSaveOrUpdateBillsCategory.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 475,
					title:"内置单据类型",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#billsCategoryForm').validationEngine('validate')){
							$.post('${vix}/system/orginialBillTypeAction!saveOrUpdateBillsCategory.action',
									 {
									  'orginialBillsCategory.id':$("#billsCategoryId").val(),
									  'orginialBillsCategory.categoryCode':$("#billsCategoryDictionaryCategoryCode").val(),
									  'orginialBillsCategory.companyCode':$("#companyCode").val()
									},
									function(result){
									    showMessage(result);
									    setTimeout("", 1000);
									    loadContent('${vix}/system/orginialBillTypeAction!goList.action');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

/* 新增类型 */
function saveOrUpdateBillsType(id,billsPropertyId,treeType){
	if(id!=0){
		saveOrUpdateBillsType_1(id,billsPropertyId,treeType);
	}
	else if(treeType=='X'){
		saveOrUpdateBillsType_1(id,billsPropertyId,treeType);
	}else {
		asyncbox.success('只能在单据类型下创建自定义单据类型!','提示信息');
	}
};
function saveOrUpdateBillsType_1(id,billsPropertyId,treeType){
	$.ajax({
		  url:'${vix}/system/orginialBillTypeAction!goSaveOrUpdate.action?id='+id+"&billsPropertyId="+billsPropertyId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 275,
					title:"单据类型",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#billsTypeForm').validationEngine('validate')){
							$.post('${vix}/system/orginialBillTypeAction!saveOrUpdate.action',
								 {
								  'orginialBillsType.id':$("#billsTypeId").val(),
								  'billsPropertyId':$("#billsPropertyId").val(),
								  'orginialBillsType.typeCode':$("#typeCode").val(),
								  'orginialBillsType.typeName':$("#typeName").val(),
								  'orginialBillsType.typeDescription':$("#typeDescription").val()
								},
								function(result){
								    showMessage(result);
								    setTimeout("", 1000);
								    loadContent('${vix}/system/orginialBillTypeAction!goList.action');
								}
							 );
							}else {
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
});
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/system/orginialBillTypeAction!goSingleList.action?name="+name,'channelDistributor');
}
loadName();
//载入分页列表数据
pager("start","${vix}/system/orginialBillTypeAction!goSingleList.action?name="+name,'channelDistributor');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/system/orginialBillTypeAction!goSingleList.action?name=",'channelDistributor');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/system/orginialBillTypeAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'channelDistributor');
}
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/orginialBillTypeAction!goSingleList.action?name="+name,'channelDistributor');
}
function goSearch() {
	$.ajax({
	url : '${vix}/dtbcenter/vehiclePlanAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				pager("start", "${vix}/system/orginialBillTypeAction!goSingleList.action",'channelDistributor');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />系统管理</a></li>
				<li><a href="#">基础设置 </a></li>
				<li><a href="#">单据类型管理(没有承租户) </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>&nbsp;&nbsp;新增&nbsp;&nbsp;</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdateBillsCategory(0,$('#selectId').val(),$('#selectIdType').val());">内置单据类型 </a></li>
					<li><a href="#" onclick="saveOrUpdateBillsType(0,$('#selectId').val(),$('#selectIdType').val());">自定义单据类型 </a></li>
				</ul></li>
		</ul>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"> </label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""> </label> <label> <input type="button"
				value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="channelDistributorList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">	
			    var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/system/orginialBillTypeAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					pager("start","${vix}/system/orginialBillTypeAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"channelDistributor");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdType" name="selectIdType" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" />单据类型</li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="channelDistributorInfo"></b> <s:text name='cmn_to' /> <b class="channelDistributorTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="channelDistributor" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="channelDistributorInfo"></b> <s:text name='cmn_to' /> <b class="channelDistributorTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>