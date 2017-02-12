package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is the visitor of our tree.
 * It will walk the tree, check the semantic, generate a ST and help with PCode generation.
 *
 * Created by Simon on 12/02/17.
 */
public class ASTVisitor extends B314BaseVisitor
{

    private static final Logger LOG = LoggerFactory.getLogger(ASTVisitor.class);

    /**
     * Steps :
     *      Check preconditions
     *      Check type matching
     *      Update SymTable
     *      determine the semantic
     *      Generate PCode
     */

    @Override
    public Object visitRoot (B314Parser.RootContext ctx)
    {
        LOG.debug("Visitor starts");

        // We go the the children
        super.visitRoot(ctx);

        LOG.debug("Visitor ends");

        return null;
    }

}
