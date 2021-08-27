package controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.SkillRepository;



@ExtendWith(MockitoExtension.class)

public class SkillControllerTest {

    @Mock
    private SkillRepository skillRepository;
    @InjectMocks
    private SkillController underTest;


}