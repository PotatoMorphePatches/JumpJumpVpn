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
 * 
 * This method is the "gatekeeper" for purchase status. It takes an integer (0=Unspecified, 1=Purchased, 2=Pending)
 * and returns the corresponding Enum instance. 
 * 
 * We identify it by its unique behavior:
 * 1. It resides in the inner class '$a' (Companion object) of 'ue/o1'.
 * 2. It calls 'values()' to iterate over all enum constants.
 * 3. It calls 'getRaw()' to compare the input integer against the enum's internal value.
 * 4. It contains conditional logic (IF_NE) to skip non-matching values.
 */
object PurchaseFactoryFingerprint : Fingerprint(
    // Target the inner class 'a' which is the Companion object for 'ue.o1'
    definingClass = ":ue/o1\$a;",
    // The factory method name is 'a' (standard Kotlin obfuscation for Companion methods)
    name = "a",
    // Parameters: Takes a integer (I) representing the status code
    parameters = listOf("I"),
    // Return Type: Returns the Enum object (Lue/o1;)
    returnType = "Lue/o1;",
    // Access: Public and Final (standard for enum factory methods)
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    
    filters = listOf(
        // Filter 1: Must call 'values()' to get the list of enum constants.
        // This is a definitive signature of an enum factory or serializer.
        methodCall(
            name = "values"
        ),
        
        // Filter 2: Must call 'getRaw()' to check the integer value of the enum constants.
        // This confirms it's converting between int and enum.
        methodCall(
            name = "getRaw"
        ),
        
        // Filter 3: Must have conditional logic (IF_NE) to skip non-matching values.
        // This matches the loop structure in the decompiled code.
        opcode(Opcode.IF_NE),
        
        // Filter 4: Must return null (const-4 v0, 0x0) if no match is found or at the end.
        // We look for this pattern within a reasonable distance to confirm the method structure.
        opcode(Opcode.CONST_4, MatchAfterImmediately(15)), 
        literal(0)
    )
)
