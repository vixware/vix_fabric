<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">



function projectVote(id){
	$.ajax({
		  url:"${vix}/oa/votesDetailsAction!goProjectVote.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"投票项目",
					html:html,
					callback : function(action){
						if(action == 'ok'){
								}
							} ,
					/* btnsbar : $.btn.OKCANCEL */
				});
		  }
	});
};


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
</script>
<input type="hidden" id="brandTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="brandPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="brandTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="brandOrderField" value="${pager.orderField}" />
<input type="hidden" id="brandOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="brandInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="75%">标题</th>
			<th width="10%">类型</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>妙妙性........</td>
				<td>多选</td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<s:if test="tag != 'choose'">
							<a href="#" nclick="projectVote('${entity.id}');">项目投票</a>
							<a href="#" onclick="saveOrUpdate(${entity.id},$('#parentId').val());">修改</a>
							<a href="#" onclick="deleteEntity('${entity.id}');">删除</a>
						</s:if>
					</div>
				</td>
				<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td> --%>
				<%-- <td><span style="color: gray;">${entity.uploadPersonName }</span></td>
				<td><a href="#" style="color: gray;"> 
						<s:if test="%{#entity.newsType == 0}">文本</s:if> 
						<s:elseif test="%{#entity.newsType == 1}">图片</s:elseif>
					</a>
				</td>
				<td><span style="color: gray;">${entity.bizOrgNames }</span></td>
				<td><a href="#" style="color: gray;" onclick="popNews('${entity.id}');">${entity.title}</a>
				<td><a href="#" style="color: gray;"><fmt:formatDate
					value="${entity.createTime}" type="both" pattern="yyyy-MM-dd" /></a>
				</td>
				<td><span style="color: gray;">${entity.readTimes }</span></td>
				<td><span style="color: gray;">${entity.comments.size() }</span></td>
				<td id="isPublish_${entity.id}">
						<s:if test="%{#entity.isPublish == 1}">终止</s:if> 
						<s:elseif test="%{#entity.isPublish == 0}">生效</s:elseif>
				</td> --%>
				<%-- <td>					
					<div class="untitled" style="position: static;display: inline;">
						<s:if test="tag != 'choose'">
							<a href="#" onclick="saveOrUpdate('${entity.id}');">修改</a>
						<a href="#" onclick="saveOrUpdate(${entity.id},$('#parentId').val());">修改</a>
						</s:if>
						<s:if test="tag != 'choose'">
							<a href="javascript:void(0);" onclick="popNews('${entity.id}');">查看评论</a>
						</s:if>
						<s:if test="%{#entity.isPublish==0 }">
							<a href="#" id="ip_link_${entity.id}" onclick="updateIsPublish(${entity.id},${entity.isPublish})">
								<span id="entity_${entity.id}">终止</span> 
							</a>
						</s:if>
						<s:elseif test="%{#entity.isPublish==1 }">
							<a href="#" id="ip_link_${entity.id}" onclick="updateIsPublish(${entity.id},${entity.isPublish})">
								<span id="entity_${entity.id}">生效</span>
							</a>
						</s:elseif><s:else></s:else>
						<a href="#" onclick="deleteEntity('${entity.id}');">删除</a>
					</div>
				</td> --%>
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
			</tr>
		</c:forEach>
	</tbody>
</table>