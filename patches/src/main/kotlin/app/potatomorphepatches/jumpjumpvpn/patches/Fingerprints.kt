package app.potatomorphepatches.jumpjumpvpn.patches

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.fieldAccess
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprint for the Companion object's factory method in JumpJumpVPN.
 * Target: ue.o1$a.a(I)Lue/o1;
 * 
 * Note: MatchAfterImmediately was removed because it caused a compilation error.
 * The engine will now look for the sequence of opcodes in order.
 * If the exact sequence isn't found (e.g., due to code changes or gaps), the patch will safely fail to apply.
 */
object PurchaseFactoryFingerprint : Fingerprint(
    definingClass = ":ue/o1\$a;",
    name = "a",
    parameters = listOf("I"),
    returnType = "Lue/o1;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    
    filters = listOf(
        // Filter 1: Must call 'values()'
        methodCall(
            name = "values"
        ),
        
        // Filter 2: Must call 'getRaw()'
        methodCall(
            name = "getRaw"
        ),
        
        // Filter 3: Must have conditional logic (IF_NE)
        opcode(Opcode.IF_NE),
        
        // Filter 4: Look for const-4 (null)
        // Removed MatchAfterImmediately() to fix compilation error.
        opcode(Opcode.CONST_4), 
        
        // Filter 5: Look for literal 0
        literal(0)
    )
)
