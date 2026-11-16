package app.potatomorphepatches.jumpjumpvpn.patches

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.InstructionLocation.MatchAfterImmediately
import app.morphe.patcher.fieldAccess
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.ReferenceType

/**
 * Fingerprint for the Companion object's factory method in JumpJumpVPN.
 * Target: ue.o1$a.a(I)Lue/o1;
 */
object PurchaseFactoryFingerprint : Fingerprint(
    definingClass = ":ue/o1\$a;",
    name = "a",
    parameters = listOf("I"),
    returnType = "Lue/o1;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    
    filters = listOf(
        methodCall(
            name = "values"
        ),
        
        methodCall(
            name = "getRaw"
        ),
        
        opcode(Opcode.IF_NE),
        
        // Fix: Remove the integer argument. MatchAfterImmediately() takes no arguments.
        // This filter now requires the const-4 instruction to be immediately after the IF_NE.
        // If the bytecode has gaps, this fingerprint will simply not match (safe failure).
        opcode(Opcode.CONST_4, MatchAfterImmediately()), 
        literal(0)
    )
)
