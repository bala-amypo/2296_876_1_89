package com.example.demo.service.impl;

import com.example.demo.entity.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationRuleServiceImpl
        implements CategorizationRuleService {

    private final CategorizationRuleRepository repository;

    public CategorizationRuleServiceImpl(CategorizationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        return repository.save(rule);
    }

    @Override
    public CategorizationRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return repository.findAll();
    }
}
