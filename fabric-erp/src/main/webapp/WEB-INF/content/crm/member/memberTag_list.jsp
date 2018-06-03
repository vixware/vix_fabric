<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadSearchCondition() {
		name = $('#name1S').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function saveOrUpdate(id) {
		$.ajax({
		url : '${vix}/crm/member/memberTagAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 785,
			height : 485,
			title : "标签",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#memberTagForm').validationEngine('validate')) {
						$.post('${vix}/crm/member/memberTagAction!saveOrUpdate.action', {
						'memberTag.id' : $("#memberTagId").val(),
						'memberTag.name' : $("#name").val(),
						'memberTag.memo' : $("#memo").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/crm/member/memberTagAction!goList.action');
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
		url : '${vix}/crm/member/memberTagAction!deleteById.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/crm/member/memberTagAction!goListContent.action?name=" + name, 'memberTag');
			});
		}
		});
	}

	function searchForContent() {
		loadSearchCondition();
		pager("start", "${vix}/crm/member/memberTagAction!goListContent.action?name=" + name, 'memberTag');
	}
	function reset() {
		$('#name1S').val('');
	}
	loadSearchCondition();
	//载入分页列表数据
	pager("start", "${vix}/crm/member/memberTagAction!goListContent.action?name=" + name, 'memberTag');
	//排序 
	function orderBy(orderField) {
		loadSearchCondition();
		var orderBy = $("#memberTagOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/crm/member/memberTagAction!goListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'memberTag');
	}

	bindSearch();
	function currentPager(tag) {
		loadSearchCondition();
		pager(tag, "${vix}/crm/member/memberTagAction!goListContent.action?name=" + name, 'memberTag');
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
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/member/memberTagAction!goList.action');">会员标签</a></li>
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
				<p>标签管理</p>
			</div>
		</div>
		<div>
			<label>名称:<input type="text" class="int" id="name1S" placeholder="名称"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent('simple');" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="memberTagInfo"></b> <s:text name='cmn_to' /> <b class="memberTagTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="memberTag" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="memberTagInfo"></b> <s:text name='cmn_to' /> <b class="memberTagTotalCount"></b>)
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