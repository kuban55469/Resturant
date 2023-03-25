package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.enums.Role;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private long dateOfBrith;
    @Email
    private String email;
    @Size(min = 4, message = "Password simbols greater 4")
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer experience;

    private Boolean acceptOrDelete;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH}, fetch = EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "user", cascade = {PERSIST, MERGE, REFRESH, DETACH}, fetch = EAGER)
    private List<Cheque> cheques;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
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

    public void addCheque(Cheque cheque) {
        if (cheques == null) {
            cheques = new ArrayList<>();
        }
        cheques.add(cheque);
    }
}