package kim.minecraft.custom

import io.izzel.taboolib.TabooLib
import io.izzel.taboolib.loader.Plugin
import io.izzel.taboolib.module.config.TConfig
import kim.minecraft.custom.features.StealthEntity
import kim.minecraft.custom.features.WeatherControl
import org.bukkit.Bukkit
import org.bukkit.WeatherType

object HalloweenFestival : Plugin() {

    val config: TConfig = TConfig.create(this.plugin, "config.yml")

    override fun onLoad() {
        // override onLoad()
    }

    override fun onEnable() {
        // override onEnable()
        plugin.saveDefaultConfig()
        if (!config.getBoolean("enable", true)) Bukkit.getPluginManager().disablePlugin(plugin)
        StealthEntity
        WeatherControl
    }

    override fun onDisable() {
        // override onDisable()
    }

}
