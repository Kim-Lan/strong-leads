package com.kimlan.strongleads.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PathAwareEntity.class)
public class PathAwareEntityMixin {
	@Redirect(method = "updateLeash",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/mob/PathAwareEntity;detachLeash(ZZ)V",
					ordinal = 1
			)
	)
	public void redirectDetachLeash(PathAwareEntity entity, boolean b1, boolean b2) {
		Entity holdingEntity = entity.getHoldingEntity();
		Vec3d pos = Vec3d.ZERO;

		if (holdingEntity != null) {
			pos = holdingEntity.getPos();
		}
		entity.refreshPositionAndAngles(pos.x, pos.y, pos.z, entity.getYaw(), entity.getPitch());
	}

	@Redirect(method = "updateLeash",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/ai/goal/GoalSelector;disableControl(Lnet/minecraft/entity/ai/goal/Goal$Control;)V"
			)
	)
	public void redirectDisableControl(GoalSelector goalSelector, Goal.Control control) {
		// skipping disableControl
	}
}