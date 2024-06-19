package com.bookhive.model.entities;

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
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private String avatar;
    @Column(columnDefinition = "boolean default false")
    private boolean enabled;
    @ManyToOne
    private UserRoleEntity role;


    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (getFirstName() != null) {
            fullName.append(getFirstName());
        }
        if (getLastName() != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }
            fullName.append(getLastName());
        }

        return fullName.toString();
    }

}
