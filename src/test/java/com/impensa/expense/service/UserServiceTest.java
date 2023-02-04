package com.impensa.expense.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.impensa.expense.dto.DashboardDTO;
import com.impensa.expense.dto.UserDTO;
import com.impensa.expense.dto.UserUpdateDTO;
import com.impensa.expense.model.Role;
import com.impensa.expense.model.User;
import com.impensa.expense.repository.UserRepository;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#save(User)}
     */
    @Test
    void testSave() {
        when(userRepository.save((User) any())).thenReturn(new User());
        userService.save(new User());
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserService#save(User)}
     */
    @Test
    void testSave2() {
        when(userRepository.save((User) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(BadCredentialsException.class, () -> userService.save(new User()));
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserService#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
        Optional<User> ofResult = Optional.of(new User());
        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
        Optional<User> actualFindByEmailResult = userService.findByEmail("jane.doe@example.org");
        assertSame(ofResult, actualFindByEmailResult);
        assertTrue(actualFindByEmailResult.isPresent());
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserService#findByEmail(String)}
     */
    @Test
    void testFindByEmail2() {
        when(userRepository.findByEmail((String) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(BadCredentialsException.class, () -> userService.findByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserFromToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserFromToken(String)}
     */
    @Test
    void testGetUserFromToken2() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(BadCredentialsException.class, () -> userService.getUserFromToken("ABC123"));
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserFromToken3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserFromToken4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserFromToken5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.User.getEmail()" because "user" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(null);
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserFromToken(String)}
     */
    @Test
    void testGetUserFromToken6() {
        ArrayList<User> userList = new ArrayList<>();
        User user = new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER);

        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        assertSame(user, userService.getUserFromToken("ABC123"));
        verify(userRepository).findAll();
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserFromToken7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "Name", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserIdFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserIdFromToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserIdFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserIdFromToken(String)}
     */
    @Test
    void testGetUserIdFromToken2() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(BadCredentialsException.class, () -> userService.getUserIdFromToken("ABC123"));
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserIdFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserIdFromToken3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserIdFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserIdFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserIdFromToken4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserIdFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserIdFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserIdFromToken5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.User.getEmail()" because "user" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(null);
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserIdFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserIdFromToken(String)}
     */
    @Test
    void testGetUserIdFromToken6() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        assertEquals(123L, userService.getUserIdFromToken("ABC123").longValue());
        verify(userRepository).findAll();
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserIdFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserIdFromToken7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "Name", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserIdFromToken("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserData(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserData() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUserData(UserService.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserData("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserData(String)}
     */
    @Test
    void testGetUserData2() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(BadCredentialsException.class, () -> userService.getUserData("ABC123"));
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserData(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserData3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserData(UserService.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserData("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserData(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserData4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUserData(UserService.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserData("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserData(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserData5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.User.getEmail()" because "user" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserData(UserService.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(null);
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserData("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUserData(String)}
     */
    @Test
    void testGetUserData6() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        UserDTO actualUserData = userService.getUserData("ABC123");
        assertEquals("janedoe", actualUserData.getUser_email());
        assertEquals("janedoe", actualUserData.getUser_name());
        verify(userRepository).findAll();
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserData(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserData7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserData(UserService.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "Name", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUserData("ABC123");
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserData() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //       at com.impensa.expense.service.UserService.updateUserData(UserService.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "iloveyou", "iloveyou"),
                "ABC123");
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    void testUpdateUserData2() throws Exception {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(BadCredentialsException.class, () -> userService
                .updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "iloveyou", "iloveyou"), "ABC123"));
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserData3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //       at com.impensa.expense.service.UserService.updateUserData(UserService.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "iloveyou", "iloveyou"),
                "ABC123");
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserData4() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //       at com.impensa.expense.service.UserService.updateUserData(UserService.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "iloveyou", "iloveyou"),
                "ABC123");
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserData5() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.User.getEmail()" because "user" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUserIdFromToken(UserService.java:45)
        //       at com.impensa.expense.service.UserService.updateUserData(UserService.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(null);
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "iloveyou", "iloveyou"),
                "ABC123");
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    void testUpdateUserData6() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        assertThrows(BadCredentialsException.class, () -> userService
                .updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "iloveyou", "iloveyou"), "ABC123"));
        verify(userRepository).findAll();
        verify(userRepository).findById((Long) any());
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    void testUpdateUserData7() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        assertThrows(Exception.class, () -> userService
                .updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "iloveyou", "iloveyou"), "ABC123"));
        verify(userRepository).findAll();
        verify(userRepository).findById((Long) any());
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    void testUpdateUserData8() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);
        assertEquals("User with ID 123 was updated",
                userService
                        .updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "janedoe", "iloveyou"), "ABC123")
                        .getMessage());
        verify(userRepository).save((User) any());
        verify(userRepository).findAll();
        verify(userRepository).findById((Long) any());
        verify(jwtService).extractUsername((String) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    void testUpdateUserData9() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new BadCredentialsException("janedoe"));
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);
        assertThrows(BadCredentialsException.class, () -> userService
                .updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "janedoe", "iloveyou"), "ABC123"));
        verify(userRepository).findAll();
        verify(userRepository).findById((Long) any());
        verify(jwtService).extractUsername((String) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#updateUserData(UserUpdateDTO, String)}
     */
    @Test
    void testUpdateUserData10() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(false);
        assertThrows(BadCredentialsException.class, () -> userService
                .updateUserData(new UserUpdateDTO("janedoe", "jane.doe@example.org", "janedoe", "iloveyou"), "ABC123"));
        verify(userRepository).findAll();
        verify(userRepository).findById((Long) any());
        verify(jwtService).extractUsername((String) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUser() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUser(UserService.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUser("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    void testGetUser2() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(jwtService.extractUsername((String) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(BadCredentialsException.class, () -> userService.getUser("ABC123"));
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUser3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUser(UserService.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUser("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUser4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:41)
        //       at com.impensa.expense.service.UserService.getUser(UserService.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUser("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUser5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.User.getEmail()" because "user" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUser(UserService.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(null);
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUser("ABC123");
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    void testGetUser6() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "janedoe", "janedoe", "GBP", "iloveyou", Role.USER));
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        DashboardDTO actualUser = userService.getUser("ABC123");
        assertEquals("GBP", actualUser.getUser_currency());
        assertEquals("janedoe", actualUser.getUser_name());
        assertEquals("janedoe", actualUser.getUser_email());
        verify(userRepository).findAll();
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUser7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.impensa.expense.service.UserService.lambda$getUserFromToken$0(UserService.java:39)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //       at com.impensa.expense.service.UserService.getUserFromToken(UserService.java:40)
        //       at com.impensa.expense.service.UserService.getUser(UserService.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123L, "Name", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        userService.getUser("ABC123");
    }
}

