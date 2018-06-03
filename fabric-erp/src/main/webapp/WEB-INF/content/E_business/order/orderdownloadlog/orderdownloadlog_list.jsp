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
		pager("start", "${vix}/business/orderDownLoadLogAction!goSingleList.action?searchContent=" + name, 'latestOperateEntity');
	}
	function resetForContent() {
		$('#nameS').val('');
	}
	//载入分页列表数据
	pager("start", "${vix}/business/orderDownLoadLogAction!goSingleList.action?1=1", 'latestOperateEntity');
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#brandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/business/orderDownLoadLogAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&1=1", 'latestOperateEntity');
	}

	function currentPager(tag) {
		pager(tag, "${vix}/business/orderDownLoadLogAction!goSingleList.action?1=1", 'latestOperateEntity');
	}

	function goListDownLoadOrderLog() {
		pager("start", "${vix}/business/orderDownLoadLogAction!goSingleList.action?1=1", 'latestOperateEntity');
	}
	/* $(function() {
		setInterval("goListDownLoadOrderLog()", 3000);
	}); */
</script>
<style>
#c_head div input.int {
	border: 1px solid #aaa;
	padding: 2px;
	width: 300px;
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_jobtodo.png" alt="" />供应链</a></li>
				<li><a href="#">网店管理</a></li>
				<li><a href="#">日志管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>日志管理</p>
			</div>
		</div>
		<div>
			<label>搜索内容:<input type="text" class="int" id="nameS" placeholder="操作内容" size="50"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="latestOperateEntityInfo"></b> <s:text name='cmn_to' /> <b class="latestOperateEntityTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="latestOperateEntity" class="table"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="latestOperateEntityInfo"></b> <s:text name='cmn_to' /> <b class="latestOperateEntityTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
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