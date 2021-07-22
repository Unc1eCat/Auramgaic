package mod.unclecat.uc_auramagic.content.multiblock;

/*
 * Instances of its subclasses are associated with corresponding multiblock creation triggers and used to create multiblocks
 */
public interface IMultiblockCreator
{
	/// Return true if the trigger matched the creator in any way
	public boolean handleCreationTrigger(IMultiblockCreationTrigger trigger);
}
