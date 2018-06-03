// sortable
$(document).ready(function(){
	$(function() {
		$( ".connectedSortable" ).sortable({
			connectWith: ".connectedSortable",
			handle: '.c_title',
			opacity: 0.4
		})
	});
});

// close
$(document).ready(function(){
	$(".close").click(	  
	  function () {$(this).parent().parent().parent().css("display","none");}
	)
});