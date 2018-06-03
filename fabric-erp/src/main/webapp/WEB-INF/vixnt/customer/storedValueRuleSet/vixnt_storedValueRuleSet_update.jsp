<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="storedValueRuleSetForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/storedValueRuleSetAction!saveOrUpdate.action">
	<input type="hidden" id="storedValueRuleSetId" name="storedValueRuleSet.id" value="${storedValueRuleSet.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 规则名称:</label>
			<div class="col-md-3">
				<input id="name" name="storedValueRuleSet.name" value="${storedValueRuleSet.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"> 是否需要授权: </label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="storedValueRuleSet.ifAuthorize == 0">active</s:if>"> <input type="radio" value="0" id="ifAuthorize" name="storedValueRuleSet.ifAuthorize" <s:if test="storedValueRuleSet.ifAuthorize == 0">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="storedValueRuleSet.ifAuthorize == 1">active</s:if>"> <input type="radio" value="1" id="ifAuthorize" name="storedValueRuleSet.ifAuthorize" <s:if test="storedValueRuleSet.ifAuthorize == 1">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 充值金额:</label>
			<div class="col-md-3">
				<input id="amount" name="storedValueRuleSet.amount" value="${storedValueRuleSet.amount}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 卡类型:</label>
			<div class="col-md-3">
				<select id="type" name="storedValueRuleSet.type" class="form-control validate[required]" onchange="check();">
					<option id="type1" value="1" <c:if test='${storedValueRuleSet.type eq "1"}'>selected="selected"</c:if>>余额卡</option>
					<option id="type2" value="2" <c:if test='${storedValueRuleSet.type eq "2"}'>selected="selected"</c:if>>次卡</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 赠送积分:</label>
			<div class="col-md-3">
				<input id="giftPoints" name="storedValueRuleSet.giftPoints" value="${storedValueRuleSet.giftPoints}" class="form-control" type="text" />
			</div>
			<div id="gift">
				<label class="col-md-2 control-label"> 赠送金额:</label>
				<div class="col-md-3">
					<input id="giftAmount" name="storedValueRuleSet.giftAmount" value="${storedValueRuleSet.giftAmount}" class="form-control" type="text" />
				</div>
			</div>
			<%-- <label class="col-md-2 control-label"> 赠送次数:</label>
			<div class="col-md-3">
				<input id="giftNumber" name="storedValueRuleSet.giftNumber" value="${storedValueRuleSet.giftNumber}" class="form-control" type="text" />
			</div> --%>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="storedValueRuleSet.memo" class="form-control">${storedValueRuleSet.memo } </textarea>
			</div>
		</div>
		<div class="form-group" id="itemDiv">
			<label class="col-md-2 control-label"></label>
			<div class="col-md-8">
				<div id="contactPersonDiv" class="jarviswidget jarviswidget-color-white">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>服务项目</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div style="margin: 5px;">
								<div class="form-group" style="margin: 0 0px;">
									<div class="col-md-3">
										<input type="text" value="" placeholder="服务项目" class="form-control" id="searchItem">
									</div>
									<button onclick="itemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchItem').val('');itemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
									<div class="listMenu-float-right">
										<button onclick="addItems();" type="button" class="btn btn-primary">
											<i class="glyphicon glyphicon-plus"></i>
											<s:text name="add" />
										</button>
									</div>
								</div>
							</div>
							<table id="itemTableId" class="table table-striped table-bordered table-hover" width="100%">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	check();
	function check() {
		var type = $("#type").val();
		if (type == "1") {
			$("#itemDiv").hide();
			$("#gift").show();
		} else if (type == "2") {
			$("#itemDiv").show();
			$("#gift").hide();
		}
	}

	$("#storedValueRuleSetForm").validationEngine();

	var itemTableColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "服务内容",
	"width" : "15%",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "服务次数",
	"width" : "15%",
	"data" : function(data) {
		return data.num;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"addItems('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var itemTable = initDataTable("itemTableId", "${nvix}/nvixnt/storedValueRuleSetAction!goStoredValueRuleSetDetailContent.action", itemTableColumns, function(page, pageSize, orderField, orderBy) {
		var searchItem = $("#searchItem").val();
		searchItem = Url.encode(searchItem);
		var storedValueRuleSetId = $("#storedValueRuleSetId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"storedValueRuleSetId" : storedValueRuleSetId,
		"searchItem" : searchItem
		};
	});

	function addItems(id) {
		if ($("#storedValueRuleSetForm").validationEngine('validate')) {
			$("#storedValueRuleSetForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/storedValueRuleSetAction!saveOrUpdate.action",
			dataType : "text",
			success : function(data) {
				var d = data.split(':');
				$("#storedValueRuleSetId").val(d[2]);
				openWindowForShow('${nvix}/nvixnt/storedValueRuleSetAction!goSaveOrUpdateItem.action?storedValueRuleSetId=' + d[2] + '&id=' + id, "添加服务项目", 750, 525);
			}
			});
		}
	};
</script>