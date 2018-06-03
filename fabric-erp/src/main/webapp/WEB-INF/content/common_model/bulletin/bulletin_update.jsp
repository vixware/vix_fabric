<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet"/>
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet"/>
<link href="${vix}/common/css/newservice.css" rel="stylesheet"/>
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script> --%>
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
	/** input 获取焦点input效果绑定  */
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
 
$("#order").validationEngine();
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


/**
 * 变更选择发布类型
 */
function changePubType(pubTypeValue){
	clearPubType();
}

/**
 * 清空选择对象
 */
function clearPubType(){
	$("#pubIds").val("");
	$("#pubNames").val("");
}

/**
 * 添加发布对象
 */
function addBulletinPubobject(){
	
	var pubTypeVal = $("input[name=entityForm.pubType]:checked").val();
	//debugger;
	if(pubTypeVal=="O"){
		chooseBulletinOrgUnit($("#pubIds").val());
	}else if(pubTypeVal=="R"){
		chooseBulletinRole($("#pubIds").val());
	}else if(pubTypeVal=="E"){
		chooseBulletinEmp($("#pubIds").val());
	}
}


/**
 * 选择部门
 */
function chooseBulletinOrgUnit(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		  data:{checkedObjIds:checkObjIds,chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择部门",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var resObj = $.parseJSON(returnValue);
								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								/* if(resObj.length == 0 ){
									return;
								} */
								//debugger;
								for(var i=0;i<resObj.length;i++){
									//alert(resObj[i].treeId+"--"+resObj[i].name);
									/* if(resObj[i].treeType!="O"){
										asyncbox.alert("只能选择部门信息！","提示");
										return;
									} */
									if(!pubIdTmp.contains(resObj[i].treeId+",")){
										selectIds += "," + resObj[i].treeId;
										selectNames += "," + resObj[i].name;
									}
									
								}
								
								selectIds = $("#pubIds").val()+selectIds;
								selectNames = $("#pubNames").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#pubNames").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


/**
 * 选择角色
 */
function chooseBulletinRole(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectRoleAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 760,
					height : 520,
					title:"选择角色",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";

								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								/* if(resObj.length == 0 ){
									return;
								} */
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										if(!pubIdTmp.contains(v[0]+",")){
											selectIds += "," + v[0];
											selectNames += "," + v[1];
										}
									}
								}
								
								selectIds = $("#pubIds").val()+selectIds;
								selectNames = $("#pubNames").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#pubNames").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}



/**
 * 选择人员
 */
function chooseBulletinEmp(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								/* if(resObj.length == 0 ){
									return;
								} */
								//debugger;
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										if(!pubIdTmp.contains(v[0]+",")){
											selectIds += "," + v[0];
											selectNames += "," + v[1];
										}
									}
								}
								
								selectIds = $("#pubIds").val()+selectIds;
								selectNames = $("#pubNames").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#pubNames").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

/**
 * 选择公司的岗位
 
function chooseParentOrgPosition(orgId){
	$.ajax({
		  url:'${vix}/hr/position/orgPositionAction!goChoosePosition.action',
		  data:"orgId="+orgId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择上级",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#entityId").val()){
									asyncbox.success("不允许引用岗位为父岗位!","提示信息");
								}else{
									$("#parentPosId").val(result[0]);
									$("#parentPosName").val(result[1]);
								}
							}else{
								$("#parentPosId").val("");
								$("#parentPosName").val("");
								asyncbox.success("请选择岗位信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
*/
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i> <strong><img alt="" src="img/drp_channel.png">公告通知</strong>
	</h2>
