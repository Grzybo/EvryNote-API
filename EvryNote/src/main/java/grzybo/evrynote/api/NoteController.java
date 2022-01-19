package grzybo.evrynote.api;

import grzybo.evrynote.dto.NoteDTO;
import grzybo.evrynote.mapper.NoteDtoMapper;
import grzybo.evrynote.model.Note;
import grzybo.evrynote.model.UserModel;
import grzybo.evrynote.service.FolderService;
import grzybo.evrynote.service.NoteService;
import grzybo.evrynote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("api/note")
@RestController
public class NoteController {

    private final NoteService noteService;
    private final NoteDtoMapper noteDtoMapper;
    private final UserService userService;
    private final FolderService folderService;

    @Autowired
    public NoteController(NoteService noteService, NoteDtoMapper noteDtoMapper, UserService userService, FolderService folderService) {
        this.noteDtoMapper = noteDtoMapper;
        this.noteService = noteService;
        this.userService = userService;
        this.folderService = folderService;
    }

    @GetMapping
    public List<NoteDTO> getAll(@RequestParam(required = false) Integer page, Sort.Direction sort){
        if(sort == null) sort = Sort.Direction.ASC; //TODO moze byc literówa w sort
        if (page == null || page < 0){page = 0;}
        return NoteDtoMapper.mapToNoteDtos((noteService.getAllPageable(page, sort)));}

     @GetMapping(path = "author/{authorID}")
     public List<NoteDTO> getAllByAuthor(@RequestParam(required = false) Integer page, Sort.Direction sort, @PathVariable("authorID") Long authorId){
     if(sort == null) sort = Sort.Direction.ASC; //TODO moze byc literówa w sort
     if (page == null || page < 0){page = 0;}
     return NoteDtoMapper.mapToNoteDtos((noteService.getAllPageableByAuthor(page, sort, authorId)));}

    @GetMapping(path = "folder/{folderID}")
    public List<NoteDTO> getAllInFolder( @PathVariable("folderID") Long folderID)
    {
        return NoteDtoMapper.mapToNoteDtos((noteService.getAllInFolder(folderID)));
    }


    /*
    *
    *  @GetMapping
    public List<NoteDTO> getAll(){
        return NoteDtoMapper.mapToNoteDtos((noteService.getAll()));}
    *
    * */

    @PostMapping
    public NoteDTO add(@NonNull @RequestBody NoteDTO noteDTO){

        System.out.println(noteDTO.toString());
        List<UserModel> sharedTo = new ArrayList<UserModel>();
        for (Long userId: noteDTO.getUsersIdSharedTo()){sharedTo.add(userService.getByID(userId).get());}

        Note note = Note.builder()
                .id(noteDTO.getId())
                .title(noteDTO.getTitle())
                .body(noteDTO.getBody())
                .created(noteDTO.getCreated())
                .modified(noteDTO.getModified())
                .author(userService.getByID(noteDTO.getAuthorID()).get())
                .folder(folderService.getByID(noteDTO.getFolderID()).get())
                .usersSharedTo(sharedTo)
                .build();

       System.out.println( note.toString());

        return NoteDtoMapper.mapToNoteDto(noteService.addNote(note));
    }

    @GetMapping(path = "{id}")
    public NoteDTO getById(@PathVariable("id") Long id){
        return NoteDtoMapper.mapToNoteDto(noteService.getByID(id).get());
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") Long id){noteService.deleteByID(id);}

    @PutMapping(path = "{id}")
    public Note updateById(@PathVariable("id") Long id, @NonNull @RequestBody Note note ){ return noteService.updateByID(id, note);}

}
