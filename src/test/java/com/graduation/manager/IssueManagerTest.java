package com.graduation.manager;

import com.graduation.domain.Issue;
import com.graduation.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
    Issue issue1 = new Issue(1, true, "Vasily", "Aleksey", null, LocalDateTime.of(2020, 8, 2, 13, 10));
    Issue issue2 = new Issue(2, true, "Alex", "Igor", Set.of("forex"), LocalDateTime.now());
    Issue issue3 = new Issue(3, false, "Denis", "Oksana", Set.of("cfd, crypto"), LocalDateTime.of(2020, 7, 2, 19, 5));
    Issue issue4 = new Issue(4, true, "Natasha", "Kostya", null, LocalDateTime.of(2019, 7, 30, 11, 0));


    @Test
    public void shouldFindById() {
        Issue returned = issue2;
        doReturn(returned).when(repository).findbyId(2);

        assertEquals(issue2, manager.findById(2));
    }


    @Test
    public void shouldRemoveById() {
        List<Issue> expected = List.of(issue1, issue2, issue4);
        doReturn(expected).when(repository).findAll();

        manager.removeById(3);
        assertEquals(expected, manager.findAll());

    }

}