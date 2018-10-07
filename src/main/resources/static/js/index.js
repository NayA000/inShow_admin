
var ulist = new Vue({
	el: "#userlist",
	data: {
		uitems: [],
	}
})
var blist = new Vue({
	el: "#bgmlist",
	data: {
		bitems: [],
	}
})
var tlist = new Vue({
	el: "#topiclist",
	data: {
		titems: [],
	}
})

var vlist = new Vue({
	el: "#videolist",
	data: {
		vitems: [],
	}
})

var balist = new Vue({
	el: "#barragelist",
	data: {
		baitems: [],
	}
})
/*var divs = new Vue({
	el: "#divs",
	data: {
		userShow : false,
		bgmShow : true,
		topicShow : true,
		videoShow : true,
		barragesShow : true
	}
})*/
var url="http://192.168.1.5:8081";
var serverUrl="http://192.168.1.7:8081";
var userPage = 0;
var bgmPage = 0;
var topicPage = 0;
var videoPage = 0;
var barragesPage = 0;

function showDiv(flag){
	var i=0;
	var list=["#userdiv","#bgmdiv","#topicdiv","#videodiv","#barragediv"];
	while(i!=5){
		if(i!=flag){
			$(list[i]).attr({ hidden:true })
		}else{
			$(list[i]).attr({ hidden:false })
		}
		i++;
	}
}

function getUserList(){
	console.log("点了一下");
	$.ajax({
		url : url+'/user/getAll?page='+userPage,
		type : 'GET',
		//data: "page=0",
		success: function(msg){
		     console.log( msg);
		     ulist.$data.uitems = msg.data;
		   }
	});
}
function getBgmList(){
	console.log("点了一下");
	$.ajax({
		url : url+'/bgm/getAll?page='+bgmPage,
		type : 'GET',
		//data: "page=0",
		success: function(msg){
		     console.log( msg);
		     blist.$data.bitems = msg.data;
		   }
	});
	bgmPage+=1;
}
function getTopicList(){
	console.log("点了一下");
	$.ajax({
		url : url+'/topic/getAll?page='+topicPage,
		type : 'GET',
		//data: "page=0",
		success: function(msg){
		     console.log( msg);
		     tlist.$data.titems = msg.data;
		   }
	});
	topicPage+=1;
}
function getVideoList(){
	console.log("点了一下");
	$.ajax({
		url : url+'/video/getAll?page='+videoPage,
		type : 'GET',
		//data: "page=0",
		success: function(msg){
		     console.log( msg);
		     vlist.$data.vitems = msg.data;
		   }
	});
	videoPage+=1;
}

function getBarrageList(){
	console.log("点了一下");
	$.ajax({
		url : url+'/barrage/getAll?page='+barragesPage,
		type : 'GET',
		//data: "page=0",
		success: function(msg){
		     console.log( msg);
		     balist.$data.baitems = msg.data;
		   }
	});
	barragesPage+=1;
}





