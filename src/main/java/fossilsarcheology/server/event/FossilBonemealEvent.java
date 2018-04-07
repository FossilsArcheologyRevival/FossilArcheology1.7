package fossilsarcheology.server.event;


import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.PalmSaplingBlock;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FossilBonemealEvent {

	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event) {
		if (event.getBlock() == FABlockRegistry.PALM_SAPLING) {
			if (!event.getWorld().isRemote) {
				((PalmSaplingBlock) FABlockRegistry.PALM_SAPLING).generateTree(event.getWorld(), event.getPos(), event.getWorld().rand);
				event.setResult(Event.Result.ALLOW);
			}
		}
	}
}
