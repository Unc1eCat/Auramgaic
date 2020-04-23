package mod.unclecat.uc_auramagic.content.network;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModNetwork
{
	public static String PROTIOCOL_VERSION = "1";
	public static SimpleChannel CHANNEL = 
			NetworkRegistry.newSimpleChannel(new ResourceLocation(Auramagic.prefix("main")), () -> PROTIOCOL_VERSION, PROTIOCOL_VERSION::equals, PROTIOCOL_VERSION::equals);
	private static int currentMessageRegisteringID = 0;	
	
	public static <T extends IMessage> void registerMessage(Class<T> messageClass) 
	{
		try
		{
			CHANNEL.registerMessage(
					currentMessageRegisteringID++, messageClass, new BiConsumer<T, PacketBuffer>()
					{
						public void accept(T t, PacketBuffer u) 
						{
							t.write(u);
						};
					},
					new Function<PacketBuffer, T>()
					{
						public Constructor<T> reader = messageClass.getDeclaredConstructor(PacketBuffer.class);
						
						@Override
						public T apply(PacketBuffer buf)
						{
								try
								{
									return reader.newInstance(buf);
								} 
								catch (InstantiationException | IllegalAccessException | IllegalArgumentException
										| InvocationTargetException e)
								{
									Auramagic.LOG.error("Failed to instantinate network message with constructor " + reader.toString());
									e.printStackTrace();
									return null;
								}
						}
					},
					new BiConsumer<T, Supplier<NetworkEvent.Context>>()
					{
						public void accept(T t, Supplier<NetworkEvent.Context> u) 
						{
							t.handle(u);
						};
					}
			);
		} 
		catch (NoSuchMethodException e)
		{
			Auramagic.LOG.error("Failed to register network message of class " + messageClass.toString() + ". Failed to get constructor of the message.");
			e.printStackTrace();
		}
	}
}
