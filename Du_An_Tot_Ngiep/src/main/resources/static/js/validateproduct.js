function clear() {
	document.getElementById("ten").innerHTML = "";
	document.getElementById("anh").innerHTML = "";
	document.getElementById("tien").innerHTML = "";
	document.getElementById("ngayhethang").innerHTML = "";
	document.getElementById("nguongoc").innerHTML = "";
}
function checkempty(formproduct) {
	clear();
//	var date1 = new Date(); // current date
//	var date2 = new Date(formproduct.dateOfManufacture.value); // mm/dd/yyyy format
//	var timeDiff = Math.abs(date2.getTime() - date1.getTime()); // in miliseconds
//	var timeDiffInSecond = Math.ceil(timeDiff / 1000); // in second
//	var days = Math.floor(timeDiffInSecond / 86400);
//	var hours = Math.floor(res / 3600) % 24;  
//	 var minutes = Math.floor(res / 60) % 60;
	if (formproduct.name.value.trim() == "") {
		document.getElementById("ten").innerHTML = "Không để trống tên!";
		return false;
	} 
	else if (formproduct.image.value.trim() == "") {
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
	else if (formproduct.price.value.trim() <1000 || formproduct.price.value == 0 ) {
		document.getElementById("tien").innerHTML = "Giá phải lớn lơn 1000!";
		return false;
	} 
	else {
		return true;
	}

}