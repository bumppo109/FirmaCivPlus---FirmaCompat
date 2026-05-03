package com.bumppo109.firmaciv_firmacompat.moonlight;

import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivBlocks;
import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import com.bumppo109.firmaciv_firmacompat.addon.CompatWatercraftMaterial;
import com.bumppo109.firmaciv_firmacompat.addon.NatureSpiritWatercraftMaterial;
import com.mojang.logging.LogUtils;
import dev.architectury.platform.Mod;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.DyeColor;
import org.slf4j.Logger;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientDynamicResourcesHandler extends DynClientResourcesGenerator {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final ClientDynamicResourcesHandler INSTANCE =
            new ClientDynamicResourcesHandler();

    public ClientDynamicResourcesHandler() {
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
        //Blockstate & Model Generation
            //Canoe
            StaticResource canoeBlockstate = StaticResource.getOrLog(manager,
                    ResType.BLOCKSTATES.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch")));
            StaticResource canoe0 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/0")));
            StaticResource canoe1 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/1")));
            StaticResource canoe2 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/2")));
            StaticResource canoe3 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/3")));
            StaticResource canoe4 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/4")));
            StaticResource canoe5 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/5")));
            StaticResource canoe6 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/6")));
            StaticResource canoe7 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/all/7")));

            StaticResource canoeEnd8 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/end/8")));
            StaticResource canoeEnd9 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/end/9")));
            StaticResource canoeEnd10 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/end/10")));
            StaticResource canoeEnd11 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/end/11")));
            StaticResource canoeEnd12 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/end/12")));

            StaticResource canoeMiddle8 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/middle/8")));
            StaticResource canoeMiddle9 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/middle/9")));
            StaticResource canoeMiddle10 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/middle/10")));
            StaticResource canoeMiddle11 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/middle/11")));
            StaticResource canoeMiddle12 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/canoe_component_block/minecraft/birch/middle/12")));

            CompatFirmaCivBlocks.getCanoeComponentBlocks().forEach((material, ro) -> {
                if (material instanceof CompatWatercraftMaterial) return;

                try {
                    assert canoeBlockstate != null;
                    addBlockStateFile(canoeBlockstate, "birch", wood.getSerializedName(), wood.getNamespace());
                    //addSimilarJsonResource(manager, canoeBlockstate, "birch", material.getSerializedName());
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Canoe Component blockstate definition for {} : {}", material, ex);
                }

                ResourceLocation strippedLogTexture = material.getStrippedLogTexture();
                ResourceLocation strippedLogTopTexture = material.getStrippedLogTopTexture();

                try {
                    addCanoeComponentModel(Objects.requireNonNull(canoe0), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe1), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe2), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe3), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe4), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe5), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe6), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe7), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle8), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle9), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle10), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle11), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle12), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd8), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd9), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd10), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd11), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd12), material.getSerializedName(), material.getNamespace(), strippedLogTexture, strippedLogTopTexture);
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Canoe Component model for {} : {}", material, ex);
                }
            });

            //Flat
            StaticResource flatBlockState = StaticResource.getOrLog(manager,
                    ResType.BLOCKSTATES.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/flat/minecraft/cherry")));
            StaticResource first = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/flat/minecraft/cherry/first")));
            StaticResource second = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/flat/minecraft/cherry/second")));
            StaticResource third = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/flat/minecraft/cherry/third")));
            StaticResource fourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/flat/minecraft/cherry/fourth")));

            CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().forEach((material, ro) -> {
                if (material instanceof CompatWatercraftMaterial) return;

                try {
                    assert flatBlockState != null;
                    addBlockStateFile(flatBlockState, "cherry", wood.getSerializedName(), wood.getNamespace());
                    //addSimilarJsonResource(manager, flatBlockState, "cherry", material.getSerializedName());
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Flat Watercraft Frame blockstate definition for {} : {}", material, ex);
                }

                ResourceLocation planksTexture = material.getPlanksTexture();

                try {
                    addWatercraftFrameModel(Objects.requireNonNull(first), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(second), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(third), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(fourth), material.getSerializedName(), material.getNamespace(), planksTexture);
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Flat Frame model for {} : {}", material, ex);
                }
            });

            //Angled
            StaticResource angledBlockState = StaticResource.getOrLog(manager,
                    ResType.BLOCKSTATES.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry")));
            StaticResource innerFirst = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/inner/first")));
            StaticResource innerSecond = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/inner/second")));
            StaticResource innerThird = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/inner/third")));
            StaticResource innerFourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/inner/fourth")));
            StaticResource outerFirst = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/outer/first")));
            StaticResource outerSecond = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/outer/second")));
            StaticResource outerThird = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/outer/third")));
            StaticResource outerFourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/outer/fourth")));
            StaticResource straightFirst = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/straight/first")));
            StaticResource straightSecond = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/straight/second")));
            StaticResource straightThird = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/straight/third")));
            StaticResource straightFourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(ResourceLocation.fromNamespaceAndPath("firmaciv_firmacompat", "wood/watercraft_frame/angled/minecraft/cherry/straight/fourth")));

            CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().forEach((material, ro) -> {
                if (material instanceof CompatWatercraftMaterial) return;

                try {
                    assert angledBlockState != null;
                    addBlockStateFile(angledBlockState, "cherry", wood.getSerializedName(), wood.getNamespace());
                    //addSimilarJsonResource(manager, angledBlockState, "cherry", material.getSerializedName());
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Angled Watercraft Frame blockstate definition for {} : {}", material, ex);
                }

                ResourceLocation planksTexture = material.getPlanksTexture();

                try {
                    addWatercraftFrameModel(Objects.requireNonNull(innerFirst), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(innerSecond), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(innerThird), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(innerFourth), material.getSerializedName(), material.getNamespace(), planksTexture);

                    addWatercraftFrameModel(Objects.requireNonNull(outerFirst), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(outerSecond), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(outerThird), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(outerFourth), material.getSerializedName(), material.getNamespace(), planksTexture);

                    addWatercraftFrameModel(Objects.requireNonNull(straightFirst), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(straightSecond), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(straightThird), material.getSerializedName(), material.getNamespace(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(straightFourth), material.getSerializedName(), material.getNamespace(), planksTexture);
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Angled Frame model for {} : {}", material, ex);
                }
            });

            /** Watercraft Entity Textures
             *  only used when adding new mod support
             */
            //generateBoatVariantTextures(manager, wood);
        }
    }

    public void addCanoeComponentModel(StaticResource resource, String woodName, String woodNamespace, ResourceLocation strippedLogTexture, ResourceLocation strippedLogTopTexture) {
        String string = new String(resource.data, StandardCharsets.UTF_8);

        String pathTemp = resource.location.getPath().replace("birch", woodName);
        String path = pathTemp.replace("minecraft", woodNamespace);

        string = string.replace("minecraft:block/stripped_birch_log", strippedLogTexture.toString());
        string = string.replace("minecraft:block/stripped_birch_log_top", strippedLogTopTexture.toString());

        //adds modified under my namespace
        ResourceLocation newRes = ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID, path);
        dynamicPack.addBytes(newRes, string.getBytes(), ResType.GENERIC);
    }

    public void addWatercraftFrameModel(StaticResource resource, String woodName, String woodNamespace, ResourceLocation planksTexture) {
        String string = new String(resource.data, StandardCharsets.UTF_8);

        String pathTemp = resource.location.getPath().replace("cherry", woodName);
        String path = pathTemp.replace("minecraft", woodNamespace);

        string = string.replace("minecraft:block/cherry_planks", planksTexture.toString());

        //adds modified under my namespace
        ResourceLocation newRes = ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID, path);
        dynamicPack.addBytes(newRes, string.getBytes(), ResType.GENERIC);
    }

    public void addBlockStateFile(StaticResource resource, String replaceWood, String woodName, String woodNamespace) {
        String string = new String(resource.data, StandardCharsets.UTF_8);

        String pathTemp = resource.location.getPath().replace(replaceWood, woodName);
        String path = pathTemp.replace("minecraft", woodNamespace);

        string = string.replace(replaceWood, woodName);
        string = string.replace("minecraft", woodNamespace);

        //adds modified under my namespace
        ResourceLocation newRes = ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID, path);
        dynamicPack.addBytes(newRes, string.getBytes(), ResType.GENERIC);
    }

    private static float rgbToHue(int r, int g, int b) {
        return java.awt.Color.RGBtoHSB(r, g, b, null)[0];
    }

    private static void generateBoatVariantTextures(ResourceManager manager, ModWatercraftMaterial wood) {
        for (BoatVariant entity : BoatVariant.values()) {

            //TODO - some colors still washed out (dark oak looks like jungle)
            ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(
                    FirmaCivFirmaCompat.MOD_ID,
                    "entity/template/watercraft/" + entity.path
            );

            ResourceLocation overlayTexture = ResourceLocation.fromNamespaceAndPath(
                    FirmaCivFirmaCompat.MOD_ID,
                    "entity/template/watercraft/" + entity.path + "_overlay"
            );

            ResourceLocation outputLoc = ResourceLocation.fromNamespaceAndPath(
                    FirmaCivFirmaCompat.MOD_ID,
                    "entity/watercraft/" + entity.path + "/" +
                            wood.getNamespace() + "/" + wood.getSerializedName() + "/" + wood.getSerializedName()
            );

            try (TextureImage base = TextureImage.open(manager, baseTexture);
                 TextureImage woodTex = TextureImage.open(manager, wood.getPlanksTexture())) {

                Palette palette = Palette.fromImage(woodTex);
                palette.matchSize(16, null);

                float[] paletteHues = precomputePaletteHues(palette);

                base.forEachPixel(p -> {
                    int argb = p.getValue();

                    int a = (argb >> 24) & 255;
                    if (a == 0) return; // respect transparency properly

                    int r = argb & 255;
                    int g = (argb >> 8) & 255;
                    int b = (argb >> 16) & 255;

                    // grayscale luminance from base texture (since you made it grayscale, r≈g≈b)
                    float brightness = (r + g + b) / (3.0f * 255.0f);

                    float hue = rgbToHue(r, g, b);

                    int idx = nearestHueIndex(hue, paletteHues);

                    HCLColor paletteColor = palette.get(idx).hcl();

                    // IMPORTANT: rebuild color preserving luminance from base
                    HCLColor shaded = new HCLColor(
                            paletteColor.hue(),
                            paletteColor.chroma(),
                            brightness,              // <-- THIS is what restores shading
                            paletteColor.alpha()
                    );

                    int rgb = shaded.asRGB().toInt();

                    // reapply original alpha correctly
                    p.setValue((a << 24) | (rgb & 0x00FFFFFF));
                });

                // overlay
                if (entity != BoatVariant.DUGOUT_CANOE) {

                    try (TextureImage overlay = TextureImage.open(manager, overlayTexture)) {
                        base.applyOverlayOnExisting(overlay);
                    } catch (Exception e) {
                        LOGGER.warn("Missing overlay for {}", entity.path);
                    }
                }

                byte[] bytes = base.getImage().asByteArray();
                CompatDynamicTextures.INSTANCE.addBytes(outputLoc, bytes, ResType.TEXTURES);

                // =========================
                // DYED VARIANTS
                // =========================

                if (entity != BoatVariant.DUGOUT_CANOE &&
                        entity != BoatVariant.SLOOP_CONSTRUCTION) {

                    for (DyeColor color : DyeColor.values()) {

                        String name = color.getSerializedName();

                        ResourceLocation paintOutputLoc = ResourceLocation.fromNamespaceAndPath(
                                FirmaCivFirmaCompat.MOD_ID,
                                "entity/watercraft/" + entity.path + "/" +
                                        wood.getNamespace() + "/" + wood.getSerializedName() + "/" + name
                        );

                        ResourceLocation overlayCol = ResourceLocation.fromNamespaceAndPath(
                                FirmaCivFirmaCompat.MOD_ID,
                                "entity/template/watercraft/paint/" + entity.path + "/" + name
                        );

                        try (TextureImage colOverlay = TextureImage.open(manager, overlayCol)) {

                            TextureImage variant = base.makeCopy();
                            variant.applyOverlayOnExisting(colOverlay);

                            CompatDynamicTextures.INSTANCE.addBytes(
                                    paintOutputLoc,
                                    variant.getImage().asByteArray(),
                                    ResType.TEXTURES
                            );

                            variant.close();

                        } catch (Exception e) {
                            LOGGER.warn("Missing dye overlay {}, {}", entity, color);
                        }
                    }
                }

            } catch (Exception e) {
                LOGGER.error("Failed generating {} {}", entity.path, wood.getSerializedName(), e);
            }
        }
    }

    private static float[] precomputePaletteHues(Palette palette) {
        float[] hues = new float[palette.size()];

        for (int i = 0; i < palette.size(); i++) {
            int rgb = palette.get(i).value();
            hues[i] = rgbToHue(rgb & 255, (rgb >> 8) & 255, (rgb >> 16) & 255);
        }

        return hues;
    }

    // Circular hue distance (handles wraparound correctly)
    private static float hueDistance(float a, float b) {
        float d = Math.abs(a - b);
        return Math.min(d, 1.0f - d);
    }

    // Nearest palette index by hue only
    private static int nearestHueIndex(float hue, float[] paletteHues) {
        int best = 0;
        float bestDist = Float.MAX_VALUE;

        for (int i = 0; i < paletteHues.length; i++) {
            float d = hueDistance(hue, paletteHues[i]);
            if (d < bestDist) {
                bestDist = d;
                best = i;
            }
        }

        return best;
    }
}