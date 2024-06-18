package com.bookhive.model.entities;

import com.bookhive.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name")
    private Role role;

}
