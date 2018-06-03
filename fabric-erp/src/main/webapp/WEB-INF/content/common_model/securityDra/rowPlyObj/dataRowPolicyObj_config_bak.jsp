<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<script type="text/javascript">
<!--
	function toSetTargetVal(tag,mdpId){
		//var tt = $(tag).parent().find("input:eq(0)");
			
		var selectInput = $(tag).parent().find("input:eq(0)");
		var selectRealInput = $(tag).parent().find("input:eq(1)");
		
		selectInput.unbind("click");
		selectInput.attr('value','');
		selectRealInput.attr('value','');
		
		//if(mdpId=='roleCode'){
		if(mdpId.indexOf('{')==0 ){	
			//$(tag).parent().find("input:eq(0)").attr('value','aaaaa');
			var dataParam = {keyUrl:mdpId};
			selectInput.click(dataParam,addSelectBizData);
			//.empty()
		}else{
			//var param = "id=${id}&metadataProperty="+mdpId;
			/* var param = {id:1,metadataProperty:mdpId};
			var resContentTmp = vixSendSync("${vix}/common/security/dataResRowPolicyObjSelectAction!checkMetadataProperty.action","POST",param);
			if(typeof(resContentTmp)!="undefine"){
				var colType = resContentTmp.isCollectionType;
				if(colType=='1'){
					alert("集合属性");
				}
			} */
			var param = "id=${id}&metadataProperty="+mdpId;
			var url = "${vix}/common/security/dataResRowPolicyObjSelectAction!checkMetadataProperty.action";
			
			vixAjaxSend(url,param,function(data){
				if(data.isCollectionType=='1' || data.isCollectionType=='2'){
					//alert("业务对象属性 或者 集合属性");
					var dataParam = {id:'${id}'};
					selectInput.click(dataParam,addSelectBizData2);
				}
			});
			
			/* 
			selectInput.unbind("click");
			selectInput.attr('value','');
			selectRealInput.attr('value',''); */
		}
		//$(tag).parent().find("input:eq(0)").attr('value','aaaaa');
	}
	
	function appendItem(){
		//var test = $("#initDivData > div");
		//alert(test);
		$("#dataRowForm").append($("#initDivData").find("div").clone());
	}
	
	function removeCondition(tag){
		$(tag).parent().remove();
	}
	
	/**
	 * 系统常量的选择
	 */
	function addSelectBizData(e){
		
		var keyUrlTmp = e.data.keyUrl;
		var selectUrl = "${vix}"+document.getElementById(keyUrlTmp).value;//$("#"+keyUrlTmp).val();
		//alert(selectUrl);
		var selectInput =$(e.target).parent().find("input:eq(0)");
		var selectRealInput =  $(e.target).parent().find("input:eq(1)");
		$.ajax({
			  url:selectUrl,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 440,
						title:"选择",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								var dataIds = "";
								var dataNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										dataIds += "," + v[0];
										dataNames += "," + v[1];
									}
								}
								if (dataIds.substr(0,1)==',') dataIds=dataIds.substr(1);
								if (dataNames.substr(0,1)==',') dataNames=dataNames.substr(1);
								
								$(selectRealInput).val(dataIds);
								$(selectInput).val(dataNames);
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**
	 * 业务对象或者集合的属性选择
	 */
	function addSelectBizData2(e){
		
		var idValTmp = e.data.id;
		var selectUrl = "${vix}/common/security/dataResRowPolicyObjSelectAction!goChooseCommonBiz.action";
		//alert(selectUrl);
		var selectInput =$(e.target).parent().find("input:eq(0)");
		var selectRealInput =  $(e.target).parent().find("input:eq(1)");
		$.ajax({
			  url:selectUrl,
			  cache: false,
			  data:"id="+idValTmp,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 440,
						title:"选择",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								var dataIds = "";
								var dataNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										dataIds += "," + v[0];
										dataNames += "," + v[1];
									}
								}
								if (dataIds.substr(0,1)==',') dataIds=dataIds.substr(1);
								if (dataNames.substr(0,1)==',') dataNames=dataNames.substr(1);
								
								$(selectRealInput).val(dataIds);
								$(selectInput).val(dataNames);
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	function saveOrUpdateObjConfig(){
		$("#dataRowForm").validationEngine('validate');
		var queryString = $('#dataRowForm').formSerialize(); 
		$.post('${vix}/common/security/dataResRowPolicyObjAction!saveOrUpdateObjConfig.action',
			queryString,
			function(result){
				asyncbox.success(result,"<s:text name='message'/>",function(action){
					//pager("start","${vix}/common/security/dataColSecAction!goSingleList.action?name="+name,'dataCol');
					loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action?name=${name}');
				});
			}
		 );
	}
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec_dataRowPolicyObj" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="appendItem()"><span>插入条件</span></a> <a href="#" onclick="saveOrUpdateObjConfig()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action?name=${name}');"><span>返回</span></a>
		</p>
	</div>
</div>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div>
		<s:iterator value="#request.rowPolicyObjMap" id="mapOne">
			<input id="<s:property value='key'/>" name="<s:property value='key'/>" type="hidden" value="<s:property value='value'/>">
		</s:iterator>
	</div>
	<div class="table" style="margin: 5px;">
		<form id="dataRowForm" action="">
			<input name="id" type="hidden" value="${id}">

			<s:iterator value="#request.dataList" var="data">
				<div>
					<s:select name="propertyName" list="mdpList" listKey="property" listValue="propertyName" value="%{#data.fields}" onchange="toSetTargetVal(this,this.value)"></s:select>
					<s:select name="propertyOperator" list="#request.operMap" listKey="key" listValue="value" value="%{#data.op}"></s:select>
					<input name="mdpValue" type="text" value="${data.value}"> <input name="mdpRealValue" type="hidden" value="${data.realValue}">

					<s:select name="propertyType" list="#request.typeMap" listKey="key" listValue="value" value="%{#data.valueType}"></s:select>
					<s:select name="ruleOpertator" list="#request.conditionMap" listKey="key" listValue="value" value="%{#data.ruleOp}"></s:select>
					<input class="btn" type="button" value="删除" onclick="removeCondition(this);"></br>
				</div>
			</s:iterator>

		</form>

		<div id="initDivData" style="display: none;">
			<div>
				<s:select name="propertyName" list="mdpList" listKey="property" listValue="propertyName" value="" onchange="toSetTargetVal(this,this.value)"></s:select>
				<s:select name="propertyOperator" list="#request.operMap" listKey="key" listValue="value" value=""></s:select>
				<input name="mdpValue" type="text" value=""> <input name="mdpRealValue" type="hidden" value="">
				<s:select name="propertyType" list="#request.typeMap" listKey="key" listValue="value" value=""></s:select>
				<s:select name="ruleOpertator" list="#request.conditionMap" listKey="key" listValue="value" value=""></s:select>
				<input class="btn" type="button" value="删除" onclick="removeCondition(this);">
			</div>
			<%-- <s:select list="mdpList" listKey="property" listValue="propertyName" value=""></s:select> --%>
		</div>
	</div>
	<div class="sub_menu sub_menu_bot">
		<div class="drop">
			<p>
				<a href="#" onclick="saveOrUpdateObjConfig()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');"><span>返回</span></a>
			</p>
		</div>
	</div>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
}
//-->
</script>