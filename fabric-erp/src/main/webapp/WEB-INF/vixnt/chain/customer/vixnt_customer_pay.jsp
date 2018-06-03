<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> <a>会员管理</a><span>> 会员充值 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<article style="overflow: hidden; zoom: 1;">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>充值信息</h2>
				</header>
				<div style="overflow: hidden; position: relative; padding: 0;">
					<form id="memberTagForm" class="form-horizontal" style="padding: 35px 15px;" action="">
					<input type="hidden" id="id" name="customerAccount.id" value="${customerAccount.id}" />
					<input type="hidden" id="customerAccountClipId" name="rechargeRecord.customerAccountClip.id" value="${rechargeRecord.customerAccountClip.id}" />
					<input type="hidden" id="cardEntityId" name="customerAccountClip.card.id" value="${customerAccountClip.card.id}" />
					<input type="hidden" id="customerAccountClipCardType" name="customerAccountClip.card.type" value="${customerAccountClip.card.type}" />
					<input type="hidden" id="rechargeRecordId" name="rechargeRecord.id" value="${rechargeRecord.id}" />
					<fieldset>
						<legend>会员信息:</legend>
						<div class="form-group">
							<s:if test="rechargeRecord.customerAccountClip.id != '' || rechargeRecord.customerAccountClip.id != null">
								<label class="col-md-2 control-label">会员卡号:</label>
								<div class="col-md-3">
									<input id="clipName" name="rechargeRecord.customerAccountClip.name" value="${rechargeRecord.customerAccountClip.name}" class="form-control validate[required]" type="text" readonly="readonly"/>
								</div>
							</s:if>
							<s:else>
								<label class="col-md-2 control-label"><span class="text-danger">*</span> 会员卡号:</label>
								<div class="col-md-3">
									<div class="row">
										<div class="col-sm-12">
											<div class="input-group">
												<input id="customerAccountClipName" name="rechargeRecord.customerAccountClip.name" value="${rechargeRecord.customerAccountClip.name}" class="form-control validate[required]" type="text" readonly="readonly" />
												<div class="input-group-btn">
													<button onclick="goChooseCustomerAccountClip();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i>
													</button>
													<button onclick="$('#customerAccountClipName').val('');$('#customerAccountClipId').val('');$('#customerName').val('');$('#customerPhone').val('');$('#customerAccountClipPoint').val('');itemTable.ajax.reload();" 
													type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</s:else>
							<label class="col-md-2 control-label"> 会员姓名:</label>
							<div class="col-md-3">
								<input id="customerName" name="customerAccount.name" value="${customerAccount.name}" class="form-control validate[required]" type="text" readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">会员手机号:</label>
							<div class="col-md-3">
								<input id="customerPhone" name="customerAccount.phone" value="${customerAccount.mobilePhone}" class="form-control validate[required]" type="text" readonly="readonly"/>
							</div>
							<label class="col-md-2 control-label">会员卡积分:</label>
							<div class="col-md-3">
								<input id="customerAccountClipPoint" name="rechargeRecord.customerAccountClip.pointValue" value="${rechargeRecord.customerAccountClip.pointValue}" class="form-control validate[required]" type="text" readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">会员卡类型:</label>
							<div class="col-md-3">
								<select id="cardType" name="" class="form-control validate[required]" disabled="disabled">
									<option  value="1" <c:if test='${customerAccountClip.cardType eq "1"}'>selected="selected"</c:if>>余额卡</option>
									<option  value="2"<c:if test='${customerAccountClip.cardType eq "2"}'>selected="selected"</c:if>>次卡</option>
								</select>
							</div>
							<label id="money1" class="col-md-2 control-label"> 会员卡余额:</label>
							<div id="money2" class="col-md-3">
								<input id="customerAccountClipMoney" name="rechargeRecord.customerAccountClip.money" value="${rechargeRecord.customerAccountClip.money}" class="form-control validate[required]" type="text" readonly="readonly" />
							</div>
							
						</div>
						<div class="form-group" id="itemDiv">
						<label class="col-md-2 control-label">服务项目:</label>
						<div class="col-md-8">
							<div id="contactPersonDiv" class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>服务项目</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="itemTableId" class="table table-striped table-bordered table-hover" width="100%">
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<legend>充值信息:</legend>
						<div class="form-group">
							<label class="col-md-2 control-label">充值规则:</label>
							<div class="col-md-3">
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group">
											<input id="storedValueRuleSetifAuthorize" type="hidden" name="storedValueRuleSet.ifAuthorize" value="${rechargeRecord.storedValueRuleSet.ifAuthorize}" />
											<input id="storedValueRuleSetId" type="hidden" name="storedValueRuleSet.id" value="${rechargeRecord.storedValueRuleSet.id }" />
											<input id="storedValueRuleSetName" name="storedValueRuleSet.name" value="${rechargeRecord.storedValueRuleSet.name}" class="form-control" type="text" readonly="readonly" />
											<div class="input-group-btn">
												<button onclick="goChooseReChargeRecord();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i>
												</button>
												<button onclick="$('#storedValueRuleSetAmount').val('');$('#storedValueRuleSetifAuthorize').val('');$('#storedValueRuleSetId').val('');$('#storedValueRuleSetName').val('');rechargeTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 充值金额:</label>
							<div class="col-md-3">
								<input id="storedValueRuleSetAmount" name="rechargeRecord.payMoney" value="${rechargeRecord.payMoney}" class="form-control validate[required]" type="text"/>
							</div>
						</div>
						<div class="form-group" id="rechargeItemId">
							<label class="col-md-2 control-label">服务项目:</label>
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
														<input type="text" value="" placeholder="服务项目" class="form-control" id="selectName">
													</div>
													<button onclick="rechargeTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="$('#selectName').val('');rechargeTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
													<%-- <div class="listMenu-float-right">
														<button onclick="addItems();" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-plus"></i>
															<s:text name="add" />
														</button>
													</div> --%>
												</div>
											</div>
											<table id="rechargeTableId" class="table table-striped table-bordered table-hover" width="100%">
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">是否打印小票:</label>
							<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default active"> 
								<input type="radio" value="Y" name="isPrinted"  checked="checked">是
								</label> 
								<label class="btn btn-default"> 
								<input type="radio" value="N" name="isPrinted">否
								</label>
							</div>
						</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-12">
								<button class="btn btn-success" type="button" onclick="checkPayMoney();">
									<i class="fa fa-save"></i> 下一步
								</button>
								<button class="btn btn-default" type="button" onclick="loadContent('drp_vixntsupplieritem', '${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');">
									<i class="fa fa-rotate-left"></i> 取消
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		</article>
	</section>
