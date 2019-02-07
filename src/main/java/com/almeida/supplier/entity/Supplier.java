package com.almeida.supplier.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.net.URI;

@Entity
@Table(name = "supplier")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = Supplier.FIND_ALL, query = "select g from Supplier g")
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "findAll";
    
    public Supplier() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
  
    @NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String comment;
	
	@NotNull
	private String CNPJ;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

    @Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", email=" + email + ", comment=" + comment + ", CNPJ=" + CNPJ
				+ "]";
	}

	public JsonObject toJson(URI self) {
        return Json.createObjectBuilder()
                .add("id", this.id)
        		.add("name", this.name)
                .add("email", this.email)
                .add("CNPJ", this.CNPJ)
                .add("comment", this.comment)
                .build();
    }
}
