package com.github.ryancwilliams.mysticalquanta.quanta;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The global value map for the Quanta System.
 * <p>
 * This contains the map of items to their global base Quanta Values. These values are constant for a give would and
 * should only be updated if the quanta values are manually updated or any mods that interact with the Quanta System are
 * updated.
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class QuantaGlobalValueMap {

	/**
	 * The singleton instance of this class.
	 */
	@Getter
	@Setter(AccessLevel.PACKAGE)
	@NonNull
	private static QuantaGlobalValueMap quantaValues = new QuantaGlobalValueMap();

	/**
	 * The map of the {@link QuantaAssetInfo} to the Quanta Values.
	 */
	private final Map<QuantaAssetInfo, Long> quantaValueMap = new HashMap<>();

	/**
	 * Gets the Quanta value of the provided {@link QuantaAssetInfo}.
	 * @param asset The asset to get the Quanta value for.
	 * @return The Quanta value of the asset.
	 */
	public long getQuantaValue(@NonNull QuantaAssetInfo asset) {

		Long quantaValue = quantaValueMap.get(asset);

		if (quantaValue == null) {
			//If the provided asset has no value
			//TODO change this
			quantaValue = 0L;
		}

		return quantaValue;
	}

	/**
	 * Gets the size of the Quanta Value Map.
	 * @return The size of the Quanta Value Map.
	 * This is the number of assets that currently have Quanta Values.
	 */
	public int getQuantaValueMapSize() {
		return quantaValueMap.size();
	}
}
