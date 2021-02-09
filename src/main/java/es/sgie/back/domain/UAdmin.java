package es.sgie.back.domain;

import es.sgie.back.security.Role;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@PrimaryKeyJoinColumn(name = "userId")
public class UAdmin extends User {

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
        return Role.ROLE_ADM.name();
    }
}
