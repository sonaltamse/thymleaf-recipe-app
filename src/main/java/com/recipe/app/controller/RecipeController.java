package com.recipe.app.controller;

import com.recipe.app.model.Recipe;
import com.recipe.app.repository.RecipeRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);
        return ResponseEntity.ok(savedRecipe);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer rating) {
        if(category != null && !category.isBlank()) {
            return ResponseEntity.ok(recipeRepository.findByCategory(category));
        }
        if(rating != null) {
            return ResponseEntity.ok(recipeRepository.findByRating(rating));
        }
        return ResponseEntity.ok(recipeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        // TODO
        return null;
    }
}