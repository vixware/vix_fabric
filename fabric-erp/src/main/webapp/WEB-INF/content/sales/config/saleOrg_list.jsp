<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $("#saleOrgName").val();
	name = encodeURI(name);
}
function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/sales/config/saleOrgAction!goSaveOrUpdate.action?id='+id+"&parentId="+parentId +"&treeType="+$("#selectIdType").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"新建销售组织",
					html:html,  
					callback : function(action){
						if(action == 'ok'){
							if($("#organizationUnitForm").validationEngine('validate')){
								var queryString = $('#organizationUnitForm').formSerialize(); 
								$.post('${vix}/sales/config/saleOrgAction!saveOrUpdate.action',
									queryString,
									function(result){
										asyncbox.success(result,"<s:text name='销售组织'/>",function(action){
											var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
											if(null != treeNode){
												treeNode.isParent = true;
											}
											zTree.reAsyncChildNodes(treeNode, "refresh");
											pager("start","${vix}/sales/config/saleOrgAction!goSingleList.action?orgName=&id="+$("#selectId").val()+"&treeType="+$("#selectIdType").val(),'saleOrg');
										});
									}
								 );
							}
							else{
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
		  url:'${vix}/sales/config/saleOrgAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='销售组织'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/sales/config/saleOrgAction!goSingleList.action?fullName="+name+"&id="+$("#selectId").val()+"&treeType="+treeNode.treeType,'saleOrg');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/sales/config/saleOrgAction!goSingleList.action?fullName="+name,'saleOrg');
}

loadName();
//载入分页列表数据
pager("start","${vix}/sales/config/saleOrgAction!goSingleList.action?fullName="+name,'saleOrg');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/sales/config/saleOrgAction!goSingleList.action?fullName=",'saleOrg');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#saleOrgOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/config/saleOrgAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&fullName="+name+"&id="+$("#selectId").val()+"&treeType="+$("#selectIdType").val(),'saleOrg');
}

function saleOrgPager(tag,entity){
	loadName();
	pager(tag,"${vix}/sales/config/saleOrgAction!goSingleList.action?fullName="+name+'&id='+$('#selectId').val()+"&treeType="+$("#selectIdType").val(),'saleOrg');
}

bindSearch();
bindSwitch();
function saleOrgTab(num,befor,id,e,entity){
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
		saleOrgPager('start',entity);
	}
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});

//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
/* 加载快捷菜单 */
loadMenuContent('${vix}/sales/salesAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_dep.png" alt="" />供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/config/saleOrgAction!goList.action');">销售组织</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
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
			<!-- 菜单栏->状态 -->
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="审批进度" /></a>
				<ul>
					<li><a href="#" onclick="hrStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">
						<s:text name="cmn_unapproved_plan" /></a></li>
					<li><a href="#" onclick="hrStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">
						<s:text name="cmn_approval_by_plan" /></a></li>
					<li><a href="#" onclick="hrStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">
						<s:text name="cmn_approval_in" /></a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label>填写内容：<input type="text" class="int" id="saleOrgName"></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" /></label>
			<strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label>申请名称：<input id="orgName" type="text" class="int" name=""></label> <label>申请部门：<input id="hr_contacts" type="text" class="int" name=""></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn"
				value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
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
						url:"${vix}/sales/config/saleOrgAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/sales/config/saleOrgAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"saleOrg");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdType" name="selectIdType" value="${treeType}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="saleOrgPager('start','saleOrg');"></a></span> <span><a class="previous" onclick="saleOrgPager('previous','saleOrg');"></a></span> <em>(<b class="saleOrgInfo"></b> <s:text name='cmn_to' /> <b class="saleOrgTotalCount"></b>)
						</em> <span><a class="next" onclick="saleOrgPager('next','saleOrg');"></a></span> <span><a class="end" onclick="saleOrgPager('end','saleOrg');"></a></span>
					</div>
				</div>
				<div id="saleOrg" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="saleOrgPager('start','saleOrg');"></a></span> <span><a class="previous" onclick="saleOrgPager('previous','saleOrg');"></a></span> <em>(<b class="saleOrgInfo"></b> <s:text name='cmn_to' /> <b class="saleOrgTotalCount"></b>)
						</em> <span><a class="next" onclick="saleOrgPager('next','saleOrg');"></a></span> <span><a class="end" onclick="saleOrgPager('end','saleOrg');"></a></span>
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