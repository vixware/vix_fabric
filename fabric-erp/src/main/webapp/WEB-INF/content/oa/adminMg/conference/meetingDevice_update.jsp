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
	
	//默认选择设备状态为可用
	if($('input:radio[name=deviceStatus]:checked').length==0){
		$('input:radio[name=deviceStatus]:first').trigger('click');
	}
	
	//默认选择设备同设备
	if($('input:radio[name=similarEquipment]:checked').length==0){
		$('input:radio[name=similarEquipment]:first').trigger('click');
	}
	
	
	/** 保存会议室设备 */
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/meetingDeviceAction!saveOrUpdate.action',
				{
				'meetingDevice.id' : $("#id").val(),
				'meetingDevice.deviceStatus':$('input:radio[name=deviceStatus]').val(),
				'meetingDevice.similarEquipment':$('input:radio[name=similarEquipment]').val(),
				'meetingDevice.deviceName' : $("#deviceName").val(),
				'meetingDevice.deviceCode' : $("#deviceCode").val(),
				'meetingDevice.deviceDescribe' : $("#deviceDescribe").val(),
				'meetingDevice.meetingRequest.id':$("#meetingRequestId").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/meetingDeviceAction!goList.action?menuId=menuContract');
				} 
			);
		}
	}
	
	/** 保存并新增会议室设备 */
	function saveOrAdd(){
		$.post('${vix}/oa/meetingDeviceAction!saveOrUpdate.action',
		   {
			'meetingDevice.id' : $("#id").val(),
			'meetingDevice.deviceStatus':$('input:radio[name=deviceStatus]').val(),
			'meetingDevice.similarEquipment':$('input:radio[name=similarEquipment]').val(),
			'meetingDevice.deviceName' : $("#deviceName").val(),
			'meetingDevice.deviceCode' : $("#deviceCode").val(),
			'meetingDevice.deviceDescribe' : $("#deviceDescribe").val(),
			'meetingDevice.meetingRequest.id':$("#meetingRequestId").val()
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/oa/meetingDeviceAction!goSaveOrUpdate.action');
			} 
		);
	}
		
	function chooseParentCategory(){
		$.ajax({
			  url:'${vix}/oa/meetingDeviceAction!goChooseCategory.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择父分类",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(",");
									if(result[0] == $("#id").val()){
										asyncbox.success("不允许引用自身为父分类!","提示信息");
									}else{
										$("#parentId").val(result[0]);
										$("#categoryName").html(result[1]);
									}
								}else{
									$("#parentId").val("");
									$("#categoryName").html("");
									asyncbox.success("请选择分类信息!","提示信息");
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
				<li><a href="#"><img src="img/mdm_meetingRequest.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action?pageNo=${pageNo}');">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action?pageNo=${pageNo}');">会议申请安排</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action?pageNo=${pageNo}');">会议设备管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action?pageNo=${pageNo}');">新增会议设备管理</a></li>
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
						onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="会议室设备" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">父分类:&nbsp;</td>
											<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${meetingDevice.parentCategory.id}" /> <span id="categoryName"><s:property value="meetingDevice.parentCategory.deviceName" /></span> <span class="btn"><a href="#" onclick="chooseParentCategory();">选择</a></span></td>
										</tr>
										<tr>
											<th align="right">设备编号：</th>
											<td><input id="deviceCode" name="deviceCode" value="${meetingDevice.deviceCode}" type="text" size="20" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
											<th align="right">设备名称/型号：</th>
											<td><input id="deviceName" name="deviceName" value="${meetingDevice.deviceName}" type="text" size="20" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">设备状态：</th>
											<td><s:radio list="#{'0':'可用','1':'不可用'}" id="deviceStatus" name="deviceStatus" value="%{meetingDevice.deviceStatus}" theme="simple"></s:radio></td>
											<th align="right">同类设备：</th>
											<td><s:radio list="#{'0':'没有','1':'有'}" id="similarEquipment" name="similarEquipment" value="%{meetingDevice.similarEquipment}" theme="simple"></s:radio></td>
										</tr>
										<%-- <tr>
											<th align="right">所属会议室：</th>
											<td>
												<s:select id="meetingRequestId" headerKey="-1" headerValue="--请选择--" 
													list="%{meetingRequestList}" listValue="name" listKey="id" 
													value="%{meetingDevice.meetingRequest.id}" multiple="false" theme="simple">
												</s:select>
											</td>	
										</tr> --%>
										<tr>
											<th align="right">设备描述：</th>
											<td colspan="3"><textarea maxlength="30" id="deviceDescribe" name="deviceDescribe" class="example" rows="2" style="width: 520px; height: 139px;">${meetingDevice.deviceDescribe }</textarea>（最多输入30个字）</td>
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







