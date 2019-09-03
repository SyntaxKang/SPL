
var d = new Date();
var currentDate = d.getFullYear() + "년 " + ( d.getMonth() + 1 ) + "월 " + d.getDate() + "일";
var result = document.getElementById("date");
result.innerHTML = currentDate ;
