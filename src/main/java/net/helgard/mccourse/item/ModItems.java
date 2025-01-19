package net.helgard.mccourse.item;

import net.helgard.mccourse.MCCourseMod;
import net.helgard.mccourse.item.custom.FuelItem;
import net.helgard.mccourse.item.custom.MetalDetectorItem;
import net.helgard.mccourse.item.custom.PaxelItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCCourseMod.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register(
            "alexandrite",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register(
            "raw_alexandrite",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register(
            "metal_detector",
            () -> new MetalDetectorItem(
                    new Item.Properties()
                            .durability(512)
            )
    );
    public static final RegistryObject<Item> KOHLRABI = ITEMS.register(
            "kohlrabi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI))
    );
    public static final RegistryObject<Item> PEAT_BRICK = ITEMS.register(
            "peat_brick",
            () -> new FuelItem(new Item.Properties(), 200)
    );

    public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register(
            "alexandrite_sword",
            () -> new SwordItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .fireResistant()
                            .attributes(
                                    SwordItem.createAttributes(Tiers.NETHERITE, 3, -2.4F)
                            )
            )
    );
    public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register(
            "alexandrite_pickaxe",
            () -> new PickaxeItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .fireResistant()
                            .attributes(
                                    PickaxeItem.createAttributes(Tiers.NETHERITE, 1.0F, -2.8F)
                            )
            )
    );
    public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register(
            "alexandrite_shovel",
            () -> new ShovelItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .fireResistant()
                            .attributes(
                                    ShovelItem.createAttributes(Tiers.NETHERITE, 1.5F, -3.0F)
                            )
            )
    );
    public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register(
            "alexandrite_axe",
            () -> new AxeItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .fireResistant()
                            .attributes(
                                    AxeItem.createAttributes(Tiers.NETHERITE, 5.0F, -3.0F)
                            )
            )
    );
    public static final RegistryObject<Item> ALEXANDRITE_HOE = ITEMS.register(
            "alexandrite_hoe",
            () -> new HoeItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .fireResistant()
                            .attributes(
                                    HoeItem.createAttributes(Tiers.NETHERITE, -4.0F, 0.0F)
                            )
            )
    );
    public static final RegistryObject<Item> ALEXANDRITE_PAXEL = ITEMS.register(
            "alexandrite_paxel",
            () -> new PaxelItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .fireResistant()
                            .attributes(
                                    PaxelItem.createAttributes(Tiers.NETHERITE, 5.0F, -2.4F)
                            )
            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
