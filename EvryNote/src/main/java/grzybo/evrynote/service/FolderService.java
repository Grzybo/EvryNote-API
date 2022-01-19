package grzybo.evrynote.service;

import grzybo.evrynote.model.Folder;
import grzybo.evrynote.model.UserModel;
import grzybo.evrynote.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<Folder> getAll(){return folderRepository.findAll();}
    public void add(Folder folder) {
        folderRepository.save(folder);
    }
    public Optional<Folder> getByID(Long id){return folderRepository.findById(id);}
    public void deleteByID(Long id){folderRepository.deleteById(id);}
    public void updateByID(Long id, Folder folder){folderRepository.save(new Folder(id, folder));}
}
