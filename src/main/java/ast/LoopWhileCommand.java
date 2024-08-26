package ast;

import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

/*
 *
 * Classe responsavel por gerar estrutura de repetição while.
 *
 */
@RequiredArgsConstructor
public class LoopWhileCommand extends AbstractCommand {

    private final String condition;
    private final ArrayList<AbstractCommand> commands;

    @Override
    public String generateJavaCode() {
       final StringBuilder javaCode = new StringBuilder();

        javaCode.append("while (")
                .append(this.condition)
                .append(") {\n");

        this.commands.forEach(command->  javaCode.append(command.generateJavaCode()));
        javaCode.append("}");
        return javaCode.toString();
    }

}
