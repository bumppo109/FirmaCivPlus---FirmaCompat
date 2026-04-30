package com.bumppo109.firmaciv_firmacompat.moonlight;

import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import com.mojang.logging.LogUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;

import java.util.List;

public class CompatDynamicResourcesHandler extends DynClientResourcesGenerator {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CompatDynamicResourcesHandler INSTANCE =
            new CompatDynamicResourcesHandler();

    public CompatDynamicResourcesHandler() {
        super(CompatDynamicTextures.INSTANCE);
    }

    @Override
    public org.apache.logging.log4j.Logger getLogger() {
        return null;
    }

    enum BoatVariant {
        DUGOUT_CANOE("dugout_canoe"),
        ROWBOAT("rowboat"),
        SLOOP("sloop"),
        SLOOP_CONSTRUCTION("sloop_construction");

        public final String path;

        BoatVariant(String path) {
            this.path = path;
        }
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {

        for (ModWatercraftMaterial wood : ModWatercraftMaterial._ALL_WATERCRAFT_MATERIALS) {

            for (BoatVariant entity : BoatVariant.values()) {

                String woodName = wood.getSerializedName();

                // ✅ Base texture (NOTE: includes filename now)
                ResourceLocation baseTexture = new ResourceLocation(
                        FirmaCivFirmaCompat.MOD_ID,
                        "entity/template/watercraft/" + entity.path
                );

                // ✅ Overlay texture (only used for some variants)
                ResourceLocation overlayTexture = new ResourceLocation(
                        FirmaCivFirmaCompat.MOD_ID,
                        "entity/template/watercraft/" + entity.path + "_overlay"
                );

                // ✅ Output location
                ResourceLocation outputLoc = new ResourceLocation(
                        FirmaCivFirmaCompat.MOD_ID,
                        "entity/watercraft/" + entity.path + "/" +
                                wood.getNamespace() + "/" + woodName
                );

                try (TextureImage base = TextureImage.open(manager, baseTexture)) {

                    // ✅ Work on a copy
                    TextureImage result = base.makeCopy();

                    // ✅ Recolor
                    int tintColor = getAverageColorFromTexture(manager, wood.getPlanksTexture());
                    applyMultiplyTint(result, tintColor);

                    // ✅ Apply overlay ONLY for these types
                    if (entity != BoatVariant.DUGOUT_CANOE) {
                        try (TextureImage overlay = TextureImage.open(manager, overlayTexture)) {
                            result.applyOverlayOnExisting(overlay);
                        } catch (Exception e) {
                            LOGGER.warn("Missing overlay for {}", entity.path);
                        }
                    }

                    // ✅ Save
                    byte[] bytes = result.getImage().asByteArray();
                    CompatDynamicTextures.INSTANCE.addBytes(outputLoc, bytes, ResType.TEXTURES);

                    result.close();

                } catch (Exception e) {
                    FirmaCivFirmaCompat.LOGGER.error(
                            "Failed for {} {}",
                            entity.path,
                            woodName,
                            e
                    );
                }
            }
        }
    }

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

    private static int getAverageColorFromTexture(ResourceManager manager, ResourceLocation textureLoc) {
        try (TextureImage img = TextureImage.open(manager, textureLoc)) {
            long sumR = 0, sumG = 0, sumB = 0;
            int count = 0;

            for (int x = 0; x < img.imageWidth(); x++) {
                for (int y = 0; y < img.imageHeight(); y++) {
                    int color = img.getPixel(x, y);
                    int a = (color >>> 24) & 0xFF;
                    if (a < 50) continue;

                    sumR += (color >> 16) & 0xFF;
                    sumG += (color >> 8) & 0xFF;
                    sumB += color & 0xFF;
                    count++;
                }
            }

            if (count == 0) return 0x8B5A2B;

            int avgR = (int) (sumR / count);
            int avgG = (int) (sumG / count);
            int avgB = (int) (sumB / count);

            return (0xFF << 24) | (avgR << 16) | (avgG << 8) | avgB;
        } catch (Exception e) {
            return 0xA67C5D;
        }
    }
}