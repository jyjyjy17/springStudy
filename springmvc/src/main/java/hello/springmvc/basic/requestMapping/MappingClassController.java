package hello.springmvc.basic.requestMapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {
    @GetMapping
    public String user() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post user";
    }
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId";
    }
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete {userId}";
    }
}
