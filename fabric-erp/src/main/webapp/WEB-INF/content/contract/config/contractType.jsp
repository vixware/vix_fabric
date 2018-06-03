<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		if (name != undefined) {
			name = Url.encode(name);
			name = Url.encode(name);
		}
	}
	$('#numBtn').click(function() {
		$('#numBtn').parent("li").toggleClass("current");
		$('#number').animate({
		height : 'toggle',
		opacity : 'toggle'
		}, 500, function() {
			$('#number').css('overflow', 'visible');
		});
		return false;
	});
	$('input.btn[type="button"],input.btn[type="submit"]').hover(function() {
		$(this).addClass("btnhover");
	}, function() {
		$(this).removeClass("btnhover");
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
	});
	$("#baseCoderForm").validationEngine();
	/** 保存数据 */
	function saveOrUpdate() {
		if ($('#baseCoderForm').validationEngine('validate')) {
			$
					.post('${vix}/contract/contractTypeAction!saveOrUpdate.action', {
					'encodingRulesTableInTheMiddle.id' : $("#id").val(),
					'encodingRulesTableInTheMiddle.codeType' : $(":radio[name=codetype][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.isOpenTime' : $(":checkbox[name=isOpenTime][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.billType' : $("#billType")
							.val(),
					'encodingRulesTableInTheMiddle.level1type' : $("#level1type")
							.val(),
					'encodingRulesTableInTheMiddle.level2type' : $("#level2type")
							.val(),
					'encodingRulesTableInTheMiddle.level3type' : $("#level3type")
							.val(),
					'encodingRulesTableInTheMiddle.level4type' : $("#level4type")
							.val(),
					'encodingRulesTableInTheMiddle.level5type' : $("#level5type")
							.val(),
					'encodingRulesTableInTheMiddle.level6type' : $("#level6type")
							.val(),
					'encodingRulesTableInTheMiddle.level7type' : $("#level7type")
							.val(),
					'encodingRulesTableInTheMiddle.level8type' : $("#level8type")
							.val(),
					'encodingRulesTableInTheMiddle.level9type' : $("#level9type")
							.val(),
					'encodingRulesTableInTheMiddle.level10type' : $("#level10type")
							.val(),
					'encodingRulesTableInTheMiddle.level11type' : $("#level11type")
							.val(),
					'encodingRulesTableInTheMiddle.level12type' : $("#level12type")
							.val(),
					'encodingRulesTableInTheMiddle.level1value' : $("#level1value")
							.val(),
					'encodingRulesTableInTheMiddle.level2value' : $("#level2value")
							.val(),
					'encodingRulesTableInTheMiddle.level3value' : $("#level3value")
							.val(),
					'encodingRulesTableInTheMiddle.level4value' : $("#level4value")
							.val(),
					'encodingRulesTableInTheMiddle.level5value' : $("#level5value")
							.val(),
					'encodingRulesTableInTheMiddle.level6value' : $("#level6value")
							.val(),
					'encodingRulesTableInTheMiddle.level7value' : $("#level7value")
							.val(),
					'encodingRulesTableInTheMiddle.level8value' : $("#level8value")
							.val(),
					'encodingRulesTableInTheMiddle.level9value' : $("#level9value")
							.val(),
					'encodingRulesTableInTheMiddle.level10value' : $("#level10value")
							.val(),
					'encodingRulesTableInTheMiddle.level11value' : $("#level11value")
							.val(),
					'encodingRulesTableInTheMiddle.level12value' : $("#level12value")
							.val(),
					'encodingRulesTableInTheMiddle.timeType' : $("#timeType")
							.val(),
					'encodingRulesTableInTheMiddle.timeFormat' : $("#timeFormat")
							.val(),
					'encodingRulesTableInTheMiddle.serialNumberBegin' : $("#serialNumberBegin")
							.val(),
					'encodingRulesTableInTheMiddle.serialNumberEnd' : $("#serialNumberEnd")
							.val(),
					'encodingRulesTableInTheMiddle.serialNumberStep' : $("#serialNumberStep")
							.val(),
					'encodingRulesTableInTheMiddle.codeLength' : $("#codeLength")
							.val(),
					'encodingRulesTableInTheMiddle.enableSeries' : $("#enableSeries")
							.val(),
					'encodingRulesTableInTheMiddle.level1Length' : $("#level1Length")
							.val(),
					'encodingRulesTableInTheMiddle.level2Length' : $("#level2Length")
							.val(),
					'encodingRulesTableInTheMiddle.level3Length' : $("#level3Length")
							.val(),
					'encodingRulesTableInTheMiddle.level4Length' : $("#level4Length")
							.val(),
					'encodingRulesTableInTheMiddle.level5Length' : $("#level5Length")
							.val(),
					'encodingRulesTableInTheMiddle.level6Length' : $("#level6Length")
							.val(),
					'encodingRulesTableInTheMiddle.level7Length' : $("#level7Length ")
							.val(),
					'encodingRulesTableInTheMiddle.level8Length' : $("#level8Length ")
							.val(),
					'encodingRulesTableInTheMiddle.level9Length' : $("#level9Length ")
							.val(),
					'encodingRulesTableInTheMiddle.level10Length' : $("#level10Length ")
							.val(),
					'encodingRulesTableInTheMiddle.level11Length' : $("#level11Length ")
							.val(),
					'encodingRulesTableInTheMiddle.level12Length' : $("#level12Length ")
							.val(),
					'encodingRulesTableInTheMiddle.islevel1' : $(":checkbox[name=islevel1][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel2' : $(":checkbox[name=islevel2][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel3' : $(":checkbox[name=islevel3][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel4' : $(":checkbox[name=islevel4][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel5' : $(":checkbox[name=islevel5][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel6' : $(":checkbox[name=islevel6][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel7' : $(":checkbox[name=islevel7][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel8' : $(":checkbox[name=islevel8][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel9' : $(":checkbox[name=islevel9][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel10' : $(":checkbox[name=islevel10][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel11' : $(":checkbox[name=islevel11][checked]")
							.val(),
					'encodingRulesTableInTheMiddle.islevel12' : $(":checkbox[name=islevel12][checked]")
							.val()
					}, function(result) {
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/contract/contractTypeAction!goCodeList.action');
					});
		}
	};
	/** 删除数据 */
	function deleteById(id) {
		$
				.ajax({
				url : '${vix}/contract/contractTypeAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					asyncbox
							.success(html, "<s:text name='message'/>", function(action) {
								var treeNode = zTree
										.getNodeByTId($("#selectIdTreeId")
												.val());
								if (null != treeNode) {
									treeNode.isParent = true;
								}
								zTree.reAsyncChildNodes(treeNode, "refresh");
								pager("start", "${vix}/contract/contractTypeAction!goSingleList.action?name=" + name, 'baseCoder');
							});
				}
				});
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/contract/contractTypeAction!goSingleList.action?name=" + name, 'baseCoder');
	function loadRoot() {
		$('#name').val("");
		pager("start", "${vix}/contract/contractTypeAction!goSingleList.action?name=" + name, 'baseCoder');
	}
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#categoryOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/contract/contractTypeAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name + "&parentId=" + $("#selectId")
				.val(), 'baseCoder');
	}
	bindSearch();
	bindSwitch();
	$(".drop>ul>li").bind('mouseover', function() {
		$(this).children('ul').delay(400).slideDown('fast');
	}).bind('mouseleave', function() {
		$(this).children('ul').slideDown('fast').stop(true, true);
		$(this).children('ul').slideUp('fast');
	});
	//面包屑
	if ($('.sub_menu').length) {
		$("#breadCrumb").jBreadCrumb();
	}
	function changeDisplay() {
		var divText = $("#lb_search_advanced").text();
		if (divText == "高级搜索") {
			$("#nameS").attr({
				disabled : 'true'
			});
		} else {
			$("#nameS").removeAttr("disabled");
		}
	}
	function resetForContent(tag) {
		if (tag == 0) {
			$("#nameS").val("");
		} else {
			$("#outcodes").val("");
			$("#departmentcodes").val("");
		}
	}
	/* 载入内容区 */
	$(document).ready(function() {
		$.ajax({
		url : '${vix}/contract/contractTypeAction!goSaveOrUpdate.action',
		cache : false,
		success : function(html) {
			$("#right").html(html);
		}
		});
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" /> <s:text name="system_control" /> </a></li>
				<li><a href="#"><s:text name="system_basic_settings" /> </a></li>
				<li><a href="#"><s:text name="system_code_management" /> </a></li>
				<li><a href="#">编码规则管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="baseCoderForm">
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
					url : "${vix}/system/billTypeManagementAction!findOrgAndUnitTreeToJson.action",
					autoParam : [ "id", "treeType" ]
					},
					callback : {
						onClick : onClick
					}
					};
					function onClick(event, treeId, treeNode, clickFlag) {
						$
								.ajax({
								url : '${vix}/contract/contractTypeAction!goSaveOrUpdate.action?billTypeCode=' + treeNode.code + "&billTypeId=" + treeNode.id,
								cache : false,
								success : function(html) {
									$("#right").html(html);
									checkedradio();
								}
								});
					}
					zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
				<div class="ui-resizable-handle ui-resizable-e"></div>
			</div>
			<div id="right"></div>
			<!-- right -->
		</div>
		<div class="c_foot">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
	</form>
	<!-- c_foot -->
</div>