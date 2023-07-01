package com.github.ryancwilliams.mysticalquanta.quanta;

import lombok.Data;
import lombok.NonNull;
import net.minecraft.world.item.Item;

/**
 * An Item that has a value in the Quanta System.
 */
@Data
public class QuantaItemInfo implements QuantaAssetInfo {

	/**
	 * The item this {@link QuantaItemInfo} is for.
	 */
	@NonNull
	private final Item item;

}
