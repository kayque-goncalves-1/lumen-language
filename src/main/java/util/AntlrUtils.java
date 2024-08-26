package util;

import ast.AbstractCommand;
import ast.LumenProgram;
import datastructures.LumenSymbol;
import datastructures.LumenSymbolTable;
import datastructures.LumenVariable;
import datastructures.LumenVariableTable;
import exceptions.LumenSemanticException;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Stack;

/*
 *
 * Classe util para interação com o antlr
 *
 */
@Setter
public final class AntlrUtils {

    public static int type;
    public static String variableName;
    public static String variableValue;
    public static String readId;
    public static String writeId;

    public static String expressionId;
    public static String expressionContent;
    public static String expressionDecision;
    public static String expressionRecursion;

    public static LumenSymbolTable symbolTable = new LumenSymbolTable();
    public static LumenVariableTable variableTable = new LumenVariableTable();

    public static LumenSymbol symbol;
    public static LumenVariable variable;
    public static LumenProgram program = new LumenProgram();

    public static ArrayList<AbstractCommand> currentThread;
    public static Stack<ArrayList<AbstractCommand>> commandStack = new Stack<>();

    public static ArrayList<AbstractCommand> requiredStructureCommands;
    public static ArrayList<AbstractCommand> optionalStructureCommands;

    public static ArrayList<AbstractCommand> whileCommands;

    public static void verifySymbolId(String id) {
        if (!symbolTable.exists(id)) {
            throw new LumenSemanticException("Simbolo: " + id + " não declarado!");
        }
    }

    public static void verifySymbolValue() {
        if (symbol.getType() == 1 && expressionContent.contains(".")) {
            throw new LumenSemanticException("Simbolo " + symbol.getName() + " com tipo inteiro não deve conter .");
        }
    }

    public static void generateCode() {
        program.generateTarget();
    }

}
