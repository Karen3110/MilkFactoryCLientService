package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.model.Admin;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import ge.alpin.javakhet.milkfactory.repository.AdminRepository;
import ge.alpin.javakhet.milkfactory.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;


    @Override
    public Admin getByUserNameAndPassword(SignInDto signInDto) {
        return adminRepository.getByUsernameAndPassword(signInDto.getLogin(), signInDto.getPassword());
    }
}
