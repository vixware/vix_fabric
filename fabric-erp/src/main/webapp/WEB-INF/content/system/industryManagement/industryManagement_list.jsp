<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = encodeURI(name);
	};
	function saveIndustry() {
		saveOrUpdateIndustry(0);
	};
	function updateIndustry() {
		var industryId = $("#selectId").val();
		if (industryId != "" && industryId > 0) {
			saveOrUpdateIndustry(industryId);
		}
	};

	function saveOrUpdateIndustry(id) {
		$.ajax({
		url : '${vix}/system/industryManagementAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 650,
			height : 295,
			title : "行业",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#industryManagementform').validationEngine('validate')) {
						$.post('${vix}/system/industryManagementAction!saveOrUpdate.action', {
						'industryManagement.id' : $("#industryManagementId").val(),
						//'industryManagement.vixCode':$("#vixCode").val(),
						'industryManagement.name' : $("#name").val(),
						'industryManagement.englishName' : $("#englishName").val(),
						'industryManagement.belongsIndustry' : $("#belongsIndustry").val(),
						'industryManagement.innerCode' : $("#innerCode").val(),
						'industryManagement.startTime' : $("#startTime").val(),
						'industryManagement.endTime' : $("#endTime").val(),
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/system/industryManagementAction!goList.action');
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

	$('input.btn[type="button"],input.btn[type="submit"]').hover(function() {
		$(this).addClass("btnhover");
	}, function() {
		$(this).removeClass("btnhover");
	});
	function deleteByIds() {
		var ids = '';
		$("[name='chkId']").each(function() {
			if ($(this).attr('checked')) {
				ids += $(this).val() + ",";
			}
		});
		asyncbox.success(ids, "选中的id");
	}

	function deleteById() {
		var id = $("#selectId").val();
		if (id != "") {
			asyncbox.confirm('确定要删除该行业吗?', '<s:text name='vix_message'/>', function(action) {
				if (action == 'ok') {
					$.ajax({
					url : '${vix}/system/industryManagementAction!deleteById.action?id=' + id,
					cache : false,
					success : function(result) {
						showMessage(result);
						setTimeout("", 1000);
						pager("start", "${vix}/system/industryManagementAction!goSingleList.action?industryMgtId=" + $("#selectId").val() + "&moduleName=" + name, 'industryCategory');
					}
					});
				}
			});
		}

	}

	function searchForContent() {
		loadName();
		pager("start", "${vix}/system/industryManagementAction!goSingleList.action?moduleName=" + name, 'industryCategory');
	}

	loadName();
	//载入分页列表数据
	pager("start", "${vix}/system/industryManagementAction!goSingleList.action?moduleName=" + name, 'industryCategory');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#industryCategoryOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/system/industryManagementAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&moduleName=" + name, 'industryCategory');
	}

	bindSearch();
	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/system/industryManagementAction!goSingleList.action?industryMgtId=" + $("#selectId").val() + "&moduleName=" + name, 'industryCategory');
	}

	/**
	 * 为选中的行业增加模块
	 */
	function addIndustryMgModule() {
		var selectIds = "";
		var selectNames = "";

		var industryMgtId = $("#selectId").val();

		if (industryMgtId == "") {
			asyncbox.alert("请选择左侧的行业！", "提示");
			return;
		}
		$.ajax({
		url : '${vix}/common/select/commonSelectModuleAction!goList.action',
		data : {
		chkStyle : "checkbox",
		industryMgtId : industryMgtId
		},
		cache : false,
		async : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 760,
			height : 520,
			title : "选择模块",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						//alert(returnValue);
						/* if(resObj.length == 0 ){
							return;
						} */
						var result = returnValue.split(",");
						for (var i = 0; i < result.length; i++) {
							if (result[i].length > 1) {
								var v = result[i].split(":");
								selectIds += "," + v[0];
								selectNames += "," + v[1];
							}
						}
						if (selectIds != "") {
							selectIds = selectIds.substring(1, selectIds.length);
							selectNames = selectNames.substring(1, selectNames.length);
						}
						if (industryMgtId != "" && selectIds != "") {
							asyncbox.confirm('确定要添加选择的模块吗?', '<s:text name='vix_message'/>', function(action) {
								if (action == 'ok') {
									$.ajax({
									url : '${vix}/system/industryManagementAction!saveForAddModule.action',
									data : {
									industryMgtId : industryMgtId,
									selectIds : selectIds
									},
									cache : false,
									success : function(result) {
										showMessage(result);
										setTimeout("", 1000);
										pager("start", "${vix}/system/industryManagementAction!goSingleList.action?industryMgtId=" + $("#selectId").val() + "&moduleName=" + name, 'industryCategory');
									}
									});
								}
							});
						}
					}

				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});

	}

	function deleteIndustryMgtModuleById(id) {
		asyncbox.confirm('确定要删除该行业模块吗?', '<s:text name='vix_message'/>', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/system/industryManagementAction!deleteIndustryMgtModuleById.action?id=' + id,
				cache : false,
				success : function(result) {
					showMessage(result);
					setTimeout("", 1000);
					pager("start", "${vix}/system/industryManagementAction!goSingleList.action?industryMgtId=" + $("#selectId").val() + "&moduleName=" + name, 'industryCategory');
					//pager("start","${vix}/system/industryManagementAction!goSingleList.action?industryMgtId="+$("#selectId").val()+"&moduleName="+name,'industryCategory');//industryCategory
				}
				});
			}
		});

	}

	/**
	 * 设定行业模块菜单
	 */
	function toSetAuthority(id) {
		$.ajax({
		url : '${vix}/system/industryManagementAction!goIndustryAuthority.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}

	/**
	 * 设定元数据
	 */
	function toSetMetaData(id) {
		$.ajax({
		url : '${vix}/system/industryManagementAction!goIndustryMetaData.action?industryMgtModuleId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}

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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" /> 系统管理 </a></li>
				<li><a href="#">平台运营管理 </a></li>
				<li><a href="#">行业管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveIndustry();"><span><s:text name='cmn_add' /></span></a> <a href="#" onclick="updateIndustry();"><span><s:text name='cmn_update' /></span></a> <a href="#" onclick="deleteById();"><span><s:text name='cmn_delete' /></span></a> <a href="#" onclick="addIndustryMgModule();"><span>增加模块</span></a>
		</p>
	</div>
</div>

<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />

<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong>
					<i class="close"><a href="#"></a></i>
					<i><a href="#"></a></i>
					<i><a href="#"></a></i>
					<b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_category"/></a></li>
			<li class="fly">
				<a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_category"/></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
				</ul>
			</li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" />
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<%--	
			<div class="left_content" style="height:500px;">
				<div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div> --%>

			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div> --%>
				<div id="industryMgTree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
			<!--
				var zTreeIndustyMg;
				var settingIndustyMg = {
				async : {
				enable : true,
				url : "${vix}/system/industryManagementAction!findTreeToJson.action",
				autoParam : [ "id" ]
				},
				callback : {
					onClick : onClickIndustyMg
				}
				};
				function onClickIndustyMg(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start", "${vix}/system/industryManagementAction!goSingleList.action?industryMgtId=" + treeNode.id + "&moduleName=" + name, 'industryCategory');
				}
				zTreeIndustyMg = $.fn.zTree.init($("#industryMgTree_root"), settingIndustyMg);
			//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="industryCategoryInfo"></b> <s:text name='cmn_to' /> <b class="industryCategoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="industryCategory" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="industryCategoryInfo"></b> <s:text name='cmn_to' /> <b class="industryCategoryTotalCount"></b>)
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