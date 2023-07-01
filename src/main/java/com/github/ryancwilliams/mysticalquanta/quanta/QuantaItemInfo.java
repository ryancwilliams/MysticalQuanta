package com.github.ryancwilliams.mysticalquanta.quanta;

import lombok.Data;
import net.minecraft.world.item.Item;
import org.checkerframework.checker.nullness.qual.NonNull;

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
