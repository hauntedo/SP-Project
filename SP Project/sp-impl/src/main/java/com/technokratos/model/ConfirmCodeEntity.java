package com.technokratos.model;

import com.technokratos.utils.enums.ConfirmCodeState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Table(name = "confirm_code")
public class ConfirmCodeEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "confirm_code_state")
    private ConfirmCodeState confirmCodeState;

    @Column(name = "confirm_code", nullable = false)
    private UUID confirmCode;

    @OneToOne
    @JoinColumn(name = "account_id")
    private UserEntity user;
}
