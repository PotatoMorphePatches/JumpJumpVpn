package app.potatomorphepatches.jumpjumpvpn.patches

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

/**
 * Companion factory method in JumpJumpVPN 3.5.2.
 * Obfuscated target: ue.o1$a.a(I)Lue/o1;  (names change between app versions)
 */
object PurchaseFactoryFingerprint : Fingerprint(
    definingClass = "Lue/o1\$a;",
    name = "a",
    parameters = listOf("I"),
    returnType = "Lue/o1;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    filters = listOf(
        methodCall(name = "values"),
        methodCall(name = "getRaw"),
        opcode(Opcode.IF_NE),
        literal(0)
    )
)
