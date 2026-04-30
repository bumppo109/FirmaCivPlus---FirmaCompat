package com.bumppo109.firmaciv_firmacompat;

import com.bumppo109.firmaciv_firmacompat.addon.CompatWatercraftMaterial;
import com.bumppo109.firmaciv_firmacompat.moonlight.CompatDynamicTextures;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.CompletableFuture;

public class CompatFirmaCivClientEvents {

    static void init(IEventBus evtBus) {
        evtBus.addListener(CompatFirmaCivClientEvents::onRegisterReloadListeners);
    }

    @SubscribeEvent
    private static void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
        CompatDynamicTextures.INSTANCE.register();

        event.registerReloadListener((stage, resourceManager, prep, reload, background, gameExecutor) ->
                CompletableFuture.runAsync(() -> generateDynamicTextures(resourceManager), gameExecutor)
        );
    }

    private static void generateDynamicTextures(ResourceManager manager) {
        for (ModWatercraftMaterial wood : ModWatercraftMaterial._ALL_WATERCRAFT_MATERIALS) {
            try {
                generateLumberTexture(manager, wood);
            } catch (Exception e) {
                System.err.println("Failed to generate dynamic lumber texture for: " + wood.getSerializedName());
                e.printStackTrace();
            }
        }
    }

    private static void generateLumberTexture(ResourceManager manager, ModWatercraftMaterial wood) {
        String name = wood.getSerializedName();

        // Base texture (usually a neutral TFC lumber or a generic wood texture)
        ResourceLocation baseTexture = new ResourceLocation("tfc", "block/lumber/oak");   // Change base if desired

        // Output location
        ResourceLocation outputLoc = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID, "item/" + name + "_lumber");

        try (TextureImage base = TextureImage.open(manager, baseTexture)) {

            TextureImage result = base.makeCopy();

            // Get tint color from the wood's planks texture (smart way)
            ResourceLocation referenceTexture = wood.getPlanksTexture();   // Uses your existing method
            int tintColor = getAverageColorFromTexture(manager, referenceTexture);

            // Apply tint
            applyMultiplyTint(result, tintColor);

            // Save to dynamic pack
            byte[] bytes = result.getImage().asByteArray();
            CompatDynamicTextures.INSTANCE.addBytes(outputLoc, bytes, ResType.TEXTURES);

            result.close();

        } catch (Exception e) {
            System.err.println("Could not create dynamic lumber for " + name);
        }
    }

    // ====================== TINTING ======================
    private static void applyMultiplyTint(TextureImage image, int tintColor) {
        int tr = (tintColor >> 16) & 0xFF;
        int tg = (tintColor >> 8) & 0xFF;
        int tb = tintColor & 0xFF;

        image.forEachPixel(pixel -> {
            int color = pixel.getValue();
            int a = (color >>> 24) & 0xFF;
            if (a == 0) return;

            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = color & 0xFF;

            r = (r * tr) / 255;
            g = (g * tg) / 255;
            b = (b * tb) / 255;

            pixel.setValue((a << 24) | (r << 16) | (g << 8) | b);
        });
    }

    // Get average color from a texture (used as tint)
    private static int getAverageColorFromTexture(ResourceManager manager, ResourceLocation textureLoc) {
        try (TextureImage img = TextureImage.open(manager, textureLoc)) {
            long sumR = 0, sumG = 0, sumB = 0;
            int count = 0;

            for (int x = 0; x < img.imageWidth(); x++) {
                for (int y = 0; y < img.imageHeight(); y++) {
                    int color = img.getPixel(x, y);
                    int a = (color >>> 24) & 0xFF;
                    if (a < 50) continue; // skip very transparent pixels

                    sumR += (color >> 16) & 0xFF;
                    sumG += (color >> 8) & 0xFF;
                    sumB += color & 0xFF;
                    count++;
                }
            }

            if (count == 0) return 0x8B5A2B; // fallback brown

            int avgR = (int) (sumR / count);
            int avgG = (int) (sumG / count);
            int avgB = (int) (sumB / count);

            return (0xFF << 24) | (avgR << 16) | (avgG << 8) | avgB;
        } catch (Exception e) {
            return 0xA67C5D; // oak fallback
        }
    }
}