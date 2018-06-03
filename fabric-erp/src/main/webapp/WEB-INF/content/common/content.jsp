<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script src="${vix}/common/js/esl/esl.js"></script>
<script type="text/javascript">
	$.ajax({
    url : "http://localhost:8080/vix/loginaction",
    dataType : 'jsonp',
    data : {
	    userName : 1
    }
    });
    $(document).ready(function() {
	   	//通知公告
	    $("#bulletinIndexListDiv").load("${vix}/common/vixIndexDataAction!goIndexBulletinPage.action");
	    $("#schedulIndexListDiv").load("${vix}/common/vixIndexDataAction!goIndexSchedulPage.action");
	    $("#jobtodoIndexListDiv").load("${vix}/common/vixIndexDataAction!goIndexJobtodoPage.action");
	    $("#newsIndexListDiv").load("${vix}/common/vixIndexDataAction!goIndexNewsPage.action");
	    $("#perHisIndexListDiv").load("${vix}/common/vixIndexDataAction!goIndexOperHisPage.action");
	    $("#trendsIndexList").load("${vix}/common/vixIndexDataAction!goIndexTrends.action");
	    $(".connectedSortable").sortable({
		    connectWith : ".connectedSortable",
		    handle : '.c_title',
		    opacity : 0.4
	    }); 
	    
	    //setIndexPageDiv();
    });
    
    function setIndexPageDiv(){
    	$.ajax({
    		url:"${vix}/common/vixIndexDataAction!findPdcData.action",
    		cache: false,
    		//async:false,
    		dataType : "json",
    		success: function(data){
    			//var resData = $.parseJSON(data);
    			//debugger;
    			if(data!=null){
    				var tmp_url = '';
    				for(var i=0;i<data.length;i++){
    					tmp_url = "${vix}"+data[i].divUrl;
    					//alert(tmp_url);
    					$("#"+data[i].divId).load(tmp_url);
    					//$("#"+data[i].divId).show();
					}
    			}
    		}
    	});
    };
    
    function chosedatabydate(onchangedate) {
	    $.ajax({
	    url : '${vix}/common/vixIndexDataAction!goIndexDoList.action?onchangedate=' + onchangedate,
	    cache : false,
	    success : function(html) {
		    $("#todoLists").html(html);
	    }
	    });
    }
    function categoryTab(num,befor,id,e,entity) {
	    var el = e.target ? e.target.parentNode : e.srcElement.parentNode;
	    var pa = el.parentNode.getElementsByTagName("li");
	    for ( var i = 0; i < pa.length; i++) {
		    pa[i].className = "";
	    }
	    el.className = "current";
	    for (i = 1; i <= num; i++) {
		    try {
			    if (i == befor) {
				    document.getElementById(id + i).style.display = "block";
			    } else {
				    document.getElementById(id + i).style.display = "none";
			    }
		    } catch (e) {
		    }
	    }
	    if (entity != undefined) {
		    categoryPager('start', entity);
	    }
    }
    function categoryPager(tag,entity) {
	    if (entity == 'category') {
		    $.ajax({
		    url : '${vix}/common/vixIndexDataAction!goIndexDoList.action',
		    cache : false,
		    success : function(html) {
			    $("#todoLists").html(html);
		    }
		    });
	    }
	    if (entity == 'news') {
		    $.ajax({
		    url : '${vix}/common/vixIndexDataAction!goIndexNews.action',
		    cache : false,
		    success : function(html) {
			    $("#news").html(html);
		    }
		    });
	    }
	    if (entity == 'highcharts') {
		    $.ajax({
		    url : '${vix}/common/vixIndexDataAction!goHighcharts.action',
		    cache : false,
		    success : function(html) {
			    $("#highcharts").html(html);
		    }
		    });
	    }
	    if (entity == 'calendar') {
		    $.ajax({
		    url : '${vix}/common/vixIndexDataAction!goCalendar.action',
		    cache : false,
		    success : function(html) {
			    $("#calendar").html(html);
		    }
		    });
	    }
    }
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img width="26" height="26" alt="" class="png" src="img/newico.png">&nbsp;首页</a></li>
				<li><a href="#">工作台</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_menu">
		<ul>
			<li class="c_current"><a onclick="javascript:menu('0')"><em>我的工作台</em></a></li>
			<li><a onclick="javascript:menu('1');categoryTab(5,2,'a',event,'category')"><em>待办提醒</em></a></li>
			<li><a onclick="javascript:menu('2');categoryTab(5,3,'a',event,'calendar')"><em>日程安排</em></a></li>
			<!-- <li><a onclick="javascript:menu('3');categoryTab(5,4,'a',event,'highcharts')"><em>仪表盘</em></a></li> -->
			<li><a onclick="javascript:menu('3');categoryTab(5,4,'a',event,'news')"><em>新闻</em></a></li>
		</ul>
	</div>
	<!-- c_menu -->
	<div id="c0" class="c_content">
		<div class="left_box connectedSortable">
			<div class="box_content" id="jobtodoIndexListDiv"></div>
			<div class="box_content" id="bulletinIndexListDiv"></div>
			<div class="box_content" id="perHisIndexListDiv"></div>
		</div>
		<div class="right_box connectedSortable">
			<div class="box_content" id="schedulIndexListDiv"></div>
			<!-- <div class="box_content" id="newsIndexListDiv"></div> -->
			<div class="box_content" id="trendsIndexList"></div>
		</div>
	</div>
	<div id="c1" class="c_content" style="display: none;">
		<div class="box">
			<div id="right">
				<div id="newtab1">
					<div class="addleft">
						<div class="addbtn">
							<p>
								<img src="img/sys_jobtodo.png" /> 待办提醒
							</p>
						</div>
						<div id="date1" class="date_box"></div>
						<script type="text/javascript">
							WdatePicker({
                            eCont : 'date1',
                            dateFmt : 'yyyy-MM-dd HH:mm:ss',
                            skin : 'blue',
                            onpicked : function(dp) {
	                            chosedatabydate(dp.cal.getDateStr());
                            }
                            });
						</script>
					</div>
					<div class="addright">
						<div class="daily_box" id="todoLists"></div>
					</div>
				</div>
			</div>
			<!-- right -->
		</div>
	</div>
	<div id="c2" class="c_content" style="display: none;">
		<div id="calendar"></div>
	</div>
	<!-- <div id="c3" class="c_content" style="display: none;">
		<div id="highcharts"></div>
	</div> -->
	<div id="c3" class="c_content" style="display: none;">
		<div id="news"></div>
	</div>
</div>
<!-- content -->