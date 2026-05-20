package com.kimlan.strongleads.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Leashable.class)
public interface LeashableMixin {
	@WrapOperation(
		method = "tickLeash",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/Leashable;leashTooFarBehaviour()V"
		)
	)
	private static void wrapLeashTooFarBehaviour(Leashable instance, Operation<Void> original) {
		Entity holdingEntity = instance.getLeashHolder();
		((Entity) instance).copyPosition(holdingEntity);
	}
}