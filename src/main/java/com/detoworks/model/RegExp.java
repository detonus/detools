package com.detoworks.model;

import com.detoworks.dto.RegExpDto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: RegExp
 *
 */
@Entity
@XmlRootElement
@IdClass(value=RegExpKey.class)
@Table(name="regexp", schema="detools")
public class RegExp implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegExp() {
		super();
	}
   
	@Id
	private long id;
	
	@Id
	private int subid;
	
	@Column(name="input",length=4096)
	@NotNull
	private String input;
	
	@Column(name="findexp")
	@NotNull
	private String findexp;
	
	@Column(name="replexp")
	private String replexp;
	
	@Column(name="modify", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modify;

	public RegExp(RegExpDto regExpDto) {
		id = regExpDto.getId();
		subid = regExpDto.getSubid();
		input = regExpDto.getInput();
		findexp = regExpDto.getFindexp();
		replexp = regExpDto.getReplexp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSubid() {
		return subid;
	}

	public void setSubid(int subid) {
		this.subid = subid;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getFindexp() {
		return findexp;
	}

	public void setFindexp(String findexp) {
		this.findexp = findexp;
	}

	public String getReplexp() {
		return replexp;
	}

	public void setReplexp(String replexp) {
		this.replexp = replexp;
	}

	public Date getModify() {
		return modify;
	}

	public void setModify(Date modify) {
		this.modify = modify;
	}

}
