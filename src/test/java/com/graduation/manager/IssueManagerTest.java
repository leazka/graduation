package com.graduation.manager;

import com.graduation.domain.Issue;
import com.graduation.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class IssueManagerTest {
    @Mock
    IssueRepository repository;
    @InjectMocks
    IssueManager manager;
    Issue issue1 = new Issue(1, true, "Vasily", "Aleksey", "test", "test", null, LocalDateTime.of(2020, 8, 2, 13, 10));
    Issue issue2 = new Issue(2, true, "Alex", "Igor", "test", "test", Set.of("forex"), LocalDateTime.now());
    Issue issue3 = new Issue(3, false, "Denis", "Oksana", "test", "test", Set.of("cfd", "crypto"), LocalDateTime.of(2020, 7, 2, 19, 5));
    Issue issue4 = new Issue(4, true, "Natasha", "Kostya", "test", "test", null, LocalDateTime.of(2019, 7, 30, 11, 0));
    Issue issue5 = new Issue(5, true, "Natasha", "Kostya", "test", "test", Set.of("cfd"), LocalDateTime.now());


    @Test
    public void shouldFindById() {
        Issue expected = issue2;
        doReturn(expected).when(repository).findById(2);
        assertEquals(issue2, manager.findById(2));
    }


    @Test
    public void shouldRemoveById() {
        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);
        manager.add(issue3);
        manager.add(issue1);

        manager.removeById(3);
        assertEquals(List.of(issue1), manager.findAll());

    }

    @Test
    public void shouldFindByAuthor() {
        List<Issue> expected = List.of(issue4);
        doReturn(List.of(issue1, issue2, issue3, issue4)).when(repository).findAll();
        List<Issue> actual = manager.searchByAuthor("Natasha");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByLabel() {
        List<Issue> expected = List.of(issue5, issue3);
        doReturn(List.of(issue1, issue2, issue3, issue4, issue5)).when(repository).findAll();
        List<Issue> actual = manager.searchByLabel("cfd");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByAssignee() {
        List<Issue> expected = List.of(issue2);
        doReturn(List.of(issue1, issue2, issue3, issue4, issue5)).when(repository).findAll();
        List<Issue> actual = manager.searchByAssignee("Igor");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCloseOpenedIssue() {
        Issue issue = issue1;
        doReturn(issue).when(repository).findById(1);
        manager.closeIssue(issue.getId());
        assertFalse(issue.isOpen());
    }

    @Test
    public void shouldAdd() {
        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);
        manager.add(issue5);
        assertEquals(List.of(issue5), manager.findAll());
    }

    @Test
    public void shouldRemoveAll() {
        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);
        manager.add(issue4);
        manager.add(issue3);
        manager.removeAll();
        assertEquals(new ArrayList<>(), manager.findAll());
    }


}