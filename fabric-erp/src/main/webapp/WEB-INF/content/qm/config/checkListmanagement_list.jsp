<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/qm/config/checkListmanagementAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 80,
					title:"检验单管理",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/qm/config/checkListmanagementAction!saveOrUpdate.action',
									 {'checkListmanagement.id':$("#id").val(),									  
									  'checkListmanagement.code':$("#code").val(),						  
									  'checkListmanagement.name':$("#name").val(),						  
									  'checkListmanagement.memo':$("#memo").val(),						  
									  'checkListmanagement.createTime':$("#createTime").val(),	
									  'checkListmanagement.enable':$('input:radio[name=enable]:checked').val()						  
									},
									 function (result){
										showMessage (result);
										setTimeout ("" , 1000);
										loadContent ('${vix}/qm/config/checkListmanagementAction!goList.action');
									}
								 ); 
			  				}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/qm/config/checkListmanagementAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='检验单管理'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/qm/config/checkListmanagementAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/qm/config/checkListmanagementAction!goSingleList.action?name="+name,'category');
}

loadName();
//载入分页列表数据
pager("start","${vix}/qm/config/checkListmanagementAction!goSingleList.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/qm/config/checkListmanagementAction!goSingleList.action?name=",'category');
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
	pager("start","${vix}/qm/config/checkListmanagementAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/qm/config/checkListmanagementAction!goSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

bindSearch();
bindSwitch();
function categoryTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		categoryPager('start',entity);
	}
}
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/qm/pmMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>

			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
			<%-- <a href="#" onclick="deleteByIds(0,$('#selectId').val());"><span><s:text name='cmn_delete'/></span></a> --%>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="cmn_uncommitted" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="cmn_unapproved" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/verifying.png"> <s:text name="cmn_approval_in" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/approved.png"> <s:text name="cmn_approval" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name='cmn_content' /><input type="text" class="int" id="nameS"></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" /> <input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" />
			</label> <strong id="lb_search_advanced"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name='编码' />:<input name="" type="text" class="int" /></label> <label><s:text name='检验状态' />:<input name="" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" /><input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<li><a href="#">111</a></li>
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="categoryList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/qm/config/checkListmanagementAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/qm/config/checkListmanagementAction!goSingleList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/common_listx16.png" alt="" />
						<s:text name='qm_check_list_management' /></a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_operate'/></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_mail'/></a></li>
								<li><a href="#"><s:text name="cmn_merger"/></a></li>
								<li><a href="#"><s:text name="cmn_export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount2">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
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