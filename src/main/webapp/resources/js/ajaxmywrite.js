
var referrer =  document.referrer;

console.log(referrer+"referrer!!!!!!!!! ");

console.log(typeof(referrer));
referrer = referrer.substring(27,referrer.length);

console.log(referrer+"referrer!!!!!!!!! ");
function confirmdelete(b_num, referrer) {
console.log(referrer+"dddddddddddd");
	var x = confirm("정말 삭제하시겠습니까?");
	if (x) {
		location.href = "writedelete?b_num=" + b_num+"&referrer="+referrer;
	} else {
		return false;
	}
}	