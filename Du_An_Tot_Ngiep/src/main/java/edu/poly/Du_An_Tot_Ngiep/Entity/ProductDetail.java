package edu.poly.Du_An_Tot_Ngiep.Entity;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "productDetail")
public class ProductDetail implements Serializable {
	
	private static final long serialVersionUID = -7893237204476214050L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProductDetail;
	private String origin;
	
	private String description;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfManufacture;
	@Lob
	private byte[] image_1;
	@Lob
	private byte[] image_2;
	@NotNull
	@OneToOne
	@JoinColumn(name= "idProduct", insertable = true, updatable = true)
	private Product product;
	public ProductDetail() {
		super();
	}
	public ProductDetail(int idProductDetail, String origin, String description, Date dateOfManufacture, byte[] image_1,
			byte[] image_2, @NotNull Product product) {
		super();
		this.idProductDetail = idProductDetail;
		this.origin = origin;
		this.description = description;
		this.dateOfManufacture = dateOfManufacture;
		this.image_1 = image_1;
		this.image_2 = image_2;
		this.product = product;
	}
	public int getIdProductDetail() {
		return idProductDetail;
	}
	public void setIdProductDetail(int idProductDetail) {
		this.idProductDetail = idProductDetail;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}
	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}
	public byte[] getImage_1() {
		return image_1;
	}
	public String getBase64Image_1() {
		if (this.getImage_1() == null) {
			return "";
		} else {
			return Base64.getEncoder().encodeToString(this.getImage_1());
		}
	}
	public void setImage_1(byte[] image_1) {
		this.image_1 = image_1;
	}
	public byte[] getImage_2() {
		return image_2;
	}
	public String getBase64Image_2() {
		if (this.getImage_2() == null) {
			return "";
		} else {
			return Base64.getEncoder().encodeToString(this.getImage_2());
		}
	}
	public void setImage_2(byte[] image_2) {
		this.image_2 = image_2;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
