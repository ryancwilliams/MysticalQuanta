package com.github.ryancwilliams.mysticalquanta.quanta.graph;

import com.github.ryancwilliams.mysticalquanta.quanta.QuantaAssetInfo;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores the {@link RecipeInfo} that are used in crafting graph to compute the Quanta values.
 */
@ToString
@NoArgsConstructor
public class QuantaRecipeStore {

	/**
	 * A set of all the known recipes.
	 */
	private final Set<RecipeInfo> recipes = new HashSet<>();

	/**
	 * A index for mapping the input {@link QuantaAssetInfo} to {@link RecipeInfo}.
	 */
	private final Map<QuantaAssetInfo, Set<RecipeInfo>> indexInputQuantaAssets = new HashMap<>();

	/**
	 * A index for mapping the output {@link QuantaAssetInfo} to {@link RecipeInfo}.
	 */
	private final Map<QuantaAssetInfo, Set<RecipeInfo>> indexOutputQuantaAssets = new HashMap<>();

	/**
	 * Adds the provided set of {@link RecipeInfo} to this store.
	 *
	 * @param recipes The recipes to add.
	 */
	public void addRecipes(@NonNull Set<RecipeInfo> recipes) {
		// For each recipe
		for (RecipeInfo recipe : recipes) {
			// Add it
			addRecipe(recipe);
		}
	}

	/**
	 * Adds the provided {@link RecipeInfo} to this store.
	 *
	 * @param recipe The recipe to add.
	 */
	public void addRecipe(@NonNull RecipeInfo recipe) {
		// Copy the recipe
		recipe = new RecipeInfo(recipe);
		// Add the recipe
		recipes.add(recipe);
		// Index the recipe
		addRecipeToIndexes(recipe);
	}

	/**
	 * Removes the provided {@link RecipeInfo} from this store.
	 *
	 * @param recipe The recipe to remove.
	 */
	public void removeRecipe(@NonNull RecipeInfo recipe) {
		// Remove the recipe
		boolean wasRemoved = recipes.remove(recipe);

		if (wasRemoved) {
			// Remove from the indexes
			removeRecipeFromIndexes(recipe);
		}
	}

	/**
	 * Gets a set of all stored {@link RecipeInfo}.
	 * @return A set of the recipes.
	 */
	public Set<RecipeInfo> getAllRecipes() {
		//Copy and return the set
		return new HashSet<>(recipes);
	}

	/**
	 * Gets the number of stored {@link RecipeInfo}.
	 * @return The number of recipes stored.
	 */
	public int getRecipeCount() {
		return recipes.size();
	}

	/**
	 * Gets a set of all stored {@link RecipeInfo} that have a given {@link QuantaAssetInfo} as an Input.
	 *
	 * @param asset The asset to get the recipes for.
	 * @return A set of the recipes.
	 */
	public Set<RecipeInfo> getRecipesForInput(@NonNull QuantaAssetInfo asset) {
		//Get the set
		Set<RecipeInfo> recipes = indexInputQuantaAssets.get(asset);

		//Copy and return the set
		return new HashSet<>(recipes);
	}

	/**
	 * Gets a set of all stored {@link RecipeInfo} that have a given {@link QuantaAssetInfo} as an Output.
	 *
	 * @param asset The asset to get the recipes for.
	 * @return A set of the recipes.
	 */
	public Set<RecipeInfo> getRecipesForOutput(@NonNull QuantaAssetInfo asset) {
		//Get the set
		Set<RecipeInfo> recipes = indexOutputQuantaAssets.get(asset);

		//Copy and return the set
		return new HashSet<>(recipes);
	}

	/**
	 * Adds the provided {@link RecipeInfo} to the indexes.
	 *
	 * @param recipe The recipe to add to the indexes.
	 */
	private void addRecipeToIndexes(@NonNull RecipeInfo recipe) {
		// Get the inputs and outputs of the recipe
		Set<RecipeAssetInfo> inputAssets = recipe.getInputs();
		Set<RecipeAssetInfo> outputAssets = recipe.getOutputs();

		//For each input
		for (RecipeAssetInfo asset : inputAssets) {
			QuantaAssetInfo quantaAsset = asset.getQuantaAsset();
			addRecipeToQuantaAssetsIndex(indexInputQuantaAssets, quantaAsset, recipe);
		}

		//For each output
		for (RecipeAssetInfo asset : outputAssets) {
			QuantaAssetInfo quantaAsset = asset.getQuantaAsset();
			addRecipeToQuantaAssetsIndex(indexOutputQuantaAssets, quantaAsset, recipe);
		}

	}

	/**
	 * Removes the provided {@link RecipeInfo} from the indexes.
	 *
	 * @param recipe The recipe to remove from the indexes.
	 */
	private void removeRecipeFromIndexes(@NonNull RecipeInfo recipe) {
		// Get the inputs and outputs of the recipe
		Set<RecipeAssetInfo> inputAssets = recipe.getInputs();
		Set<RecipeAssetInfo> outputAssets = recipe.getOutputs();

		//For each input
		for (RecipeAssetInfo asset : inputAssets) {
			QuantaAssetInfo quantaAsset = asset.getQuantaAsset();
			removeRecipeFromQuantaAssetsIndex(indexInputQuantaAssets, quantaAsset, recipe);
		}

		//For each output
		for (RecipeAssetInfo asset : outputAssets) {
			QuantaAssetInfo quantaAsset = asset.getQuantaAsset();
			removeRecipeFromQuantaAssetsIndex(indexOutputQuantaAssets, quantaAsset, recipe);
		}

	}

	/**
	 * Adds the provided {@link RecipeInfo} to the provided index under the provided {@link QuantaAssetInfo}.
	 *
	 * @param index  The index to add the provided recipe to.
	 * @param asset  The asset to index this under.
	 * @param recipe The recipe to add to the index.
	 */
	private void addRecipeToQuantaAssetsIndex(@NonNull Map<QuantaAssetInfo, Set<RecipeInfo>> index,
											  @NonNull QuantaAssetInfo asset,
											  @NonNull RecipeInfo recipe) {
		Set<RecipeInfo> indexEntry = index.get(asset);
		if (indexEntry == null) {
			// If there is no entry in the index for this asset
			// Create an entry
			indexEntry = new HashSet<>();
			// Add it to the index
			index.put(asset, indexEntry);
		}

		// Add the recipe to the index entry
		indexEntry.add(recipe);
	}

	/**
	 * Removes the provided {@link RecipeInfo} from the provided index under the provided {@link QuantaAssetInfo}.
	 *
	 * @param index  The index to remove the provided recipe from.
	 * @param asset  The asset to index this under.
	 * @param recipe The recipe to remove from the index.
	 */
	private void removeRecipeFromQuantaAssetsIndex(@NonNull Map<QuantaAssetInfo, Set<RecipeInfo>> index,
												   @NonNull QuantaAssetInfo asset,
												   @NonNull RecipeInfo recipe) {
		Set<RecipeInfo> indexEntry = index.get(asset);
		if (indexEntry == null) {
			// If there is no entry in the index for this asset
			// Nothing to remove
			return;
		}

		// Remove the recipe from the index entry
		indexEntry.remove(recipe);
	}

}
