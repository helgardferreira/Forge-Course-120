package net.helgard.mccourse.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class SoundBlock extends Block {
    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack itemStack,
            BlockState blockState,
            Level level,
            BlockPos blockPos,
            Player player,
            InteractionHand interactionHand,
            BlockHitResult blockHitResult
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

                return ItemInteractionResult.SUCCESS;
            } else {
                level.playSound(
                        null,
                        blockPos,
                        SoundEvents.NOTE_BLOCK_COW_BELL.get(),
                        SoundSource.BLOCKS,
                        1f,
                        1f
                );

                return ItemInteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
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
}
