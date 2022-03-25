package com.transit.ticketing.controller;

import com.transit.ticketing.dto.StationResponseDto;
import com.transit.ticketing.entity.Staff;
import com.transit.ticketing.repository.StaffRepository;
import com.transit.ticketing.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffController {
    private static final Logger LOG = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    @PostMapping(value = "/api/v1/secure/staff")
    public ResponseEntity<Object> createStaff(@RequestBody Staff staff) {
        if (staff == null) {
            return ResponseEntity.badRequest().body("Please send the staff details to save");
        }
        return ResponseEntity.ok(staffService.createStaff(staff));
    }

    @GetMapping(value="/api/v1/secure/staffs")
    public ResponseEntity<List<Staff>> listAllStaff() {
        return ResponseEntity.ok().body(staffService.listAllStaff());
    }

    @GetMapping(value="/api/v1/secure/staffs/page={page}&records={records}")
    public ResponseEntity<Page<Staff>> listAllStaffWithPagination(@PathVariable int page, @PathVariable int records) {
        return ResponseEntity.ok().body(staffService.listAllStaffWithPagination(page, records));
    }

    @GetMapping(value="/api/v1/secure/staff/role={role}")
    public ResponseEntity<List<Staff>> filterStaffBasedOnRole(@PathVariable String role) {
        return ResponseEntity.ok().body(staffService.filterStaffsByRole(role));
    }

}
