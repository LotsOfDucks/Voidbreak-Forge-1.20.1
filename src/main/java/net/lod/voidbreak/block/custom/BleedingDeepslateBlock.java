package net.lod.voidbreak.block.custom;

import net.lod.voidbreak.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class BleedingDeepslateBlock extends Block {
    public BleedingDeepslateBlock(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = false;
        Item item = itemstack.getItem();
        if (itemstack.is(Items.GLASS_BOTTLE)) {
            itemstack.shrink(1);
            pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (itemstack.isEmpty()) {
                pPlayer.setItemInHand(pHand, new ItemStack(ModItems.VOID_BOTTLE.get()));
            } else if (!pPlayer.getInventory().add(new ItemStack(ModItems.VOID_BOTTLE.get()))) {
                pPlayer.drop(new ItemStack(ModItems.VOID_BOTTLE.get()), false);
            }

            flag = true;
            pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, pPos);
        }

        if (!pLevel.isClientSide() && flag) {
            pPlayer.awardStat(Stats.ITEM_USED.get(item));
        }

        if (flag) {
            pLevel.setBlock(pPos, Blocks.DEEPSLATE.defaultBlockState(), 3);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }
}
