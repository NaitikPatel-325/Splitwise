package com.naitik.splitwise.controller;

import com.naitik.splitwise.entity.User;
import com.naitik.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/user")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return userService.findUserByEmail(oauth2User.getAttribute("email"))
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

//    @PostMapping("/logout")
//    public void customLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated()) {
//            request.getSession().invalidate();
//            response.addCookie(new jakarta.servlet.http.Cookie("JSESSIONID", null));
//            System.out.println("Logged out");
//            response.sendRedirect("http://localhost:5173/");
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//        }
//    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return "Unauthorized";
        }
        String email = oauth2User.getAttribute("email");
        System.out.println("principal: " + oauth2User);
        return "Hello, Secured! Logged in as: " + email;
    }
}
