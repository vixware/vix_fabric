<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $("#orgName").val();
		name = Url.encode(name);
		name = encodeURI(name);
	};
	
	$('#numBtn').click(function() {
		$('#numBtn').parent("li").toggleClass("current");
		$('#number').stop().animate({
		height : 'toggle',
		opacity : 'toggle'
		}, 500, function() {
			$('#number').css('overflow', 'visible');
		});
		return false;
	});
	
	$(function() {
		if ($('#numBox').length)
			$('#numBox').listmenu({
			menuWidth : '100%',
			cols : {
			count : 6,
			gutter : 0
			}
			});
		$("#left").resizable({
		maxHeight : 650,
		minHeight : 650,
		maxWidth : 400,
		minWidth : 120,
		handles : 'e'
		});
		$("#organizationForm").validationEngine();
	});
	
	$('input.btn[type="button"],input.btn[type="submit"]').hover(function() {
		$(this).addClass("btnhover");
	}, function() {
		$(this).removeClass("btnhover");
	});

	function saveOrUpdate(id, parentId) {
		$.ajax({
		url : '${vix}/system/companyOperationAction!goSaveOrUpdate.action?id=' + id + "&parentId=" + parentId,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1024,
			height : 650,
			title : "<s:text name='cmn_org_org'/>",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($("#organizationForm").validationEngine('validate')) {
						var tipMsg = "确定要保存公司信息么?";
						if ($("#companyOrgTypeId").val() == "jtgs" && $("#companyOrgDataFilterType").val() != "A") {
							tipMsg = "当前所建为集团公司，建议'数据查看'使用'集团所有数据',确定要保存公司信息么?";
						}
						if (confirm(tipMsg)) {
							var queryString = $('#organizationForm').formSerialize();
							$.post('${vix}/system/companyOperationAction!saveOrUpdate.action', queryString, function(result) {
								showMessage(result);
								setTimeout("", 1000);
								var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
								if (null != treeNode) {
									treeNode.isParent = true;
								}
								zTree.reAsyncChildNodes(treeNode, "refresh");
								pager("start", "${vix}/system/companyOperationAction!goSingleList.action?orgName=" + name + "&id=" + $("#selectId").val(), 'category');
							});
							return true;
						}
					}
					return false;
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};

	function deleteById(id) {
		$.ajax({
		url : '${vix}/system/companyOperationAction!deleteById.action?id=' + id,
		cache : false,
		success : function(result) {
			showMessage(result);
			setTimeout("", 1000);
			var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
			pager("start", "${vix}/system/companyOperationAction!goSingleList.action?orgName=" + name, 'category');
		}
		});
	};

	function searchForContent() {
		loadName();
		pager("start", "${vix}/system/companyOperationAction!goSingleList.action?orgName=" + name, 'category');
	};

	loadName();
	//载入分页列表数据
	pager("start", "${vix}/system/companyOperationAction!goSingleList.action?orgName=" + name, 'category');
	function loadRoot() {
		$('#name').val("");
		$('#selectId').val("");
		pager("start", "${vix}/system/companyOperationAction!goSingleList.action?orgName=", 'category');
	};
	
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#categoryOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/system/companyOperationAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&orgName=" + name + "&id=" + $("#selectId").val(), 'category');
	};

	function categoryPager(tag, entity) {
		loadName();
		if (entity == 'category') {
			pager(tag, "${vix}/system/companyOperationAction!goSingleList.action?orgName=" + name + '&id=' + $('#selectId').val(), entity);
		}
	};

	bindSearch();
	bindSwitch();
	function categoryTab(num, befor, id, e, entity) {
		var el = e.target ? e.target.parentNode : e.srcElement.parentNode;
		var pa = el.parentNode.getElementsByTagName("li");
		for (var i = 0; i < pa.length; i++) {
			pa[i].className = "";
		}
		el.className = "current";
		for (i = 1; i <= num; i++) {
			try {
				if (i == befor) {
					document.getElementById(id + i).style.display = "block";
				} else {
					document.getElementById(id + i).style.display = "none";
				}
			} catch (e) {
			}
		}
		if (entity != undefined) {
			categoryPager('start', entity);
		}
	};
	
	$(".drop>ul>li").bind('mouseover', function() {
		$(this).children('ul').delay(400).slideDown('fast');
	}).bind('mouseleave', function() {
		$(this).children('ul').slideDown('fast').stop(true, true);
		$(this).children('ul').slideUp('fast');
	});
	
	//面包屑
	if ($('.sub_menu').length) {
		$("#breadCrumb").jBreadCrumb();
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/system/cmn_company.png" alt="" /> <s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_base" /></a></li>
				<li><a href="#"><s:text name="system_control_base_comp" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,'');"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="orgName" value=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> </label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
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
				url : "${vix}/system/companyOperationAction!findTreeToJson.action",
				autoParam : [ "id" ]
				},
				callback : {
					onClick : onClick
				}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start", "${vix}/system/companyOperationAction!goSingleList.action?id=" + treeNode.id, "category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/mail.png" alt="" />公司组织机构</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>