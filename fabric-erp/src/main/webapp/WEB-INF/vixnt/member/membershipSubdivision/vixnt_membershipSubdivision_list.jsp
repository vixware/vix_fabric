<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-globe"></i> 会员营销 <span>> 会员细分管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增');">
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
					<h2>店铺</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntDistributionSystemAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							membershipSubdivisionTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>会员细分列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<input type="hidden" id="organizationUnitId" name="" value="" />
									<div class="form-group">
										主题: <input type="text" value="" class="form-control" id="searchCode">
									</div>
									<button onclick="membershipSubdivisionTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCode').val('');membershipSubdivisionTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="membershipSubdivisionTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var membershipSubdivisionColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "主题",
	"width" : "25%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"title" : "开始时间",
	"data" : function(data) {
		return data.startTimeTimeStr;
	}
	}, {
	"title" : "结束时间",
	"data" : function(data) {
		return data.endTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var customer = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goCustomerList('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteEntityById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + customer + "&nbsp;&nbsp;" + del;
	}
	} ];

	var membershipSubdivisionTable = initDataTable("membershipSubdivisionTableId", "${nvix}/nvixnt/vixntMembershipSubdivisionAction!goSingleList.action", membershipSubdivisionColumns, function(page, pageSize, orderField, orderBy) {
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
	//新增会员细分
	function goSaveOrUpdate(id) {
		if (id != '' && id != null) {
			saveOrUpdate('${nvix}/nvixnt/vixntMembershipSubdivisionAction!goSaveOrUpdate.action?id=' + id);
		} else {
			if ($("#selectId").val() != '' && $("#selectId").val() != null && $("#selectTreeType").val() == 'CH') {
				saveOrUpdate('${nvix}/nvixnt/vixntMembershipSubdivisionAction!goSaveOrUpdate.action?id=' + id + "&parentId=" + $("#selectId").val());
			} else {
				layer.alert("请选择门店!");
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

	function deleteEntityById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntMembershipSubdivisionAction!deleteById.action?id=' + id, "是否删除该会员细分!", membershipSubdivisionTable);
	};

	function goCustomerList(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntMembershipSubdivisionAction!goCustomerList.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	pageSetUp();
</script>