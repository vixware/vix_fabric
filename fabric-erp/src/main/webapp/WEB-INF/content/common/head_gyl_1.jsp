<%@page import="com.vix.common.security.util.SecurityUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function loadContent (url,mainMenuId) {
		//_head_loading(true);
		if(null != mainMenuId){
			$(".nav_current").attr("class","nav_arrow");
			$("#"+mainMenuId).attr("class","nav_current nav_arrow");
		}
		$.ajax ({
			url : url ,
			cache : false ,
			success : function (html) {
				_pad_page_view_clear();
				
				$ ("#mainContent").html (html);
				$ ("#mainContent").trigger('main_content_load',[url]);
				_head_loading(false);
			}
		});
	}
	
	function _head_loading(isShow){
		_resouse_show_menu_mask(isShow);
	}

	function initPlatform(){
		window.open ('${vix}/common/vixAction!goSetup.action', '初始化设置');
	}
	
	/**
	 * 加载内容到div
	 */
	function loadDivContent(divContent,url){
		$.ajax({
			  url:url,
			  cache: false,
			  success: function(html){
				  $("#"+divContent).html(html);
			  }
		});
	}

	/**
	 * 下载附件
	 */
	function downloadVixFile(fileId){
		var url="${vix}/common/model/fileAction!checkFileExist.action";
		var downUrl = "${vix}/common/model/fileAction!download.action";// fileId="+fileId;
		var downParam = "fileId="+fileId;
	    $.ajax({
			type: "POST",
			async: false,//修改表单提交为同步
			url: url,
			data : "fileId="+fileId,
			success: function(result){
				var success = result.success;
				var msgTmp = result.message;
				if(!success){
					asyncbox.alert(msgTmp,"错误");
				}else{
					if(Get_IE_Version()==6)
						window.open(downUrl+'?'+downParam);
					else if(isFireFox()){
						document.location.href=downUrl+"?"+downParam;
					}else{
						postWindow(downUrl,downParam);
					}
				}
			   
			} 
		});
	}


	function postWindow(url,param){
	    var postUrl = url;
	    var postData = param;
	    var iframe = document.getElementById("postData_iframe");
	    if(!iframe){
	        iframe = document.createElement("iframe");
	        iframe.id = "postData_iframe";
	        iframe.scr= "about:blank";
	        iframe.frameborder = "0";
	        iframe.style.width = "0px";
	        iframe.style.height = "0px";
	        var form = document.createElement("form");
	        form.id = "postData_form";
	        form.method = "post";
	        form.target = "_blank";
	        
	        document.body.appendChild(iframe);
	        iframe.contentWindow.document.write("<body>" + form.outerHTML + "</body>");
	      
	    }
	   
	   	var paramsArray = new Array();
		paramsArray= param.split("&");
		var field_input = "";
		
		for (i=0;i<paramsArray.length ;i++ ){
			var paramTemp = paramsArray[i];
			var paramTempOne  = new Array();
			paramTempOne = paramTemp.split("=");;
			var paramTempName= paramTempOne[0];
			var paramTempValue= paramTempOne[1];
			field_input = field_input + " <input type=\"hidden\" name=\"" + paramTempName+ "\" value=\"" + paramTempValue +"\" >" ;
		}
	   
	    iframe.contentWindow.document.getElementById("postData_form").innerHTML = field_input;
	   
	    iframe.contentWindow.document.getElementById("postData_form").action = postUrl;
	    iframe.contentWindow.document.getElementById("postData_form").submit();
	}
	 
	/**
	 * 删除附件
	 */
	function deleteVixFile(fileId,divId,dataId,businessTag,fileAttType){
		var fileDeleteUrl = '${vix}/common/model/fileAction!deleteFile.action';
		var paramStr = "fileId="+fileId;
		$.ajax({
			type: "POST",
			async: false,//修改表单提交为同步
			url: fileDeleteUrl,
			data : paramStr,
			success: function(result){
				var success = result.success;
				var msgTmp = result.message;
				if(!success){
					asyncbox.alert(msgTmp,"错误");
				}else{
					var paramTmp = "dataId="+dataId+"&businessTag="+businessTag+"&fileAttType="+fileAttType+"&divId="+divId;
					loadDivContent( divId,'${vix}/common/model/fileAction!findBizFileList.action?'+paramTmp);	
				}
			} 
		});
	}
	function isFireFox(){
		if(navigator.userAgent.indexOf("Firefox")>0){
			return true;
		}
		return false;
	}
	function Get_IE_Version(){
		if(!document.all)
			return 0;
	       var v;
	       if(navigator.userAgent.indexOf("MSIE 6.0")>0)//IE 6.0
	       { 
	         v=6;
	       } 
	       else if(navigator.userAgent.indexOf("MSIE 7.0")>0)//IE 7.0 
	       {
	         v=7
	       }
	       else if(navigator.userAgent.indexOf("MSIE 8.0")>0)//IE 8.0
	       {
	         v=8;
	       }
	       return v;
	}
	function goAbout (){
		asyncbox.open ({
		modal : true ,
		width : 780 ,
		height : 460 ,
		title : "关于" ,
		url : "${vix}/common/vixAction!goAbout.action"
		});
	}
    $(document).ready(function(showsamount) {
    	  $.ajax({
			    url : '${vix}/system/latestOperateAction!goList.action?showsamount='+showsamount,
			    cache : false,
			    success : function(html) {
			    	  $("#latestOperate").html(html);
			    }
			    });
    });
