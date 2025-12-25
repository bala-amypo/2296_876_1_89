package com.example.demo.service.impl;

import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {

    @Autowired
    private CategorizationRuleRepository repository;

    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public CategorizationRule getRuleById(Long id) {
        Optional<CategorizationRule> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public CategorizationRule updateRule(Long id, CategorizationRule rule) {
        CategorizationRule existing = getRuleById(id);
        if (existing != null) {
            existing.setName(rule.getName());
            existing.setKeyword(rule.getKeyword());
            existing.setCategory(rule.getCategory());
            return repository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteRule(Long id) {
        repository.deleteById(id);
    }
}
