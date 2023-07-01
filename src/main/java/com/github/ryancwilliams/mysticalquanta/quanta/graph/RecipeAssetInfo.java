package com.github.ryancwilliams.mysticalquanta.quanta.graph;

import com.github.ryancwilliams.mysticalquanta.quanta.QuantaAssetInfo;

/**
 * Info on a given input or output from a {@link RecipeInfo}.
 */
public interface RecipeAssetInfo {

	/**
	 * Gets the {@link QuantaAssetInfo} that is in this {@link RecipeAssetInfo}.
	 * @return The Quanta Asset that is in this Recipe Asset.
	 */
	public QuantaAssetInfo getQuantaAsset();

	/**
	 * The number of {@link QuantaAssetInfo} that is in this {@link RecipeAssetInfo}.
	 * @return The number of Quanta Assets that is in this Recipe Asset.
	 */
	public int getQuantity();

}
