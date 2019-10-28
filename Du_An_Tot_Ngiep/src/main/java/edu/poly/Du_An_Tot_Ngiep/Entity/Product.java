package edu.poly.Du_An_Tot_Ngiep.Entity;

import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	@NotNull
	private String name;
	@Min(value = 1000)
	private double price;
	@Lob
	private byte[] image;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idCategory", insertable = true, updatable = true)
	private Category category;

	public Product() {
		super();
	}

	public Product(int idProduct, @NotNull String name, @Min(1000) double price, byte[] image,
			@NotNull Category category) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.price = price;
		this.image = image;
		this.category = category;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public String getImageBase64() {
		if (this.getImage() == null) {
			return "";
		} else {
			return Base64.getEncoder().encodeToString(this.image);
		}
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
