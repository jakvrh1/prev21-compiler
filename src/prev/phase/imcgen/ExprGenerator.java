package prev.phase.imcgen;

import prev.common.report.Report;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.AstExprStmt;
import prev.data.ast.tree.stmt.AstStmt;
import prev.data.ast.visitor.AstVisitor;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.ImcSTMTS;
import prev.data.imc.code.stmt.ImcStmt;
import prev.data.mem.*;
import prev.data.typ.SemChar;
import prev.data.typ.SemType;
import prev.phase.memory.Memory;
import prev.phase.seman.SemAn;

import java.nio.charset.StandardCharsets;
import java.util.Stack;
import java.util.Vector;

public class ExprGenerator implements AstVisitor<ImcExpr, Stack<MemFrame>> {

    // EX1, EX2, EX3
    @Override
    public ImcExpr visit(AstAtomExpr atomExpr, Stack<MemFrame> memFrames) {

        long val = 0;
        switch (atomExpr.type) {
            case VOID, POINTER:
                break;
            case BOOL:
                if (atomExpr.value.equalsIgnoreCase("true"))
                    val = 1;
                break;
            case CHAR:
                var arr = atomExpr.value.getBytes(StandardCharsets.US_ASCII);
                if(arr.length == 3)
                    val = arr[1];
                else
                    val = arr[2];
                break;
            case INT:
                val = Long.parseLong(atomExpr.value);
                break;
            case STRING:
                MemAbsAccess maa = Memory.strings.get(atomExpr);
                ImcGen.exprImc.put(atomExpr, new ImcNAME(maa.label));
                break;
            default:
                return null;
        }

        ImcGen.exprImc.put(atomExpr, new ImcCONST(val));
        return null;
    }

    // EX4
    @Override
    public ImcExpr visit(AstPfxExpr pfxExpr, Stack<MemFrame> memFrames) {
        ImcExpr e = ImcGen.exprImc.get(pfxExpr.expr);
        Vector<Long> offs = new Vector<>();
        Vector<ImcExpr> args = new Vector<>();

        ImcUNOP.Oper oper = null;
        switch (pfxExpr.oper) {
            case NOT:
                oper = ImcUNOP.Oper.NOT;
                break;
            case ADD:
                oper = ImcUNOP.Oper.ADD;
                break;
            case SUB:
                oper = ImcUNOP.Oper.SUB;
                break;
                // EX6 levi
            case PTR:
                if(e instanceof ImcMEM)
                   ImcGen.exprImc.put(pfxExpr, ((ImcMEM) e).addr);
                return null;
                // EX7
            case NEW:
                // EX8
            case DEL:
                MemLabel newL = new MemLabel();
                offs.add(0L);
                offs.add(8L);
                args.add(e);
                ImcGen.exprImc.put(pfxExpr, new ImcCALL(newL, offs, args));
                return null;

            //new ImcCALL(delL, offs, args);
            default:
                return null;
        }

        ImcGen.exprImc.put(pfxExpr, new ImcUNOP(oper, e));
        return null;
    }

    // EX5
    @Override
    public ImcExpr visit(AstBinExpr binExpr, Stack<MemFrame> memFrames) {
        ImcBINOP.Oper oper = null;
        switch (binExpr.oper) {
            case OR:
                oper = ImcBINOP.Oper.OR;
                break;
            case AND:
                oper = ImcBINOP.Oper.AND;
                break;
            case EQU:
                oper = ImcBINOP.Oper.EQU;
                break;
            case NEQ:
                oper = ImcBINOP.Oper.NEQ;
                break;
            case LTH:
                oper = ImcBINOP.Oper.LTH;
                break;
            case GTH:
                oper = ImcBINOP.Oper.GTH;
                break;
            case LEQ:
                oper = ImcBINOP.Oper.LEQ;
                break;
            case GEQ:
                oper = ImcBINOP.Oper.GEQ;
                break;
            case ADD:
                oper = ImcBINOP.Oper.ADD;
                break;
            case SUB:
                oper = ImcBINOP.Oper.SUB;
                break;
            case MUL:
                oper = ImcBINOP.Oper.MUL;
                break;
            case DIV:
                oper = ImcBINOP.Oper.DIV;
                break;
            case MOD:
                oper = ImcBINOP.Oper.MOD;
                break;
            default:
                return null;
        }

        ImcExpr e1 = ImcGen.exprImc.get(binExpr.fstExpr);
        ImcExpr e2 = ImcGen.exprImc.get(binExpr.sndExpr);
        ImcGen.exprImc.put(binExpr, new ImcBINOP(oper, e1, e2));
        return null;
    }

    // EX6 desni
    @Override
    public ImcExpr visit(AstSfxExpr sfxExpr, Stack<MemFrame> memFrames) {
        ImcExpr ie = ImcGen.exprImc.get(sfxExpr.expr);
        ImcGen.exprImc.put(sfxExpr, new ImcMEM(ie));
        return null;
    }

    // EX9
    @Override
    public ImcExpr visit(AstNameExpr nameExpr, Stack<MemFrame> memFrames) {
        AstDecl amd = SemAn.declaredAt.get(nameExpr);
        MemAccess ma = null;
        if(amd instanceof AstVarDecl || amd instanceof AstParDecl)
            ma = Memory.accesses.get((AstMemDecl) amd);

        ImcExpr ie = null;
        if(ma instanceof MemAbsAccess) {
            ImcNAME in = new ImcNAME(((MemAbsAccess) ma).label);
            ie = new ImcMEM(in);
        }
        if(ma instanceof MemRelAccess) {
           ImcExpr ime = new ImcTEMP(memFrames.peek().FP);
           int depthDiff = memFrames.size() - ((MemRelAccess) ma).depth - 1;
           for(int i = 0; i < depthDiff; ++i) {
               ime = new ImcMEM(ime);
           }

           ImcBINOP binop = new ImcBINOP(ImcBINOP.Oper.ADD, ime, new ImcCONST(((MemRelAccess) ma).offset));
           ie = new ImcMEM(binop);
        }

        ImcGen.exprImc.put(nameExpr, ie);
        return null;
    }

