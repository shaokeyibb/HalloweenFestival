package kim.minecraft.custom.features

import kim.minecraft.custom.HalloweenFestival
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Monster
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

object StealthEntity {

    private val action = object : BukkitRunnable() {
        override fun run() {
            Bukkit.getWorlds().forEach { world ->
                world.livingEntities.forEach { livingEntity ->
                    if (livingEntity !is Player
                            && livingEntity !is ArmorStand
                            && livingEntity is Monster
                            && livingEntity.equipment != null
                            && livingEntity.equipment!!.armorContents.firstOrNull { it.type != Material.AIR } != null) {
                        livingEntity.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, 65535, 5, false, true, false))
                    }
                }
            }
        }
    }

    init {
        action.runTaskTimer(HalloweenFestival.plugin, 0, 20)
    }
}