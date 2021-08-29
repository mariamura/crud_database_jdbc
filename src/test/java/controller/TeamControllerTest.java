package controller;

import model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.jdbcImpl.TeamRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class TeamControllerTest {

    Long ID = 1L;
    Team team1 = new Team(1L, "test1");
    TeamController teamController = null;
    TeamRepositoryImpl teamRepository = Mockito.mock(TeamRepositoryImpl.class);

    @Before
    public void setUp() {
        teamController = new TeamController(teamRepository);
    }

    @Test
    public void testGetAll() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        Mockito.when(teamRepository.getAll()).thenReturn(teams);
        Assert.assertEquals(teamController.getAll(), teams);
    }

    @Test
    public void testGetById() {
        teamController.save(team1);
        Mockito.when(teamController.getById(ID)).thenReturn(team1);
        Assert.assertEquals(teamRepository.getById(ID),team1);
    }

}