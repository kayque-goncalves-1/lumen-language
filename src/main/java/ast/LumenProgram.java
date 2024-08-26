package ast;


import datastructures.LumenSymbolTable;
import lombok.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/*
 *
 * Classe responsavel por gerar programa base.
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LumenProgram {

    private LumenSymbolTable symbolTable;

    private ArrayList<AbstractCommand> commands;

    public void generateTarget() {
        final StringBuilder javaCode = new StringBuilder();
        javaCode.append("import java.util.Scanner;\n")
                .append("public class GeneratedJavaClass{ \n")
                .append("public static void main(String args[]){\n ")
                .append(" Scanner scanner = new Scanner(System.in);\n");

        this.symbolTable.getAll()
                .forEach(symbol -> javaCode.append(symbol.generateJavaCode()).append("\n"));

        this.commands.forEach(command -> javaCode.append(command.generateJavaCode()).append("\n"));

        javaCode.append("  }");
        javaCode.append("}");

        buildJavaFile(javaCode);

    }

    private static void buildJavaFile(StringBuilder javaCode) {
        try {
            FileWriter fr = new FileWriter(new File("GeneratedJavaClass.java"));
            fr.write(javaCode.toString());
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
