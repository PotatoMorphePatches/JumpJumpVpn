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
 * Fixed: Replaced MatchAfterImmediately with instruction() to define 
 * the exact sequence of opcodes required for the match.
 */
object PurchaseFactoryFingerprint: Fingerprint(
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
    // Note: Removed MatchAfterImmediately and wrapped in instruction() 
    // to match a strict sequence of opcodes rather than relying on 
    // implicit post-instruction matching.
   instruction(Opcode.CONST_4), 
  
    // Filter 5: Look for literal 0
    literal(0)
   )
)
