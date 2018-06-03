<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 会员交互管理 <span onclick="">&gt; 会员细分管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntMembershipSubdivisionAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>会员细分管理</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="membershipSubdivisionForm">
				<fieldset>
					<input type="hidden" id="membershipSubdivisionId" name="membershipSubdivision.id" value="${membershipSubdivision.id}" /> <input type="hidden" id="channelDistributorId" name="membershipSubdivision.channelDistributor.id" value="${membershipSubdivision.channelDistributor.id }" /> <input type="hidden" id="memberTagId" name="memberTagId"
						value="${memberTagId}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="membershipSubdivision.name" value="${membershipSubdivision.name}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> <span class="text-danger">*</span> 门店:
						</label>
						<div class="col-md-3">
							<input id="channelDistributorName" name="membershipSubdivision.channelDistributor.name" value="${membershipSubdivision.channelDistributor.name}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startTime" name="membershipSubdivision.startTime" value="${membershipSubdivision.startTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="endTime" name="membershipSubdivision.endTime" value="${membershipSubdivision.endTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 标签:
						</label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-gray">
								<header>
									<h2>
										<strong></strong> <i>标签</i>
									</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<textarea class="form-control no-border" rows="4" id="logContent" name="membershipSubdivision.memberTagName"> ${membershipSubdivision.memberTagName }</textarea>
										<div class="widget-footer">
											<button class="btn btn-sm btn-info" type="button" onclick="goChooseMemberTag();">
												<i class="fa fa-copy"></i> 选择标签
											</button>
											<button class="btn btn-sm btn-default pull-left" type="button" onclick="$('#memberTagId').val('');$('#memberTagName').val('');">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="membershipSubdivision.memo" class="form-control" rows="4">${membershipSubdivision.memo }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">客户细分条件:</label>
						<div class="col-md-8">
							<div id="" class="jarviswidget">
								<header>
									<span class="widget-icon"><i class="fa fa-table"></i></span>
									<h2>客户细分条件</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="名称" class="form-control" id="itemName">
												</div>
												<button onclick="membershipSubdivisionDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#itemName').val('');membershipSubdivisionDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right">
													<button onclick="goSaveOrUpdateSaleOrderItem('','新增');" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i>
														<s:text name="新增" />
													</button>
												</div>
											</div>
										</div>
										<table id="membershipSubdivisionDetailTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntMembershipSubdivisionAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	var membershipSubdivisionDetailColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "条件名称",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "运算符名称",
	"name" : "operationalCharacterName",
	"data" : function(data) {
		return data.operationalCharacterName;
	}
	}, {
	"title" : "值",
	"name" : "categoryValue",
	"data" : function(data) {
		return data.categoryValue;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateSaleOrderItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];

	var membershipSubdivisionDetailTable = initDataTable("membershipSubdivisionDetailTableId", "${nvix}/nvixnt/vixntMembershipSubdivisionAction!goMembershipSubdivisionDetailList.action", membershipSubdivisionDetailColumns, function(page, pageSize, orderField, orderBy) {
		var membershipSubdivisionId = $("#membershipSubdivisionId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"membershipSubdivisionId" : membershipSubdivisionId
		};
	}, 10);

	function goChooseMemberTag() {
		chooseListData('${nvix}/nvixnt/vixntMembershipSubdivisionAction!goChooseMemberTag.action', 'multi', '选择标签', "memberTag", function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
			} else {
				layer.alert("请选择标签!");
			}
		}, 750, 450);
	};

	$("#membershipSubdivisionForm").validationEngine();
	function saveOrUpdate() {
		if ($("#membershipSubdivisionForm").validationEngine('validate')) {
			$("#membershipSubdivisionForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntMembershipSubdivisionAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntMembershipSubdivisionAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};

	function goSaveOrUpdateSaleOrderItem(id, title) {
		if (id != null && id != '' & id != 'undefined') {
		    openSaveOrUpdateWindow('${nvix}/nvixnt/vixntMembershipSubdivisionAction!goSaveOrUpdateMembershipSubdivision.action?id=' + id, "membershipSubdivisionDetailForm", title, 750, 350, membershipSubdivisionDetailTable);
		} else {
			if ($("#membershipSubdivisionForm").validationEngine('validate')) {
				$("#membershipSubdivisionForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntMembershipSubdivisionAction!saveOrUpdate.action",
				dataType : "text",
				success : function(membershipSubdivisionId) {
					$("#membershipSubdivisionId").val(membershipSubdivisionId);
					openSaveOrUpdateWindow('${nvix}/nvixnt/vixntMembershipSubdivisionAction!goSaveOrUpdateMembershipSubdivision.action?id=' + id+ "&membershipSubdivisionId=" + membershipSubdivisionId, "membershipSubdivisionDetailForm", title, 750, 350, membershipSubdivisionDetailTable);
				}
				});
			} else {
				return false;
			}
		}
	};
	pageSetUp();
</script>