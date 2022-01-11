package grzybo.evrynote.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import grzybo.evrynote.model.Note;
import grzybo.evrynote.model.UserModel;
import grzybo.evrynote.repository.NoteRepository;
import grzybo.evrynote.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    void shouldGetSingleNote() throws Exception {

        LocalDateTime timeStamp = LocalDateTime.now();
        UserModel user = userRepository.findById((long)1).get();

        Note testNote = new Note("TEST TITLE", "TEST BODY", timeStamp, timeStamp, user);
        noteRepository.save(testNote);

        MvcResult mvcResultGet =  mockMvc.perform(MockMvcRequestBuilders.get("/api/note/" + testNote.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        Note note = objectMapper.readValue(mvcResultGet.getResponse().getContentAsString(), Note.class);
        assertThat(note).isNotNull();
        assertThat(note.getId()).isEqualTo(testNote.getId());
        assertThat(note.getTitle()).isEqualTo(testNote.getTitle());
        assertThat(note.getBody()).isEqualTo(testNote.getBody());
        assertThat(note.getAuthor().getUsername()).isEqualTo(user.getUsername());

        noteRepository.deleteById(testNote.getId());
        System.out.println("TERAZ USUWAM");

        MvcResult mvcResultDelete =  mockMvc.perform(MockMvcRequestBuilders.get("/api/note/" + testNote.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        MvcResult mvcResultGetUser =  mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + user.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }
}