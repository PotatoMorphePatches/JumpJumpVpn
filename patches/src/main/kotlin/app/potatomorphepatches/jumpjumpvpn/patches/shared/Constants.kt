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
        // The source file is an .apks (split bundle), but the internal type is usually APK or APKM.
        // If this is a split APK bundle, ensure the user knows to use the full bundle.
        apkFileType = ApkFileType.APK, 
        // Use a generic VPN color (e.g., Blue) or extract from icon if known.
        appIconColor = 0xFF2196F3, 
        targets = listOf(
            // Confirmed working version based on your filename
            AppTarget(
                version = "3.5.2"
            ),
            // Allow experimental use on newer versions (users will be warned)
            AppTarget(
                version = null,
                isExperimental = true
            )
        )
    )
}
