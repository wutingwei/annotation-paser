package com.cesec.smartcity.entity;

import com.cesec.smartcity.annotation.COLUMNTYPE;

public class Column {

    private boolean pk;
    private boolean nullable;
    private String name;
    private String comment;
    private COLUMNTYPE type;
    private int length;
    private int point;
    private String value;
    private boolean autoIncrement;
    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public COLUMNTYPE getType() {
        return type;
    }

    public void setType(COLUMNTYPE type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}
