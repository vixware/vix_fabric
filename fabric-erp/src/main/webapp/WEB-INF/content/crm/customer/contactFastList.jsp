<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id){
	var pageNo = $("#contactPersonPageNoHidden").val();
	$.ajax({
		  url:'${vix}/crm/customer/crmContactPersonAction!goSingleSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 360,
					title:"联系人信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#contactPersonForm').validationEngine('validate')){
								$.post('${vix}/crm/customer/crmContactPersonAction!saveOrUpdate.action',
										{'contactPerson.id': $("#id").val(),
									      'contactPerson.lastName':$("#lastName").val(),
										  'contactPerson.firstName':$("#firstName").val(),
										  'contactPerson.sex':$(":radio[name=sex][checked]").val(),
										  'contactPerson.company':$("#company").val(),
										  'contactPerson.birthday':$("#birthday").val(),
										  'contactPerson.mobile':$("#mobile").val(),
										  'contactPerson.presideBusiness':$("#presideBusiness").val(),
										  'contactPerson.directPhone':$("#directPhone").val(),
										  'contactPerson.email':$("#email").val(),
										  'contactPerson.phoneHome':$("#phoneHome").val(),
										  'contactPerson.msnAccount':$("#msnAccount").val(),
										  'contactPerson.qqAccount':$("#qqAccount").val(),
										  'contactPerson.skypeAccount':$("#skypeAccount").val(),
										  'contactPerson.wangAccount':$("#wangAccount").val(),
										  'contactPerson.fax':$("#fax").val(),
										  'contactPerson.customerAccount.id':$("#customerAccountId").val()
										},
										function(result){
											showMessage(result);
											pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?name=",'contactPerson');
										}
									);
							}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
loadName();
//载入分页列表数据
pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?name="+name,'contactPerson');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?name=",'contactPerson');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#contactPersonOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'contactPerson');
}

function contactPersonPager(tag){
	loadName();
	pager(tag,"${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?name="+name,'contactPerson');
}
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');

