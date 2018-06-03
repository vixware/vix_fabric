<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName() {
	name = $('#nameS').val();
	name = Url.encode(name);
	name = Url.encode(name);
}
$("#wxpQyDepartmentForm").validationEngine();
function saveOrUpdate(id) {
	$.ajax({
	url : '${vix}/wechat/wechatDepartmentAction!goSaveOrUpdate.action?id=' + id,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 850,
		height : 355,
		title : "企业号",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				if ($('#wxpQyDepartmentForm').validationEngine('validate')) {
					$.post('${vix}/wechat/wechatDepartmentAction!saveOrUpdate.action', {
					'wxpQyDepartment.id' : $("#wxpQyDepartmentId").val(),
					'wxpQyDepartment.parentId' : $("#parentId").val(),
					'wxpQyDepartment.name' : $("#wxpQyDepartmentName").val(),
					'wxpQyDepartment.sortOrder' : $("#sortOrder").val()
					}, function(result) {
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/wechat/wechatDepartmentAction!goList.action');
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

function searchForContent(i) {
	loadName();
	pager("start", "${vix}/wechat/wechatDepartmentAction!goSingleList.action?name=" + name, 'wxpQyDepartment');
}
function resetForContent() {
	$('#nameS').val('');
}

loadName();
// 载入分页列表数据
pager("start", "${vix}/wechat/wechatDepartmentAction!goSingleList.action?name=" + name, 'wxpQyDepartment');
// 排序
function orderBy(orderField) {
	loadName();
	var orderBy = $("#wxpWeixinSiteOrderBy").val();
	if (orderBy == 'desc') {
		orderBy = "asc";
	} else {
		orderBy = 'desc';
	}
	pager("start", "${vix}/wechat/wechatDepartmentAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'wxpQyDepartment');
}

function categoryPager(tag) {
	loadName();
	pager(tag, "${vix}/wechat/wechatDepartmentAction!goSingleList.action?name=" + name, 'wxpQyDepartment');
}
//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/wechat/wechatDepartmentAction!goSearch.action',
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
				pager("start", "${vix}/wechat/wechatDepartmentAction!goSingleList.action?selectCode=" + $('#selectCode').val() + "&selectName=" + $('#selectName').val(), 'wxpQyDepartment');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
//单条删除
function deleteById(id) {
	$.ajax({
	url : '${vix}/wechat/wechatDepartmentAction!deleteById.action?id=' + id,
	cache : false,
	success : function(html) {
		asyncbox.success(html, "提示信息", function(action) {
			pager("start", "${vix}/wechat/wechatDepartmentAction!goSingleList.action?1=1", 'wxpQyDepartment');
		});
	}
});
}

//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	deleteByIds(ids);
}
function deleteByIds(ids) {
		$.ajax({
		url : '${vix}/wechat/wechatDepartmentAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/wechat/wechatDepartmentAction!goSingleList.action?name=" + name, 'wxpQyDepartment');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> 系统管理 </a></li>
				<li><a href="#">微信企业号管理 </a></li>
				<li><a href="#">部门管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /> </span> </a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>AddpopupDetails</b>
				</strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label><label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="wxpWeixinSiteList" var="c">
				<li><a href="#" onclick="goSaveOrUpdateCargoSpace(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_email' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a> </span> <span><a class="previous" onclick="categoryPager('previous');"></a> </span> <em>(<b class="wxpWeixinSiteInfo"></b> <s:text name='cmn_to' /> <b class="wxpWeixinSiteTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a> </span> <span><a class="end" onclick="categoryPager('end');"></a> </span>
					</div>
				</div>
				<div id="wxpQyDepartment" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_email' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a> </span> <span><a class="previous" onclick="categoryPager('previous');"></a> </span> <em>(<b class="wxpWeixinSiteInfo"></b> <s:text name='cmn_to' /> <b class="wxpWeixinSiteTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a> </span> <span><a class="end" onclick="categoryPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>