// sortable
$(document).ready(function(){
	$(function() {
		$( "#mail_list" ).resizable({
			maxHeight: 300,
			minHeight: 118,
			handles: 's'
		});
		$( "#m_left" ).resizable({
			maxHeight: 650,
			minHeight: 650,
			maxWidth: 400,
			minWidth: 120,
			handles: 'e'
		});
	});
});