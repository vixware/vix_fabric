<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#specTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#specTable tr:even").addClass("alt");
</script>
<input type="hidden" id="specCount" value="<s:property value='specificationList.size'/>" />
<table id="specTable" style="width: 85%; margin-left: 15%; text-align: center;" class="list">
	<tr>
		<th style="text-align: center;" width="15%">SKU码</th>
		<s:iterator var="specification" value="specificationList" status="s">
			<th style="text-align: center;">${specification.name}</th>
		</s:iterator>
		<th style="text-align: center;">操作</th>
	</tr>
	<s:iterator var="savedList" value="savedSpecificationList" status="s">
		<tr height="30">
			<td>${savedList[1]}<input type="hidden" id="spec_code_${s.count}" value="${savedList[3]}" /></td>
			<s:iterator var="specDetail" value="savedList" status="ss">
				<s:if test="#ss.count > 4">
					<td>${specDetail}</td>
				</s:if>
			</s:iterator>
			<td><s:if test="#savedList[2] <= 0">
					<a href="###"><img onclick="removeThisSpecification(this,${savedList[0]});" src="${vix}/common/img/icon_12.png" alt="删除" /></a>
				</s:if></td>
		</tr>
	</s:iterator>
</table>
<script type="text/javascript">
	function removeThisSpecification(t,specId){
		$(t).parent().parent().parent().remove();
		if(specId > 0){
			$.ajax({
				url:'${vix}/mdm/item/fastAddItemAction!deleteItemSpecificationById.action?itemId='+$("#id").val()+'&specId='+specId,
				cache: false,
				success: function(html){
					asyncbox.success(html,"<s:text name='vix_message'/>");
				}
			});
		}
	}
	
	function checkSkuIsExist(sku){
		var skuValue = $(sku).val();
		if(skuValue != ''){
			$.ajax({
				url:'${vix}/mdm/item/fastAddItemAction!checkSkuIsExist.action?itemId='+$("#id").val()+'&sku='+skuValue,
				cache: false,
				success: function(html){
					if(html.contains('exist')){
						asyncbox.success("SKU已存在,请修改!","<s:text name='vix_message'/>");
						$(sku).focus();
					}
				}
			});
		}
	}
	
	/** 获取新建规格数据，规格数据格式： sku!specCode(,)!specName(,): */
	function getSpecificationDetail(){
		var specificationData = "";
		$("input[type=text][id^='sku_']").each(function(){
			if($(this).val() != ''){
				var data = $(this).val() + "!";
				var id = $(this).attr("id").split("_")[1];
				$("input[type=hidden][id^='specDetailCode_"+id+"_']").each(function(){
					data = data + $(this).val() + ",";
				});
				data = data + "!";
				$("span[id^='specDetailName_"+id+"_']").each(function(){
					var html = $(this).html();
					if(html == ''){
						html = "PlaceHolder";
					}
					data = data + html + ",";
				});
				specificationData = specificationData + ":" + data;
			}
		});
		return specificationData;
	}
	
	/** 将持久化和未持久化的规格组合拼接，后台用来控制生成的规格组合不重复! */
	function getContainsSpecDetail(){
		var data = "";
		$("input[type=hidden][id^='spec_code_']").each(function(){
			data = data + $(this).val() + ":";
		});
		var sdc = "";
		var specCount = Number($("#specCount").val());
		$("input[type=hidden][id^='specDetailCode_']").each(function(i){
			sdc = sdc + $(this).val();
			if((i+1)%specCount == 0){
				sdc = sdc + ",:";
			}else{
				sdc = sdc + ",";
			}
		});
		data = data + sdc;
		return data;
	}
	
</script>