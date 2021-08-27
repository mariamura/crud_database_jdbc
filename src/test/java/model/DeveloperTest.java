package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeveloperTest {

    Developer developer = new Developer();
    List<Skill> skills = new ArrayList<>();

    @Before
    public void setUp(){
        skills.add((new Skill(1L, "java")));
        developer.setFirstName("Lu");
        developer.setLastName("Ma");
        developer.setSkills(skills);
    }

    @Test
    public void shouldCreateDeveloperInstanceTest(){
        Assert.assertEquals("Lu", developer.getFirstName());
        Assert.assertEquals("Ma", developer.getLastName());
        Assert.assertEquals(skills, developer.getSkills());
    }


}