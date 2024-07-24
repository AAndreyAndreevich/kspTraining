package app.controller;

import app.entity.Cat;
import app.repository.CatRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MainController {

    @Autowired
    private CatRepo catRepo;

    @PostMapping("/create")
    public void createCat(@RequestBody Cat cat) {
        log.info("Создан кот: " + catRepo.save(cat));
    }

    @PutMapping("/change")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            log.info("Был запрошен несуществующий кот");
            return "Такого кота не существует";
        }
        log.info("Был изменен кот с id: " + cat.getId());
        return catRepo.save(cat).toString();
    }

    @GetMapping("/getById")
    public String getCatById(@RequestParam Long id) {
        log.info("Запрошен кот по id: " + id);
        return catRepo.findById(id).orElseThrow().toString();
    }

    @GetMapping("/getAll")
    public String getAllCat() {
        log.info("Запрошен список всех котов");
        return catRepo.findAll().toString();
    }

    @DeleteMapping("/delete")
    public void deleteCat(@RequestParam Long id) {
        log.info("Удален кот по id: " + id);
        catRepo.deleteById(id);
    }
}