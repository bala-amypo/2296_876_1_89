 package com.example.demo.service.impl;

import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.*;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {

    private final CategorizationRuleRepository repo;
    private final CategoryRepository categoryRepo;

    public CategorizationRuleServiceImpl(CategorizationRuleRepository repo,
                                         CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    public CategorizationRule createRule(Long categoryId, CategorizationRule rule) {
        rule.setCategory(categoryRepo.findById(categoryId).orElseThrow());
        return repo.save(rule);
    }

    public List<CategorizationRule> getRulesByCategory(Long categoryId) {
        return repo.findByCategoryIdOrderByPriorityAsc(categoryId);
    }

    public void deleteRule(Long ruleId) {
        repo.deleteById(ruleId);
    }
}
