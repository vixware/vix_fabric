<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	initAuthorityTree();
});


var zTreeAuthority;

function initAuthorityTree(){
	var bizTypeTmp = $("#authorityBizType").val();//var bizType = $("#authorityBizType").val();
	
	var treeNodeTmp = [];
	$.ajax({
		  url:'${vix}/system/industryManagementAuthorityAction!findAuthorityTreeData.action',
		  data:{bizType : bizTypeTmp, industryMgtModuleId : '${id}'},
		  cache: false,
		  async:false,
		  dataType : "text",
		  success: function(data){
			  if(data!=""){
				  //treeNodeTmp = new Array($.parseJSON(data.));
				  treeNodeTmp = $.parseJSON(data).authdata;
			  }
		  }
	});
	//debugger;
	//treeNodeTmp	=[ {id:13491,pId:0,name:"工作台",authType:"null",checkId:13491,isParent:true,isCheck:false,children:[]}];
	var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom
		},
		check: {
			enable: true,
			chkStyle: "checkbox",
			radioType: "level"
		},	
		callback: {
			onClick: onClick
		}
	};
	function addHoverDom(treeId, treeNode){
		var aObj = $("#" + treeNode.tId + "_a");
		if ($("#auTreeDiv_"+treeNode.id).length>0) return;
		var editStr = "<span id='auTreeDiv_space_" +treeNode.id+ "' title='授权所有子节点' style='margin:20px;'>授权</span>"
			//+ "<button type='button' id='diyBtn_" + treeNode.id
			//+ "' title='"+treeNode.name+"' onfocus='this.blur();'>B</button>";
			+ "<span id='auTreeDiv_" + treeNode.id
			+ "' title='取消所有子节点' onfocus='this.blur();' style='margin:10px;'>取消</button>";
		aObj.append(editStr); 
		/* 
		var editStr = "<img src='${vix}/common/img/cmn_sec_im_auth.png' id='auTreeDiv_space_" +treeNode.id+ "' alt='授权所有子节点'  />"
		//+ "<button type='button' id='diyBtn_" + treeNode.id
		//+ "' title='"+treeNode.name+"' onfocus='this.blur();'>B</button>";
		+ "<img src='${vix}/common/img/cmn_sec_im_auth_cl.png' id='auTreeDiv_" + treeNode.id
		+ "' alt='取消所有子节点' />";
		aObj.append(editStr); */
	
		//授权
		var btn = $("#auTreeDiv_space_"+treeNode.id);
		if(btn){
			btn.bind("click", function(){
				asyncbox.confirm('确定要授权\''+treeNode.name+'\'及所有子权限么?','<s:text name="cmn_message"/>',function(action){
					if(action == 'ok'){
						//alert("授权:"+treeNode.id + "####" +treeNode.name);
						$.post('${vix}/system/industryManagementAuthorityAction!saveForAuthorityByAuthId.action',
								{
									industryMgtModuleId :'${id}',
									bizType : bizTypeTmp,
									authId : treeNode.id									
								},
								function(result){
									asyncbox.success(result,"提示信息",function(action){
										// 当前节点 和 子节点全部选中
										zTreeAuthority.checkNode(treeNode,true,true,false);
										//treeNode 节点,   checked = true 表示勾选节点, 进行父子节点的勾选联动操作, 执行此方法时触发 beforeCheck & onCheck 事件回调函数

									});
								}
						 );
						
					}
				});
			});
		}
	
		//取消授权
		var btn = $("#auTreeDiv_"+treeNode.id);
		if(btn){
			btn.bind("click", function(){
				asyncbox.confirm('确定要取消授权\''+treeNode.name+'\'及所有子权限么?','<s:text name="cmn_message"/>',function(action){
					if(action == 'ok'){
						//alert("取消:"+treeNode.id + "####" +treeNode.name);//loadAuthorityFunGrid(treeNode.id);
						$.post('${vix}/system/industryManagementAuthorityAction!deleteForAuthorityByAuthId.action',
								{
									industryMgtModuleId :'${id}',
									bizType : bizTypeTmp,
									authId : treeNode.id									
								},
								function(result){
									asyncbox.success(result,"提示信息",function(action){
										// 当前节点 和 子节点全部 取消 选中
										zTreeAuthority.checkNode(treeNode,false,true,false);
										//treeNode 节点,   checked = true 表示勾选节点, 进行父子节点的勾选联动操作, 执行此方法时触发 beforeCheck & onCheck 事件回调函数
									});
								}
						 );
					}
				});
				//alert("diy Button for " + treeNode.name);});
				
			});
		}
			
	};
	function removeHoverDom(treeId, treeNode){
		$("#auTreeDiv_"+treeNode.id).unbind().remove();
		$("#auTreeDiv_space_" +treeNode.id).unbind().remove();
	};
	
	function onClick(event, treeId, treeNode, clickFlag) {
		//alert("treeNode.tId:"+treeNode.tId);
		//debugger;
		$("#selectId").val(treeNode.id);
		//$("#selectIdTreeId").val(treeNode.tId);
		//$("#selectBizType").val(treeNode.bizType);//&bizType=
		/* pager("start","${vix}/system/industryManagementAction!goAuthorityTreeSingleList.action?industryMgtId=${id}&treeId="+treeNode.treeId+"&bizType="+treeNode.bizType,"category");
	 	*/
		//loadAuthorityFunGrid(treeNode.id);
		loadAuthorityFunGrid(treeNode.authorityCode);
	};
	zTreeAuthority = $.fn.zTree.init($("#tree_root"), setting, treeNodeTmp);
	
	
	loadAuthorityFunGrid('-1');
};

