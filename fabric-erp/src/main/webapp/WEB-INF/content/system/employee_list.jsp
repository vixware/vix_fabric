<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}

function saveOrUpdate(id,departmentId){
	$.ajax({
		  url:'${vix}/system/employeeAction!goSaveOrUpdate.action?id='+id+"&departmentId="+departmentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 380,
					title:"职员信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$.post('${vix}/system/employeeAction!saveOrUpdate.action',
								 {'employee.id':$("#id").val(),
								  'employee.code':$("#code").val(),
								  'employee.name':$("#name").val(),
								  'employee.birthday':$("#birthday").val(),
								  'employee.gender':$(":radio[name=gender][checked]").val(),
								  'employee.isMarriage':$(":radio[name=isMarriage][checked]").val(),
								  'employee.graduation':$("#graduation").val(),
								  'employee.professional':$("#professional").val(),
								  'employee.birthplace':$("#birthplace").val(),
								  'employee.currentResidence':$("#currentResidence").val(),
								  'employee.national':$("#national").val(),
								  'employee.isDemission':$(":radio[name=isDemission][checked]").val(),
								  'employee.user.id':$("#userId").val(),
								  'employee.department.id':$("#deptId").val()
								},
								function(result){
									asyncbox.success(result,"提示信息",function(action){
										zTree.reAsyncChildNodes(null, "refresh");
										changePage("start","${vix}/system/employeeAction!goSingleList.action?name="+name);
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
		  url:'${vix}/system/employeeAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				changePage("start","${vix}/system/employeeAction!goSingleList.action?name="+name);
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	changePage("start","${vix}/system/employeeAction!goSingleList.action?name="+name);
}
 
bindSearchSwitch();
loadName();
//载入分页列表数据
changePage("start","${vix}/system/employeeAction!goSingleList.action?name="+name);
function loadRoot(){
	$('#name').val("");
	changePage("start","${vix}/system/employeeAction!goSingleList.action?name=");
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#orderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	changePage("start","${vix}/system/employeeAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&id="+$("#selectId").val());
}
</script>
<div id="switch"></div>
<div id="box">
	<div id="box_left">
		<input type="hidden" id="selectId" name="selectId" value="0" />
		<div id="tree" style="width: 96%; overflow-x: scroll; border: 1px solid gray; border-radius: 4px 4px 4px 4px;">
			<div style="padding-left: 4px;">
				<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">根目录</a>
			</div>
			<div id="tree_root" class="ztree" style="padding: 0;"></div>
		</div>
		<script type="text/javascript">
			<!--
			$("#tree").css({"height":getTotalHeight()-100});
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
				changePage("start","${vix}/system/employeeAction!goSingleList.action?id="+treeNode.id);
			}
			zTree = $.fn.zTree.init($("#tree_root"), setting);
			//-->
			</script>
	</div>
	<!-- box-left -->
	<div id="box_right">
		<div id="switch_search"></div>
		<div class="search">
			<div id="search_bar">
				<h2>
					<img src="${vix}/common/img/icon_21.gif" alt="" />搜索
				</h2>
				<hr />
				<p>
					<label>权限：<input id="nameS" value="${name}" type="text" /></label> <label><input type="button" value="查询" class="btn" onclick="searchForRightContent();" /></label>
				</p>
			</div>
			<div class="table">
				<p id="table_top">
					<a href="#" onclick="saveOrUpdate(0,$('#selectId').val())"><img src="${vix}/common/img/icon_22.gif" alt="" />
					<s:text name="add" /></a>
				</p>
				<div id="tableContent"></div>
				<div class="pagelist">
					<strong>共有<i id="totalCount">${pager.totalCount}</i>条数据,当前第<i id="pageNo">${pager.pageNo}</i>页,共<i id="totalPage">${pager.totalPage}</i>页
					</strong> <span> <b><a id="start" href="#" onclick="changePage('start','${vix}/system/employeeAction!goSingleList.action?name='+$('#nameS').val());">首页</a></b> <b><a id="previous" href="#" onclick="changePage('previous','${vix}/system/employeeAction!goSingleList.action?name='+$('#nameS').val());">上一页</a></b> <b><input id="pageNo_text"
							type="text" style="text-align: center;" size="4" value="${pager.pageNo}" onkeydown="pageNoChangeClick(this,event,'${vix}/system/employeeAction!goSingleList.action?name='+$('#nameS').val());" /></b> <b><a id="next" href="#" onclick="changePage('next','${vix}/system/employeeAction!goSingleList.action?name='+$('#nameS').val());">下一页</a></b> <b><a
							id="end" href="#" onclick="changePage('end','${vix}/system/employeeAction!goSingleList.action?name='+$('#nameS').val());">尾页</a></b>
					</span>
				</div>
			</div>
		</div>
	</div>
	<!-- box-right -->
</div>