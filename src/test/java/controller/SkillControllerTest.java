package controller;
import model.Skill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.SkillRepository;
import repository.jdbcImpl.SkillRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class SkillControllerTest {

    SkillController skillController = Mockito.mock(SkillController.class);
    SkillRepository skillRepository = Mockito.mock(SkillRepository.class);

    @Before
    public void setUp() {
        
    }

    @Test
    public void testGetAllSkill() {
        Skill java = new Skill(1L, "java");
        Skill php = new Skill(2L, "php");
        List<Skill> skills = new ArrayList<>();
        skills.add(java);
        skills.add(php);
        when(skillRepository.getAll()).thenReturn(skills);
        Assert.assertEquals(skillController.getAll(), skills);
    }



}