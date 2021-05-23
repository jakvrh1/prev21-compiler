package prev.phase.memory;

import prev.data.ast.attribute.AstAttribute;
import prev.data.ast.tree.decl.AstFunDecl;
import prev.data.ast.tree.decl.AstMemDecl;
import prev.data.ast.tree.expr.AstAtomExpr;
import prev.data.mem.MemAbsAccess;
import prev.data.mem.MemAccess;
import prev.data.mem.MemFrame;
import prev.phase.Phase;

/**
 * Computing memory layout: frames and accesses.
 */
public class Memory extends Phase {

    /**
     * Maps function declarations to frames.
     */
    public static final AstAttribute<AstFunDecl, MemFrame> frames = new AstAttribute<AstFunDecl, MemFrame>(0);

    /**
     * Maps variable declarations to accesses.
     */
    public static final AstAttribute<AstMemDecl, MemAccess> accesses = new AstAttribute<AstMemDecl, MemAccess>(0);

    /**
     * Maps string constants to accesses.
     */
    public static final AstAttribute<AstAtomExpr, MemAbsAccess> strings = new AstAttribute<AstAtomExpr, MemAbsAccess>(0);

    /**
     * Constructs a new phase for computing layout.
     */
    public Memory() {
        super("memory");
    }

}
