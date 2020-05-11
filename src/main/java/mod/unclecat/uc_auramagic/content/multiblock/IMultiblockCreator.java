package mod.unclecat.uc_auramagic.content.multiblock;

/*
 * Instances of its subclasses are associated with corresponding multiblock creation triggers and used to create multiblocks
 */
public interface IMultiblockCreator
{
	public boolean matches(IMultiblockCreationTrigger trigger);
	
	public void create(IMultiblockCreationTrigger trigger);
	
	default public float getPriority()
	{
		return 0;
	}
}
