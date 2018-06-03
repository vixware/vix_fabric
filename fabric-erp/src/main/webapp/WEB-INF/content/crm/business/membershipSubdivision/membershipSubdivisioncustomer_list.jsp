<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	/* 内容 */
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}

	//载入分页列表数据
	pager("start", "${vix}/crm/business/membershipSubdivisionAction!goCustomerListContent.action?id=" + $('#id').val(), 'customerList');
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#customerListOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/crm/business/membershipSubdivisionAction!goCustomerListContent.action?orderField=" + orderField + "&orderBy=" + orderBy, 'customerList');
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/crm/business/membershipSubdivisionAction!goCustomerListContent.action?name=" + name + "&id=" + $('#id').val(), 'customerList');
	}

	function resetForContent() {
		$("#nameS").val("");
	}
	function categoryPager(tag) {
		pager(tag, "${vix}/crm/business/membershipSubdivisionAction!goCustomerListContent.action?id=" + $('#id').val(), 'customerList');
	}
</script>
<input type="hidden" id="id" name="id" value="${id}" />

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">营销中心 </a></li>
				<li><a href="#">智能营销 </a></li>
				<li><a href="#">会员列表</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="loadContent('${vix}/crm/business/membershipSubdivisionAction!goList.action','bg_02');"><span>返回 </span> </a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b>主题:(${membershipSubdivisionName})</b> <b>客户数量:(${memberNum}人)</b> <b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>会员列表</p>
			</div>
		</div>
		<div>
			<label>姓名:<input type="text" class="int" id="nameS"></label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent()"> </label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(5,1,'a',event,'customerList')"><img src="img/holidaysam.png" alt="" />会员列表</a></li>
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
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a></span> <span><a class="previous" onclick="categoryPager('previous');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="customerListTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a></span> <span><a class="end" onclick="categoryPager('end');"></a></span>
					</div>
				</div>
				<div id="customerList" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a></span> <span><a class="previous" onclick="categoryPager('previous');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="customerListTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a></span> <span><a class="end" onclick="categoryPager('end');"></a></span>
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