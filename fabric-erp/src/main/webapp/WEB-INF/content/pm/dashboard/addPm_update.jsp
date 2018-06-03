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
	
	$(window).scroll(function(){
		if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
			if(!$('#orderTitleFixd').hasClass('fixed')){
				$('#orderTitleFixd').addClass('fixed');
				$('#orderTitleFixd').parent('dl').css('padding-top',30);
			}
		}else{
			$('#orderTitleFixd').removeClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',0);
		}
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/pm_project.png" alt="" />
					<s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="项目管理" /></a></li>
				<li><a href="#"><s:text name="仪表板" /></a></li>
				<li><a href="#"><s:text name="新增项目" /></a></li>
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
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_savenew.png" /> </a> <a href="#"><img width="22" height="22" title="取消"
							src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" />
					</a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png"> </a> <a href="#"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#"><img width="22"
							height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/pm/pmListAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png">
					</a>
					</span> <strong><b><s:text name="新增项目" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">项目名称：</th>
											<td><input id="masterContractCoding" name="masterContractCoding" value="${contract.masterContractCoding}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">项目编码：</th>
											<td><input id="masterContractCoding" name="masterContractCoding" value="${contract.masterContractCoding}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">所属部门：</th>
											<td><select style="width: 200px" id="moneyType" name="moneyType" class="validate[required] text-input" onclick="fillingMoneyTypeCode();">
													<option value="">请选择</option>
													<c:forEach var="ot" items="${moneyTypeList }">
														<option value="${ot.code }">${ot.name }</option>
													</c:forEach>
											</select> <input type="hidden" id="moneyTypeCode" name="moneyTypeCode" value="${contract.moneyTypeCode }" /></td>
											<th align="right">创建时间：</th>
											<td><input id="retentionsStartDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">开始时间：</th>
											<td><input id="retentionsStartDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th align="right">结束时间：</th>
											<td><input id="retentionsStartDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">关联产品：</th>
											<td><input id="projectName" name="projectName" value="${contract.projectName}" type="text" size="26" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="projectName" name="projectName" value="${contract.id}" /> <input class="btn" type="button" value="选择信息"
												onclick="chooseProjectName();" /></td>
											<th align="right">关联客户：</th>
											<td><input id="projectName" name="projectName" value="${contract.projectName}" type="text" size="26" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="projectName" name="projectName" value="${contract.id}" /> <input class="btn" type="button" value="选择信息"
												onclick="chooseProjectName();" /></td>
										</tr>
										<tr>
											<th align="right">项目概述：</th>
											<td colspan="3"><textarea id="mainContent" name="mainContent">${contract.mainContent}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
										 var contents = KindEditor.create('textarea[name="mainContent"]',
										{basePath:'${vix}/plugin/KindEditor/',
											width:735,
											height:300,
											cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
											uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
											fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
											allowFileManager : true 
											}
										 );
									 </script></td>
										</tr>
										<tr>
											<td></td>
											<td>创建关于这个项目的概述信息可以帮助您的团队成员或者客户快速了解项目范围</td>
										</tr>
										<tr>
											<th align="right">项目状态：</th>
											<td><select style="width: 300px" id="moneyType" name="moneyType" class="validate[required] text-input" onclick="fillingMoneyTypeCode();">
													<option value="">请选择</option>
													<c:forEach var="ot" items="${moneyTypeList }">
														<option value="${ot.code }">${ot.name }</option>
													</c:forEach>
											</select> <input type="hidden" id="moneyTypeCode" name="moneyTypeCode" value="${contract.moneyTypeCode }" /></td>
										</tr>
										<tr>
											<th align="right">所有者：</th>
											<td><select style="width: 300px" id="moneyType" name="moneyType" class="validate[required] text-input" onclick="fillingMoneyTypeCode();">
													<option value="">请选择</option>
													<c:forEach var="ot" items="${moneyTypeList }">
														<option value="${ot.code }">${ot.name }</option>
													</c:forEach>
											</select> <input type="hidden" id="moneyTypeCode" name="moneyTypeCode" value="${contract.moneyTypeCode }" /></td>
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







