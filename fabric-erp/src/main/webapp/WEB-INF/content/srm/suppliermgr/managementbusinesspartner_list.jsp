<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdate(id, parentId) {
		var title = '新增供应商';
		var url = '${vix}/srm/${usedAction}!goSaveOrUpdate.action';
		var data = {};
		if (id > 0) {
			title = '编辑供应商信息';
			data.id = id;
		}
		if (parentId > 0) {
			data.parentId = parentId;
		}

		//var newPageId = _tabShow(title,url,'_p_saveOrUpdate',null,null,data);

		_pad_page_view_push(url, data);
	}

	$(function() {
		//载入tab数据
		_load_tab_page_content();
	});

	function goSearch() {
		$.ajax({
		url : '${vix}/dtbcenter/vehiclePlanAction!goSearch.action',
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
					loadcode();
					loadselectname();
					pager("start", "${vix}/dtbcenter/vehiclePlanAction!goSingleList.action?code=" + code + "&name=" + selectname, 'salesOrder');
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
		<i> <a href="#" class="nav_print_btn"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#" class="nav_help_btn"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<ul>
			<li><a href="#" id="numBtn" class="num_btn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="code" class="int more" placeholder="<s:text name='srm_supplier_code'/>"></label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number" class="quick_index">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
		</ul>
	</div>
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
				url : "${vix}/srm/${usedAction}!findTreeToJson.action",
				autoParam : [ "id", "name=n", "level=lv" ]
				},
				callback : {
					onClick : onClick
				}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					$('#selectName').val(treeNode.name);

					_tabLocateUrl("${vix}/srm/${usedAction}!goSingleList.action?parentId=" + treeNode.id, 'tab_home');
				}

				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="-1" /> <input type="hidden" id="selectName" name="selectName" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/srm/${usedAction}!goSingleList.action"> <img src="img/mail.png" alt="" /> 供应商清单
					</a></li>
				</ul>
				<div id="tab_content_page" class="right_content">
					<div id="tab_home" class="table"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>