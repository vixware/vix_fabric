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
				
				$("#mainContent").html (html);
				$("#mainContent").trigger('main_content_load',[url]);
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
					if (SecurityUtil.isSuperAdmin()) {
				%>
				<li id="systemManage" class="nav_arrow"><a href="#"><span>平台运营管理</span> </a>
					<ul>
						<!-- <li class="fly"><a href="#"></a> 
								<ul>-->
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/companyOperationAction!goList.action');">承租户管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/system/industryManagementAction!goList.action');">行业管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/security/moduleAction!goList.action');">模块管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/security/configUrlAction!goList.action');">首页面配置管理</a></li>
						<li><a href="###" onclick="initPlatform();">初始化设置</a></li>
						<li><a href="#" onclick="loadContent('${vix}/system/billTypeSetAction!goList.action');">单据类型字典管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/system/orginialBillTypeAction!goList.action');">单据类型管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/model/orginialVarAction!goList.action');">系统常量管理</a></li>
						<li class="fly"><a href="#">元数据管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/model/orginialBaseMetaDataCategoryAction!goList.action');">业务对象分类</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/model/orginialBaseMetaDataAction!goList.action');">业务对象</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/orginialMeasureUnitAction!goList.action');">计量单位管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/orginialCurrencyTypeAction!goList.action');">币种</a></li>
							</ul></li>
						<li class="fly"><a href="#">基础权限资源管理</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/common/security/orginialAuthorityAction!goList.action');">权限管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/orginialResourceAction!goList.action');">资源管理</a></li>
							</ul></li>


						<!-- </ul>
							</li> -->
					</ul></li>

				<%
					} else if (!userName.startsWith("test") && !userName.startsWith("gw")) {
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
					} else {
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

				<!-- 供应链 -->
				<s:include value="head_temp_gyl.jsp"></s:include>


				<li class="nav_arrow" id="bg_03"><a href="#"><span>生产管理</span></a>
					<ul>
						<li class="fly"><a href="#">设置</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/resourceInformationAction!goList.action');">资源资料</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mm/workCentersAction!goList.action');">工作中心</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mm/processAction!goList.action');">工序管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mm/craftsAction!goList.action');">工艺路线</a></li>
								<li class="fly_end"><a href="#">产品模块化配置</a></li>
							</ul></li>
						<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}清单</a>
							<ul>
								<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}清单维护</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/mdm/bom/bomStructAction!goList.action');">${vv:varView("vix_mdm_item")}清单资料维护</a></li>
										<li><a href="#">${vv:varView("vix_mdm_item")}清单${vv:varView("vix_mdm_item")}取代</a></li>
										<li><a href="#">${vv:varView("vix_mdm_item")}清单${vv:varView("vix_mdm_item")}删除</a></li>
										<li><a href="#">${vv:varView("vix_mdm_item")}低阶码推算</a></li>
										<li><a href="#">${vv:varView("vix_mdm_item")}清单逻辑查验</a></li>
									</ul></li>
								<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}清单查询报表</a>
									<ul>
										<li><a href="#">我的报表</a></li>
										<li><a href="#">母件结构查询-多阶</a></li>
										<li><a href="#">子件用途查询-多阶</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/singleStagetructureAction!goList.action');">母件结构表-单阶</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/multiLevelStructureAction!goList.action');">母件结构表-多阶</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/useSinglOrderAction!goList.action');">子件用途表-单阶</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/multiPurposeAction!goList.action');">子件用途表-多阶</a></li>
										<li><a href="#">母件结构表-汇总式</a></li>
										<li><a href="#">公用清单明细表</a></li>
										<li><a href="#">${vv:varView("vix_mdm_item")}清单替代料明细表</a></li>
										<li><a href="#">${vv:varView("vix_mdm_item")}清单差异比较表</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">配方管理</a>
							<ul>
								<li class="fly_top"><a href="#">设置</a></li>
								<li class="fly"><a href="#">配方清单</a>
									<ul>
										<li class="fly_top"><a href="#">新增</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/mm/recipesListAction!goList.action');">列表</a></li>
									</ul></li>
								<li class="fly_end"><a href="#">配方查询</a></li>
							</ul></li>
						<li class="fly"><a href="#">生产规划</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/planningAction!goList.action');">生产规划编制</a>
								<li><a href="#" onclick="loadContent('${vix}/mm/productForecastPlanAction!goList.action');">生产预测计划</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/mm/statisticalPlanningAction!goList.action');">规划统计</a></li>
							</ul></li>
						<li class="fly"><a href="#">主生产计划</a>
							<ul>
								<li class="fly_top"><a href="#">设置</a>
									<ul>
										<li class="fly_top"><a href="#">基本资料维护</a>
										<li><a href="#">需求来源资料维护</a></li>
										<li class="fly_end"><a href="#">MPS计划参数维护</a></li>
									</ul></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/mm/planSourceAction!goList.action');">MPS计划来源</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/planSourceAction!goSaveOrUpdate.action');">订单</a></li>
										<li><a href="#" onclick="loadContent('${vix}/mm/planSourceAction!goMakeToForecast.action');">预测</a></li>
										<!-- <li><a href="#">生产</a></li> -->
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/mm/planSourceAction!goMakeToInsert.action');">紧急插单</a></li>
									</ul></li>
								<li class="fly"><a href="#">MPS计划管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/planGenerateAction!goList.action');">MPS计划编制与生成</a> <!-- <li><a href="#">MPS计划维护</a>
									<li><a href="#">MPS计划整批删除</a> -->
										<li><a href="#">供需资料查询-订单</a>
										<li><a href="#">供需资料查询—${vv:varView("vix_mdm_item")}</a>
										<li><a href="#">供需资料查询-汇总式</a>
										<li><a href="#">供需资料查询-需求分类式</a>
										<li><a href="#">供需追溯资料查询</a>
										<li><a href="#">自动规划错误信息表</a>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/mm/selectPlanAction!goList.action');">MPS计划查询</a></li>
								<li class="fly"><a href="#">MPS计划前稽核作业</a>
									<ul>
										<li class="fly_top"><a href="#">累计提前天数推算</a>
										<li><a href="#">库存异常状况查询</a></li>
										<li><a href="#">仓库净算定义查询</a></li>
										<li><a href="#">订单异常状况查询</a></li>
										<li class="fly_end"><a href="#">库存异常状态查询</a></li>
									</ul></li>
								<li class="fly_end"><a href="#">统计与报表</a></li>
							</ul></li>
						<li class="fly"><a href="#">产能管理</a>
							<ul>
								<li class="fly"><a href="#">基本资料</a>
									<ul>
										<li class="fly_top"><a href="#">产能管理参数设定</a>
										<li><a href="#">工艺路线转资源清单</a>
										<li class="fly_end"><a href="#">资源清单维护</a>
									</ul></li>
								<li class="fly"><a href="#">资源需求计划</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/demandListAction!goList.action');">资源需求列表</a></li>
										<li><a href="#">资源需求计算</a>
										<li class="fly_end"><a href="#">资源需求汇总表</a>
									</ul></li>
								<li class="fly"><a href="#">粗能力需求计划</a>
									<ul>
										<li class="fly_top"><a href="#">粗能需求计算</a>
										<li><a href="#">粗能需求汇总表</a>
										<li class="fly_end"><a href="#">关键资源负载明细表</a>
									</ul></li>
								<li class="fly"><a href="#">能力需求计划</a>
									<ul>
										<li class="fly_top"><a href="#">能力需求计算</a>
										<li><a href="#">能力需求汇总表</a>
										<li><a href="#">产能问题核查</a>
										<li class="fly_end"><a href="#">资源负载明细表</a>
									</ul></li>
							</ul></li>

						<li class="fly"><a href="#" onclick="loadContent('${vix}/mm/demandPlanningAction!goList.action');">${vv:varView("vix_mdm_item")}需求计划</a>
							<ul>
								<li class="fly_top"><a href="#">基本资料维护</a>
								<li><a href="#">需求来源资料维护</a></li>
								<li><a href="#">MRP计划前稽核作业</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mm/operationalPlanningAction!goList.action');">计划作业</a></li>
								<li class="fly_end"><a href="#">报表</a></li>
							</ul></li>
						<li class="fly"><a href="#">生产订单</a>
							<ul>
								<li class="fly_top"><a href="#">基本资料维护</a> <%-- <li><a href="#" onclick="loadContent('${vix}/mm/pmordersAction!goList.action');">生产订单列表</a> --%>
								<li><a href="#" onclick="loadContent('${vix}/mm/pmOrderAction!goList.action');">生产订单列表</a>
								<li class="fly"><a href="#">生产订单生成</a>
									<ul>
										<li class="fly_top"><a href="#">生产订单手工输入</a>
										<li><a href="#">集合生产订单维护</a>
										<li><a href="#">重复计划手工输入</a>
										<li><a href="#">销售订单转生产订单</a>
										<li><a href="#">生产订单自动生成</a>
										<li><a href="#">重复计划自动生成</a>
										<li><a href="#">不良品返工处理</a>
									</ul></li>
								<li class="fly"><a href="#">生产订单处理</a>
									<ul>
										<li class="fly_top"><a href="#">生产订单整批处理</a>
										<li><a href="#">已审核生产订单修改</a>
										<li><a href="#">已审核重复计划修改</a>
										<li><a href="#">生产订单综合查询</a>
										<li><a href="#">生产订单插单处理</a>
										<li><a href="#">生产订单改制</a>
										<li><a href="#">生产订单挪料</a>
										<li><a href="#">生产订单改制挪料列表</a>
									</ul></li>
								<li class="fly"><a href="#">报表</a>
									<ul>
										<li class="fly_top"><a href="#">生产订单通知单</a>
										<li><a href="#">生产订单领料单</a>
										<li><a href="#">生产订单明细表</a>
										<li class="fly_end"><a href="#">重复计划明细表</a>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">车间管理</a>
							<ul>
								<li class="fly_top"><a href="#">基本资料</a>
								<li><a href="#" onclick="loadContent('${vix}/mm/productionTaskManagementAction!goList.action');">生产任务管理</a></li>
								<ul>
									<li class="fly"><a href="#">业务</a>
										<ul>
											<li class="fly_top"><a href="#">工作中心维护</a></li>
										</ul></li>
								</ul>

								<li><a href="#" onclick="loadContent('${vix}/mm/jobShopSchedulingAction!goSaveOrUpdate.action');">车间作业计划</a>
								<li class="fly"><a href="#">生产订单工序计划</a>
									<ul>
										<li class="fly_top"><a href="#">生产订单工序计划生成</a>
										<li><a href="#">生产订单工序资料维护</a>
										<li><a href="#">重覆计划工序资料维护</a>
										<li><a href="#">工序派工资料维护</a>
										<li><a href="#">工序资料整批处理</a>
										<li class="fly_end"><a href="#">工序计划产能检核</a>
									</ul></li>
								<li class="fly"><a href="#">交易处理</a>
									<ul>
										<li class="fly_top"><a href="#">报功基础设置</a>
										<li><a href="#">订单报功</a>
										<li><a href="#">工序报功</a>
										<li><a href="#">报功列表</a>
										<li><a href="#">报工单生产工时记录</a>
										<li><a href="#">工序转移单(逐笔)</a>
										<li><a href="#">工序转移单(整批)</a>
										<li><a href="#">工序转移单整批处理</a>
										<li><a href="#">工时记录单(汇总式)</a>
										<li class="fly_end"><a href="#">工时记录单(明细式)</a>
									</ul></li>
								<li class="fly"><a href="#">报表</a>
									<ul>
										<li class="fly_top"><a href="#">工序在制状况表</a>
										<li class="fly_end"><a href="#">工序完工统计表</a>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">工程变更</a>
							<ul>
								<li class="fly"><a href="#">基本资料维护</a>
									<ul>
										<li class="fly_top"><a href="#">工程变更原因资料维护</a>
										<li class="fly_end"><a href="#">工程变更等级资料维护</a>
									</ul></li>
								<li class="fly"><a href="#">单据资料维护</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/engineeringOrderMaintenanceAction!goSaveOrUpdate.action');">工程变更单维护</a>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/mm/engineeringplayMaintenanceAction!goList.action');">工程变更单处理</a>
									</ul></li>
								<li class="fly"><a href="#">工程变更资料维护</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mm/engineeringBOMmaintenanceAction!goSaveOrUpdate.action');">工程${vv:varView("vix_mdm_item")}清单维护</a>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/mm/engineeringProcessRouteMaintenanceAction!goSaveOrUpdate.action');">工程工艺路线维护</a>
									</ul></li>
								<li class="fly_end"><a href="#">报表</a></li>
							</ul></li>
						<li class="fly"><a href="#">设备管理</a>
							<ul>
								<li class=""><a href="#">基础设置</a>
									<ul>
										<li class="fly"><a href="#">基础档案</a>
											<ul>
												<li class="fly"><a href="#">客商信息</a>
													<ul>
														<li class="fly_top"><a href="#">供应商分类</a>
														<li class="fly_end"><a href="#">供应商档案</a>
													</ul></li>
												<li class="fly"><a href="#">存货</a>
													<ul>
														<li class="fly_top"><a href="#">计量单位</a>
														<li class="fly_end"><a href="#">存货档案</a>
													</ul></li>
											</ul></li>
									</ul></li>
								<li class="fly"><a href="#">设备台账</a>
									<ul>
										<li class="fly_top"><a href="#">设备类型台账</a></li>
										<li class="fly_end"><a href="#">设备台账</a></li>
									</ul></li>
								<li class="fly"><a href="#">作业管理</a>
									<ul>
										<li class="fly_top"><a href="#">作业内容</a>
										<li><a href="#">作业计划</a>
										<li class="fly_end"><a href="#">作业单</a>
									</ul></li>
								<li class="fly"><a href="#">运行管理</a>
									<ul>
										<li class="fly_top"><a href="#">测量点</a>
										<li><a href="#">测量点记录</a>
										<li><a href="#">故障记录</a>
										<li><a href="#">运行记录</a>
										<li class="fly_end"><a href="#">运行统计</a>
									</ul></li>
								<li><a href="#">备件管理</a>
									<ul>
										<li class="fly_top"><a href="#">设备备件清单</a>
										<li class="fly_end"><a href="#">备件需求统计</a>
									</ul></li>
								<li class="fly_end"><a href="#">报表</a></li>
							</ul>
					</ul></li>
				<li class="nav_arrow" id="bg_04"><a href="#"><span>财务会计</span> </a>
					<ul>
						<li class="fly"><a href="#">设置</a>
							<ul>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/optionsAction!goListContent.action');">选项</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/accountsAction!goList.action');">会计科目</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/vocherTypeAction!goList.action');">凭证类型</a></li>
										<li class="fly_end"><a href="#">外币设置</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/fa/beginningBalanceAction!goListContent.action');">期初余额</a></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/projectDirectoryAction!goListContent.action');">项目目录</a>
									<ul>
										<li class="fly_top"><a href="#">项目分类</a></li>
										<li class="fly_end"><a href="#">项目维护</a></li>
									</ul></li>
								<li><a href="#">收复结算方式</a></li>
								<li><a href="#">数据权限分配</a></li>
								<li><a href="#">金额权限分配</a></li>
								<li><a href="#">总账套打工具</a></li>
								<li class="fly_end"><a href="#">账簿清理</a></li>
							</ul></li>
						<li class="fly"><a href="#">账套管理</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/fa/ledgerAction!goSaveOrUpdate.action');">新增</a></li>
								<li><a href="#" onclick="loadContent('${vix}/fa/ledgerAction!goList.action');">列表</a></li>
								<li class="fly"><a href="#">输出</a>
									<ul>
										<li><a href="#">备份计划</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">总账</a>
							<ul>
								<li class="fly"><a href="#">凭证</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/vocherAction!goSaveOrUpdate.action');">填制凭证</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/vocherAction!goList.action');">凭证列表</a></li>
										<li><a href="#">审核凭证</a></li>
										<li><a href="#">查询凭证</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/courseSummaryAction!goListContent.action');">科目汇总</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/summaryCollectAction!goListContent.action');">摘要汇总表</a></li>
										<li><a href="#">现金流量凭证查询</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/accountingAction!goList.action');">记账</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/commonCertificateAction!goListContent.action');">常用凭证</a></li>
									</ul></li>
								<li class="fly"><a href="#">出纳</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/cashJournalAction!goListContent.action');">现金日记账</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/bankJournalAction!goListContent.action');">银行日记账</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/financialDailyReportAction!goListContent.action');">资金日报表</a></li>
										<li><a href="#">账簿打印</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/checkRegisterBookAction!goListContent.action');">支票登记簿</a></li>
										<li><a href="#">银行对账</a></li>
										<li class="fly_end"><a href="#">长期未达账审计</a></li>
									</ul></li>
								<li class="fly"><a href="#">账表</a>
									<ul>
										<li class="fly_top"><a href="#">我的账表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/subjectsAccountAction!goListContent.action');">科目账</a>
											<ul>
												<li class="fly_top"><a href="#">总账</a></li>
												<li><a href="#">余额表</a></li>
												<li><a href="#">明细账</a></li>
												<li class="fly_end"><a href="#">多栏账</a></li>
											</ul></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/customerAuxiliaryAccountAction!goListContent.action');">客户往来辅助账</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/supplierAuxiliaryAccountAction!goListContent.action');">供应商往来辅助账</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/personalAccountAccountAction!goListContent.action');">个人往来账</a></li>
										<li><a href="#">部门辅助账</a></li>
										<li><a href="#">项目辅助账</a></li>
										<li><a href="#">现金流量表</a></li>
										<li class="fly_end"><a href="#">账簿打印</a></li>
									</ul></li>
								<li class="fly"><a href="#">综合辅助账</a>
									<ul>
										<li class="fly_top"><a href="#">科目辅助明细账</a></li>
										<li class="fly_end"><a href="#">科目辅助汇总表</a></li>
									</ul></li>
								<li class="fly"><a href="#">期末</a>
									<ul>
										<li class="fly_top"><a href="#">转账定义</a></li>
										<li><a href="#">转账生成</a></li>
										<li><a href="#">对账</a></li>
										<li class="fly_end"><a href="#">结账</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">应收款管理</a>
							<ul>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/receivablesSettingsAction!goListContent.action');">设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/receivablesSettingsAction!goListContent.action');">初始设置</a></li>
										<li><a href="/vix/content/fa/gl/beginningBalance.jsp">期初余额</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/fa/receivablesManagementOptionsAction!goListContent.action');">选项</a></li>
									</ul></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/accountsDocumentsAction!goList.action');">应收单据处理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/accountsDocumentsAction!goSaveOrUpdate.action');">应收单据录入</a></li>
										<li><a href="#">应收单据审核</a></li>
										<li class="fly_end"><a href="#">合同结算单审核</a></li>
									</ul></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/receivMoneyBillsDealAction!goList.action');">收款单据处理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/receivMoneyBillsDealAction!goSaveOrUpdate.action');">收款单据录入</a></li>
										<li class="fly_end"><a href="#">收款单据审核</a></li>
									</ul></li>
								<li class="fly"><a href="#">核销处理</a>
									<ul>
										<li class="fly_top"><a href="#">手动核销</a></li>
										<li class="fly_end"><a href="#">自动核销</a></li>
									</ul></li>
								<li class="fly"><a href="#">票据管理</a>
									<ul>
										<li class="fly_top"><a href="#">票据查询</a></li>
										<li class="fly"><a href="#">票据列表</a>
											<ul>
												<li class="fly_top"><a href="#">新增</a></li>
												<li><a href="#">修改</a></li>
												<li><a href="#">删除</a></li>
												<li><a href="#">贴现</a></li>
												<li><a href="#">背书</a></li>
												<li><a href="#">转出</a></li>
												<li><a href="#">计息</a></li>
												<li class="fly_end"><a href="#">结算</a></li>
											</ul></li>
										<li><a href="#">票据新增</a></li>
									</ul></li>
								<li class="fly"><a href="#">转账</a>
									<ul>
										<li class="fly_top"><a href="#">应收冲应收</a></li>
										<li><a href="#">预收冲应收</a></li>
										<li><a href="#">应收冲应付</a></li>
										<li class="fly"><a href="#">红票对冲</a>
											<ul>
												<li class="fly_top"><a href="#">手工对冲</a></li>
												<li><a href="#">自动对冲</a></li>
											</ul>
									</ul></li>
								<li class="fly"><a href="#">坏账处理</a>
									<ul>
										<li class="fly_top"><a href="#">计提坏账准备</a></li>
										<li><a href="#">坏账发生</a></li>
										<li><a href="#">坏账收回</a></li>
										<li class="fly_end"><a href="#">坏账查询</a></li>
									</ul></li>
								<!-- <li><a href="#">汇总损益</a></li> -->
								<li><a href="#">制单处理</a></li>
								<li class="fly"><a href="#">单据查询</a>
									<ul>
										<li class="fly_top"><a href="#">发票查询</a></li>
										<li><a href="#">应收单查询</a></li>
										<li><a href="#">应付款单查询</a></li>
										<li><a href="#">凭证查询</a></li>
										<li><a href="#">单据报警查询</a></li>
										<li><a href="#">信用报警查询</a></li>
										<li><a href="#">应收核销明细表</a></li>
										<li class="fly_end"><a href="#">合同结算单查询</a></li>
									</ul></li>
								<li class="fly"><a href="#">账表管理</a>
									<ul>
										<li class="fly_top"><a href="#">我的账表</a></li>
										<li class="fly"><a href="#">业务账表</a>
											<ul>
												<li class="fly_top"><a href="#">业务总账</a></li>
												<li><a href="#">业务余额表</a></li>
												<li><a href="#">业务明细账</a></li>
												<li><a href="#">对账单</a></li>
												<li><a href="#">与总账对账</a></li>
											</ul>
										<li class="fly"><a href="#">统计分析</a>
											<ul>
												<li class="fly_top"><a href="#">应收账龄分析</a></li>
												<li><a href="#">收款账龄分析</a></li>
												<li><a href="#">欠款分析</a></li>
												<li><a href="#">收款预测</a></li>
											</ul>
										<li class="fly"><a href="#">科目账查询</a>
											<ul>
												<li class="fly_top"><a href="#">科目明细账</a></li>
												<li><a href="#">科目余额表</a></li>
											</ul>
									</ul></li>
								<li class="fly"><a href="#">期末处理</a>
									<ul>
										<li class="fly_top"><a href="#">月末结账</a></li>
										<li class="fly_end"><a href="#">取消操作</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">应付款管理</a>
							<ul>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/receivablesSettingssAction!goListContent.action');">设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/receivablesSettingssAction!goListContent.action');">初始设置</a></li>
										<li><a href="#">期初余额</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/fa/receivablesManagementOptionssAction!goListContent.action');">选项</a></li>
									</ul></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/payableOrderAction!goList.action');">应付单据处理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/payableOrderAction!goSaveOrUpdate.action');">应付单据录入</a></li>
										<li><a href="#">应付单据审核</a></li>
										<li class="fly_end"><a href="#">合同结算单审核</a></li>
									</ul></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/fa/payBillsDealAction!goList.action');">付款单据处理</a>
									<ul>
										<li class="fly_top" onclick="loadContent('${vix}/fa/payBillsDealAction!goSaveOrUpdate.action');"><a href="#">付款单据录入</a></li>
										<li><a href="#">付款单据审核</a></li>
									</ul></li>
								<li class="fly"><a href="#">核销处理</a>
									<ul>
										<li class="fly_top"><a href="#">手工核销</a></li>
										<li class="fly_end"><a href="#">自动核销</a></li>
									</ul></li>
								<li class="fly"><a href="#">票据管理</a>
									<ul>
										<li class="fly_top"><a href="#">票据查询</a></li>
										<li class="fly"><a href="#">票据列表</a>
											<ul>
												<li class="fly_top"><a href="#">新增</a></li>
												<li><a href="#">修改</a></li>
												<li><a href="#">删除</a></li>
												<li><a href="#">贴现</a></li>
												<li><a href="#">背书</a></li>
												<li><a href="#">转出</a></li>
												<li><a href="#">计息</a></li>
												<li class="fly_end"><a href="#">结算</a></li>
											</ul></li>
										<li><a href="#">票据新增</a></li>
									</ul></li>
								<li class="fly"><a href="#">转账</a>
									<ul>
										<li class="fly_top"><a href="#">应收冲应收</a></li>
										<li><a href="#">预收冲应收</a></li>
										<li><a href="#">应收冲应付</a></li>
										<li class="fly"><a href="#">红票对冲</a>
											<ul>
												<li class="fly_top"><a href="#">手工对冲</a></li>
												<li><a href="#">自动对冲</a></li>
											</ul>
									</ul></li>
								<!-- <li><a href="#">汇总损益</a></li> -->
								<li><a href="#">制单处理</a></li>
								<li class="fly"><a href="#">单据查询</a>
									<ul>
										<li class="fly_top"><a href="#">发票查询</a></li>
										<li><a href="#">应收单查询</a></li>
										<li><a href="#">应付款单查询</a></li>
										<li><a href="#">凭证查询</a></li>
										<li><a href="#">单据报警查询</a></li>
										<li><a href="#">信用报警查询</a></li>
										<li><a href="#">应收核销明细表</a></li>
										<li class="fly_end"><a href="#">合同结算单查询</a></li>
									</ul></li>
								<li class="fly"><a href="#">账表管理</a>
									<ul>
										<li class="fly_top"><a href="#">我的账表</a></li>
										<li class="fly"><a href="#">业务账表</a>
											<ul>
												<li class="fly_top"><a href="#">业务总账</a></li>
												<li><a href="#">业务余额表</a></li>
												<li><a href="#">业务明细账</a></li>
												<li><a href="#">对账单</a></li>
												<li><a href="#">与总账对账</a></li>
											</ul>
										<li class="fly"><a href="#">统计分析</a>
											<ul>
												<li class="fly_top"><a href="#">应收账龄分析</a></li>
												<li><a href="#">收款账龄分析</a></li>
												<li><a href="#">欠款分析</a></li>
												<li><a href="#">收款预测</a></li>
											</ul>
										<li class="fly"><a href="#">科目账查询</a>
											<ul>
												<li class="fly_top"><a href="#">科目明细账</a></li>
												<li><a href="#">科目余额表</a></li>
											</ul>
									</ul></li>
								<li class="fly"><a href="#">期末处理</a>
									<ul>
										<li class="fly_top"><a href="#">月末结账</a></li>
										<li class="fly_end"><a href="#">取消操作</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">固定资产</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/receivablesManagementOptionsssAction!goListContent.action');">选项</a></li>
										<li><a href="#">部门对应折旧科目</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/assetClassAction!goList.action');">资产类别</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/changeMethodAction!goList.action');">增减方式</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/usageAction!goList.action');">使用状况</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/fa/depreciationMethodsAction!goList.action');">折旧方法</a></li>
									</ul></li>
								<li class="fly"><a href="#">卡片</a>
									<ul>
										<li class="fly_top"><a href="#">卡片项目</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/cardManagementsAction!goList.action');">卡片管理</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/fa/cardManagementsAction!goSaveOrUpdate.action');">录入原始卡片</a></li>
									</ul></li>
								<li class="fly"><a href="#">处理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/assetsIncreaseAction!goList.action');">资产增加</a></li>
										<li><a href="#" onclick="loadContent('${vix}/fa/assetsReduceAction!goList.action');">资产减少</a></li>
										<li class="fly"><a href="#">变动单</a>
											<ul>
												<li class="fly_top"><a href="#" onclick="loadContent('${vix}/fa/changeOrderAction!goList.action');">变动清单</a></li>
												<li><a href="#" onclick="loadContent('${vix}/fa/originalIncreaseAction!goSaveOrUpdate.action');">原值增加</a></li>
												<li><a href="#" onclick="loadContent('${vix}/fa/sectorAction!goSaveOrUpdate.action');">部门转移</a></li>
												<li><a href="#" onclick="loadContent('${vix}/fa/usingConditionAdjustmentAction!goSaveOrUpdate.action');">使用状况调整</a></li>
												<li><a href="#" onclick="loadContent('${vix}/fa/termAdjustmentAction!goSaveOrUpdate.action');">使用年限调整</a></li>
												<li><a href="#" onclick="loadContent('${vix}/fa/depreciationAdjustedAction!goSaveOrUpdate.action');">折旧方法调整</a></li>
												<li class="fly_end"><a href="#" onclick="loadContent('${vix}/fa/accumulatedDepreciationAdjustmentAction!goSaveOrUpdate.action');">累计折旧调整</a></li>
											</ul></li>
										<li><a href="#">批量变动</a></li>
										<li><a href="#">资产评估</a></li>
										<li class="fly_end"><a href="#">资产盘点</a></li>
									</ul></li>
								<li class="fly"><a href="#">账表</a>
									<ul>
										<li class="fly_end"><a href="#">我的账表</a></li>
									</ul></li>
								<li class="fly_end"><a href="#">维护</a></li>
							</ul></li>
						<li class="fly"><a href="#">网上报销</a>
							<ul>
								<li class="fly"><a href="#">基础设置</a>
									<ul>
										<li class="fly_top"><a href="#">选项</a></li>
										<li><a href="#">业务类型</a></li>
										<li><a href="#">收支项目</a></li>
										<li><a href="#">报销标准</a></li>
										<li><a href="#">支援档案</a></li>
										<li><a href="#">出差地区</a></li>
										<li><a href="#">借款期初</a></li>
										<li class="fly_end"><a href="#">凭证设置</a></li>
									</ul></li>
								<li><a href="#">借款</a></li>
								<li><a href="#">报销</a></li>
								<li><a href="#">报销审核</a></li>
								<li><a href="#">网上支付</a></li>
								<li><a href="#">制单</a></li>
								<li class="fly"><a href="#">单据查询</a>
									<ul>
										<li class="fly_top"><a href="#">借款</a></li>
										<li><a href="#">报销</a></li>
										<li class="fly_end"><a href="#">凭证</a></li>
									</ul></li>
								<li class="fly"><a href="#">报销查询</a>
									<ul>
										<li class="fly_top"><a href="#">借款到期报警单</a></li>
										<li><a href="#">借款账龄分析</a></li>
										<li><a href="#">借款统计表</a></li>
										<li><a href="#">业务类型汇总表</a></li>
										<li class="fly_end"><a href="#">收支统计表</a></li>
									</ul></li>
								<li class="fly"><a href="#">其他</a>
									<ul>
										<li class="fly_end"><a href="#">帮助</a></li>
									</ul></li>
							</ul></li>
						<!-- 
					<li class="fly"><a href="#">网上银行</a>
						<ul>
							<li class="fly"><a href="#">设置</a>
								<ul>
									<li class="fly_top"><a href="#">银行设置</a></li>
									<li><a href="#">业务类型</a></li>
									<li><a href="#">操作员管理</a></li>
									<li><a href="#">选项</a></li>
									<li class="fly_end"><a href="#">银行服务器设置</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">单据</a>
								<ul>
									<li class="fly_top"><a href="#">单据处理</a></li>
									<li><a href="#">单据复核</a></li>
									<li><a href="#">单据审批</a></li>
									<li><a href="#">单据支付</a></li>
									<li><a href="#">支付变更</a></li>
									<li class="fly_end"><a href="#">单据查询</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">制单</a>
								<ul>
									<li class="fly_top"><a href="#">批量制单</a></li>
									<li class="fly_end"><a href="#">凭证查询</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">查询</a>
								<ul>
									<li class="fly_top"><a href="#">银行账户余额查询</a></li>
									<li><a href="#">当日交易明细查询</a></li>
									<li><a href="#">历史交易明细查询</a></li>
									<li><a href="#">交易明细与付款单据对账</a></li>
									<li class="fly_end"><a href="#">操作日志查询</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">维护</a>
								<ul>
									<li class="fly_top"><a href="#">导入工具</a></li>
									<li><a href="#">导出工具</a></li>
									<li class="fly_end"><a href="#">取消年终</a></li>
								</ul>
							</li>
						</ul>
					</li>
					 -->
						<li class="fly" style="display: none;"><a href="#">WEB财务</a>
							<ul>
								<li class="fly"><a href="#">基本设置</a>
									<ul>
										<li class="fly_top"><a href="#">权限设置</a></li>
										<li><a href="#">科目档案</a></li>
										<li><a href="#">项目档案</a></li>
										<li><a href="#">客户档案</a></li>
										<li><a href="#">供应商档案</a></li>
										<li><a href="#">部门档案</a></li>
										<li class="fly_end"><a href="#">存货档案</a></li>
									</ul></li>
								<li class="fly"><a href="#">凭证</a>
									<ul>
										<li class="fly_top"><a href="#">凭证录入</a></li>
										<li><a href="#">凭证查询</a></li>
										<li><a href="#">凭证处理</a></li>
										<li class="fly_end"><a href="#">凭证打印</a></li>
									</ul></li>
								<li class="fly"><a href="#">单据</a>
									<ul>
										<li class="fly_top"><a href="#">付款单</a></li>
										<li><a href="#">红字付款单</a></li>
										<li><a href="#">收款单</a></li>
										<li><a href="#">红字收款单</a></li>
										<li class="fly_end"><a href="#">单据列表</a></li>
									</ul></li>
								<li class="fly"><a href="#">账表</a>
									<ul>
										<li class="fly_top"><a href="#">总账</a></li>
										<li><a href="#">明细账</a></li>
										<li><a href="#">辅助明细账</a></li>
										<li><a href="#">科目日报表</a></li>
										<li><a href="#">科目余额表</a></li>
										<li><a href="#">正式账簿打印</a></li>
										<li><a href="#">应收明细账</a></li>
										<li><a href="#">应付明细账</a></li>
										<li><a href="#">应收余额表</a></li>
										<li><a href="#">应付余额表</a></li>
										<li><a href="#">应收账龄分析</a></li>
										<li><a href="#">应付账额分析</a></li>
										<li class="fly_end"><a href="#">我的账表</a></li>
									</ul></li>
								<li class="fly"><a href="#">其他</a>
									<ul>
										<li class="fly_end"><a href="#">帮助</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">财务报表</a>
							<ul>
								<li><a href="#">现金流量表</a></li>
								<li><a href="#">资产负债表</a></li>
								<li><a href="#">所有者权益表</a></li>
							</ul></li>
						<li class="fly"><a href="#">公司对账</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly_end"><a href="#">对账单设置</a></li>
									</ul></li>
								<li class="fly"><a href="#">往来对账单</a>
									<ul>
										<li class="fly_top"><a href="#">往来对账单查询</a></li>
										<li><a href="#">单位账导出</a></li>
										<li class="fly_end"><a href="#">对方账导入</a></li>
									</ul></li>
								<li><a href="#">往来自动对账</a></li>
								<li class="fly"><a href="#">账表</a>
									<ul>
										<li class="fly_top"><a href="#">我的账表</a></li>
										<li><a href="#">对账结果汇总查询</a></li>
										<li class="fly_end"><a href="#">对账结果明细查询</a></li>
									</ul></li>
							</ul></li>
						<!-- 
					<li><a href="#">票据通</a></li>
					<li class="fly"><a href="#">报账中心</a>
						<ul>
							<li class="fly"><a href="#">基础设置</a>
								<ul>
									<li class="fly_top"><a href="#">系统选项</a></li>
									<li><a href="#">单位分类</a></li>
									<li><a href="#">单位目录</a></li>
									<li><a href="#">开户银行</a></li>
									<li><a href="#">期初余额</a></li>
									<li><a href="#">收支项目</a></li>
									<li><a href="#">资金性质</a></li>
									<li><a href="#">预算计划</a></li>
									<li><a href="#">凭证设置</a></li>
									<li class="fly_end"><a href="#">授权管理</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">日常处理</a>
								<ul>
									<li class="fly_top"><a href="#">单据录入</a></li>
									<li><a href="#">单据审核</a></li>
									<li><a href="#">网上支付</a></li>
									<li class="fly_end"><a href="#">生成凭证</a></li>
								</ul>
							</li>
							<li><a href="#">单据查询</a></li>
							<li><a href="#">凭证查询</a></li>
							<li class="fly"><a href="#">报表查询</a>
								<ul>
									<li class="fly_top"><a href="#">单位余额表</a></li>
									<li><a href="#">收支明细表</a></li>
									<li><a href="#">银行存款现金余额表</a></li>
									<li><a href="#">银行存款现金明细表</a></li>
									<li><a href="#">收入汇总表</a></li>
									<li><a href="#">支出汇总表</a></li>
									<li class="fly_end"><a href="#">单位预算余额表</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">期末处理</a>
								<ul>
									<li class="fly_top"><a href="#">月末结账</a></li>
									<li class="fly_end"><a href="#">取消结账</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">其他</a>
								<ul>
									<li class="fly_end"><a href="#">帮助</a></li>
								</ul>
							</li>
						</ul>
					</li>
					 -->
					</ul></li>
				<li class="nav_arrow" id="bg_05"><a href="#"><span>管理会计</span> </a>
					<ul>
						<li class="fly"><a href="#">成本管理</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/ma/maTypeSettingsAction!goSaveOrUpdate.action');">选项</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/costManagementPlanAction!goList.action');">成本方案管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/defineOrganizationAction!goList.action');">定义成本中心</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/definitionAttributesAction!goList.action');">定义产品属性</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/defineExpensesAndGeneralAction!goList.action');">定义费用明细与总账接口</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/defineDistributionRateAction!goSaveOrUpdate.action');">定义分配率</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/quotaManagementAction!goList.action');">定额管理</a></li>
										<li class="fly_end"><a href="#">建账期初余额</a></li>
									</ul></li>
								<li class="fly"><a href="#">数据录入</a>
									<ul>
										<li class="fly_top"><a href="#">期初在产调整表</a></li>
										<li><a href="#">材料及外购半成品耗用</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/laborCostsAction!goSaveOrUpdate.action');">人工费用表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/depreciationExpenseAction!goSaveOrUpdate.action');">折旧费用表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/manufacturingCostAction!goSaveOrUpdate.action');">制造费用表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/otherExpensesAction!goSaveOrUpdate.action');">其他费用表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/workingHoursDailyAction!goSaveOrUpdate.action');">工时日报表</a></li>
										<li><a href="#">完工产品日报表</a></li>
										<li><a href="#">废品回收表</a></li>
										<li><a href="#">在产品盘点表</a></li>
										<li><a href="#">产品耗用日报表</a></li>
										<li><a href="#">完工产品日报表</a></li>
										<li><a href="#">在产品每月变动约挡数</a></li>
										<li><a href="#">分配标准表</a></li>
										<li class="fly_end"><a href="#">产品材料定额每月变动表</a></li>
									</ul></li>
								<li class="fly"><a href="#">核算</a>
									<ul>
										<li class="fly_top"><a href="#">我的账表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/costCalculationAction!goList.action');">成本计算</a></li>
										<li class="fly_end"><a href="#">凭证处理</a></li>
									</ul></li>
								<li class="fly"><a href="#">计划</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/ma/planningUnitAction!goSaveOrUpdate.action');">制定计划单价</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/costProductConsumptionAction!goList.action');">单位产品费用耗用量</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/generationPlanCostAction!goList.action');">生成计划成本</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/ma/generationStandardCostAction!goList.action');">生成标准成本</a></li>
									</ul></li>
								<li><a href="#">预测</a></li>
								<li class="fly_end"><a href="#">分析</a></li>
							</ul></li>
						<li class="fly"><a href="#">项目成本管理</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly"><a href="#">基础资料维护</a>
											<ul>
												<li class="fly_top"><a href="#" onclick="loadContent('${vix}/ma/costManagementAction!goList.action');">成本项管理</a></li>
												<li class="fly_end"><a href="#" onclick="loadContent('${vix}/ma/resourceManagementAction!goList.action');">资源项管理</a></li>
											</ul></li>
										<li class="fly"><a href="#">系统初始化</a>
											<ul>
												<li class="fly_top"><a href="#" onclick="loadContent('${vix}/ma/projectCostPlanAction!goList.action');">项目成本方案</a></li>
											</ul></li>
										<li><a href="#">用户管理</a></li>
										<li><a href="#">日志信息</a></li>
									</ul></li>
								<li class="fly"><a href="#">项目规划</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/ma/budgetPreparationAction!goList.action');">概算编制</a></li>
										<!-- <li><a href="#">概算发布</a></li>
									<li><a href="#">概算调整</a></li> -->
										<li><a href="#" href="#" onclick="loadContent('${vix}/ma/approximateQueryAction!goSaveOrUpdate.action');">概算查询</a></li>
									</ul></li>
								<li class="fly"><a href="#">项目执行</a>
									<ul>
										<li><a href="#">项目订单</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/singleProjectBudgetAction!goList.action');">项目收支单</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/purchaseQueryAction!goSaveOrUpdate.action');">项目采购查询</a></li>
										<li><a href="#">项目收入查询</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/payQueryAction!goSaveOrUpdate.action');">项目支出查询</a></li>
										<li><a href="#">项目付款查询</a></li>
										<li><a href="#">项目核算查询</a></li>
										<li><a href="#">进度报告</a></li>
										<li><a href="#">甘特图</a></li>
									</ul></li>
								<li class="fly"><a href="#">项目终止</a>
									<ul>
										<li><a href="#">费用分摊</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/accountProjectAction!goList.action');">项目决算</a></li>
										<li><a href="#">成本汇总</a></li>
										<li><a href="#">预算资产</a></li>
										<!-- <li><a href="#">关闭项目</a></li> -->
									</ul></li>
								<li class="fly"><a href="#">报表</a>
									<ul>
										<li><a href="#">工作分解结构报表</a></li>
										<li><a href="#">按任务列示的概算报表</a></li>
										<li><a href="#">按资源列示的概算报表</a></li>
										<li><a href="#">按任务列示的支出汇总表和明细表</a></li>
										<li><a href="#">按资源列示的支出汇总表和明细表</a></li>
										<li><a href="#">决算报表</a></li>
										<li><a href="#">其他报表</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">预算管理</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly"><a href="#">模版管理</a>
											<ul>
												<li class="fly_top"><a href="#">模版设置</a></li>
												<li><a href="#">定义规则</a></li>
												<li class="fly_end"><a href="#">预算定制</a></li>
											</ul></li>
										<li class="fly"><a href="#">维度管理</a>
											<ul>
												<li class="fly_top"><a href="#">方案</a></li>
												<li><a href="#" onclick="loadContent('${vix}/ma/budgetVersionAction!goList.action');">版本</a></li>
												<li class="fly_end"><a href="#">期间</a></li>
											</ul></li>
										<li class="fly"><a href="#">业务监控</a>
											<ul>
												<li class="fly_top"><a href="#" onclick="loadContent('${vix}/ma/budgetProgressAction!goList.action');">进度</a></li>
												<li class="fly_end"><a href="#">流程</a></li>
											</ul></li>
										<!-- <li class="fly_end"><a href="#">样式库</a></li> -->
									</ul></li>
								<li class="fly"><a href="#">预算体系</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/ma/budgetAccountsAction!goList.action');">预算科目</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/budgetOrganizationAction!goList.action');">预算组织</a></li>
										<li><a href="#" onclick="loadContent('${vix}/ma/budgetProgramAction!goList.action');">预算方案</a></li>
										<li><a href="#">审核流程设置</a></li>
									</ul></li>
								<li class="fly"><a href="#">日常管理</a>
									<ul>
										<li><a href="#">预算版本管理</a></li>
										<li><a href="#">预算进程监控</a></li>
										<li><a href="#">审核过程监控</a></li>
										<li><a href="#">审核日志查询</a></li>
										<li><a href="#">预算角色维护</a></li>
										<li><a href="#">消息管理</a></li>
									</ul></li>
								<!-- 
							<li class="fly"><a href="#">目标管理</a>
								<ul>
									<li><a href="#">目标设定</a></li>
									<li><a href="#">目标报审</a></li>
									<li><a href="#">目标审核</a></li>
								</ul>
							</li>
							 -->
								<li class="fly"><a href="#">预算编制处理</a>
									<ul>
										<li class="fly"><a href="#" onclick="loadContent('${vix}/ma/budgetEstablishmentAction!goList.action');">预算编制列表</a>
											<ul>
												<li class="fly_top"><a href="#">上报</a></li>
												<li><a href="#">汇总</a></li>
												<li><a href="#">审核</a></li>
												<li class="fly_end"><a href="#">分解</a></li>
											</ul></li>
										<li class="fly"><a href="#" onclick="loadContent('${vix}/ma/budgetEstablishmentAction!goList.action');">预算编制</a>
											<ul>
												<li class="fly_top"><a href="#" onclick="loadContent('${vix}/ma/createTableAction!goList.action');">创建表格</a></li>
												<li class="fly_end"><a href="#" onclick="loadContent('${vix}/ma/dataEntryAction!goList.action');">数据录入</a></li>
											</ul></li>
										<li><a href="#">预算报审</a></li>
										<li><a href="#">预算汇总</a></li>
										<li><a href="#">预算审核</a></li>
										<li><a href="#">预算分解</a></li>
									</ul></li>
								<li class="fly"><a href="#">预算调整</a>
									<ul>
										<li><a href="#">追加预算</a></li>
										<li><a href="#">削减预算</a></li>
									</ul></li>
								<li class="fly"><a href="#">预算控制</a>
									<ul>
										<li><a href="#">费用申请、核销等</a></li>
										<li><a href="#">材料采购支出</a></li>
										<li><a href="#">资本支出</a></li>
										<li><a href="#">资金安全量预警</a></li>
									</ul></li>
								<li class="fly"><a href="#">预算查询</a>
									<ul>
										<li><a href="#">预算查询</a></li>
									</ul></li>
								<li class="fly"><a href="#">分析报告</a>
									<ul>
										<li><a href="#">预算分析</a></li>
									</ul></li>
								<li class="fly"><a href="#">预算差异分析及考核</a>
									<ul>
										<li><a href="#">预算差异分析及考核</a></li>
									</ul></li>
								<li class="fly"><a href="#">系统接口</a>
									<ul>
										<li><a href="#">数据导出</a></li>
										<li><a href="#">数据导入</a></li>
										<li><a href="#">日志查询</a></li>
									</ul></li>
							</ul></li>
						<!-- <li class="fly"><a href="#">资金管理</a>
						<ul>
							<li><a href="#">资金计划管理</a></li>
							<li class="fly"><a href="#">账户管理</a>
								<ul>
									<li><a href="#">开立账户</a></li>
									<li><a href="#">账户收支管理</a></li>
									<li><a href="#">银企直联</a></li>
									<li><a href="#">资金监控</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">资金结算</a>
								<ul>
									<li><a href="#">资金上划</a></li>
									<li><a href="#">资金下拨</a></li>
									<li><a href="#">资金调拨</a></li>
									<li class="fly"><a href="#">收付结算</a>
										<ul>
											<li><a href="#">委托收款</a></li>
											<li><a href="#">委托付款</a></li>
										</ul>
									</li>
									<li class="fly"><a href="#">收付结算</a>
										<ul>
											<li><a href="#">活期存款</a></li>
											<li><a href="#">定期存款</a></li>
											<li><a href="#">通知存款</a></li>
											<li><a href="#">协议存款</a></li>
										</ul>
									</li>
									<li><a href="#">日终处理</a></li>
									<li><a href="#">利息计算</a></li>
									<li><a href="#">统计查询</a></li>
									<li><a href="#">银行对账</a></li>
								</ul>
							</li>
							<li><a href="#">资金核算</a></li>
							<li class="fly"><a href="#">融资业务管理</a>
								<ul>
									<li><a href="#">综合授信管理</a></li>
									<li><a href="#">担保业务管理</a></li>
								</ul>
							</li>
							<li><a href="#">资金安全管理</a></li>
						</ul>
					</li> -->
						<!-- <li><a href="#">资金预算管理</a></li> -->
						<!-- <li class="fly"><a href="#">资金管理2</a>
						<ul>
							<li class="fly"><a href="#">基础设置</a>
								<ul>
							<li class="fly"><a href="#">定义预测指标</a>
								<ul>
									<li><a href="#">系统预置预测指标</a></li>
									<li><a href="#">总账预测指标</a></li>
									<li><a href="#">自定义预测指标</a></li>
								</ul>
							</li>
							<li><a href="#">收付款预期</a></li>
							<li><a href="#">设置预测模版</a></li>
							<li><a href="#">定义筹投资类别</a></li>
							<li><a href="#">单位定义</a></li>
							<li><a href="#">单据科目设置</a></li>
							<li><a href="#">系统选项</a></li>
								</ul>
							</li>
							<li class="fly"><a href="#">资金预测</a>
								<ul>
									<li><a href="#">录入数据</a></li>
									<li><a href="#">预测定义</a></li>
									<li><a href="#">期初余额</a></li>
									<li><a href="#">采集数据</a></li>
									<li><a href="#">余额预测</a></li>
									<li><a href="#">数据分解</a></li>
									<li><a href="#">资金预测</a></li>
									<li><a href="#">资金分析</a></li>
									<li><a href="#">资金计划</a></li>
								</ul>
							</li>
						</ul>
					</li> -->
						<li class="fly"><a href="#">筹投资计划</a>
							<ul>
								<li class="fly"><a href="#">期初单据</a>
									<ul>
										<li><a href="#">债权筹资单</a></li>
										<li><a href="#">债权筹资还款单</a></li>
										<li><a href="#">债权筹资利息单</a></li>
										<li><a href="#">股权筹资单</a></li>
										<li><a href="#">股权筹资还款单</a></li>
										<li><a href="#">股权筹资股利单</a></li>
										<li><a href="#">债权投资单</a></li>
										<li><a href="#">债权投资回收单</a></li>
										<li><a href="#">债权投资利息单</a></li>
										<li><a href="#">股权投资单</a></li>
										<li><a href="#">股权投资回收单</a></li>
										<li><a href="#">股权投资收益单</a></li>
										<li><a href="#">定期存款单</a></li>
										<li><a href="#">定期取款单</a></li>
										<li><a href="#">担保单</a></li>
									</ul></li>
								<li class="fly"><a href="#">查询单据</a>
									<ul>
										<li><a href="#">债权筹资单</a></li>
										<li><a href="#">债权筹资还款单</a></li>
										<li><a href="#">债权筹资利息单</a></li>
										<li><a href="#">股权筹资单</a></li>
										<li><a href="#">股权筹资还款单</a></li>
										<li><a href="#">股权筹资股利单</a></li>
										<li><a href="#">债权投资单</a></li>
										<li><a href="#">债权投资回收单</a></li>
										<li><a href="#">债权投资利息单</a></li>
										<li><a href="#">股权投资单</a></li>
										<li><a href="#">股权投资回收单</a></li>
										<li><a href="#">股权投资收益单</a></li>
										<li><a href="#">定期存款单</a></li>
										<li><a href="#">定期取款单</a></li>
										<li><a href="#">担保单</a></li>
									</ul></li>
								<li><a href="#">凭证批处理</a></li>
								<li><a href="#">凭证查询</a></li>
							</ul></li>
					</ul></li>
				<li class="nav_arrow" id="bg_06"><a href="#"><span>人力资源</span> </a>
					<ul>
						<li class="fly"><a href="#">基础设置</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/hr/hrTypeSettingsAction!goSaveOrUpdate.action','bg_06');">系统参数</a></li>
								<li><a href="#">流程设置</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/hrCanlendarAction!goList.action','bg_06');">公共日历</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/hrattendSettingsAction!goSaveOrUpdate.action','bg_06');">考勤设置</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/titleSysAction!goList.action','bg_06');">职称体系</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/postSysAction!goList.action','bg_06');">职务体系</a></li>
								<li class="fly"><a href="#">教育培训</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/qualityIndexAction!goList.action','bg_06');">素质指标</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/courseNatureAction!goList.action','bg_06');">课程性质</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/trainCategoryAction!goList.action','bg_06');">培训类别</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/courseTypeAction!goList.action','bg_06');">课程类别</a></li>
									</ul></li>
								<li class="fly"><a href="#">职称设置</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/titleTypeAction!goList.action','bg_06');">职称类型</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/titleGradeAction!goList.action','bg_06');">职称等级</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/technicalLevelAction!goList.action','bg_06');">技术水平</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/proficiencyAction!goList.action','bg_06');">熟练程度</a></li>
									</ul></li>
								<li class="fly"><a href="#">职务设置</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/postsysTypesAction!goList.action','bg_06');">职务类型</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/rankAction!goList.action','bg_06');">等级</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/gradingAction!goList.action','bg_06');">职等</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/powersAction!goList.action','bg_06');">职权</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">组织管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/org/organizationAction!goList.action','bg_06');">组织架构</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/orgChartAction!goList.action','bg_06');">组织架构图</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/org/organizationUnitAction!goList.action','bg_06');">部门管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action','bg_06');">岗位管理</a></li>
								<!-- <li><a href="#">高级模式</a></li> -->
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/hr/advancedSearchAction!goSaveOrUpdate.action','bg_06');">高级查询</a></li>
							</ul></li>
						<li class="fly"><a href="#">员工管理</a>
							<ul>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action','bg_06');">员工信息管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action','bg_06');">列表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/staffRosterAction!goList.action','bg_06');">花名册</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/employeesAction!goList.action','bg_06');">在职管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/employeessAction!goList.action','bg_06');">后备人才管理</a></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/hr/probationAction!goList.action');">人事事务管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/hr/probationAction!goList.action','bg_06');">列表</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdate.action','bg_06');">转正</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateXtaffTurnover.action''bg_06');">异动</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateStaffSecondments.action','bg_06');">借调</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateAskForLeave.action','bg_06');">请假</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateXresign.action','bg_06');">离职</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateDismiss.action','bg_06');">辞退</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateRetirement.action','bg_06');">离退休</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateBridget.action','bg_06');">返聘</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/agreementAction!goList.action','bg_06');">合同管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/employeeOrgFileAction!goList.action','bg_06');">档案查询</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/inceManageAction!goList.action','bg_06');">奖惩管理</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/hr/policiesAction!goList.action','bg_06');">政策制度管理</a></li>
							</ul></li>
						<li class="fly"><a href="#">薪酬管理</a>
							<ul>
								<li class="fly"><a href="#">设置</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/hr/salary/salaryProjectAction!goList.action');">工资类别设置</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/salary/salaryProjectItemModAction!goList.action');">工资项设置</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/salary/salaryProjectOptionAction!goList.action');">辅助项设置</a></li>
										<li><a href="#" onclick="loadContent('');">员工薪资属性设置</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/hr/salary/salaryProjectEmpAction!goList.action');">员工工资项设置</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/wagesManageAction!goList.action','bg_06');">工资模型定义</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/definingSalaryAction!goList.action','bg_06');">工资项管理</a></li>
								<li class="fly"><a href="#">业务处理</a>
									<ul>
										<li class="fly_top"><a href="#">工资变动</a></li>
										<li><a href="#">工资分钱清单</a></li>
										<li><a href="#">扣缴所得税</a></li>
										<li class="fly_end"><a href="#">银行待发</a></li>
									</ul></li>
								<li class="fly_top"><a href="#">薪资标准</a>
								<li><a href="#">薪资调整</a></li>
								<li class="fly_end"><a href="#">统计分析与报表</a></li>
							</ul></li>
						<li class="fly"><a href="#">保险福利管理</a>
							<ul>
								<li class="fly_top"><a href="#">基础设置</a></li>
								<li class="fly"><a href="#">福利业务</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/welfareArchivesAction!goList.action','bg_06');">福利档案</a></li>
										<li><a href="#">福利缴交</a></li>
										<li><a href="#">费用分摊</a></li>
										<li><a href="#">凭证查询</a></li>
										<li><a href="#">期末处理</a></li>
									</ul></li>
								<li class="fly_end"><a href="#">统计分析</a></li>
							</ul></li>
						<li class="fly"><a href="#">教育培训</a>
							<ul>
								<li class="fly"><a href="#">培训计划</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/trainPlanAction!goList.action','bg_06');">活动计划</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/planAction!goList.action','bg_06');">填报计划</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/planTrainCourseAction!goList.action','bg_06');">计划汇总</a>
									</ul></li>
								<li class="fly"><a href="#">培训需求</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/demandNoticeAction!goList.action','bg_06');">需求通知</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/demandApplyAction!goList.action','bg_06');">需求申请</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/demandSummaryAction!goList.action','bg_06');">需求汇总</a>
									</ul></li>
								<%-- <li><a href="#" onclick="loadContent('${vix}/hr/trainImpAction!goList.action','bg_06');">培训活动1</a></li> --%>
								<li><a href="#" onclick="loadContent('${vix}/hr/trainingActivityAction!goList.action','bg_06');">培训活动</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/trainingCostAction!goList.action','bg_06');">培训费用</a></li>
								<%-- <li><a href="#" onclick="loadContent('${vix}/hr/trainCostAction!goList.action','bg_06');">培训费用1</a></li> --%>
								<li><a href="#" onclick="loadContent('${vix}/hr/trainingCMAction!goList.action','bg_06');">培训课程管理</a></li>
								<li class="fly"><a href="#">培训资源</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/hr/trainingCourseAction!goList.action','bg_06');">培训课程</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/trainingDataAction!goList.action','bg_06');">培训资料</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/trainingChannelAction!goList.action','bg_06');">培训渠道</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/trainingLecturerAction!goList.action','bg_06');">培训讲师</a> <%-- <li class="fly_end"><a href="#" onclick="loadContent('${vix}/hr/trainingFacilitiesAction!goList.action','bg_06');">培训设施</a> --%>
									</ul></li>
								<li><a href="#">查询统计</a></li>
								<li><a href="#">在线考试</a></li>
								<li><a href="#">专业题库</a></li>
								<li><a href="#">问卷调查</a></li>
								<li class="fly_end"><a href="#">图书管理</a></li>
							</ul></li>
						<li class="fly"><a href="#">考勤管理</a>
							<ul>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/hr/hrattendSettingsAction!goSaveOrUpdate.action','bg_06');">考勤设置</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/attendanceTypeAction!goList.action','bg_06');">考勤类别</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/attendanceFrequencyAction!goList.action','bg_06');">考勤班次</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/attendanceTeamAction!goList.action','bg_06');">考勤班组</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/attendanceUniversalAction!goList.action','bg_06');">通用考勤制度</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/attendanceSpecialAction!goList.action','bg_06');">特殊考勤制度</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/curManageAction!goList.action','bg_06');">日常管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/attendanceRecordAction!goList.action','bg_06');">考勤记录</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/punchRecordsAction!goList.action','bg_06');">打卡机记录</a></li>
								<li><a href="#">申请登记管理</a></li>
								<li class="fly_end"><a href="#">统计</a></li>
							</ul></li>
						<li class="fly"><a href="#">招聘管理</a>
							<ul>
								<li class="fly_top"><a href="#">招聘计划</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/rePlanAction!goList.action','bg_06');">填报</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/recruitmentPlanSummaryAction!goList.action','bg_06');">汇总</a>
										<li><a href="#" onclick="loadContent('${vix}/hr/recruitmentPlanIssuedAction!goList.action','bg_06');">下达</a>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/reCollAction!goList.action','bg_06');">招聘征集</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/empAAppAction!goList.action','bg_06');">用人申请</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/reActAction!goList.action','bg_06');">招聘活动</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/seniorReAction!goList.action','bg_06');">高级人才招聘</a></li>
								<li class="fly"><a href="#">甄选录用</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/hr/dataScreAction!goList.action','bg_06');">资料筛选</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/audiManageAction!goList.action','bg_06');">面试管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/hirManageAction!goList.action','bg_06');">录用管理</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/hr/backupResumeDatabaseAciton!goList.action','bg_06');">后备简历库</a></li>
									</ul></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/hr/reSumAciton!goList.action','bg_06');">招聘总结</a></li>

								<!-- <li ><a href="#">招聘通知</a></li> -->
							</ul></li>
						<!-- <li class="fly"><a href="#">毕业生管理</a>
						<ul>
							<li class="fly_top"><a href="#">毕业生计划</a>
							<li><a href="#">毕业生招聘</a></li>
							<li class="fly_end"><a href="#">查询与报表</a>
							</li>
						</ul>
					</li> -->
						<li class="fly"><a href="#" onclick="loadContent('${vix}/hr/hrEmpstaffselfAction!goSaveOrUpdate.action','bg_06');">员工自助</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/hr/employeeQueryAction!goList.action','bg_06');">个人信息维护</a>
								<li><a href="#" onclick="loadContent('${vix}/hr/salaryInformationAction!goList.action','bg_06');">薪资查询</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/jobPerformanceAction!goList.action','bg_06');">绩效考核</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/responsibilitiesAction!goList.action','bg_06');">岗位说明</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/hr/applyregistrationAction!goList.action','bg_06');">应聘申请</a></li>
							</ul></li>
						<li class="fly"><a href="#">绩效管理</a>
							<ul>
								<li class="fly_top"><a href="#">基础设置</a></li>
								<li><a href="#">绩效计划</a></li>
								<li><a href="#">考评结果</a></li>
								<li><a href="#">绩效反馈</a></li>
								<li><a href="#">Web应用</a></li>
								<li class="fly_end"><a href="#">统计分析</a></li>
							</ul></li>
						<li><a href="#" onclick="loadContent('${vix}/hr/hrstaffselfAction!goSaveOrUpdate.action','bg_06');">综合管理监控平台</a></li>
						<li class="fly"><a href="">统计报表</a>
							<ul>
								<li class="fly_top"><a href="#">组织结构</a></li>
								<li><a href="#">机构单位</a></li>
								<li><a href="#">岗位报表</a></li>
								<li><a href="#">员工花名册</a></li>
								<li><a href="#">薪资报表</a></li>
								<li><a href="#">招聘报表</a></li>
								<li><a href="#">毕业生报表</a></li>
								<li><a href="#">教育培训报表</a></li>
								<li><a href="#">考勤报表</a></li>
								<li class="fly"><a href="/vix/content/hr/im/ws.jsp">人资原界面</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/essentialinformationAction!essentialinFormation.action');">新增</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/workingsituationAction!goSaveOrUpdate.action');">工作情况</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/personalskillsAction!goSaveOrUpdate.action');">个人技能</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/relationshipAction!goSaveOrUpdate.action');">关系情况</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/organization/orgAction!goList.action');">组织架构原界面</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/organization/deparManageAction!goList.action');">部门管理原界面</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/organization/postManageAction!goList.action');">岗位管理原界面</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/orgItemAction!goSaveOrUpdate.action');">员工向导页</a></li>
									</ul></li>
							</ul></li>
					</ul></li>
				<li class="nav_arrow" id="bg_07"><a href="#"><span>协同办公</span> </a>
					<ul>
						<li class="fly"><a href="#">个人办公</a>
							<ul>
								<%-- <li class="fly_top"><a href="#"
								onclick="loadContent('${vix}/oa/emailAction!goList.action');">电子邮件</a> --%>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');">电子邮件</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/messageManagementAction!goList.action');">消息管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/phoneSmsAction!goList.action');">手机短信</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/announcementAction!goList.action');">公告通知</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/newsManagementAction!goList.action');">新闻</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/voTeAction!goList.action');">投票</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/scHeduleAction!goCanlendar.action');">日程安排</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/workLogAction!goList.action');">工作日志</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/personalAttendanceAction!goList.action');">个人考勤</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/travelClaimsAction!goList.action','bg_07');">日常报销</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/wabAction!goList.action');">通讯簿</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/privateCabinetAction!goList.action');">个人文件柜</a></li>
							</ul></li>
						<li class="fly"><a href="#">任务管理</a>
							<ul>
								<li class="fly"><a href="#">初始设置</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/taskSourceTypeAction!goList.action');">任务来源</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/taskTypeAction!goList.action');">任务类型</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/difficultyCoefficientAction!goList.action');">难度系数</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/taskLevelAction!goList.action');">任务层级</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/completionMarkAction!goList.action');">完成标志</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/nissinManagementAction!goList.action');">日清管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goSaveOrUpdate.action');">任务定义</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action');">任务列表</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/myTasksAction!goList.action');">我的任务</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/taskPandectAction!goList.action');">任务总览</a></li>
							</ul></li>
						<li class="fly"><a href="#">公文管理</a>
							<ul>
								<li class="fly"><a href="#">发文管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/odDispatchAction!goList.action');">发文拟稿</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/odDispatchCheckAction!goList.action');">发文核稿</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/odDispatchSealAction!goList.action');">套红盖章</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/odDispatchPubAction!goList.action');">公文发布</a></li>
									</ul></li>
								<li class="fly"><a href="#">收文管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/odReceiveAction!goList.action');">收文登记</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/odReceiveAuditAction!goList.action');">领导批阅</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/odReceiveDistributeAction!goList.action');">收文分发</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/odReceiveReadAction!goList.action');">收文阅读</a></li>
									</ul></li>
								<li class="fly"><a href="#">基础设置</a>
									<ul>
										<li class="fly_top"><a href="#">参数设置</a></li>
										<li><a href="/vix/content/oa/DocumentManagement/dm_mesh.jsp">主题词管理</a></li>
										<li><a href="#">公文类型设置</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/odParamTypeAction!goList.action');">收文类型设定</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/oa/administrativeAction!goList.action');">行政办公中心</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/form/businessFormdata_list.jsp');">表单管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/form/formAction!goList.action');">待办任务</a></li>
								<li><a href="#" onclick="loadContent('${vix}/form/donetask_list.jsp');">完结任务</a></li>
							</ul></li>
						<li class="fly"><a href="#">工作流</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/oa/oaFlowWorkAction!goList.action');">新建流程</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/oaFlowMyWorkAction!goList.action');">我的工作</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/oaFlowSearchWorkAction!goList.action');">工作查询</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/oaFlowMonitorWorkAction!goList.action');">工作监控</a></li>
								<li><a href="#">数据报表</a></li>
								<li><a href="#">超时统计分析</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/oaFlowEntrustWorkAction!goList.action');">工作委托</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/oaFlowDestroyWorkAction!goList.action');">工作销毁</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/oaFlowLogSearchAction!goList.action');">流程日志查询</a></li>
							</ul></li>
						<li class="fly"><a href="#">行政办公</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/oa/announcementNotificationAction!goList.action');">公告通知管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/trendsAction!goList.action');">新闻管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/votingManagementAction!goList.action');">投票管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/schedulesAction!goList.action');">日程安排管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/workLogqueryAction!goList.action');">工作日志管理</a></li>
								<li><a href="#">管理驾驶舱</a></li>
								<li class="fly"><a href="#">工作计划</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/workPlanAction!goList.action');">工作计划查询</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action');">工作计划管理</a> <!-- 后面如果不需要再删除 --> <%-- <li><a href="#" onclick="loadContent('${vix}/oa/projectSettingsAction!goListType.action');">工作计划类型设置</a></li>--%>
									</ul></li>
								<li class="fly"><a href="#">办公用品管理</a>
									<ul>
										<%-- <li class="fly"><a href="#">初始设置</a>
										<ul>
											<li><a href="#"
												onclick="loadContent('${vix}/oa/officeSuppliesTypeAction!goList.action');">办公用品类别</a>
											</li>
										</ul>
									</li> --%>
										<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesAction!goList.action');">办公用品台帐</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesRegisterAction!goList.action');">办公用品业务管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">报销管理</a>
									<ul>
										<li class="fly"><a href="#">设置</a>
											<ul>
												<li><a href="#" onclick="loadContent('${vix}/oa/accountMaintenanceAction!goList.action','bg_07');">费用项目维护</a>
												<li><a href="#" onclick="loadContent('${vix}/oa/areaLevelAction!goList.action','bg_07');">地区设置</a>
												<li><a href="#" onclick="loadContent('${vix}/oa/vehicleAction!goList.action','bg_07');">交通工具设置</a>
												<li><a href="#" onclick="loadContent('${vix}/oa/areaExpensesStandardsAction!goList.action','bg_07');">交通报销标准</a>
												<li><a href="#" onclick="loadContent('${vix}/oa/dailyExpensesStandardsAction!goList.action','bg_07');">日常报销标准</a>
												<li><a href="#" onclick="loadContent('${vix}/oa/accomodationsExpensesStandardsAction!goList.action','bg_07');">住宿报销标准</a>
											</ul></li>
										<%-- <li><a href="#" onclick="loadContent('${vix}/oa/currentexpenseAction!goList.action','bg_07');">日常费用报销</a></li> --%>
										<li><a href="#" onclick="loadContent('${vix}/oa/travelClaimsAction!goList.action','bg_07');">报销管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/expensesSummaryPersonAction!goList.action','bg_07');">报销汇总(人员)</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/expensesSummaryDepartmentAction!goList.action','bg_07');">报销汇总(部门)</a></li>
									</ul></li>
								<li><a href="#">固定资产</a></li>
								<li class="fly"><a href="#">图书管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/bookEntryAction!goList.action');">图书录入</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action');">借还书管理</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/resourceRequestAction!goList.action');">资源申请管理</a></li>
								<li class="fly"><a href="#">会议室管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/applicationMeetingAction!goList.action');">会议申请</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/meetingRManagementAction!goList.action');">会议申请管理</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/meetingQueryAction!goList.action');">会议室总览</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/meetingRequestAction!goList.action');">会议室基础数据</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action');">会议室设备管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">车辆申请安排</a>
									<ul>
										<li class="fly"><a href="#">初始设置</a>
											<ul>
												<li><a href="#" onclick="loadContent('${vix}/oa/vehicleTypeAction!goList.action');">车辆类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/vehicleColorAction!goList.action');">车辆颜色</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/transmissionTypeAction!goList.action');">变速器类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/engineTypeAction!goList.action');">引擎类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/displacementTypeAction!goList.action');">排量类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/maintenanceTypeAction!goList.action');">维护类型</a></li>
											</ul></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/vehicleApplicationsAction!goList.action');">车辆申请管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/vehicleUseAction!goList.action');">车辆使用管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/vehicleApplicationQueryAction!goList.action');">车辆查询管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/carMaintenanceAction!goList.action');">车辆维护管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/requisitionCarAction!goList.action');">车辆信息管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/tpkAction!goList.action');">油耗统计</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/commonWabAction!goList.action');">公共通讯薄</a></li>
							</ul></li>
						<li class="fly"><a href="#">知识管理</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/oa/publicCabinetAction!goList.action');">公共文件柜</a></li>
								<li><a href="#">网络硬盘</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/pictureAction!goList.action');">图片浏览</a></li>
								<!-- <li><a href="#">OA指导</a></li> -->
								<!-- <li><a href="#">维基百科</a></li> -->
								<li><a href="#">文档检索中心</a></li>
							</ul></li>
						<li class="fly"><a href="#">交流园地</a>
							<ul>
								<li class="fly_top"><a href="#">微讯分享</a></li>
								<li><a href="#">讨论区</a></li>
								<li><a href="#">论坛</a></li>
								<li><a href="#">会议</a></li>
								<li><a href="#">聊天室</a></li>
								<li class="fly_end"><a href="#">视频会议</a></li>
							</ul></li>
						<li class="fly"><a href="#">签章管理</a>
							<ul>
								<li class="fly_top"><a href="#">工作流版式文件签章</a></li>
								<li><a href="#">工作流电子表单签章</a></li>
								<li><a href="#">NTKO文档在线管理</a></li>
								<li class="fly_end"><a href="#">签章在线及套红管理</a></li>
							</ul></li>
						<li class="fly"><a href="#">系统设置</a>
							<ul>

								<li class="fly"><a href="#">行政办公设置</a>
									<ul>
										<li class="fly_Top"><a href="#">知识管理设置</a></li>
										<li><a href="#">印章管理</a></li>
										<li><a href="#">定时任务管理</a></li>
										<li><a href="#">紧急通知管理</a></li>
										<li><a href="#">界面设置</a></li>
										<li class="fly_end"><a href="#">菜单设置</a></li>
									</ul></li>
								<li class="fly"><a href="#">信息交流设置</a>
									<ul>
										<li class="fly_top"><a href="#">短信提醒设置</a></li>
										<li><a href="#">手机短信设置</a></li>
										<li><a href="#">讨论区设置</a></li>
										<li><a href="#">词语过滤管理</a></li>
										<li><a href="#">信息过滤审核</a></li>
										<li><a href="#">系统代码设置</a></li>
									</ul></li>
								<li class="fly"><a href="#">系统代码设置</a>
									<ul>
										<li class="fly_top"><a href="#">自定义字段设置</a></li>
										<li><a href="#">数据库管理</a></li>
										<li><a href="#">系统日志管理</a></li>
										<li><a href="#">系统资源管理</a></li>
										<li><a href="#">附件管理</a></li>
										<li><a href="#">系统访问控制</a></li>
										<li class="fly_end"><a href="#">系统参数设置</a></li>
									</ul></li>
								<li class="fly"><a href="#">系统接口设置</a>
									<ul>
										<li class="fly_top"><a href="#">服务器监控</a></li>
										<li class="fly_end"><a href="#">系统信息</a></li>
									</ul></li>
							</ul></li>
					</ul></li>

				<li class="nav_arrow" id="bg_08"><a href="#"><span id="nav_change">企业资产管理</span></a>
					<ul>
						<li class="fly"><a href="javascript:void(0);">设置</a>
							<ul>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!whgxsz.action');">维护工序设置</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!gdsz.action');">工单设置</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!gdnljhsz.action');">工单能力计划设置</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!bzgdmbgl.action');">标准工单模板管理</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!fenlei.action');">分类</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!sblxdy.action');">设备类型定义</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!sbxhggdy.action');">设备型号规格定义</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!bmsz.action');">编码设置</a>
								<li class="fly_top"><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamSettingAction!ywlcdy.action');">业务流程定义</a>
							</ul></li>
						<li class=""><a href="javascript:void(0);" onclick="loadContent('${vix}/eam/eamCalendarAction!rlgl.action');">日历管理</a></li>
						<li class="fly"><a>设备信息管理</a>
							<ul>
								<li class="fly_top" icon="${vix}/module/eam/image/breadcrumb_equipment.png"><a onclick="loadContent('${vix}/eam/accountManageAction!goList.action');">台账管理</a>
								<li><a onclick="loadContent('${vix}/eam/accountManageAction!daArchiveRecordMgr.action');">档案管理</a>
								<li><a onclick="loadContent('${vix}/eam/accountManageAction!hdStatusRecordMgr.action');">活动变动管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/accountManageAction!ztgl.action');">状态管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/accountManageAction!sybjgl.action');">所用备件管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/accountManageAction!wxcbjzczj.action');">维修成本及资产折旧</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/accountManageAction!ljLogicTreeMgr.action');">关联结构和层次体系</a>
								<li><a onclick="loadContent('${vix}/eam/accountManageAction!bxServiceInfoMgr.action');">保修服务合同管理</a>
								<li><a onclick="loadContent('${vix}/eam/accountManageAction!gzErrorCodeMgr.action');">故障体系结构</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/accountManageAction!pjgl.action');">评级管理</a> <!-- 
								<li><a  onclick="loadContent('${vix}/eam/accountManageAction!sbRepairPartMgr.action');">备件管理</a>
								<li><a  onclick="loadContent('${vix}/eam/accountManageAction!sjBaseDataMgr.action');">设备基础数据管理</a>
								<li><a  onclick="loadContent('${vix}/eam/accountManageAction!ljLogicTreeMgr.action');">逻辑设备层次结构</a>
 -->
								<li class="fly_end"><a onclick="loadContent('${vix}/eam/accountManageAction!tjStatisticMgr.action');">统计分析</a>
							</ul></li>
						<li class="fly"><a>设备变动管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamEquipmentAction!diaobo.action');">设备调拨</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamEquipmentAction!tingyong.action');">设备停用</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamEquipmentAction!fengcun.action');">设备封存</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamEquipmentAction!xianzhi.action');">设备闲置</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamEquipmentAction!baofei.action');">设备报废</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamEquipmentAction!touyong.action');">设备投用</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamEquipmentAction!bdqd.action');">设备变动清单</a>
							</ul></li>
						<li class="fly"><a href="#">故障与缺陷管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/faultReportAction!gzReportMgr.action');">故障缺陷报告/维修通知单</a>
								<li><a onclick="loadContent('${vix}/eam/faultReportAction!spReview.action');">故障缺陷报告审批单</a>
								<li class="fly_end"><a onclick="loadContent('${vix}/eam/faultReportAction!tjStatisticMgr.action');">故障缺陷统计分析</a>
							</ul></li>
						<li class="fly"><a>工单管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!shezhi.action');">设置</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!gdCreateMgr.action');">工单创建</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!lbWorkOrderMgr.action');">工单列表</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!jihua.action');">工单计划</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!spReview.action');">工单调度与审批</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!gdzxgcgl.action');">工单执行过程管理</a> <!-- 
								<li class="fly_top"><a  onclick="loadContent('${vix}/eam/workOrdersAction!bgReport.action');">工单报告</a></li>
 -->
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!wgFinalReport.action');">工单完工与报告管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!wbfwhtgl.action');">外包服务合同管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!gdcbgl.action');">工单成本管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workOrdersAction!daArchiveRecord.action');">工单档案管理</a></li>
								<li class="fly_end"><a onclick="loadContent('${vix}/eam/workOrdersAction!tjStatisticMgr.action');">统计分析与报表</a></li>
							</ul></li>
						<li class="fly"><a>预防性维护管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/preMaintainAction!jcBaseDataMgr.action');">预防性维护基础数据管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/preMaintainAction!hdgl.action');">预防性维护活动管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/preMaintainAction!rwqdgl.action');">任务清单管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/preMaintainAction!yfjh.action');">预防计划</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/preMaintainAction!yxjh.action');">运行计划</a></li>
								<li><a onclick="loadContent('${vix}/eam/preMaintainAction!whWorkMgr.action');">维护任务管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/preMaintainAction!gzbgl.action');">工作包管理</a></li>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/preMaintainAction!jhjkjtz.action');">计划监控及调整</a></li>
								<!-- 
								<li><a  onclick="loadContent('${vix}/eam/preMaintainAction!gdGenWorkOrder.action');">任务工单生成</a></li>
								<li><a  onclick="loadContent('${vix}/eam/preMaintainAction!dxProjectMgr.action');">大修项目管理</a></li>
								<li><a  onclick="loadContent('${vix}/eam/preMaintainAction!jkInfoMgr.action');">计划监控及调整</a></li>
								<li class="fly_end"><a  onclick="loadContent('${vix}/eam/preMaintainAction!cbCostMgr.action');">维护成本估算</a></li>
 -->
							</ul></li>
						<li class="fly"><a>项目管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!jbxx.action');">项目基本信息</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!jgjl.action');">项目结构建立</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!gckzhsh.action');">项目过程控制和审核</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!ysmxcx.action');">项目预算明细查询</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!xmcbgl.action');">项目成本管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!fytz.action');">项目费用台账</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!jjxxm.action');">警戒线项目</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!xmfx.action');">项目分析</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamProjectAction!xmjdgl.action');">项目进度管理</a>
							</ul></li>
						<li class="fly"><a>设备点巡检管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/equipmentCheckAction!jhgl.action');">点巡检计划管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/equipmentCheckAction!jhxd.action');">点巡检计划下达</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/equipmentCheckAction!sjcjsb.action');">点巡检数据采集上报</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/equipmentCheckAction!sjfx.action');">点巡检数据分析</a>
							</ul></li>
						<li class="fly"><a>运行管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!gzpgl.action');">工作票管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!czpgl.action');">操作票管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!yxrz.action');">运行日志</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!dqqhysy.action');">定期切换与试验</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!yxryczfx.action');">运行人员操作分析</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!yxsgfx.action');">运行事故分析</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!fzcgkfx.action');">非正常工况分析</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/workingInfoAction!yxgcbzk.action');">运行规程标准库</a>
							</ul></li>
						<li class=""><a onclick="loadContent('${vix}/eam/equipmentPartsAction!cgybjgl.action');">采购与备件管理</a></li>
						<li class="fly"><a>预算管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/budgetManageAction!jihua.action');">计划</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/budgetManageAction!zhixing.action');">执行</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/budgetManageAction!jiankong.action');">监控</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/budgetManageAction!shenpi.action');">审批</a>
							</ul></li>
						<li class="fly"><a>绩效管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/kpiManageAction!sbgldzbtxgl.action');">设备管理的指标体系管理</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/kpiManageAction!zcxzb.action');">政策性指标</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/kpiManageAction!zyglzb.action');">专业管理指标</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/kpiManageAction!sbzhxnzbfx.action');">设备综合性能指标分析</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/kpiManageAction!phjfkgl.action');">平衡计分卡管理</a>
							</ul></li>
						<li class="fly"><a>监督管理</a>
							<ul>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!jngd.action');">节能监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!dyjd.action');">电压监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!bhjd.action');">保护监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!jsjd.action');">金属监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!rgjd.action');">热工监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!djjd.action');">点测监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!hxjd.action');">滑雪监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!hbjd.action');">环保监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!jyjd.action');">绝缘监督</a>
								<li class="fly_top"><a onclick="loadContent('${vix}/eam/eamControlAction!scsxdzclgl.action');">生产所需大宗材料管理</a>
							</ul></li>
						<li class=""><a onclick="loadContent('${vix}/eam/eamReportAction!zhbbfx.action');">综合报表分析</a>
					</ul></li>
				<li id="mainDataManage" class="nav_arrow"><a href="#"><span>主数据管理</span> </a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/mdm/basicSettingsAction!goListContent.action');">基础设置</a></li>
						<li class="fly"><a href="#">主数据维护</a>
							<ul>
								<li class="fly_top"><a href="/vix/content/hr/pm/oo.jsp">组织架构</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=mdm','mainDataManage');">客户</a></li>
								<li><a href="/vix/content/scm/scm/fts.jsp">供应商</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action','mainDataManage');">${vv:varView("vix_mdm_item")}</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/fastAddItemAction!goList.action','mainDataManage');">${vv:varView("vix_mdm_item")}商品管理</a></li>
								<li><a href="#">设备</a></li>
								<li><a href="/vix/content/hr/ei/pm.jsp">人员</a></li>
								<li class="fly_end"><a href="/vix/content/fa/gl/AccountingSubject.jsp">会计科目</a></li>
							</ul></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/dataExtractionAction!goList.action');">数据抽取</a></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/dataCleanAction!goList.action');">数据清洗管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/monitoringServiceAction!goList.action');">监控服务</a></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/synchronousAction!goList.action');">主数据同步管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/sharingAndPublishingAction!goList.action');">主数据共享与发布管理</a></li>
						<li class="fly_end"><a href="#">统计分析</a></li>
					</ul></li>
				<li id="systemManage" class="nav_arrow"><a href="#"><span>系统管理</span> </a>
					<ul>
						<%-- <li class="fly"><a href="#">平台运营管理</a>
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
								</ul>
							</li>
						</ul>
					</li> --%>
						<li class="fly"><a href="#">基础设置</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/system/codeAction!goCodeList.action');">编码管理</a> <%-- <ul>
									<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/codeAction!goList.action');">编码级数管理</a></li>
									<li><a href="#" onclick="loadContent('${vix}/system/codeAction!goCodeList.action');">编码规则管理</a></li>
								</ul> --%></li>
								<li><a href="#" onclick="loadContent('${vix}/system/systemVarAction!goList.action');">系统常量管理</a></li>
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
								<%-- <li><a href="#" onclick="loadContent('${vix}/system/billTypeSetAction!goList.action');">单据类型设置(内用)</a></li>
							<li><a href="#" onclick="loadContent('${vix}/system/orginialBillTypeAction!goList.action');">单据类型设置(内用)</a></li> --%>
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
								<%-- <li><a href="#" onclick="loadContent('${vix}/system/objectExpandAction!goList.action','systemManage');">对象类型</a></li>
							<li><a href="#" onclick="loadContent('${vix}/system/specificationAction!goList.action','systemManage');">对象规格</a></li> --%>
								<%-- <li><a href="#" onclick="loadContent('${vix}/system/enumerationCategoryAction!goList.action');">字典分类</a></li>
							<li><a href="#" onclick="loadContent('${vix}/system/enumerationAction!goList.action');">字典管理</a></li> --%>
								<li class="fly"><a href="#">采购管理</a>
									<ul>
										<%-- <li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchasingOptionsAction!goListContent.action','bg_02');">设置</a></li> --%>
										<li><a href="#" onclick="loadContent('${vix}/traceability/traceabilityAction!goList.action','bg_02');">水果管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/traceability/fruitDiameterAction!goList.action','bg_02');">果径管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/traceability/farmersAction!goList.action','bg_02');">果农管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/traceability/codeDataSourceAction!goList.action','bg_02');">编码管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/traceability/fruitTypesAction!goList.action','bg_02');">水果分类管理</a></li>
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
												<%-- <li class="fly_top"><a
												href="#"  onclick="loadContent('${vix}/fa/accountsAction!goList.action');">新增</a></li> --%>
												<li class="fly_end"><a href="#" onclick="loadContent('${vix}/fa/accountsAction!goList.action');">列表</a></li>
											</ul></li>

										<li><a href="#" onclick="loadContent('${vix}/fa/vocherAction!goList.action');">凭证管理</a></li>
										<!-- <li class="fly"><a href="#">收付结账</a>
										<ul>
											<li><a href="#">结账方式</a></li>
										</ul> -->
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
										<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action?fromKey=system');">岗位管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/businessViewAction!goList.action');">业务组织视图管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/businessOrgAction!goList.action');">业务组织管理</a></li>
										<!-- <li><a href="#">高级查询</a></li> -->
									</ul></li>
								<li class="fly"><a href="#">销售和分销</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/sales/config/saleOrgAction!goList.action','bg_02');">销售组织管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/drp/channelAction!goList.action');">分销渠道管理</a></li>
										<li><a href="#">销售办公室</a></li>
										<li class="fly_end"><a href="#">销售组</a></li>
									</ul></li>
								<!-- <li><a href="#">用户管理</a></li> -->
								<li><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action');">员工管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/advancedSearchAction!goSaveOrUpdate.action');">高级查询</a></li>
								<%-- <li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action');">职位管理</a></li> --%>
								<%-- <li class="fly"><a href="#">流程与规则</a>
						<ul>
							<li class="fly_top"><a href="#"
								onclick="loadContent('${vix}/workflow/droolsCategoryAction!goList.action','systemManage');">规则分类</a>
							<li><a href="#"
								onclick="loadContent('${vix}/workflow/droolsRuleAction!goList.action','systemManage');">规则管理</a>
							</li>
						</ul>
					</li> --%>
							</ul></li>
						<li class="fly"><a href="#">权限管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action');">角色管理</a>
								<li><a href="#" onclick="loadContent('${vix}/common/security/userAccountAction!goList.action');">账号管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/authorityAction!goList.action');">权限管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/resourceAction!goList.action');">资源管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/dataColSecAction!goList.action');">列级权限配置</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/dataColPolicyAction!goList.action');">列级权限策略管理</a></li>
								<li class="fly"><a href="#">行级权限管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action');">行集权限策略配置管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyAction!goList.action');">行集权限策略管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowOwnerAction!goList.action');">行集权限主体管理</a> <%-- 	<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowMethodConfigAction!goList.action');">行集权限方法设置</a></li>
							<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowSysParamAction!goList.action');">参数和预处理数据</a></li> --%>
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
            <s:include value="head_wechat.jsp"></s:include>
            <li><a href="#" onclick="loadContent('${vix}/flow/flowlist.jsp');">流程管理</a></li>
			<li class="fly"><a href="#">表单管理</a>
				<ul>
					<li class="fly_top"><a href="#" onclick="loadContent('${vix}/form/metadata.jsp');">字典维护</a></li>
					<li><a href="#" onclick="loadContent('${vix}/form/baseform.jsp');">基础表维护</a></li>
					<li><a href="#" onclick="loadContent('${vix}/form/businessform.jsp');">表单列表</a></li>
					<li><a href="#" onclick="loadContent('${vix}/system/formBindingAction!goList.action');">表单模板绑定</a></li>
					<li><a href="#" onclick="loadContent('${vix}/system/formBindingAction!goEmp.action');">表单绑定人员</a></li>
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
				<span><a href="#" onclick="loadContent('${vix}/system/personalInformationAction!goList.action');"><img src="img/user.png" alt="" /><small>个人设置</small></a></span>
				<span><a href="#" onclick="loadContent('${vix}/system/systemSetAction!goList.action');"><img src="img/wrench_screwdriver.png" alt="" /><small>运行参数</small></a></span>
				<span><a href="#" onclick="loadContent('${vix}/common/contactsAction!goList.action');"><img src="img/address_book.png" alt="" /><small>通讯簿</small></a></span>
				<span><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');"><img src="img/mail.png" alt="" /><small>邮件管理</small></a></span>
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
						<li><span class="head_menu"><img src="${vix}/common/img/icon_sec_fg.png" class="arrow"/></span>
							<div class="flick_menu">
								<s:iterator value="#session.EMP_PROXY_ORG_OBJ" var="empOrg" > 
									<a href="${vix}/common/vixAction!handlerSetUserCompanyInnerCodeByFg.action?fgCompanyInnerCode=${empOrg.companyInnerCode}">${empOrg.briefName}</a>
								</s:iterator>
							</div>
						</li>
						<li><span class="head_menu"><img src="img/recent_list.png" class="arrow"/></span>
							<div class="flick_menu">
								<a href="index.html">用户中心</a>
								<a href="index.html">我的订单</a>
								<a href="index.html">我的流程</a>
								<a href="index.html">我的日程</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>