package ast;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;


/*
 *
 * Classe responsavel por gerar do...while
 *
  */
@RequiredArgsConstructor
public class LoopDoWhileCommand extends AbstractCommand {

    private final String condition;
    private final ArrayList<AbstractCommand> commands;

    @Override
    public String generateJavaCode() {
        final StringBuilder javaCode = new StringBuilder();

        javaCode.append("do {\n");
        this.commands.forEach(command -> javaCode.append(command.generateJavaCode()));

        javaCode.append("\n}while(")
                .append(this.condition)
                .append(");\n");
        return javaCode.toString();
    }
}
