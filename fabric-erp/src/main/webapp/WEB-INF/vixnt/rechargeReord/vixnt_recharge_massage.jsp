<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerForm" class="form-horizontal" style="padding: 35px;">
	<fieldset>
		<div class="form-group">
			<label class="col-md-4 control-label">验证码:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="code" name="code" class="form-control validate[required,custom[integer]]" type="text" placeholder="请输入验证码" />
					<input id="checkedCode"  type="hidden" />
				</div>
			</div>
			<button onclick="sendMessage(this);" type="button" class="btn btn-primary">发送验证码</button>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	var countdown = 60;
	function settime(obj) {
		if (countdown == 0) {
			obj.removeAttribute("disabled");
			$(obj).text("发送验证码");
			countdown = 60;
			$(obj).addClass("select");
			return;
		} else {
			obj.setAttribute("disabled", true);
			$(obj).text("重新发送(" + countdown + ")");
			countdown--;
		}
		setTimeout(function() {
			settime(obj)
		}, 1000)
	}
	function sendMessage(obj){
		$.ajax({
			url:'${nvix}/nvixnt/nvixRechargeRecordAction!sendMessage.action',
			success:function(result){
				var r = result.split(":");
				if(r[0]=='1'){
					$("#checkedCode").val(r[1]);
					showMessage("短信发送成功!");
				}else{
					showMessage(r[1]);
				}
			}
		});
		settime(obj);
	}
</script>