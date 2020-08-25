package com.graduation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private boolean open;
    private String author;
    private String assignee;
    private Set<String> labels;
    private LocalDateTime openedAt;


    @Override
    public int compareTo(Issue o) {
        return this.openedAt.compareTo(o.openedAt);
    }

    public boolean matches(String request) {
        boolean result = false;
        if (this.author.equalsIgnoreCase(request)) {
            return true;
        }
        if (this.assignee.equalsIgnoreCase(request)) {
            return true;
        }
        if (this.labels.contains(request)) {
            return true;
        }
        return result;
    }
}
