package edu.poly.Du_An_Tot_Ngiep.Entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Import")
public class Import {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int IdImport;
	
	@NotNull
	@Column(columnDefinition = "nvarchar(150)")
	private String IdUser;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idProduct", insertable = true, updatable = true)
	private Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name ="createDate",updatable = false)
	private Date createDate;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@UpdateTimestamp
//	@Column(name = "updateDate")
//	private Date updateDate;
	
	
	@Min (value = 1)
	@Column(name = "Quantity",updatable = false )
	private int Quantity;
	
	public Import() {
		super();
	}
	
	public Import(int IdImport, @NotNull String IdUser, @NotNull Product product, Date createDate, @Min(1) int Quantity   ) {
		super();
		this.IdImport = IdImport;
		this.IdUser = IdUser;
		this.product = product;
		this.createDate = createDate;
//		this.updateDate = updateDate;
		this.Quantity = Quantity;
	}

	public int getIdImport() {
		return IdImport;
	}

	public void setIdImport(int idImport) {
		IdImport = idImport;
	}

	public String getIdUser() {
		return IdUser;
	}

	public void setIdUser(String idUser) {
		IdUser = idUser;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
//
//	public Date getUpdateDate() {
//		return updateDate;
//	}
//
//	public void setUpdateDate(Date updateDate) {
//		this.updateDate = updateDate;
//	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	
	
}
