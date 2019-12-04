function clear() {
	document.getElementById("gmail").innerHTML = "";
	document.getElementById("matkhau").innerHTML = "";
}

function checkempty(form) {
	clear();
	if (form.email.value.trim() == "") {
		document.getElementById("gmail").innerHTML = "Không để trống email!";
		return false;
	} 
	else if (form.password.value.trim() == "") {
		document.getElementById("matkhau").innerHTML = "Không để trống mật khẩu!";
		return false;
	} 

	else {
		return true;
	}

}