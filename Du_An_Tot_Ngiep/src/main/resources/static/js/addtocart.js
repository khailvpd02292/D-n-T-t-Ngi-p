$(document)
				.ready(
						function() {

							$('.procart')
									.click(
											function(e) {
												e.preventDefault();
												console
														.log("---start function--");
												//var amount = parseInt($('#quantity').val());
												var amount = 1;
												var idproduct = $(this).data(
														'productid');
												$
														.post(
																"/insertproduct}",
																{
																	idproduct : idproduct,
																	amount : amount
																},
																function(data,
																		status) {
																	console
																			.log(data);
																	if (data == "1") { // them thanh cong. san pham chua nam trong gio hang
																		alert("Thêm thành công !");
																		var so_luong = parseInt($(
																				"#so_luong_in_cart")
																				.html());
																		so_luong++;
																		$(
																				"#so_luong_in_cart")
																				.html(
																						so_luong);
																	} else if (data == "5") { // them thanh cong, san pham da nam trong gio hang
																		alert("Thêm thành công ! San pham da ton tai trong gio hang");
																	} else if (data == "2") {
																		alert("Thêm thất bại!");
																	} else if (data == "3") {
																		alert("Có lỗi !");
																	} else if (data == "4") {
																		alert("Đã cộng thêm 1 sản phẩm vào giỏ hàng");
																	} else
																		alert("error:"
																				+ data)
																});
											});
						});