package pl.ardas.restfullapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 123123123L;

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable=false)
    private String userId;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 120)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    private String emailVerificationToken;
    @Column(nullable = false)
    private boolean emailVerificationStatus = false;
}
