package org.yhackday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/v1")
public class UIController {
    @GetMapping("/action/{userId}")
    public String getNextActionInfo(@PathVariable int userId) {
        return "action_ui";
    }
}
