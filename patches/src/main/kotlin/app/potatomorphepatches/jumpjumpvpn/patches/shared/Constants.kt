package app.potatomorphepatches.jumpjumpvpn.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility
import app.morphe.patcher.patch.SupportedAbi

object Constants {
    /**
     * Compatibility definition for JumpJumpVPN.
     * Target APK: JumpJumpVPN-Fast-Secure_3.5.2_apkcube.apks
     * Package: app.jumpjumpvpn.jumpjumpvpn
     */
    val COMPATIBILITY_JUMPVPN = Compatibility(
        name = "JumpJumpVPN",
        packageName = "app.jumpjumpvpn.jumpjumpvpn",
        apkFileType = ApkFileType.APK, 
        // Fix: Use String format "#RRGGBB" instead of Int literal
        appIconColor = 0x2196F3.toInt(),
        targets = listOf(
            AppTarget(
                version = "3.5.2"
            ),
            AppTarget(
                version = null,
                isExperimental = true
            )
        )
    )
}
