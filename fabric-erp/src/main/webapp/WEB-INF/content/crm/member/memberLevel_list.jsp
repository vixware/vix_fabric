<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadSearchCondition() {
		name = $('#levelName').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function saveOrUpdate(id) {
		$.ajax({
		url : '${vix}/crm/member/memberLevelAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 650,
			height : 300,
			title : "会员等级",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#memberLevelForm').validationEngine('validate')) {
						$.post('${vix}/crm/member/memberLevelAction!saveOrUpdate.action', {
						'memberLevel.id' : $("#memberLevelId").val(),
						'memberLevel.code' : $("#code").val(),
						'memberLevel.name' : $("#name").val(),
						'memberLevel.fromAmount' : $("#fromAmount").val(),
						'memberLevel.toAmount' : $("#toAmount").val(),
						'memberLevel.fromPoints' : $("#fromPoints").val(),
						'memberLevel.toPoints' : $("#toPoints").val(),
						'memberLevel.levelType' : $(":radio[name=levelType][checked]").val(),
						'memberLevel.memo' : $("#memo").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/crm/member/memberLevelAction!goList.action');
						});
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
		url : '${vix}/crm/member/memberLevelAction!deleteById.action?id=' + id,
		cache : false,
		success : function(result) {
			showMessage(result);
			setTimeout("", 1000);
			loadContent('${vix}/crm/member/memberLevelAction!goList.action');
		}
		});
	}

	function searchForContent() {
		loadSearchCondition();
		pager("start", "${vix}/crm/member/memberLevelAction!goListContent.action?name=" + name, 'memberLevel');
	}

	loadSearchCondition();
	//载入分页列表数据
	pager("start", "${vix}/crm/member/memberLevelAction!goListContent.action?name=" + name, 'memberLevel');
	//排序 
	function orderBy(orderField) {
		loadSearchCondition();
		var orderBy = $("#memberLevelOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/crm/member/memberLevelAction!goListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'memberLevel');
	}
	function reset() {
		$('#levelName').val('');
		pager("start", "${vix}/crm/member/memberLevelAction!goListContent.action?1=1", 'memberLevel');
	}
	bindSearch();
	function currentPager(tag) {
		loadSearchCondition();
		pager(tag, "${vix}/crm/member/memberLevelAction!goListContent.action?name=" + name, 'memberLevel');
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/memberLevel.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">会员管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/member/memberLevelAction!goList.action');">会员等级</a></li>
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
				<p>会员等级管理</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>名称:<input type="text" class="int" id="levelName" placeholder="名称"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="memberLevelList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="memberLevelInfo"></b> <s:text name='cmn_to' /> <b class="memberLevelTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="memberLevel" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="memberLevelInfo"></b> <s:text name='cmn_to' /> <b class="memberLevelTotalCount"></b>)
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