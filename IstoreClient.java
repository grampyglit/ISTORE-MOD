package ISTORE.istore;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

public class IstoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Client-only: opening a Screen must never be referenced from the common entrypoint.
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (!level.isClientSide()) return InteractionResult.PASS;
            if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
            if (player.isShiftKeyDown()) return InteractionResult.PASS; // sneak-click to place blocks against it

            if (level.getBlockState(hitResult.getBlockPos()).is(Istore.STORAGE_DISPLAY)) {
                Minecraft.getInstance().setScreen(new StorageScreen());
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        });
    }
}
