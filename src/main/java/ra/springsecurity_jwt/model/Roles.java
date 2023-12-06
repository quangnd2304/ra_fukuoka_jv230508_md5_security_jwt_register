package ra.springsecurity_jwt.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
@Data
public class Roles {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private ERoles name;
}
