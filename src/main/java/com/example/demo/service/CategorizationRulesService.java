 package com.example.demo.service;

import com.example.demo.model.CategorizationRuleService;
import java.util.List;

public interface CategorizationRuleService {
    CategorizationRule createRule(Long categoryId, CategorizationRule rule);
    List<CategorizationRule> getRulesByCategory(Long categoryId);
    void deleteRule(Long ruleId);
}
