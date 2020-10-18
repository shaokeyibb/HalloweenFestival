package kim.minecraft.custom.features

import io.izzel.taboolib.module.inject.TListener
import kim.minecraft.custom.HalloweenFestival
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scheduler.BukkitRunnable
import java.util.*
import kotlin.random.Random

@TListener
object LightningEverywhere : Listener {

    val actions: MutableMap<UUID, Action> = mutableMapOf()
    val fakeEntities: MutableList<Entity> = mutableListOf()

    @EventHandler
    fun onDamage(e: EntityDamageByEntityEvent) {
        if (e.damager in fakeEntities) e.isCancelled = true
    }

    @EventHandler
    fun onDeath(e: EntityDeathEvent) {
        if (e.entityType != EntityType.PLAYER) {
            if (Random.nextDouble(100.0) > 25.0) return
            e.entity.world.strikeLightningEffect(e.entity.location)
            e.entity.world.spawnEntity(e.entity.location, EntityType.WITCH)
        } else {
            e.entity.world.spawnEntity(e.entity.location, EntityType.ZOMBIE).also { entity ->
                entity.customName = e.entity.name
                entity.isCustomNameVisible = true
                (entity as Zombie).equipment!!.also {
                    it.armorContents = e.entity.equipment!!.armorContents
                }
            }
        }

    }

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        Action(e.player)
    }

    @EventHandler
    fun onJoin(e: PlayerQuitEvent) {
        actions.remove(e.player.uniqueId)
    }

    class Action(val player: Player) {
        private val action = object : BukkitRunnable() {
            override fun run() {
                if (player.world.time < 13000) return
                if (Random.nextDouble(0.0, 1.0) > HalloweenFestival.config.getDouble("lightningSpawnChance", 0.60)) return

                val type = Random.nextInt(0, 2).run {
                    when (this) {
                        0 -> EntityType.ZOMBIE
                        1 -> EntityType.CREEPER
                        2 -> EntityType.SKELETON
                        else -> EntityType.ZOMBIE
                    }
                }
                player.world.strikeLightningEffect(player.location)
                player.world.spawnEntity(player.location.add(-1.0, 0.0, -1.0), type).also {
                    if (it is Creeper) it.isPowered = false
                    fakeEntities.add(it)
                }
            }

        }

        init {
            action.runTaskTimer(HalloweenFestival.plugin, 1, HalloweenFestival.config.getLong("lightningSpawnPeriodTicks", 60))
            actions[player.uniqueId] = this
        }
    }
}