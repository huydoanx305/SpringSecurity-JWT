package com.hit.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hit.security.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@JsonIgnoreProperties(value = {
        "status",
        "createdAt",
        "updatedAt"
})
public class Role extends BaseEntity {

    private String roleName;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "roles")
    @JsonIgnore
    private Set<Account> accounts;

}
