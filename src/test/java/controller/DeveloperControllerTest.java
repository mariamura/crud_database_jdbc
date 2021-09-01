package controller;

import model.Developer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.jdbcImpl.DeveloperRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class DeveloperControllerTest {

    final long ID = 1L;
    DeveloperController developerController = null;
    DeveloperRepositoryImpl developerRepository = Mockito.mock(DeveloperRepositoryImpl.class);

    @Before
    public void setUp() {
        developerController = new DeveloperController(developerRepository);
    }

    private Developer getDeveloper() {
        return new Developer(1L, "first1", "last1");
    }

    @Test
    public void testGetAll() {
        List<Developer> developers = new ArrayList<>();
        developers.add(getDeveloper());
        Mockito.when(developerRepository.getAll()).thenReturn(developers);
        Assert.assertEquals(developerController.getAll(), developers);
    }

    @Test
    public void testGetById() {
        developerController.save(getDeveloper());
        Mockito.when(developerController.getById(ID)).thenReturn(getDeveloper());
        Assert.assertEquals(developerRepository.getById(ID),getDeveloper());
    }
}
