<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#itemPriceForm").validationEngine();
$(document).ready(function(){
	var spec = getSpecificationDetail();
	var specArray = spec.split(":");
	for(var i=0;i<specArray.length;i++){
		var sd = specArray[i];
		if(sd != ""){
			var sds = sd.split("!");
			var html = "<option value='"+sds[0]+"'>"+sds[2]+"</option>";
			$("#itemSku").append(html);
		}
	}
	$.getJSON('${vix}/mdm/item/fastAddItemAction!loadContainsSkuJson.action?itemId='+$("#id").val(),
		function(json){
			var specJson = json.specJson ;
			if(specJson != null && specJson != 'undefined'){
			var specArray = specJson.split(":");
				for(var i=0;i<specArray.length;i++){
					var sd = specArray[i];
					if(sd != ""){
						var sds = sd.split("!");
						var html = "<option value='"+sds[0]+"'>"+sds[1]+"</option>";
						$("#itemSku").append(html);
					}
				}
			}
		}
	);
});
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<s:hidden id="id" name="itemPrice.id" value="%{itemPrice.id}" theme="simple" />
	<s:hidden id="startDate" name="itemPrice.startDate" value="%{itemPrice.startDate}" theme="simple" />
	<s:hidden id="endDate" name="itemPrice.endDate" value="%{itemPrice.endDate}" theme="simple" />
	<form id="itemPriceForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">最高价格:&nbsp;</td>
					<td><input id="maxPrice" name="itemPrice.maxPrice" value="${itemPrice.maxPrice}" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
					</td>
					<td align="right">最低价格:&nbsp;</td>
					<td><input id="minPrice" name="itemPrice.minPrice" value="${itemPrice.minPrice}" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
					</td>
				</tr>
				<tr height="30">
					<td align="right">SKU:&nbsp;</td>
					<td><select id="itemSku">
							<option value="">----请选择----</option>
					</select></td>
					<td align="right">价格:&nbsp;</td>
					<td><input id="price" name="itemPrice.price" value="${itemPrice.price}" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>