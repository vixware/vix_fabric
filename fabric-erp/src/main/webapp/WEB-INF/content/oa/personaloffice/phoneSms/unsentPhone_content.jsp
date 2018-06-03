<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("sendMessages tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("sendMessages tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){ 
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
   
function chooseAll(chk){
	if(null == chk){
		$("input[name='chkId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkId']").attr("checked", true);
		}else{
			$("input[name='chkId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCount1").html(selectCount);
	$("#selectCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkIds']").attr("checked", false);
	}else{
		$("input[name='chkIds']").attr("checked", true);
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

/**获取未发送消息数据*/
function unsentMessages1(id){
	$.ajax({
		  url:'${vix}/oa/phoneSmsAction!goSaveOrUpdate.action?id='+id,
		  success: function(html){
			  $('#page1').html(html);
		  
		  }
	});
}

/* 删除消息 */
function deleteEntity1(id,a){
	asyncbox.confirm('确定要删除该消息么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById1(id,a);
		}
	});
}

</script>
<table id="sendMessages" class="list">
	<tbody>
		<tr>
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input name="" type="checkbox" value="" /></label>
						<ul>
							<li><a href="#">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th><s:text name="收信人" /></th>
			<th><s:text name="手机号码" /></th>
			<th><s:text name="保存时间" /></th>
			<th><s:text name="oa_content" /><a href="#"></th>
			<th><s:text name="oa_operating" /><a href="#"></a></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><a href="#" style="color: gray;">${entity.recipients}</a></td>
				<td><a href="#" style="color: gray;">${entity.phoneNumber}</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.sendTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td><a href="#" style="color: gray;">${entity.phoneSmsContent}</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<s:if test="tag != 'choose'">
							<a href="#" onclick="unsentMessages1('${entity.id}');">编辑</a>
						</s:if>
						<a href="#" onclick="deleteEntity1(${entity.id},this);">删除</a>
					</div>
				</td>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>