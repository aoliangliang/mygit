var linkOne = window.location.href.substring(window.location.href.lastIndexOf("\/") - 3, window.location.href.length)
var op = document.getElementsByClassName('mainList_a');
for (let i = 0; i < op.length; i++) {
	var linkOne2 = op[i].href.substring(op[i].href.lastIndexOf("\/") - 3,
			op[i].href.length)
	if (linkOne == linkOne2) {
		op[i].style.color = '#3699ff'
         op[i].parentNode.parentNode.style.display='block';
		 op[i].parentNode.parentNode.parentNode.childNodes[1].childNodes[1].style.transform='rotate(90deg) translateY(-50%)';
	}
}