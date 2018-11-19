package org.perl6.nqp.truffle.nodes.sixmodel;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.perl6.nqp.truffle.nodes.NQPNode;
import org.perl6.nqp.truffle.nodes.NQPObjNode;
import org.perl6.nqp.truffle.runtime.NQPNull;
import org.perl6.nqp.dsl.Deserializer;

@NodeInfo(shortName = "attribute-get")
public final class NQPAttributeGetNode extends NQPObjNode {
    @Child private NQPNode objNode;
    @Child private NQPNode classHandleNode;
    private final String name;

    @Deserializer("attribute-get")
    public NQPAttributeGetNode(NQPNode objNode, NQPNode classHandleNode, String name) {
        this.objNode = objNode;
        this.classHandleNode = classHandleNode;
        this.name = name;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object obj = objNode.execute(frame);
        Object classHandle = classHandleNode.execute(frame);

        //return attribute-get(objNode.execute(frame), classHandleNode.execute(frame), nameNode.executeStr(frame), dNode.execute(frame));
        System.out.println("attribute-get: " + name);
        return NQPNull.SINGLETON;
    }
}
