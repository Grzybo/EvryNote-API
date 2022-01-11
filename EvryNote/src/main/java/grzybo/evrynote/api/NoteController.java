package grzybo.evrynote.api;

import grzybo.evrynote.dto.NoteDTO;
import grzybo.evrynote.mapper.NoteDtoMapper;
import grzybo.evrynote.model.Note;
import grzybo.evrynote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("api/note")
@RestController
public class NoteController {

    private final NoteService noteService;
    private final NoteDtoMapper noteDtoMapper;

    @Autowired
    public NoteController(NoteService noteService, NoteDtoMapper noteDtoMapper) {
        this.noteDtoMapper = noteDtoMapper;
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteDTO> getAll(@RequestParam(required = false) Integer page, Sort.Direction sort){
        if(sort == null) sort = Sort.Direction.ASC; //TODO moze byc literówa w sort
        if (page == null || page < 0){page = 0;}
        return NoteDtoMapper.mapToNoteDtos((noteService.getAllPageable(page, sort)));}

    /*
    *
    *  @GetMapping
    public List<NoteDTO> getAll(){
        return NoteDtoMapper.mapToNoteDtos((noteService.getAll()));}
    *
    * */


    @PostMapping
    public Note add(@NonNull @RequestBody Note note){return noteService.addNote(note);}

    @GetMapping(path = "{id}")
    public Optional<Note> getById(@PathVariable("id") Long id){ return noteService.getByID(id);}

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") Long id){noteService.deleteByID(id);}

    @PutMapping(path = "{id}")
    public Note updateById(@PathVariable("id") Long id, @NonNull @RequestBody Note note ){ return noteService.updateByID(id, note);}

}
