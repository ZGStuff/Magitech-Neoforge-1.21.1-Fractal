package net.stln.magitech.content.item.creative_tab;

import de.dafuqs.fractal.api.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.content.item.ItemInit;
import net.stln.magitech.content.item.ThreadboundGenerator;
import net.stln.magitech.feature.magic.spell.SpellLike;

import java.util.List;

import static net.stln.magitech.Magitech.id;

public class FractalTabInit {
    // Create our parent creative tab
    public static final CreativeModeTab TAB = CreativeModeTab.builder()
            .icon(() -> new ItemStack(Blocks.REDSTONE_BLOCK))
            .displayItems((displayContext, entries) ->
            {
                // At least one item must be added to the parent tab or else it won't be visible.
                // Make sure that this item isn't in one of your subtabs, otherwise you'll get an error about duplicate items in a tab.
                entries.accept(Items.APPLE, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

                // Add all of our subgroup's items to the parent tab.
                for (CreativeSubTab subGroup : FractalTabInit.TAB.fractal$getChildren()) {
                    entries.acceptAll(subGroup.getSearchTabDisplayItems(), CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
                }
            })
            .title(Component.translatable("itemGroup.magitech.magitech"))
            .build();
    // Create a subtab for the basic redstone things.
    public static final CreativeModeTab RESOURCES = new CreativeSubTab.Builder(
            TAB, // This is the parent tab we're registering the subtab to.
            id("resources"),
            Component.translatable("itemGroup.magitech.resources")
    )
            .entries((displayContext, entries) -> {
                entries.accept(ItemInit.ALCHAEFABRIC.get());
                entries.accept(ItemInit.AEGIS_WEAVE.get());
                entries.accept(ItemInit.FLUORITE.get());
                entries.accept(ItemInit.MANA_CHARGED_FLUORITE.get());
                entries.accept(ItemInit.TOURMALINE.get());
                entries.accept(ItemInit.EMBER_CRYSTAL.get());
                entries.accept(ItemInit.GLACE_CRYSTAL.get());
                entries.accept(ItemInit.SURGE_CRYSTAL.get());
                entries.accept(ItemInit.PHANTOM_CRYSTAL.get());
                entries.accept(ItemInit.TREMOR_CRYSTAL.get());
                entries.accept(ItemInit.MAGIC_CRYSTAL.get());
                entries.accept(ItemInit.FLOW_CRYSTAL.get());
                entries.accept(ItemInit.HOLLOW_CRYSTAL.get());
                entries.accept(ItemInit.AGGREGATED_NOCTIS.get());
                entries.accept(ItemInit.AGGREGATED_LUMINIS.get());
                entries.accept(ItemInit.AGGREGATED_FLUXIA.get());
                entries.accept(ItemInit.CITRINE.get());
                entries.accept(ItemInit.RAW_ZINC.get());
                entries.accept(ItemInit.ZINC_INGOT.get());
                entries.accept(ItemInit.CHROMIUM_INGOT.get());
                entries.accept(ItemInit.REDSTONE_CRYSTAL.get());
                entries.accept(ItemInit.POLISHED_REDSTONE_CRYSTAL.get());
                entries.accept(ItemInit.SULFUR.get());
                entries.accept(ItemInit.ENDER_METAL_INGOT.get());
                entries.accept(ItemInit.NETHER_STAR_BRILLIANCE.get());
                entries.accept(ItemInit.RADIANT_STEEL_INGOT.get());
                entries.accept(ItemInit.FRIGIDITE.get());
                entries.accept(ItemInit.POLISHED_FRIGIDITE.get());
                entries.accept(ItemInit.TRANSLUCIUM.get());
                entries.accept(ItemInit.POLISHED_TRANSLUCIUM.get());
                entries.accept(ItemInit.RESONITE.get());
                entries.accept(ItemInit.POLISHED_RESONITE.get());
                entries.accept(ItemInit.ABYSSITE.get());
                entries.accept(ItemInit.POLISHED_ABYSSITE.get());
                entries.accept(ItemInit.MANA_INSULATING_GLASS.get());
                entries.accept(ItemInit.SULFURIC_ACID_BATTERY.get());
                entries.accept(ItemInit.MANA_DEEXCITER_CORE.get());
                entries.accept(ItemInit.ASPECT_COLLECTOR.get());
                entries.accept(ItemInit.BOOTS_FRAME.get());
                entries.accept(ItemInit.MANA_CELL.get());
                entries.accept(ItemInit.MANA_BERRIES.get());
                entries.accept(ItemInit.ALCHEMICAL_FLASK.get());
                entries.accept(ItemInit.WATER_FLASK.get());
                entries.accept(ItemInit.LAVA_FLASK.get());
                entries.accept(ItemInit.SULFURIC_ACID_FLASK.get());
                entries.accept(ItemInit.MANA_POTION_FLASK.get());
                entries.accept(ItemInit.HEALING_POTION_FLASK.get());
                entries.accept(ItemInit.EMBER_POTION_FLASK.get());
                entries.accept(ItemInit.GLACE_POTION_FLASK.get());
                entries.accept(ItemInit.SURGE_POTION_FLASK.get());
                entries.accept(ItemInit.PHANTOM_POTION_FLASK.get());
                entries.accept(ItemInit.TREMOR_POTION_FLASK.get());
                entries.accept(ItemInit.MAGIC_POTION_FLASK.get());
                entries.accept(ItemInit.FLOW_POTION_FLASK.get());
                entries.accept(ItemInit.HOLLOW_POTION_FLASK.get());
            })
            .build();
    public static final CreativeModeTab EQUIPMENT_MISC = new CreativeSubTab.Builder(
            TAB,
            id("equipment_misc"),
            Component.translatable("itemGroup.magitech.equipment_misc")
    )
            .entries((displayContext, entries) -> {
                var registries = displayContext.holders();
                var spellLookup = registries.lookupOrThrow(MagitechRegistries.Keys.SPELL);
                List<SpellLike> allSpells = spellLookup.listElements().map(Holder::value).map(spell -> (SpellLike) spell).toList();

                entries.accept(ThreadboundGenerator.generateThreadbound(ItemInit.GLISTENING_LEXICON.get(), allSpells));
                entries.accept(ThreadboundGenerator.generateThreadbound(ItemInit.MATERIALS_AND_TOOLCRAFT_DESIGN.get(), allSpells));
                entries.accept(ThreadboundGenerator.generateThreadbound(ItemInit.THE_FIRE_THAT_THINKS.get(), allSpells));
                entries.accept(ThreadboundGenerator.generateThreadbound(ItemInit.APPLIED_ARCANE_CIRCUITRY.get(), allSpells));
                entries.accept(ThreadboundGenerator.generateThreadbound(ItemInit.ARCANE_ENGINEERING_COMPENDIUM.get(), allSpells));
                entries.accept(ItemInit.AETHER_LIFTER.get());
                entries.accept(ItemInit.FLAMGLIDE_STRIDER.get());
                entries.accept(ItemInit.MANA_RING.get());
                entries.accept(ItemInit.GALEVENT_RING.get());
                entries.accept(ItemInit.CHARGEBIND_RING.get());
                entries.accept(ItemInit.TORSION_RING.get());
                entries.accept(ItemInit.UMBRAL_RING.get());
                entries.accept(ItemInit.DAWN_RING.get());
                entries.accept(ItemInit.FLUXBOUND_RING.get());
                entries.accept(ItemInit.TOOL_BELT.get());
                entries.accept(ItemInit.MANA_PIE.get());
                entries.accept(ItemInit.WEAVER_SPAWN_EGG.get());
            })
            .build();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "mymod");

    public FractalTabInit(IEventBus modBus)
    {
        TABS.register("tab", () -> TAB);
        TABS.register(modBus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Magitech.MOD_ID, path);
    }
}
