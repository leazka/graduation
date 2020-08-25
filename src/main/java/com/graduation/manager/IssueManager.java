package com.graduation.manager;

import com.graduation.domain.Issue;
import com.graduation.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;

public class IssueManager {
    private IssueRepository repository;

    public void add(Issue item) {
        repository.save(item);
    }


    public List<Issue> findAll() {
        return repository.findAll();
    }

    public Issue findById(int id) {
        return repository.findbyId(id);
    }

    public void removeAll() {
        repository.removeAll();
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public List<Issue> searchBy(String request) {
        List<Issue> issues = repository.findAll();
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.matches(request)) {
                result.add(issue);
            }
        }
        return result;
    }
}
