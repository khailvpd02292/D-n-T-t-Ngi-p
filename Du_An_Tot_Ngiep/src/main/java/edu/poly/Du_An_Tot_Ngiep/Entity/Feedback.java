package edu.poly.Du_An_Tot_Ngiep.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFeedback;
	@NotNull
	@Column(columnDefinition = "nvarchar(50)", updatable = false)
	private String name;
	@NotNull
	@Column(columnDefinition = "varchar(50)", updatable = false)
	private String email;
	@NotNull
	@Column(columnDefinition = "nvarchar(50)")
	private String subjects;
	@NotNull
	@Column(columnDefinition = "nvarchar(255)")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name ="createDate",updatable = false)
	private Date createDate;
	public Feedback() {
		super();
	}
	public Feedback(int idFeedback, @NotNull String name, @NotNull String email, @NotNull String subjects,
			@NotNull String content, Date createDate) {
		super();
		this.idFeedback = idFeedback;
		this.name = name;
		this.email = email;
		this.subjects = subjects;
		this.content = content;
		this.createDate = createDate;
	}
	public int getIdFeedback() {
		return idFeedback;
	}
	public void setIdFeedback(int idFeedback) {
		this.idFeedback = idFeedback;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	 
	
	
	 
}
