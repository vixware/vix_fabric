<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var buyerNick = "";
	function loadBuyerNick() {
		buyerNick = $('#buyerNick').val();
		buyerNick = Url.encode(buyerNick);
		buyerNick = Url.encode(buyerNick);
	}
	var receiverMobile = "";
	function loadReceiverMobile() {
		receiverMobile = $('#receiverMobile').val();
		receiverMobile = Url.encode(receiverMobile);
		receiverMobile = Url.encode(receiverMobile);
	}
	function saveOrUpdate(id) {
		$.ajax({
		url : '${vix}/crm/member/memberLevelManagementAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 500,
			height : 200,
			title : "会员等级",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#customerAccountForm').validationEngine('validate')) {
						$.post('${vix}/crm/member/memberLevelManagementAction!saveOrUpdate.action', {
						'customerAccount.id' : $("#customerAccountId").val(),
						'memberLevelId' : $("#memberLevelId").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/crm/member/memberLevelManagementAction!goList.action');
						});
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};

	function searchForContent(tag) {
		loadBuyerNick();
		loadReceiverMobile();
		pager("start", "${vix}/crm/member/memberLevelManagementAction!goSingleList.action?buyerNick=" + buyerNick + "&receiverMobile=" + $("#receiverMobile").val(), 'customerAccount');
	}
	function resetForContent() {
		$("#buyerNick").val("");
		$("#receiverMobile").val("");
	}

	//载入分页列表数据
	pager("start", "${vix}/crm/member/memberLevelManagementAction!goSingleList.action?1=1", 'customerAccount');
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#memberLevelOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/crm/member/memberLevelManagementAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&1=1", 'customerAccount');
	}

	function currentPager(tag) {
		pager(tag, "${vix}/crm/member/memberLevelManagementAction!goSingleList.action?1=1", 'customerAccount');
	}
	function selectLevel(id) {
		pager("start", "${vix}/crm/member/memberLevelManagementAction!goSingleList.action?memberLevelId=" + id, 'customerAccount');
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/memberLevel.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">会员管理</a></li>
				<li><a href="#">忠诚度管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/member/memberLevelManagementAction!goList.action');">会员等级管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<%-- <p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
		</p> --%>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<s:select headerKey="-1" headerValue="--请选择--" list="%{memberLevelList}" listValue="name" listKey="id" value="" multiple="false" theme="simple" style="height:20px;width: 100px;" onchange="selectLevel(this.value);">
			</s:select>
			<b><img alt="" src="img/icon_11.png"></b>
		</div>
		<div>
			<label>客户姓名:<input type="text" class="int" id="buyerNick"></label> <label>手机号码:<input type="text" class="int" id="receiverMobile"></label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button"
				value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent()"> </label>
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