<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- --%>

<script type="text/javascript">
<!--
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	
	
	function chooseMetaData(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectMetaDataAction!goChooseMetaData2.action?tag=choose&chkStyle=radio',//dataResRowOwnerAction
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
					 	width : 820,
						height : 520,
						title:"选择元数据",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								var metaData = "";
								var metaDataName = "";
								var result = returnValue.split(",");
								//debugger;
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										metaData +=  v[0];
										metaDataName += v[1];
										break;
									}
								}
								$("#metaDataId").val(metaData);
								$("#metaDataDisplayName").val(metaDataName);
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
		
	}
	
	
	
	$(function(){
		$("#salaryProjectOptionForm").validationEngine();
		//loadSalaryProjectItemGrid();
	});
	
	
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
			
			$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
								.animate({'opacity':'1', 'marginTop':'0'}, 500);
			
		}).focusout(function() {
			
			$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
					
				$(this).remove();
				tooltip.removeClass('showed-tooltip');
					
			});
		});
		JT_init();
	}
	$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
	$("table tr:even").addClass("alt");
	
	/**
	 * 保存元数据信息
	 */
	function saveOrUpdateSalaryProjectOption(){
		$("#salaryProjectOptionForm").validationEngine('validate');
		/* 
		var boData = $('#dgdA1').datagrid('getData');
		var boDataRow = boData.rows;
		if(boDataRow.length<=0){
			asyncbox.alert("必须为职员添加业务组织！","提示");
			return;
		} */
		if($('#salaryProjectOptionForm').validationEngine('validate')){
			var queryString = $('#salaryProjectOptionForm').formSerialize(); 
			$.post('${vix}/hr/salary/salaryProjectOptionAction!saveOrUpdate.action',
				queryString,
				function(result){
					showMessage(result);
					setTimeout("", 1000);
					loadContent('${vix}/hr/salary/salaryProjectOptionAction!goList.action');
				}
			 );
		}
		
	}
	
	/* 
	pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
	
	function currentPager(tag){
		pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
	}
	
	$("#order").validationEngine(); */
	
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/hr/hr_salary.png" alt="" />
					<s:text name="hrmgr" /></a></li>
				<li><a href="#"><s:text name="hrmgr_salary_set" /></a></li>
				<li><a href="#"><s:text name="hrmgr_salary_set_salaryProjectOption" /></a></li>
				<li><a href="#"><s:text name="hrmgr_salary_set_salaryProjectOption" />编辑</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="order">
	<dl>
		<dt>
			<span class="no_line"> <a href="#" onclick="saveOrUpdateSalaryProjectOption()"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
					src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img
					width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
					src="${vix}/common/img/dt_print.png"></a>
				<div class="ms" style="float: none; display: inline;">
					<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
					<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
					</ul>
				</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/hr/salary/salaryProjectOptionAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
			</span> <strong> <b>辅助项编辑</b> <i></i>
			</strong>
		</dt>
		<dd class="clearfix">
			<div class="order_table">
				<div class="voucher newvoucher">
					<dl>
						<dt>
							<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a>
							</span> <strong>辅助项信息</strong>
						</dt>
						<dd style="display: block;">
							<form id="salaryProjectOptionForm">

								<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
								<s:hidden id="salaryProjectId" name="salaryProjectId" value="%{salaryProjectId}" theme="simple" />

								<table style="border: none;">
									<tr height="40">
										<td width="15%" align="right">名称:&nbsp;</td>
										<td width="35%"><input type="text" id="optionName" name="entityForm.optionName" value="${entity.optionName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
										<td width="15%" align="right">编码:&nbsp;</td>
										<td width="35%"><input type="text" id="optionCode" name="entityForm.optionCode" value="${entity.optionCode}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /> <%--
											<s:radio id="isActive" list="#{\"Y\":\"是\",\"N\":\"否\"}" name="entityForm.isActive" value="%{entity.isActive}" theme="simple"></s:radio>
											onchange="changePubType(this.value);"  --%></td>
									</tr>
									<tr height="40">
										<td align="right">类型:&nbsp;</td>
										<td><s:select list="#{\"BLLX\":\"变值类型\",\"YWDX\":\"业务对象\"}" name="entityForm.optionType" value="%{entity.optionType}" theme="simple"></s:select></td>
										<td align="right"></td>
										<td></td>
									</tr>
									<tr>
										<td align="right">元数据:&nbsp;</td>
										<td colspan="3">
											<%-- <input type="hidden" id="metaDataId" name="metaDataId" value="${metaDataId}"/> --%> <input type="hidden" id="metaDataId" name="entityForm.metaDataId" value="${entity.metaDataId}" /> <%-- <span id="parentTreeName"><s:property value="parentTreeName"/></span> --%> <input type="text" id="metaDataDisplayName"
											name="entityForm.metaDataDisplayName" readonly="readonly" value="${entity.metaDataDisplayName}" /> <input class="btn" type="button" value="选择" onclick="chooseMetaData();" />
										</td>
									</tr>
								</table>
							</form>
						</dd>
					</dl>
				</div>
			</div>
		</dd>

		<%--
			<div class="clearfix" style="background:#FFF;position:relative;">
				<div class="right_menu">
					<ul>
						<li class="current"><a onclick="javascript:tab(2,1,'a',event);">工资项</a></li>
						<!-- <li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)">自定义属性</a></li>-->
					</ul>
				</div>
				<div id="a1" style="width:100%;">
					<table id="dgdA1"></table>
				</div>
			</div>
			 --%>
	</dl>
</div>
<!--oder-->
<div class="sub_menu sub_menu_bot">
	<div class="drop">
		<p>
			<%-- <a href="#" onclick="saveOrUpdateEss();"><span>保存</span></a>
				<a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action');"><span>返回</span></a> --%>
			<!-- <a href="#"><span>编辑</span></a>
				<a href="#"><span>复制</span></a>
				<a href="#"><span>删除</span></a>
				<a href="#"><span>关闭并新建</span></a>
				<a href="#"><span>关闭</span></a> -->
		</p>
	</div>
</div>
<!--submenu-->
<!-- content -->