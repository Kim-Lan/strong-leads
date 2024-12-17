package com.kimlan.strongleads.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.Leashable;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Leashable.class)
interface LeashableMixin {

	@Redirect(method = "tickLeash",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/Leashable;breakLongLeash()V"
			)
	)
	private static void redirectBreakLongLeash(Leashable instance) {
		Entity holdingEntity = instance.getLeashHolder();
		Vec3d pos = Vec3d.ZERO;

		if (holdingEntity != null) {
			pos = holdingEntity.getPos();
		}
		((Entity) instance).refreshPositionAndAngles(pos, ((Entity) instance).getYaw(), ((Entity) instance).getPitch());
	}
}