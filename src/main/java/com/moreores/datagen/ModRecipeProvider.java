package com.moreores.datagen;

import com.moreores.material.ModMaterials;
import com.moreores.material.OreMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

/**
 * Datagen recipe provider for all moreores materials.
 * Note: Static JSON recipe files under data/moreores/recipes/ are the primary
 * source of truth. This class generates the same recipes programmatically for
 * datagen runs.
 */
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        for (OreMaterial mat : ModMaterials.ALL_MATERIALS) {
            generateMaterialRecipes(exporter, mat);
        }
        generateSpecialAlloyRecipes(exporter);
    }

    private void generateMaterialRecipes(Consumer<RecipeJsonProvider> exporter, OreMaterial mat) {
        String name = mat.getName();

        // Smelt/blast raw -> ingot
        if (mat.hasRawItem() && mat.hasIngot()) {
            Item rawItem = getItem("moreores", "raw_" + name);
            Item ingotItem = getItem("moreores", name + "_ingot");
            if (rawItem != null && ingotItem != null) {
                CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(rawItem), RecipeCategory.MISC,
                                ingotItem, 0.7f, 200)
                        .criterion(FabricRecipeProvider.hasItem(rawItem), FabricRecipeProvider.conditionsFromItem(rawItem))
                        .offerTo(exporter, new Identifier("moreores", "smelt_" + name + "_ingot"));
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(rawItem), RecipeCategory.MISC,
                                ingotItem, 0.7f, 100)
                        .criterion(FabricRecipeProvider.hasItem(rawItem), FabricRecipeProvider.conditionsFromItem(rawItem))
                        .offerTo(exporter, new Identifier("moreores", "blast_" + name + "_ingot"));
            }
        }

        // Ingot <-> block
        if (mat.hasIngot() && mat.hasStorageBlock()) {
            Item ingotItem = getItem("moreores", name + "_ingot");
            Item blockItem = getItem("moreores", name + "_block");
            if (ingotItem != null && blockItem != null) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, blockItem)
                        .pattern("III").pattern("III").pattern("III")
                        .input('I', ingotItem)
                        .criterion(FabricRecipeProvider.hasItem(ingotItem), FabricRecipeProvider.conditionsFromItem(ingotItem))
                        .offerTo(exporter, new Identifier("moreores", name + "_block_from_ingots"));
                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ingotItem, 9)
                        .input(blockItem)
                        .criterion(FabricRecipeProvider.hasItem(blockItem), FabricRecipeProvider.conditionsFromItem(blockItem))
                        .offerTo(exporter, new Identifier("moreores", name + "_ingot_from_block"));
            }
        }

        // Gem <-> block
        if (mat.hasGem() && mat.hasStorageBlock()) {
            Item gemItem = getItem("moreores", name);
            Item blockItem = getItem("moreores", name + "_block");
            if (gemItem != null && blockItem != null) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, blockItem)
                        .pattern("GGG").pattern("GGG").pattern("GGG")
                        .input('G', gemItem)
                        .criterion(FabricRecipeProvider.hasItem(gemItem), FabricRecipeProvider.conditionsFromItem(gemItem))
                        .offerTo(exporter, new Identifier("moreores", name + "_block_from_gems"));
                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, gemItem, 9)
                        .input(blockItem)
                        .criterion(FabricRecipeProvider.hasItem(blockItem), FabricRecipeProvider.conditionsFromItem(blockItem))
                        .offerTo(exporter, new Identifier("moreores", name + "_from_block"));
            }
        }

        // Tools
        if (mat.hasTools()) {
            Item material = mat.hasIngot() ? getItem("moreores", name + "_ingot") : getItem("moreores", name);
            if (material != null) {
                offerPickaxeRecipe(exporter, name, material);
                offerAxeRecipe(exporter, name, material);
                offerShovelRecipe(exporter, name, material);
                offerHoeRecipe(exporter, name, material);
            }
        }

        // Sword
        if (mat.hasSword()) {
            Item material = mat.hasIngot() ? getItem("moreores", name + "_ingot") : getItem("moreores", name);
            if (material != null) {
                offerSwordRecipe(exporter, name, material);
            }
        }

        // Dagger
        if (mat.hasDagger()) {
            Item material = mat.hasGem() ? getItem("moreores", name) : getItem("moreores", name + "_ingot");
            if (material != null) {
                offerDaggerRecipe(exporter, name, material);
            }
        }

        // Armor
        if (mat.hasArmor()) {
            Item material = mat.hasGem() ? getItem("moreores", name) : getItem("moreores", name + "_ingot");
            if (material != null) {
                offerArmorRecipes(exporter, name, material);
            }
        }

        // Building blocks
        String blockSource = (mat.hasIngot() || mat.hasGem()) ? name + "_block" : name;
        Item blockItem = getItem("moreores", blockSource);
        if (blockItem != null) {
            if (mat.hasSlabs()) {
                Item slabItem = getItem("moreores", name + "_slab");
                if (slabItem != null) {
                    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, slabItem, 6)
                            .pattern("BBB")
                            .input('B', blockItem)
                            .criterion(FabricRecipeProvider.hasItem(blockItem), FabricRecipeProvider.conditionsFromItem(blockItem))
                            .offerTo(exporter, new Identifier("moreores", name + "_slab"));
                }
            }
            if (mat.hasStairs()) {
                Item stairsItem = getItem("moreores", name + "_stairs");
                if (stairsItem != null) {
                    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stairsItem, 4)
                            .pattern("B  ").pattern("BB ").pattern("BBB")
                            .input('B', blockItem)
                            .criterion(FabricRecipeProvider.hasItem(blockItem), FabricRecipeProvider.conditionsFromItem(blockItem))
                            .offerTo(exporter, new Identifier("moreores", name + "_stairs"));
                }
            }
            if (mat.hasWalls()) {
                Item wallItem = getItem("moreores", name + "_wall");
                if (wallItem != null) {
                    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, wallItem, 6)
                            .pattern("BBB").pattern("BBB")
                            .input('B', blockItem)
                            .criterion(FabricRecipeProvider.hasItem(blockItem), FabricRecipeProvider.conditionsFromItem(blockItem))
                            .offerTo(exporter, new Identifier("moreores", name + "_wall"));
                }
            }
        }
    }

    private void generateSpecialAlloyRecipes(Consumer<RecipeJsonProvider> exporter) {
        // Bronze: 3 copper + 6 tin -> 4 bronze ingots
        Item copperIngot = Items.COPPER_INGOT;
        Item tinIngot = getItem("moreores", "tin_ingot");
        Item bronzeIngot = getItem("moreores", "bronze_ingot");
        if (tinIngot != null && bronzeIngot != null) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, bronzeIngot, 4)
                    .input(copperIngot).input(copperIngot).input(copperIngot)
                    .input(tinIngot).input(tinIngot).input(tinIngot)
                    .input(tinIngot).input(tinIngot).input(tinIngot)
                    .criterion(FabricRecipeProvider.hasItem(tinIngot), FabricRecipeProvider.conditionsFromItem(tinIngot))
                    .offerTo(exporter, new Identifier("moreores", "bronze_ingot_crafting"));
        }

        // Steel: 1 iron + 4 coal -> 1 steel ingot
        Item steelIngot = getItem("moreores", "steel_ingot");
        if (steelIngot != null) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, steelIngot, 1)
                    .input(Items.IRON_INGOT)
                    .input(Items.COAL).input(Items.COAL).input(Items.COAL).input(Items.COAL)
                    .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT), FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                    .offerTo(exporter, new Identifier("moreores", "steel_ingot_crafting"));
        }

        // Copper alloy: 2 copper + 1 tin -> 3 copper_alloy ingots
        Item copperAlloyIngot = getItem("moreores", "copper_alloy_ingot");
        if (copperAlloyIngot != null && tinIngot != null) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, copperAlloyIngot, 3)
                    .input(copperIngot).input(copperIngot)
                    .input(tinIngot)
                    .criterion(FabricRecipeProvider.hasItem(tinIngot), FabricRecipeProvider.conditionsFromItem(tinIngot))
                    .offerTo(exporter, new Identifier("moreores", "copper_alloy_ingot_crafting"));
        }
    }

    // Tool recipe helpers
    private void offerPickaxeRecipe(Consumer<RecipeJsonProvider> exporter, String name, Item mat) {
        Item pickaxe = getItem("moreores", name + "_pickaxe");
        if (pickaxe == null) return;
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, pickaxe)
                .pattern("III").pattern(" S ").pattern(" S ")
                .input('I', mat).input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                .offerTo(exporter, new Identifier("moreores", name + "_pickaxe"));
    }

    private void offerAxeRecipe(Consumer<RecipeJsonProvider> exporter, String name, Item mat) {
        Item axe = getItem("moreores", name + "_axe");
        if (axe == null) return;
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, axe)
                .pattern("II").pattern("IS").pattern(" S")
                .input('I', mat).input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                .offerTo(exporter, new Identifier("moreores", name + "_axe"));
    }

    private void offerShovelRecipe(Consumer<RecipeJsonProvider> exporter, String name, Item mat) {
        Item shovel = getItem("moreores", name + "_shovel");
        if (shovel == null) return;
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, shovel)
                .pattern("I").pattern("S").pattern("S")
                .input('I', mat).input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                .offerTo(exporter, new Identifier("moreores", name + "_shovel"));
    }

    private void offerHoeRecipe(Consumer<RecipeJsonProvider> exporter, String name, Item mat) {
        Item hoe = getItem("moreores", name + "_hoe");
        if (hoe == null) return;
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, hoe)
                .pattern("II").pattern(" S").pattern(" S")
                .input('I', mat).input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                .offerTo(exporter, new Identifier("moreores", name + "_hoe"));
    }

    private void offerSwordRecipe(Consumer<RecipeJsonProvider> exporter, String name, Item mat) {
        Item sword = getItem("moreores", name + "_sword");
        if (sword == null) return;
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, sword)
                .pattern("I").pattern("I").pattern("S")
                .input('I', mat).input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                .offerTo(exporter, new Identifier("moreores", name + "_sword"));
    }

    private void offerDaggerRecipe(Consumer<RecipeJsonProvider> exporter, String name, Item mat) {
        Item dagger = getItem("moreores", name + "_dagger");
        if (dagger == null) return;
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, dagger)
                .pattern("I").pattern("S")
                .input('I', mat).input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                .offerTo(exporter, new Identifier("moreores", name + "_dagger"));
    }

    private void offerArmorRecipes(Consumer<RecipeJsonProvider> exporter, String name, Item mat) {
        Item helmet = getItem("moreores", name + "_helmet");
        Item chestplate = getItem("moreores", name + "_chestplate");
        Item leggings = getItem("moreores", name + "_leggings");
        Item boots = getItem("moreores", name + "_boots");

        if (helmet != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, helmet)
                    .pattern("III").pattern("I I")
                    .input('I', mat)
                    .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                    .offerTo(exporter, new Identifier("moreores", name + "_helmet"));
        }
        if (chestplate != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, chestplate)
                    .pattern("I I").pattern("III").pattern("III")
                    .input('I', mat)
                    .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                    .offerTo(exporter, new Identifier("moreores", name + "_chestplate"));
        }
        if (leggings != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, leggings)
                    .pattern("III").pattern("I I").pattern("I I")
                    .input('I', mat)
                    .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                    .offerTo(exporter, new Identifier("moreores", name + "_leggings"));
        }
        if (boots != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, boots)
                    .pattern("I I").pattern("I I")
                    .input('I', mat)
                    .criterion(FabricRecipeProvider.hasItem(mat), FabricRecipeProvider.conditionsFromItem(mat))
                    .offerTo(exporter, new Identifier("moreores", name + "_boots"));
        }
    }

    private Item getItem(String namespace, String path) {
        Identifier id = new Identifier(namespace, path);
        if (Registries.ITEM.containsId(id)) {
            return Registries.ITEM.get(id);
        }
        return null;
    }
}
