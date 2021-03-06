package club.claycoffee.ClayTech.implementation.Planets;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;


import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;

public class Mars extends ChunkGenerator{
	private SimplexOctaveGenerator sog;
	
	public Mars() {
		new Planet("CMars",
				Utils.newItemD(Material.YELLOW_GLAZED_TERRACOTTA, Lang.readPlanetsText("Mars")), this,
				Environment.NORMAL, true, 1, 100, 0, false).register();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		ChunkData chunkData = createChunkData(world);
		if (sog == null) {
			sog = new SimplexOctaveGenerator(world.getSeed(), 1);
			sog.setScale(0.00125D);
		}
		// 最低一层为基岩
		chunkData.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int realX = chunkX * 16 + x;
				int realZ = chunkZ * 16 + z;
				double noise1 = sog.noise(realX, realZ, 4D, 128D);
				double noise2 = sog.noise(realX, realZ, 8D, 64D);
				double noise3 = sog.noise(realX, realZ, 16D, 32D);
				double noise4 = sog.noise(realX, realZ, 32D, 16D);
				double noise5 = sog.noise(realX, realZ, 64D, 8D);
				
				double[] sort = new double[] {noise1,noise2,noise3,noise4,noise5};
				Arrays.sort(sort);
				
				double basicNoiseValue = sort[0];
			    
				int basicHeight = (int) (basicNoiseValue * 40D + 55D);
				int finalHeight = basicHeight;
				finalHeight += 7D;
				
				chunkData.setBlock(x, 0, z, Material.BEDROCK);
				
				for(int eh = 1 ; eh < finalHeight - 1 ;eh++) {
					// 设置高度
					if(eh == 45 || eh == 46 || eh == 47 || eh == 48) {
						if(eh == 45) {
							chunkData.setBlock(x, 45, z, Material.LAVA);
						}
						if(eh == 46) {
							chunkData.setBlock(x, 46, z, Material.AIR);
						}
						if(eh == 47) {
							chunkData.setBlock(x, 47, z, Material.AIR);
						}
						if(eh == 48) {
							chunkData.setBlock(x, 48, z, Material.AIR);
						}
					}
					else {
						chunkData.setBlock(x, eh, z, Material.STONE);
					}
				}
				
				if(random.nextDouble() >= 0.4) {
					// 生成沙砾
					chunkData.setBlock(x, finalHeight - 1, z, Material.GRAVEL);
				}
				else {
					// 生成红沙
					chunkData.setBlock(x, finalHeight - 1, z, Material.RED_SAND);
				}
				
				// 最顶端红沙石覆盖
				chunkData.setBlock(x, finalHeight, z, Material.RED_SANDSTONE);
				
				biome.setBiome(x, z, Biome.DESERT);
			}
		}
		return chunkData;
	}

//	@Override
//	public List<BlockPopulator> getDefaultPopulators(World world) {
//  	TODO 点缀
//	}
}
