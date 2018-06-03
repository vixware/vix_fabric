<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
	</head>
	<body>
		<header>
		    <a href="#"></a>客户信息<a href="#" class="shear_Click"></a>
		</header>
		<section>
			<input id="id" name="customerAccount.id" value="${customerAccount.id }" type="hidden"/>
			<div class="tab_menu">
				<ul>
					<li class="selected"><a href="#tabOne" onclick="loadTags('tabOne');">基本信息</a></li>
					<li><a href="#tabTwo" onclick="loadTags('tabTwo');">联系人</a></li>
					<li><a href="#tabThree" onclick="loadTags('tabThree');">机会与跟踪</a></li>
					<li><a href="#tabFour" onclick="loadTags('tabFour');">拜访</a></li>
				</ul>
			</div>
			<div id="tabOne" class="newMeet_list2">
				<dl>
					<dt>
						<span>客户分类</span> 
						<strong> 
							<input type="text" value="${customerAccount.customerAccountCategory.name }" readonly="readonly"> 
						</strong>
					</dt>
					<dt>
						<span>类型</span> 
						<strong>
							<c:if test='${customerAccount.type eq "enterPrise"}'>
								<input type="text" value="企业客户" readonly="readonly"> 
							</c:if>
							<c:if test='${customerAccount.type eq "personal"}'>
								<input type="text" value="个人客户" readonly="readonly"> 
							</c:if>
						</strong>
					</dt>
					<dt>
						<span>客户编码</span>
						<strong>
							<input type="text" value="${customerAccount.code}" readonly="readonly">
						</strong>
					</dt>
					<dt>
						<span>客户名称</span>
						<strong>
							<input type="text" value="${customerAccount.name}" placeholder="请输客户姓名">
						</strong>
					</dt>
					<dt>
						<span>英文名称</span>
						<strong>
							<input value="${customerAccount.englishName}" type="text" readonly="readonly"/>
						</strong>
					</dt>
					<dt>
						<span>所属行业</span> 
						<strong>
							<input value="${customerAccount.industry.name}" type="text" readonly="readonly"/> 
						</strong>
					</dt>
					<dt>
						<span>客户来源</span> 
						<strong>
							<input value="${customerAccount.customerSource.name}" type="text" readonly="readonly"/>
						</strong>
					</dt>
					<dt>
						<span>公司简介</span> 
						<strong>
							<input value="${customerAccount.companyBrief}" type="text" placeholder="公司简介"/>
						</strong>
					</dt>
				</dl>
				<div class="taskDetail_list2">
			        <h2>
			            <span class="btn"><a href="#" onclick="goSaveOrUpdateCustomer('${customerAccount.id}');">修改客户</a></span><span class="btn"><a href="#" onclick="goBackCustomerList();">返回</a></span>
			        </h2>
			    </div>
			</div>
			<div id="tabTwo" class="subJob" style="display: none;">
				<s:if test="contactPersonList.size > 0">
					<s:iterator value="contactPersonList" var="entity" status="s">
						<dl>
							<dt>
								<span style="height: 28px;"></span>
								<strong>
									<a href="#" onclick="goSaveOrUpdateContactPerson('${entity.id}','sync');">${entity.name}</a>
								</strong>
							</dt>
						</dl>
					</s:iterator>
					<div class="taskDetail_list2">
				    	<h2 style="padding: 0px; border-bottom: 0px;">
					    	<span class="btn"><a href="#" onclick="deleteContactPersonById('${entity.id}');">删除选中联系人</a></span><span class="btn"><a href="#" onclick="addContactPerson('0');">添加联系人</a></span>
				    	</h2>
				    </div>
				</s:if>
				<s:else>
					<h3>
						<span class="btn"><a href="#" onclick="addContactPerson('');" style="background: #2ecc32;">添加联系人</a></span>
					</h3>
				</s:else>
			</div>
			<div id="tabThree" class="newMeet_list2" style="display: none;">
				<s:if test="saleChanceList.size > 0">
					<s:iterator value="saleChanceList" var="entity" status="s">
						<dl>
							<dt>
								<span>机会主题:</span>
								<strong>
									<input value="${entity.subject}" type="text" readonly="readonly" onclick="goSaveOrUpdate('${entity.id}');"/>
								</strong>
							</dt>
						</dl>
					</s:iterator>
				</s:if>
				<s:else>
					<dt>
						<dl align="center">
							<strong>
								<a href="#">无数据</a>
							</strong>
						</dl>
					</dt>
				</s:else>
			</div>
			<div id="tabFour" class="newMeet_list2" style="display: none;">
				<dt>
					<dl align="center">
						<strong>
							<a href="#">无数据</a>
						</strong>
					</dl>
				</dt>
			</div>
		</section>
	</body>
</html>
<script type="text/javascript">
function loadTags(tags){
	if(tags == 'tabOne'){
		$("#tabOne").attr('style','display:block');
		$("#tabTwo").attr('style','display:none');
		$("#tabThree").attr('style','display:none');
		$("#tabFour").attr('style','display:none');
	}
	if(tags == 'tabTwo'){
		$("#tabOne").attr('style','display:none');
		$("#tabTwo").attr('style','display:block;');
		$("#tabThree").attr('style','display:none');
		$("#tabFour").attr('style','display:none');
	}
	if(tags == 'tabThree'){
		$("#tabOne").attr('style','display:none');
		$("#tabTwo").attr('style','display:none');
		$("#tabThree").attr('style','display:block');
		$("#tabFour").attr('style','display:none');
	}
	if(tags == 'tabFour'){
		$("#tabOne").attr('style','display:none');
		$("#tabTwo").attr('style','display:none');
		$("#tabThree").attr('style','display:none');
		$("#tabFour").attr('style','display:block');
	}
}

function goSaveOrUpdateCustomer(id){
	window.location.href = "${vix}/wechat/webChatCustomerAction!goSaveOrUpdateCustomerAccont.action?id=" + id +'&chooseType='+'goSaveOrUpdateCustomer';
}

function goSaveOrUpdateContactPerson(id,sync){
	window.location.href = "${vix}/wechat/webChatCustomerAction!goSaveOrUpdateContactPerson.action?id=" + id + "&sync=" + sync;
}

function goBackCustomerList() {
	window.location.href = '${vix}/wechat/webChatCustomerAction!goCustomerAccountList.action';
};

function addContactPerson(id){
	var customerAccountId = $('#id').val();
	if(id == "0" && customerAccountId != ""){
		window.location.href = "${vix}/wechat/webChatCustomerAction!goSaveOrUpdateContactPerson.action?customerAccountId=" + customerAccountId + "&id=" + id;
	}else {
		$.ajax({
			
			
		});
	}
}
function deleteContactPersonById(id){
	alert(id);
}
</script>
