<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 会员管理 <span>&gt; 新增会员</span>
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
			<h2>会员</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="customerAccountForm">
				<input type="hidden" id="id" name="customerAccount.id" value="${customerAccount.id}" />
				<input type="hidden" id="clipId" name="customerAccountClip.id" value="${customerAccountClip.id}" />
				<input type="hidden" id="code" name="customerAccount.code" value="${customerAccount.code}" />
				<input type="hidden" id="status" name="customerAccount.status" value="${customerAccount.status}" />
				<!--<input type="text" id="channelDistributorId" name="customerAccount.channelDistributor.id" value="${customerAccount.channelDistributor.id}" />-->
				<fieldset>
					<legend>基本信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>姓名:</label>
						<div class="col-md-3">
							<input id="name" name="customerAccount.name" value="${customerAccount.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>手机:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="mobilePhone" name="customerAccount.mobilePhone" value="${customerAccount.mobilePhone}" data-prompt-position="topLeft" class="form-control validate[required,custom[phone]]" type="text" />
								 <span class="input-group-addon"><i class="fa fa-phone"></i></span>
							 </div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">身份证号码:</label>
						<div class="col-md-3">
							<input id="identityId" name="customerAccount.identityId" value="${customerAccount.identityId}" data-prompt-position="topLeft" class="form-control validate[custom[chinaIdLoose]]" type="text" />
						</div>
						<label class="col-md-2 control-label">性别:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <c:if test="${customerAccount.sex == 1}">active</c:if>"> <input type="radio" value="1" id="sex" name="customerAccount.sex" <c:if test="${customerAccount.sex == 1}">checked="checked"</c:if>>男
								</label> <label class="btn btn-default <c:if test="${customerAccount.sex == 0}">active</c:if>"> <input type="radio" value="0" id="sex" name="customerAccount.sex" <c:if test="${customerAccount.sex == 0}">checked="checked"</c:if>>女
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">年龄:</label>
						<div class="col-md-3">
							<input id="age" name="customerAccount.age" value="${customerAccount.age}" data-prompt-position="topLeft" class="form-control validate[custom[integer]]" type="text" />
						</div>
						<label class="col-md-2 control-label">地址:</label>
						<div class="col-md-3">
							<input id="address" name="customerAccount.address" value="${customerAccount.address}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>生日:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="birthday" name="customerAccount.birthday" value="${customerAccount.birthdayStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">单位地址:</label>
						<div class="col-md-3">
							<input id="workAddress" name="customerAccount.workAddress" value="${customerAccount.workAddress}" data-prompt-position="topLeft" class="form-control validate" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>等级标识:</label>
						<div class="col-md-3">
							<select id="memberLevelId" name="customerAccount.memberLevel.id" class="form-control validate[required]">
								<c:if test="${customerAccount.memberLevel.id == null}">
									<c:forEach items="${memberLevelList}" var="entity">
										<option value="${entity.id}" <c:if test="${entity.isDefault == 'Y'}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>	
								</c:if>
								<c:if test="${customerAccount.memberLevel.id != null}">
									<c:forEach items="${memberLevelList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.memberLevel.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='customerAccount.isUse == "Y"'>active</s:if>"> <input type="radio" value="Y" id="status" name="customerAccount.isUse" class="validate[required]" <s:if test='customerAccount.isUse == "Y"'>checked="checked"</s:if>>启用
								</label> <label class="btn btn-default <s:if test='customerAccount.isUse == "N"'>active</s:if>"> <input type="radio" value="N" id="status" name="customerAccount.isUse" <s:if test='customerAccount.isUse == "N"'>checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">门店:</label>
						<div class="col-md-3">
							<input id="channelDistributorId" name="customerAccount.channelDistributor.id" value="${customerAccount.channelDistributor.id}" data-prompt-position="topLeft" class="form-control" type="hidden" readonly="readonly"/>
							<input id="channelDistributorName" name="customerAccount.channelDistributor.name" value="${customerAccount.channelDistributor.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
					<legend>拍照上传:</legend>
					<div class="form-group">
					<label class="col-md-2 control-label"></label>
						<div class="col-xs-5"> 
				   		<s:if test="customerAccount.headImage!=null && customerAccount.headImage!=''">
					   		
				   			<p/>
					    	<input id="headImage" type="hidden" name="customerAccount.headImage" value="<s:property value="customerAccount.headImage"/>"/>
					    	<img alt="会员头像" src="${customerAccount.headImage}" style="width:300px;height:300px;" > 
				   		</s:if>
				   		<s:else>
					   		<%-- <script type="text/javascript" src="${nvix}/vixntcommon/base/js/ajaxfileuploadcam.js"></script> --%>
							<script type="text/javascript" src="${nvix}/vixntcommon/base/js/camera.js"></script>
					    	<input id="headImageId" type="hidden" name="customerAccount.headImage" value="<s:property value="customerAccount.headImage"/>"/>
					   		<!-- 相机加载失败时默认加载显示的头像 -->
					   		<img id="loadImage" alt="隐藏头像" src="" style="display:none;"> 
							<div class="form-group">
								<label class="ace-file-input ace-file-multiple">
									<!-- <span class="ace-file-container" data-title="上传头像" style="height:430px;line-height:430px;"></span> -->
									<video id="navy_video" width="320px" height="320px"  autoplay="autoplay" class="borderstyle"></video>
									<canvas id="canvas" width="320px" height="320px" class="borderstyle"   style="display:none;"></canvas>
								</label>
							</div>
							<input id="snap" type="button" value="拍照"/>
							<input id="cancel" type="button" value="清除"/>
							
 				   		</s:else>
					</div>
					</div>
					<legend>联系方式:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">QQ:</label>
						<div class="col-md-3">
							<input id="qqAccount" name="customerAccount.qqAccount" value="${customerAccount.qqAccount}" class="form-control validate[custom[qq]]" type="text" />
						</div>
						<label class="col-md-2 control-label">微信:</label>
						<div class="col-md-3">
							<input id="msnAccount" name="customerAccount.msnAccount" value="${customerAccount.msnAccount}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">邮箱:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="email" name="customerAccount.email" value="${customerAccount.email}" class="form-control validate[custom[email]]" type="text" />
								 <span class="input-group-addon"><i class="fa fa-envelope-o"></i> </span>
							 </div>
						</div>
					</div>
					<legend>推荐人:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">推荐人:</label>
						<div class="col-md-3">
							<input id="referrerPhone" name="customerAccount.referrerPhone" value="${customerAccount.referrerPhone}" class="form-control validate" type="text" placeholder="请输入推荐人手机号" />
						</div>
					</div>
					<legend>会员卡:</legend>
					<input type="hidden" id="cardEntityType" name="customerAccountClip.card.type" value="${customerAccountClip.card.type}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡类型:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="cardEntityId" type="hidden" name="customerAccountClip.card.id" value="${customerAccountClip.card.id}" />
										<input id="cardEntityName" name="customerAccountClip.card.name" value="${customerAccountClip.card.name}" class="form-control validate[required]" type="text" readonly="readonly" />
										<div class="input-group-btn">
											<button onclick="goChooseCustomerClipType();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#cardEntityId').val('');$('#cardEntityName').val('');$('#cardEntityPayMoney').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>储值金额:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="cardEntityPayMoney" name="customerAccountClip.payMoney" value="${customerAccountClip.payMoney}" class="form-control validate[required,custom[number]]" type="text" /><span class="input-group-addon"><i class="fa">元</i></span>
						    </div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡积分:</label>
						<div class="col-md-3">
							<input id="clipPointValue" name="customerAccountClip.pointValue" value="${customerAccountClip.pointValue}" class="form-control validate[required]" type="text" />
						</div>
							<input id="clipCode" name="customerAccountClip.code" value="${customerAccountClip.code}" class="form-control validate" type="hidden" />
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡号:</label>
						<div class="col-md-3">
							<input id="clipName" name="customerAccountClip.name" value="${customerAccountClip.name}" class="form-control validate" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员卡有效期至:</label>
						<div class="col-md-3">
							<div class="input-group">
							<%-- <input id="clipExpiryDate" name="customerAccountClip.expiryDate" value="${customerAccountClip.expiryDate}" class="form-control validate[required]" type="text" /><span class="input-group-addon"><i class="fa">年</i></span> --%>
								<input id="endTime" name="customerAccountClip.expiryDate" value="${customerAccountClip.expiryDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='customerAccountClip.isUse == "Y"'>active</s:if>"> <input type="radio" value="Y" id="status" name="customerAccountClip.isUse" class="validate" <s:if test='customerAccountClip.isUse == "Y"'>checked="checked"</s:if>>启用
								</label> <label class="btn btn-default <s:if test='customerAccountClip.isUse == "N"'>active</s:if>"> <input type="radio" value="N" id="status" name="customerAccountClip.isUse" <s:if test='customerAccountClip.isUse == "F"'>checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label">生效日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startTime" name="customerAccountClip.startTime" value="${customerAccountClip.startTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">截止日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="endTime" name="customerAccountClip.endTime" value="${customerAccountClip.endTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div> --%>
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
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('drp_vixntsupplieritem', '${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');">
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
	function saveOrUpdate() {
		var clipId = $("#clipId").val();
		var status = $("#status").val();
		//var cardEntityId = $("#cardEntityId").val();
		if(status == "2"){
			if ($("#customerAccountForm").validationEngine('validate')) {
				$("#customerAccountForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixCustomerAccountAction!updateCustomer.action",
				dataType : "text",
				success : function() {
					loadContent('', '${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');
				}
				});
			} 
		}else{
		if ($("#customerAccountForm").validationEngine('validate')) {
			$("#customerAccountForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixCustomerAccountAction!saveOrUpdate.action",
			dataType : "text",
			success : function(data) {
				var id = data.split(":");
				if(id[0] == '0'){
					layer.alert(id[1]);
					return;
				}else{
					$("#id").val(id[0]);
					$("#clipId").val(id[1])
					if(!id[1]){
						layer.alert("请办理会员卡!");
						return ;
					}
					$.ajax({
						url:'${nvix}/nvixnt/nvixCustomerAccountClipAction!goCustomerCilpPay.action',
						cache:false,
						data :{
							"clipId":id[1]
						},
						success: function(html){
							layer.open({
								type: 1,
							    title: "办卡结算",
							    area: ['850px', '350px'],
							    closeBtn: 1,
							    content: html,
							    btn: ['确定','关闭'],
							    yes:function(index, layero){
							    	var payType = $("input[name='payType']:checked").val();
							    	var payMoney = $("#payMoney").val();
							    	var clipId = $("#clipId").val();
							    	var rechargeRecordId = $("#rechargeRecordId").val();
							    	if(payType == "4"){
							    		weixinPay(payType,payMoney,clipId,rechargeRecordId,index);
							    	}else{
							    		$.ajax({
								    		url : "${nvix}/nvixnt/nvixCustomerAccountClipAction!customerClipPay.action",
								    		cache : false,
								    		data : {
								    			"payType":payType,
								    			"clipId":clipId,
								    			"payMoney":payMoney,
								    			"rechargeRecordId":rechargeRecordId
								    		},
								    		success : function(result){
								    			var r= result.split(":");
							    				if(r[0] != '0'){
							    					layer.close(index);
							    					loadContent('','${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');
							    					
							    				}else{
							    					layer.alert(r[1]);
							    					return ;
							    				}
								    		}
								    	});
							    	}
							    },cancel: function(index, layero){
							    	
							    }
							});
						},error: function(XMLHttpRequest, textStatus, errorThrown) {
							layer.alert("系统错误，请联系管理员");
						}
					});
				}
			}
			});
		} 
		}
	};
	var t1;
	function weixinPay(payType,payMoney,clipId,rechargeRecordId,loadIndex){
		//alert(rechargeRecordId);
		$.ajax({
			url : '${nvix}/nvixnt/vixntWxPayRechargeAction!weixinRecharge.action',
			cache: false,
			data : {
				"rechargeRecordId" : rechargeRecordId,
				"customerAccountClipId" : clipId,
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
	function checkPay(rechargeRecordId){
		//alert(index + 1);
		$.ajax({
			url : '${nvix}/nvixnt/vixntWxPayRechargeAction!checkPay.action',
			cache: false,
			data : {
				"rechargeRecordId" : rechargeRecordId
			},
			success: function(result){
				if(result == "success"){
					//goPrintOrder(rechargeRecordId);
					layer.closeAll();
					clearInterval(t1);
					loadContent('','${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');
				}
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
	}
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

		var itemTable = initDataTable("itemTableId", "${nvix}/nvixnt/cardAction!goStoredCardEntityDetailContent.action", itemTableColumns, function(page, pageSize, orderField, orderBy) {
			var searchItem = $("#searchItem").val();
			searchItem = Url.encode(searchItem);
			var cardEntityId = $("#cardEntityId").val();
			check();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"cardEntityId" : cardEntityId,
			"searchItem" : searchItem
			};
		});
		function addItems(id) {
			if($("#id").val() != null && $("#id").val() != ""){
				if($("#clipId").val() != null && $("#clipId").val() != ""){
					openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdateClipDetail.action?id='+$("#clipId").val()+'&customerAccountClipDetailId='+id,"memberTagForm","添加服务项目", 750, 325,itemTable);
				}else{
					layer.alert("请办理会员卡!");
					return false;
				}
			}else{
				if ($("#customerAccountForm").validationEngine('validate')) {
					$("#customerAccountForm").ajaxSubmit({
						type : "post",
						url : "${nvix}/nvixnt/nvixCustomerAccountAction!saveOrUpdate.action?attachmentId?="+$("#attachmentId").val(),
						dataType : "text",
						success : function(data) {
							var ids = data.split(",");
							$("#id").val(ids[0]);
							if(ids[1] != null && ids[1] != ''){
								$("#clipId").val(ids[1]);
								openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdateClipDetail.action?id='+ids[1]+'&customerAccountClipDetailId='+id,"memberTagForm","添加服务项目", 750, 325,itemTable);
							}else{
								layer.alert("请办理会员卡!");
								return false;
							}
						}
					});
				}
			}
			
		};
		
		function goChooseCustomer() {
			chooseListData('${nvix}/nvixnt/nvixCustomerAccountAction!goChooseChannelDistributor.action', 'single', '选择门店', 'channelDistributor');
		};
		function convertCanvasToImage() {
			var pic = document.getElementById("canvas").toDataURL("image/png");
			pic = pic.replace(/^data:image\/(png|jpg);base64,/, "")
			//var data={};
			//data.baseimg = pic;
			$.ajax({
				type:"POST",
				url:'${nvix}/nvixnt/nvixCustomerAccountAction!saveOrUpdateAttachments.action',
				//fileElementId:'canvas',
				data:{'baseimg':pic},
				success:function(result){
					var d= result.split(",");
					if(d[0]=='1'){
						$("#headImageId").val(d[1]);
						//layer.alert("图片上传成功...");
					}else{
						layer.alert("图片上传失败...");
					}
				}
			});
		}
		function goChooseCustomerClipType() {
			chooseListData('${nvix}/nvixnt/nvixCustomerAccountClipAction!goChooseCustomerClipType.action', 'single', '会员卡类型', 'cardEntity',function(){
				var payMoney = $("#cardEntityPayMoney").val();
				$.ajax({
					type:"POST",
					url:'${nvix}/nvixnt/nvixCustomerAccountClipAction!countPoint.action',
					data:{'payMoney':payMoney},
					success:function(result){
						check();
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
		function deleteById(id){
			deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAccountClipAction!deleteClipDetail.action?id='+id,'是否删除?',itemTable);
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
</script>