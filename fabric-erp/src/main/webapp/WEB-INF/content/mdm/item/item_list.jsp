<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}

$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});

function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!goSaveOrUpdate.action?id='+id+"&categoryId="+$("#selectId").val()+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function fastAdd(id){
	$.ajax({
		  url:'${vix}/mdm/item/fastAddItemAction!goSaveOrUpdateCirculationIndustryItem.action?id='+id+"&categoryId="+$("#selectId").val(),
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}
function importItemFile(){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!chooseItemFile.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 700,
					height : 200,
					title:"选择文件",
					html:html,
					callback : function(action){
						if(action == 'import'){
							$("#itemFileForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/mdm/item/itemAction!importItemFile.action",
							     dataType: "text",
							     success: function(result){
							    	 asyncbox.success(result,"提示信息",function(action){
							    		 loadContent('${vix}/mdm/item/itemAction!goList.action');
							    	 });
							     }
							 });
						}
					},
					btnsbar : [{
						text    : '导入',
						action  : 'import'
					}]
				});
		  }
	});
}
function deleteById(id){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/mdm/item/itemAction!goListContent.action?name="+name+"&categoryId="+$("#selectId").val(),'item');
			});
		  }
	});
}

function showOrder(id){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!showOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/mdm/item/itemAction!goListContent.action?name="+name+"&categoryId="+$("#selectId").val(),'item');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/mdm/item/itemAction!goListContent.action?name="+name+"&pageNo=${pageNo}",'item');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#orderOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/mdm/item/itemAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&categoryId="+$("#selectId").val(),'item');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/mdm/item/itemAction!goListContent.action?name="+name+"&categoryId="+$("#selectId").val()+'&companyCode='+$("#companyCode").val(),'item');
}

function deleteChoose(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	if(ids == ''){
		asyncbox.confirm('请选择需要删除的${vv:varView('vix_mdm_item')}!','提示信息');
		return;
	}
	asyncbox.confirm('确定要删除选中的${vv:varView('vix_mdm_item')}么?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/mdm/item/itemAction!deleteByIds.action?ids='+ids,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						pager("current","${vix}/mdm/item/itemAction!goListContent.action?name=",'item');
					});
				  }
			});
		}
	});
}
function reset(){
	$('#nameS').val('');
}
//选择公司
function chooseCompany(){
	$.ajax({
		  url:'${vix}/common/org/organizationAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择公司",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#companyCode").val(result[0]);
								$("#companyName").html(result[1]);
								loadName();
								pager('start',"${vix}/mdm/item/itemAction!goListContent.action?name="+name+"&categoryId="+$("#selectId").val()+'&companyCode='+result[0],'item');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

//高级搜索 
function goSearch() {
	$.ajax({
	url : '${vix}/mdm/item/itemAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			loadConditions();
			if (action == 'ok') {
				pager("start","${vix}/mdm/item/itemAction!goListContent.action?name=" + name +"&code=" + code,'item');
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
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/item.png" alt="" />主数据</a></li>
				<li><a href="#">主数据管理</a></li>
				<li><a href="#">主数据维护</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action');">${vv:varView('vix_mdm_item')}</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name="cmn_add" /></span></a> <a href="#" onclick="fastAdd(0);"><span>快速新增</span></a> <a href="#" onclick="importItemFile();"><span>导入${vv:varView('vix_mdm_item')}</span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="${vix}/common/img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
			<li><a href="###" id="numBtn"><img src="${vix}/common/img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">${vv:varView('vix_mdm_item')}类型</a>
				<ul>
					<li><a href="#" onclick="purStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">产品</a></li>
					<li><a href="#" onclick="purStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">半成品</a></li>
					<li><a href="#" onclick="purStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">零件</a></li>
					<li><a href="#" onclick="purStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">外购件</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">近期访问</a>
				<ul>
					<li><a href="#" onclick="purRecent('T1')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="purRecent('T2')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="purRecent('T3')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="purRecent('T4')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label> <input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="itemList" var="c">
				<li><a href="###" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chinaShortName}</span>${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">分类</a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/mdm/item/itemCatalogAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/mdm/item/itemAction!goListContent.action?categoryId="+treeNode.id,'item');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#" onclick="deleteChoose();"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="itemInfo"></b> <s:text name="cmn_to" /> <b class="itemTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="item" class="table" style="height: 468px;"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="itemInfo"></b> <s:text name="cmn_to" /> <b class="itemTotalCount"></b>)
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