package com.bumppo109.firmaciv_firmacompat.moonlight;

import com.alekiponi.firmaciv.Firmaciv;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivBlocks;
import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import com.bumppo109.firmaciv_firmacompat.addon.CompatWatercraftMaterial;
import com.mojang.logging.LogUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

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
        //Blockstate & Model Generation
            //Canoe
            StaticResource canoeBlockstate = StaticResource.getOrLog(manager,
                    ResType.BLOCKSTATES.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch")));
            StaticResource canoe0 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/0")));
            StaticResource canoe1 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/1")));
            StaticResource canoe2 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/2")));
            StaticResource canoe3 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/3")));
            StaticResource canoe4 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/4")));
            StaticResource canoe5 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/5")));
            StaticResource canoe6 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/6")));
            StaticResource canoe7 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/all/7")));

            StaticResource canoeEnd8 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/end/8")));
            StaticResource canoeEnd9 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/end/9")));
            StaticResource canoeEnd10 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/end/10")));
            StaticResource canoeEnd11 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/end/11")));
            StaticResource canoeEnd12 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/end/12")));

            StaticResource canoeMiddle8 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/middle/8")));
            StaticResource canoeMiddle9 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/middle/9")));
            StaticResource canoeMiddle10 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/middle/10")));
            StaticResource canoeMiddle11 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/middle/11")));
            StaticResource canoeMiddle12 = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/canoe_component_block/firma_compat/birch/middle/12")));

            CompatFirmaCivBlocks.getCanoeComponentBlocks().forEach((material, ro) -> {
                if (material instanceof CompatWatercraftMaterial) return;
                //CanoeComponentBlock block = ro.get();
                //String blockId = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString();

                try {
                    addSimilarJsonResource(manager, canoeBlockstate, "birch", material.getSerializedName());
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Canoe Component blockstate definition for {} : {}", material, ex);
                }

                ResourceLocation strippedLogTexture = material.getStrippedLogTexture();
                ResourceLocation strippedLogTopTexture = material.getStrippedLogTopTexture();

                try {
                    addCanoeComponentModel(Objects.requireNonNull(canoe0), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe1), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe2), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe3), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe4), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe5), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe6), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoe7), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle8), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle9), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle10), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle11), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeMiddle12), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd8), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd9), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd10), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd11), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                    addCanoeComponentModel(Objects.requireNonNull(canoeEnd12), material.getSerializedName(), strippedLogTexture, strippedLogTopTexture);
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Canoe Component model for {} : {}", material, ex);
                }
            });

            //Flat
            StaticResource flatBlockState = StaticResource.getOrLog(manager,
                    ResType.BLOCKSTATES.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/flat/firma_compat/cherry")));
            StaticResource first = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/flat/firma_compat/cherry/first")));
            StaticResource second = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/flat/firma_compat/cherry/second")));
            StaticResource third = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/flat/firma_compat/cherry/third")));
            StaticResource fourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/flat/firma_compat/cherry/fourth")));

            CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().forEach((material, ro) -> {
                if (material instanceof CompatWatercraftMaterial) return;

                try {
                    addSimilarJsonResource(manager, flatBlockState, "cherry", material.getSerializedName());
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Flat Watercraft Frame blockstate definition for {} : {}", material, ex);
                }

                ResourceLocation planksTexture = material.getPlanksTexture();

                try {
                    addWatercraftFrameModel(Objects.requireNonNull(first), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(second), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(third), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(fourth), material.getSerializedName(), planksTexture);
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Flat Frame model for {} : {}", material, ex);
                }
            });

            //Angled
            StaticResource angledBlockState = StaticResource.getOrLog(manager,
                    ResType.BLOCKSTATES.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry")));
            StaticResource innerFirst = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/inner/first")));
            StaticResource innerSecond = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/inner/second")));
            StaticResource innerThird = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/inner/third")));
            StaticResource innerFourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/inner/fourth")));
            StaticResource outerFirst = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/outer/first")));
            StaticResource outerSecond = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/outer/second")));
            StaticResource outerThird = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/outer/third")));
            StaticResource outerFourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/outer/fourth")));
            StaticResource straightFirst = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/straight/first")));
            StaticResource straightSecond = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/straight/second")));
            StaticResource straightThird = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/straight/third")));
            StaticResource straightFourth = StaticResource.getOrLog(manager,ResType.BLOCK_MODELS.getPath(new ResourceLocation("firmaciv_firmacompat", "wood/watercraft_frame/angled/firma_compat/cherry/straight/fourth")));

            CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().forEach((material, ro) -> {
                if (material instanceof CompatWatercraftMaterial) return;

                try {
                    addSimilarJsonResource(manager, angledBlockState, "cherry", material.getSerializedName());
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Angled Watercraft Frame blockstate definition for {} : {}", material, ex);
                }

                ResourceLocation planksTexture = material.getPlanksTexture();

                try {
                    addWatercraftFrameModel(Objects.requireNonNull(innerFirst), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(innerSecond), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(innerThird), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(innerFourth), material.getSerializedName(), planksTexture);

                    addWatercraftFrameModel(Objects.requireNonNull(outerFirst), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(outerSecond), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(outerThird), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(outerFourth), material.getSerializedName(), planksTexture);

                    addWatercraftFrameModel(Objects.requireNonNull(straightFirst), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(straightSecond), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(straightThird), material.getSerializedName(), planksTexture);
                    addWatercraftFrameModel(Objects.requireNonNull(straightFourth), material.getSerializedName(), planksTexture);
                } catch (Exception ex) {
                    getLogger().error("Failed to generate Angled Frame model for {} : {}", material, ex);
                }
            });

        //Watercraft Entity Textures
            for (BoatVariant entity : BoatVariant.values()) {

                String woodName = wood.getSerializedName();

                //Base textures
                ResourceLocation baseTexture = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID,"entity/template/watercraft/" + entity.path);
                ResourceLocation overlayTexture = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID,"entity/template/watercraft/" + entity.path + "_overlay");

                //Output Location
                ResourceLocation outputLoc = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID,
                        "entity/watercraft/" + entity.path + "/" + wood.getNamespace() + "/" + woodName + "/" + woodName);

                try (TextureImage base = TextureImage.open(manager, baseTexture)) {

                    Respriter respriter = Respriter.of(base);

                    try (TextureImage woodTexture = TextureImage.open(manager, wood.getPlanksTexture())) {

                        Palette woodPalette = Palette.fromImage(woodTexture);

                        TextureImage recoloredImage = respriter.recolor(woodPalette);

                        // ✅ Apply overlay ONLY for these types
                        if (entity != BoatVariant.DUGOUT_CANOE) {
                            try (TextureImage overlay = TextureImage.open(manager, overlayTexture)) {
                                recoloredImage.applyOverlayOnExisting(overlay);
                            } catch (Exception e) {
                                LOGGER.warn("Missing overlay for {}", entity.path);
                            }
                        }

                        // ✅ Save
                        byte[] bytes = recoloredImage.getImage().asByteArray();
                        CompatDynamicTextures.INSTANCE.addBytes(outputLoc, bytes, ResType.TEXTURES);

                        //Paint Variants
                        if(entity != BoatVariant.DUGOUT_CANOE) {
                            for (DyeColor color : DyeColor.values()) {
                                String name = color.getSerializedName(); // e.g. "white", "light_blue", etc.

                                ResourceLocation paintOutputLoc = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID,
                                        "entity/watercraft/" + entity.path + "/" + wood.getNamespace() + "/" + woodName + "/" + name);

                                ResourceLocation colorOverlay = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID,
                                        "entity/template/watercraft/paint/" + entity.path + "/" + name);

                                try (TextureImage colorOverlayImage = TextureImage.open(manager, colorOverlay)) {
                                    TextureImage colorVariant = recoloredImage.makeCopy();

                                    colorVariant.applyOverlayOnExisting(colorOverlayImage);

                                    byte[] colorBytes = colorVariant.getImage().asByteArray();
                                    CompatDynamicTextures.INSTANCE.addBytes(paintOutputLoc, colorBytes, ResType.TEXTURES);

                                    colorVariant.close();
                                } catch (Exception e) {
                                    LOGGER.warn("Missing color overlay for {}, {}", entity, color);
                                }
                            }
                        }

                        recoloredImage.close();
                    }
                } catch (Exception e) {
                    FirmaCivFirmaCompat.LOGGER.error("Failed for {} {}", entity.path, woodName, e);
                }
            }
        }
    }

    public void addCanoeComponentModel(StaticResource resource, String woodName, ResourceLocation strippedLogTexture, ResourceLocation strippedLogTopTexture) {
        String string = new String(resource.data, StandardCharsets.UTF_8);

        String path = resource.location.getPath().replace("birch", woodName);

        string = string.replace("minecraft:block/stripped_birch_log", strippedLogTexture.toString());
        string = string.replace("minecraft:block/stripped_birch_log_top", strippedLogTopTexture.toString());

        //adds modified under my namespace
        ResourceLocation newRes = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID, path);
        dynamicPack.addBytes(newRes, string.getBytes(), ResType.GENERIC);
    }

    public void addWatercraftFrameModel(StaticResource resource, String woodName, ResourceLocation planksTexture) {
        String string = new String(resource.data, StandardCharsets.UTF_8);

        String path = resource.location.getPath().replace("cherry", woodName);

        string = string.replace("minecraft:block/cherry_planks", planksTexture.toString());

        //adds modified under my namespace
        ResourceLocation newRes = new ResourceLocation(FirmaCivFirmaCompat.MOD_ID, path);
        dynamicPack.addBytes(newRes, string.getBytes(), ResType.GENERIC);
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