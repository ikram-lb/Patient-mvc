package ma.enset.patientsmvc.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.patientsmvc.security.entities.AppRole;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data @NoArgsConstructor  @AllArgsConstructor
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    private String password ;
    private  boolean active ;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> appRoles = new ArrayList<>();
    

}
