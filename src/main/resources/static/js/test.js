/**
 * 
 */
var flag = 0;
var acookie = document.cookie.split("; ");
var app = new Vue({
	  el: '#app',
	  data: {
	    message: 'Hello Vue!'
	  }
	})
var string22 = 'wahaha';
var divString = new Vue({
	el: "#div2",
	data:{
		myString: string22
	}
})
function but2(){
	console.log("-+-+-+-+-+-+-+"+app.$data.message);
	divString.$data.myString += "+-";
	string22+="0-";
	console.log("++++++"+string22);
}


var mylist = [1,5,6,8,9];
var list1 = new Vue({
	el: "#list1",
	data: {
		items: mylist 
	} 
})
function getlist(){
	console.log("点了一下");
	$.ajax({
		url : 'http://192.168.1.5:8081/bgm/getAll?page=0',
		type : 'GET',
		//data: "page=0",
		success: function(msg){
		     console.log( msg);
		     list1.$data.items = msg.data;
		   }
	});
}






function aaa() {
	console.log("----------------");
	if (flag === 0) {
		$.ajax({
			url : 'http://192.168.1.5:8081/admin/login',
			type : 'GET',
			success : function(data) {
				if (data) {
					var a = "#div1";
					$(a).html(data);
					flag = 1;
				}
			}
		});
	} else {
		$.ajax({
			url : 'http://192.168.1.5:8081/admin/login',
			type : 'GET',
			success : function(data) {
				if (data) {
					var a = "#div1";
					console.log($(a).attr("id"));
					// $("img").attr({ src: "test.jpg", alt: "Test Image" });设置属性值
					//$("p").text("Hello world!");                           设置标签值   无参时为获取text值
					$(a).html("------");
					flag = 0;
				}
			}
		});
	}

}