package com.example.mission_1.note;

import com.example.mission_1.note.note.Note;
import com.example.mission_1.note.note.NoteRepository;
import com.example.mission_1.note.note.NoteService;
import com.example.mission_1.note.notebook.Notebook;
import com.example.mission_1.note.notebook.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NotebookRepository notebookRepository;
    private final NoteRepository noteRepository;
    private final NoteService noteService;

    @RequestMapping("/")
    public String main(Model model) {
        List<Notebook> notebookList = notebookRepository.findAll();
        if (notebookList.isEmpty()) {
            Notebook notebook = new Notebook();
            notebook.setName("새 노트");
            notebookRepository.save(notebook);

            return "redirect:/";
        }
        Notebook targetNotebook = notebookList.get(0);
        List<Note> noteList = noteRepository.findByNotebook(targetNotebook);


//      List<Note> noteList = noteRepository.findAll();
        if (noteList.isEmpty()) {
            noteService.saveDefault(targetNotebook);
            return "redirect:/";
        }

        model.addAttribute("noteList", noteList);
        model.addAttribute("targetNoteList", noteList.get(0));
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetNotebook", targetNotebook);

        return "mainForm";
    }
}
