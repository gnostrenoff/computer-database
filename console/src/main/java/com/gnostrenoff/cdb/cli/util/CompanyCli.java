package com.gnostrenoff.cdb.cli.util;

import java.io.Serializable;

/**
 * The Class ComputerDto.
 */
public class CompanyCli implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6390443097239367616L;

    /**
     * The id.
     */
    private String id;

    /**
     * The name.
     */
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company : " +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
