package prev.phase.imcgen;

import prev.common.report.Report;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.visitor.AstVisitor;
import prev.data.imc.code.expr.*;
import prev.data.mem.MemAbsAccess;
import prev.data.mem.MemAccess;
import prev.data.mem.MemFrame;
import prev.data.mem.MemRelAccess;
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
            default:
                return null;
        }

        ImcGen.exprImc.put(atomExpr, new ImcCONST(val));
        return null;
    }

    // EX4
    @Override
    public ImcExpr visit(AstPfxExpr pfxExpr, Stack<MemFrame> memFrames) {
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
                ImcExpr ie = ImcGen.exprImc.get(pfxExpr.expr);
                if(ie instanceof ImcMEM)
                   ImcGen.exprImc.put(pfxExpr, ((ImcMEM) ie).addr);
                return null;
                // EX7
            case NEW:
            case DEL:

            default:
                return null;
        }

        ImcExpr e = ImcGen.exprImc.get(pfxExpr.expr);
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
           MemFrame frame = memFrames.get(((MemRelAccess) ma).depth);
           ImcBINOP binop = new ImcBINOP(ImcBINOP.Oper.ADD, new ImcTEMP(frame.FP), new ImcCONST(((MemRelAccess) ma).offset));
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

        }
        return null;
    }

    // EX13

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
