package com.inShowAdmin.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`type`")
public class Type implements Serializable {
	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + "]";
	}

	@Id
    private String id;

    private String type;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}