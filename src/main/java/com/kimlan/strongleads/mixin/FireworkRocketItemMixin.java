package com.kimlan.strongleads.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemMixin {

	@WrapOperation(method = "use",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/player/PlayerEntity;detachAllHeldLeashes(Lnet/minecraft/entity/player/PlayerEntity;)Z"
			)
	)
	private boolean doNotDetachLeashes(PlayerEntity instance, PlayerEntity playerEntity, Operation<Boolean> original) {
        return false;
    }
}