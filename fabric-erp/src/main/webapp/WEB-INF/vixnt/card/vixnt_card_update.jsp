<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="cardEntityForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/cardAction!saveOrUpdate.action">
	<input type="hidden" id="cardEntityId" name="cardEntity.id" value="${cardEntity.id}" /> <input type="hidden" id="imagesId" name="imagesId" value="${imagesId}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<input id="name" name="cardEntity.name" value="${cardEntity.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡有效期至:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="expiryDate" name="cardEntity.expiryDate" value="${cardEntity.expiryDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'expiryDate'});">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 金额:</label>
			<div class="col-md-3">
				<input id="payMoney" name="cardEntity.payMoney" value="${cardEntity.payMoney}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 卡类型:</label>
			<div class="col-md-3">
				<select id="type" name="cardEntity.type" class="form-control validate[required]" onchange="check();">
					<option id="type1" value="1" <c:if test='${cardEntity.type eq "1"}'>selected="selected"</c:if>>余额卡</option>
					<option id="type2" value="2" <c:if test='${cardEntity.type eq "2"}'>selected="selected"</c:if>>次卡</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 能否储值:</label>
			<div class="col-md-3">
				<select id="isStoredValue" name="cardEntity.isStoredValue" class="form-control">
					<option value="Y" <c:if test='${cardEntity.isStoredValue eq "Y"}'>selected="selected"</c:if>>能</option>
					<option value="N" <c:if test='${cardEntity.isStoredValue eq "N"}'>selected="selected"</c:if>>否</option>
				</select>
			</div>
			<label class="col-md-2 control-label"> 能否挂失:</label>
			<div class="col-md-3">
				<select id="isReport" name="cardEntity.isReport" class="form-control">
					<option value="Y" <c:if test='${cardEntity.isReport eq "Y"}'>selected="selected"</c:if>>能</option>
					<option value="N" <c:if test='${cardEntity.isReport eq "N"}'>selected="selected"</c:if>>否</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 卡背景图片:</label>
			<div class="col-md-8">
				<div style="float: left; display: inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${cardEntity.pictureUrl}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img
						id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${cardEntity.pictureUrl}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
				</div>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="imageChange();" style="width: 80%;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="cardEntity.memo" class="form-control">${cardEntity.memo } </textarea>
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
	$(function(){
		check();
	});
	$("#cardEntityForm").validationEngine();
	function imageChange() {
		uploadFileOrImage("imageForm", "${nvix}/nvixnt/cardAction!saveOrUpdatePicture.action", "fileToUpload", "image", function(data) {
			var d = data.split(",");
			if (d[0] == '0') {
				showMessage(d[1]);
			} else {
				$('#imagesId').val(d[0]);
				$("#mainPic").attr("src", "${nvix}" + d[1]);
				$("#mainPicBig").attr("src", "${nvix}" + d[1]);
				showMessage("图片上传成功!");
			}
		});
	};
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
		// var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"addItems('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteServiceItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return del;
	}
	} ];

	var itemTable = initDataTable("itemTableId", "${nvix}/nvixnt/cardAction!goStoredCardEntityDetailContent.action", itemTableColumns, function(page, pageSize, orderField, orderBy) {
		var searchItem = $("#searchItem").val();
		searchItem = Url.encode(searchItem);
		var cardEntityId = $("#cardEntityId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"cardEntityId" : cardEntityId,
		"selectName" : searchItem
		};
	});
	function addItems(id) {
		if ($("#cardEntityId").val() != null && $("#cardEntityId").val() != "") {
			openWindowForShow('${nvix}/nvixnt/cardAction!goSaveOrUpdateItem.action?cardEntityId=' + $("#cardEntityId").val() + '&cardEntityDetailId=' + id,  "添加服务项目", 750, 525);
		} else {
			if ($("#cardEntityForm").validationEngine('validate')) {
				$("#cardEntityForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/cardAction!saveOrUpdate.action",
				dataType : "text",
				success : function(data) {
					var d = data.split(':');
					$("#cardEntityId").val(d[2]);
					openWindowForShow('${nvix}/nvixnt/cardAction!goSaveOrUpdateItem.action?cardEntityId=' + d[2]+'&cardEntityDetailId='+id, "添加服务项目", 750, 525);
				}
				});
			}
		}
	};
	
	// 删除服务明细
	function deleteServiceItemById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/cardAction!deleteServiceItemById.action?id=' + id, '是否删除服务项目?', itemTable);
	}
	
	function check(){
		var type = $("#type").val();
		if(type == "1"){
			$("#itemDiv").attr("style","display:none;");
		}else if(type == "2"){
			$("#itemDiv").attr("style","");
		}
	}
</script>