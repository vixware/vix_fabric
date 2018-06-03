<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
var chart; 
$(document).ready(function(){
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'container', 
            defaultSeriesType: 'line', //图表类别，可取值有：line、spline、area、areaspline、bar、column等 
            marginRight: 130, 
            marginBottom: 25
        },
        title: {
            text: '招标项目[${purchaseTender.title }]报价监控',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: ['第一次报价', '第二次报价', '第三次报价', '第四次报价', '第五次报价', '第六次报价',
                '第七次报价', '第八次报价', '第九次报价', '第十次报价', '第十一次报价', '第十二次报价']
        },
        yAxis: {
            title: {
                text: '报价金额 (RMB:万元)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '万元'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '绿藤花店有限公司',
            data: [50, 55, 80, 105, 130, 155, 180, 205, 230, 255, 280, 300]
        }, {
            name: '南京福禧工艺品厂',
            data: [28, 60, 90, 100, 140, 180, 155, 140, 190, 245, 280, 290]
        }]
    });
    autoRefresh();
});
function autoRefresh(){
	$.ajax({
		url:"${vix}/purchase/quoteMonitorAction!goSaveOrUpdate.action?id=${purchaseTender.id }",
		type:"get"
		});
	setTimeout(function(){
		autoRefresh();
	},10000);
}
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

