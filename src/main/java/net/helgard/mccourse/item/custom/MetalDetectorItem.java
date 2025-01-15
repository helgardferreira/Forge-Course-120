package net.helgard.mccourse.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if (!context.getLevel().isClientSide()) {
            BlockPos positionClicked = context.getClickedPos();
            boolean foundBlock = false;

            if (player != null) {
                for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                    BlockState blockState = context.getLevel().getBlockState(positionClicked.below(i));

                    if (isValuableBlock(blockState)) {
                        outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                        foundBlock = true;

                        break;
                    }
                }

                if (!foundBlock) {
                    outputNoValuableFound(player);
                }
            }
        }

        if (player != null) {
            ItemStack itemstack = context.getItemInHand();
            itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack,
            @NotNull TooltipContext tooltipContext,
            @NotNull List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag
    ) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tooltip.shift"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tooltip"));
        }

        super.appendHoverText(itemStack, tooltipContext, tooltipComponents, tooltipFlag);
    }

    private void outputNoValuableFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.mccourse.metal_detector.no_valuables"));
    }

    private void outputValuableCoordinates(BlockPos below, Player player, Block block) {
        player.sendSystemMessage(
                Component.literal(
                        "Valuable Found: "
                                + I18n.get(block.getDescriptionId())
                                + " at ("
                                + below.getX()
                                + ", "
                                + below.getY()
                                + ", "
                                + below.getZ()
                                + ")"
                )
        );
    }

    // TODO: implement multiple valuable blocks using tags
    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(Blocks.IRON_ORE)
                || blockState.is(Blocks.DEEPSLATE_IRON_ORE)
                || blockState.is(Blocks.DIAMOND_ORE);
    }
}
