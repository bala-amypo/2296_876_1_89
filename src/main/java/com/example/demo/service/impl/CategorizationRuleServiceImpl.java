package com.example.demo.service.impl;

import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {

    private final CategorizationRuleRepository ruleRepository;

    public CategorizationRuleServiceImpl(CategorizationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public CategorizationRule getRuleById(Long id) {
        Optional<CategorizationRule> optionalRule = ruleRepository.findById(id);
        return optionalRule.orElse(null);
    }

    @Override
    public CategorizationRule updateRule(Long id, CategorizationRule updatedRule) {
        Optional<CategorizationRule> optionalRule = ruleRepository.findById(id);
        if (optionalRule.isPresent()) {
            CategorizationRule existingRule = optionalRule.get();
            // Use correct getters/setters
            existingRule.setKeyword(updatedRule.getKeyword());
            existingRule.setCategoryName(updatedRule.getCategoryName());
            return ruleRepository.save(existingRule);
        }
        return null;
    }

    @Override
    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }

    // Example method where you might have used getName() or getCategory()
    public void printRules() {
        List<CategorizationRule> rules = ruleRepository.findAll();
        for (CategorizationRule rule : rules) {
            System.out.println("Keyword: " + rule.getKeyword());
            System.out.println("Category Name: " + rule.getCategoryName());
        }
    }
}
