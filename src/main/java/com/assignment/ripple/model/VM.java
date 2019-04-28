package com.assignment.ripple.model;

import com.assignment.ripple.model.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vm", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "osType",
                "ram",
                "hardDisk",
                "cpuCores"
        })
})
public class VM extends UserDateAudit {
    @Id
    private String vmId;

    @NotBlank
    private String osType;

    @NotBlank
    private String ram;

    @NotBlank
    private String hardDisk;

    @NotNull
    private int cpuCores;

    @NotBlank
    private String userWhoCreatedVm;

    public VM(){}

    public VM(String vmId,String osType,String ram, String hardDisk,int cpuCores,String currentUser){
        this.vmId = vmId;
        this.osType = osType;
        this.ram = ram;
        this.hardDisk = hardDisk;
        this.cpuCores = cpuCores;
        this.userWhoCreatedVm = currentUser;
    }

    public String getUserWhoCreatedVm() {
        return userWhoCreatedVm;
    }

    public void setUserWhoCreatedVm(String userWhoCreatedVm) {
        this.userWhoCreatedVm = userWhoCreatedVm;
    }

    public String getVmId() {
        return vmId;
    }

    public void setVmId(String vmId) {
        this.vmId = vmId;
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

    public void setCpuCores(int cpuCore) {
        this.cpuCores = cpuCore;
    }
}
