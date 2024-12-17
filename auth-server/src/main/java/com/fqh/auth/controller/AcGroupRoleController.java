package com.fqh.auth.controller;

import com.fqh.auth.service.access.AcGroupRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/groupRole")
@RequiredArgsConstructor
public class AcGroupRoleController {

    private final AcGroupRoleService acGroupRoleService;

    @PostMapping(value = "/saveRole")
    public Boolean saveRole() {
        return true;
    }


    @PostMapping(value = "/updateRole")
    public Boolean updateRole() {
        return true;
    }


    @PostMapping(value = "/deleteRole")
    public Boolean deleteRole() {
        return true;
    }


    @PostMapping(value = "/enabledRole")
    public Boolean enabledRole() {
        return true;
    }




}
