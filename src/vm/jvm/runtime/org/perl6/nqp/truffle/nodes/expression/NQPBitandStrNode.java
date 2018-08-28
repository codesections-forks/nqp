package org.perl6.nqp.truffle.nodes.expression;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.perl6.nqp.truffle.nodes.NQPNode;
import org.perl6.nqp.truffle.nodes.NQPStrNode;
import org.perl6.nqp.dsl.Deserializer;

@NodeInfo(shortName = "bitand_s")
public final class NQPBitandStrNode extends NQPStrNode {
    @Child private NQPNode leftNode;
    @Child private NQPNode rightNode;

    @Deserializer
    public NQPBitandStrNode(NQPNode leftNode, NQPNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public String executeStr(VirtualFrame frame) {
        String a = leftNode.executeStr(frame);
        String b = rightNode.executeStr(frame);
        int alength = a.length();
        int blength = b.length();
        int mlength = alength > blength ? alength : blength;
        StringBuilder r = new StringBuilder(mlength);
        int apos = 0;
        int bpos = 0;
        while (apos < alength && bpos < blength) {
           int cpa = a.codePointAt(apos);
           int cpb = b.codePointAt(bpos);
           r.appendCodePoint(cpa & cpb);
           apos += Character.charCount(cpa);
           bpos += Character.charCount(cpb);
        }
        return r.toString();
    }
}
