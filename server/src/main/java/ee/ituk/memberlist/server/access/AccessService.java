package ee.ituk.memberlist.server.access;

import org.springframework.stereotype.Service;


@Service
public class AccessService {
    private AccessRepository accessRepository;

    public AccessService(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public Access addAccess(Access access) {
        return accessRepository.save(access);
    }

    public Access getAccessById(long id) {
        return accessRepository.findOne(id);
    }

    public Iterable<Access> getAllAccesses() {
        return accessRepository.findAll();
    }

    public void deleteAccess(long id) {
        accessRepository.delete(id);
    }

    public Access saveAccess(Access access) {
        return accessRepository.save(access);
    }

}
