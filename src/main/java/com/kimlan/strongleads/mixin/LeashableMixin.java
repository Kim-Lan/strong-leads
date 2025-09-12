package com.kimlan.strongleads.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Leashable;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Leashable.class)
public interface LeashableMixin {

	@WrapOperation(method = "tickLeash",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/Leashable;snapLongLeash()V"
			)
	)
	private static void wrapSnapLongLeash(Leashable instance, Operation<Void> original) {
		Entity holdingEntity = instance.getLeashHolder();
		Vec3d pos = Vec3d.ZERO;

		if (holdingEntity != null) {
			pos = holdingEntity.getPos();
		}
		((Entity) instance).refreshPositionAndAngles(pos, ((Entity) instance).getYaw(), ((Entity) instance).getPitch());
	}
}