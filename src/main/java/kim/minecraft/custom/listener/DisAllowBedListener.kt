package kim.minecraft.custom.listener

import io.izzel.taboolib.module.inject.TListener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerBedEnterEvent

@TListener
object DisAllowBedListener {

    @EventHandler
    fun onBed(e: PlayerBedEnterEvent) {
        e.isCancelled = false
    }
}