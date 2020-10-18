package kim.minecraft.custom.listener

import io.izzel.taboolib.module.inject.TListener
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

@TListener
object DeliciousFoods: Listener {

    @EventHandler
    fun onEat(e: PlayerItemConsumeEvent) {
        if (e.item.type == Material.PUMPKIN_PIE) e.player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, 2400, 1, true, true, true))
    }

    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        if (e.entity !is Player) return
        if ((e.entity as Player).hasPotionEffect(PotionEffectType.NIGHT_VISION)) e.damage = e.damage + 5
    }
}