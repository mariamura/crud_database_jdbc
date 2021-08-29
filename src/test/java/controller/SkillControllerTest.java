package controller;
import model.Skill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.jdbcImpl.SkillRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class SkillControllerTest {

    Long ID = 1L;
    Skill skill = new Skill(1L, "java");
    SkillController skillController = null;
    SkillRepositoryImpl skillRepository = Mockito.mock(SkillRepositoryImpl.class);

    @Before
    public void setUp() {
        skillController = new SkillController(skillRepository);
    }

    @Test
    public void testGetAll() {
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        Mockito.when(skillRepository.getAll()).thenReturn(skills);
        Assert.assertEquals(skillController.getAll(), skills);
    }


    @Test
    public void testGetById() {
        skillController.save(skill);
        Mockito.when(skillController.getById(ID)).thenReturn(skill);
        Assert.assertEquals(skillRepository.getById(ID),skill);
    }

}