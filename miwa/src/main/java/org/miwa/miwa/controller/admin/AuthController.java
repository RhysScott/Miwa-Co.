package org.miwa.miwa.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.LoginRequest;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${app.admin.username:admin}")
    private String adminUser;

    @Value("${app.admin.password}")
    private String adminPass;

    @PostMapping("/login")
    public Result<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        String pwd = adminPass.startsWith("{bcrypt}") ? adminPass.substring(8) : adminPass;
        if (!adminUser.equals(request.getUsername()) ||
                !passwordEncoder.matches(request.getPassword(), pwd)) {
            return Result.fail(401, "用户名或密码错误");
        }
        String token = jwtTokenProvider.generateToken(request.getUsername());
        Map<String, String> data = new LinkedHashMap<>();
        data.put("token", token);
        return Result.ok(data);
    }
}
