package com.detoworks.model;

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
@IdClass(value=FiddleKey.class)
@Table(name="regexp", schema="detools")
public class Fiddle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Fiddle() {
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

	@Transient
	private String output;

	@Transient
	private String outputTree;

	@Transient
	private String messages;

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

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutputTree() {
		return outputTree;
	}

	public void setOutputTree(String outputTree) {
		this.outputTree = outputTree;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messges) {
		this.messages = messages;
	}
}
