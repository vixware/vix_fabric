<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<%-- 
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script> 
 --%>
<%-- 
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>

  --%>
<link href="${vix}/plugin/ke/themes/default/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="${vix}/plugin/ke/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="${vix}/plugin/ke/lang/zh_CN.js"></script>


<link href="${vix}/plugin/ke/plugins/salaryPlugin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="${vix}/plugin/ke/plugins/salaryPlugin.js"></script>


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
	
	

	/* KindEditor.lang({
		selectOption : '插入项目'
	});
	 */
	

	 
	var itemModFormulaEditor;
	
	$(function(){
		//$("#salaryProjectItemModForm").validationEngine();
		//loadSalaryProjectItemGrid();
		//$("#formular").html("${entity.formular}");
		//alert("vvvv");
		//$("#formular").text("${entity.formular}");
		//<s:property value="entity." escapeHtml="false"/>
		//initKindEditor();
		itemModFormulaEditor = KindEditor.create(
				'#itemModFormulaView', {
					basePath : '${vix}/plugin/ke/',
					width : 1000,
					height : 400,
					newlineTag : 'br',
					filterMode : false,
					//cssPath : ['${vix}/plugin/ke/plugins/code/prettify.css', 'index.css'],
					items : ['selectOption', 
					         'formulaBracketsLeft','formulaBracketsRight','formulaConditionIf',
					         'formulaCpEqual','formulaGt','formulaCpLt','formulaCpNoEqual',
					         'formulaEqual','formulaPlus','formulaMinus','formulaMultiply','formulaDivide',
					         ]
				}); 
		
		//KindEditor.g['#itemModFormulaView']\
		//itemModFormulaEditor.iframeDoc
		/* debugger;
		$(K.g['#itemModFormulaView'].iframeDoc).bind('click',function selectItemContent(e){
		 	alert("");
		 	debugger;
		}); */
	});
	
	/* 
	KindEditor.ready(function(K) {
		$(".salaryItemSelect").live('click',function selectItemContent(e){
		 	alert("");
		 	debugger;
		});
	}); */
	
	
	
	/**
	 * 回调使用
	 */
	function salaryProjectItemSelect(){
		/* var selectCode = $("#selectIdCode").val();
		if(selectCode!=''){
			return selectCode;
		}
		
		return ""; */
		var resArray = new Array();
		var itemType = $("#selectIdItemType").val();
		if(itemType!=''){
			//选择的不是根节点的类型
			var selectItemName = $("#selectIdName").val();
			var selectItemCode = $("#selectIdCode").val();
			
			//return itemType+"-"+selectItemCode+"-"+selectItemName;
			resArray.push(itemType);
			resArray.push(selectItemCode);
			resArray.push(selectItemName);
		}
		
		
		return resArray;
	}
	
	/* 
	KindEditor.lang({
		example1 : '插入HTML'
	});
	KindEditor.plugin('example1', function(K) {
		var self = this, name = 'example1';
		self.clickToolbar(name, function() {
			self.insertHtml('<strong>测试内容</strong>');
		});
	});
	
	var itemModFormulaEditor = KindEditor.create(
			'#itemModFormulaView',
			{
				basePath : '${vix}/plugin/KindEditor/',
				width : 1000,
				height : 400,
				items :[
					'source', '|', 'undo', 'redo', '|','example1'
											        
				        
				   ],
				cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
				uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
				fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
				allowFileManager : true
			});
	 */

	
	
	function initKindEditor(){
		/* 
		var itemModFormulaEditor = KindEditor.create(
				'#itemModFormulaView',
				{
					basePath : '${vix}/plugin/KindEditor/',
					width : 1000,
					height : 400,
					items :[
					        
												        
					        
					   ],
					cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
					uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
					fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
					allowFileManager : true
				});
		 */
		//itemModFormulaEditor.html("a");
		
	}
	
	 
	/**
	 * 验证公式合法
	 */
	function checkSalaryProjectModFormula(){
		var formulaHtml = itemModFormulaEditor.html();
		var formulaText = itemModFormulaEditor.text();
		
		itemModFormulaEditor.sync();
		
		alert(formulaHtml);
		alert(formulaText);
	}

	
	/**
	 * 保存公式
	
	function saveOrUpdateSalaryProjectModFormula(){
		if($('#salaryProjectItemModForm').validationEngine('validate')){
			
			//$("#entityFormFormular").val($("#formular").val());
			var queryString = $('#salaryProjectItemModForm').formSerialize(); 
			$.post('${vix}/hr/salary/salaryProjectItemModAction!saveOrUpdate.action',
				queryString,
				function(result){
					showMessage(result);
					setTimeout("", 1000);
					loadContent('${vix}/hr/salary/salaryProjectItemModAction!goList.action');
				}
			 );
		}
		
	}
	 */
	
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
				<li><a href="#"><s:text name="hrmgr" /></a></li>
				<li><a href="#"><s:text name="hrmgr_salary_set" /></a></li>
				<li><a href="#"><s:text name="hrmgr_salary_set_salaryProjectItemMod" /></a></li>
				<li><a href="#"><s:text name="hrmgr_salary_set_salaryProjectItemMod" />公式编辑</a></li>
			</ul>
		</div>
	</h2>
