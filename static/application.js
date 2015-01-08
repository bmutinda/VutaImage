
$(document).ready( function(){
	
	var scrollHack = function( ){
		console.log("afaftt")
	}
	
	
	$('.menu a').click(function(event) {
		event.preventDefault();
		var link = this;
		$.smoothScroll({
			offset: -110,
			scrollTarget: link.hash,
			afterScroll: scrollHack
		});
	});
	
	prettyPrint();
})