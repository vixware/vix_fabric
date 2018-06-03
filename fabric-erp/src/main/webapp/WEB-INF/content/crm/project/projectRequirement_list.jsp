<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var subject = "";
var crmProject = "";
var customerAccount = "";
function loadSearchCondition(tag){
	if(tag == 'advance'){
		subject = $('#subject').val();
	}else{
		subject = $('#subject1S').val();
	}
	subject=Url.encode(subject);
	subject=Url.encode(subject);
}
 
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/crm/project/projectRequirementAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 320,
					title:"项目需求",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$.post('${vix}/crm/project/projectRequirementAction!saveOrUpdate.action',
								 {'projectRequirement.id':$("#projectRequirementId").val(),
								  'projectRequirement.subject':$("#subject").val(),
								  'projectRequirement.provider.id':$("#providerId").val(),
								  'projectRequirement.customerAccount.id':$("#customerAccountId").val(),
								  'projectRequirement.crmProject.id':$("#crmProjectId").val(),
								  'projectRequirement.recordDate':$("#recordDate").val(),
								  'projectRequirement.description':$("#description").val()
								},
								function(result){
									asyncbox.success(result,"提示信息",function(action){
										pager("start","${vix}/crm/project/projectRequirementAction!goListContent.action?subject="+subject+"&crmProject="+crmProject+"&customerAccount="+customerAccount,'projectRequirement');
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

function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/crm/project/projectRequirementAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/crm/project/projectRequirementAction!goListContent.action?subject="+subject+"&crmProject="+crmProject+"&customerAccount="+customerAccount,'projectRequirement');
			});
		  }
	});
}

function searchForContent(){
	loadSearchCondition();
	pager("start","${vix}/crm/project/projectRequirementAction!goListContent.action?subject="+subject+"&crmProject="+crmProject+"&customerAccount="+customerAccount,'projectRequirement');
}
 
loadSearchCondition();
//载入分页列表数据
pager("start","${vix}/crm/project/projectRequirementAction!goListContent.action?subject="+subject+"&crmProject="+crmProject+"&customerAccount="+customerAccount,'projectRequirement');

function orderBy(orderField){
	loadSearchCondition();
	var orderBy = $("#projectRequirementOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/project/projectRequirementAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&subject="+subject+"&crmProject="+crmProject+"&customerAccount="+customerAccount,'projectRequirement');
}

bindSearch();
function currentPager(tag){
	loadSearchCondition();
	pager(tag,"${vix}/crm/project/projectRequirementAction!goListContent.action?subject="+subject+"&crmProject="+crmProject+"&customerAccount="+customerAccount,'projectRequirement');
}

function reset(){
	$("#subject1S").val('');
}
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');

//高级搜索 
function goSearch() {
	$.ajax({
	url : '${vix}/crm/project/projectRequirementAction!goSearch.action',
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
				pager("start","${vix}/crm/project/projectRequirementAction!goListContent.action?subject="+subject+"&crmProject="+crmProject+"&customerAccount="+customerAccount,'projectRequirement');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/projectRequirement.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">基础资料管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/projectRequirementAction!goList.action');">项目需求</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
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
		<div>
			<label>主题<input type="text" class="int" id="subject1S"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent('simple');" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="projectRequirementInfo"></b> <s:text name='cmn_to' /> <b class="projectRequirementTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="projectRequirement" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="projectRequirementInfo"></b> <s:text name='cmn_to' /> <b class="projectRequirementTotalCount"></b>)
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