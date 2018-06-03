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
function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	
	
	
	/** 投票 */
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/votingManagementAction!saveOrUpdate.action',
				{
				'votingManagement.id' : $ ("#id").val () ,
				'votingManagement.title' : $ ("#title").val () ,
				'votingManagement.pubNames':$("#pubNames").val(),
				'votingManagement.pubType':$('input:radio[name=pubType]').val(),
			    'votingManagement.votingType':$('input:radio[name=votingType]').val(),
				'votingManagement.voteType':$('input:radio[name=voteType]').val(),
				'votingManagement.anonymity':$('input:radio[name=anonymity]').val(),
				'votingManagement.publishType':$('input:radio[name=publishType]').val(), 
				'votingManagement.voteDescribe' : $ ("#voteDescribe").val () ,
				'votingManagement.startTime' : $ ("#startTime").val () ,
				'votingManagement.endTime' : $ ("#endTime").val () ,
				'votingManagement.remarks' : $ ("#remarks").val () 
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/votingManagementAction!goList.action?menuId=menuContract');
				} 
			);
		}
	}
	
	/** 保存并新增投票 */
	function saveOrAdd(){
		$.post('${vix}/oa/votingManagementAction!saveOrUpdate.action',
		   {
			'votingManagement.id' : $ ("#id").val () ,
			'votingManagement.title' : $ ("#title").val () ,
			'votingManagement.pubNames':$("#pubNames").val(),
			'votingManagement.pubType':$('input:radio[name=pubType]').val(),
		    'votingManagement.votingType':$('input:radio[name=votingType]').val(),
			'votingManagement.voteType':$('input:radio[name=voteType]').val(),
			'votingManagement.anonymity':$('input:radio[name=anonymity]').val(),
			'votingManagement.publishType':$('input:radio[name=publishType]').val(), 
			'votingManagement.voteDescribe' : $ ("#voteDescribe").val () ,
			'votingManagement.startTime' : $ ("#startTime").val () ,
			'votingManagement.endTime' : $ ("#endTime").val () ,
			'votingManagement.remarks' : $ ("#remarks").val () 
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/oa/votingManagementAction!goSaveOrUpdate.action');
			} 
		);
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
		
		var pubTypeVal = $("input[name=votingManagement.pubType]:checked").val();
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
			 url:'${vix}/oa/votingManagementAction!goChooseOrganization.action',
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
											selectIds += "," + resObj[i].treeid;
											selectNames += "," + resObj[i].treename;
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
						width : 560,
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
	
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa_poll_manager.png" alt="" /> <s:text name="oa_xtbg" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/votingManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="行政办公" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/votingManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="投票管理" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/votingManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="子投票" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/votingManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="新建子投票" /> </a></li>
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
						onclick="loadContent('${vix}/oa/votesDetailsAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="子投票管理" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">标题：</th>
											<td><input id="title" name="title" value="${votingManagement.title}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">投票描述：</th>
											<td colspan="3"><textarea id="voteDescribe" name="voteDescribe" class="example" rows="2" style="width: 823px; height: 74px;">${votingManagement.voteDescribe }</textarea></td>
										</tr>
										<tr>
											<th align="right">排序号：</th>
											<td><input id="title" name="title" value="${votingManagement.title}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">类型：</th>
											<td><s:radio list="#{'0':'单选','1':'多选','2':'文本输入'}" id="votingType" name="votingType" value="%{votingManagement.votingType}" theme="simple"></s:radio></td>
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







