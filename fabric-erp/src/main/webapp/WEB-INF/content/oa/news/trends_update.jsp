<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
    //加载发布时间(新增)
    if (document.getElementById("createTime").value == "") {
	    var myDate = new Date();
	    $("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
    }
    //默认选择部门
    if($('input:radio[name=pubType]:checked').length==0){
    	$('input:radio[name=pubType]:first').trigger('click');
    }
    //默认选择文本
    if($('input:radio[name=newsType]:checked').length==0){
    	$('input:radio[name=newsType]:first').trigger('click');
    }
    //默认选择实名评论
    if($('input:radio[name=review]:checked').length==0){
    	$('input:radio[name=review]:first').trigger('click');
    }
    //默认选择发布
    if($('input:radio[name=isPublish]:checked').length==0){
    	$('input:radio[name=isPublish]:first').trigger('click');
    }
    
    //默认选择不置顶
    if($('input:radio[name=isTopTrends]:checked').length==0){
    	$('input:radio[name=isTopTrends]:first').trigger('click');
    }
});

function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	
	
	
	/** 新闻 */
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/share/trendsAction!saveOrUpdate.action',
				{
				'trends.id' : $("#id").val(),
				'trends.title':$("#title").val(),						  
				'trends.subtitle':$("#subtitle").val(),						  
				'trends.createTime':$("#createTime").val(),	
				'trends.keyContent':$("#keyContent").val(),	
				'trends.bizOrgNames':$("#bizOrgNames").val(),	
				'trends.pubType':$('input:radio[name=pubType]:checked').val(),
				'trends.isTop':$('input:radio[name=isTopTrends]:checked').val(),
				'trends.pubIds' : $("#pubIds").val(),
				'trends.content' : contents.html(),
				'trends.keywords':$("#keywords").val(),						  
				'trends.sourceFrom':$("#sourceFrom").val(),	
				'trends.newsType':$('input:radio[name=newsType]:checked').val(),		  
				'trends.isPublish':$('input:radio[name=isPublish]:checked').val(),		  
				'trends.review':$('input:radio[name=review]:checked').val(),		  
				'trends.sourceFromUrl':$("#sourceFromUrl").val()	
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/share/trendsAction!goList.action?menuId=menuContract');
				} 
			);
		}else {
			return false;
		}
	}
	
	/** 保存并新增新闻 */
	function saveOrAdd(){
		$.post('${vix}/oa/trendsAction!saveOrUpdate.action',
		   {
			'trends.id' : $("#id").val(),
			'trends.title':$("#title").val(),						  
			'trends.subtitle':$("#subtitle").val(),						  
			'trends.createTime':$("#createTime").val(),	
			'trends.keyContent':$("#keyContent").val(),	
			'trends.bizOrgNames':$("#bizOrgNames").val(),	
			'trends.pubType':$('input:radio[name=pubType]:checked').val(),
			'trends.isTop':$('input:radio[name=isTopTrends]:checked').val(),
			'trends.pubIds' : $("#pubIds").val(),
			'trends.content' : contents.html(),
			'trends.keywords':$("#keywords").val(),						  
			'trends.sourceFrom':$("#sourceFrom").val(),	
			'trends.newsType':$('input:radio[name=newsType]:checked').val(),		  
			'trends.isPublish':$('input:radio[name=isPublish]:checked').val(),		  
			'trends.review':$('input:radio[name=review]:checked').val(),		  
			'trends.sourceFromUrl':$("#sourceFromUrl").val()	
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/oa/trendsAction!goSaveOrUpdate.action');
			} 
		);
	}
	
	$("#orderForm").validationEngine();
		
	/**
	 * 添加发布对象
	 */
	function addBizOrgObject(){
		
		var pubTypeVal = $("input[name=projectManagement.pubType]:checked").val();
		if(pubTypeVal=="O"){
			chooseBizOrgUnit($("#pubIds").val());
		}else if(pubTypeVal=="R"){
			chooseBizRole($("#pubIds").val());
		}else if(pubTypeVal=="E"){
			chooseBizEmp($("#pubIds").val());
		}
	}

	/**
	 * 清空选择对象
	 */
	function clearBizOrgType(){
		$("#pubIds").val("");
		$("#bizOrgNames").val("");
	}

	/**
	 * 变更选择发布类型
	 */
	function changeBizOrgType(typeValue){
		clearBizOrgType();
	}
	
	/**
	 * 选择部门
	 */
	 function chooseBizOrgUnit(){
			$.ajax({
				  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
				  data:{chkStyle:"checkbox",canCheckComp:0},
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
										
										for(var i=0;i<resObj.length;i++){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}

										if(selectIds!=''){
											selectIds = selectIds.substring(1);
											selectNames = selectNames.substring(1);
											//alert(selectIds)
											$("#pubIds").val(selectIds);
											$("#bizOrgNames").val(selectNames);
										}
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
	 function chooseBizRole(checkObjIds){
			$.ajax({
				  url:'${vix}/common/select/commonSelectRoleAction!goList.action',
				  data:{chkStyle:"checkbox"},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 560,
							height : 340,
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
										selectNames = $("#bizOrgNames").val()+selectNames;
										
										$("#pubIds").val(selectIds);
										selectNames = selectNames.substring(1,selectNames.length);
										$("#bizOrgNames").val(selectNames);
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
	 function chooseBizEmp(checkObjIds){
			$.ajax({
				  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
				  data:{chkStyle:"checkbox"},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 760,
							height : 440,
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
										selectNames = $("#bizOrgNames").val()+selectNames;
										
										$("#pubIds").val(selectIds);
										selectNames = selectNames.substring(1,selectNames.length);
										$("#bizOrgNames").val(selectNames);
									}
									
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		}
	 
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/oa/oa_news.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/trendsAction!goList.action?pageNo=${pageNo}');">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/trendsAction!goList.action?pageNo=${pageNo}');">新闻管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/trendsAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新建新闻" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">发布时间：</th>
											<td><input id="createTime" name="trends.createTime" value="${trends.createTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th align="right">新闻来源：</th>
											<td><input id="sourceFrom" name="sourceFrom" value="${trends.sourceFrom}" type="text" size="26" /></td>
										</tr>
										<tr>
											<th align="right">标题：</th>
											<td><input id="title" name="title" value="${trends.title}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">副标题：</th>
											<td><input id="subtitle" name="subtitle" value="${trends.subtitle}" type="text" size="26" /></td>
										</tr>
										<tr>
											<td class="tr">按类型发布：</td>
											<td colspan="3"><input type="hidden" id="id" name="id" value="${trends.id}" /> <s:radio id="pubType" list="#{'Q':'全部','O':'部门','R':'角色','E':'人员'}" name="pubType" value="%{trends.pubType}" onchange="changeBizOrgType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBizOrgObject()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearBizOrgType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="bizOrgNames" name="bizOrgNames" style="width: 820px; height: 114px;">${trends.bizOrgNames}</textarea> <input type="hidden" id="pubIds" name="trends" value="${trends.pubIds}" /></td>
										</tr>
										<tr>
											<th align="right">新闻类型：</th>
											<td><s:radio list="#{'0':'文本','1':'图片'}" id="newsType" name="newsType" value="%{trends.newsType}" theme="simple"></s:radio></td>
											<th align="right">评论：</th>
											<td><s:radio list="#{'0':'实名评论','1':'匿名评论','2':'禁止评论'}" name="review" value="%{trends.review}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">提醒：</th>
											<td><s:radio list="#{'0':'否','1':'是'}" id="" name="" value="" theme="simple"></s:radio></td>
											<th align="right">置顶：</th>
											<td><s:radio list="#{'0':'否','1':'是'}" id="isTopTrends" name="isTopTrends" value="%{trends.isTop}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">内容简介：</th>
											<td colspan="3"><textarea maxlength="30" id="keyContent" name="keyContent" class="example" rows="2" style="width: 698px; height: 123px;">${trends.keyContent }</textarea>（最多输入30个字）</td>
										</tr>
										<tr>
											<th align="right">状态：</th>
											<td><s:radio list="#{'0':'发布','1':'终止'}" id="isPublish" name="isPublish" value="%{trends.isPublish}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">新闻内容：</th>
											<td colspan="3"><textarea id="content" name="content">${trends.content}</textarea> <script type="text/javascript">
													 var contents = KindEditor.create('textarea[name="content"]',
													{basePath:'${vix}/plugin/KindEditor/',
														width:825,
														height:281,
														cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
														uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
														fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
														allowFileManager : true 
														}
													 );
											 	</script></td>
										</tr>
										<tr>
											<th align="right">关键字：</th>
											<td colspan="3"><textarea id="keywords" name="keywords" class="example" rows="2" style="width: 823px; height: 74px;">${trends.keywords }</textarea></td>
										</tr>
										<tr>
											<th align="right">新闻地址：</th>
											<td colspan="3"><textarea id="sourceFromUrl" name="sourceFromUrl" class="example" rows="2" style="width: 823px; height: 74px;">${trends.sourceFromUrl }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>







