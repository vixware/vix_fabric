<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-user"></i> 系统管理 <span>> 员工管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增员工');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>员工管理</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/wxpEmployeeAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							wxpEmployeeTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>员工列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<input type="hidden" id="organizationUnitId" name="" value="" />
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="searchCode">
									</div>
									<button onclick="wxpEmployeeTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCode').val('');wxpEmployeeTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="wxpEmployeeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var wxpEmployeeColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "员工职号",
	"width" : "25%",
	"data" : function(data) {
		return data.staffJobNumber;
	}
	}, {
	"title" : "姓名",
	"width" : "25%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "电话",
	"width" : "25%",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改成员');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var chooseOrganizationUnit = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goChooseOrganizationUnit('" + data.id + "');\" title='调换部门'><span class='txt-color-blue pull-right'><i class='fa fa-exchange'></i></span></a>";
		var chooseorgposition = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goChooseOrgPosition('" + data.id + "');\" title='分配岗位'><span class='txt-color-blue pull-right'><i class='fa  fa-gears'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteEntityById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + chooseOrganizationUnit + "&nbsp;&nbsp;" + chooseorgposition + "&nbsp;&nbsp;" + del;
	}
	} ];

	var wxpEmployeeTable = initDataTable("wxpEmployeeTableId", "${nvix}/nvixnt/wxpEmployeeAction!goSingleList.action", wxpEmployeeColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var searchCode = $("#searchCode").val();
		searchCode = Url.encode(searchCode);
		var treeType = $("#selectTreeType").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"treeType" : treeType,
		"searchCode" : searchCode
		};
	});

	/* function goSaveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/wxpEmployeeAction!goSaveOrUpdate.action?parentId=' + $('#selectId').val() + "&treeType=" + $('#selectTreeType').val() + "&id=" + id, "wxpEmployeeForm", title, 500, 550, wxpEmployeeTable, null, function() {
			var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	}; */
	function goSaveOrUpdate(id) {
		if (id != '') {
			saveOrUpdate('${nvix}/nvixnt/wxpEmployeeAction!goSaveOrUpdate.action?id=' + id);
		} else {
			if ($('#selectId').val() != '' && $('#selectTreeType').val() == 'O') {
				saveOrUpdate('${nvix}/nvixnt/wxpEmployeeAction!goSaveOrUpdate.action?parentId=' + $('#selectId').val() + "&treeType=" + $('#selectTreeType').val() + "&id=" + id);
			} else {
				layer.alert("请选择部门!");
			}
		}
	};
	function saveOrUpdate(url) {
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goChooseOrgPosition(id) {
		var url = "${nvix}/nvixnt/wxpEmployeeAction!goChooseOrgPosition.action?id=" + id;
		var mycars = new Array("确定", "取消")
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : "选择岗位",
			area : [ 450 + 'px', 350 + 'px' ],
			closeBtn : 1,
			content : html,
			btn : mycars,
			yes : function(index, layero) {
				$.post('${nvix}/nvixnt/wxpEmployeeAction!saveOrUpdateOrgPosition.action', {
				'orgPositionId' : $("#orgPositionId").val(),
				'employeeId' : $("#employeeId").val(),
				}, function(html) {
					layer.close(index);
					showMessage(html, 'success');
				});
			},
			btn2 : function(index, layero) {
				layer.close(index);
			}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统错误，请联系管理员");
		}
		});
	};

	function goChooseOrganizationUnit(id) {
		chooseListData('${nvix}/nvixnt/wxpEmployeeAction!goChooseOrganizationUnit.action', 'single', '选择部门', 'organizationUnit', function() {
			$.ajax({
			url : '${nvix}/nvixnt/wxpEmployeeAction!changeOrganizationUnit.action?id=' + id + "&organizationUnitId=" + $('#organizationUnitId').val(),
			cache : false,
			success : function() {
				wxpEmployeeTable.ajax.reload();
				var treeNode = zTree.getNodeByTId($("#selectId").val());
				if (null != treeNode) {
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
			}
			});
		});
	};
	function deleteEntityById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/wxpEmployeeAction!deleteEntityById.action?id=' + id, "是否删除该员工!", wxpEmployeeTable, "删除员工");
	};
	pageSetUp();
</script>