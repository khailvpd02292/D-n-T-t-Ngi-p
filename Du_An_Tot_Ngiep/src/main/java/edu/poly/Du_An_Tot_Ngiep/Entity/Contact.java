package edu.poly.Du_An_Tot_Ngiep.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContact;
	@Column(columnDefinition = "nvarchar(50)")
	private String username;
	@Column(columnDefinition = "nvarchar(50)")
	private String email;
	@Column(columnDefinition = "nvarchar(50)")
	private String nameProduct;
	@Column(columnDefinition = "nvarchar(255)")
	private String content;
	public Contact() {
		super();
	}
	public Contact(int idContact, String username, String email, String nameProduct, String content) {
		super();
		this.idContact = idContact;
		this.username = username;
		this.email = email;
		this.nameProduct = nameProduct;
		this.content = content;
	}
	public int getIdContact() {
		return idContact;
	}
	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
