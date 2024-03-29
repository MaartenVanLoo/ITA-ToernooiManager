package be.ita.toernooimanager.service.local.acl;

import be.ita.toernooimanager.model.local.acl.Privilege;
import be.ita.toernooimanager.repositories.local.acl.PrivilegeRepository;
import be.ita.toernooimanager.service.Exceptions.AlreadyExistsException;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PrivilegeService {
    PrivilegeRepository privilegeRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Privilege createPrivilege(Privilege privilege) throws AlreadyExistsException {
        if (privilegeRepository.existsPrivilegeByName(privilege.getName())){
            throw new AlreadyExistsException(String.format("Privilege %s already exists", privilege.getName()));
        }
        privilege = privilegeRepository.save(privilege);
        return privilege;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Privilege createPrivilege(String name, String description) throws AlreadyExistsException {
        Privilege privilege = new Privilege(name, description);
        return this.createPrivilege(privilege);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Privilege getPrivilegeByName(String name) {
        return privilegeRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Privilege %s doesn't exist", name)));
    }

}
