package ast;

import lombok.RequiredArgsConstructor;
import lombok.ToString;


/*
 *
 * Classe responsavel por gerar println do java
 *
 */
@ToString
@RequiredArgsConstructor
public class WriteCommand extends AbstractCommand {

    private final String id;

    @Override
    public String generateJavaCode() {
        return "System.out.println(" + this.id + ");\n";
    }

}
