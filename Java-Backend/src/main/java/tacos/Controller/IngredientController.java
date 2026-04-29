package tacos.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.Dto.IngredientDTO;
import tacos.Mapper.IngredientMapper;
import tacos.Model.Ingredient;
import tacos.Service.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final IngredientMapper mapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper mapper) {
        this.ingredientService = ingredientService;
        this.mapper = mapper;
    }

    // ------------------ CREATE ------------------
    @PostMapping
    public ResponseEntity<IngredientDTO> createIngredient(@Valid @RequestBody IngredientDTO dto) {
        Ingredient saved = ingredientService.createIngredient(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

    // ------------------ READ ------------------

    // Get all ingredients or optionally filter by name/type
    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAllIngredients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type) {

        List<Ingredient> ingredients;

        if (name != null && !name.isBlank()) {
            ingredients = ingredientService.getIngredientsByName(name);
        } else if (type != null && !type.isBlank()) {
            ingredients = ingredientService.getIngredientsByType(type);
        } else {
            ingredients = ingredientService.getAllIngredients();
        }

        List<IngredientDTO> dtos = ingredients.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Get ingredient by ID
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredientsById(id);
        return ResponseEntity.ok(mapper.toDTO(ingredient));
    }

    // ------------------ UPDATE ------------------
    @PutMapping("/{id}")
    public ResponseEntity<IngredientDTO> updateIngredient(
            @PathVariable int id,
            @Valid @RequestBody IngredientDTO dto) {

        Ingredient updated = ingredientService.updateIngredient(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok("Ingredient deleted successfully");
    }
}
