package com.example.plassb.entity;

import java.io.Serializable;
import java.util.Objects;

public class AssignmentRecordId implements Serializable {
    private Long dumpsterId;
    private String plantName;

    public AssignmentRecordId() {}
    public AssignmentRecordId(Long dumpsterId, String plantName) {
        this.dumpsterId = dumpsterId;
        this.plantName = plantName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof AssignmentRecordId)) return false;
        AssignmentRecordId that = (AssignmentRecordId) o;
        return Objects.equals(dumpsterId, that.dumpsterId) &&
               Objects.equals(plantName, that.plantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dumpsterId, plantName);
    }
}
