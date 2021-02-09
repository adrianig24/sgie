package es.sgie.back.domain;

import es.sgie.back.security.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@PrimaryKeyJoinColumn(name = "userId")
public class UCustomer extends User {

    @Column(unique = true)
    @NotNull
    private String name;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    private String mobile;

    @Override
    public String getRole() {
        return Role.ROLE_CTM.name();
    }
}
