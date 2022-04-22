package com.hit.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.security.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    @NotBlank
    @Nationalized
    private String fullName;

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //phải để EAGER k thì sẽ lỗi "could not initialize proxy – no Session"
    @JoinTable(name = "account_role", //Tạo ra một join Table tên là "account_role"
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),  // Trong đó, khóa ngoại chính là account_id trỏ tới class hiện tại (Account)
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Role)
    )
    private Set<Role> roles;
}
