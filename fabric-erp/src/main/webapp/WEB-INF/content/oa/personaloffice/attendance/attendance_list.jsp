<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
/* 外出登记 */
function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/oa/personalAttendanceAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
} 

/* 请假记录 */
function saveOrTleaveRecords(id){
	$.ajax({
		  url:"${vix}/oa/tleaveRecordsAction!goSaveOrTleaveRecords.action?id="+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

/* 出差登记 */
function saveOrTripRecord(id){
	$.ajax({
		  url:"${vix}/oa/tripRecordAction!goSaveOrTripRecord.action?id="+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

/* 加班登记 */
function saveOrOvertimeRecords(id){
	$.ajax({
		  url:"${vix}/oa/overtimeRecordsAction!goSaveOrOvertimeRecords.action?id="+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

/* 个人考勤记录 */
function saveOrAttendance(id){
	$.ajax({
		  url:"${vix}/oa/attendanceAction!goSaveOrAttendance.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 230,
					title:"个人记录",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/oa/attendanceAction!saveOrAttendance.action',
									 {'attendance.id':$("#id").val(),									  
									  'attendance.loginDate':$("#loginDate").val(),	
									  'attendance.reason':$('input:radio[name=reason]:checked').val()
									},
									function(result){
										asyncbox.open({
											modal:true,
											width : 320,
											height : 150,
											title:"<s:text name='个人考勤记录'/>",
											html:result,
											callback : function(action){
												 pager("start","${vix}/oa/personalAttendanceAction!goAttendanceList.action?name=",'newtab5');
											},
											btnsbar : $.btn.OKCANCEL
										});
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

//处理外出记录删除操作
function deleteById(id,a){
	$.ajax({
		  url:'${vix}/oa/personalAttendanceAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='外出记录'/>",function(action){
				$(a).parent().parent().parent().empty();
			});
		  }
	});
}

//处理外出记录删除操作
function deleteLeave(id,a){
	$.ajax({
		  url:'${vix}/oa/personalAttendanceAction!deleteLeave.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='外出记录'/>",function(action){
				$(a).parent().parent().parent().empty();
			});
		  }
	});
}

//处理出差记录删除操作
function deleteTripRecord(id,a){
	$.ajax({
		  url:'${vix}/oa/personalAttendanceAction!deleteTripRecord.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='出差记录'/>",function(action){
				$(a).parent().parent().parent().empty();
			});
		  }
	});
}

//处理外出记录删除操作
function deleteOvertimeRecords(id,a){
	$.ajax({
		  url:'${vix}/oa/personalAttendanceAction!deleteOvertimeRecords.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='外出记录'/>",function(action){
				$(a).parent().parent().parent().empty();
			});
		  }
	});
}

//处理考勤记录删除操作
function deleteAttendance(id,a){
	$.ajax({
		  url:'${vix}/oa/personalAttendanceAction!deleteAttendance.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='考勤记录'/>",function(action){
				$(a).parent().parent().parent().empty();
			});
		  }
	});
}


//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');


/* 外出记录 */
pager("start","${vix}/oa/personalAttendanceAction!goSingleList.action?name="+name,'newtab1');
/* 请假记录 */
pager("start","${vix}/oa/personalAttendanceAction!goTleaveRecordsList.action?name="+name,'newtab2');
/* 出差记录 */
pager("start","${vix}/oa/personalAttendanceAction!goTripRecordList.action?name="+name,'newtab3');
/* 加班登记 */
pager("start","${vix}/oa/personalAttendanceAction!goOvertimeRecordsList.action?name="+name,'newtab4');
/* 考勤记录 */
pager("start","${vix}/oa/personalAttendanceAction!goAttendanceList.action?name="+name,'newtab5');
/* 我的值班 */
pager("start","${vix}/oa/personalAttendanceAction!goSingleList5.action?name="+name,'newtab6');


/* 外出记录搜索功能 */
switchSearch(1);
function switchSearch(idx){
	var url = "";
	if(idx==1){
		url = "${vix}/oa/personalAttendanceAction!goSearchPager.action";
	}else if(idx==2){
		url = "${vix}/oa/personalAttendanceAction!goTleaveList.action";
	}else if(idx==3){
		url = "${vix}/oa/personalAttendanceAction!goTripList.action";
	}else if(idx==4){
		url = "${vix}/oa/personalAttendanceAction!goOvertimeList.action";
	}else if(idx==5){
		url = "${vix}/oa/personalAttendanceAction!goAttendancePager.action";
	}else if(idx==6){
		url = "${vix}/oa/personalAttendanceAction!goSingle.action";
	}
	
	$.ajax({
		url:url ,
		type: "post",
		cache:false,
		success:function(html){
			$('#c_head').find('.search_simple').remove();
			$('#c_head').find('.search_advanced').remove();
			$('#c_head').append(html);
		}
	});
}


function djTab(count,idx,name,eventObj){
	switchSearch(idx);
	tab(count,idx,name,eventObj);
} 
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" /> <s:text name="print" /> </a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" /> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa_personal_office.png" alt="" /> <s:text name="oa_xtbg" /> </a></li>
				<li><a href="#"><s:text name="oa_grbg" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/personalAttendanceAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_personal_attendance" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="javascript:void(0);" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='外出登记' /></span></a>
		</p>
		<p>
			<a href="javascript:void(0);" onclick="saveOrTleaveRecords(0,$('#selectId').val());"><span><s:text name='请假登记' /></span></a>
		</p>
		<p>
			<a href="javascript:void(0);" onclick="saveOrTripRecord(0,$('#selectId').val());"><span><s:text name='出差登记' /></span></a>
		</p>
		<p>
			<a href="javascript:void(0);" onclick="saveOrOvertimeRecords(0,$('#selectId').val());"><span><s:text name='加班登记' /></span></a>
		</p>
		<p>
			<a href="javascript:void(0);" onclick="saveOrAttendance(0,$('#selectId').val());"><span><s:text name='个人考勤' /></span></a>
		</p>
	</div>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="${vix}/common/img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> < b>帮助</b>
				</strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
			</div>
		</div>
		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:djTab(6,1,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="外出记录" />
			</a></li>
			<li><a href="javascript:void(0);" id="qingjia" onclick="javascript:djTab(6,2,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="请假记录" />
			</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:djTab(6,3,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="出差记录" />
			</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:djTab(6,4,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="加班登记 " />
			</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:djTab(6,5,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="考勤记录" />
			</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:djTab(6,6,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="我的值班  " />
			</a></li>
		</ul>
	</div>

	<!-- 1 -->
	<div class="box">
		<div id="right">
			<!--外出记录 -->
			<div id="newtab1"></div>
			<!--请假记录 -->
			<div id="newtab2" style="display: none;"></div>
			<!-- 出差记录 -->
			<div id="newtab3" style="display: none;"></div>
			<!-- 加班登记 -->
			<div id="newtab4" style="display: none;"></div>
			<!-- 考勤记录 -->
			<div id="newtab5" style="display: none;"></div>
			<!-- 我的值班 -->
			<div id="newtab6" style="display: none;"></div>
		</div>
	</div>
</div>