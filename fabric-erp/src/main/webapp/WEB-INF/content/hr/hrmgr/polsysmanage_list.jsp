<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var code = "";
var name = "";
var contacts ="";
function loadCode(){ 
	code = $('#srm_code').val();
	code=Url.encode(code);
	code=Url.encode(code);
} 
function loadName(){
	name = $('#srm_name').val();
	name=Url.encode(name);
	name=Url.encode(name);
} 
function loadContacts(){
	contacts = $('#srm_contacts').val();
	contacts=Url.encode(contacts);
	contacts=Url.encode(contacts);
}
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/hr/polSysManageAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 700,
					height : 400,
					title:"新增政策制度分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#supplierCategoryForm').validationEngine('validate')){
								$.post('${vix}/hr/polSysManageAction!saveOrUpdate.action?parentId='+$("#newScId").val(), {
										'polSysManage.id' : $("#newId").val(),
										'polSysManage.code' : $("#newCode").val(),
										'polSysManage.name' : $("#newName").val()
										}, function(result){
											showMessage(result);
											setTimeout("",1000);
											loadContent('${vix}/hr/polSysManageAction!goList.action');
										});
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

function saveOrUpdatePol(id,pageNo){
	$.ajax({
		  url:'${vix}/hr/polSysManageAction!goSaveOrUpdatePol.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 700,
					height : 400,
					title:"新增政策制度",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#supplierCategoryForm').validationEngine('validate')){
								$.post('${vix}/hr/polSysManageAction!saveOrUpdatePol.action?parentId='+$("#newScId").val(), {
										'polSysManage.id' : $("#newId").val(),
										'polSysManage.code' : $("#newCode").val(),
										'polSysManage.name' : $("#newName").val()
										}, function(result){
											showMessage(result);
											setTimeout("",1000);
											loadContent('${vix}/hr/polSysManageAction!goList.action');
										});
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
		  url:'${vix}/hr/polSysManageAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/hr/polSysManageAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}
/*改变搜索按钮的显示*/
function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#srm_code").attr({disabled:'true'});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#srm_code").removeAttr("disabled");
		$("#simpleSearch").show();
		$("#simpleReset").show();
	}
}
/*搜索*/
function searchForContent(i){
	loadCode();
	loadName();
	loadContacts();
	if(i == 0){
		pager("start","${vix}/hr/polSysManageAction!goSearchList.action?i="+i+"&searchContent="+code,'category');
	}
	else{
		pager("start","${vix}/hr/polSysManageAction!goSearchList.action?i="+i+"&contacts="+contacts+"&name="+name,'category');
	}
}
/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}
/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#srm_code").val("");
	}
	else{
		$("#srm_name").val("");
		$("#srm_contacts").val("");
	}
}

loadCode();
//载入分页列表数据
pager("start","${vix}/hr/polSysManageAction!goSingleList.action?code="+code+"&pageNo=${pageNo}",'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/hr/polSysManageAction!goSingleList.action?name=",'category');
}
//排序 
function orderBy(orderField){
	loadCode();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/hr/polSysManageAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadCode();
	if(entity == 'category'){
		pager(tag,"${vix}/hr/polSysManageAction!goSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
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
//状态
function srmStatus(status){
	pager("start","${vix}/hr/polSysManageAction!goSingleList.action?status="+status,'category');
}
//最近使用
function srmRecent(rencentDate){
	pager("start","${vix}/hr/polSysManageAction!goSingleList.action?updateTime="+rencentDate,'category');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_empma.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#"><s:text name="hr_staff_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/polSysManageAction!goList.action');"><s:text name='hr_policy_system_manage' /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a> <a href="#" onclick="saveOrUpdatePol(0,$('#selectId').val());"><span>新增政策</span></a>
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
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a>
				<ul>
					<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="srmStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
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
		<div>
			<label><s:text name='srm_supplier_code' />:<input id="srm_code" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>"
				onclick="resetForContent(0);" />
			</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name='eam_suppliername' />:<input id="srm_name" name="" type="text" class="int" /></label> <label>联系人:<input id="srm_contacts" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button"
				class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="polSysManagesList" var="s">
				<li><a href="#" onclick="saveOrUpdate(${s.id});">${s.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">根目录</a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/hr/polSysManageAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/hr/polSysManageAction!goSingleList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/common_listx16.png" alt="" />政策制度</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
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