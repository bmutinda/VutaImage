
$(document).ready( function(){
	
	$('.menu a').click(function(event) {
		event.preventDefault();
		var link = this;
		$.smoothScroll({
			scrollTarget: link.hash
		});
	});
	
	prettyPrint();
})