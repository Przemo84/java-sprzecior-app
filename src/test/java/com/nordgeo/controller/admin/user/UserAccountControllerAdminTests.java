package com.nordgeo.controller.admin.user;


import com.nordgeo.service.user.UserService;
import com.nordgeo.validators.PasswordConstraintValidator;
import com.nordgeo.validators.UserPasswordValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountControllerAdminTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    UserPasswordValidator userPasswordValidator;

    @MockBean
    PasswordConstraintValidator passwordConstraintValidator;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testChangePasswordFormAction() throws Exception {

        mockMvc.perform(get("/admin/account/password"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin.user.change.password.form"));
    }

    @Test
    public void testSubmitAction() throws Exception {

        mockMvc.perform(post("/admin/account/save")
                .param("currentPassword", "random")
                .param("newPassword", "new_password")
                .param("passwordConfirm", "new_password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/dashboard"));
    }


}
