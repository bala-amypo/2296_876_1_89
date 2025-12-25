package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class CategorizationRuleController {

    @Autowired
    private CategorizationRuleService ruleService;

    @PostMapping
    public CategorizationRule createRule(@RequestBody CategorizationRule rule) {
        return ruleService.createRule(rule);
    }

    @GetMapping
    public List<CategorizationRule> getAllRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/{id}")
    public CategorizationRule getRuleById(@PathVariable Long id) {
        return ruleService.getRuleById(id);
    }

    @PutMapping("/{id}")
    public CategorizationRule updateRule(@PathVariable Long id, @RequestBody CategorizationRule rule) {
        return ruleService.updateRule(id, rule);
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
    }
}
