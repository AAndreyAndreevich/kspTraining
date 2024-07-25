package app.controller;

import app.dto.CatDto;
import app.entity.Cat;
import app.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "main_methods")
@Slf4j
@RestController
public class MainController {

    @Autowired
    private CatRepo catRepo;

    @Operation(
            summary = "Создает нового кота",
            description = "Получает dto кота и билдером собирает и сохраняет сущность в базу данных"
    )
    @PostMapping("/create")
    public void createCat(@RequestBody CatDto catDto) {
        log.info("Создан кот: " + catRepo.save(Cat.builder()
                        .name(catDto.getName())
                        .age(catDto.getAge())
                        .weight(catDto.getWeight())
                .build()));
    }

    @Operation(
            summary = "Изменяет существующего кота",
            description = "Заменяет существующие данные кота с указанным id на новые данные"
    )
    @PutMapping("/change")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            log.info("Был запрошен несуществующий кот");
            return "Такого кота не существует";
        }
        log.info("Был изменен кот с id: " + cat.getId());
        return catRepo.save(cat).toString();
    }

    @Operation(
            summary = "Получает информацию о коте",
            description = "Создает запрос по id в базу данных и получает данные о коте"
    )
    @GetMapping("/getById")
    public String getCatById(@RequestParam Long id) {
        log.info("Запрошен кот по id: " + id);
        return catRepo.findById(id).orElseThrow().toString();
    }

    @Operation(
            summary = "Выводит список всех существующих котов"
    )
    @GetMapping("/getAll")
    public String getAllCat() {
        log.info("Запрошен список всех котов");
        return catRepo.findAll().toString();
    }

    @Operation(
            summary = "Удаляет кота",
            description = "Получает id кота, и если он существует то удаляет его из базы данных"
    )
    @DeleteMapping("/delete")
    public void deleteCat(@RequestParam Long id) {
        if (!catRepo.existsById(id)) {
            log.info("Был запрошен несуществующий id");
            System.out.println("Кота с этим id не существует");
        }
        log.info("Кот с id: " + id + " был удален");
        catRepo.deleteById(id);
    }
}