</script>
<input type="hidden" id="menuId" value="${menuId}" />
<div class="head">
	<div>
		<h1 id="logo">
			<a href="${vix}/common/vixAction!goIndex.action?isHomePage=1"><img src="${vix}/common/img/logo.png" alt="" onclick="" /> </a>
		</h1>
		<div class="menu">
			<strong id="menu"> <i>欢迎,<b>${userInfo.account}</b>
			</i> <em>│<a href="${vix}/logout">注销</a>
			</em> <em class="menu_hidd">│<a href="#" onclick="loadContent('${vix}/hr/hrEmpstaffselfAction!goSaveOrUpdate.action');">员工自助</a>│<a href="#">支持平台</a>│<a href="#" onclick="goAbout();">关于我们</a>
			</em>
			</strong> <span id="menu_btn"></span>
		</div>
		<div id="nav" class="drop_nav">
			<div id="navHideButton"></div>
			<ul id="nav_menu">

				<%
				    String userName = SecurityUtil.getCurrentUserName();
				    if (SecurityUtil.isSuperAdmin())
				    {
				%>
				<li id="systemManage" class="nav_arrow"><a href="#"><span>系统管理</span> </a>
					<ul>
						<li class="fly"><a href="#">平台运营管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/industryManagementAction!goList.action');">行业管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/system/companyOperationAction!goList.action');">承租户管理</a></li>
								<li><a href="###" onclick="initPlatform();">初始化设置</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/configUrlAction!goList.action');">首页面配置管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/moduleAction!goList.action');">模块管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/model/orginialBillsCategoryAction!goList.action');">单据分类管理</a></li>
								<li class="fly"><a href="#">元数据管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/model/orginialBaseMetaDataCategoryAction!goList.action');">业务对象分类</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/model/orginialBaseMetaDataAction!goList.action');">业务对象</a></li>
									</ul></li>
								<li class="fly"><a href="#">基础权限资源管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/common/security/authorityAction!goList.action');">权限管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/security/resourceAction!goList.action');">资源管理</a></li>
									</ul></li>


							</ul></li>
					</ul></li>

				<%
				    }
				    else if (!userName.startsWith("test") && !userName.startsWith("gw"))
				    {
				%>
				<s:iterator var="menu1" value="#session.userMenu">
					<li class="nav_arrow"><a href="#"><span><s:property value="%{#menu1.name}" /></span></a> <s:if test="%{#menu1.children!=null && #menu1.children.size>0}">
							<ul>
								<s:iterator var="menu2" value="#menu1.children">
									<c:set var="menu" value="${menu2}" scope="request"></c:set>
									<jsp:include page="headMenu.jsp"></jsp:include>
								</s:iterator>
							</ul>
						</s:if></li>
				</s:iterator>

				<%
				    }
				    else
				    {
				%>
				<li class="nav_current nav_arrow" id="bg_01"><a href="/vix/common/vixAction!goIndex.action"><span>工作台</span> </a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goToDo.action','bg_01');" title="待办事宜">待办事宜</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/model/jobTodoAction!goList.action','bg_01');" title="待审批">待审批</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/canlendarAction!goCanlendar.action','bg_01');">日程安排</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goOECC.action','bg_01');">日清管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goTaskCenter.action','bg_01');">任务中心</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goBulletInboardNotices.action','bg_01');">通知公告</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action','bg_01');">邮件</a></li>
						<li><a href="#" onclick="loadContent('${vix}/oa/commonWabAction!goList.action','bg_01');">通讯薄</a></li>
						<!-- <li><a href="#">计划</a></li> -->
						<li><a href="#" onclick="loadContent('${vix}/system/remindsCenterAction!goList.action','bg_01');">预警中心</a></li>
					</ul></li>
				<li class="nav_arrow" id="bg_02"><a href="#"><span>会员交互管理</span></a>
					<ul>
						<li class="fly"><a href="#">商业智能与决策</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/purchasingBehaviorAction!goList.action','bg_02');">购买行为分析</a></li>
								<li><a href="#" onclick="loadContent('${vix}/crm/analyse/rfmAnalyseAction!showAnalyseData.action','bg_02');">RFM模型分析</a></li>
								<li class="fly"><a href="#">会员结构分析</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/membershipStructureAnalysisAction!goList.action','bg_02');">会员层次分析</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/salesAnalysisAction!goList.action','bg_02');">销售分析</a></li>
									</ul></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/categoryAnalysisAction!goList.action','bg_02');">品类分析</a></li>
							</ul></li>
						<li class="fly"><a href="#">会员管理</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/member/memberLevelAction!goList.action','bg_02');">会员等级</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/member/memberTagAction!goList.action','bg_02');">会员标签</a></li>
									</ul></li>
								<li><a href="###" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=member','bg_02');">会员列表</a></li>
								<li><a href="#">会员关怀</a></li>
								<li class="fly"><a href="#">忠诚度管理</a>
									<ul>
										<li class="fly_top"><a href="#">会员等级管理</a></li>
										<li><a href="###" onclick="loadContent('${vix}/crm/member/customerBlackListAction!goList.action','bg_02');">黑名单管理</a></li>
										<li class="fly_end"><a href="#">会员积分管理</a></li>
									</ul></li>
								<li><a href="#">会员标签视图</a></li>
								<li class="fly_end"><a href="#">会员整合管理</a></li>
							</ul></li>
						<li class="fly"><a href="#">营销中心</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/businessSetAction!goMessageList.action','bg_02');">短信模板</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/businessSetAction!goEmailList.action','bg_02');">邮件模板</a></li>
									</ul></li>
								<li class="fly"><a href="#">智能营销</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/membershipSubdivisionAction!goList.action','bg_02');">会员细分管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/crm/business/marketingActivitiesAction!goList.action','bg_02');">营销活动</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/marketingAutomationProcessAction!goList.action','bg_02');">营销自动化流程</a></li>
									</ul></li>
								<li class="fly"><a href="#">促销管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/couponAction!goList.action','bg_02');">优惠券</a></li>
									</ul></li>
								<li class="fly"><a href="#">短信管理</a>
									<ul>
										<li class="fly_top"><a href="#">创建短信模板</a></li>
										<li><a href="#">短信模板管理</a></li>
										<li><a href="#">发送短信</a></li>
										<li class="fly_end"><a href="#">短信批次管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">EDM管理</a>
									<ul>
										<li class="fly_top"><a href="#">创建EDM模板</a></li>
										<li><a href="#">EDM模板管理</a></li>
										<li><a href="#">发送EDM</a></li>
										<li><a href="#">EDM批次管理</a></li>
										<li class="fly_end"><a href="#">EDM素材管理</a></li>
									</ul></li>
								<li class="fly_end"><a href="#">微营销管理</a></li>
							</ul></li>
						<li class="fly"><a href="#">订单中心</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/orderSupervisoryControlAction!goList.action','bg_02');">订单监控</a></li>
								<li><a href="#" onclick="loadContent('${vix}/crm/business/orderExpeditingAction!goList.action','bg_02');">订单催付</a></li>
								<li><a href="#" onclick="loadContent('${vix}/crm/business/orderCareAction!goList.action','bg_02');">订单关怀</a></li>
								<li><a href="#">发送记录</a></li>
								<li class="fly_end"><a href="#">异常告警</a></li>
							</ul></li>
						<li class="fly"><a href="#">客服中心</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/orderManagementAction!goList.action','bg_02');">询单管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/crm/business/orderExpeditingAction!goList.action','bg_02');">智能催付</a></li>
								<li><a href="#" onclick="loadContent('${vix}/crm/business/changePostAndPriceAction!goList.action','bg_02');">改邮改价</a></li>
								<li class="fly"><a href="#">事务跟进</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/followPaymentAction!goList.action','bg_02');">未付款跟进</a></li>
										<li><a href="#">发货跟进</a></li>
										<li><a href="#" onclick="loadContent('${vix}/crm/business/logisticsFollowUpAction!goList.action','bg_02');">物流跟进</a></li>
										<li class="fly_end"><a href="#">评价跟进</a></li>
									</ul></li>
								<li><a href="#">我的事务</a></li>
								<li class="fly_end"><a href="#">客服监控</a></li>
							</ul></li>
					</ul></li>


				<li id="systemManage" class="nav_arrow"><a href="#"><span>系统管理</span> </a>
					<ul>
						<li class="fly"><a href="#">基础设置</a>
							<ul>
								<li class="fly"><a href="#">编码管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/codeAction!goList.action');">编码级数管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/codeAction!goCodeList.action');">编码规则管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">字典管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/currencyTypeAction!goList.action','systemManage');">币种管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/exchangeRateAction!goList.action','systemManage');">汇率</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/regionalAction!goList.action','systemManage');">地域</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/common/measureUnitGroupAction!goList.action','systemManage');">计量单位</a></li>
									</ul></li>
								<li class="fly"><a href="#">对象类型</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/system/enumerationCategoryAction!goList.action','systemManage');">引用字典分类</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/enumerationAction!goList.action','systemManage');">引用字典管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/objectExpandAction!goList.action','systemManage');">类型管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/specificationAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}规格管理</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/system/precisionControlAction!goList.action');">数据精度管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/system/billTypeManagementAction!goList.action');">单据类型管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/system/billTypeSetAction!goList.action');">单据类型设置(内用)</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/system/systemSetAction!goList.action');">运行参数</a></li>
							</ul></li>
						<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}管理</a>
							<ul>
								<li class="fly"><a href="###">设置</a>
									<ul>
										<li><a href="###" onclick="loadContent('${vix}/mdm/item/itemGroupAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}组设置</a></li>
										<li><a href="###" onclick="loadContent('${vix}/mdm/item/itemBrandAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}品牌</a></li>
										<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}模版</a>
											<ul>
												<li class="fly_top"><a href="#">客户化订购管理</a></li>
												<li><a href="#" onclick="loadContent('${vix}/mdm/bom/bomStructAction!goList.action','systemManage');">BOM管理</a></li>
												<li class="fly_end"><a href="#" onclick="loadContent('${vix}/mdm/bom/bomTypeAction!goList.action','systemManage');">BOM类别</a></li>
											</ul></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemCatalogAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}分类管理</a></li>
								<li><a href="###" onclick="loadContent('${vix}/mdm/item/itemAction!goSaveOrUpdate.action?id=','systemManage');">新增${vv:varView("vix_mdm_item")}</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}列表</a></li>
								<%-- <li><a href="#" onclick="loadContent('${vix}/mdm/item/priceConditionAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}定价</a></li> --%>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/salePriceConditionAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}销售定价</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/purchasePriceConditionAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}采购定价</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/bom/bomStructAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}清单管理</a></li>
							</ul></li>
						<li class="fly"><a href="#">基础数据管理</a>
							<ul>
								<li class="fly"><a href="#">元数据管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/model/baseMetaDataCategoryAction!goList.action');">业务对象分类</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/model/baseMetaDataAction!goList.action');">业务对象</a></li>
									</ul></li>
								<li class="fly"><a href="#">采购管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/srm/supplierCategoryAction!goList.action','bg_02');">供应商分类</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/srm/managementBusinessPartnerAction!goList.action','bg_02');">供应商清单</a></li>
									</ul></li>
								<li class="fly"><a href="#">供应商</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/srm/supplierCategoryAction!goList.action','bg_02');">分类</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/srm/directoryManagementAction!goList.action','bg_02');">供应商列表</a></li>
									</ul></li>
								<li class="fly"><a href="#">销售管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountCategoryAction!goList.action','bg_02');">客户分类</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=crm','bg_02');">客户管理</a></li>
									</ul></li>
								<li class="fly"><a href="#" onclick="">库存</a>
									<ul>
										<li class="fly_top"><a href="#">期初录入</a></li>
										<li class="fly_top"><a href="#">仓库</a></li>
										<li><a href="#">库位</a></li>
										<li><a href="#" onclick="loadContent('${vix}/inventory/productAssemblyAction!goList.action');">商品组装</a></li>
										<li><a href="#" onclick="loadContent('${vix}/inventory/productDisassemblyAction!goList.action');">商品拆装</a></li>
										<li><a href="#">饮片加工单</a></li>
										<li class="fly_end"><a href="#">饮片组方单</a></li>
									</ul></li>
								<li class="fly"><a href="#">财务</a>
									<ul>
										<li class="fly"><a href="#">会计科目</a>
											<ul>
												<li class="fly_end"><a href="#" onclick="loadContent('${vix}/fa/accountsAction!goList.action');">列表</a></li>
											</ul></li>

										<li><a href="#" onclick="loadContent('${vix}/fa/vocherAction!goList.action');">凭证管理</a></li>
									</ul></li>
								<li><a href="#">成本管理</a></li>
								<li class="fly"><a href="#" onclick="">生产管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/resourceInformationAction!goList.action');">资源资料</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/workCentersAction!goList.action');">工作中心</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/processAction!goList.action');">工序管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/craftsAction!goList.action');">工艺路线</a></li>
										<li class="fly_end"><a href="#">产品模块化配置</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">组织架构管理</a>
							<ul>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/common/org/organizationAction!goList.action');">企业组织管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/org/organizationAction!goList.action');">组织架构</a></li>
										<li><a href="/vix/content/hr/pm/sm.jsp">组织架构图</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/organizationUnitAction!goList.action');">部门管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action');">岗位管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/businessViewAction!goList.action');">业务组织视图管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/businessOrgAction!goList.action');">业务组织管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">销售和分销</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/sales/config/saleOrgAction!goList.action','bg_02');">销售组织管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/drp/channelAction!goList.action');">分销渠道管理</a></li>
										<li><a href="#">销售办公室</a></li>
										<li class="fly_end"><a href="#">销售组</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action');">员工管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/advancedSearchAction!goSaveOrUpdate.action');">高级查询</a></li>
							</ul></li>
						<li class="fly"><a href="#">权限管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action');">角色管理</a>
								<li><a href="#" onclick="loadContent('${vix}/common/security/userAccountAction!goList.action');">账号管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/dataColSecAction!goList.action');">列级权限配置</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/dataColPolicyAction!goList.action');">列级权限策略管理</a></li>
								<li class="fly"><a href="#">行级权限管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action');">行集权限策略配置管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyAction!goList.action');">行集权限策略管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowOwnerAction!goList.action');">行集权限主体管理</a>
									</ul></li>
								<li><a href="#">界面设置</a></li>
								<li><a href="#">菜单设置</a></li>
							</ul></li>
						<li class="fly"><a href="#">业务设置</a>
							<ul>
								<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}设置</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemClassAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}类别</a></li>
										<li><a href="###" onclick="loadContent('${vix}/mdm/item/itemGroupAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}组设置</a></li>
										<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}模版</a>
											<ul>
												<li class="fly_top"><a href="#">客户化订购管理</a></li>
												<li class="fly_end"><a href="#">BOM管理</a></li>
											</ul></li>
									</ul></li>
								<li class="fly"><a href="#">采购设置</a>
									<ul>
										<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchasingOptionsAction!goListContent.action','bg_02');">采购选项</a>
											<ul>
												<li class="fly_top"><a href="#">批号管理</a></li>
												<li><a href="#">序号管理</a></li>
												<li class="fly_end"><a href="#">条形码管理</a></li>
											</ul></li>
										<li class="fly"><a href="#" onclick="loadContent('${vix}/srm/tempAction!goBeginSetting.action','bg_02');">期初设置</a>
											<ul>
												<li class="fly_top"><a href="#">期初入库单</a></li>
												<li class="fly_end"><a href="#">期初发票</a></li>
											</ul></li>
										<li><a href="#" onclick="loadContent('${vix}/purchase/orderTypeAction!goList.action','bg_02');">采购单据类型</a></li>
										<li><a href="#" onclick="loadContent('${vix}/purchase/bizTypeAction!goList.action','bg_02');">采购业务类型</a></li>
										<li><a href="#">采购订单模版</a></li>
										<li><a href="#" onclick="loadContent('${vix}/srm/tempAction!goProcedures.action','bg_02');">采购流程设置</a></li>
										<li class="fly"><a href="#">采购会计指令</a>
											<ul>
												<li class="fly_top"><a href="#">采购入库指令</a></li>
												<li><a href="#">采购发票指令</a></li>
												<li class="fly_end"><a href="#">采购结算指令</a></li>
											</ul></li>
									</ul></li>
								<li class="fly"><a href="#">销售设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/sales/config/saleConfigAction!goSaleConfig.action','bg_02');">销售选项</a></li>
										<li><a href="#" onclick="loadContent('${vix}/sales/config/saleOrgAction!goList.action','bg_02');">销售组织</a></li>
										<li><a href="#" onclick="loadContent('${vix}/sales/config/productGroupAction!goList.action','bg_02');">产品组</a></li>
										<li><a href="#" onclick="loadContent('${vix}/sales/config/salesAreaAction!goList.action','bg_02');">销售范围</a></li>
										<li class="fly"><a href="#">后勤执行</a>
											<ul>
												<li class="fly_top"><a href="#" onclick="loadContent('${vix}/sales/config/shippingPointAction!goList.action','bg_02');">起运点</a></li>
												<li><a href="#" onclick="loadContent('${vix}/sales/config/mountPointAction!goList.action','bg_02');">装载点</a></li>
												<li><a href="#">运输条件</a></li>
												<li class="fly_end"><a href="#">装载组</a></li>
											</ul></li>
										<li><a href="#" onclick="loadContent('${vix}/sales/config/saleBillTypeAction!goList.action','bg_02');">销售单据类型</a></li>
										<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationCategoryAction!goList.action','bg_02');">报价模版分类</a></li>
										<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationTemplateAction!goList.action','bg_02');">报价单模板</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mdm/item/priceConditionAction!goList.action','bg_02');">价格管理</a></li>
										<li class="fly_end"><a href="#">期初录入</a></li>
									</ul></li>
								<li class="fly"><a href="#">库存设置</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/inventory/optionAction!goList.action');">选项</a></li>
										<li><a href="#" onclick="loadContent('${vix}/inventory/optionAction!goSetting.action');">字典管理</a></li>
										<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goList.action');">仓库管理</a>
											<ul>
												<li><a href="#"
													onclick="loadContent('${vix}/inventory/warehouseAction!goSaveOrUpdate.action'>新增仓库</a></li>
									<li><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goSaveOrUpdateCargoSpace.action'>新增货位</a></li>
									<li><a href="#"
										onclick="loadContent('${vix}/inventory/warehouseAction!goList.action');">仓库列表</a>
									</li>
								</ul>
							</li>
							<li><a href="#"
								onclick="loadContent('${vix}/inventory/initInventoryAction!goList.action');">期初录入</a>
							</li>
							<li><a href="#"
								onclick="loadContent('${vix}/inventory/beginDefectiveItemAction!goList.action');">期初不合格品</a>
							</li>
							<li class="fly"><a href="#">保质期管理</a>
								<ul>
									<li class="fly_top"><a href="#"
										onclick="loadContent('${vix}/inventory/shelfLifeAction!goList.action');">快到期库存查询</a></li>
									<li><a href="#" >失效库存报警</a></li>
									<li class="fly_end"><a href="#">保质期预警</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li><a href="#">客户关系设置</a></li>
					<li class="fly"><a href="#">供应商设置</a>
						<ul>
							<li class="fly_top"><a href="#" onclick="loadContent('${vix}/srm/directoryManagementAction!goList.action','bg_02');">产品目录管理</a></li>
							<li><a href="#" onclick="loadContent('${vix}/srm/contractTemplatesAction!goList.action','bg_02');">合同模版</a></li>
							<li><a href="#" onclick="loadContent('${vix}/srm/tenderSetAction!goListContent.action','bg_02');">招标设置</a></li>
							<li><a href="#" onclick="loadContent('${vix}/srm/supplierCategoryAction!goList.action','bg_02');">分类设置</a>
							<li><a href="#" onclick="loadContent('${vix}/srm/tempAction!goAssessmentManagement.action','bg_02');">评估项管理</a></li>
							<li class="fly_end"><a href="#" onclick="loadContent('${vix}/srm/parameterSetAction!goListContent.action','bg_02');">参数设置</a></li>
						</ul>
					</li>
					<li><a href="#" onclick="loadContent('${vix}/business/onlineStoreSetAction!goList.action');">网店设置</a></li>
					<li><a href="#">项目设置</a></li>
					<li class="fly"><a href="#">分销设置</a>
						<ul>
							<li class="fly_top"><a href="#"
								onclick="loadContent('${vix}/drp/distributionOptionsAction!goList.action');">分销选项</a></li>
							<li><a href="#"
								onclick="loadContent('${vix}/drp/distributionSystemRelationShipAction!goList.action');">分销体系管理</a></li>
							<li><a href="#"
								onclick="loadContent('${vix}/drp/salwableProductAction!goList.action');">分销产品管理</a></li>
							<li><a href="#"
								onclick="loadContent('${vix}/drp/employeeManagementAction!goList.action');">分销员工管理</a></li>
							<li><a href="#"
								onclick="loadContent('${vix}/drp/accountManagementAction!goList.action?source=drp');">分销账号管理</a></li>
							<li><a href="#"
								onclick="loadContent('${vix}/drp/channelPriceAction!goList.action');">分销价格管理</a></li>
							<li class="fly_end"><a href="#"
												onclick="loadContent('${vix}/drp/swaohAction!goList.action');">分销点管理</a></li>
							<li class="fly"><a href="#">价格管理</a>
								<ul>
									<li class="fly_top"><a href="#">变价处理</a></li>
									<li><a href="#">地区价格管理</a></li>
									<li><a href="#">经销商价格管理</a></li>
									<li><a href="#">批量优惠管理</a></li>
									<li><a href="#">现付优惠管理</a></li>
									<li><a href="#">促销优惠管理</a></li>
									<li><a href="#">期货优惠管理</a></li>
									<li class="fly_end"><a href="#">降价保护管理</a></li>
								</ul></li>
						</ul>
					</li>
					<li><a href="#">配送中心设置</a></li>
					<li><a href="#">合同设置</a></li>
					<li><a href="#">单据报表设置</a></li>
					<li class="fly"><a href="#">报表管理</a>
						<ul>
							<li class="fly_top"><a href="#">分类管理</a></li>
							<li class="fly"><a href="#">报表定制</a></li>
							<li class="fly_end"><a href="#">打印设置</a></li>
						</ul>
					</li>
				</ul>
			</li>
            <li><a href="#" onclick="loadContent('${vix}/flow/flowlist.jsp');">流程管理</a></li>
			<li class="fly"><a href="#">表单管理</a>
				<ul>
					<li class="fly_top"><a href="#" onclick="loadContent('${vix}/form/metadata.jsp');">字典维护</a></li>
					<li><a href="#" onclick="loadContent('${vix}/form/baseform.jsp');">基础表维护</a></li>
					<li><a href="#" onclick="loadContent('${vix}/form/businessform.jsp');">表单列表</a></li>
					<li class="fly_end"><a href="#" onclick="loadContent('${vix}/form/datasource.jsp');">数据源维护</a></li>
				</ul>
			</li>
			<li class="fly"><a href="#">预警管理</a>
				<ul>
					<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/remindsCenterAction!goList.action');">提醒中心</a></li>
					<li><a href="#" onclick="loadContent('${vix}/system/warningSourceAction!goList.action');">预警源设置</a></li>
					<li class="fly_end"><a href="#" onclick="loadContent('${vix}/system/warningCenterAction!goList.action');">预警和定时任务</a></li>
				</ul>
			</li>
			<li class="fly"><a href="#">运行管理</a>
				<ul>
					<li class="fly_top"><a href="#"
						onclick="loadContent('${vix}/system/changeManagementAction!goList.action');">变更管理</a></li>
					<li class="fly"><a href="#">维护管理</a>
						<ul>
							<li class="fly_top"><a href="#"
								onclick="loadContent('${vix}/system/onLineUpdateAction!goList.action');">在线更新</a></li>
							<li><a href="#"
								onclick="loadContent('${vix}/system/issuesFeedbackAction!goList.action');">问题反馈</a></li>
							<li class="fly_end"><a href="#"
								onclick="loadContent('${vix}/system/onLineAssistenceAction!goList.action');">在线帮助</a></li>
						</ul>
					</li>
					<li><a href="#"
						onclick="loadContent('${vix}/system/databaseManagementAction!goList.action');">数据库管理</a></li>
					<li><a href="#"
						onclick="loadContent('${vix}/system/systemLogManagementAction!goList.action');">系统日志</a></li>
					<!-- <li><a href="#">系统资源</a></li>
					<li><a href="#">系统访问</a></li>
					<li class="fly_end"><a href="#">系统安全</a></li> -->
				</ul>
			</li>
			<li ><a href="#" onclick="goAbout();">关于</a></li>
			</ul>
			</li>
			<li class="nav_arrow">
				<ul>
					<li><a onclick="javascript:change('点这里');"><img
							src="img/balloon.png" alt="" />点这里</a></li>
					<li class="fly"><a href="#"><img
							src="img/wrench_screwdriver.png" alt="" />新建选项</a>
						<ul>
							<li><a href="#"><img src="img/home.png" alt="" />新建选项</a></li>
							<li><a href="#"><img src="img/user.png" alt="" />新建选项</a></li>
							<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
							</li>
							<li><a href="#"><img src="img/wrench_screwdriver.png"
									alt="" />新建选项</a></li>
						</ul>
					</li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
					<li><a href="#"><img src="img/wrench_screwdriver.png"
							alt="" />新建选项</a></li>
					<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a>
					</li>
				</ul>
			</li>
			<%	
			}				
			%>
			</ul>
			<%-- --%>
			<div id="nav_ico">
				<span><a href="#"
					onclick="loadContent('${vix}/system/personalInformationAction!goList.action');"><img src="img/user.png" alt="" /><small>个人设置</small> </a> </span> <span><a
					href="#" onclick="loadContent('${vix}/system/systemSetAction!goList.action');"><img src="img/wrench_screwdriver.png" alt="" /><small>运行参数</small> </a> </span> <span><a
					href="#" onclick="loadContent('${vix}/common/contactsAction!goList.action');"><img src="img/address_book.png" alt="" /><small>通讯簿</small>
				</a> </span> <span><a href="#"
					onclick="loadContent('${vix}/common/mailAction!goMailList.action');"><img
						src="img/mail.png" alt="" /><small>邮件管理</small> </a> </span>
				<div id="nicemenu">
					<ul>
						<li><span class="head_menu"><img
								src="img/application_lightning.png" class="arrow" /></span>
							<div class="flick_menu" id="latestOperate">
			                      <s:iterator value="latestOperateEntityList" var="latestOperateEntity" status="s">
										<a href="#">${latestOperateEntity.objectType}</a>
								  </s:iterator>
							</div>
						</li>
						<li><span class="head_menu"><img src="img/recent_list.png"
								class="arrow" /> </span>
							<div class="flick_menu">
								<a href="index.html">用户中心</a> <a href="index.html">我的订单</a> <a
									href="index.html">我的流程</a> <a href="index.html">我的日程</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
