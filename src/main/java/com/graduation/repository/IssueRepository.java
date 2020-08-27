package com.graduation.repository;

import com.graduation.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue item) {
        issues.add(item);

    }

    public void saveAll(Collection<Issue> items) {
        this.issues.addAll(items);

    }

    public void removeById(int id) {
        issues.removeIf((i) -> i.getId() == id);
    }

    public Issue findById(int id) {
        Issue issue = null;
        for (Issue i : issues) {
            if (i.getId() == id) {
                issue = i;
            }
        }
        return issue;
    }

    public List<Issue> findAll() {
        return issues;
    }

    public void removeAll() {
        issues.clear();
    }

}
