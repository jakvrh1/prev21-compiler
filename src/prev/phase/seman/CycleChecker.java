package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.tree.stmt.AstExprStmt;
import prev.data.ast.tree.stmt.AstIfStmt;
import prev.data.ast.tree.stmt.AstWhileStmt;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.AstFullVisitor;
import prev.data.typ.SemType;

import java.util.HashSet;
import java.util.Set;

public class CycleChecker extends AstFullVisitor<SemType, Object> {

    public Set<Object> path = new HashSet<>();

    @Override
    public SemType visit(AstCompDecl compDecl, Object o) {
        if (path.contains(compDecl))
            throw new Report.Error(compDecl, "Type error: found cycle.");
        //throw new Report.Error(compDecl, "Type error: found cycle in [" + compDecl.name + "] in location [" + compDecl.location() + "].");

        path.add(compDecl);
        super.visit(compDecl, o);
        path.remove(compDecl);

        return null;
    }

    @Override
    public SemType visit(AstFunDecl funDecl, Object o) {
        if (path.contains(funDecl))
            throw new Report.Error(funDecl, "Type error: found cycle.");

        path.add(funDecl);
        super.visit(funDecl, o);
        path.remove(funDecl);

        return null;
    }

    @Override
    public SemType visit(AstParDecl parDecl, Object o) {
        if (path.contains(parDecl))
            throw new Report.Error(parDecl, "Type error: found cycle.");
        path.add(parDecl);
        super.visit(parDecl, o);
        path.remove(parDecl);
        return null;
    }

    @Override
    public SemType visit(AstTypeDecl typeDecl, Object o) {
        if (path.contains(typeDecl))
            throw new Report.Error(typeDecl, "Type error: found cycle.");
        path.add(typeDecl);
        super.visit(typeDecl, o);
        path.remove(typeDecl);
        return null;
    }

    @Override
    public SemType visit(AstVarDecl varDecl, Object o) {
        if (path.contains(varDecl))
            throw new Report.Error(varDecl, "Type error: found cycle.");
        path.add(varDecl);
        super.visit(varDecl, o);
        path.remove(varDecl);
        return null;
    }

    @Override
    public SemType visit(AstArrExpr arrExpr, Object o) {
        if (path.contains(arrExpr))
            throw new Report.Error(arrExpr, "Type error: found cycle.");
        path.add(arrExpr);
        super.visit(arrExpr, o);
        path.remove(arrExpr);
        return null;
    }

    @Override
    public SemType visit(AstAtomExpr atomExpr, Object o) {
        if (path.contains(atomExpr))
            throw new Report.Error(atomExpr, "Type error: found cycle.");
        path.add(atomExpr);
        super.visit(atomExpr, o);
        path.remove(atomExpr);
        return null;
    }

    @Override
    public SemType visit(AstBinExpr binExpr, Object o) {
        if (path.contains(binExpr))
            throw new Report.Error(binExpr, "Type error: found cycle.");

        path.add(binExpr);
        super.visit(binExpr, o);
        path.remove(binExpr);
        return null;
    }

    @Override
    public SemType visit(AstCallExpr callExpr, Object o) {
        if (path.contains(callExpr))
            throw new Report.Error(callExpr, "Type error: found cycle.");
        path.add(callExpr);
        super.visit(callExpr, o);
        path.remove(callExpr);
        return null;
    }

    @Override
    public SemType visit(AstCastExpr castExpr, Object o) {
        if (path.contains(castExpr))
            throw new Report.Error(castExpr, "Type error: found cycle.");
        path.add(castExpr);
        super.visit(castExpr, o);
        path.remove(castExpr);
        return null;
    }

    @Override
    public SemType visit(AstNameExpr nameExpr, Object o) {
        if (path.contains(nameExpr))
            throw new Report.Error(nameExpr, "Type error: found cycle.");

        path.add(nameExpr);
        super.visit(nameExpr, o);
        path.remove(nameExpr);
        return null;
    }

