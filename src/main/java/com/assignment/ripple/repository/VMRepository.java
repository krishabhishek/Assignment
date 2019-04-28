package com.assignment.ripple.repository;

import com.assignment.ripple.model.VM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VMRepository extends JpaRepository<VM, Long> {
    List<VM> findByUserWhoCreatedVm(String user_who_created_vm);
    VM findByVmId(String vmId);
}
