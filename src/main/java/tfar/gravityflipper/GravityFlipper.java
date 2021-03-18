package tfar.gravityflipper;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GravityFlipper.MODID)
public class GravityFlipper {
    // Directly reference a log4j logger.

    public static final String MODID = "gravityflipper";

    public GravityFlipper() {
        MinecraftForge.EVENT_BUS.addListener(this::gravityFlipper);
    }

    private static final AttributeModifier REVERSE = new AttributeModifier(UUID.fromString("1e350e85-36c5-4220-b32b-0ce2a3957f98"),MODID,-0.16, AttributeModifier.Operation.ADDITION);

    private void gravityFlipper(TickEvent.PlayerTickEvent event) {
        if (event.phase== TickEvent.Phase.START) {
            PlayerEntity player = event.player;
            World world = player.world;
            if (!player.world.isRemote && world.getGameTime() % 1200 == 0) {
                if (player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).hasModifier(REVERSE)) {
                    player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removePersistentModifier(REVERSE.getID());
                } else {
                    player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).applyPersistentModifier(REVERSE);
                }
            }
        }
    }
}
