package com.detoworks.model;

import java.io.Serializable;

public class FiddleKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private int subid;
	
	public FiddleKey() {
		super();
	}

	public FiddleKey(long id, int subid) {
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
		if (!(obj instanceof FiddleKey))
			return false;
		FiddleKey other = (FiddleKey) obj;
		if (id != other.id)
			return false;
		if (subid != other.subid)
			return false;
		return true;
	}
	
}
