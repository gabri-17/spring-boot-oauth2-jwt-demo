package com.devsuperior.demo.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name = "tb_user_role", // Tabela de associação entre User e Role.
            joinColumns = @JoinColumn(name = "user_id"), // Chave estrangeira da entidade atual (User).
            inverseJoinColumns = @JoinColumn(name = "role_id")) // Chave estrangeira da outra entidade (Role).
    private Set<Role> roles = new HashSet<>(); // Coleção que não permite duplicatas.


    public User() {
    }

    public User(Long id, String name, String email, String phone, LocalDate birthDate, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    public boolean hasRole(String roleName) {
        //return roles.stream().anyMatch(role -> role.getAuthority().equals(roleName));
        for (Role role : roles) {
            // Lógica para verificar se o usuário possui o perfil (role) especificado.
            if (role.getAuthority().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