</div>
<div class="content">
	<form id="bulletinForm">

		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateBulletin();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <%-- <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png"/></a> --%> <a onclick="loadContent('${vix}/common/model/bulletinAction!goList.action');"
						href="#"><img width="22" height="22" alt="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="打印" src="${vix}/common/img/dt_print.png"></a> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a>
					</span> <strong> <b>编辑公告通知</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table class="table-padding020">
							<!-- <tr height="50">
								<td class="tr"><select name="">
										<option value="">行政</option>
										<option value="">行政</option>
								</select>
								</td>
								<td><input name="" type="text" class="ipt"
									style="width: 440px" />
								</td>
							</tr> -->
							<tr>
								<td class="tr">按类型发布：</td>
								<td><input type="hidden" name="entityForm.id" value="${entity.id}" /> <s:radio id="bulletinPubType" list="#{\"O\":\"部门\",\"R\":\"角色\",\"E\":\"人员\"}" name="entityForm.pubType" value="%{entity.pubType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <br /> <textarea rows="6" cols="6" id="pubNames"
										name="entityForm.pubNames" style="width: 50%">${entity.pubNames}</textarea> &nbsp;&nbsp; <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img src="img/icon_25.gif" />添加</a>&nbsp;&nbsp; <a href="#"><img src="img/delete.gif" onclick="clearPubType()" />清空</a> <input type="hidden" id="pubIds" name="entityForm.pubIds"
									value="${entity.pubIds}" /></td>
							</tr>
							<tr>
								<td class="tr">有效期：</td>
								<td><input type="text" name="entityForm.activeStartDate" value="<s:date name='entity.activeStartDate' format='yyyy-MM-dd HH:mm:ss'/>" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">至 <input type="text" name="entityForm.activeEndDate"
									value="<s:date name='entity.activeEndDate' format='yyyy-MM-dd HH:mm:ss'/>" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">为空为手动终止</td>
							</tr>
							<tr>
								<td class="tr">事物提醒：</td>
								<td><label><input name="entityForm.sendMsg" type="checkbox" value="true" <s:if test="%{entity.sendMsg}">checked="checked"</s:if> class="checkbox" /> 插入事物提醒消息</label></td>
							</tr>
							<tr>
								<td class="tr">置顶：</td>
								<td><label><input name="entityForm.onTop" type="checkbox" value="true" <s:if test="%{entity.onTop}">checked="checked"</s:if> class="checkbox" /> 使公告通知置顶，显示为重要</label> &nbsp;&nbsp; <input name="entityForm.onTopDay" value="${entity.onTopDay}" type="text" class="ipt" style="width: 36px;" /> 天后结束置顶，0表示一直置顶</td>
							</tr>
							<tr>
								<td class="tr">内容简介：</td>
								<td><input id="bulletinTitle" name="entityForm.title" value="${entity.title}" type="text" class="ipt" maxlength="30" style="width: 350px;" /> （最多输入30个字）</td>
							</tr>
							<!-- <tr>
								<td class="tr">附件与权限：</td>
								<td><label> <input name="" type="checkbox" value=""
										class="checkbox" /> 允许下载office附件</label> <label> <input
										name="" type="checkbox" value="" class="checkbox" />
										允许打印office附件</label> <span class="gray">(都不选中，则只能阅读内容)</span></td>
							</tr> -->
							<tr>
								<th align="right">通知内容：</th>
								<td colspan="3"><textarea id="bulletinContentView"></textarea> <input id="bulletinContentHidden" type="hidden" name="entityForm.content" value="${entity.content}"> <%--<textarea id="bulletinContent" name="entityForm.content"></textarea> --%> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
									<script type="text/javascript">
											var bulletinEditor = KindEditor
													.create(
															'#bulletinContentView',
															{
																basePath : '${vix}/plugin/KindEditor/',
																width : 600,
																height : 200,
																cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
																uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
																fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
																allowFileManager : true
															});
											bulletinEditor.html("${entity.content}");
										</script></td>
							</tr>
						</table>

					</div>
				</dd>
			</dl>
		</div>
		<!--oder  textarea[name="content"]-->
		<div class="sub_menu sub_menu_bot">
			<div class="drop">
				<p>
					<a href="#" onclick="saveOrUpdateBulletin()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/model/bulletinAction!goList.action');"><span>返回</span></a>
					<!-- <a href="#"><span>编辑</span></a>
				<a href="#"><span>复制</span></a>
				<a href="#"><span>删除</span></a>
				<a href="#"><span>关闭并新建</span></a>
				<a href="#"><span>关闭</span></a>
				<a href="#" id="text"><span>弹出窗口测试</span></a> -->
				</p>
			</div>
		</div>
		<!--submenu-->
	</form>
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
