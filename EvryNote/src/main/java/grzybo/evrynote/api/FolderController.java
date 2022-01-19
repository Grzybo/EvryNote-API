package grzybo.evrynote.api;

import grzybo.evrynote.dto.FolderDTO;
import grzybo.evrynote.mapper.FolderDtoMapper;
import grzybo.evrynote.model.Folder;
import grzybo.evrynote.model.UserModel;
import grzybo.evrynote.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/folder")
@RestController
public class FolderController {

    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping
    public List<FolderDTO> getAll(){
        return FolderDtoMapper.mapToFolderDtos(folderService.getAll());
    }

    @PostMapping
    public void add(@NonNull @RequestBody Folder folder){
        folderService.add(folder);
    }

    @GetMapping(path = "{id}")
    public FolderDTO getById(@PathVariable("id") Long id){
        return FolderDtoMapper.mapToFolderDto(folderService.getByID(id).get());
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") Long id){
        folderService.deleteByID(id);}

    @PutMapping(path = "{id}")
    public void updateById(@PathVariable("id") Long id, @NonNull @RequestBody Folder folder ){
        folderService.updateByID(id, folder);
    }


    /*
     */





}
