package be.ita.toernooimanager;

import be.ita.toernooimanager.model.local.acl.Privilege;
import be.ita.toernooimanager.service.acl.PrivilegeService;
import be.ita.toernooimanager.service.acl.RoleService;
import be.ita.toernooimanager.service.acl.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class DataLoader {

    PrivilegeService privilegeService;
    RoleService roleService;
    UserService userService;

    @PostConstruct
    public void initData(){
        createPrivileges();
        createRoles();
        createUsers();
    }

    private void createPrivileges() {
        try {
            //Admin user (more control if ever needed)
            privilegeService.createPrivilege("admin_read", "");
            privilegeService.createPrivilege("admin_write", "");
            //Centrale tafel
            privilegeService.createPrivilege("manager_read", "");
            privilegeService.createPrivilege("manager_write", "");
            //Info pc's => only read?
            privilegeService.createPrivilege("info_read", "");
            //Weeg pc's
            privilegeService.createPrivilege("weeg_read","");
            privilegeService.createPrivilege("weeg_write","");
            //Tatami's
            privilegeService.createPrivilege("tatami_read","");
            privilegeService.createPrivilege("tatami_write","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createRoles(){
        try{
            Set<Privilege> infoPrivileges = new HashSet<>();
            infoPrivileges.add(privilegeService.getPrivilegeByName("info_read"));
            roleService.createRole("Info","", infoPrivileges);

            Set<Privilege> weegPrivileges = new HashSet<>();
            weegPrivileges.add(privilegeService.getPrivilegeByName("weeg_read"));
            weegPrivileges.add(privilegeService.getPrivilegeByName("weeg_write"));
            roleService.createRole("Weeg","", weegPrivileges);

            Set<Privilege> tatamiPrivileges = new HashSet<>();
            tatamiPrivileges.add(privilegeService.getPrivilegeByName("tatami_read"));
            tatamiPrivileges.add(privilegeService.getPrivilegeByName("tatami_write"));
            roleService.createRole("Tatami","", tatamiPrivileges);

            Set<Privilege> managerPrivileges = new HashSet<>();
            managerPrivileges.add(privilegeService.getPrivilegeByName("manager_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("manager_write"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("info_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("weeg_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("weeg_write"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("tatami_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("tatami_write"));
            roleService.createRole("Manager","", managerPrivileges);

            Set<Privilege> adminPrivileges = new HashSet<>();
            adminPrivileges.add(privilegeService.getPrivilegeByName("admin_read"));
            adminPrivileges.add(privilegeService.getPrivilegeByName("admin_write"));
            adminPrivileges.addAll(managerPrivileges);
            roleService.createRole("Admin","", adminPrivileges);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createUsers(){
        try{
            userService.createUser("Info","",roleService.getRoleByName("Info"));
            userService.createUser("Weeg","",roleService.getRoleByName("Weeg"));
            userService.createUser("Tatami1","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami2","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami3","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami4","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami5","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami6","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami7","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami8","",roleService.getRoleByName("Tatami"));
            userService.createUser("Manager","",roleService.getRoleByName("Manager"));
            userService.createUser("Admin","",roleService.getRoleByName("Admin"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
