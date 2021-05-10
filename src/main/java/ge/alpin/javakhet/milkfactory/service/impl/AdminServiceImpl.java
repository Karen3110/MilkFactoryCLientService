package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Admin;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import ge.alpin.javakhet.milkfactory.repository.AdminRepository;
import ge.alpin.javakhet.milkfactory.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    @Override
    public Admin getByLoginAndPassword(SignInDto signInDto) throws ResponseException {
        return adminRepository
                .getByLoginAndPassword(signInDto.getLogin(), signInDto.getPassword())
                .orElseThrow(() -> new ResponseException(HttpStatus.UNAUTHORIZED, "Wrong login or password for admin-account to SignIn"));
    }

}
