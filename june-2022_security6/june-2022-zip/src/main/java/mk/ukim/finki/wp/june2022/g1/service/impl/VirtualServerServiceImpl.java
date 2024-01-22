package mk.ukim.finki.wp.june2022.g1.service.impl;

import mk.ukim.finki.wp.june2022.g1.model.OSType;
import mk.ukim.finki.wp.june2022.g1.model.User;
import mk.ukim.finki.wp.june2022.g1.model.VirtualServer;
import mk.ukim.finki.wp.june2022.g1.model.exceptions.InvalidVirtualMachineIdException;
import mk.ukim.finki.wp.june2022.g1.repository.UserRepository;
import mk.ukim.finki.wp.june2022.g1.repository.VirtualServerRepository;
import mk.ukim.finki.wp.june2022.g1.service.UserService;
import mk.ukim.finki.wp.june2022.g1.service.VirtualServerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirtualServerServiceImpl implements VirtualServerService {

    private final VirtualServerRepository virtualServerRepository;
    private final UserService userService;

    public VirtualServerServiceImpl(VirtualServerRepository virtualServerRepository, UserService userService) {
        this.virtualServerRepository = virtualServerRepository;
        this.userService = userService;
    }

    @Override
    public List<VirtualServer> listAll() {
        return this.virtualServerRepository.findAll();
    }

    @Override
    public VirtualServer findById(Long id) {
        return this.virtualServerRepository.findById(id).orElseThrow(InvalidVirtualMachineIdException::new);
    }

    @Override
    public VirtualServer create(String name, String ipAddress, OSType osType, List<Long> owners, LocalDate launchDate) {
        List<User> users = owners.stream().map(userService::findById).collect(Collectors.toList());
        return this.virtualServerRepository.save(new VirtualServer(name, ipAddress, osType, users, launchDate));
    }

    @Override
    public VirtualServer update(Long id, String name, String ipAddress, OSType osType, List<Long> owners) {
        List<User> users = owners.stream().map(userService::findById).collect(Collectors.toList());
        VirtualServer vs = this.findById(id);

        vs.setInstanceName(name);
        vs.setIpAddress(ipAddress);
        vs.setOSType(osType);
        vs.setOwners(users);
        return this.virtualServerRepository.save(vs);
    }

    @Override
    public VirtualServer delete(Long id) {
        VirtualServer vs = this.findById(id);
        this.virtualServerRepository.delete(vs);
        return vs;
    }

    @Override
    public VirtualServer markTerminated(Long id) {
        VirtualServer vs = this.findById(id);
        vs.setTerminated(true);
        return this.virtualServerRepository.save(vs);
    }

    @Override
    public List<VirtualServer> filter(Long ownerId, Integer activeMoreThanDays) {

        if(ownerId != null && activeMoreThanDays != null) {
            LocalDate date = LocalDate.now().minusDays((long) activeMoreThanDays);
            User user = userService.findById(ownerId);
            return this.virtualServerRepository.findVirtualServersByOwnersAndLaunchDateBefore(user, date);
        }

        if(ownerId != null) {
            User user = userService.findById(ownerId);
            return this.virtualServerRepository.findVirtualServersByOwners(user);
        }

        if(activeMoreThanDays != null) {
            LocalDate date = LocalDate.now().minusDays((long) activeMoreThanDays);
            return this.virtualServerRepository.findVirtualServersByLaunchDateBefore(date);
        }

        return this.virtualServerRepository.findAll();
    }
}
