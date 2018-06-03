<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}

	function saveOrUpdate(id) {
		$.ajax({
		url : '${vix}/crm/business/memberShipAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 920,
			height : 340,
			title : "客户关怀",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#customerCareForm').validationEngine('validate')) {
						$.post('${vix}/crm/business/memberShipAction!saveOrUpdate.action', {
						'customerAccount.id' : $("#id").val(),
						'customerAccount.subject' : $("#subject").val(),
						'customerAccount.careContent' : $("#careContent").val(),
						'customerAccount.customerFeedback' : $("#customerFeedback").val(),
						'customerAccount.employee.id' : $("#employeeId").val(),
						'customerAccount.customerAccount.id' : $("#customerAccountId").val(),
						'customerAccount.contactPerson.id' : $("#contactPersonId").val(),
						'customerAccount.crmProject.id' : $("#crmProjectId").val(),
						'customerAccount.isDeleted' : $("#isDeleted").val(),
						'customerAccount.memo' : $("#memo").val()
						}, function(result) {
							asyncbox.success(result, "<s:text name='vix_message'/>", function() {
								pager("start", "${vix}/crm/business/memberShipAction!goListContent.action?name=" + name, 'customerAccount');
							});
						});
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
	function deleteByIds() {
		var ids = '';
		$("[name='chkId']").each(function() {
			if ($(this).attr('checked')) {
				ids += $(this).val() + ",";
			}
		});
		asyncbox.success(ids, "选中的id");
	}

	function deleteById(id) {
		$.ajax({
		url : '${vix}/crm/business/memberShipAction!deleteById.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/crm/business/memberShipAction!goListContent.action?name=" + name, 'customerAccount');
			});
		}
		});
	}

	function searchForContent() {
		loadName();
		pager("start", "${vix}/crm/business/memberShipAction!goListContent.action?name=" + name, 'customerAccount');
	}

	loadName();
	//载入分页列表数据
	pager("start", "${vix}/crm/business/memberShipAction!goListContent.action?name=" + name, 'customerAccount');
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#customerAccountOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/crm/business/memberShipAction!goListContent.action?orderField=" + orderField + "&orderBy=" + orderBy, 'customerAccount');
	}

	bindSearch();
	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/crm/business/memberShipAction!goListContent.action?name=" + name, 'customerAccount');
	}
	function goMemberShipView(id) {
		$.ajax({
		url : '${vix}/crm/business/memberShipAction!goMemberShipView.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');
	//高级搜索
	function goSearch() {
		$.ajax({
		url : '${vix}/crm/business/memberShipAction!goSearch.action',
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
					pager("start", "${vix}/crm/business/memberShipAction!goListContent.action?name=" + $('#name').val(), 'customerAccount');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客服管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/memberShipAction!goList.action');">客户列表</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<%-- <a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a> --%>
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
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <label> <input type="button" value="高级搜索"
				class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
		</div> --%>
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
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="customerAccountInfo"></b> <s:text name='cmn_to' /> <b class="customerAccountTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="customerAccount" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="customerAccountInfo"></b> <s:text name='cmn_to' /> <b class="customerAccountTotalCount"></b>)
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