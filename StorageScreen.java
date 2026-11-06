package ISTORE.istore;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageScreen extends Screen {
    private EditBox searchBox;
    private final List<ItemStack> allItems = new ArrayList<>();
    private List<ItemStack> filteredItems = new ArrayList<>();

    public StorageScreen() {
        super(Component.literal("iStore Storage"));

        // PLACEHOLDER DATA: later, read this from the Marker NBT
        allItems.add(new ItemStack(Items.COPPER_INGOT, 512));
        allItems.add(new ItemStack(Items.IRON_INGOT, 64));
        allItems.add(new ItemStack(Items.DIAMOND, 12));
        allItems.add(new ItemStack(Items.GOLD_INGOT, 128));

        updateFilteredItems("");
    }

    @Override
    protected void init() {
        this.searchBox = new EditBox(this.font, this.width / 2 - 100, 20, 200, 20, Component.literal("Search items..."));
        this.searchBox.setHint(Component.literal("Search items..."));
        this.searchBox.setResponder(this::updateFilteredItems);
        this.addRenderableWidget(this.searchBox);
        this.setInitialFocus(this.searchBox);
    }

    private void updateFilteredItems(String query) {
        String q = query.toLowerCase();
        filteredItems = allItems.stream()
                .filter(stack -> stack.getHoverName().getString().toLowerCase().contains(q))
                .sorted((a, b) -> Integer.compare(b.getCount(), a.getCount())) // highest count first
                .collect(Collectors.toList());
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        // Draws the background and the search box
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);

        int x = this.width / 2 - 100;
        int y = 50;

        for (ItemStack stack : filteredItems) {
            graphics.item(stack, x, y);
            String label = stack.getHoverName().getString() + " x" + stack.getCount();
            graphics.text(this.font, label, x + 25, y + 5, 0xFFFFFFFF, false);
            y += 20;
            if (y > this.height - 20) break;
        }
    }

    // Click handling (export/import) goes here. In 26.x the signature is
    // mouseClicked(MouseButtonEvent event, boolean doubleClick).
    // On a Realm: Minecraft.getInstance().player.connection.sendCommand("trigger iStore_Export set 1");

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
