package es.sgie.back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.sgie.back.domain.enums.Kind;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue
    @Column(name = "ID", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column (updatable = false)
    @NotNull
    private Date created = new Date();

    /**
     * PersonalIdentCode: Ident code. Random text for customers and email for others
     */
    @Column(unique = true)
    @NotNull
    private String pic;

    @Column
    @NotNull
    @JsonIgnore
    private String password;

    @Column
    @NotNull
    private Boolean active = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Kind kind;

    /**
     * Authorization role
     */
    @JsonIgnore
    public abstract String getRole();
}
