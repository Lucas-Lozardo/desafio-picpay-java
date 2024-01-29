package lozardo.picpaydesafio.services;

import lozardo.picpaydesafio.domain.users.IUserType;
import lozardo.picpaydesafio.domain.users.User;
import lozardo.picpaydesafio.dtos.UserDTO;
import lozardo.picpaydesafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction (User sender, BigDecimal amount) throws Exception {
        if(sender.getUsertype() == IUserType.MERCHANT){
            throw new Exception("Lojista não pode efetuar transações");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente!");
        }
    }

    public User findUserById(Long id) throws Exception{
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
