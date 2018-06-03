<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/base/css/task.css" >
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> 任务与计划 <span>> 项目任务 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3 col-md-3 col-lg-3">
			<input id="employeeId" type="hidden" value="${emp.id}">
			<div class="well custom-scroll" style="height:990px;overflow:auto;">
				<!-- 搜索 -->	
				<!-- <div class="input-group">
					<input type="text" class="form-control"> 
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div> -->
				<h4 class="semi-bold text-center">成员列表</h4>
				<hr/>
				<!-- 成员列表 -->
				<div class="chat-body no-padding profile-message">
					<ul>
						<c:if test="${employeeList != null && fn:length(employeeList) > 0}">
							<c:forEach items="${employeeList}"  var="employee" varStatus="s">
								<li class="message" style="margin:20px 0;" id="${employee.id}">
									<s:if test="employee.headImgUrl != null && employee.headImgUrl != ''">
										<img src="${employee.headImgUrl}" class="" alt="sunny"> 
									</s:if>
									<s:else>
										<img src="${nvix}/vixntcommon/base/images/touxiang.png" class="" alt="sunny"> 
									</s:else>
									<span class="message-text"> 
										<a href="javascript:void(0);" class="username single-line">${employee.name}</a> 
									</span>
									<c:choose>
										<c:when test="${emp != null && emp.id == employee.id}">
											<i class="fa fa-check txt-color-red"></i>
										</c:when>
										<c:otherwise>
											<i class="fa fa-check"></i>
										</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<!-- <button type="button" class="btn btn-primary btn-lg btn-block"><i class="fa fa-check"></i>&nbsp;&nbsp;添加关注</button> -->
			</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-9">
			<div class="jarviswidget jarviswidget-color-white">
				<header>
					<span class="widget-icon"> <i class="fa fa-calendar"></i></span>
					<h2>任务记录</h2>
					<div class="widget-toolbar">
						<div class="btn-group">
							<button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
								展示 <i class="fa fa-caret-down"></i>
							</button>
							<ul class="dropdown-menu js-status-update pull-right">
								<li><a href="javascript:void(0);" id="mt">月</a></li>
								<li><a href="javascript:void(0);" id="ag">周</a></li>
								<li><a href="javascript:void(0);" id="td">天</a></li>
							</ul>
						</div>
					</div>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div class="widget-body-toolbar">
							<div id="calendar-buttons">
								<div class="btn-group">
									<a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-prev"><i class="fa fa-chevron-left"></i></a> <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-next"><i class="fa fa-chevron-right"></i></a>
								</div>
							</div>
						</div>
						<div id="calendar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		pageSetUp();
		// "use strict";
	    var date = new Date();
	    var d = date.getDate();
	    var m = date.getMonth();
	    var y = date.getFullYear();
	    var initDrag = function (e) {
	        var eventObject = {
	            title: $.trim(e.children().text()), // use the element's text as the event title
	            description: $.trim(e.children('span').attr('data-description')),
	            icon: $.trim(e.children('span').attr('data-icon')),
	            className: $.trim(e.children('span').attr('class')) // use the element's children as the event class
	        };
	        e.data('eventObject', eventObject);
	        e.draggable({
	            zIndex: 999,
	            revert: true, // will cause the event to go back to its
	            revertDuration: 0 //  original position after the drag
	        });
	    };
	    /* var addEvent = function (title, priority, description, icon) {
	        title = title.length === 0 ? "Untitled Event" : title;
	        description = description.length === 0 ? "No Description" : description;
	        icon = icon.length === 0 ? " " : icon;
	        priority = priority.length === 0 ? "label label-default" : priority;
	
	        var html = $('<li><span class="' + priority + '" data-description="' + description + '" data-icon="' +
	            icon + '">' + title + '</span></li>').prependTo('ul#external-events').hide().fadeIn();
	
	        $("#event-container").effect("highlight", 800);
	
	        initDrag(html);
	    }; */
	    /* $('#external-events > li').each(function () {
	        initDrag($(this));
	    });
	    $('#add-event').click(function () {
	        var title = $('#title').val(),
	            priority = $('input:radio[name=priority]:checked').val(),
	            description = $('#description').val(),
	            icon = $('input:radio[name=iconselect]:checked').val();
	
	        addEvent(title, priority, description, icon);
	    }); */
	    var calendar =  $('#calendar').fullCalendar({
	    	lang : 'zh-cn',
			header : {
			left : 'title',
			center : 'month,agendaWeek,agendaDay',
			right : 'prev,today,next'
			},
			firstDay : 1,
			editable : false,
			droppable : false,
			selectable : true,
			selectHelper : true,
	        buttonText: {
	            prev: '<i class="fa fa-chevron-left"></i>',
	            next: '<i class="fa fa-chevron-right"></i>'
	        },
	        /* drop: function (date, allDay) { // this function is called when something is dropped
	        	var originalEventObject = $(this).data('eventObject');
				$.ajax({
				type : "POST",
				url : "${nvix}/nvixnt/nvixScheduleAction!updateCanlendar.action?id=" + originalEventObject.id + '&startTime=' + moment(date).format('YYYY-MM-DD HH:mm:ss') + "&endTime=" + moment(date).format('YYYY-MM-DD HH:mm:ss'),
				success : function() {
					$('#calendar').fullCalendar('refetchEvents');
				}
				});
				var copiedEventObject = $.extend({}, originalEventObject);
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
			},
			eventDrop : function(calEvent, jsEvent, ui, view) {
				var startDate = calEvent.start;
				var endDate = calEvent.start;
				if (calEvent.end != null) {
					endDate = calEvent.end;
				}
				$.ajax({
				type : "POST",
				url : "${nvix}/nvixnt/taskAndPlanAction!eventDropCanlendar.action?id=" + calEvent.id + '&startTime=' + moment(startDate).format('YYYY-MM-DD HH:mm:ss') + "&endTime=" + moment(endDate).format('YYYY-MM-DD HH:mm:ss'),
				success : function() {
					$('#calendar').fullCalendar('refetchEvents');
				}
				});
			}, */
			select : function(start, end, allDay) {
				var params = {
				start : start,
				end : end,
				allDay : allDay
				};
				openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateTeamTask.action?parentId=' + $("#employeeId").val() + '&start=' + moment(start).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(end).format('YYYY-MM-DD HH:mm:ss') + "&treeType=E", "taskForm", "新增任务", 900, 650, null,null,function(){
					loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTeamTask.action?employeeId=' + $("#employeeId").val());
				});
			},
			eventClick : function(calEvent, jsEvent, view) {
				if (calEvent.id != null) {
					goShowTaskDetails(calEvent.id);
				}else{
					openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateTeamTask.action?parentId=' + $("#employeeId").val() + '&start=' + moment(start).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(end).format('YYYY-MM-DD HH:mm:ss') + "&treeType=E", "taskForm", "新增任务", 900, 650, null,null,function(){
						loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTeamTask.action?employeeId=' + $("#employeeId").val());
					});
				}
			},
			events : function(start, end, timezone, callback) {
				$.ajax({
				url : "${nvix}/nvixnt/taskAndPlanAction!calendarEvents.action?employeeId=" + $("#employeeId").val(),
				dataType : 'json',
				data : {
				start : start.format(),
				end : end.format()
				},
				success : function(doc) {
					var event = [];
					$(doc).each(function() {
						event.push({
						id : $(this).attr('id'),
						allDay : $(this).attr('allDay'),
						icon : $(this).attr('icon'),
						title : $(this).attr('title'),
						start : $(this).attr('start'),
						end : $(this).attr('end'),
						className : eval($(this).attr('className'))
						});
					});
					callback(event);
				}
				});
			},

			eventRender : function(event, element, icon) {
				if (!event.description == "") {
					element.find('.fc-event-title').append("<br/><span class='ultra-light'>" + event.description + "</span>");
				}
				if (!event.icon == "") {
					element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon + " '></i>");
				}
			},
			/* eventResize : function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view) {
				var startDate = event.start;
				var endDate = event.start;
				if (event.end != null) {
					endDate = event.end;
				}
				openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateTeamTask.action?parentId=' + $("#employeeId").val() + '&startTime=' + $.fullCalendar.formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss') + "&treeType=E", "taskForm", "新增任务", 900, 650, null,null,function(){
					loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTeamTask.action?employeeId=' + $("#employeeId").val());
				});
			}, */
			windowResize : function(event, ui) {
				$('#calendar').fullCalendar('render');
			}
		});

		$('#external-events > li').each(function() {
			initDrag($(this));
		});

		$('.fc-header-right, .fc-header-center').hide();

		$('#calendar-buttons #btn-prev').click(function() {
			$('.fc-button-prev').click();
			return false;
		});

		$('#calendar-buttons #btn-next').click(function() {
			$('.fc-button-next').click();
			return false;
		});

		$('#calendar-buttons #btn-today').click(function() {
			$('.fc-button-today').click();
			return false;
		});

		$('#mt').click(function() {
			$('#calendar').fullCalendar('changeView', 'month');
		});
		$('#ag').click(function() {
			$('#calendar').fullCalendar('changeView', 'agendaWeek');
		});
		$('#td').click(function() {
			$('#calendar').fullCalendar('changeView', 'agendaDay');
		});
		/* $(".chat-body ul").on("mouseover","li.message",function(){
			$(this).children("i").addClass("txt-color-blue");
		})
		
		$(".chat-body ul").on("mouseout","li.message",function(){
			$(this).children("i").removeClass("txt-color-blue");
		}) */
		$(".message").click(function(){
			//$(this).children("i.fa").addClass("txt-color-blue");
			//$(this).siblings().children("i.fa").removeClass("txt-color-blue");
			$("#employeeId").val($(this).attr("id"));
			loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTeamTask.action?employeeId=' + $("#employeeId").val());
		});

	});
	
	function goSaveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateTeamTask.action?parentId=' + $("#employeeId").val() + "&id=" + id + "&treeType=E", "taskForm", "新增任务", 900, 650, null,null,function(){
			loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTeamTask.action?employeeId=' + $("#employeeId").val());
		});
	};
	
	//跳转到任务详情
	function goShowTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id + '&source=teamtask');
	};

</script>