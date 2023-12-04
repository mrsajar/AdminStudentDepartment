package Spring.Boot.API.Server.Controller;

import Spring.Boot.API.Server.Entity.Admin;
import Spring.Boot.API.Server.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) throws Exception {
        Admin save = adminService.addAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long id) throws Exception {
        adminService.deleteAdmin(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted admin");
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdminAndSearchByName(@RequestParam String searchName, SortOrder sortOrder) throws Exception {
        List<Admin> allAdmin = adminService.getAllAdmin(searchName, sortOrder);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allAdmin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Admin>> getById(@PathVariable("id") Long id) throws Exception {
        Optional<Admin> adminById = adminService.getAdminById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long id, @RequestBody Admin admin) throws Exception {
        Admin updated = adminService.updateAdmin(admin, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }
}
