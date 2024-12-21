package com.jeffhb60.backendshoppingcart.bootstrap;

import com.jeffhb60.backendshoppingcart.model.Category;
import com.jeffhb60.backendshoppingcart.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>();

            categories.add(new Category("Electronics"));
            categories.add(new Category("Fashion"));
            categories.add(new Category("Home & Kitchen"));
            categories.add(new Category("Books"));
            categories.add(new Category("Toys & Games"));
            categories.add(new Category("Health & Beauty"));
            categories.add(new Category("Sports & Outdoors"));
            categories.add(new Category("Automotive"));
            categories.add(new Category("Groceries"));
            categories.add(new Category("Pet Supplies"));
            categories.add(new Category("Baby Products"));
            categories.add(new Category("Office Supplies"));
            categories.add(new Category("Music & Movies"));
            categories.add(new Category("Garden & Outdoors"));
            categories.add(new Category("Tools & Home Improvement"));
            categories.add(new Category("Jewelry"));
            categories.add(new Category("Shoes"));
            categories.add(new Category("Bags & Luggage"));
            categories.add(new Category("Art Supplies"));
            categories.add(new Category("Video Games"));

            // Save all categories to the database
            categoryRepository.saveAll(categories);
        }
    }
}
