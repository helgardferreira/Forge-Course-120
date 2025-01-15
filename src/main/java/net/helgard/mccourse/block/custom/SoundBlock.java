package net.helgard.mccourse.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SoundBlock extends Block {
    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(
            @NotNull ItemStack itemStack,
            @NotNull BlockState blockState,
            Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull InteractionHand interactionHand,
            @NotNull BlockHitResult blockHitResult
    ) {
        if (!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            if (player.isCrouching()) {
                level.playSound(
                        null,
                        blockPos,
                        SoundEvents.NOTE_BLOCK_BANJO.get(),
                        SoundSource.BLOCKS,
                        1f,
                        1f
                );
            } else {
                level.playSound(
                        null,
                        blockPos,
                        SoundEvents.NOTE_BLOCK_COW_BELL.get(),
                        SoundSource.BLOCKS,
                        1f,
                        1f
                );
            }
        }

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Entity entity) {
        level.playSound(
                entity,
                blockPos,
                SoundEvents.NOTE_BLOCK_BIT.get(),
                SoundSource.BLOCKS,
                1f,
                1f
        );

        super.stepOn(level, blockPos, blockState, entity);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack,
            @NotNull Item.TooltipContext tooltipContext,
            @NotNull List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag
    ) {
        tooltipComponents.add(Component.translatable("tooltip.mccourse.sound_block"));

        super.appendHoverText(itemStack, tooltipContext, tooltipComponents, tooltipFlag);
    }
}
