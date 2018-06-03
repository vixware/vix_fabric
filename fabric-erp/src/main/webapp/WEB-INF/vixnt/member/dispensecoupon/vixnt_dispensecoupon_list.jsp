<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-globe"></i> <a>会员营销</a><span>> 优惠券管理 </span><span>> 优惠券发放 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;批量发放
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
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
							customerAccountTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>会员列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="selectName">
									</div>
									<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#selectName').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var customerAccountColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "来源网店",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "手机",
	"data" : function(data) {
		return data.mobilePhone;
	}
	}, {
	"title" : "联系地址",
	"data" : function(data) {
		return data.mobilePhone;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!goSingleList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"treeType" : treeType,
		"selectName" : selectName
		};
	}, 10, '0','1');
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAccountAction!deleteById.action?id=' + id, '是否删除?', customerAccountTable);
	};
	function goSaveOrUpdate(id) {
		if (id != null && id != '') {
			$.ajax({
			url : '${nvix}/nvixnt/vixntDispenseCouponAction!goSaveOrUpdate.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
		} else {
			/* if ($('#selectTreeType').val() != '' && $('#selectTreeType').val() != null && $('#selectTreeType').val() == 'CH') { */
				$.ajax({
				url : '${nvix}/nvixnt/vixntDispenseCouponAction!goSaveOrUpdate.action?id=' + id + '&parentId=' + $('#selectId').val(),
				cache : false,
				success : function(html) {
					$("#mainContent").html(html);
				}
				});
			/* } else {
				layer.alert("请选择门店!");
			} */
		}
	};
</script>