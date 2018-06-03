<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="integralRulesForm" class="form-horizontal" style="padding:40px 40px;" action="">
	<fieldset>
		<input type="hidden" id="id" name="customerAccount.id" value="${customerAccount.id}" />
		<input type="hidden" id="integralRulesId" name="integralRules.id" value="${integralRules.id}" />
		<input type="hidden" id="integralConsumptionRatio" name="integralRules.integralConsumptionRatio" value="${integralRules.integralConsumptionRatio}" class="form-control" readonly="readonly"/>
		<div class="form-group">
			<label class="col-md-5 control-label">拥有积分:</label>
			<div class="col-md-4">
				<input id="point" name="customerAccount.point" value="${customerAccount.point}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-5 control-label">兑换比例<span class="text-danger">(100积分可兑换)</span>:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="text" id="integralConsumptionRatio1" name="" value="${integralRules.integralConsumptionRatio*100}" class="form-control" readonly="readonly"/>
					<span class="input-group-addon">￥</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-5 control-label">消耗积分:</label>
			<div class="col-md-4">
				<input id="changePoint" name="" value="" type="text" data-prompt-position="topLeft" class="form-control validate[required]" onblur="check();"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-5 control-label">可兑换金额:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="changeMoney" name="" value="" type="text" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly"/>
					<span class="input-group-addon">￥</span>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	function check(){
		var integralConsumptionRatio = $("#integralConsumptionRatio").val();
		//layer.alert(integralConsumptionRatio);
		var changePoint = $("#changePoint").val();//10000
		var point = $("#point").val();//1000
		if(!integralConsumptionRatio){
			layer.alert("没有积分兑换规则,暂不能兑换!");
			return;
		}
		if((point - changePoint) < 0){
			layer.alert("您没有足够的积分");
		}else{
			var changeMoney = (changePoint*integralConsumptionRatio).toFixed(2);
			$("#changeMoney").val(changeMoney);
			$("#integralRulesForm").attr('action','${nvix}/nvixnt/nvixCustomerAccountAction!integralChangeCustomer.action?id='+$("#id").val()+"&changePoint="+changePoint+"&changeMoney="+changeMoney);
		}
	}
	/* $.ajax({
		url:'${nvix}/nvixnt/nvixCustomerAccountAction!integralChangeCustomer.action?id='+$("#id").val()+"&changePoint="+changePoint+"&changeMoney="+changeMoney,
		type:'Post',
		success:function(result){
			var r = result.split(":");
			layer.alert(r[1]);
		}
	}); */
</script>