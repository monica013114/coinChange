package com.coinchange.controller;

import com.coinchange.model.ChangeResponse;
import com.coinchange.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/change")
public class ChangeController {

    @Autowired
    private ChangeService changeService;

    @GetMapping("/{bill}")
    public ChangeResponse getChange(@PathVariable int bill) {
        return changeService.getChange(bill);
    }
}