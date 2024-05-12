package ma.enset.patientsmvc.security.service;

import ma.enset.patientsmvc.security.entities.AppRole;
import ma.enset.patientsmvc.security.entities.AppUser;

public interface SecurityService  {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName ,String description);
    void addRoleToUser(String username , String roleName);
    void removeRoleToUser(String username , String roleName);
    AppUser loadByUserName(String username);


}
