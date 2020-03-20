package mod.unclecat.uc_auramagic.util.game_objects;

public interface IKind<T>
{
	/*
	 * Modifies original name of a game object
	 */
	public String appendToName(T gameObjectDataStorage, String name);
}