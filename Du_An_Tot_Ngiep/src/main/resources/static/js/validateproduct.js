function clear() {
	document.getElementById("ten").innerHTML = "";
	document.getElementById("anh").innerHTML = "";
	document.getElementById("tien").innerHTML = "";
	document.getElementById("ngayhethang").innerHTML = "";
	document.getElementById("nguongoc").innerHTML = "";
}


function checkempty(formproduct) {
	clear();
	if (formproduct.name.value.trim() == "") {
		document.getElementById("ten").innerHTML = "Không để trống tên!";
		return false;
	} 
	else if (formproduct.file.value.trim() == "") {
		document.getElementById("anh").innerHTML = "Không để trống ảnh!";
		return false;
	} 
	else if (formproduct.price.value.trim() == "") {
		document.getElementById("tien").innerHTML = "Không để trống giá tiền!";
		return false;
	} 
	else if (formproduct.dateOfManufacture.value.trim() == "") {
		document.getElementById("ngayhethang").innerHTML = "Không để trống ngày hết hạng!";
		return false;
	} 
	else if (formproduct.origin.value.trim() == "") {
		document.getElementById("nguongoc").innerHTML = "Không để trống nguồn gốc!";
		return false;
	} 
	else {
		return false;
	}

}