import exceptions.LumenSemanticException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import static util.AntlrUtils.generateCode;

public class LumenApplication
{

    public static  void main(String [] args){

        try {
            LumenLanguageLexer lexer;
            LumenLanguageParser parser;

            lexer = new LumenLanguageLexer(CharStreams.fromFileName("input.lumen"));

            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            parser = new LumenLanguageParser(tokenStream);

            parser.prog();

            System.out.println("Compilado com sucesso!");

            generateCode();

        }
        catch(LumenSemanticException ex) {
            System.err.println("Erro semântico: "+ex.getMessage());
        }
        catch(Exception ex) {
            System.err.println("Erro inesperado: "+ex.getMessage());
        }

    }
}
