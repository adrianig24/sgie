package es.sgie.back.domain;

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
public class Note {

    @Id
    @GeneratedValue
    @Column(name = "ID", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String text;



}