    @Override
    public SemType visit(AstPfxExpr pfxExpr, Object o) {
        if (path.contains(pfxExpr))
            throw new Report.Error(pfxExpr, "Type error: found cycle.");

        path.add(pfxExpr);
        super.visit(pfxExpr, o);
        path.remove(pfxExpr);
        return null;
    }

    @Override
    public SemType visit(AstRecExpr recExpr, Object o) {
        if (path.contains(recExpr))
            throw new Report.Error(recExpr, "Type error: found cycle.");

        path.add(recExpr);
        super.visit(recExpr, o);
        path.remove(recExpr);
        return null;
    }

    @Override
    public SemType visit(AstSfxExpr sfxExpr, Object o) {
        if (path.contains(sfxExpr))
            throw new Report.Error(sfxExpr, "Type error: found cycle.");
        path.add(sfxExpr);
        super.visit(sfxExpr, o);
        path.remove(sfxExpr);
        return null;
    }

    @Override
    public SemType visit(AstStmtExpr stmtExpr, Object o) {
        if (path.contains(stmtExpr))
            throw new Report.Error(stmtExpr, "Type error: found cycle.");
        path.add(stmtExpr);
        super.visit(stmtExpr, o);
        path.remove(stmtExpr);
        return null;
    }

    @Override
    public SemType visit(AstWhereExpr whereExpr, Object o) {
        if (path.contains(whereExpr))
            throw new Report.Error(whereExpr, "Type error: found cycle.");
        path.add(whereExpr);
        super.visit(whereExpr, o);
        path.remove(whereExpr);
        return null;
    }

    @Override
    public SemType visit(AstAssignStmt assignStmt, Object o) {
        if (path.contains(assignStmt))
            throw new Report.Error(assignStmt, "Type error: found cycle.");
        path.add(assignStmt);
        super.visit(assignStmt, o);
        path.remove(assignStmt);
        return null;
    }

    @Override
    public SemType visit(AstExprStmt exprStmt, Object o) {
        if (path.contains(exprStmt))
            throw new Report.Error(exprStmt, "Type error: found cycle.");
        path.add(exprStmt);
        super.visit(exprStmt, o);
        path.remove(exprStmt);
        return null;
    }

    @Override
    public SemType visit(AstIfStmt ifStmt, Object o) {
        if (path.contains(ifStmt))
            throw new Report.Error(ifStmt, "Type error: found cycle.");
        path.add(ifStmt);
        super.visit(ifStmt, o);
        path.add(ifStmt);
        return null;
    }

    @Override
    public SemType visit(AstWhileStmt whileStmt, Object o) {
        if (path.contains(whileStmt))
            throw new Report.Error(whileStmt, "Type error: found cycle.");
        path.add(whileStmt);
        super.visit(whileStmt, o);
        path.remove(whileStmt);
        return null;
    }

    @Override
    public SemType visit(AstArrType arrType, Object o) {
        if (path.contains(arrType))
            throw new Report.Error(arrType, "Type error: found cycle.");
        path.add(arrType);
        super.visit(arrType, o);
        path.remove(arrType);
        return null;
    }

    @Override
    public SemType visit(AstAtomType atomType, Object o) {
        if (path.contains(atomType))
            throw new Report.Error(atomType, "Type error: found cycle.");
        path.add(atomType);
        super.visit(atomType, o);
        path.remove(atomType);
        return null;
    }

    @Override
    public SemType visit(AstNameType nameType, Object o) {
        if (path.contains(nameType))
            throw new Report.Error(nameType, "Type error: found cycle.");
        path.add(nameType);
        super.visit(nameType, o);
        path.remove(nameType);
        return null;
    }

    @Override
    public SemType visit(AstPtrType ptrType, Object o) {
        if (path.contains(ptrType))
            throw new Report.Error(ptrType, "Type error: found cycle.");
        path.add(ptrType);
        super.visit(ptrType, o);
        path.remove(ptrType);
        return null;
    }

    @Override
    public SemType visit(AstRecType recType, Object o) {
        if (path.contains(recType))
            throw new Report.Error(recType, "Type error: found cycle.");
        path.add(recType);
        super.visit(recType, o);
        path.remove(recType);
        return null;
    }
}
