<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerForm" class="form-horizontal" style="padding: 35px;">
	<fieldset>
		<div class="form-group">
			<label class="col-md-4 control-label">会员识别号:</label>
			<div class="col-md-6">
				<div class="input-group">
					<input id="code" name="code" class="form-control validate[required,custom[integer]] input-vip" type="text" placeholder="输入手机号或会员卡号" />
					<span class="input-group-addon" style="cursor: pointer;"><i class="fa fa-keyboard-o"></i></span>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<div class="num-key" style="display:none;">
	<div class="key-n key-number" style="cursor: pointer;">1</div>
	<div class="key-n key-number" style="cursor: pointer;">2</div>
	<div class="key-n key-number" style="cursor: pointer;">3</div>
	<div class="key-n key-number" style="cursor: pointer;">4</div>
	<div class="key-n key-number" style="cursor: pointer;">5</div>
	<div class="key-n key-number" style="cursor: pointer;">6</div>
	<div class="key-n key-number" style="cursor: pointer;">7</div>
	<div class="key-n key-number" style="cursor: pointer;">8</div>
	<div class="key-n key-number" style="cursor: pointer;">9</div>
	<div class="key-n key-number" style="cursor: pointer;">0</div>
	<div class="key-n key-clear" style="cursor: pointer;">清除</div>
	<div class="key-n key-dlete" style="cursor: pointer;">退格</div>
	<!-- <i class="ui-dialog-titlebar-close close_box"></i> -->
</div>
<script type="text/javascript">
	$(function(){
		//会员名输入
	  	$(".input-group-addon").click(function(){
	  		if($(".num-key").css("display")=="block"){
	  			$(".num-key").hide();
	  		}else{
	  			$(".num-key").show();
	  		}
	  	});
	  	$(".num-key .key-number").click(function(){
			var VIP = $(".input-vip")[0];
			VIP.value+=$(this).html(); 
		});
		//清除
		$(".num-key .key-clear").click(function(){
			var VIP = $(".input-vip")[0];
			VIP.value='';
		});
		//退格
		$(".num-key .key-dlete").click(function(){
			var VIP = $(".input-vip")[0];
			VIP.value=VIP.value.substring(0,VIP.value.length-1);
		});
		$(".num-key .key-n,.input-group-addon").hover(function(){
		  	$(this).css("background","#2AA6D9")
	  	},function(){
		  	$(this).css("background","#a9a9a9")
	  	})
	})
</script>