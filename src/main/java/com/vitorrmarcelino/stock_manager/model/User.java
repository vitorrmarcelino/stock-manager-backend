package com.vitorrmarcelino.stock_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="\"user\"")
@Entity(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "user_password", length = 255, nullable = false)
    private String password;

    @Column(name = "user_is_super")
    private Boolean isSuper;

    @OneToOne(mappedBy = "user")
    private Company company;

    @OneToOne(mappedBy = "user")
    private  Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(isSuper){
            return List.of(new SimpleGrantedAuthority("ROLE_SUPER"), new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
