package com.assignment.ripple.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VMRequest {

    @NotBlank
    @Size(max = 40)
    private String osType;

    @NotBlank
    @Size(max = 40)
    @Max(message = "Max 500GB is allowed",value = 500)
    private String ram;

    @NotBlank
    @Size(max = 40)
    @Max(message = "Max 1024GB is allowed",value = 1024)
    private String hardDisk;

    @NotNull
    @Max(message = "Max 48 cores are allowed",value = 48)
    private int cpuCores;

    public VMRequest(String osType,String ram, String hardDisk,int cpuCores){
        this.osType = osType;
        this.ram = ram;
        this.hardDisk = hardDisk;
        this.cpuCores = cpuCores;
    }

    @Override
    public String toString() {
        return "VMRequest{" +
                "osType='" + osType + '\'' +
                ", ram='" + ram + '\'' +
                ", hardDisk='" + hardDisk + '\'' +
                ", cpuCores=" + cpuCores +
                '}';
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }
}
