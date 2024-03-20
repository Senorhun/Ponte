package org.ponte;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class AppUserControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void test_createAppUserSuccessful() throws Exception {
        String basicAppUserJsonData = "{\n" +
                "\"firstName\": \"Hermione\",\n" +
                "\"lastName\": \"Granger\", \n" +
                "\"email\": \"hermione@gmail.com\", \n" +
                "\"password\": \"testHotel1234!\" \n" +
                "}";


        mock.perform(post("/api/users/createUser")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(basicAppUserJsonData))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Hermione")))
                .andExpect(jsonPath("$.lastName", is("Granger")))
                .andExpect(jsonPath("$.email", is("hermione@gmail.com")));
    }

    @Test
    void test_createAppUserUnsuccessful() throws Exception {
        String invalidAppUserJsonData = "{\n" +
                "\"firstName\": \"Hermione\",\n" +
                "\"lastName\": \"Granger\", \n" +
                "\"email\": \"invalidemail\", \n" +
                "\"password\": \"testHotel1234!\" \n" +
                "}";

        mock.perform(post("/api/users/createUser")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(invalidAppUserJsonData))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_deleteUserById_Success() throws Exception {
        long userIdToDelete = 1L;
        mock.perform(delete("/api/users/deleteUserById/{userId}", userIdToDelete)
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    void test_deleteUserById_UserNotFound() throws Exception {
        long nonExistentUserId = 9999L;
        mock.perform(delete("/api/users/deleteUserById/{userId}", nonExistentUserId)
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
