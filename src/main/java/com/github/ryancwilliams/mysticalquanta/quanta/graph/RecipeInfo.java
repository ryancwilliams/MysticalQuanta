package com.github.ryancwilliams.mysticalquanta.quanta.graph;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Info on a given recipe or process.
 */
@Data
public class RecipeInfo {

	/**
	 * A list of inputs for this recipe or process.
	 */
	@Getter
	@NonNull
	private final Set<RecipeAssetInfo> inputs;

	/**
	 * A list of outputs for this recipe or process.
	 */
	@Getter
	@NonNull
	private final Set<RecipeAssetInfo> outputs;

	/**
	 * Creates a copy of the provided {@link RecipeInfo}.
	 * <p>
	 * This does NOT copy the {@link RecipeAssetInfo} that are inside the sets.
	 *
	 * @param recipe The recipe to copy.
	 */
	public RecipeInfo(@NonNull RecipeInfo recipe) {
		this.inputs = new HashSet<>(recipe.inputs);
		this.outputs = new HashSet<>(recipe.outputs);
	}

}
