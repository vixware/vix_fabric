<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 设置 <span>> 会计科目 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<!-- <div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div> -->
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>待选科目</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/hr/nvixDepartmentAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectIdTreeId").val(treeNode.tId);
							$("#selectTreeType").val(treeNode.treeType);
							organizationUnitTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>科目列表</h2>
						<ul class="nav nav-tabs pull-right in" id="">
							<li class="active"><a data-toggle="tab" href="#hr1"><span class="hidden-mobile hidden-tablet">科目列表</span></a></li>
							<li><a data-toggle="tab" href="#hr2"><span class="hidden-mobile hidden-tablet">输入</span></a></li>
						</ul>
					</header>
						<div class="tab-content">
						<div class="tab-pane active" id="hr1">
							<div class="widget-body no-padding">
								<div id="">
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											科目名称: <input type="text" value="" class="form-control" id="overselectname">
										</div>
										<button  type="button" class="btn btn-info">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button  type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="10%">级次</th>
											<th width="15%">科目编码</th>
											<th width="25%">科目名称</th>
											<th width="20%">外币币种</th>
											<th width="15%">辅助核算</th>
											<th width="10%">银行科目</th>
											
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="tab-pane" id="hr2">
						  <form class="form-horizontal" id="employeeForm">
							 <fieldset>
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 会计编码:</label>
										<div class="col-md-3">
											<input id="name" name="employee.name" value="${employee.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 会计名称:</label>
										<div class="col-md-3">
											<input id="staffJobNumber" name="employee.staffJobNumber" value="${employee.staffJobNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
										</div>
									</div>
					                <div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 科目英文名称:</label>
										<div class="col-md-3">
											<input id="name" name="employee.name" value="${employee.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 科目类型:</label>
										<div class="col-md-3">
											<input id="staffJobNumber" name="employee.staffJobNumber" value="${employee.staffJobNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 格式:</label>
										<div class="col-md-3">
											<input id="name" name="employee.name" value="${employee.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 助记码:</label>
										<div class="col-md-3">
											<input id="staffJobNumber" name="employee.staffJobNumber" value="${employee.staffJobNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 科目性质:</label>
										<div class="col-md-3">
											<select id="gender" name="employee.gender" class="form-control validate[required]">
											    <option value="">请选择</option>
												<option value="1" <c:if test='${employee.gender == "1"}'>selected="selected"</c:if>>借方</option>
												<option value="0" <c:if test='${employee.gender == "0"}'>selected="selected"</c:if>>贷方</option>
											</select>
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 辅助核算:</label>
										<div class="col-md-3">
											<select id="gender" name="employee.gender" class="form-control validate[required]">
											    <option value="">请选择</option>
												<option value="1" <c:if test='${employee.gender == "1"}'>selected="selected"</c:if>>部门核算</option>
												<option value="0" <c:if test='${employee.gender == "0"}'>selected="selected"</c:if>>个人往来</option>
												<option value="0" <c:if test='${employee.gender == "0"}'>selected="selected"</c:if>>客户往来</option>
												<option value="0" <c:if test='${employee.gender == "0"}'>selected="selected"</c:if>>供应商往来</option>
												<option value="0" <c:if test='${employee.gender == "0"}'>selected="selected"</c:if>>项目核算</option>
											</select>
										</div>
									</div>
				                </fieldset>
				                <div class="form-actions">
									 <div class="row">
										<div class="col-md-12">
											<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
												<i class="fa fa-save"></i> 保存
											</button>
											<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNameBookAction!goEmployeeList.action');">
												<i class="fa fa-rotate-left"></i> 返回
											</button>
										</div>
									</div>
				               </div>
							</form> 
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
		pageSetUp();
		var salesOrderColumns = [ {
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"name" : "code",
		"data" : function(data) {
			return data.code;
		}
		}, {
		"name" : "name",
		"data" : function(data) {
			return data.name;
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			return data.channelDistributorName;
		}
		}, {
		"name" : "deliveryTime",
		"data" : function(data) {
			return data.deliveryTimeStr;
		}
		}, {
		"name" : "status",
		"data" : function(data) {
			if (data.status == 0) {
				return "<span class='label label-danger'>待审核</span>";
			} else if (data.status == 1) {
				return "<span class='label label-warning'>待发货</span>";
			} else if (data.status == 2) {
				return "<span class='label label-info'>配送中</span>";
			} else if (data.status == 3) {
				return "<span class='label label-primary'>已收货</span>";
			} else if (data.status == 4) {
				return "<span class='label label-success'>已入库</span>";
			}
			return "";
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			return update;
		
		}
		} ];
		var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntSalesOrderAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
			var selectname = $("#selectname").val();
			var parentId = $("#selectId").val();
			var treeType = $("#selectTreeType").val();
			selectname = Url.encode(selectname);
			return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"selectname" : selectname,
			"parentId" : parentId,
			"treeType" : treeType
			};
		});
</script>