package net.lod.voidbreak.item;

import net.lod.voidbreak.Voidbreak;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Voidbreak.MOD_ID);

    public static final RegistryObject<Item> VOID_BOTTLE = ITEMS.register("void_bottle",
            () -> new Item(new Item.Properties()
                    .craftRemainder(Items.GLASS_BOTTLE)
                    .stacksTo(16)));

    public static final RegistryObject<Item> CHALK_DUST = ITEMS.register("chalk_dust",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
