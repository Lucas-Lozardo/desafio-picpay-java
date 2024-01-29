package lozardo.picpaydesafio.dtos;

import lozardo.picpaydesafio.domain.users.IUserType;

import java.math.BigDecimal;

public record UserDTO(
        Long id,
        String name,
        String document,
        String email,
        String password,
        IUserType usertype,
        BigDecimal balance
) {
}
