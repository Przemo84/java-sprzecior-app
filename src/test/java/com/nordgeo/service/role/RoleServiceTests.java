package com.nordgeo.service.role;


import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.repository.RoleRepository;
import com.nordgeo.repository.UserRepository;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.Role.RoleServiceImpl;
import com.nordgeo.service.user.activity.UserActivitiesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceTests {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AuthManager authManager;

    @Mock
    private UserActivitiesService activitiesService;

    private final PageRequest pageRequest = mock(PageRequest.class);

    private Role role;

    @Before
    public void setup() {
        User authUser = new User(1);
        given(authManager.user()).willReturn(authUser);

        this.role = spy(Role.class);
        this.role.setId(1);
    }

    @Test
    public void shouldPerformFindById() {
        //given role

        //when
        roleService.findById(role.getId());

        //then
        verify(roleRepository, times(1)).findOne(role.getId());
    }

    @Test
    public void shouldPerformFindAll() {
        //given pageRequest

        //when
        roleService.findAll(pageRequest);

        //then
        verify(roleRepository, times(1)).findAll(pageRequest);
    }

    @Test
    public void shouldPerformSave() {
        //given role
        role.setName("NEW_ROLE_NAME");

        //when
        roleService.save(role);

        //then
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void shouldPerformDelete() {
        //given role

        User authUser = authManager.user();
        authUser.setRole(new Role("Admin"));

        when(roleRepository.findOne(role.getId())).thenReturn(role);
        when(authManager.user()).thenReturn(authUser);

        //when
        roleService.delete(role.getId());

        //then
        verify(roleRepository, times(1)).delete(role.getId());
    }


}
