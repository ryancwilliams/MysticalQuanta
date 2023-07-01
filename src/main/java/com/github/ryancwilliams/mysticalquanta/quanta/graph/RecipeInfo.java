package com.github.ryancwilliams.mysticalquanta.quanta.graph;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

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

}