function resize(){
	$('#test').datagrid('resize', {
		width:700,
		height:400
	});
}
function getSelected(){
	var selected = $('#test').datagrid('getSelected');
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
function getSelections(){
	var ids = [];
	var rows = $('#test').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections(){
	$('#test').datagrid('clearSelections');
}
function selectRow(){
	$('#test').datagrid('selectRow',2);
}
function selectRecord(){
	$('#test').datagrid('selectRecord','002');
}
function unselectRow(){
	$('#test').datagrid('unselectRow',2);
}
function mergeCells(){
	$('#test').datagrid('mergeCells',{
		index:2,
		field:'addr',
		rowspan:2,
		colspan:2
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

pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
}

$("#tenderForm").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_tender.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#">招标管理</a></li>
				<a href="#" onclick="loadContent('${vix}/purchase/quoteMonitorAction!goList.action');">供应商实时报价监控</a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchaseTender.id }" />
<div class="content">
	<form id="tenderForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <!-- 
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
					<a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> 
					-->
					</span>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<div id="container"></div>
								</dd>
							</dl>
						</div>
						<div style="margin: 15px 70px; display: none;">
							<textarea id="content" name="content"></textarea>
							<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
							<script type="text/javascript">
					 var editor = KindEditor.create('textarea[name="content"]',
								{basePath:'${vix}/plugin/KindEditor/',
									width:1100,
									height:300,
									cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
									uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
									fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
									allowFileManager : true 
								}
					 );
					 </script>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />子项目组</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchaseTenderAction!getTenderDetailsJson.action?id=${purchaseTender.id}',onClickRow: onDlClickRow1">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'title',align:'center',width:120">主题</th>
										<th data-options="field:'type',align:'center',width:120">类型</th>
										<th data-options="field:'tenderAgent',align:'center',width:120">竞标地点</th>
										<th data-options="field:'contact',align:'center',width:120">联系人</th>
										<th data-options="field:'telephone',align:'center',width:120">联系电话</th>
										<th data-options="field:'startTime',align:'center',width:120,formatter:formatDatebox">开始时间</th>
										<th data-options="field:'endTime',align:'center',width:120,formatter:formatDatebox">结束时间</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addTenderDetails();"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="editTenderDetails();"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick=""><span class="l-btn-left"><span
										class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlLineItem').datagrid();
							var editIndexDlLineItem = undefined;
							function endDlEditing1(){
								if (editIndexDlLineItem == undefined){return true;}
								if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)){
									$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow1(index){
								if (editIndexDlLineItem != index){
									if (endDlEditing1()){
										$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlLineItem = index;
									} else {
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
									}
								}
							}
							function removeDlLineItem(){
								if (editIndexDlLineItem == undefined){return;}
								$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem)
										.datagrid('deleteRow', editIndexDlLineItem);
								editIndexDlLineItem = undefined;
							}
							function addTenderDetails(){
								var ptId = '${purchaseTender.id }';
								if(null != ptId && "" != ptId){
									$.ajax({
										  url:'${vix}/purchase/purchaseTenderAction!goSaveOrUpdate2.action?isS=0&pId='+ptId,
										  cache: false,
										  success: function(html){
											  $("#mainContent").html(html);
										  }
									});
								}
								else{
									alert("请先保存主项目!");	
								}
							}
							function editTenderDetails(){
								var ptId = '${purchaseTender.id }';
								if(null != ptId && "" != ptId){
									var row = $('#dlLineItem').datagrid("getSelected");
									if(null != row){
										$.ajax({
											  url:'${vix}/purchase/purchaseTenderAction!goSaveOrUpdate2.action?isS=0&pId='+ptId+'&id='+row.id,
											  cache: false,
											  success: function(html){
												  $("#mainContent").html(html);
											  }
										});
									}
									else{
										alert("请选择需要修改的行!");	
									}
								}
								else{
									alert("请先保存主项目!");	
								}
							}
							//为原始Date类型拓展format一个方法，用于日期显示的格式化
							Date.prototype.format = function (format) 
							 {
							     var o = {
							         "M+": this.getMonth() + 1, //month 
							         "d+": this.getDate(),    //day 
							         "h+": this.getHours(),   //hour 
							         "m+": this.getMinutes(), //minute 
							         "s+": this.getSeconds(), //second 
							         "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
							         "S": this.getMilliseconds() //millisecond 
							     }
							     if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
							     (this.getFullYear() + "").substr(4 - RegExp.$1.length));
							     for (var k in o) if (new RegExp("(" + k + ")").test(format))
							         format = format.replace(RegExp.$1,
							       RegExp.$1.length == 1 ? o[k] :
							         ("00" + o[k]).substr(("" + o[k]).length));
							     return format;
							 }
							
							//格式化日期
							function formatDatebox(value) {
						         if (value == null || value == '') {
						             return '';
						         }
						     var dt;
						     if (value instanceof Date) {
						         dt = value;
						     }
						     else {
						         dt = new Date(value);
						         if (isNaN(dt)) {
						             value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
						             dt = new Date();
						             dt.setTime(value);
						         }
						     }
						 
						    return dt.format("yyyy-MM-dd");  //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
						 }
					</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/purchase/purchaseTenderAction!getAttachmentsJson.action?id=${purchaseTender.id}',
						title: '订单附件',
						width: 900,
						height: 'auto',
						fitColumns: true,
						columns:[[
							{field:'id',title:'编号',width:80},
							{field:'name',title:'名称',width:180},
						]],
						toolbar:[{
							id:'saBtnadd',
							text:'添加',
							iconCls:'icon-add',
							handler:function(){
								$('#btnsave').linkbutton('enable');
								$.ajax({
									  url:'${vix}/purchase/purchaseTenderAction!addAttachments.action',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 364,
												height : 160,
												title:"上传附件",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														uploadFile('${vix}/purchase/purchaseTenderAction!uploadAttachments.action?id=${purchaseTender.id}','fileToUpload');
														$('#soAttach').datagrid("reload");
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
						},'-',{
							text:'删除',
							iconCls:'icon-remove',
							handler:function(){
								var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
								//循环所选的行
								for(var i =0;i<rows.length;i++){
									var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
									$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
									$.ajax({url:'${vix}/purchase/purchaseTenderAction!deleteAttachments.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
					});
				</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
				</div>
		</div>
		</dl>
</div>
<!--oder-->
<div class="sub_menu sub_menu_bot">
	<div class="drop" style="display: none;">
		<p>
			<a href="#" onclick="saveOrUpdateOrder()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#" id="text"><span>弹出窗口测试</span></a>
		</p>
		<ul>
			<li><a href="#"><span>帮助</span></a>
				<ul>
					<li><a href="#">帮助</a></li>
					<li><a href="#">帮助</a></li>
					<li><a href="#">帮助</a></li>
					<li><a href="#">帮助</a></li>
				</ul></li>
		</ul>
	</div>
</div>
<!--submenu-->
</form>
</div>
<!-- content -->