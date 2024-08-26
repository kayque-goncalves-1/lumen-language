package ast;

import lombok.RequiredArgsConstructor;
import java.util.ArrayList;


/*
*
* Classe responsavel por gerar if...else
*
*
*/
@RequiredArgsConstructor
public class DecisionCommand extends AbstractCommand {

    private final String condition;
    private final ArrayList<AbstractCommand> requiredStructureCommands;
    private final ArrayList<AbstractCommand> optionalStructureCommands;

    @Override
    public String generateJavaCode() {
        final StringBuilder javaCode = new StringBuilder();
        javaCode.append("if (")
                .append(condition)
                .append(") {\n");

        requiredStructureCommands.forEach(command -> javaCode.append(command.generateJavaCode()));

        javaCode.append("}");

        if (this.optionalStructureCommands!=null && !optionalStructureCommands.isEmpty()) {
            javaCode.append("else {\n");

            optionalStructureCommands.forEach(command -> javaCode.append(command.generateJavaCode()));

            javaCode.append("}\n");
        }
        return javaCode.toString();
    }

}
