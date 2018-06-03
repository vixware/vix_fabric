<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
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
function saveOrUpdate(id,tag){
	var pageNo = $("#customerAccountPageNoHidden").val();
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goSaveOrUpdate.action?source=${source}&id='+id+"&customerAccountType="+tag+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
function goCustomerView(id,tag){
	var pageNo = $("#customerAccountPageNoHidden").val();
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goCustomerView.action?source=${source}&id='+id+"&customerAccountType="+tag+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
function goMemberView(id){
	var pageNo = $("#customerAccountPageNoHidden").val();
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goMemberView.action?source=${source}&id='+id+"&customerAccountType=enterPrise&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
function addToBlackList(id){
	$.ajax({
		  url:'${vix}/crm/member/customerBlackListAction!goAddReason.action?type=add&id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 200,
					title:"加入黑名单",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#addReasonForm').validationEngine('validate')){
								$.post('${vix}/crm/member/customerBlackListAction!addReason.action',
									 {'id':id,
									  'reason':$("#reason").val(),
									  'type':'add'
									 },
									function(result){
										asyncbox.success(result,"提示信息",function(action){
											pager("start","${vix}/mdm/crm/customerAccountAction!goListContent.action?source=${source}&name="+name,'customerAccount');
										});
									}
								);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function deleteById(id){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/mdm/crm/customerAccountAction!goListContent.action?name="+name,'customerAccount');
			});
		  }
	});
}

function deleteByIds(){
	var ids = '';
	$("[name='chkCustomerAccountIds']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	if(ids == ''){
		asyncbox.confirm('请选择需要删除的客户!','<s:text name='vix_message'/>');
		return;
	}
	asyncbox.confirm('确定要删除选中的客户么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/mdm/crm/customerAccountAction!deleteByIds.action?ids='+ids,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
						pager("start","${vix}/mdm/crm/customerAccountAction!goListContent.action?name="+name+"&source=${source}",'customerAccount');
					});
				  }
			});
		}
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/mdm/crm/customerAccountAction!goListContent.action?name="+name+"&source=${source}",'customerAccount');
}

loadName();
//载入分页列表数据
pager("start","${vix}/mdm/crm/customerAccountAction!goListContent.action?name="+name+"&pageNo=${pageNo}&source=${source}",'customerAccount');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/mdm/crm/customerAccountAction!goListContent.action?source=${source}",'customerAccount');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#customerAccountOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/mdm/crm/customerAccountAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val()+"&source=${source}",'customerAccount');
}

function customerAccountPager(tag){
	loadName();
	pager(tag,"${vix}/mdm/crm/customerAccountAction!goListContent.action?name="+name+'&parentId='+$('#selectId').val()+'&companyCode='+$("#companyCode").val()+"&source=${source}",'customerAccount');
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
								pager('start',"${vix}/mdm/crm/customerAccountAction!goListContent.action?name="+name+'&parentId='+$('#selectId').val()+'&companyCode='+result[0]+"&source=${source}",'customerAccount');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function importCustomerAccountFile(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!chooseCustomerAccountFile.action',
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
							$("#customerAccountFileForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/mdm/crm/customerAccountAction!importCustomerAccountFile.action",
							     dataType: "text",
							     success: function(result){
							    	 asyncbox.success(result,"提示信息",function(action){
							    		 loadContent("${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}");
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
bindSearch();
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
bindSwitch();
$("#switch_box").click();

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='mdm' ">
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />主数据管理</a></li>
					<li><a href="#">主数据维护</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=mdm');">客户</a></li>
				</s:if>
				<s:elseif test="source =='member'">
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
					<li><a href="#">会员交互管理</a></li>
					<li><a href="#">会员管理</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=member');">会员列表</a></li>
				</s:elseif>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
					<li><a href="#">客户关系管理</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}');">客户</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<s:if test="source =='member' ">
				<a href="#" onclick="saveOrUpdate(0,'member');"><span>新增会员</span></a>
			</s:if>
			<s:else>
				<a href="#" onclick="saveOrUpdate(0,'enterPrise');"><span>新增企业客户</span></a>
				<a href="#" onclick="saveOrUpdate(0,'internal');"><span>新增内部客户</span></a>
				<a href="#" onclick="saveOrUpdate(0,'personal');"><span>新增个人客户</span></a>
				<a href="#" onclick="importCustomerAccountFile();"><span>导入客户</span></a>
				<%-- <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete'/></span></a> --%>
			</s:else>
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
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
		</ul>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label> <strong id="lb_search_advanced"><s:text
					name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="sa_ids" /><input type="text" class="int" name=""></label> <label><s:text name="sa_subjects" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='search'/>" class="btn" name=""><input type="button" value="<s:text name='reset'/>" class="btn" name=""></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="caList" var="ca">
				<li><a href="###" onclick="saveOrUpdate(${ca.id},$('#selectId').val());"><span style="display: none;">${ca.chineseFirstLetter}</span>${ca.name}</a></li>
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
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/crm/customer/crmCustomerAccountCategoryAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/crm/customer/crmCustomerAccountAction!goListContent.action?id="+treeNode.id,'customerAccount');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
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
						<span><a class="start" onclick="customerAccountPager('start','account');"></a></span> <span><a class="previous" onclick="customerAccountPager('previous','account');"></a></span> <em>(<b class="customerAccountInfo"></b> <s:text name='cmn_to' /> <b class="customerAccountTotalCount"></b>)
						</em> <span><a class="next" onclick="customerAccountPager('next','account');"></a></span> <span><a class="end" onclick="customerAccountPager('end','account');"></a></span>
					</div>
				</div>
				<div id="customerAccount" class="table"></div>
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
						<span><a class="start" onclick="customerAccountPager('start','account');"></a></span> <span><a class="previous" onclick="customerAccountPager('previous','account');"></a></span> <em>(<b class="customerAccountInfo"></b> <s:text name='cmn_to' /> <b class="customerAccountTotalCount"></b>)
						</em> <span><a class="next" onclick="customerAccountPager('next','account');"></a></span> <span><a class="end" onclick="customerAccountPager('end','account');"></a></span>
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