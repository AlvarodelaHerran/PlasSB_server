	package com.example.plassb.dto;
	
	import java.time.LocalDate;
	import java.util.List;
	
	public class AssignResponseDto {
	    private Long dumpsterId;
	    private Long employeeId;
	    private LocalDate date;
	
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
	
	    public LocalDate getDate() {
	        return date;
	    }
	
	    public void setDate(LocalDate date) {
	        this.date = date;
	    }
	
	
	    public static AssignResponseDto map(Long dumpsterId, Long employeeId, LocalDate date) {
	        AssignResponseDto response = new AssignResponseDto();
	        response.setDumpsterId(dumpsterId);
	        response.setEmployeeId(employeeId);
	        response.setDate(date);
	        return response;
	    }
	}
