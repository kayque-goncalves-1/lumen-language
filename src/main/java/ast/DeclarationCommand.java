package ast;

import lombok.RequiredArgsConstructor;

/*
 *
 * Classe responsavel por gerar estrutura de declaração em java.
 *
 */
@RequiredArgsConstructor
public class DeclarationCommand extends AbstractCommand {

    private final String id;
    private final String expr;


    @Override
    public String generateJavaCode() {
        return this.id + " = " + this.expr + ";";
    }

}
