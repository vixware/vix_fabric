<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该客户么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkCustomerAccountIds']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkCustomerAccountIds']").attr("checked", true);
		}else{
			$("input[name='chkCustomerAccountIds']").attr("checked", false);
		}
	}
	selectCount();
}

function chooseOther(){
	$.each($("input[name='chkCustomerAccountIds']"), function(i, n){
		if($(n).attr('checked')){
			$(n).attr('checked',false);
		}else{
			$(n).attr('checked',true);
		}
	});
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkCustomerAccountIds']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCategoryCount1").html(selectCount);
	$("#selectCategoryCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkCustomerAccountIdss']").attr("checked", false);
	}else{
		$("input[name='chkCustomerAccountIdss']").attr("checked", true);
	}
}

$(".untitled").hover(
	function () {
		$(this).css({ "position": "relative"});
		$(this).children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $(this).offset();
		if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
  	},
	function () {
		$(this).css({ "position": "static"});
		$(this).children('.popup').css({ "display": "none"});
 	}
);
$(".close").click(
	function () {
		$(this).parent().parent().css({ "display": "none"});
	}
);
</script>
<input type="hidden" id="customerAccountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerAccountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerAccountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerAccountOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerAccountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerAccountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="6%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCustomerAccountIdss" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#" onclick="chooseOther();">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="5%">编码</th>
			<th width="10%">名称</th>
			<s:if test="source =='member' ">
				<th width="10%">生日</th>
				<th width="15%">教育程度</th>
				<th width="10%">收入水平</th>
				<th width="15%">客户身份</th>
				<th width="10%">黑名单</th>
			</s:if>
			<s:else>
				<th width="10%">简称</th>
				<th width="10%">客户类型</th>
				<th width="10%">热点程度</th>
				<th width="10%">客户种类</th>
				<th width="10%">关系等级</th>
				<th width="10%">客户来源</th>
				<th width="10%">阶段</th>
			</s:else>
			<th width="15%">操作</th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCustomerAccountIds" name="chkCustomerAccountIds" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.code}</span></td>
				<td><span style="color: gray;">${entity.name}</span></td>
				<s:if test="source =='member' ">
					<td><span style="color: gray;"><s:property value="formatDateToString(#entity.contactPerson.birthday)" /></span></td>
					<td><span style="color: gray;">${entity.contactPerson.education}</span></td>
					<td><span style="color: gray;">${entity.contactPerson.incomeLevel}</span></td>
					<td><span style="color: gray;">${entity.contactPerson.identity}</span></td>
					<td><span style="color: gray;"> <s:if test="#entity.contactPerson.isBlack == 1 ">
							是
						</s:if> <s:elseif test="#entity.contactPerson.isBlack == 0 ">
							否
						</s:elseif>
					</span></td>
				</s:if>
				<s:else>
					<td><span style="color: gray;">${entity.shorterName}</span></td>
					<td><span style="color: gray;"> <s:if test='#entity.type == "enterPrise"'>企业客户</s:if> <s:elseif test='#entity.type == "internal"'>内部客户</s:elseif> <s:elseif test='#entity.type == "personal"'>个人客户</s:elseif>
					</span></td>
					<td><span style="color: gray;">${entity.hotLevel.name }</span></td>
					<td><span style="color: gray;">${entity.customerType.name }</span></td>
					<td><span style="color: gray;">${entity.relationshipClass.name }</span></td>
					<td><span style="color: gray;">${entity.customerSource.name }</span></td>
					<td><span style="color: gray;">${entity.customerStage.name }</span></td>
				</s:else>
				<td><s:if test="source =='member' ">
						<s:if test="#entity.contactPerson.isBlack == 0 ">
							<a href="#" onclick="addToBlackList('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" />
							</a>
						</s:if>
						<a href="#" onclick="saveOrUpdate(${entity.id},'${entity.type}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
						</a>
						<a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
						</a>
					</s:if> <s:else>
						<a href="#" onclick="goMemberView('${entity.id}');" title="会员视图"> <img src="${vix}/common/img/view.png" />
						</a>
						<a href="#" onclick="goCustomerView('${entity.id}','${entity.type}');" title="<s:text name='cmn_view'/>"> <img src="${vix}/common/img/view.png" />
						</a>
						<a href="#" onclick="saveOrUpdate('${entity.id}','${entity.type}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
						</a>
						<a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
						</a>
					</s:else></td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<s:if test="source =='member' ">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</s:if>
				<s:else>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</s:else>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>