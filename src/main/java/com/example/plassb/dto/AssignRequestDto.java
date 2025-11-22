package com.example.plassb.dto;

public class AssignRequestDto {

    private Long dumpsterId;
    private Long employeeId;
    private int filling;

    public AssignRequestDto() {}

    public Long getDumpsterId() {
        return dumpsterId;
    }

    public void setDumpsterId(Long dumpsterId) {
        this.dumpsterId = dumpsterId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public int getFilling() {
        return filling;
    }

    public void setFilling(int filling) {
        this.filling = filling;
    }
}
