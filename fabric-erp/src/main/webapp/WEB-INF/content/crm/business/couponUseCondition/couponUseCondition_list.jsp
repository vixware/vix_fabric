<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}

	function searchForContent() {
		loadName();
		pager("start", "${vix}/crm/business/couponUseConditionAction!goListContent.action?name=" + name, 'coupon');
	}

	loadName();
	//载入分页列表数据
	pager("start", "${vix}/crm/business/couponUseConditionAction!goListContent.action?name=" + name, 'coupon');
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#couponOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/crm/business/couponUseConditionAction!goListContent.action?orderField=" + orderField + "&orderBy=" + orderBy, 'coupon');
	}

	bindSearch();
	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/crm/business/couponUseConditionAction!goListContent.action?name=" + name, 'coupon');
	}

	loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');
	//高级查询
	function goSearch() {
		$.ajax({
		url : '${vix}/crm/business/couponUseConditionAction!goSearch.action',
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
					pager("start", "${vix}/crm/business/couponUseConditionAction!goListContent.action?name=" + name, 'coupon');
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
				<li><a href="#"><img src="${vix}/common/img/money_26x26.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">会员管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/couponUseConditionAction!goList.action');">优惠券使用汇总</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
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
			<label>搜索内容:<input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" /></label> <label><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label><label> <input type="button" value="高级搜索"
				class="btn" onclick="goSearch();" /></label>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="couponInfo"></b> <s:text name='cmn_to' /> <b class="couponTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="coupon" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="couponInfo"></b> <s:text name='cmn_to' /> <b class="couponTotalCount"></b>)
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