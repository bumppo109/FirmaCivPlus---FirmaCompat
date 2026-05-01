package com.bumppo109.firmaciv_firmacompat.datagen;

import com.alekiponi.alekiroofs.SquaredAngleBlock;
import com.alekiponi.alekiships.AlekiShips;
import com.alekiponi.alekiships.common.block.*;
import com.alekiponi.firmaciv.Firmaciv;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import com.bumppo109.firmaciv_firmacompat.CompatFirmaCivBlocks;
import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class FirmaCivPlusBlockStateProvider extends BlockStateProvider {

    private static final String[] BOAT_FRAME_PROGRESS_STRINGS = {"first", "second", "third", "fourth"};

    private static final Map<String, List<Integer>> CANOE_SECTION_TO_STEP_RANGE = Map.of(
            "all", List.of(0, 1, 2, 3, 4, 5, 6, 7),
            "end", List.of(8, 9, 10, 11, 12),
            "middle", List.of(8, 9, 10, 11, 12)
    );
    private static final Direction[] VALID_CANOE_DIRECTIONS = new Direction[]
            {
                    Direction.NORTH,
                    Direction.EAST,
                    Direction.SOUTH,
                    Direction.WEST
            };

    public FirmaCivPlusBlockStateProvider(final PackOutput output, final ExistingFileHelper existingFileHelper) {
        super(output, FirmaCivFirmaCompat.MOD_ID, existingFileHelper);
    }

    static BiConsumer<ModWatercraftMaterial, Supplier<? extends Block>> canoeComponentBlock(final FirmaCivPlusBlockStateProvider stateProvider)
    {
        return (wood, registryObject) ->
        {
            if(!wood.isSoftwood())
            {
                return;
            }

            String namespace = wood.getNamespace();
            var variantBlockStateBuilder = stateProvider.getVariantBuilder(registryObject.get());
            CANOE_SECTION_TO_STEP_RANGE.forEach((section, canoeCarvedSteps) ->
            {
                for(Direction validDirection : VALID_CANOE_DIRECTIONS)
                {
                    for(int step : canoeCarvedSteps)
                    {
                        //Canoe State Selectors
                        var whenFacingAndStep = variantBlockStateBuilder.partialState()
                                .with(CanoeComponentBlock.FACING, validDirection)
                                .with(CanoeComponentBlock.CANOE_CARVED, step + 1);

                        VariantBlockStateBuilder.PartialBlockstate postSectionSelection = null;
                        switch(section)
                        {
                            case "end" -> postSectionSelection = whenFacingAndStep.with(CanoeComponentBlock.END, true);
                            case "middle" -> postSectionSelection = whenFacingAndStep.with(CanoeComponentBlock.END, false);
                            case "all" -> postSectionSelection = whenFacingAndStep;
                        }

                        //Model Creation
                        var strippedLogSideTexture = new ResourceLocation(namespace, String.format(Locale.ROOT,
                                "block/%s", wood.getStrippedLogTexture().getPath()));
                        var strippedLogTopTexture = new ResourceLocation(namespace, String.format(Locale.ROOT,
                                "block/%s", wood.getStrippedLogTopTexture().getPath()));
                        String modelName = String.format(Locale.ROOT, "block/wood/canoe_component_block/%s/%s/%s", wood.getSerializedName(), section, step);
                        String parentName = String.format(Locale.ROOT, "block/canoe_component_block/template/%s/%s",
                                section, step);
                        var modelFile = stateProvider.models()
                                .withExistingParent(modelName, new ResourceLocation("firmaciv", parentName))
                                .texture("0", strippedLogSideTexture)
                                .texture("1", strippedLogTopTexture)
                                .texture("particle", strippedLogSideTexture);

                        //set model for state above, rotate on Y if needed
                        var configuredModel = postSectionSelection.modelForState()
                                .modelFile(modelFile);

                        if(validDirection != Direction.NORTH)
                            configuredModel.rotationY(directionToYRot(validDirection));

                        configuredModel.addModel(); //may be wrong?
                    }
                }
            });
        };
    }

    static BiConsumer<ModWatercraftMaterial, Supplier<? extends Block>> woodenBoatFrameFlat(
            final BlockStateProvider blockStateProvider, final ModelFile frameFlat) {
        return (wood, registryObject) -> {

            if(wood.isSoftwood())
            {
                return;
            }
            var plankTexture = new ResourceLocation(wood.getNamespace(), String.format(Locale.ROOT,
                    "block/%s", wood.getPlanksTexture().getPath()));
            final var multipartBuilder = blockStateProvider.getMultipartBuilder(registryObject.get()).part()
                    .modelFile(frameFlat).addModel().end();

            IntStream.range(0, 8).forEach(progress -> {
                if(progress < 4)
                {
                    final var plankModel = blockStateProvider.models().withExistingParent(
                            String.format(Locale.ROOT, "block/wood/watercraft_frame/flat/%s/%s", wood.getSerializedName(),
                                    BOAT_FRAME_PROGRESS_STRINGS[progress]), new ResourceLocation(AlekiShips.MOD_ID,
                                    String.format(Locale.ROOT, "block/watercraft_frame/flat/template/%s",
                                            BOAT_FRAME_PROGRESS_STRINGS[progress]))).texture("plank", plankTexture);



                    multipartBuilder.part().modelFile(plankModel).addModel()
                            .condition(FlatWoodenBoatFrameBlock.FRAME_PROCESSED,
                                    IntStream.range(progress, 8).boxed().toArray(Integer[]::new));
                }
                else
                {
                    final var plankModel = blockStateProvider.models().getExistingFile(new ResourceLocation(Firmaciv.MOD_ID, String.format(Locale.ROOT, "block/watercraft_frame/flat/bolt/%s", BOAT_FRAME_PROGRESS_STRINGS[progress % 4])));

                    multipartBuilder.part().modelFile(plankModel).addModel()
                            .condition(FlatWoodenBoatFrameBlock.FRAME_PROCESSED, IntStream.range(progress, 8).boxed().toArray(Integer[]::new));
                }
            });
        };
    }

    static BiConsumer<ModWatercraftMaterial, Supplier<? extends Block>> woodenBoatFrameAngled(
            final BlockStateProvider blockStateProvider, final ModelFile straight,
            final ModelFile inner, final ModelFile outer) {
        return (wood, registryObject) -> {
            if(wood.isSoftwood())
            {
                return;
            }

            var plankTexture = new ResourceLocation(wood.getNamespace(), String.format(Locale.ROOT,
                    "block/%s", wood.getPlanksTexture().getPath()));

            AngledWoodenBoatFrameBlock.FACING.getPossibleValues().forEach(facing -> {
                AngledWoodenBoatFrameBlock.SHAPE.getPossibleValues().forEach(shape -> {
                    final var multipartBuilder = blockStateProvider.getMultipartBuilder(registryObject.get());

                    final int yRot = angledBoatFrameYRot(shape, facing);

                    multipartBuilder.part().modelFile(
                                    shape == StairsShape.STRAIGHT ? straight : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? inner : outer)
                            .rotationY(yRot)/*.uvLock(yRot != 0)*/.addModel()
                            .condition(AngledWoodenBoatFrameBlock.FACING, facing)
                            .condition(AngledWoodenBoatFrameBlock.SHAPE, shape);

                    IntStream.range(0, 8).forEach(progress -> {
                        final String modelShape = shape == StairsShape.STRAIGHT ? "straight" : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? "inner" : "outer";
                        if(progress < 4)
                        {
                            final var plankModel = blockStateProvider.models().withExistingParent(
                                            String.format(Locale.ROOT, "block/wood/watercraft_frame/angled/%s/%s/%s",
                                                    wood.getSerializedName(), modelShape, BOAT_FRAME_PROGRESS_STRINGS[progress]),
                                            new ResourceLocation(AlekiShips.MOD_ID,
                                                    String.format(Locale.ROOT, "block/watercraft_frame/angled/template/%s/%s",
                                                            modelShape, BOAT_FRAME_PROGRESS_STRINGS[progress])))
                                    .texture("plank", plankTexture);

                            multipartBuilder.part().modelFile(plankModel).rotationY(yRot).uvLock(yRot != 0).addModel()
                                    .condition(AngledWoodenBoatFrameBlock.FACING, facing)
                                    .condition(AngledWoodenBoatFrameBlock.SHAPE, shape)
                                    .condition(AngledWoodenBoatFrameBlock.FRAME_PROCESSED,
                                            IntStream.range(progress, 8).boxed().toArray(Integer[]::new));
                        }
                        else
                        {
                            final var plankModel = blockStateProvider.models().getExistingFile(new ResourceLocation(Firmaciv.MOD_ID, String.format(Locale.ROOT, "block/watercraft_frame/angled/bolt/%s/%s", modelShape, BOAT_FRAME_PROGRESS_STRINGS[progress % 4])));

                            multipartBuilder.part().modelFile(plankModel).rotationY(yRot)/*.uvLock(yRot != 0)*/.addModel()
                                    .condition(AngledWoodenBoatFrameBlock.FACING, facing)
                                    .condition(AngledWoodenBoatFrameBlock.SHAPE, shape)
                                    .condition(AngledWoodenBoatFrameBlock.FRAME_PROCESSED,
                                            IntStream.range(progress, 8).boxed().toArray(Integer[]::new));
                        }
                    });
                });
            });
        };
    }

    static int angledBoatFrameYRot(final StairsShape shape, final Direction facing) {
        return switch (shape) {
            case INNER_RIGHT, STRAIGHT -> ((int) facing.toYRot());
            case OUTER_LEFT -> (int) facing.toYRot() + 90;
            case OUTER_RIGHT -> (int) facing.toYRot() + 180;
            case INNER_LEFT -> (int) facing.toYRot() + 270;
        } % 360;
    }

    static int directionToYRot(final Direction direction)
    {
        return switch(direction)
        {
            case NORTH -> 0;
            case EAST -> 90;
            case SOUTH -> 180;
            case WEST -> 270;
            default ->
                    throw new IllegalArgumentException("Invalid direction provided, Direction was \"" + direction + "\" when it should conform to NORTH, EAST, SOUTH or WEST");
        };
    }

    @Override
    protected void registerStatesAndModels() {

        final ModelFile frameFlat =
                new ModelFile.UncheckedModelFile(
                        new ResourceLocation(Firmaciv.MOD_ID, "block/watercraft_frame/flat/frame"));

        CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().forEach(
                woodenBoatFrameFlat(this, frameFlat)
        );

        final ModelFile angledFrameStraight =
                new ModelFile.UncheckedModelFile(
                        new ResourceLocation(Firmaciv.MOD_ID,"block/watercraft_frame/angled/straight"));

        final ModelFile angledFrameInner =
                new ModelFile.UncheckedModelFile(
                        new ResourceLocation(Firmaciv.MOD_ID,"block/watercraft_frame/angled/inner"));

        final ModelFile angledFrameOuter =
                new ModelFile.UncheckedModelFile(
                        new ResourceLocation(Firmaciv.MOD_ID,"block/watercraft_frame/angled/outer"));

        CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().forEach(
                woodenBoatFrameAngled(this, angledFrameStraight, angledFrameInner, angledFrameOuter)
        );

        CompatFirmaCivBlocks.getCanoeComponentBlocks().forEach(
                canoeComponentBlock(this)
        );
    }

    /*
    @Override
    protected void registerStatesAndModels() {
        models().generatedModels.get(new ResourceLocation(Firmaciv.MOD_ID, "block/watercraft_frame/flat/frame"));
        final var frameFlat = this.models().getExistingFile(new ResourceLocation(Firmaciv.MOD_ID,"block/watercraft_frame/flat/frame"));

        CompatFirmaCivBlocks.getWoodenBoatFrameFlatBlocks().forEach(
                FirmaCivPlusBlockStateProvider.woodenBoatFrameFlat(this, frameFlat));

        final ModelFile.ExistingModelFile angledFrameStraight = this.models()
                .getExistingFile(new ResourceLocation(Firmaciv.MOD_ID,"block/watercraft_frame/angled/straight"));
        final ModelFile.ExistingModelFile angledFrameInner = this.models()
                .getExistingFile(new ResourceLocation(Firmaciv.MOD_ID,"block/watercraft_frame/angled/inner"));
        final ModelFile.ExistingModelFile angledFrameOuter = this.models()
                .getExistingFile(new ResourceLocation(Firmaciv.MOD_ID,"block/watercraft_frame/angled/outer"));

        CompatFirmaCivBlocks.getWoodenBoatFrameAngledBlocks().forEach(woodenBoatFrameAngled(this, angledFrameStraight, angledFrameInner, angledFrameOuter));

        CompatFirmaCivBlocks.getCanoeComponentBlocks().forEach(canoeComponentBlock(this));

        //CompatFirmaCivBlocks.getWoodRoofings().forEach(woodRoofing(this));

    }

     */
}