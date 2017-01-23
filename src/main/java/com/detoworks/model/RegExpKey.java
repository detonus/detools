package com.detoworks.model;

import java.io.Serializable;

public class RegExpKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private int subid;
	
	public RegExpKey() {
		super();
	}

	public RegExpKey(long id, int subid) {
		this.id = id;
		this.subid = subid;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + subid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RegExpKey))
			return false;
		RegExpKey other = (RegExpKey) obj;
		if (id != other.id)
			return false;
		if (subid != other.subid)
			return false;
		return true;
	}
	
}
