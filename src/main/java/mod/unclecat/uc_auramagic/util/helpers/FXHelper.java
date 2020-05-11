package mod.unclecat.uc_auramagic.util.helpers;

import mod.unclecat.uc_auramagic.Auramagic;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;

public class FXHelper
{
	public static void spawnComplexParticles(IParticleData particleData, int amountMin, int amountMax, double xMin, double xRange, double yMin, double yRange, 
			double zMin, double zRange, double xVelMin, double xVelMax, double yVelMin, double yVelMax, double zVelMin, double zVelMax, 
			World world, boolean forceAlwaysRender)
	{
		if (!world.isRemote) return;
		for (int i = 0; i < amountMin + Auramagic.RANDOM.nextInt(amountMax - amountMin); i++)
		{
			world.addParticle(particleData, forceAlwaysRender, xMin + Auramagic.RANDOM.nextDouble() * xRange, yMin + Auramagic.RANDOM.nextDouble() * yRange, 
					zMin + Auramagic.RANDOM.nextDouble() * zRange, JavaHelper.randomBetween(xVelMin, xVelMax), 
					JavaHelper.randomBetween(yVelMin, yVelMax), JavaHelper.randomBetween(zVelMin, zVelMax));
		}
	}
	public static void spawnComplexParticles(IParticleData particleData, int amountMin, int amountMax, double xMin, double xRange, double yMin, double yRange, 
			double zMin, double zRange, double velMin, double velMax, 
			World world, boolean forceAlwaysRender)
	{
		if (!world.isRemote) return;
		for (int i = 0; i < amountMin + Auramagic.RANDOM.nextInt(amountMax - amountMin); i++)
		{
			world.addParticle(particleData, forceAlwaysRender, xMin + Auramagic.RANDOM.nextDouble() * xRange, yMin + Auramagic.RANDOM.nextDouble() * yRange, 
					zMin + Auramagic.RANDOM.nextDouble() * zRange, JavaHelper.randomBetween(velMin, velMax), 
					JavaHelper.randomBetween(velMin, velMax), JavaHelper.randomBetween(velMin, velMax));
		}
	}
}
