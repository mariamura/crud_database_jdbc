package controller;
import model.Skill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;


public class SkillControllerTest {

    SkillRepository skillRepository = Mockito.mock(SkillRepository.class);

    @Test
    public void testGetAllSkill() {
        Skill java = new Skill(1L, "java");
        Skill php = new Skill(2L, "php");
        List<Skill> skills = new ArrayList<>();
        skills.add(java);
        skills.add(php);
        Mockito.when(skillRepository.getAll()).thenReturn(skills);
        Assert.assertEquals(skillRepository.getAll(), skills);
    }



}