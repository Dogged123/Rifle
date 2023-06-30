package me.isaacfediw.rifle;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class rifleworkings implements Listener {
    Rifle plugin;
    int timeLeft;
    int time;

    boolean canShoot = true;
    public rifleworkings(Rifle plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onShoot(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().getType() == Material.IRON_HOE) {
                if (!canShoot) return;
                canShoot = false;
                //Projectile
                Snowball projectile = p.launchProjectile(Snowball.class);
                projectile.setItem(new ItemStack(Material.FLINT));
                timeLeft = 5;
                time = 50;

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (timeLeft == 0) {
                            canShoot = true;
                            cancel();
                        }
                        p.setLevel(timeLeft);
                        p.getWorld().spawnParticle(Particle.FLAME, projectile.getLocation(), 0, 0, 0, 0);
                        if (time%10 == 0){
                            timeLeft --;
                        }
                        time--;
                    }
                }.runTaskTimer(plugin, 0, 1);
            }
        }
    }

//    @EventHandler
//    public void onHandItemChange(PlayerItemHeldEvent e){
//        Player p = e.getPlayer();
//        if (Objects.requireNonNull(p.getInventory().getItem(e.getNewSlot())).getType() == Material.NETHERITE_HOE){
//            p.sendMessage(ChatColor.GREEN + "Switched to Netherite Hoe!");
//            p.setLevel(ammo);
//            canReload = true;
//        }
//    }

    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent e){
        if ((e.getDamager() instanceof Snowball)) {
            e.setDamage(2);
        }
    }
}