    @Override
    public ImcExpr visit(AstVarDecl varDecl, Stack<MemFrame> memFrames) {
        return null;
    }

    @Override
    public ImcExpr visit(AstParDecl parDecl, Stack<MemFrame> memFrames) {
        return null;
    }

    // EX10
    @Override
    public ImcExpr visit(AstArrExpr arrExpr, Stack<MemFrame> memFrames) {
        ImcExpr ieID = ImcGen.exprImc.get(arrExpr.idx);
        ImcExpr ie = ImcGen.exprImc.get(arrExpr.arr);
        SemType type = SemAn.ofType.get(arrExpr);
        ImcBINOP idx = new ImcBINOP(ImcBINOP.Oper.MUL, ieID, new ImcCONST(type.size()));

        if(ie instanceof ImcMEM) {
            ImcBINOP binop = new ImcBINOP(ImcBINOP.Oper.ADD, ((ImcMEM) ie).addr, idx);
            ImcGen.exprImc.put(arrExpr, new ImcMEM(binop));
        }
        return null;
    }

    // EX11
    @Override
    public ImcExpr visit(AstRecExpr recExpr, Stack<MemFrame> memFrames) {
        ImcExpr ie = ImcGen.exprImc.get(recExpr.rec);
        if(ie instanceof ImcMEM) {
            AstDecl acd = SemAn.declaredAt.get(recExpr.comp);
            MemAccess ma = Memory.accesses.get((AstMemDecl) acd);
            if(ma instanceof MemRelAccess) {
                ImcBINOP binop = new ImcBINOP(ImcBINOP.Oper.ADD, ((ImcMEM) ie).addr, new ImcCONST(((MemRelAccess) ma).offset));
                ImcGen.exprImc.put(recExpr, new ImcMEM(binop));
            }
        }
        return null;
    }

    // EX12
    @Override
    public ImcExpr visit(AstCallExpr callExpr, Stack<MemFrame> memFrames) {
        AstDecl ad = SemAn.declaredAt.get(callExpr);
        if(ad instanceof AstFunDecl) {
            MemFrame mf = Memory.frames.get((AstFunDecl) ad);

            Vector<Long> offs = new Vector<>();
            Vector<ImcExpr> args = new Vector<>();

            offs.add(0L);
            if(mf.depth == 0)
                args.add(new ImcCONST(0));
             else
                args.add(new ImcTEMP(memFrames.peek().FP));

            for(var arg: callExpr.args) {
                SemType type = SemAn.ofType.get(arg);
                offs.add(type.size() + offs.lastElement());
                args.add(ImcGen.exprImc.get(arg));
            }
            /*
            if(mf == null) {
                ImcGen.exprImc.put(callExpr, new ImcCALL(new MemLabel(((AstFunDecl) ad).name), offs, args));
            } else {
            */

            ImcGen.exprImc.put(callExpr, new ImcCALL(mf.label, offs, args));
            //}

        }
        return null;
    }

    // EX13
    @Override
    public ImcExpr visit(AstStmtExpr stmtExpr, Stack<MemFrame> memFrames) {
        Vector<ImcStmt> stmts = new Vector<>();

        for(int i = 0; i < stmtExpr.stmts.size() - 1; ++i)
            stmts.add(ImcGen.stmtImc.get(stmtExpr.stmts.get(i)));

        AstStmt st = stmtExpr.stmts.get(stmtExpr.stmts.size() - 1);

        if(st instanceof AstExprStmt) {
            ImcSEXPR sexpr = new ImcSEXPR(new ImcSTMTS(stmts), ImcGen.exprImc.get(((AstExprStmt) st).expr));
            ImcGen.exprImc.put(stmtExpr, sexpr);
        } else {
            stmts.add(ImcGen.stmtImc.get(stmtExpr.stmts.get(stmtExpr.stmts.size() - 1)));
            ImcSEXPR sexpr = new ImcSEXPR(new ImcSTMTS(stmts), new ImcCONST(0L));
            ImcGen.exprImc.put(stmtExpr, sexpr);
        }
        return null;
    }


    // EX14

    // EX15 / EX16
    @Override
    public ImcExpr visit(AstCastExpr castExpr, Stack<MemFrame> memFrames) {
        SemType type = SemAn.isType.get(castExpr.type);
        ImcExpr ie = ImcGen.exprImc.get(castExpr.expr);
        if(type.actualType() instanceof SemChar) {
           ImcBINOP binop = new ImcBINOP(ImcBINOP.Oper.MOD, ie, new ImcCONST(256));
           ImcGen.exprImc.put(castExpr, binop);
        } else
            ImcGen.exprImc.put(castExpr, ie);
        return null;
    }

    // EX17
    @Override
    public ImcExpr visit(AstWhereExpr whereExpr, Stack<MemFrame> memFrames) {
        ImcExpr ie = ImcGen.exprImc.get(whereExpr.expr);
        ImcGen.exprImc.put(whereExpr, ie);
        return null;
    }

}
