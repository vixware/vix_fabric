<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}

function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/system/departmentAction!goSaveOrUpdate.action?id='+id+"&parentId="+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 620,
					height : 380,
					title:"部门信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$.post('${vix}/system/departmentAction!saveOrUpdate.action',
								 {'department.id':$("#id").val(),
								  'department.parentDepartment.id':$("#deptId").val(),
								  'department.code':$("#code").val(),
								  'department.name':$("#name").val(),
								  'department.employeeId':$("#employeeId").val(),
								  'department.telephone':$("#telephone").val(),
								  'department.fax':$("#fax").val(),
								  'department.email':$("#email").val(),
								  'department.memo':$("#memo").val(),
								  'department.orgainzation.id':$("#orgId").val()
								},
								function(result){
									asyncbox.success(result,"提示信息",function(action){
										zTree.reAsyncChildNodes(null, "refresh");
										pager("start","${vix}/system/departmentAction!goSingleList.action?name="+name+"&id="+$("#selectId").val(),'department');
									});
								}
							 );
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/system/departmentAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				zTree.reAsyncChildNodes(null, "refresh");
				pager("start","${vix}/system/departmentAction!goSingleList.action?name="+name,'department');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/system/departmentAction!goSingleList.action?name="+name,'department');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/system/departmentAction!goSingleList.action?name="+name,'department');
function loadRoot(){
	$('#name').val("");
	pager("start","${vix}/system/departmentAction!goSingleList.action?name=",'department');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#departmentOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/system/departmentAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&id="+$("#selectId").val(),'department');
}
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/departmentAction!goSingleList.action?name="+name+"&id="+$("#selectId").val(),'department');
}
$("#search_advanced").toggle(
		function () {
			$("#c_head").addClass("advanced");
			$("#search_advanced").text("Basic Search");
		  },
		  function () {
			$("#c_head").removeClass("advanced");
			$("#search_advanced").text("Advanced Search");
		  }
	);
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">打印</a> <a href="#"><img alt="" src="img/icon_15.gif">帮助</a>
		</i> <strong><img alt="" src="img/drp_channel.png">部门管理</strong>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span>添加</span></a>
		</p>
	</div>
</div>

<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#"><img alt="" src="img/icon_10.png">数据分类</a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">热点分类</a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
				</ul></li>
		</ul>
		<div>
			<label>Name<input type="text" class="int" id="nameS"></label> <label>My items<input type="checkbox" value="" name=""></label> <label><input type="button" value="搜索" class="btn" onclick="searchForContent();"><input type="button" value="重置" class="btn" name=""></label> <strong id="search_advanced">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>名称<input type="text" class="int" name=""></label> <label>我的项目<input type="checkbox" value="" name=""></label> <label><input type="button" value="搜索" class="btn" name=""><input type="button" value="重置" class="btn" name=""></label> <label>名称<input type="text" class="int" name=""></label> <label>我的项目<input
				type="checkbox" value="" name=""></label> <label><input type="button" value="搜索" class="btn" name=""><input type="button" value="重置" class="btn" name=""></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable">
			<div id="switch_box" class="switch_btn"></div>
			<div style="padding-left: 4px;">
				<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">根目录</a>
			</div>
			<div id="tree_root" class="ztree" style="padding: 0;"></div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/system/departmentAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/system/departmentAction!goSingleList.action?id="+treeNode.id,"department");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#">Actions</a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong>选中:<span id="selectCount">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start','category');"></a></span> <span><a class="previous" onclick="currentPager('previous','category');"></a></span> <em>(<b class="departmentInfo"></b>到 <b class="departmentTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','category');"></a></span> <span><a class="end" onclick="currentPager('end','category');"></a></span>
					</div>
				</div>
				<div id="department" class="table" style="height: 428px;"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#">选项</a>
							<ul>
								<li><a href="#">删除</a></li>
								<li><a href="#">邮件</a></li>
								<li><a href="#">批量更新</a></li>
								<li><a href="#">合并</a></li>
								<li><a href="#">添加到目标列表</a></li>
								<li><a href="#">导出</a></li>
							</ul></li>
					</ul>
					<strong>选中:<span id="selectCount">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start','category');"></a></span> <span><a class="previous" onclick="currentPager('previous','category');"></a></span> <em>(<b class="departmentInfo"></b>到 <b class="departmentTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','category');"></a></span> <span><a class="end" onclick="currentPager('end','category');"></a></span>
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