package grzybo.evrynote;

import grzybo.evrynote.model.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class EvryNoteApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(EvryNoteApplication.class, args);
        //for (String s : applicationContext.getBeanDefinitionNames()){System.out.println(s);}
        System.out.println("HELLO EVRYNOTE!");

        //TODO foldery
        //TODO w notatce: data utworzenia + data ost. modyfikacji
        //TODO get notes by user
        //TODO poprawic cashowanie - po dodaniu note, dalej nie ma nowgo w GET api/note
    }
}