function selectNamePager(shortName,name){
	shortName=Url.encode(shortName);
	shortName=Url.encode(shortName);
	pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?name=" + name +"&shortName=" + shortName,'contactPerson');
}
function searchForContent(){
	loadTitle();
	pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?title="+title,'contactPerson');
}
function reset(){
	$('#nameS').val('');
}
function loadTitle(){
	title = $('#nameS').val();
	title=Url.encode(title);
	title=Url.encode(title);
}
function searchForSex(sex){
	pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?sex="+sex,'contactPerson');
}
function searchForInternal(tag){
	pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?internal="+tag,'contactPerson');
}
function searchForPrimaryContact(tag){
	pager("start","${vix}/crm/customer/crmContactPersonAction!goFastListContent.action?primaryContact="+tag,'contactPerson');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/surname.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">联系人管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmContactPersonAction!goFastList.action');">百家姓</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div style="margin: 0;" class="addbox">
					<div class="addtitle">百家姓</div>
					<ul class="pxlist">
						<li><a href="#" class="xst" onclick="selectNamePager('','A');">A</a>
						<s:iterator value="fastMap['A']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','B');">B</a>
						<s:iterator value="fastMap['B']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','C');">C</a>
						<s:iterator value="fastMap['C']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','D');">D</a>
						<s:iterator value="fastMap['D']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','E');">E</a>
						<s:iterator value="fastMap['E']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','F');">F</a>
						<s:iterator value="fastMap['F']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','G');">G</a>
						<s:iterator value="fastMap['G']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','H');">H</a>
						<s:iterator value="fastMap['H']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','I');">I</a>
						<s:iterator value="fastMap['I']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','J');">J</a>
						<s:iterator value="fastMap['J']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','K');">K</a>
						<s:iterator value="fastMap['K']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','L');">L</a>
						<s:iterator value="fastMap['L']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','M');">M</a>
						<s:iterator value="fastMap['M']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','N');">N</a>
						<s:iterator value="fastMap['N']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','O');">O</a>
						<s:iterator value="fastMap['O']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','P');">P</a>
						<s:iterator value="fastMap['P']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','Q');">Q</a>
						<s:iterator value="fastMap['Q']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','R');">R</a>
						<s:iterator value="fastMap['R']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','S');">S</a>
						<s:iterator value="fastMap['S']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','T');">T</a>
						<s:iterator value="fastMap['T']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','U');">U</a>
						<s:iterator value="fastMap['U']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','V');">V</a>
						<s:iterator value="fastMap['V']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','W');">W</a>
						<s:iterator value="fastMap['W']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','X');">X</a>
						<s:iterator value="fastMap['X']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','Y');">Y</a>
						<s:iterator value="fastMap['Y']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
						<li><a href="#" class="xst" onclick="selectNamePager('','Z');">Z</a>
						<s:iterator value="fastMap['Z']" var="ln">
								<a href="#" onclick="selectNamePager('${ln}','');">${ln}</a>
							</s:iterator></li>
					</ul>
				</div>
				<div class="addbox">
					<div class="addtitle clearfix">
						<span class="l">提示：</span>
					</div>
					<ol class="gray">
						<li>点击字幕（英文姓名）</li>
						<li>点击汉字（姓氏）</li>
						<li>在百家姓应用异常时，“整理”可恢复正常</li>
					</ol>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" style="border: 0;">
				<table width="100%">
					<tr>
						<td width="50%" style="vertical-align: top"><table class="list">
								<tbody>
									<tr>
										<th><img src="img/address_book.png" style="vertical-align: middle;" /> 本月纪念日</th>
									</tr>
									<s:iterator value="currentMonthDate" var="cmd">
										<tr>
											<td>【<s:property value="formatDateToString(#cmd.birthday)" />】【${cmd.name}<img src="img/file.png" />】
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table></td>
						<td width="50%" style="vertical-align: top"><table class="list">
								<tbody>
									<tr>
										<th><img src="img/address_book.png" style="vertical-align: middle;" /> 下月纪念日</th>
									</tr>
									<s:iterator value="nextMonthDate" var="nmd">
										<tr>
											<td>【<s:property value="formatDateToString(#nmd.birthday)" />】【${nmd.name}<img src="img/file.png" />】
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table></td>
					</tr>
				</table>
				<div class="fllist">
					<p style="line-height: 15px;">
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong> <a href="#" onclick="searchForContent();">·全部数据</a> <a href="#" onclick="searchForSex('1');">·男</a> <a href="#" onclick="searchForSex('0');">·女</a> <a href="#" onclick="searchForPrimaryContact('1');">·主联系人</a> <a href="#" onclick="searchForInternal('internal');">·个人客户</a>
						<a href="#">·7天来跟踪（没有日程/任务/历史行动）的联系人</a> <a href="#">·100天未来跟踪</a><a href="#">·本月纪念日联系人</a> <a href="#">·下月纪念日联系人</a> <br />&nbsp;
					</p>
					<p>
						<strong><img src="img/list_icon_24.gif" class="icon">快速查询：</strong> &nbsp;联系人姓名：<input type="text" class="int" id="nameS"> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
					</p>
				</div>
				<div id="contactPerson" class="table"></div>
				<div class="pagelist drop clearfix">
					<ul>
						<li class="ed"><a href="#">选项</a>
							<ul>
								<li><a href="#">删除</a></li>
								<li><a href="#">邮件</a></li>
								<li><a href="#">批量更新</a></li>
								<li><a href="#">合并</a></li>
								<li><a href="#">添加到目标列表</a></li>
								<li><a href="#">导出</a></li>
							</ul></li>
					</ul>
					<strong>选中:0</strong>
					<div>
						<span><a class="start" onclick="contactPersonPager('start','contactPerson');"></a></span> <span><a class="previous" onclick="contactPersonPager('previous','contactPerson');"></a></span> <em>(<b class="contactPersonInfo"></b> <s:text name='cmn_to' /> <b class="contactPersonTotalCount"></b>)
						</em> <span><a class="next" onclick="contactPersonPager('next','contactPerson');"></a></span> <span><a class="end" onclick="contactPersonPager('end','contactPerson');"></a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<!-- content -->
