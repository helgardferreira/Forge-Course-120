package net.helgard.mccourse.item.custom;

import net.helgard.mccourse.util.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class PaxelItem extends DiggerItem  {
    public PaxelItem(Tier tier, Properties properties) {
        super(tier, ModTags.Blocks.PAXEL_MINEABLE, properties);
    }
}
