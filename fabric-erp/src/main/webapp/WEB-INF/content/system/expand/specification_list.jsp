<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id,objectExpandId){
	if(objectExpandId == ''){
		asyncbox.success("请选择对象类型!","<s:text name='vix_message'/>");
		return;
	}
	$.ajax({
		  url:'${vix}/system/specificationAction!goSaveOrUpdate.action?id='+id+'&objectExpandId='+objectExpandId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 540,
					title:"对象类型规格",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#specificationForm').validationEngine('validate')){
								$.post('${vix}/system/specificationAction!saveOrUpdate.action',
									 {'specification.id':$("#id").val(),
									  'specification.code':$("#code").val(),
									  'specification.name':$("#name").val(),
									  'specification.orderBy':$("#orderBy").val(),
									  'specification.memo':$("#memo").val(),
									  'specification.objectExpand.id':$("#objectExpandId").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											pager("current","${vix}/system/specificationAction!goSingleList.action?name="+name+"&id="+$("#selectId").val(),'specification');
										});
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
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function deleteById(id){
	$.ajax({
		  url:'${vix}/system/specificationAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				pager("current","${vix}/system/specificationAction!goSingleList.action?name="+name,'specification');
			});
		  }
	});
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/system/specificationAction!goSingleList.action?name="+name+"&id="+$("#selectId").val(),'specification');
}
loadName();
//载入分页列表数据
pager("start","${vix}/system/specificationAction!goSingleList.action?name="+name,'specification');
function loadRoot(){
	$('#nameS').val("");
	$('#selectId').val("");
	pager("start","${vix}/system/specificationAction!goSingleList.action?name=",'specification');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#specificationOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/system/specificationAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&id="+$("#selectId").val(),'specification');
}

//生成商品扩展字段
function createOrUpdateTable(){
	var id = $("#selectId").val();
	if(id=='' || id ==undefined){
		asyncbox.confirm('请选择分类!','<s:text name='vix_message'/>');
	}else{
		asyncbox.confirm('确定要生成数据表么，数据表生成后将不允许修改?','<s:text name='vix_message'/>',function(action){
			if(action == 'ok'){
				$.ajax({
					url:'${vix}/system/specificationAction!createOrUpdateTable.action?id='+id,
					cache: false,
					success: function(html){
						asyncbox.success(html,"<s:text name='vix_message'/>");
						pager('current',"${vix}/system/specificationAction!goSingleList.action?name="+name+"&id=" + id,'specification');
					}
				});
			}
		});
	}
}
bindSwitch();
bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/specificationAction!goSingleList.action?name="+name+'&companyCode='+$("#companyCode").val(),'specification');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/system/specificationAction!goSingleList.action?name="+name+'&companyCode='+$("#companyCode").val(),'specification');
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
								pager('start',"${vix}/system/specificationAction!goSingleList.action?name="+name+'&companyCode='+result[0],'specification');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/objectSpecification.png" alt="" />系统管理</a></li>
				<li><a href="#">系统管理</a></li>
				<li><a href="#">基础数据管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/system/specificationAction!goList.action');">对象类型规格</a></li>
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
			<b><img src="${vix}/common/img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
		</ul>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text
					name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
		</div>
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
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/system/objectExpandAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/system/specificationAction!goSingleList.action?id="+treeNode.id,'specification');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="${vix}/common/img/mail.png" alt="" />对象类型规格</li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /></a></li>
								<li><a href="#"><s:text name='cmn_update' /></a></li>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
							</ul></li>
					</ul>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="specificationInfo"></b> <s:text name='cmn_to' /> <b class="specificationTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="specification" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
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
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="specificationInfo"></b> <s:text name='cmn_to' /> <b class="specificationTotalCount"></b>)
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