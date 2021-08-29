package controller;

import model.Developer;
import model.Skill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.jdbcImpl.DeveloperRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class DeveloperControllerTest {

    Long ID = 1L;
    Developer developer = new Developer(1L, "first1", "last1");
    DeveloperController developerController = null;
    DeveloperRepositoryImpl developerRepository = Mockito.mock(DeveloperRepositoryImpl.class);

    @Before
    public void setUp() {
        developerController = new DeveloperController(developerRepository);
    }

    @Test
    public void testGetAll() {
        List<Developer> developers = new ArrayList<>();
        developers.add(developer);
        Mockito.when(developerRepository.getAll()).thenReturn(developers);
        Assert.assertEquals(developerController.getAll(), developers);
    }

    @Test
    public void testGetById() {
        developerController.save(developer);
        Mockito.when(developerController.getById(ID)).thenReturn(developer);
        Assert.assertEquals(developerRepository.getById(ID),developer);
    }
}
