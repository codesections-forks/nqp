package org.perl6.nqp.truffle.nodes.expression;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.perl6.nqp.truffle.nodes.NQPNode;
import org.perl6.nqp.truffle.nodes.NQPIntNode;
import org.perl6.nqp.dsl.Deserializer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NodeInfo(shortName = "fileexecutable")
public final class NQPFileexecutableNode extends NQPIntNode {
    @Child private NQPNode argNode;

    @Deserializer
    public NQPFileexecutableNode(NQPNode argNode) {
        this.argNode = argNode;
    }

    @Override
    public long executeInt(VirtualFrame frame) {
        Path path = Paths.get(argNode.executeStr(frame));
        try {
            return Files.isExecutable(path) ? 1 : 0;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
