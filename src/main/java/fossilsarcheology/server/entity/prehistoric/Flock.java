package fossilsarcheology.server.entity.prehistoric;

import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Flock {
	public float flockYaw;
	public List<EntityPrehistoric> flockMembers = new ArrayList<EntityPrehistoric>();
	public EntityPrehistoric flockLeader;
	public PrehistoricEntityType type;
	private double flockPosX;
	private double flockPosY;
	private double flockPosZ;
	/**
	 * The PathEntity of our entity
	 */
	private Path flockPathEntity;
	/**
	 * The PathNavigate of our entity
	 */
	private PathNavigate flockPathNavigate;

	public static int generateVarience(int max, int min) {
		return new Random().nextInt(max - min) + min;
	}

	public void createFlock(EntityPrehistoric creator) {
		flockMembers.add(creator);
		flockLeader = creator;
		flockPosX = creator.posX;
		flockPosY = creator.posY;
		flockPosZ = creator.posZ;

	}

	public void onUpdate() {
		if (flockLeader == null || flockLeader.isDead) {
			setNewLeader();
		}
		for (EntityPrehistoric member : flockMembers) {
			if (member != null && flockLeader != null && this.flockPathNavigate != null && this.flockPathNavigate.getPath() != null) {
				if (member.getNavigator().noPath() && member != this.flockLeader) {
					Path path = this.flockLeader.getNavigator().getPath();
					member.getNavigator().setPath(this.flockPathNavigate.getPathToXYZ(path.getFinalPathPoint().x + generateVarience(6, -6), path.getFinalPathPoint().y + generateVarience(6, -6), path.getFinalPathPoint().z + generateVarience(6, -6)), 1);
				}
			}
		}

		if (flockPathNavigate == null) {
			flockPathNavigate = flockLeader.getNavigator();
		}
		if (flockLeader != null) {
			if (!flockLeader.isMovementBlocked() && flockLeader.getNavigator().noPath()) {
				Vec3d vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(flockLeader, 32, 7, new Vec3d(flockLeader.posX, flockLeader.posY, flockLeader.posZ));
				if (flockPathNavigate == null) {
					flockPathNavigate = flockLeader.getNavigator();
				}
				if (flockLeader.getNavigator() != null && vec3 != null) {
					this.flockLeader.getNavigator().setPath(this.flockPathNavigate.getPathToXYZ(vec3.x, vec3.y, vec3.z), 1);
				}
			}
		}
	}

	public EntityPrehistoric findNewMember(World world, AxisAlignedBB bb, EntityPrehistoric leader) {
		List list = world.getEntitiesWithinAABB(EntityPrehistoric.class, bb);
		EntityPrehistoric entity1 = null;
		double d0 = Double.MAX_VALUE;

		for (int i = 0; i < list.size(); ++i) {
			EntityPrehistoric entity2 = (EntityPrehistoric) list.get(i);

			if (entity2 != leader && !this.flockMembers.contains(entity2) && entity2.type == leader.type) {
				double d1 = leader.getDistanceSq(entity2);

				if (d1 <= d0) {
					entity1 = entity2;
					d0 = d1;
				}
			}
		}
		return entity1;
	}

	public void setNewLeader() {
		flockLeader = flockMembers.get(new Random().nextInt(flockMembers.size()));
	}

	public void disband() {
		flockMembers.clear();

	}
}
