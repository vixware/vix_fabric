<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<%-- 
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script> 
 --%>
<%-- 
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>

  --%>
<script type="text/javascript" src="${vix}/plugin/hr/salary/salary.js"></script>

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
	$('#gsinput').keyup(function(e){
		var val = $(this).val();
		if(val == '' && (e.keyCode == 8 || e.keyCode == 46)){
			$('#gs span').last().remove();
			return;
		}
		/* 
		如下代码暂时去掉  不清楚具体的含义
		if((/ $/.test(val) || e.keyCode==13) && val.replace(/(^\s*)|(\s*$)/g, "") != ''){
			$(this).val('');
			$('#gs').append('<span itemCode="s">'+ val.replace(/(^\s*)|(\s*$)/g, "") + '</span>');
			return ;
		}
		val = val.replace(/(^\s*)|(\s*$)/g, "");
		
		if(/\+|\-|\*|\/|\<|\>|\=|\(|\)/.test(val)){
			$(this).val('');
			if(val.substr(0,val.length-1) !=''){
				$('#gs').append('<span class="text" itemCode="s">'+ val.substr(0,val.length-1)+ '</span>');
			}
			$('#gs').append('<span class="symbol">'+val.substr(val.length-1)+'</span>');
		} */
	});
	$(document).on('click',function(e){
		$('#gs span').removeClass('focus');
		if($(e.target).closest('#gs').length > 0 && e.target.nodeName == 'SPAN'){			
			$(e.target).addClass('focus');
			return ;
		}else if($(e.target).closest('#gsbox').length > 0){
			$('#gsinput').focus();
		}else{
			$('#gs span').removeClass('focus');
		}
	});
	
	$(document).on('keydown',function(e){
		if ($('#gs span.focus').length > 0 && (e.keyCode == 8 || e.keyCode == 46)){
			$('#gs span.focus').remove();
			e.preventDefault();
			return false;
		}
	});
	
	/* 
	$('#param option').click(function(){
		$('#gs').append('<span class="text" itemCode="'+$(this).val()+'">'+ $(this).text() + '</span>');
	});
	 */
	
	
	/* $('.gsbtn button').click(function(){
		$('#gs').append('<span class="text" itemCode="'+$(this).val()+'">'+ $(this).text() + '</span>');
	}); */
	
	$('.normalSymbol').click(clickSalarySymbol);
	$('.ifSymbol').click(clickSalarySymbol);
	$('.valueSymbol').click(clickSalaryValueSymbol);//
	
});
	
	/**
	  *  不显示名称  只追加内容
	  *  如换行
	  */
	function clickSalaryValueSymbol(){
		//$('#gs').append('<span class="text" itemCode="'+$(this).val()+'">'+ $(this).text() + '</span>');
		//$('#gs').append('<span class="text" itemCode="'+$(this).val()+'">'+ $(this).text() + '</span>');
		//var inserHtml = "<span class='text' dataType='normalSymbol'>"+ $(this).text() +"</span>";
		//var inserHtml = $(this).text();
		var insertHtml = $(this).val();
		$('#gs').append(insertHtml);
	}

	
	
	/**
	 * 保存公式
	 */
	function saveOrUpdateSalaryProjectModFormula(){
			
		//$("#entityFormFormular").val($("#formular").val());
		
		var formularHtml = $('#gs').html();
		if(formularHtml!=""){
			//alert(formularHtml);
			//alert(html_decode(formularHtml));
			$("#entityFormFormular").val(html_decode(formularHtml));
			//$("#entityFormFormular").val(encodeURIComponent(formularHtml));
			var queryString = $('#salaryProjectItemModFormularForm').formSerialize(); 
			
			$.post('${vix}/hr/salary/salaryProjectItemModAction!saveOrUpdateFormular.action',
				queryString,
				function(result){
					showMessage(result);
					setTimeout("", 1000);
				}
			 );
		}
		
		//alert($('#gs').html());
		/* 
		$.post('${vix}/hr/salary/salaryProjectItemModAction!saveOrUpdateFormular.action',
			queryString,
			function(result){
				showMessage(result);
				setTimeout("", 1000);
			}
		 );
		 */
	}
	
	/* if(action == 'ok'){
	loadContent('${vix}/hr/salary/salaryProjectItemModAction!goList.action');
	} */

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
						onClick: onSalClick
					}
				};
				function onSalClick(event, treeId, treeNode, clickFlag) {
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
						<span class="no_line"> <!-- <a href="#" onclick="checkSalaryProjectModFormula();"><img width="22" height="22" alt="验证公式">验证</a> --> <a href="#" onclick="saveOrUpdateSalaryProjectModFormula();"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增"
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
									<dd style="display: block; height: auto;">
										<table width="100%" class="gstable">
											<tr>
												<td style="text-align: right; vertical-align: top; width: 50px;">公式:&nbsp;</td>
												<td>
													<div id="gsbox" style="width: 615px;">
														<span id="gs"><s:property value="entity.formular" escapeHtml="false" /></span> <input id="gsinput" name="" style="background: none; border: none; box-shadow: none;" />
													</div> <input type="button" value="插入工资项" class="btn" onclick="selectSalaryProjectItem();">
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<div class="gsbox" style="width: 400px;">
														<div class="gsbtn">
															<div>
																<button type="button" class="normalSymbol" value="7">7</button>
																<button type="button" class="normalSymbol" value="8">8</button>
																<button type="button" class="normalSymbol" value="9">9</button>
																<button type="button" class="normalSymbol" value="/">/</button>
																<button type="button" class="normalSymbol" value="(">(</button>
															</div>
															<div>
																<button type="button" class="normalSymbol" value="4">4</button>
																<button type="button" class="normalSymbol" value="5">5</button>
																<button type="button" class="normalSymbol" value="6">6</button>
																<button type="button" class="normalSymbol" value="*">*</button>
																<button type="button" class="normalSymbol" value=")">)</button>
															</div>
															<div>
																<button type="button" class="normalSymbol" value="1">1</button>
																<button type="button" class="normalSymbol" value="2">2</button>
																<button type="button" class="normalSymbol" value="3">3</button>
																<button type="button" class="normalSymbol" value="-">-</button>
																<button type="button" class="normalSymbol" value="+">+</button>
															</div>
															<div>
																<button type="button" class="normalSymbol" value="0">0</button>
																<button type="button" class="normalSymbol" value=".">.</button>
																<button type="button" class="valueSymbol" value=";<br/>">结尾换行</button>
																<!-- <button type="button" class="normalSymbol" value="++">基增和</button>
															    	<button type="button" class="normalSymbol" value="--">基减和</button> -->
															</div>
														</div>
														<p>关系符号：</p>
														<div class="gsbtn">
															<div>
																<button type="button" class="normalSymbol" value="<"><</button>
																<button type="button" class="normalSymbol" value=">">></button>
																<button type="button" class="normalSymbol" value="=">=</button>
																<button type="button" class="ifSymbol">if</button>
																<button type="button" class="ifSymbol">else</button>
																<button type="button" class="ifSymbol">endif</button>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</table>
										<form id="salaryProjectItemModFormularForm">
											<%-- <input id="entityFormFormular" name="formular" type="hidden" value='${entity.formular}'> --%>
											<s:hidden id="entityFormFormular" name="formular" value="%{entity.formular}" theme="simple"></s:hidden>
											<s:hidden name="id" value="%{entity.id}" theme="simple" />
										</form>
										<%-- 
											<div id="gsbox" style="width:615px;">
												<span id="gs"></span>
												<input id="gsinput" name="" style="background:none; border:none; box-shadow:none;"/>
											</div>
											
											
											<div class="gsbox" style="width:400px;">
												<div class="gsbtn">
										    		<div><button type="button" value="7">7</button>
										    			<button type="button" value="8">8</button>
												    	<button type="button" value="9">9</button>
												    	<button type="button" value="/">/</button>
												    	<button type="button" value="（">(</button>
												    </div>
												    <div>
												    	<button type="button" value="4">4</button>
										    			<button type="button" value="5">5</button>
												    	<button type="button" value="6">6</button>
												    	<button type="button" value="*">*</button>
												    	<button type="button" value="）">)</button>
												    </div>
										    		<div>
										    			<button type="button" value="1">1</button>
												    	<button type="button" value="2">2</button>
												    	<button type="button" value="3">3</button>
												    	<button type="button" value="-">-</button>
												    	<button type="button" value="+">+</button>
												    </div>
										    		<div>
										    			<button type="button" value="0">0</button>
												    	<button type="button" value=".">.</button>
												    	<button type="button" value="++">基增和</button>
												    	<button type="button" value="--">基减和</button>
												    </div>
									   			 </div>
											    <p>关系符号：</p>
											    <div class="gsbtn">
											    	<button type="button" value="<">&lt;</button>
											    	<button type="button" value=">">&gt;</button>
											    	<button type="button" value="=">=</button>
											    	<button type="button" value="iif">iif</button>
											    	<button type="button" value="floor">floor</button>
											    </div>
											</div>
											 --%>
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