$(function(){
	$("#su").click(function(){
		var kw = $("#kw").val();
		kw = kw.replace(/[ ]/g, "");
		if(kw === '')
			return false;
	});
	$("#kw").focus(function(){
		$(".s_ipt_wr").css("borderColor","#2d78f4");
	});
	$("#kw").blur(function(){
		$(".s_ipt_wr").css("borderColor","#b8b8b8");
	});
});
var t=setInterval(urlencode,1000);
function urlencode(){
	if($(".related_kwy_words").html() != ""){
		$(".related_kwy_words").find("a").each(function(i){
			href = ($(this).attr("href")).split("=");
			$(this).attr("href",href[0]+"="+encodeURIComponent(href[1]));
		});
		clearInterval(t);
	}
}
