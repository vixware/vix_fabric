<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i> 会员管理 <span>&gt; 会员办卡</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_allocationitem', '${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>会员卡</h2>
		</header>
		<div class="widget-body">
			<form id="memberTagForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixCustomerAccountClipAction!applayCard.action">
				<input type="hidden" id="id" name="customerAccountClip.id" value="${customerAccountClip.id}" />
				<input type="hidden" id="customerAccountId" name="customerAccountClip.customerAccount.id" value="${customerAccountClip.customerAccount.id}" />
				<fieldset>
					<input type="hidden" id="cardEntityType" name="customerAccountClip.card.type" value="${customerAccountClip.card.type}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡类型:</label>
						<div class="col-md-3">
										<input id="cardEntityId" type="hidden" name="customerAccountClip.card.id" value="${customerAccountClip.card.id}" />
										<input id="cardEntityName" name="customerAccountClip.card.name" value="${customerAccountClip.card.name}" class="form-control validate[required]" type="text" readonly="readonly" />
										<!-- <div class="input-group-btn">
											<button onclick="goChooseCustomerClipType();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#cardEntityId').val('');$('#cardEntityName').val('');$('#cardEntityPayMoney').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div> -->
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡余额:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="cardEntityPayMoney" name="customerAccountClip.money" value="${customerAccountClip.money}" class="form-control" type="text" readonly="readonly" /><span class="input-group-addon"><i class="fa">元</i></span>
						    </div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡积分:</label>
						<div class="col-md-3">
							<input id="clipPointValue" name="customerAccountClip.pointValue" value="${customerAccountClip.pointValue}" class="form-control" readonly="readonly" type="text" />
						</div>
							<input id="clipCode" name="customerAccountClip.code" value="${customerAccountClip.code}" class="form-control validate" type="hidden" />
						<label class="col-md-2 control-label"><span class="text-danger">*</span>新会员卡号:</label>
						<div class="col-md-3">
							<input id="clipName" name="" value="" class="form-control validate" type="text" />
						</div>
					</div>
					<div class="form-group">
						
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡有效期限:</label>
						<div class="col-md-3">
								<input id="clipExpiryDate" name="customerAccountClip.expiryDate" value="${customerAccountClip.expiryDateStr}" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='customerAccountClip.isUse == "Y"'>active</s:if>"> <input type="radio" value="Y" id="status" name="customerAccountClip.isUse" class="validate" <s:if test='customerAccountClip.isUse == "Y"'>checked="checked"</s:if>>启用
								</label> <label class="btn btn-default <s:if test='customerAccountClip.isUse == "N"'>active</s:if>"> <input type="radio" value="N" id="status" name="customerAccountClip.isUse" <s:if test='customerAccountClip.isUse == "F"'>checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
					</div>
					<%-- <div class="form-group" id="moneyDiv">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡余额:</label>
						<div class="col-md-3">
							<input id="clipMoney" name="customerAccountClip.money" value="${customerAccountClip.money}" class="form-control validate[required]" type="text" />
						</div>
					</div> --%>
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
		</div>
	</div>
</div>
	
<script type="text/javascript">
	$("#memberTagForm").validationEngine();
	function check(){
		var tag = $("#cardEntityType").val();
		if(tag == "1"){
			$("#itemDiv").attr("style","display:none;");
			$("#moneyDiv").attr("style","");
		}else if(tag == "2"){
			$("#moneyDiv").attr("style","display:none;");
			$("#itemDiv").attr("style","");
		}else if(tag == "3"){
			$("#moneyDiv").attr("style","");
			$("#itemDiv").attr("style","");
		}
	}
	var itemTableColumns = [ {
		"orderable" : false,
		"title" : "编号",
		"width" : "5%",
		"data" : function(data) {
			return "";
		}
		}, {
		"title" : "服务项目",
		"width" : "10%",
		"name" : "code",
		"data" : function(data) {
			return data.name;
		}
		}, {
		"title" : "服务内容",
		"width" : "15%",
		"data" : function(data) {
			return data.itemServiceContent;
		}
		} ];

		var itemTable = initDataTable("itemTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!getItemSingleList.action", itemTableColumns, function(page, pageSize, orderField, orderBy) {
			var searchItem = $("#searchItem").val();
			searchItem = Url.encode(searchItem);
			var clipId = $("#id").val();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"clipId" : clipId,
			"searchItem" : searchItem
			};
		});
		function addItems(id) {
			if($("#id").val() != null && $("#id").val() != ""){
				openWindowForShow('${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdateClipDetail.action?id='+$("#id").val()+'&customerAccountClipDetailId='+id, "添加服务项目", 850, 425);
			}else{
				if ($("#memberTagForm").validationEngine('validate')) {
					$("#memberTagForm").ajaxSubmit({
						type : "post",
						url : "${nvix}/nvixnt/nvixCustomerAccountClipAction!applayCard.action",
						dataType : "text",
						success : function(data) {
							$("#id").val(data);
							openWindowForShow('${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdateClipDetail.action?id='+data, "添加服务项目", 850, 425);
						}
					});
				}
			}
		};
		function saveOrUpdate(){
			if ($("#memberTagForm").validationEngine('validate')) {
				$("#memberTagForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixCustomerAccountClipAction!applayCard.action?clipName="+$("#clipName").val()+"&customerAccountClipId="+$("#id").val(),
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						layer.alert(r[1]);
					}else if(r[0] == "1"){
						showMessage(r[1]);
						loadContent('','${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');
					}
					
				}
				});
			} else {
				return false;
			}
		}
		function goChooseCustomerClipType() {
			chooseListData('${nvix}/nvixnt/nvixCustomerAccountClipAction!goChooseCustomerClipType.action', 'single', '会员卡类型', 'cardEntity',function(){
				var payMoney = $("#cardEntityPayMoney").val();
				$.ajax({
					type:"POST",
					url:'${nvix}/nvixnt/nvixCustomerAccountClipAction!countPoint.action',
					data:{'payMoney':payMoney},
					success:function(result){
						var r = result.split(":");
						if(r[0]=='1'){
							$("#clipPointValue").val(r[1]);
						}else{
							$("#clipPointValue").val(0);
						}
					}
				});
				itemTable.ajax.reload();
			});
		};
		$(function(){
			check();
		})
</script>