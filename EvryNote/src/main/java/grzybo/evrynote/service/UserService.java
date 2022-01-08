package grzybo.evrynote.service;

import grzybo.evrynote.model.UserModel;
import grzybo.evrynote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserModel> getAll(){return userRepo.findAll();}

    public void add(UserModel user) {
        userRepo.save(user);
    }

    public Optional<UserModel> getByID(Long id){return userRepo.findById(id);}
    public void deleteByID(Long id){userRepo.deleteById(id);}
    public void updateByID(Long id, UserModel user){userRepo.save(new UserModel(id, user));}
}