</div>


<div class="box">
	<div id="left" class="ui-resizable" style="height: 500px;">
		<div id="switch_box" class="switch_btn"></div>
		<%--	
			<div class="left_content" style="height:500px;">
				<div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div> --%>

		<div class="left_content" style="height: 500px;">
			<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div> --%>
			<div id="tree_root" class="ztree" style="padding: 0;"></div>
		</div>
		<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/hr/salary/salaryProjectItemModAction!findTreeToJson.action",
						autoParam:["treeId","salaryItemType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					//debugger;
					var treeCode = treeNode.code;
					if(treeCode!=""){
						$("#selectId").val(treeNode.treeId);
						$("#selectIdTreeId").val(treeNode.tId);
						
						
						$("#selectIdItemType").val(treeNode.salaryItemType);
						$("#selectIdCode").val(treeNode.code);
						$("#selectIdName").val(treeNode.name);
					}else{
						$("#selectId").val("");
						$("#selectIdTreeId").val(treeNode.tId);
						
						
						$("#selectIdItemType").val("");
						$("#selectIdCode").val("");
						$("#selectIdName").val("");
					}
					
					
					//pager("start","${vix}/common/model/baseMetaDataAction!goSingleList.action?categoryId="+treeNode.id+"&metaDataName="+name,'metaDataCategory');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
	</div>
	<input type="hidden" id="selectId" name="selectId" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" /> <input type="hidden" id="selectIdItemType" name="selectIdItemType" /> <input type="hidden" id="selectIdCode" name="selectIdCode" /> <input type="hidden" id="selectIdName" name="selectIdName" />
	<!-- left -->
	<div id="right">
		<div class="right_content">


			<div class="order">
				<dl>
					<dt>
						<span class="no_line"> <a href="#" onclick="checkSalaryProjectModFormula();"><img width="22" height="22" alt="验证公式">验证</a> <a href="#" onclick="saveOrUpdateSalaryProjectModFormula();"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增"
								src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img
								width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条"
								src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印" src="${vix}/common/img/dt_print.png"></a>
							<div class="ms" style="float: none; display: inline;">
								<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
								<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
									<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
									<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
									<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
									<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								</ul>
							</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/hr/salary/salaryProjectItemModAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
						</span> <strong> <b>工资项编辑</b> <i></i>
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
										</span> <strong>工资项信息</strong>
									</dt>
									<dd style="display: block;">

										<textarea id="itemModFormulaView"></textarea>


									</dd>
								</dl>
							</div>
						</div>
					</dd>

				</dl>
			</div>

		</div>
	</div>
	<!-- right -->
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