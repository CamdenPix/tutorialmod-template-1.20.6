package name.tutorialmod;

import name.tutorialmod.components.CADComponent;
import name.tutorialmod.components.CADStatsComponent;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class TutorialModComponent implements EntityComponentInitializer {
    public static final ComponentKey<CADComponent> CAD =
            ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);

    public static final ComponentKey<CADStatsComponent> STATS =
            ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cadstats"), CADStatsComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(CAD, player -> new CADComponent(), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(STATS, player -> new CADStatsComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
