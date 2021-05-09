package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Admin;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;

public interface AdminService {


    Admin getByLoginAndPassword(SignInDto signInDto) throws ResponseException;
}
