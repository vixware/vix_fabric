<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<script type="text/javascript">
<!--
	$(function(){
		$('#follow').scrollFollow({
			offset:50,
		   container: 'followBox'
		  });
		
	});
	
	
	function toSetTargetVal(tag,mdpId){
		//var tt = $(tag).parent().find("input:eq(0)");
			
		//var selectInput = $(tag).parent().find("input:eq(0)");
		//var selectRealInput = $(tag).parent().find("input:eq(1)");
		var selectInput = $(tag).parent().parent().find("input:eq(0)");
		var selectRealInput = $(tag).parent().parent().find("input:eq(1)");
		
		
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
			//var param = "id=${id}&metadataProperty="+mdpId;
			var param = "id=${metadataId}&metadataProperty="+mdpId;
			var url = "${vix}/common/security/dataResRowPolicyObjSelectAction!checkMetadataProperty.action";
			
			vixAjaxSend(url,param,function(data){
				if(data.isCollectionType=='1' || data.isCollectionType=='2'){
					//alert("业务对象属性 或者 集合属性");
					var dataParam = {id:data.propertyMetadataId};
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
		//alert($("#initDivData").html());
		$("#dataRowPolicyTable").append($("#initDivData").find("tr").clone());
	}
	
	function removeCondition(tag){
		$(tag).parent().parent().remove();
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
						width : 760,
						height : 540,
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
		var selectInput =$(e.target).parent().parent().find("input:eq(0)");
		var selectRealInput =  $(e.target).parent().parent().find("input:eq(1)");
		$.ajax({
			  url:selectUrl,
			  cache: false,
			  data:"id="+idValTmp,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 760,
						height : 540,
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
								debugger;
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
	 * 根据值类型 处理
	 */
	function toSetTargetValByValueType(tag,valueType){

		//if(mdpId=='roleCode'){
		if(valueType=='hql'){	
			var selectInput = $(tag).parent().parent().find("input:eq(0)");
			var selectRealInput = $(tag).parent().parent().find("input:eq(1)");
			
			selectInput.unbind("click");
			selectInput.attr('value','');
			selectRealInput.attr('value','');
		}
	}
	
	
	function saveOrUpdateObjConfig(){
		$("#dataRowForm").validationEngine('validate');
		var queryString = $('#dataRowForm').formSerialize(); 
		$.post('${vix}/common/security/dataResRowPolicyObjAction!saveOrUpdateObjConfig.action',
			queryString,
			function(result){
				showMessage(result);
				setTimeout("", 1000);		
				//pager("start","${vix}/common/security/dataColSecAction!goSingleList.action?name="+name,'dataCol');
				loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action?name=${name}');
			}
		 );
	}
		


//-->
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i> <strong><img alt="" src="img/drp_channel.png">策略配置</strong>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_right" style="position: relative" id="followBox">
			<div class="np_right_box" id="follow" style="position: absolute; top: 0">
				<!-- <div class="t_btn">
	            	<a href="javascript:;" onclick="javascript:showAddToDoId(this);scrollInViewPart('addtodo');"><img src="img/list_icon_22.gif" style="vertical-align:middle;"> New Task</a>
	            </div> -->
				<div class="nprb_title">帮助</div>
				<ul class="npr_list">
					<li><a href="javascript:;">如何选择属性?</a></li>
					<li><a href="javascript:;">如何选择条件?</a></li>
					<li><a href="javascript:;">如何填写（和选择）值?</a></li>
					<li><a href="javascript:;">如何选择值类型?</a></li>
					<li><a href="javascript:;">如何选择操作符?</a></li>
				</ul>
			</div>
		</div>
		<div class="np_left clearfix" id="common_table">
			<div class="np_left_title">
				<h2>策略条件配置</h2>
				<p></p>
			</div>

			<form id="dataRowForm" action="">
				<div class="voucher npv lineh30">
					<div class="addanddel clearfix" style="margin-bottom: 10px;">
						<a href="javascript:;" onclick="javascript:appendItem();" class="abtn"><span>增加</span></a> <a href="javascript:;" onclick="javascript:saveOrUpdateObjConfig();" class="abtn"><span>保存</span></a> <a href="javascript:;" onclick="loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action?name=${name}');" class="abtn"><span>返回</span></a>

						<s:iterator value="#request.rowPolicyObjMap" id="mapOne">
							<input id="<s:property value='key'/>" name="<s:property value='key'/>" type="hidden" value="<s:property value='value'/>">
						</s:iterator>
					</div>
					<div class="cbrem">

						<input name="id" type="hidden" value="${id}">
						<table id="dataRowPolicyTable" style="margin-top: 0;" id="addlistbox">
							<tr>
								<!-- <th class="removeAllBox"><input class="removeAll" name="" type="checkbox" value="" /></th> -->
								<th>属性</th>
								<th>条件</th>
								<th style="width: 50%;">值</th>
								<th>值类型</th>
								<th>操作符</th>
								<th>删除</th>
							</tr>

							<s:iterator value="#request.dataList" var="data">
								<tr>
									<!-- 	<td class="removeLineBox" >
								  		<input name="" type="checkbox" class="removeLine" value="" />
								  	</td> -->
									<td><s:select name="propertyName" list="mdpList" listKey="property" listValue="propertyName" value="%{#data.fields}" onchange="toSetTargetVal(this,this.value)" theme="simple"></s:select></td>
									<td><s:select name="propertyOperator" list="#request.operMap" listKey="key" listValue="value" value="%{#data.op}" theme="simple"></s:select></td>
									<td><input name="mdpValue" type="text" value="${data.value}" style="width: 100%;"> <input name="mdpRealValue" type="hidden" value="${data.realValue}"></td>
									<td><s:select name="propertyType" list="#request.typeMap" listKey="key" listValue="value" value="%{#data.valueType}" theme="simple" onchange="toSetTargetValByValueType(this,this.value);"></s:select></td>
									<td><s:select name="ruleOpertator" list="#request.conditionMap" listKey="key" listValue="value" value="%{#data.ruleOp}" theme="simple"></s:select></td>
									<td><input class="btn" type="button" value="删除" onclick="removeCondition(this);"></td>
								</tr>
							</s:iterator>

							<%--  <tr>
							  	<td class="removeLineBox"><input name="" type="checkbox" class="removeLine" value="" /></td>
			                    <td>
			                    	<select name="" style="width:100%;">
				                      <option value="0"></option>
				                      <option value="1">Drochure Design Doutle</option>
				                    </select>
			                    </td>
			                    <td><textarea cols="" rows="" style="width:97%;"></textarea></td>
			                    <td><input name="" type="text" value="1.00" size="10" class="ar" /></td>
			                    <td><input name="" type="text" value="0.00" size="10" class="ar" /></td>
			                    <td><input name="" type="text" value="0.00" size="10" class="ar" /></td>
			                    <td>
			                    	<select name="">
				                      <option value="1">none</option>
				                    </select>
				                </td>
							</tr> --%>

						</table>
					</div>
				</div>
			</form>
			<table id="initDivData" style="display: none;">
				<%-- <div>
					<s:select name="propertyName" list="mdpList" listKey="property" listValue="propertyName"  value="" onchange="toSetTargetVal(this,this.value)"></s:select>
					<s:select name="propertyOperator" list="#request.operMap" listKey="key" listValue="value" value=""></s:select>
					<input name="mdpValue" type="text" value="">
					<input name="mdpRealValue" type="hidden" value="">
					<s:select name="propertyType" list="#request.typeMap" listKey="key" listValue="value" value=""></s:select>
					<s:select name="ruleOpertator" list="#request.conditionMap" listKey="key" listValue="value" value=""></s:select>
					<input class="btn" type="button" value="删除" onclick="removeCondition(this);">
				</div> --%>

				<tr>
					<!-- <td class="removeLineBox">
				  		<input name="" type="checkbox" class="removeLine" value="" />
				  	</td> -->
					<td><s:select name="propertyName" list="mdpList" listKey="property" listValue="propertyName" value="" onchange="toSetTargetVal(this,this.value)" theme="simple"></s:select></td>
					<td><s:select name="propertyOperator" list="#request.operMap" listKey="key" listValue="value" value="" theme="simple"></s:select></td>
					<td><input name="mdpValue" type="text" value="" style="width: 100%;"><input name="mdpRealValue" type="hidden" value=""></td>
					<td><s:select name="propertyType" list="#request.typeMap" listKey="key" listValue="value" value="" theme="simple" onchange="toSetTargetValByValueType(this,this.value);"></s:select></td>
					<td><s:select name="ruleOpertator" list="#request.conditionMap" listKey="key" listValue="value" value="" theme="simple"></s:select></td>
					<td><input class="btn" type="button" value="删除" onclick="removeCondition(this);"></td>
				</tr>
				<%-- <s:select list="mdpList" listKey="property" listValue="propertyName" value=""></s:select> --%>
			</table>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>