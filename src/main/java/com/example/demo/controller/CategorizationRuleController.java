package com.example.demo.controller;

import com.example.demo.entity.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class CategorizationRuleController {

    private final CategorizationRuleService service;

    public CategorizationRuleController(CategorizationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public CategorizationRule createRule(@RequestBody CategorizationRule rule) {
        return service.createRule(rule);
    }

    @GetMapping("/{id}")
    public CategorizationRule getRule(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping
    public List<CategorizationRule> getAllRules() {
        return service.getAllRules();
    }
}
