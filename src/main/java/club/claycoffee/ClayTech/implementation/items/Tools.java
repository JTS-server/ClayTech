package club.claycoffee.ClayTech.implementation.items;

import org.bukkit.NamespacedKey;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Tools {
	public Tools() {
		Slimefunutils.registerItem(ClayTechItems.C_TOOLS, "REINFORCED_ALLOY_PICKAXE",
				ClayTechItems.REINFORCED_ALLOY_PICKAXE, "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE,
				ClayTechMachineRecipes.REINFORCED_ALLOY_PICKAXE, false);
		Slimefunutils.registerItem(ClayTechItems.C_TOOLS, "CLAY_ALLOY_PICKAXE", ClayTechItems.CLAY_ALLOY_PICKAXE,
				"notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.CLAY_ALLOY_PICKAXE,
				false);

		Research rs = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_TOOLS_1"), 9922,
				Lang.readResearchesText("CLAYTECH_TOOLS_I"), 35);
		rs.addItems(SlimefunItem.getByItem(ClayTechItems.REINFORCED_ALLOY_PICKAXE),
				SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_PICKAXE));
		rs.register();
	}
}
