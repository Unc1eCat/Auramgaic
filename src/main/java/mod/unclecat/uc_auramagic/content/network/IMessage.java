package mod.unclecat.uc_auramagic.content.network;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public interface IMessage
{
	public void write(PacketBuffer buf);
	
	public void handle(Supplier<NetworkEvent.Context> ctx);
}
