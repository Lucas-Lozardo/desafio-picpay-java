package lozardo.picpaydesafio.domain.users;

import jakarta.persistence.*;
import lozardo.picpaydesafio.dtos.UserDTO;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private IUserType usertype;
    private BigDecimal balance;


    public User(){
    }

    public User(Long id, String name, String document, String email, String password, IUserType usertype, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.balance = balance;
    }

    public User(UserDTO userDTO) {
        this.id = userDTO.id();
        this.name = userDTO.name();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.usertype = userDTO.usertype();
        this.balance = userDTO.balance();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IUserType getUsertype() {
        return usertype;
    }

    public void setUsertype(IUserType usertype) {
        this.usertype = usertype;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(document, user.document) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && usertype == user.usertype && Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, document, email, password, usertype, balance);
    }
}
