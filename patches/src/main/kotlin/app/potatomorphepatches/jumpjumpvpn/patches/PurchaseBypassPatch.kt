package app.potatomorphepatches.jumpjumpvpn.patches

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.patch
import app.potatomorphepatches.jumpjumpvpn.patches.shared.Constants.COMPATIBILITY_JUMPVPN

// The class containing the enum we need to access
private const val ENUM_CLASS = "Lue/o1;"
// The specific field holding the PURCHASED constant
private const val PURCHASED_FIELD = "PURCHASED"

@Suppress("unused")
val purchaseBypassPatch = patch(
    name = "Unlock VIP (Purchase Bypass)",
    description = "Forces the app to treat the user as 'PURCHASED' by hijacking the status factory method.",
    default = true
) {
    compatibleWith(COMPATIBILITY_JUMPVPN)

    // We depend on the fingerprint matching the factory method in the Companion object
    execute {
        // Locate the factory method 'a(I)Lue/o1;' in the Companion object 'ue.o1$a'
        val factoryMethod = PurchaseFactoryFingerprint.method

        // Inject code at the very beginning of the method to return PURCHASED immediately
        // This bypasses the original logic that checks the integer input
        factoryMethod.addInstructions(
            0,
            """
                # Get the static field 'PURCHASED' from the enum class 'ue.o1'
                sget-object v0, $ENUM_CLASS->$PURCHASED_FIELD:$ENUM_CLASS
                # Return the PURCHASED instance immediately
                return-object v0
            """
        )
    }
}
