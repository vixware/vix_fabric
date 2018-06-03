$(function () { 

	var tg_actor = {};

			
	
	
	var tg1 = $("#p1").timeline({
			"timezone":"-07:00",
			"min_zoom":1, 
			"max_zoom":52, 
			"show_centerline":true,
			"data_source":"json_tests/js_history.json",
			"show_footer":true,
			"display_zoom_level":true
	}).resizable({
		stop:function(){ 
			$(this).data("timeline").resize();
		}
	});


	tg_actor = tg1.data("timeline");
	
	timeglider.methods = {
		zoomIn:function() { tg_actor.zoom("in"); }
	};

	
	// test to bring up filter by default, on load
	setTimeout(function() {
		$(".tg-timeline-legend-bt").trigger("click");
	}, 2000);
	
	
	$("#tags-button").bind("click", function() {
		var txt = $("#tags-input").val();
		tg_actor.filterBy("tags", txt);
	});

					

}); // end document-ready
