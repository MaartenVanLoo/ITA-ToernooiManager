package be.ita.toernooimanager.service.acl;

import be.ita.toernooimanager.repositories.local.acl.PrivilegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeService {
    PrivilegeRepository privilegeRepository;
/*

    public Privilege createPrivilege(Privilege privilege) throws AlreadyExistsException {

        if (privileges.containsKey(privilege.getName())) {
            throw new AlreadyExistsException(String.format("Privilege %s already exists",privilege.getName()));
        }
        privileges.put(privilege.getName(),privilege);
        return privilege;
    }
    public Privilege createPrivilege(String name, String description) throws AlreadyExistsException {
        Privilege privilege = new Privilege(name, description);
        return this.createPrivilege(privilege);
    }

    public Privilege getPrivilegeByName(String name) {
        return privileges.get(name);
    }
*/
}
