package controller;
import model.Skill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.jdbcImpl.SkillRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SkillControllerTest {

    SkillController skillController = null;
    SkillRepositoryImpl skillRepository = Mockito.mock(SkillRepositoryImpl.class);


    @Before
    public void setUp() {
        skillController = new SkillController(skillRepository);
    }

    @Test
    public void testGetAllSkill() {
        Skill java = new Skill(1L, "java");
        Skill php = new Skill(2L, "php");
        List<Skill> skills = new ArrayList<>();
        skills.add(java);
        skills.add(php);
        Mockito.when(skillRepository.getAll()).thenReturn(skills);
        Assert.assertEquals(skillController.getAll(), skills);
    }



}