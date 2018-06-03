<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%-- <script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script> --%>
<%-- <link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet"/>
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet"/>
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script> --%>

<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>

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
	
	
	$(function(){
		loadChargeOrgUnitGrid();
		loadChargeOrgGrid();
	});
	
	
	/**
	 *  分管部门列表
	 */
	 
	function loadChargeOrgUnitGrid(){
		$('#dgdB1').datagrid({
			//title:'',
			iconCls:'icon-save',
			width:800,
			height:350,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/org/organizationChargeOfAction!goChargeOfOrgUnitList.action?empId=${empId}',
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'fs',title:'简称',width:200}
			]],
			columns:[[
				{field:'fullName',title:'全称',width:120},
				{field:'orgCode',title:'编码 ',width:150},
				{field:'unitType',title:'部门类型 ',width:80,
					formatter:function(value){
						if(value=='JZBM'){
							return "基准部门";
						}else if(value=='XSBGS'){
							return "销售办公室";
						}else if(value=='XSZ'){
							return "销售组";
						}else if(value=='XSZZ'){
							return "销售组织";
						}else{
							return '';
						}
					}	
				}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					addChargeOfOrganizationUnit();
				}
			},{
				id:'btncutA1',
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					//$('#btnsave').linkbutton('enable');
					//alert('cut')
					var idVal = getDataGridId("dgdB1");
					if(idVal!=''){
						deleteChargeOfEmp('O',idVal);
					}
					
				}
			}]
		});
	}	


	/**
	 * 分管单位列表
	 */
	function loadChargeOrgGrid(){
		
		//$('#dgdB2').datagrid({
		$('#dgdB2').datagrid({
			//title:'',
			iconCls:'icon-save',
			width:800,
			height:250,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/org/organizationChargeOfAction!goChargeOfOrgList.action?empId=${empId}',
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'briefName',title:'简称',width:200}
			]],
			columns:[[
				{field:'orgName',title:'全称',width:120},
				{field:'companyCode',title:'编码 ',width:150},
				{field:'orgType',title:'公司类型 ',width:80,
					formatter:function(value){
						if(value=='jtgs'){
							return "集团公司";
						}else if(value=='gs'){
							return "公司";
						}else{
							return '';
						}
					}	
				}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					addChargeOfOrganization();
				}
			},{
				id:'btncutA1',
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					//$('#btnsave').linkbutton('enable');
					//alert('cut')
					var idVal = getDataGridId("dgdB2");
					if(idVal!=''){
						deleteChargeOfEmp('C',idVal);
						
						//////
					}
					
				}
			}]
		});
	}	


	function getDataGridId(dgId){
		var res = '';
		var rows = $('#'+dgId).datagrid('getSelected');
		if(rows){
			res = rows.id;
		}else{
			showMessage("只能操作一条数据!");
			setTimeout("", 1000);
			return ;
		}
		return res;
		
	}
	
	
	/**
	 * 添加公司
	 */
	function addChargeOfOrganization(){
		$.ajax({
			  url:'${vix}/common/org/organizationAction!goChooseOrganization.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择分管公司",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(",");
									//asyncbox.success("不允许引用本公司为父公司!","提示信息");
									
									//$("#parentId").val(result[0]);
									//$("#organizationName").val(result[1]);
									asyncbox.confirm('确定要添加分管的公司么?','提示信息',function(action){
										if(action == 'ok'){
											//alert(result[0]+" #---------# "+result[1]);
											saveChargeOfEmp('C',result[0],'');

										}
									});
								}else{
									//$("#parentId").val("");
									//$("#organizationName").val("");
									showMessage("请选择公司信息!");
									setTimeout("", 1000);
									return false;
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});		
	}
	
	/**
	 * 添加部门
	 */
	function addChargeOfOrganizationUnit(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  data:{chkStyle:"checkbox",canCheckComp:0},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择分管部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									
									for(var i=0;i<resObj.length;i++){
										selectIds += "," + resObj[i].treeId;
										selectNames += "," + resObj[i].name;
									}
									//selectIds = $("#pubIds").val()+selectIds;
									//selectNames = $("#pubNames").val()+selectNames;
									asyncbox.confirm('确定要添加分管的部门么?','提示信息',function(action){
										if(action == 'ok'){
											//alert(selectIds+"#        #"+selectNames);
											saveChargeOfEmp('O','',selectIds);
										}
									});
									//$("#pubIds").val(selectIds);
									//selectNames = selectNames.substring(1,selectNames.length);
									//$("#pubNames").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**
	 * 分管公司和部门的保存
	 */
	function saveChargeOfEmp(orgType,orgId,orgUnitIds){
		var queryString = "empId=${empId}";
		queryString += "&orgType=" + orgType + "&orgId=" + orgId + "&orgUnitIds="+orgUnitIds;
		$.post('${vix}/common/org/organizationChargeOfAction!saveOrUpdate.action',
			queryString,
			function(result){
				showMessage(result);
				setTimeout("", 1000);
				if(orgType=='C'){
					loadChargeOrgGrid();
				}else{
					loadChargeOrgUnitGrid();
				}
			}
		 );
	
	}
	
	/**
	 * 删除分管信息
	 */
	function deleteChargeOfEmp(orgType,orgOrUnitId){
		asyncbox.confirm('确定要删除么?','提示信息',function(action){
			if(action == 'ok'){
				var queryString = "empId=${empId}&orgType=" +orgType+ "&orgOrUnitId="+orgOrUnitId;
				$.post('${vix}/common/org/organizationChargeOfAction!deleteById.action',
					queryString,
					function(result){
						showMessage(result);
						setTimeout("", 1000);
						if(orgType=='C'){
							loadChargeOrgGrid();
						}else{
							loadChargeOrgUnitGrid();
						}
					}
				 );
			}
		});
		
	}
	
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
				<li><a href="#"><img src="img/hr_emp.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action');">员工管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action');">员工信息管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goSaveOrUpdate.action');">职员管理</a></li>
				<li><a href="#">分管单位部门</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="order">
	<dl>
		<dt>
			<span class="no_line"> <%-- <a href="#" onclick="saveOrUpdateMetaData()"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a>
					<a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png"/></a>
					<a href="#"><img width="22" height="22" alt="取消" src="${vix}/common/img/dt_cancelback.png"/></a>
					<a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png"/></a>
					<a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png"/></a>
					<a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png"/></a>
					<a href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a>
					<a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a>
					<a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a>
					<a href="#"><img width="22" height="22" alt="打印" src="${vix}/common/img/dt_print.png"></a>
					<div class="ms" style="float:none;display:inline;"><a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
                        <ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                        </ul>
                    </div>
					<a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> --%> <a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
			</span> <strong> <b>分管单位部门编辑</b> <i></i>
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
							</span> <strong>分管信息</strong>
						</dt>
						<dd style="display: none;"></dd>
					</dl>
				</div>
			</div>
		</dd>

		<div class="clearfix" style="background: #FFF; position: relative;">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="javascript:tab(2,1,'b',event);">分管部门</a></li>
					<li><a onclick="javascript:$('#b2').attr('style','');tab(2,2,'b',event)">分管单位</a></li>
				</ul>
			</div>
			<div id="b1" style="width: 100%;">
				<table id="dgdB1"></table>
			</div>
			<div id="b2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<table id="dgdB2"></table>
			</div>
		</div>
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