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

    final long ID = 1L;
    TeamController teamController = null;
    TeamRepositoryImpl teamRepository = Mockito.mock(TeamRepositoryImpl.class);

    @Before
    public void setUp() {
        teamController = new TeamController(teamRepository);
    }

    private Team getTeam() {
        return new Team(1L, "test1");
    }

    @Test
    public void testGetAll() {
        List<Team> teams = new ArrayList<>();
        teams.add(getTeam());
        Mockito.when(teamRepository.getAll()).thenReturn(teams);
        Assert.assertEquals(teamController.getAll(), teams);
    }

    @Test
    public void testGetById() {
        teamController.save(getTeam());
        Mockito.when(teamController.getById(ID)).thenReturn(getTeam());
        Assert.assertEquals(teamRepository.getById(ID),getTeam());
    }

}