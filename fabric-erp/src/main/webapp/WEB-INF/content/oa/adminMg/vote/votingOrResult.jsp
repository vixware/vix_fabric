<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<head>
<title>jQuery common</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="" rel="stylesheet">
<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        //初始化下拉列表--动态添加
        var item = ['幼儿园','小学','初中','高中','大学','研究生','博士','硕士'];
        var html ="<option value='0'>请选择</option>";
        for (var i = 0;i < item.length;i++){
            html += "<option value='"+(i+1)+"'>"+item[i]+"</option>";
        }
        $("#grade").empty().append(html);

        $("#grade option[value='0']").attr("selected","selected");//默认第一项被选中
    })
    //为Select添加事件，当选择其中一项时触发
    function showIt(){
        var selectText = $("#grade option:selected").text();//获取Select选择的Text
        //var selectText = $("#grade").find("option:selected").text();//这种方式也可行
        var selectValue = $("#grade").val();//获取被选择的value
        var selectIndex = $("#grade").get(0).selectedIndex//获取select的索引值
        var text = '选择：'+selectText+"\n";
        text +='value值：'+selectValue+"\n";
        text +='索引值：'+selectIndex;
        $("#text").text(text);
    }
</script>
</head>
<body>
	<div>
		<select name='grade' id='grade' onchange="showIt()"></select>
		<p>
			<textarea name='text' id='text' row='30' col='100'></textarea>
		</p>
	</div>
</body>