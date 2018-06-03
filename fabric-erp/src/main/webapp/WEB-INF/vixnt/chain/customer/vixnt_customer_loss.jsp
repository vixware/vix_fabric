<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerForm" class="form-horizontal" style="padding: 35px;">
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 会员卡号:</label>
			<div class="col-md-3">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
						<input type="hidden" id="customerAccountClipId" name="rechargeRecord.customerAccountClip.id" value="${rechargeRecord.customerAccountClip.id}" />
							<input id="customerAccountClipName" name="rechargeRecord.customerAccountClip.name" value="${rechargeRecord.customerAccountClip.name}" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseCustomerAccountClip();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#clipName').val('');$('#clipId').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
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
								<select id="customerAccountClipCardType" name="" class="form-control validate[required]" disabled="disabled">
									<option  value="1" <c:if test='${customerAccountClip.cardType eq "1"}'>selected="selected"</c:if>>余额卡</option>
									<option  value="2"<c:if test='${customerAccountClip.cardType eq "2"}'>selected="selected"</c:if>>次卡</option>
									<option  value="3"<c:if test='${customerAccountClip.cardType eq "3"}'>selected="selected"</c:if>>通用卡</option>
								</select>
							</div>
							
							<label id="money1" class="col-md-2 control-label"> 会员卡余额:</label>
							<div id="money2" class="col-md-3">
								<input id="customerAccountClipMoney" name="rechargeRecord.customerAccountClip.name" value="${rechargeRecord.customerAccountClip.money}" class="form-control validate[required]" type="text" readonly="readonly" />
							</div>
							
						</div>
	</fieldset>
</form>
<script type="text/javascript">
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
		check();
	});
}
function check(){
	var cardType = $("#customerAccountClipCardType").val();
	if(cardType == "1"){
		$("#money1").attr("style","");
		$("#money2").attr("style","");
	}else if(cardType == "2"){
		$("#money1").attr("style","display:none;");
		$("#money2").attr("style","display:none;");
	}
}
</script>