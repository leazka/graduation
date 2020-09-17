package com.graduation.manager;

import com.graduation.domain.Issue;
import com.graduation.repository.IssueRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class IssueManager {
    private IssueRepository repository;

    public void add(Issue item) {
        repository.save(item);
    }


    public List<Issue> findAll() {
        return repository.findAll();
    }

    public Issue findById(int id) {
        return repository.findById(id);
    }

    public void removeAll() {
        repository.removeAll();
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public List<Issue> searchBy(Predicate<Issue> filter) {
        return repository.findAll().stream()
                .filter(filter)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Issue> searchByAuthor(String author) {
        return searchBy(issue -> issue.getAuthor().equalsIgnoreCase(author));
    }

    public List<Issue> searchByAssignee(String assignee) {
        return searchBy(issue -> issue.getAssignee().equalsIgnoreCase(assignee));
    }

    public List<Issue> searchByLabel(String label) {
        return searchBy(issue -> Objects.nonNull(issue.getLabels()) && issue.getLabels().contains(label.toLowerCase()));
    }


    public void closeIssue(int issueId) {
        Issue issue = repository.findById(issueId);
        if (issue.isOpen()) {
            issue.setOpen(false);
        }
    }
}
