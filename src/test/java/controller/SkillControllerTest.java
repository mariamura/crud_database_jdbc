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

    final long ID = 1L;
    SkillController skillController = null;
    SkillRepositoryImpl skillRepository = Mockito.mock(SkillRepositoryImpl.class);

    @Before
    public void setUp() {
        skillController = new SkillController(skillRepository);
    }

    private Skill getSkill() {
        return new Skill(ID, "java");
    }

    @Test
    public void testGetAll() {
        List<Skill> skills = new ArrayList<>();
        skills.add(getSkill());
        Mockito.when(skillRepository.getAll()).thenReturn(skills);
        Assert.assertEquals(skillController.getAll(), skills);
    }


    @Test
    public void testGetById() {
        skillController.save(getSkill());
        Mockito.when(skillController.getById(ID)).thenReturn(getSkill());
        Assert.assertEquals(skillRepository.getById(ID),getSkill());
    }

}