package ISTORE.istore;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Istore implements ModInitializer {
    public static final String MOD_ID = "istore";

    private static final Identifier ID = Identifier.fromNamespaceAndPath(MOD_ID, "storage_display");
    private static final ResourceKey<Block> BLOCK_KEY = ResourceKey.create(Registries.BLOCK, ID);
    private static final ResourceKey<Item> ITEM_KEY = ResourceKey.create(Registries.ITEM, ID);

    // Copper block properties; the registry key must be set on the properties before construction.
    public static final Block STORAGE_DISPLAY = new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).setId(BLOCK_KEY));

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.BLOCK, ID, STORAGE_DISPLAY);
        Registry.register(BuiltInRegistries.ITEM, ID,
                new BlockItem(STORAGE_DISPLAY, new Item.Properties().setId(ITEM_KEY).useBlockDescriptionPrefix()));
    }
}
