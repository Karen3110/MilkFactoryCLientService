package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Admin;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import ge.alpin.javakhet.milkfactory.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminAccountController {
    @Autowired
    private AdminService adminService;


    @PostMapping("/login")
    ResponseEntity<Admin> login(@RequestBody SignInDto signInDto) throws ResponseException {
        return ResponseEntity.ok(adminService.getByLoginAndPassword(signInDto));
    }
}
