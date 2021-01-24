package com.nordgeo.service.user;

import com.nordgeo.data.dto.UserDto;
import com.nordgeo.data.dto.UserPasswordDto;
import com.nordgeo.data.mapper.UserDtoMapper;
import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.repository.RoleRepository;
import com.nordgeo.repository.UserRepository;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.user.activity.UserActivitiesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AuthManager authManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserActivitiesService activitiesService;

    @Mock
    private UserDtoMapper userDtoMapper;

    private final PageRequest pageRequest = mock(PageRequest.class);

    private User user;

    private Role role;

    @Before
    public void setup() {
        given(authManager.user()).willReturn(new User());
        this.role = spy(Role.class);
        this.role.setId(3);
        this.role.setName("Employee");

        this.user = spy(User.class);
        this.user.setId(3);
        this.user.setRole(role);
    }

    @Test
    public void shouldPerformFindByUsername() {
        //given user
        user.setUsername("user_name");

        //when
        userService.findByUsername(user.getUsername());

        //then
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    public void shouldPerformFindByEmail() {
        //given user
        user.setEmail("test@email.com");

        //when
        userService.findByEmail(user.getEmail());

        //then
        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    public void shouldPerformFindById() {
        //given user
        when(userRepository.findOne(user.getId())).thenReturn(user);

        //when
        userService.findById(user.getId());

        //then
        verify(userRepository, times(1)).findOne(user.getId());
        ;
    }

    @Test
    public void shouldPerformFindAll() {
        //given pageRequest

        //when
        userService.findAllAdmins(pageRequest);

        //then
        verify(userRepository, times(1)).findUsersByRoleAndLockDateIsNull(roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue()), pageRequest);
    }

    @Test
    public void shouldPerformSave() {
        //given user
        user.setUsername("user_name");
        user.setEmail("user@email.com");
        user.setFirstName("user_first_name");
        user.setLastName("user_last_name");
        user.setPassword("user_password");
        user.setPasswordConfirm("user_password");
        user.setRole(role);

        //when
        userService.save(user);

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldPerformLock() {
        //given user,
        User authUser = authManager.user();
        authUser.setId(1);
        authUser.setRole(new Role("Admin"));

        when(authManager.user()).thenReturn(authUser);
        when(userRepository.findById(user.getId())).thenReturn(user);

        //when
        userService.lock(user.getId());

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldPerformUnlock() {
        //given user
        User authUser = authManager.user();
        authUser.setId(1);
        authUser.setRole(new Role("Admin"));

        when(authManager.user()).thenReturn(authUser);
        when(userRepository.findById(user.getId())).thenReturn(user);

        //when
        userService.unlock(user.getId());

        //then
        verify(userRepository, times(1)).save(user);
    }


    @Test
    public void shouldPerformChangePassword() {
        //given
        UserPasswordDto passwordDto = spy(UserPasswordDto.class);
        passwordDto.setCurrentPassword("current_password");
        passwordDto.setNewPassword("new_password");
        passwordDto.setPasswordConfirm("new_password");

        User authUser = authManager.user();
        authUser.setRole(new Role("Admin"));

        when(passwordEncoder.encode(passwordDto.getNewPassword())).thenReturn("hashed_password");
        when(authManager.user()).thenReturn(authUser);

        //when
        userService.changePassword(passwordDto);

        //then
        verify(userRepository, times(1)).save(authUser);
    }

    @Test
    public void shouldPerformFindAllLocked() {
        //given pageRequest

        //when
        userService.findAllLocked(pageRequest);

        //then
        verify(userRepository, times(1)).findAllByLockDateIsNotNull(pageRequest);
    }

    @Test
    public void shouldPerformFindAllAdmins() {
        //given pageRequest

        //when
        userService.findAllAdmins(pageRequest);

        //then
        verify(userRepository, times(1)).findUsersByRoleAndLockDateIsNull(roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue()), pageRequest);
    }

    @Test
    public void shouldPerformFindAllEditors() {
        //given pageRequest

        //when
        userService.findAllEditors(pageRequest);

        //then
        verify(userRepository, times(1)).findUsersByRoleAndLockDateIsNull(roleRepository.findOne(User.RoleName.EDITOR_ROLE.getValue()), pageRequest);
    }

    @Test
    public void shouldPerformFindAllEmployees() {
        //given pageRequest

        //when
        userService.findAllEmployees(pageRequest);

        List<Role> roles = new ArrayList<Role>() {{
            add(roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue()));
        }};

        //then
        verify(userRepository, times(1)).findUsersByRoleIsNotInAndLockDateIsNull(roles, pageRequest);
    }

    @Test
    public void shouldPerformUpdateWithOldPassword() {
        //given user
        user.setUsername("new_user_name");
        user.setEmail("new@email.com");
        user.setFirstName("first_name");
        user.setLastName("last_name");

        //when
        userService.updateWithOldPassword(user);

        //then
        verify(userRepository, times(1)).save(user);
    }


    @Test
    public void shouldPerformSetLastLoginDate() {
        //given user

        //when
        userService.setLastLoginDate(user);

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldPerformChangeRole() {
        //given user

        when(userRepository.findById(user.getId())).thenReturn(user);
        when(roleRepository.findOne(User.RoleName.EDITOR_ROLE.getValue())).thenReturn(role);

        //when
        userService.changeRole(user.getId(), User.RoleName.EDITOR_ROLE);

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldPerformSaveOrUpdateWithAdminRole() {
        //given user
        UserDto userDto = spy(UserDto.class);
        userDto.setUsername("user_name");
        userDto.setEmail("user@email.com");
        userDto.setFirstName("user_first_name");
        userDto.setLastName("user_last_name");
        userDto.setPassword("user_password");
        userDto.setPasswordConfirm("user_password");

        User user = spy(User.class);
        user.setUsername("user_name");
        user.setEmail("user@email.com");
        user.setFirstName("user_first_name");
        user.setLastName("user_last_name");
        user.setPassword("user_password");
        user.setPasswordConfirm("user_password");

        when(userDtoMapper.map(userDto)).thenReturn(user);
        when(roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue())).thenReturn(role);
        //when
        userService.saveOrUpdate(userDto, User.RoleName.ADMIN_ROLE);

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldPerformSaveOrUpdateWithEditorRoleWithNoPasswordChanged() {
        //given user
        UserDto userDto = spy(UserDto.class);
        userDto.setId(10);
        userDto.setUsername("user_name");
        userDto.setEmail("user@email.com");
        userDto.setFirstName("user_first_name");
        userDto.setLastName("user_last_name");
        userDto.setPassword("");

        User user = spy(User.class);
        user.setId(10);
        user.setUsername("user_name");
        user.setEmail("user@email.com");
        user.setFirstName("user_first_name");
        user.setLastName("user_last_name");
        user.setPassword("");

        when(userDtoMapper.map(userDto)).thenReturn(user);
        when(roleRepository.findOne(User.RoleName.ADMIN_ROLE.getValue())).thenReturn(role);
        when(userRepository.findById(userDto.getId())).thenReturn(user);
        //when
        userService.saveOrUpdate(userDto, User.RoleName.ADMIN_ROLE);

        //then
        verify(userRepository, times(1)).save(user);
    }


}