var initDgCheckedRow = null;
//右侧的列表
function loadAuthorityFunGrid(selectCode){
	initDgCheckedRow = new Array();
	$('#dgAuthFun').datagrid({
		//title:'元数据属性信息 ',
		width:800,
		height:250,
		singleSelect: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/system/industryManagementAuthorityAction!findFunGrid.action',
		queryParams : {
			industryMgtModuleId : '${id}',
			//authId : selectId,
			authCode : selectCode,
			bizType : $("#authorityBizType").val()
		},
		remoteSort: false,
		idField:'id',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'name',title:'名称 ',width:200,editor:'text'},
			{field:'memo',title:'说明',width:200,editor:'text'}
		]],
		/* columns:[[
			{field:'propertyCode',title:'编码 ',width:120,editor:'text'},
			{field:'property',title:'属性 ',width:150,editor:'text'}
		]], */
		pagination:false,
		rownumbers:true,
		onLoadSuccess:function(data){
			 var rowData = data.rows;
			$.each(rowData, function (index, value) {
				 if (value.isCheck == "Y") {
					 $("#dgAuthFun").datagrid("checkRow", index);
					 initDgCheckedRow.push(value.id);
				 }
			});
		}
		
	});
};

//载入分页列表数据
//pager("start","${vix}/common/security/authority/authorityAction!goSingleList.action?authorityName="+name,'category');
/* 
function toLoadSubAuthority(){
	$.ajax({
		  url:"${vix}/system/industryManagementAction!goAuthorityTreeSingleList.action?industryMgtId=${industryMgtId}&treeId=${treeId}&bizType=${bizType}",
		  cache: false,
		  success: function(html){
			  $("#category").html(html);
		  }
	});
}
*/

/**
* 保存行业和菜单配置关系
*/
function saveForAuthority(){
	
	var zTreeTmp = $.fn.zTree.getZTreeObj("tree_root");
	
	
	//判断左侧树选中是否有变化
	var changedNodes = zTreeTmp.getChangeCheckedNodes();
	var isChangedChecked = false;
	if(changedNodes.length>0){
		isChangedChecked = true;
	}
	
	var isChangdeFun = false;
	var checkedFunArray = new Array();//右侧的选择
	
	if(initDgCheckedRow!=null){//已经加载过grid
		var checkedFunObj = $('#dgAuthFun').datagrid('getChecked');
		
		$.each(checkedFunObj, function(index, item){
			checkedFunArray.push(item.id);
		});
		if(initDgCheckedRow!=null){
			isChangdeFun = (initDgCheckedRow.sort().toString() != checkedFunArray.sort().toString());
		}
	}
	
	// isChangedChecked  左侧的树是否有变化
	// isChangdeFun  右侧列表选择 是否有变化
	//alert(isChangdeFun);
	//return;
	//获取左侧树全部选中的节点
	if(!isChangedChecked && !isChangdeFun){
		asyncbox.alert("授权没有修改!","提示信息");
		return;
	}
	
	var nodes = zTreeTmp.getCheckedNodes(true);
	var objIdsArray = new Array();
	for (var i=0, l=nodes.length; i<l; i++) {
		objIdsArray.push(nodes[i].id);
	}
	
	//类型
	var bizTypeTmp = $("#authorityBizType").val();
	
	$.post('${vix}/system/industryManagementAuthorityAction!saveForAuthority.action',
		{
			industryMgtModuleId:'${id}',
			bizType : bizTypeTmp,
			menuIds : objIdsArray.join(),
			funIds : checkedFunArray.join(),
			checkedMenuId : $("#selectId").val(),
			isChangCheckMenu : isChangedChecked,
			isChangeCheckFun : isChangdeFun
			
		},
		function(result){
			asyncbox.success(result,"提示信息",function(action){
				//loadContent('${vix}/common/security/dataColPolicyAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');
				//accept();
			});
		}
	 );
};


function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	//pager("start","${vix}/common/security/authority/authorityAction!goSingleList.action?authorityName=",'category');
};

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
				<li><a href="#">行业模块管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveForAuthority();"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/system/industryManagementAction!goList.action');"><span>返回</span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>权限类别：<s:select list="bizTypeMap" id="authorityBizType" listKey="key" listValue="value" onchange="initAuthorityTree();" theme="simple"></s:select>
			</label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="initAuthorityTree();" />
		</div>
	</div>
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div> --%>
				<div id="tree_root" class="ztree" style="padding: 0; height: 500px; overflow: scroll;"></div>
			</div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" /> <input type="hidden" id="selectBizType" name="selectBizType" />


		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/mail.png" alt="" />功能按钮</a></li>
					<%-- <li><a onclick="categoryTab(3,2,'a',event,'brand')"><img src="img/mail.png" alt="" /><s:text name="brand"/></a></li> --%>
				</ul>
			</div>
			<div class="right_content" id="a1">

				<div id="categoryFun" class="table" style="width: 100%;">
					<table id="dgAuthFun"></table>
				</div>

			</div>
			<!-- right -->
		</div>
		<div class="c_foot">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
		<!-- c_foot -->
	</div>