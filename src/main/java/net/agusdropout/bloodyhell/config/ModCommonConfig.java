package net.agusdropout.bloodyhell.config;

import net.agusdropout.bloodyhell.item.custom.base.SpellType;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ModCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;


    public static final ForgeConfigSpec.BooleanValue GIVE_GUIDE_BOOK_ON_JOIN;



    public static final ForgeConfigSpec.ConfigValue<String> VESPER_QUEST_ITEM_1_ID;
    public static final ForgeConfigSpec.IntValue VESPER_QUEST_ITEM_1_COUNT;

    public static final ForgeConfigSpec.ConfigValue<String> VESPER_QUEST_ITEM_2_ID;
    public static final ForgeConfigSpec.IntValue VESPER_QUEST_ITEM_2_COUNT;

    public static final ForgeConfigSpec.DoubleValue GLOBAL_SPELL_DAMAGE;
    public static final Map<String, ForgeConfigSpec.DoubleValue> INDIVIDUAL_SPELL_DAMAGE = new HashMap<>();
    public static final ForgeConfigSpec.ConfigValue<String> HARVESTER_DEFAULT_BLOOD_FLUID;
    public static final ForgeConfigSpec.ConfigValue<String> BLOOD_GEM_SPROUT_REQUIRED_FLUID;

    static {
        BUILDER.push("Global Spell Scaling");
        GLOBAL_SPELL_DAMAGE = BUILDER.comment("Global multiplier applied to ALL spells. (1.0 = Default, 0.5 = Half Damage, 2.0 = Double Damage)")
                .defineInRange("globalDamageMultiplier", 1.0, 0.0, 100.0);
        BUILDER.pop();

        BUILDER.push("Individual Spell Scaling");
        BUILDER.comment("Multipliers for specific spells. These stack multiplicatively with the Global multiplier.");


        for (SpellType spell : SpellType.values()) {
            INDIVIDUAL_SPELL_DAMAGE.put(spell.getId(), BUILDER
                    .comment("Damage multiplier for " + spell.name().toLowerCase().replace("_", " "))
                    .defineInRange(spell.getId() + "_damage", 1.0, 0.0, 100.0));
        }
        BUILDER.pop();


        BUILDER.push("Gameplay Settings");

        GIVE_GUIDE_BOOK_ON_JOIN = BUILDER
                .comment("Should players receive the Unknown Guide Book when they first join the world?")
                .define("giveGuideBookOnJoin", true);

        BUILDER.pop();

        BUILDER.push("Vesper Settings");

        VESPER_QUEST_ITEM_1_ID = BUILDER
                .comment("Registry name of the first item Vesper requires (e.g., 'minecraft:bone' or 'bloodyhell:rhnull')")
                .define("vesperQuestItem1_ID", "minecraft:bone");

        VESPER_QUEST_ITEM_1_COUNT = BUILDER
                .comment("Amount of the first item Vesper requires.")
                .defineInRange("vesperQuestItem1_Count", 10, 1, 64);

        VESPER_QUEST_ITEM_2_ID = BUILDER
                .comment("Registry name of the second item Vesper requires.")
                .define("vesperQuestItem2_ID", "minecraft:ender_pearl");

        VESPER_QUEST_ITEM_2_COUNT = BUILDER
                .comment("Amount of the second item Vesper requires.")
                .defineInRange("vesperQuestItem2_Count", 1, 1, 64);

        BUILDER.pop();

        BUILDER.push("Machine Settings");
        HARVESTER_DEFAULT_BLOOD_FLUID = BUILDER
                .comment("Registry name of the default fluid produced by the Sanguinite Blood Harvester. Useful for unifying blood fluids in modpacks.")
                .define("harvesterDefaultBloodFluid", "bloodyhell:blood");

        BLOOD_GEM_SPROUT_REQUIRED_FLUID = BUILDER
                .comment("Registry name of the explicit fluid required by the Blood Gem Sprout. If empty or invalid, defaults to 'bloodyhell:blood'.")
                .define("bloodGemSproutRequiredFluid", "bloodyhell:blood");



        BUILDER.pop();


        SPEC = BUILDER.build();
    }

    public static double getFinalDamageMultiplier(String spellId) {
        double global = GLOBAL_SPELL_DAMAGE.get();
        double individual = INDIVIDUAL_SPELL_DAMAGE.containsKey(spellId) ? INDIVIDUAL_SPELL_DAMAGE.get(spellId).get() : 1.0;
        return global * individual;
    }

}