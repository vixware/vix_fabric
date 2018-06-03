<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("table1 tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table1 tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){ 
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
   
function deleteEntity1(id){
	asyncbox.confirm('确定要删除该品牌么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
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


/* 删除附件 */
function deleteManagement(id,a){
	asyncbox.confirm('确定要删除该附件么?','提示信息',function(action){
		if(action == 'ok'){
			deleteUploader(id,a);
		}
	});
}
</script>
<table id="table1" class="list">
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
			<th><s:text name="编号" /></th>
			<th><s:text name="文档名称" /></th>
			<th><s:text name="文档类型" /></th>
			<th><s:text name="上传时间" /></th>
			<th><s:text name="上传者" /></th>
			<th><s:text name="oa_operating" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><a href="${vix}/oa/phoneSmsAction!downloadEqDocument.action?id=${entity.id}">${entity.id}</a></td>
				<td><span style="color: gray;">${entity.title}</span></td>
				<td><span style="color: gray;">${entity.fileType}</span></td>
				<td><span style="color: gray;"><s:date name="#entity.uploadTime" format="yyyy-MM-dd" /></td>
				<td><span style="color: gray;">${entity.title}</span></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<a href="${vix}/oa/phoneSmsAction!downloadEqDocument.action?id=${entity.id}">下载</a> <a href="#" onclick="deleteManagement(${entity.id},this);">删除</a>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>