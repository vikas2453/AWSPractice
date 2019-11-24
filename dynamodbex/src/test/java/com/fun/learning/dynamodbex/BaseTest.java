package com.fun.learning.dynamodbex;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)

@SpringBootTest
public abstract class BaseTest {
	
	
	
}
