package com.nordgeo.controller.admin.user.employee;

import com.nordgeo.entity.User;
import com.nordgeo.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testIndexAction() throws Exception {
        when(userService.findAllLocked(any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        mockMvc.perform(get("/admin/employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("users.employees.index"));
    }

    @Test
    public void testFormAction() throws Exception {

        mockMvc.perform(get("/admin/employees/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("user.employees.form"));
    }

    @Test
    public void testEditFormAction() throws Exception {

        when(userService.findById(anyInt())).thenReturn(new User());

        mockMvc.perform(get("/admin/employees/form/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user.employees.form"));
    }

    @Test
    public void testSubmitAction() throws Exception {

        mockMvc.perform(post("/admin/employees/save")
                .param("username", "user_name")
                .param("email", "user_email@email.com")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .param("password", "user_secret_password")
                .param("passwordConfirm", "user_secret_password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/employees"));
    }

    @Test
    public void testLockAction() throws Exception {

        mockMvc.perform(get("/admin/employees/lock/3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/employees"));
    }

    @Test
    public void testUpgradeToEditorAction() throws Exception {

        mockMvc.perform(get("/admin/employees/make-editor/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/employees"));
    }


}
