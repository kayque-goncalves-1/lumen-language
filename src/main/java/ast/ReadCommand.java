package ast;


import datastructures.LumenVariable;
import lombok.RequiredArgsConstructor;

/*
 *
 * Classe responsavel por gerar interação com Scanner
 *
 */
@RequiredArgsConstructor
public class ReadCommand extends AbstractCommand {

    private final String id;
    private final LumenVariable var;

    @Override
    public String generateJavaCode() {
        return new StringBuilder().append(this.id)
                .append("= scanner.")
                .append(this.var.getType() == LumenVariable.DECIMAL ? "nextDouble();" : "nextInt();")
                .toString();
    }
}
