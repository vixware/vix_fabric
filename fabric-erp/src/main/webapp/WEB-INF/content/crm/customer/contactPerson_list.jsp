<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
/* var customerAccountName = "";
var firstName = ""; */
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
	/* firstName = $('#firstName').val();
	firstName = Url.encode(firstName);
	firstName = Url.encode(firstName);
	customerAccountName = $('#customerAccountName').val();
	customerAccountName = Url.encode(customerAccountName);
	customerAccountName = Url.encode(customerAccountName); */
}

function saveOrUpdate(id){
	var pageNo = $("#contactPersonPageNoHidden").val();
	$.ajax({
		  url:'${vix}/crm/customer/crmContactPersonAction!goSingleSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 630,
					title:"联系人信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#contactPersonForm').validationEngine('validate')){
								$.post('${vix}/crm/customer/crmContactPersonAction!saveOrUpdate.action',
										{ 
										  'contactPerson.id': $("#id").val(),
									      'contactPerson.name':$("#fullName").val(),
									      'contactPerson.lastName':$("#lastName").val(),
										  'contactPerson.firstName':$("#firstName").val(),
										  'contactPerson.callTitle':$("#callTitle").val(),
										  'contactPerson.sex':$(":radio[name=sex][checked]").val(),
										  'contactPerson.crmContactType.id':$("#crmContactTypeId").val(),
										  'contactPerson.birthday':$("#birthday").val(),
										  'contactPerson.company':$("#company").val(),
										  'contactPerson.department':$("#department").val(),
										  'contactPerson.position':$("#position").val(),
										  'contactPerson.mobile':$("#mobile").val(),
										  'contactPerson.presideBusiness':$("#presideBusiness").val(),
										  'contactPerson.directPhone':$("#directPhone").val(),
										  'contactPerson.email':$("#email").val(),
										  'contactPerson.phoneHome':$("#phoneHome").val(),
										  'contactPerson.msnAccount':$("#msnAccount").val(),
										  'contactPerson.qqAccount':$("#qqAccount").val(),
										  'contactPerson.skypeAccount':$("#skypeAccount").val(),
										  'contactPerson.wangAccount':$("#wangAccount").val(),
										  'contactPerson.fax':$("#fax").val(),
										  'contactPerson.dateEntered':$("#dateEntered").val(),
										  'contactPerson.createdBy':$("#createdBy").val(),
										  'contactPerson.customerAccount.id':$("#customerAccountId").val()
										},
										function(result){
											if (result != null) {
												showMessage('信息保存成功');
											} else {
												showErrorMessage('信息保存失败');
											}
											pager("start","${vix}/crm/customer/crmContactPersonAction!goListContent.action?name=",'contactPerson');
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
		  url:'${vix}/crm/customer/crmContactPersonAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/crm/customer/crmContactPersonAction!goListContent.action?name="+name,'contactPerson');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/crm/customer/crmContactPersonAction!goListContent.action?name="+name,'contactPerson');
}

loadName();
//载入分页列表数据
pager("start","${vix}/crm/customer/crmContactPersonAction!goListContent.action?name="+name+"&pageNo=${pageNo}",'contactPerson');

//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#contactPersonOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/customer/crmContactPersonAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'contactPerson');
}

function contactPersonPager(tag){
	loadName();
	pager(tag,"${vix}/crm/customer/crmContactPersonAction!goListContent.action?name="+name ,'contactPerson');
}

bindSearch();

loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');

//高级搜索
function goSearch() {
	//loadName();
	$.ajax({
	url : '${vix}/crm/customer/crmContactPersonAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				pager("start", "${vix}/crm/customer/crmContactPersonAction!goListContent.action?customerAccountName=" + $("#customerAccountName").val() + "&firstName=" + $("#firstName").val(), 'contactPerson');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

function reset(){
	$('#nameS').val('');
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
				<li><a href="#"><img src="${vix}/common/img/crm/contactPerson.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmContactPersonAction!goList.action');">联系人管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate('');"><span><s:text name='cmn_add' /></span></a>
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
			<!-- 菜单栏->状态 -->
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_category" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_unapproved_plan" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_approval_by_plan" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_approval_in" /></a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"></label>
			<!--  	<label><s:text name="my_item"/><input type="checkbox" value="" name=""></label>-->
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="reset();"></label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="indexList" var="index">
				<li><a href="###" onclick="saveOrUpdate(${index.id});"><span style="display: none;">${index.chineseFirstLetter}</span>${index.lastName}</a></li>
			</s:iterator>
		</ul>
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
	            	async : {
		            	enable : true,
		                url:"${vix}/common/org/organizationUnitAction!findOrgAndUnitTreeToJson.action",
		                autoParam : [ "id", "treeType" ]
	                },
	                callback : {
	                	onClick : onClick
	                }
	            };
                function onClick(event,treeId,treeNode,clickFlag) {
                	$("#selectId").val(treeNode.id);
                	pager("start","${vix}/crm/customer/crmContactPersonAction!goListContent.action?name="+name+"&companyCode="+treeNode.id,'contactPerson');
                }
                zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
			<input type="hidden" id="selectId" />
		</div>
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
						<span><a class="start" onclick="contactPersonPager('start','contactPerson');"></a></span> <span><a class="previous" onclick="contactPersonPager('previous','contactPerson');"></a></span> <em>(<b class="contactPersonInfo"></b> <s:text name='cmn_to' /> <b class="contactPersonTotalCount"></b>)
						</em> <span><a class="next" onclick="contactPersonPager('next','contactPerson');"></a></span> <span><a class="end" onclick="contactPersonPager('end','contactPerson');"></a></span>
					</div>
				</div>
				<div id="contactPerson" class="table"></div>
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
						<span><a class="start" onclick="contactPersonPager('start','contactPerson');"></a></span> <span><a class="previous" onclick="contactPersonPager('previous','contactPerson');"></a></span> <em>(<b class="contactPersonInfo"></b> <s:text name='cmn_to' /> <b class="contactPersonTotalCount"></b>)
						</em> <span><a class="next" onclick="contactPersonPager('next','contactPerson');"></a></span> <span><a class="end" onclick="contactPersonPager('end','contactPerson');"></a></span>
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