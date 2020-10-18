package kim.minecraft.custom.listener

import io.izzel.taboolib.module.inject.TListener
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

@TListener
object DisAllowBedListener:Listener {

    @EventHandler
    fun onBed(e: PlayerBedEnterEvent) {
        e.setUseBed(Event.Result.DENY)
    }
}