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
	
/* 出差登记 */
function saveOrUpdateOrder(){
	if($('#orderForm').validationEngine('validate')){
		$.post('${vix}/oa/tripRecordAction!saveOrTripRecord.action',
			{'tripRecord.id':$("#id").val(),									  
			  'tripRecord.approver':$("#approver").val(),						  
			  'tripRecord.businessLocation':$("#businessLocation").val(),						  
			  'tripRecord.businessstartDate':$("#businessstartDate").val(),						  
			  'tripRecord.businessendDate':$("#businessendDate").val(),	
			  'tripRecord.minutes':$("#minutes").val(),						  
			  'tripRecord.dates':$("#dates").val(),
			  'tripRecord.isPublish':$('input:radio[name=isPublish]:checked').val(),
			  'tripRecord.reason' : reasons.html(),
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/oa/personalAttendanceAction!goList.action?menuId=menuContract');
			} 
		);
	}else {
		return false;
	}
}

/** 保存并新增出差 */
function saveOrAdd(){
	if($('#orderForm').validationEngine('validate')){
		$.post('${vix}/oa/tripRecordAction!saveOrTripRecord.action',
			{'tripRecord.id':$("#id").val(),									  
			  'tripRecord.approver':$("#approver").val(),						  
			  'tripRecord.businessLocation':$("#businessLocation").val(),						  
			  'tripRecord.businessstartDate':$("#businessstartDate").val(),						  
			  'tripRecord.businessendDate':$("#businessendDate").val(),	
			  'tripRecord.minutes':$("#minutes").val(),						  
			  'tripRecord.dates':$("#dates").val(),
			  'tripRecord.isPublish':$('input:radio[name=isPublish]:checked').val(),
			  'tripRecord.reason' : reasons.html(),
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/oa/tripRecordAction!goSaveOrTripRecord.action');
			} 
		);
	}
}
	
/**审批人*/
function chooseEmployees(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"选择负责人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
									}
								}
								$("#approver").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
	
	
/* 计算两个日期的间隔天数  */
function calculation() {
	var businessstartDate= $("#businessstartDate").val();
    var businessendDate = $("#businessendDate").val();
    var startTime = new Date(Date.parse(businessstartDate.replace(/-/g, "/")));
    var endTime = new Date(Date.parse(businessendDate.replace(/-/g, "/"))); 
    
   //两时间差为毫秒数，除以1000则转换为秒数
    var leftsecond=parseInt((endTime.getTime()-startTime.getTime())/1000);

      d=parseInt(leftsecond/(3600*24));//计算出相差天数
      h=parseInt((leftsecond/3600)%24);//扣除相差天数，计算出相差小时数
      m=parseInt((leftsecond/60)%60);//扣除相差天数，小时数，计算出相差分钟数
      s=parseInt(leftsecond%60);//扣除相差天数、小时数、分钟数相差数，计算出相差秒速
	  /* document.getElementById("times").innerHTML=d+'天'+h+"小时"+m+"分"+s+"秒"; */
	  $ ("#dates").val (d);
	  $ ("#minutes").val (h);
}

$("#orderForm").validationEngine();
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
				<li><a href="#" onclick="loadContent('${vix}/oa/personalAttendanceAction!goList.action?pageNo=${pageNo}');">个人办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/personalAttendanceAction!goList.action?pageNo=${pageNo}');">个人考勤 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/personalAttendanceAction!goList.action?pageNo=${pageNo}');">出差登记 </a></li>
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
						onclick="loadContent('${vix}/oa/personalAttendanceAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="出差登记" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">负责人：</th>
											<td><input id="approver" name="approver" value="${tripRecord.approver}" type="text" style="width: 200px; height: 22px;" validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择"
												onclick="chooseEmployees();" /></td>
										</tr>
										<tr>
											<th align="right">出差时间：</th>
											<td><input id="businessstartDate" type="text" name="businessstartDate" value="${tripRecord.businessstartDate}" style="width: 200px; height: 22px;" validate[required] text-input" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})"><span style="color: red;">*</span>至 <input id="businessendDate" type="text"
												name="businessendDate" value="${tripRecord.businessendDate}" style="width: 200px; height: 22px;" validate[required] text-input" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})"><span style="color: red;">*</span> <input class="btn" type="button" value="计算天数" onclick="calculation();" /> <input id="dates"
												type="text" name="dates" style="width: 20px;" value="${tripRecord.dates}">天 <input id="minutes" type="text" name="minutes" style="width: 20px;" value="${tripRecord.minutes}">小时</td>
										</tr>
										<tr>
											<th align="right">是否用车：</th>
											<td><s:radio list="#{'0':'是','1':'否'}" name="isPublish" value="%{tripRecord.isPublish}" theme="simple"></s:radio></td>
										</tr>
										<tr></tr>
										<tr>
											<th align="right">事由：</th>
											<td colspan="3"><textarea id="reason" name="reason">${tripRecord.reason}</textarea> <script type="text/javascript">
													 var reasons = KindEditor.create('textarea[name="reason"]',
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
									</table>
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>
