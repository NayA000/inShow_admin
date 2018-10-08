var ulist = new Vue({
	el : "#userlist",
	data : {
		uitems : [],
	}
})
var blist = new Vue({
	el : "#bgmlist",
	data : {
		bitems : [],
	}
})
var tlist = new Vue({
	el : "#topiclist",
	data : {
		titems : [],
	}
})

var vlist = new Vue({
	el : "#videolist",
	data : {
		vitems : [],
	}
})

var balist = new Vue({
	el : "#barragelist",
	data : {
		baitems : [],
	}
})
/*
 * var divs = new Vue({ el: "#divs", data: { userShow : false, bgmShow : true,
 * topicShow : true, videoShow : true, barragesShow : true } })
 */
//var url = "http://192.168.1.5:8081";
var url = "http://localhost:8081";
var serverUrl = "http://192.168.1.7:8081";
var userPage = 0;
var bgmPage = 0;
var topicPage = 0;
var videoPage = 0;
var barragesPage = 0;

function showDiv(flag) {
	var i = 0;
	var list = [ "#userdiv", "#bgmdiv", "#topicdiv", "#videodiv", "#barragediv" ];
	while (i != 5) {
		if (i != flag) {
			$(list[i]).attr({
				hidden : true
			})
		} else {
			$(list[i]).attr({
				hidden : false
			})
		}
		i++;
	}
}

function getUserList() {
	console.log("点了一下");
	$.ajax({
		url : url + '/user/getAll?page=' + userPage,
		type : 'GET',
		// data: "page=0",
		success : function(msg) {
			console.log(msg);
			ulist.$data.uitems = msg.data;
		}
	});
}
function getBgmList() {
	console.log("点了一下");
	$.ajax({
		url : url + '/bgm/getAll?page=' + bgmPage,
		type : 'GET',
		// data: "page=0",
		success : function(msg) {
			console.log(msg);
			blist.$data.bitems = msg.data;
		}
	});
	bgmPage += 1;
}
function getTopicList() {
	console.log("点了一下");
	$.ajax({
		url : url + '/topic/getAll?page=' + topicPage,
		type : 'GET',
		// data: "page=0",
		success : function(msg) {
			console.log(msg);
			tlist.$data.titems = msg.data;
		}
	});
	topicPage += 1;
}
function getVideoList() {
	console.log("点了一下");
	$.ajax({
		url : url + '/video/getAll?page=' + videoPage,
		type : 'GET',
		// data: "page=0",
		success : function(msg) {
			console.log(msg);
			vlist.$data.vitems = msg.data;
		}
	});
	videoPage += 1;
}

function getBarrageList() {
	console.log("点了一下");
	$.ajax({
		url : url + '/barrage/getAll?page=' + barragesPage,
		type : 'GET',
		// data: "page=0",
		success : function(msg) {
			console.log(msg);
			balist.$data.baitems = msg.data;
		}
	});
	barragesPage += 1;
}
// ------------------------------------------------------------
// day 2
var userCheck = [];
var bgmCheck = [];
var topicCheck = [];
var videoCheck = [];
var barrageCheck = [];
var check=[userCheck,bgmCheck,topicCheck,videoCheck,barrageCheck]
function getCheck(flag,name) {
	obj = document.getElementsByName(name);
	check[flag] = [];
	for (k in obj) {
		if (obj[k].checked)
			check[flag].push(obj[k].value);
	}
	alert(check[flag]);
}
//------------------------------------------
//topic-->delect     search
function topicDelect(){
	getCheck(2,'topicCheck');
	$.ajax({
		//url : url + '/topic/delect' ,
		url : url + '/topic/delect?tid='+ check[2],
		type : 'GET',
		success : function(msg) {
			console.log(msg);
			if(topicPage>0)
				topicPage-=1;
			getTopicList();
		}
	});
}
var topicSearchPage = 0;

function topicSearch(newSearch){// 搜索案件使用topicSearch(1) 下一页使用topicSearch(2) 上一页使用topicSearch(0)
	console.log("----------");
	if(newSearch===1) topicSearchPage=0;
	var key = document.getElementsByName("topicKey")[0].value;
	//console.log("----------"+key+"+++"+key2+"-+-+-+-+"+key3);
	$.ajax({
		url : url + '/topic/search' ,
		type : 'GET',
		data: {
			key: key,
			page: topicSearchPage
		},
		success : function(msg) {
			console.log(msg);
			tlist.$data.titems = msg.data;
		}
	});
	if(newSearch===0&&topicSearchPage>0) topicSearchPage-=1;
	if(newSearch===2) topicSearchPage+=1;
}

//-------------33333-----------------------------------------------------------
//video  ==>    delect   chack    seal
function videoDelect(){
	getCheck(3,'videoCheck');
	$.ajax({
		//url : url + '/topic/delect' ,
		url : url + '/video/delect?tid='+ check[3],
		type : 'GET',
		success : function(msg) {
			console.log(msg);
			if(topicPage>0)
				topicPage-=1;
			getVideoList();
		}
	});
}
var videoSearchPage = 0;

function videoSearch(newSearch){// 搜索案件使用videoSearch(1) 下一页使用videoSearch(2) 上一页使用videoSearch(0)
	console.log("----------");
	if(newSearch===1) videoSearchPage=0;
	var key = document.getElementsByName("videoKey")[0].value;
	console.log("----------"+key);
	$.ajax({
		url : url + '/video/search' ,
		type : 'GET',
		data: {
			key: key,
			page: videoSearchPage
		},
		success : function(msg) {
			console.log(msg);
			vlist.$data.vitems = msg.data;
		}
	});
	if(newSearch===0&&videoSearchPage>0) videoSearchPage-=1;
	if(newSearch===2) videoSearchPage+=1;
}