</div>
<script type="text/javascript">
	$("#memberTagForm").validationEngine();
	function goChooseReChargeRecord() {
		chooseListData('${nvix}/nvixnt/nvixRechargeRecordAction!goChooseReChargeRecord.action?id='+$("#customerAccountClipId").val(), 'single', '选择规则', 'storedValueRuleSet',function(){
			rechargeTable.ajax.reload();
		});
	};
	function goChooseCustomerAccountClip() {
		chooseListData('${nvix}/nvixnt/nvixRechargeRecordAction!goChooseCustomerAccountClip.action', 'single', '选择会员卡', 'customerAccountClip',function(){
			var clipId = $("#customerAccountClipId").val();
			$.ajax({
				url:'${nvix}/nvixnt/nvixRechargeRecordAction!goCustomerAccountClipByClipId.action?customerAccountClipId='+clipId,
				success: function(result){
					var r = result.split(":");
					$("#customerName").val(r[1]);
					$("#customerPhone").val(r[3]);
				}
			});
			itemTable.ajax.reload();
		});
	};
	
var itemTableColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "服务名称",
	"width" : "15%",
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"title" : "服务内容",
	"width" : "15%",
	"data" : function(data) {
		return data.itemServiceContent;
	}
	}, {
	"title" : "服务次数",
	"width" : "15%",
	"data" : function(data) {
		return data.number;
	}
	} ];

	var itemTable = initDataTable("itemTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!getItemSingleList.action", itemTableColumns, function(page, pageSize, orderField, orderBy) {
		check();	
		var clipId = $("#customerAccountClipId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"clipId" : clipId
		};
	});
var rechargeTableColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "服务名称",
	"width" : "15%",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "服务内容",
	"width" : "15%",
	"data" : function(data) {
		return data.serviceContent;
	}
	}, {
	"title" : "服务次数",
	"width" : "15%",
	"data" : function(data) {
		return data.num;
	}
	} ];

	var rechargeTable = initDataTable("rechargeTableId", "${nvix}/nvixnt/storedValueRuleSetAction!goStoredValueRuleSetDetailContent.action", rechargeTableColumns, function(page, pageSize, orderField, orderBy) {
		check();	
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		var storedValueRuleSetId = $("#storedValueRuleSetId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"storedValueRuleSetId" : storedValueRuleSetId,
		"selectName" : selectName
		};
	});
		function check(){
			var tag = $("#customerAccountClipCardType").val();
			obj = document.getElementById("cardType");
		    for(i=0;i<obj.length;i++){
		        if(obj[i].value==tag)
		            obj[i].selected = true;
		    }
			if(tag == "1"){
				$("#itemDiv").attr("style","display:none;");
				$("#rechargeItemId").attr("style","display:none;");
				$("#money1").attr("style","");
				$("#money2").attr("style","");
			}else if(tag == "2"){
				$("#itemDiv").attr("style","");
				$("#rechargeItemId").attr("style","");
				$("#money1").attr("style","display:none;");
				$("#money2").attr("style","display:none;");
			}else if(tag == "3"){
				$("#itemDiv").attr("style","");
				$("#rechargeItemId").attr("style","");
				$("#money1").attr("style","");
				$("#money2").attr("style","");
			}
		}
		
		

	/* function addItems(id) {
		if ($("#memberTagForm").validationEngine('validate')) {
			$("#memberTagForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixRechargeRecordAction!saveRechargeRecord.action?storedValueRuleSetId="+storedValueRuleSetId,
			dataType : "text",
			success : function(result) {
				var r = result.split(":");
				if(r[0]=="1"){
					$("#rechargeRecordId").val(r[1]);
					openSaveOrUpdateWindow('${nvix}/nvixnt/nvixRechargeRecordAction!goSaveOrUpdateRechargeDetail.action?id='+id+'&rechargeRecordId='+r[1],"memberForm","添加服务项目", 750, 325,rechargeTable);
				}else{
					return;
				}
			}
			});
		}else{
			return;
		}	
	} */
	/* function deleteById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixRechargeRecordAction!deleteRechargeDetailById.action?id='+id,'是否删除?',rechargeTable	);
	} */
	function checkPayMoney(){
		var payMoney = $("#storedValueRuleSetAmount").val();
		var ifAuthorize = $("#storedValueRuleSetifAuthorize").val();
		if(ifAuthorize == "0"){
			//openSaveOrUpdateWindow('${nvix}/nvixnt/nvixRechargeRecordAction!goSendMassage.action','','授权验证',750,325,null);
			$.ajax({
				url:'${nvix}/nvixnt/nvixRechargeRecordAction!goSendMassage.action',
				success:function(html){
					layer.open({
					    type: 1,
					    title: "授权验证",
					    area: ['575px', '255px'],
					    closeBtn: 1,
					    content: html,
					    btn: ['确定','关闭']
				    	,yes: function(index, layero){
				    		var code = $("#code").val();
				    		var checkedCode = $("#checkedCode").val();
				    		if(!code){
				    			showMessage("请输入验证码!");
				    			return;
				    		}
				    		if(checkedCode == code){
		    					layer.close(index);
		    					checkClipType();
				    		}else{
				    			showMessage("验证码输入错误!");
				    		}
				    	}
					})
				}
			});
		}else if(ifAuthorize == '' || ifAuthorize == '1'){
			checkClipType();
		}
	}
	function checkClipType(){
		var storedValueRuleSetId = $("#storedValueRuleSetId").val();
		var cardType = $("#cardType").val();
		if ($("#memberTagForm").validationEngine('validate')) {
			if(cardType == "2"){
				if(!storedValueRuleSetId){
					layer.alert("请选择充值规则!");
					return;
				}
				saveOrUpdate(storedValueRuleSetId,cardType);
			}else{
				saveOrUpdate(storedValueRuleSetId,cardType);
			}
		}else{
			return false;
		}
	}
	function saveOrUpdate(storedValueRuleSetId,cardType){
		$("#memberTagForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/nvixRechargeRecordAction!saveRechargeRecord.action?storedValueRuleSetId="+storedValueRuleSetId,
		dataType : "text",
		success : function(result) {
			var r = result.split(":");
			$.ajax({
				url:'${nvix}/nvixnt/nvixRechargeRecordAction!goSaveOrUpdate.action',
				cache:false,
				data :{
					"id":r[1]
				},
				success: function(html){
					layer.open({
						type: 1,
					    title: "充值结算",
					    area: ['850px', '350px'],
					    closeBtn: 1,
					    content: html,
					    btn: ['确定','关闭'],
					    yes:function(index, layero){
					    	var storedValueRuleSetId = $("storedValueRuleSetId").val();
					    	var payType = $("input[name='payType']:checked").val();
					    	var isPrinted = $("input[name='isPrinted']:checked").val();
					    	var customerAccountClipId = $("#customerAccountClipId").val();
					    	var rechargeRecordId = r[1];
					    	var payMoney = $("#payMoney").val();
					    	if(payType == "4"){
					    		weixinPay(payType,payMoney,customerAccountClipId,storedValueRuleSetId,rechargeRecordId,index,isPrinted);
					    	}else{
					    		layer.confirm("确认充值?",{
					    			btn:["确定","取消"]
					    		},function(){
					    			$.ajax({
							    		url : "${nvix}/nvixnt/nvixRechargeRecordAction!saveOrUpdate.action",
							    		cache : false,
							    		data : {
							    			"payType":payType,
							    			"customerAccountClipId":customerAccountClipId,
							    			"storedValueRuleSetId":storedValueRuleSetId,
							    			"id":rechargeRecordId
							    		},
							    		success : function(result){
							    			var r= result.split(":");
						    				if(r[0] != '0'){
						    					layer.closeAll();
						    					if(isPrinted == "Y"){
						    						goPrintOrder(rechargeRecordId);
						    					}
						    					loadContent('', '${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');
						    				}else{
						    					layer.alert(r[1]);
						    					return ;
						    				}
							    		}
							    	});
					    		}
					    		)
				    			
					    	}
					    },cancel: function(index, layero){
					    	
					    }
					});
				},error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert("系统错误，请联系管理员");
				}
			});
		}
		});
	}
	// 打印小票
	var LODOP;
	function goPrintOrder(rechargeRecordId) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixRechargeRecordAction!goPrintOrder.action?rechargeRecordId=' + rechargeRecordId,
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.SET_PRINT_PAGESIZE(3, "210mm", "80mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);//打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PREVIEW_WINDOW(1, 2, 0, 1024, 550, "");
			LODOP.PRINT();
			LODOP.PRINT_DESIGN(); 
			LODOP.PREVIEW();
		}
		});
	};
	var t1;
	function weixinPay(payType,payMoney,customerAccountClipId,storedValueRuleSetId,rechargeRecordId,loadIndex,isPrinted){
		//alert(rechargeRecordId);
		$.ajax({
			url : '${nvix}/nvixnt/vixntWxRechargeAction!weixinRecharge.action',
			cache: false,
			data : {
				"rechargeRecordId" : rechargeRecordId,
				"customerAccountClipId" : customerAccountClipId,
				"storedValueRuleSetId" : storedValueRuleSetId,
				"payAmount" : payMoney,
				"payType":payType
			},
			success: function(html){
				layer.open({
				    type: 1,
				    title: "微信支付",
				    area: ['330px', '370px'],
				    closeBtn: 1,
				    content: html
				});
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
		t1 = window.setInterval("checkPay('" + rechargeRecordId +"')",2000); 
	}
	function checkPay(rechargeRecordId,loadIndex,index){
		$.ajax({
			url : '${nvix}/nvixnt/vixntWxPayRechargeAction!checkPay.action',
			cache: false,
			data : {
				"rechargeRecordId" : rechargeRecordId
			},
			success: function(result){
				if(result == "success"){
					//goPrintOrder(orderId);
					layer.closeAll();
					clearInterval(t1);
					//window.location.href = '${nvix}/nvixnt/vixntWxPayRechargeAction!goPaySuccess.action';
					loadContent('','${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');
					
				}
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
	}
</script>