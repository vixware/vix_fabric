<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
  function changeCustomerType(tag){
  	if(tag == 'enterPrise'){
  		$("#enterPrise1").attr('style','');
  		$("#enterPrise2").attr('style','');
  		$("#personal1").attr('style','display:none');
  		$("#personal2").attr('style','display:none');
  		$("#personal3").attr('style','display:none');
  	}else{
  		$("#enterPrise1").attr('style','display:none');
  		$("#enterPrise2").attr('style','display:none');
  		$("#personal1").attr('style','');
  		$("#personal2").attr('style','');
  		$("#personal3").attr('style','');
  	}
  }
  function saveOrUpdateEnterPriceCustomerAccount(){
	  if($('#customerAccountForm').validationEngine('validate')){
		$.post('${vix}/mdm/crm/customerAccountAction!fastSaveOrUpdate.action',
			{ 'customerAccount.name':$("#name").val(),
			  'customerAccount.type':$(":radio[name=customerType][checked]").val(),
			  'customerAccount.isHighSea':'0',
			  'customerAccount.shorterName':$("#shorterName").val(),
			  'customerAccount.englishName':$("#englishName").val(),
			  'customerAccount.telephone':$("#telephone1").val(),
			  'customerAccount.address':$("#address1").val()
			},
			function(result){
				var r = result.split(',');
				$("#customerAccountId").val(r[0]);
				$("#customerName").val(r[1]);
				showMessage(r[2]);
			}
		 );
	  }
  }
  function saveOrUpdatePersonalCustomerAccount(){
	  if($('#customerAccountForm').validationEngine('validate')){
		$.post('${vix}/mdm/crm/customerAccountAction!fastSaveOrUpdate.action',
			{ 'customerAccount.name':$("#name").val(),
			  'customerAccount.telephone':$("#telephone2").val(),
			  'customerAccount.address':$("#address2").val(),
			  'customerAccount.sex':$(":radio[name=sex][checked]").val(),
			  'customerAccount.type':$(":radio[name=customerType][checked]").val(),
			  'customerAccount.mobilePhone':$("#mobilePhone").val(),
			  'customerAccount.email':$("#email").val()
			},
			function(result){
				var r = result.split(',');
				$("#customerAccountId").val(r[0]);
				$("#customerName").val(r[1]);
				showMessage(r[2]);
			}
		 );
	  }
  }
  $("#customerAccountForm").validationEngine();
//-->
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="customerAccountForm">
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr>
					<td align="right">姓名：</td>
					<td><input id="name" name="name" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">客户类型:</td>
					<td><input type="radio" id="customerType1" name="customerType" value="enterPrise" onchange="changeCustomerType('enterPrise');" checked="checked" />企业客户 <input type="radio" id="customerType2" name="customerType" value="personal" onchange="changeCustomerType('personal');" />个人客户 <input type="radio" id="customerType3" name="customerType"
						value="internal" onchange="changeCustomerType('internal');" />内部客户</td>
				</tr>
				<tr id="enterPrise1">
					<td align="right">简称：</td>
					<td><input id="shorterName" name="shorterName" type="text" size="30" /></td>
					<td align="right">英文名称：</td>
					<td><input id="englishName" name="englishName" type="text" size="30" /></td>
				</tr>
				<tr id="enterPrise2">
					<td align="right">电话：</td>
					<td><input id="telephone1" name="telephone" type="text" size="30" /></td>
					<td align="right">企业地址：</td>
					<td><input id="address1" name="address" type="text" size="30" /></td>
				</tr>
				<tr id="personal1" style="display: none;">
					<td align="right">性别：</td>
					<td><input type="radio" id="sex1" name="sex" value="1" />男 <input type="radio" id="sex2" name="sex" value="0" />女</td>
					<td align="right">手机：</td>
					<td><input id="mobilePhone" name="mobilePhone" type="text" size="30" /></td>
				</tr>
				<tr id="personal2" style="display: none;">
					<td align="right">工作电话：</td>
					<td><input id="telephone2" name="telephone" type="text" size="30" /></td>
					<td align="right">邮箱：</td>
					<td><input id="email" name="email" type="text" size="30" /></td>
				</tr>
				<tr id="personal3" style="display: none;">
					<td align="right">地址：</td>
					<td colspan="3"><input id="address2" name="address" type="text" size="30" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->