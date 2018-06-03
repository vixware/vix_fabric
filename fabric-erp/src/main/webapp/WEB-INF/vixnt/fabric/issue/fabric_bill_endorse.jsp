<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 区块链管理 <span onclick="">&gt; 票据管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="endorse();">
					<i class="fa fa-save"></i> 发起背书
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntFabricBillAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>票据</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="fabricBillForm">
				<fieldset>
					<input type="hidden" id="fabricBillId" name="fabricBill.id" value="${fabricBill.id}" />
					<legend>票据信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 票据类型:</label>
						<div class="col-md-3">
							<input id="billInfoType" name="fabricBill.billInfoType" value="${fabricBill.billInfoType}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 票据号码:</label>
						<div class="col-md-3">
							<input id="billInfoId" name="fabricBill.billInfoId" value="${fabricBill.billInfoId}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 票据金额:</label>
						<div class="col-md-3">
							<input id="billInfoAmt" name="fabricBill.billInfoAmt" value="${fabricBill.billInfoAmt}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>票据出票日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="billInfoIsseDate" name="fabricBill.billInfoIsseDate" value="${fabricBill.billInfoIsseDate}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyyMMdd',el:'billInfoIsseDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>票据到期日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="billInfoDueDate" name="fabricBill.billInfoDueDate" value="${fabricBill.billInfoDueDate}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyyMMdd',el:'billInfoDueDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 出票人名称:</label>
						<div class="col-md-3">
							<input id="drwrAcct" name="fabricBill.drwrAcct" value="${fabricBill.drwrAcct}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 出票人证件号码:</label>
						<div class="col-md-3">
							<input id="drwrCmId" name="fabricBill.drwrCmId" value="${fabricBill.drwrCmId}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 承兑人名称:</label>
						<div class="col-md-3">
							<input id="accptrAcct" name="fabricBill.accptrAcct" value="${fabricBill.accptrAcct}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 承兑人证件号码:</label>
						<div class="col-md-3">
							<input id="accptrCmId" name="fabricBill.accptrCmId" value="${fabricBill.accptrCmId}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 收款人名称:</label>
						<div class="col-md-3">
							<input id="pyeeAcct" name="fabricBill.pyeeAcct" value="${fabricBill.pyeeAcct}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 收款人证件号码:</label>
						<div class="col-md-3">
							<input id="pyeeCmId" name="fabricBill.pyeeCmId" value="${fabricBill.pyeeCmId}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 持票人名称:</label>
						<div class="col-md-3">
							<input id="hodrAcct" name="fabricBill.hodrAcct" value="${fabricBill.hodrAcct}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 持票人证件号码:</label>
						<div class="col-md-3">
							<input id="hodrCmId" name="fabricBill.hodrCmId" value="${fabricBill.hodrCmId}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<legend>若发起背书请填写背书人信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 背书人名称:</label>
						<div class="col-md-3">
							<input id="waitEndorserAcct" name="fabricBill.waitEndorserAcct" value="${fabricBill.waitEndorserAcct}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 背书人证件号码:</label>
						<div class="col-md-3">
							<input id="waitEndorserCmId" name="fabricBill.waitEndorserCmId" value="${fabricBill.waitEndorserCmId}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>历史流转记录</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="fabricHistoryItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="endorse();">
								<i class="fa fa-save"></i> 发起背书
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntFabricBillAction!goList.action');">
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
	$("#fabricBillForm").validationEngine();
	function saveOrUpdate() {
		if ($("#fabricBillForm").validationEngine('validate')) {
			$("#fabricBillForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntFabricBillAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntFabricBillAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function endorse() {
		if ($("#fabricBillForm").validationEngine('validate')) {
			$("#fabricBillForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntFabricBillAction!endorse.action?id=" + $('#fabricBillId').val(),
			dataType : "text",
			success : function(data) {
				showMessage(data, 'success');
				loadContent('', '${nvix}/nvixnt/vixntFabricBillAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};

	var fabricHistoryItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "txId",
	"data" : function(data) {
		return data.txId;
	}
	}, {
	"title" : "操作业务",
	"data" : function(data) {
		return data.fabricBill.state;
	}
	}, {
	"title" : "操作描述",
	"data" : function(data) {
		return data.fabricBill.state;
	}
	}, {
	"title" : "当前持票人",
	"data" : function(data) {
		return data.fabricBill.hodrAcct;
	}
	} ];
	var fabricHistoryItemTable = initDataTable("fabricHistoryItemTableId", "${nvix}/nvixnt/vixntFabricBillAction!goFabricHistoryItemList.action", fabricHistoryItemColumns, function(page, pageSize, orderField, orderBy) {
		var fabricBillId = $("#fabricBillId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"fabricBillId" : fabricBillId
		};
	});
</script>