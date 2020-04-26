package mod.unclecat.uc_auramagic.util.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.ColorHandler;
import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.datagen.DataGenerators;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryHelper
{
	@SuppressWarnings("unchecked") // I believe in Forge's reliability
	public static <T extends IForgeRegistryEntry<T>> void registerAllViaEvent(RegistryEvent.Register<T> event)
	{
		Field[] fields = Content.class.getDeclaredFields();
		
		for (Field i : fields)
		{
			try
			{
				if (((Class<T>)event.getGenericType()).isAssignableFrom(i.get(null).getClass()))
				{
					event.getRegistry().register((T) i.get(null));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not register object \"" + i.getName() + "\"\n" + "Skipping the object, it will not appear in the game.");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IForgeRegistryEntry<T>> void registerAllFromAdditionalsGameObjects(RegistryEvent.Register<T> event)
	{
		for (Object i : Content.additionalGameObjects)
		{ 
			try
			{
				if (((Class<T>)event.getGenericType()).isAssignableFrom(i.getClass()))
				{
					event.getRegistry().register((T) i);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not register object of type \"" + i.getClass().getName() + "\"\n" + "Skipping the object, it will not appear in the game.");
			}
		}	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllInstancesOf(Class<T> clas)
	{
		List<T> ret = new ArrayList<T>();	
		Field[] fields = Content.class.getDeclaredFields();
		
		for (Field i : fields)
		{
			try
			{
				if (clas.isInstance(i.get(null)))
				{
					ret.add((T) i.get(null));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not get object of field \"" + i.getName() + "\" from contetns.");
			}
		}
		
		for (Object i : Content.additionalGameObjects)
		{
			if (clas.isInstance(i))
			{
				ret.add((T) i);
			}
		}	
		
		return ret;
	}
	
	public static List<IItemProvider> getDynamicColorItems()
	{
		List<IItemProvider> list = new ArrayList<IItemProvider>(1);
		Field[] fields = Content.class.getDeclaredFields();

		for (Field i : fields)
		{
			try
			{
				if (ColorHandler.mustHandle(i) && i.get(null) instanceof IItemProvider)
				{
					list.add((IItemProvider) i.get(null));
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not add object " + i.getName() + " to dynamic color list.");
			}
		}
		
		for (Object i : Content.additionalGameObjects) 
		{
			if (ColorHandler.mustHandle(i) && i instanceof IItemProvider)
			{
				list.add((IItemProvider) i);
			}
		}
		
		return list;
	}

	public static List<Block> getDynamicColorBlocks()
	{
		List<Block> list = new ArrayList<Block>();
		Field[] fields = Content.class.getDeclaredFields();

		for (Field i : fields)
		{
			try
			{
				if (ColorHandler.mustHandle(i) && i.get(null) instanceof Block)
				{
					list.add((Block) i.get(null));
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				Auramagic.LOG.error("Could not add object " + i.getName() + " to dynamic color list.");
			}
		}
		
		for (Object i : Content.additionalGameObjects) 
		{
			if (ColorHandler.mustHandle(i) && i instanceof Block)
			{
				list.add((Block) i);
			}
		}
		
		return list;
	}
	
	public static String classNameToRegistryName(Class<?> clas)
	{
		String name = clas.getSimpleName();
		String ret = "";
		
		name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
		
		for (char i : name.toCharArray())
		{
			if (Character.isUpperCase(i))
			{
				ret += "_" + Character.toLowerCase(i);
			}
			else if (i == '$' || i == '#') // TODO: find right inner classes java notation
			{
				ret += "_";
			}
			else
			{
				ret += i;
			}
		}
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends ModTileEntity, Y extends TileEntityRenderer<T>> void registerTileEntityTypeFromMapEntry(Entry<Class<? extends ModTileEntity>, List<Block>> entry, IForgeRegistry<TileEntityType<?>> registry)
	{
		TileEntityType<T> type = TileEntityType.Builder.<T>create(() -> 
		{ 
			try
			{
				return (T) entry.getKey().newInstance();
			} 
			catch (Exception e)
			{
				Auramagic.LOG.error("Counld not create tile enitity " + entry.getKey().toString());
				e.printStackTrace();
				return null;
			} 
		}, entry.getValue().toArray(new Block[] {})).build(null);
		
		type.setRegistryName(Auramagic.prefix(RegistryHelper.classNameToRegistryName(entry.getKey())));
		registry.register(type);
		
		try
		{
			entry.getKey().getDeclaredField("TYPE").set(null, type);
		} 
		catch (Exception e1)
		{
			Auramagic.LOG.error("Could not find tile entity type field for class " + entry.getKey().toString());
			e1.printStackTrace();
			return;
		}
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> 
		{ 
			Class<Y> ter = ModTileEntity.getTERClass((Class<T>) entry.getKey());
			
			if (ter != null)
			{
				ClientRegistry.bindTileEntityRenderer(type, new Function<TileEntityRendererDispatcher, Y>()
				{
					@Override
					public Y apply(TileEntityRendererDispatcher t)
					{
						try
						{
							return ter.getConstructor(TileEntityRendererDispatcher.class).newInstance(t);
						} 
						catch (Exception e)
						{
							Auramagic.LOG.error("Could not create tile entity renderer.");
							e.printStackTrace();
							return null;
						}
					}
				});
			}
		});
	}
}
 