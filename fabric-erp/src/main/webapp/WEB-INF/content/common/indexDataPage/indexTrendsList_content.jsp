<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function popNews (id){
		asyncbox.open ({
		modal : true ,
		width : 780 ,
		height : 460 ,
		title : "新闻" ,
		url : "${vix}/common/vixIndexDataAction!goNews.action?id=" + id ,
		btnsbar : $.btn.OKCANCEL
		});
	}
</script>
<div class="box_news">
	<table class="list">
		<tbody>
			<tr>
				<th></th>
			</tr>
			<s:iterator value="trendsList" var="entity" status="s">
				<tr>
					<td><a href="javascript:;" onclick="popNews('${entity.id}');"><span>${entity.title}</span> <span><s:date format="yyyy-MM-dd" name="%{#entity.createTime}" /></span></a>
						<p>${entity.keyContent}</p></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>
