package com.impensa.expense.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.impensa.expense.dto.LoginDTO;
import com.impensa.expense.dto.RegisterDTO;
import com.impensa.expense.exception.AuthorizationException;
import com.impensa.expense.model.Role;
import com.impensa.expense.model.User;
import com.impensa.expense.response.AuthenticationResponse;
import com.impensa.expense.response.Response;
import io.jsonwebtoken.JwtException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AuthenticationService#register(RegisterDTO)}
     */
    @Test
    void testRegister() throws AuthorizationException {
        when(userService.findByEmail((String) any())).thenReturn(Optional.of(new User()));
        doNothing().when(userService).save((User) any());
        assertThrows(AuthorizationException.class, () -> authenticationService
                .register(new RegisterDTO("Name", "GBP", "jane.doe@example.org", "iloveyou", Role.USER)));
        verify(userService).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterDTO)}
     */
    @Test
    void testRegister2() throws AuthorizationException {
        when(userService.findByEmail((String) any())).thenReturn(Optional.empty());
        doNothing().when(userService).save((User) any());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        when(jwtService.extractExpiration((String) any())).thenReturn(fromResult);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        Response actualRegisterResult = authenticationService
                .register(new RegisterDTO("Name", "GBP", "jane.doe@example.org", "iloveyou", Role.USER));
        assertSame(fromResult, ((AuthenticationResponse) actualRegisterResult).getExpiresAt());
        assertEquals("Success", actualRegisterResult.getMessage());
        assertEquals("ABC123", ((AuthenticationResponse) actualRegisterResult).getJwtToken());
        verify(userService).findByEmail((String) any());
        verify(userService).save((User) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(jwtService).extractExpiration((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterDTO)}
     */
    @Test
    void testRegister3() throws AuthorizationException {
        when(userService.findByEmail((String) any())).thenReturn(Optional.empty());
        doNothing().when(userService).save((User) any());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(jwtService.extractExpiration((String) any()))
                .thenReturn(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new JwtException("An error occurred"));
        assertThrows(JwtException.class, () -> authenticationService
                .register(new RegisterDTO("Name", "GBP", "jane.doe@example.org", "iloveyou", Role.USER)));
        verify(userService).findByEmail((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister4() throws AuthorizationException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.dto.RegisterDTO.getEmail()" because "registerDTO" is null
        //       at com.impensa.expense.service.AuthenticationService.register(AuthenticationService.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userService.findByEmail((String) any())).thenReturn(Optional.empty());
        doNothing().when(userService).save((User) any());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(jwtService.extractExpiration((String) any()))
                .thenReturn(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        authenticationService.register(null);
    }

    /**
     * Method under test: {@link AuthenticationService#login(LoginDTO)}
     */
    @Test
    void testLogin() throws AuthorizationException, AuthenticationException {
        when(userService.findByEmail((String) any())).thenReturn(Optional.of(new User()));
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        when(jwtService.extractExpiration((String) any())).thenReturn(fromResult);
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        Response actualLoginResult = authenticationService.login(new LoginDTO("jane.doe@example.org", "iloveyou"));
        assertSame(fromResult, ((AuthenticationResponse) actualLoginResult).getExpiresAt());
        assertEquals("Success", actualLoginResult.getMessage());
        assertEquals("ABC123", ((AuthenticationResponse) actualLoginResult).getJwtToken());
        verify(userService, atLeast(1)).findByEmail((String) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(jwtService).extractExpiration((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthenticationService#login(LoginDTO)}
     */
    @Test
    void testLogin2() throws AuthorizationException, AuthenticationException {
        when(userService.findByEmail((String) any())).thenReturn(Optional.of(new User()));
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(jwtService.extractExpiration((String) any()))
                .thenReturn(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        when(authenticationManager.authenticate((Authentication) any())).thenThrow(new JwtException("An error occurred"));
        assertThrows(JwtException.class,
                () -> authenticationService.login(new LoginDTO("jane.doe@example.org", "iloveyou")));
        verify(userService).findByEmail((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthenticationService#login(LoginDTO)}
     */
    @Test
    void testLogin3() throws AuthorizationException, AuthenticationException {
        when(userService.findByEmail((String) any())).thenReturn(Optional.empty());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(jwtService.extractExpiration((String) any()))
                .thenReturn(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(AuthorizationException.class,
                () -> authenticationService.login(new LoginDTO("jane.doe@example.org", "iloveyou")));
        verify(userService).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#login(LoginDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogin4() throws AuthorizationException, AuthenticationException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.dto.LoginDTO.getEmail()" because "loginDTO" is null
        //       at com.impensa.expense.service.AuthenticationService.login(AuthenticationService.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userService.findByEmail((String) any())).thenReturn(Optional.of(new User()));
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(jwtService.extractExpiration((String) any()))
                .thenReturn(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        authenticationService.login(null);
    }

    /**
     * Method under test: {@link AuthenticationService#verify(String)}
     */
    @Test
    void testVerify() {
        when(jwtService.isTokenValid((String) any(), (UserDetails) any())).thenReturn(true);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        assertTrue(authenticationService.verify("ABC123"));
        verify(jwtService).isTokenValid((String) any(), (UserDetails) any());
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#verify(String)}
     */
    @Test
    void testVerify2() {
        when(jwtService.isTokenValid((String) any(), (UserDetails) any())).thenReturn(false);
        when(jwtService.extractUsername((String) any())).thenReturn("janedoe");
        assertThrows(JwtException.class, () -> authenticationService.verify("ABC123"));
        verify(jwtService).isTokenValid((String) any(), (UserDetails) any());
        verify(jwtService).extractUsername((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#verify(String)}
     */
    @Test
    void testVerify3() {
        when(jwtService.isTokenValid((String) any(), (UserDetails) any()))
                .thenThrow(new JwtException("An error occurred"));
        when(jwtService.extractUsername((String) any())).thenThrow(new JwtException("An error occurred"));
        assertThrows(JwtException.class, () -> authenticationService.verify("ABC123"));
        verify(jwtService).extractUsername((String) any());
    }
}

