package kim.minecraft.custom.features

import kim.minecraft.custom.HalloweenFestival
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import kotlin.random.Random

object WeatherControl {
    private val action = object : BukkitRunnable() {
        override fun run() {
            val world = Bukkit.getWorld(HalloweenFestival.config.getString("OverallWorldName", "world")!!)!!
            if (world.time == 13000L && Random.nextBoolean()) world.setStorm(true)
        }

    }

    init {
        action.runTaskTimer(HalloweenFestival.plugin, 0, 1)
    }
}