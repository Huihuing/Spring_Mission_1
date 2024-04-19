package com.example.mission_1.note.notebook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotebookController {

    @PostMapping("/books/write")
    public String write() {

    }
}
