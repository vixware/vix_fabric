<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--

function addUserAccountRole(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectRoleAction!goList.action?tag=choose',
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
							var roleIds = "";
							var roleNames = "";
							var result = returnValue.split(",");
							for (var i=0; i<result.length; i++){
								if(result[i].length>1){
									var v = result[i].split(":");
									roleIds += "," + v[0];
									roleNames += "," + v[1];
								}
							}
							$("#addUserAccountRoleIds").val(roleIds);
							
							roleNames = roleNames.substring(1,roleNames.length);
							$("#addUserAccountRoleNames").html(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function deleteUserAccountRole(row,id){
	$(row).parent().parent().remove();
	$("#deleteUserAccountRoleIds").val($("#deleteUserAccountRoleIds").val()+","+id);
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}


/**
 * 选择人员
 */
function chooseUserAccountEmp(){
	$.ajax({
		 url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		 data:{chkStyle:"radio"},
		 //url:'${vix}/common/select/commonSelectEmpByBizOrgAction!goChooseEmp.action',
		 //data:{chkStyle:"radio","bizViewCode":"pvcode1"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 540,
					title:"选择人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								/* if(resObj.length == 0 ){
									return;
								} */
								//var pubIdTmp = $("#empId").val();
								
								//pubIdTmp = pubIdTmp + ",";
								//debugger;
								var result = returnValue.split(",");
								//for (var i=0; i<result.length; i++){
								var resObj = result[result.length-1];
								if(resObj.length>1){
									var v = resObj.split(":");
									selectIds = v[0];
									selectNames = v[1];
								}
								//selectIds = $("#empId").val()+selectIds;
								//selectNames = $("#empName").val()+selectNames;
								$("#empId").val(selectIds);
								//selectNames = selectNames.substring(1,selectNames.length);
								$("#empName").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

$(function(){
	$("#userAccountForm").validationEngine();
});

//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="userAccountForm" name="userAccountForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">账户:&nbsp;</td>
					<td><input type="text" id="account" name="entityForm.account" value="${entity.account}" <s:if test="%{entity.id!=null}">readonly="readonly"</s:if> class="validate[required] text-input" /></td>
					<td align="right">是否禁用:&nbsp;</td>
					<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.enable" value="%{entity.enable}" theme="simple"></s:radio> <%-- <s:if test="entity.enable.equals(\"0\")">
						<input type="radio" id="enable1" name="entityForm.enable" value="1" checked="checked"/>是
						<input type="radio" id="enable2" name="entityForm.enable" value="0" />否
					</s:if>
					<s:elseif test="entity.enable.equals(\"1\")">
						<input type="radio" id="enable1" name="entityForm.enable" value="1" />是
						<input type="radio" id="enable2" name="entityForm.enable" value="0" checked="checked"/>否
					</s:elseif>
					<s:else>
						<input type="radio" id="enable1" name="entityForm.enable" value="1"/>是
						<input type="radio" id="enable2" name="entityForm.enable" value="0"/>否
					</s:else> --%></td>
					<!-- <td align="right">职员:&nbsp;</td>
				<td>普通职员</td> -->
				</tr>
				<tr height="40">
					<td align="right">职员:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="empId" name="entityForm.employee.id" value="${entity.employee.id}" /> <input type="text" id="empName" name="entityForm.employee.name" readonly="readonly" class='<s:if test="%{!isAdmin}">validate[required]</s:if> text-input' value="${entity.employee.name}" /> <input class="btn" type="button"
						value="选择" onclick="chooseUserAccountEmp();" /></td>
				</tr>
				<%-- 
			<tr height="40">
				<td align="right">密码:&nbsp;</td>
				<td><input type="password" id="password" name="entityForm.password" value="${entity.password}" class="validate[required] text-input"/></td>
				<td align="right">确认密码:&nbsp;</td>
				<td><input type="password" id="passwordConfirm" name="entityForm.passwordConfirm" value="${entity.password}" class="validate[required] text-input"/></td>
			</tr>
			 --%>
				<tr height="40">
					<td align="right">微信号:&nbsp;</td>
					<td><input type="text" id="wxAccount" name="entityForm.wxAccount" value="${entity.wxAccount}" class="text-input" /></td>
					<td align="right"></td>
					<td></td>
				</tr>
				<tr height="40">
					<td align="right">个性首页:&nbsp;</td>
					<td><s:select id="loginPage" name="entityForm.loginPage" list="#request.lpList" listKey="key" listValue="value" value="%{entity.loginPage}" theme="simple"></s:select></td>
					<td align="right">菜单显示:&nbsp;</td>
					<td><s:select list="#{'C':'正常','U':'升级'}" id="userResourceReadType" name="entityForm.userResourceReadType" value="%{entity.userResourceReadType}" theme="simple"></s:select></td>
				</tr>
				<tr height="40">
					<td align="right">开始时间：</td>
					<td><input type="text" name="entityForm.startTime" value="<s:date name='entity.startTime' format='yyyy-MM-dd'/>" class="time_aoto_input ipt validate[required] text-input" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"></td>
					<td align="right">截止时间：</td>
					<td><input type="text" name="entityForm.endTime" value="<s:date name='entity.endTime' format='yyyy-MM-dd'/>" class="time_aoto_input ipt validate[required] text-input" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"></td>
				</tr>
				<tr height="30">
					<td align="right">分配角色:</td>
					<td>
						<!-- <a id="addUserAccountRole" href="#" onclick="addUserAccountRole();" style="color: #000000; text-decoration: none;">添加</a> --> <input class="btn" type="button" value="添加" onclick="addUserAccountRole();" />
					</td>
					<td align="right">角色:</td>
					<td><span id="addUserAccountRoleNames"></span></td>
				</tr>
			</table>
			<hr />
			<input type="hidden" id="addUserAccountRoleIds" name="addUserAccountRoleIds" value="" /> <input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value="" />
			<div class="table" style="margin: 5px;">
				<p id="table_top" style="border: 1px solid #D6D6D6; color: #CDCDCD; padding: 5px;">
					<img src="${vix}/common/img/icon_22.gif" /><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">角色列表</a>
				</p>
				<table id="brandSub" class="list">
					<tr>
						<th width="10%">编号</th>
						<th width="80%" align="left">名称</th>
						<th width="10%">操作</th>
					</tr>
					<s:iterator value="entity.roles" var="role" status="s">
						<tr>
							<td align="right">${s.count}</td>
							<td align="left">${role.name}</td>
							<td align="center"><a href="#" onclick="deleteUserAccountRole(this,${role.id});" title="删除"> <img src="${vix}/common/img/icon_12.png" />
							</a></td>
						</tr>
					</s:iterator>
				</table>
				<%-- <table id="dimensionSub" class="list" style="display:none;">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="category.dimensions" var="entity" status="s">
					<tr>
						<td>${s.count}</td>
						<td>${entity.name}</td>
						<td align="center">
							<a href="#" onclick="deleteUserAccountRoleDimension(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table> --%>
			</div>
		</div>
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