package com.assignment.ripple.service;

import com.assignment.ripple.model.VM;
import com.assignment.ripple.payload.VMRequest;
import com.assignment.ripple.repository.VMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VMService {

    @Autowired
    VMRepository vmRepository;

    public List<VM> getVmDetailsByUserName(String userName){
        return vmRepository.findByUserWhoCreatedVm(userName);
    }

    public VM saveVmDetails(VM vmDetails){
        return vmRepository.save(vmDetails);
    }

    public VM modifyVmDetails(VM modifyVMDetails){
        return vmRepository.save(modifyVMDetails);
    }

    public VM getVmDetailByVmId(String vmId){
        return  vmRepository.findByVmId(vmId);
    }


}
