package com.assignment.ripple.controller;


import com.assignment.ripple.model.VM;
import com.assignment.ripple.payload.ApiResponse;
import com.assignment.ripple.payload.ModifyVmRequest;
import com.assignment.ripple.payload.VMRequest;
import com.assignment.ripple.repository.VMRepository;
import com.assignment.ripple.security.CurrentUser;
import com.assignment.ripple.security.UserPrincipal;
import com.assignment.ripple.service.VMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/vm")
public class VMController {

    @Autowired
    VMRepository vmRepository;

    @Autowired
    VMService vmService;

    @PostMapping("/provision")
    public ResponseEntity<?> provisionVM(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody VMRequest vmRequest) throws Exception {

        VM vmDetails = new VM("vm-" + currentUser.getUsername() + "-" + Objects.hash(Instant.now()), vmRequest.getOsType(), vmRequest.getRam(),
                vmRequest.getHardDisk(), vmRequest.getCpuCores(), currentUser.getUsername());

        System.out.println("Printing current or logged in user name,," + currentUser.getUsername());

        try {
            VM result = vmService.saveVmDetails(vmDetails);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{vmId}")
                    .buildAndExpand(result.getVmId()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "VM " + result.getVmId() + "provisioned Successfully"));
        } catch (Throwable t) {
            System.out.println("Printing the issue details.." + t.getMessage() + "--" + t.getCause());
            t.printStackTrace();
        }
        return ResponseEntity.created(new URI("www.google.com")).body(new ApiResponse(false, "Failure"));
    }

    @GetMapping("/vmlist/{userName}")
    public List<VM> getCurrentUser(@PathVariable("userName") String userName) {
        System.out.println("Passing user name.." + userName);
        return vmService.getVmDetailsByUserName(userName);
    }

    @PostMapping("/modify/{vmId}")
    public ResponseEntity<?> modifyVm(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody ModifyVmRequest vmRequest, @PathVariable("vmId") String vmId) throws Exception {
        System.out.println("Updating the existing vm details " + vmId);
        if (vmId != null) {
            VM modifyDetails = vmService.getVmDetailByVmId(vmId);
            Optional<ModifyVmRequest> optionalModifyVmRequest = Optional.ofNullable(vmRequest);
            if (optionalModifyVmRequest.isPresent()) {
                int cpuCores = optionalModifyVmRequest.get().getCpuCores();
                String ram = optionalModifyVmRequest.get().getRam();
                String hardDisk = optionalModifyVmRequest.get().getHardDisk();
                String osType = optionalModifyVmRequest.get().getOsType();
                Optional<Integer> optionalCpuCores = Optional.ofNullable(cpuCores);
                Optional<String> optionalRam = Optional.ofNullable(ram);
                Optional<String> optionalHardDisk = Optional.ofNullable(hardDisk);
                Optional<String> optionalOsType = Optional.ofNullable(osType);
                if (optionalCpuCores.isPresent()) {
                    modifyDetails.setCpuCores(vmRequest.getCpuCores());
                }
                if (optionalHardDisk.isPresent()) {
                    modifyDetails.setHardDisk(vmRequest.getHardDisk());
                }
                if (optionalOsType.isPresent()) {
                    modifyDetails.setOsType(vmRequest.getOsType());
                }
                if (optionalRam.isPresent()) {
                    modifyDetails.setRam(vmRequest.getRam());
                }
                System.out.println("Printing the modify details..." + modifyDetails.toString());
            }
            VM result = vmService.modifyVmDetails(modifyDetails);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{vmId}")
                    .buildAndExpand(result.getVmId()).toUri();
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Modied VM" + result.getVmId() + " details Successfully"));

        } else {

            return ResponseEntity.created(new URI("www.google.com"))
                    .body(new ApiResponse(false, "VM ID is missing."));

        }

    }
}
