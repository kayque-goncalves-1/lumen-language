grammar LumenLanguage;

@header{
	import datastructures.LumenSymbol;
	import datastructures.LumenVariable;
	import datastructures.LumenSymbolTable;
	import exceptions.LumenSemanticException;
	import ast.LumenProgram;
	import ast.AbstractCommand;
	import ast.ReadCommand;
	import ast.LoopWhileCommand;
	import ast.LoopDoWhileCommand;
	import ast.WriteCommand;
	import ast.DeclarationCommand;
	import ast.DecisionCommand;
	import static util.AntlrUtils.*;
	}


prog	: 'inicio' declarationBlock commandBlock  'fim;'
           {
           program.setSymbolTable(symbolTable);
           program.setCommands(commandStack.pop());
           }
		;

declarationBlock    :  (declaration)+
        ;


declaration :  type ID  {
	                  variableName = _input.LT(-1).getText();
	                  variableValue = null;
	                  symbol = new LumenVariable(variableName, type, variableValue);

	                  if (!symbolTable.exists(variableName)){
	                     symbolTable.add(symbol);
	                  }
	                  else{
	                  	 throw new LumenSemanticException("Simbolo "+variableName+" já foi declarado!");
	                  }
                    }
              (  VIR
              	 ID {
	                  variableName = _input.LT(-1).getText();
	                  variableValue = null;
	                  symbol = new LumenVariable(variableName, type, variableValue);
	                  if (!symbolTable.exists(variableName)){
	                     symbolTable.add(symbol);
	                  }
	                  else{
	                  	 throw new LumenSemanticException("Simbolo "+variableName+" já foi declarado!");
	                  }
                    }
              )*
               SC
           ;

type       : 'decimal' { type = LumenVariable.DECIMAL;  }
           | 'inteiro'  { type = LumenVariable.INTEGER;  }
           ;

commandBlock	: {
            currentThread = new ArrayList<AbstractCommand>();
	        commandStack.push(currentThread);
          }
          (command)+
		;


command		:readCommand
 		|  writeCommand
 		|  declarationCommand
 		|  decisionCommand
 		|  loopWhileCommand
 		|  loopDoWhileCommand
		;

readCommand	: 'leia' AP
                     ID {
                          verifySymbolId(_input.LT(-1).getText());
                     	  readId = _input.LT(-1).getText();
                        }
                     FP
                     SC

              {
              	LumenVariable var = (LumenVariable)symbolTable.get(readId);
                variableTable.add(var);
              	ReadCommand cmd = new ReadCommand(readId, var);
              	commandStack.peek().add(cmd);
              }
			;

writeCommand	: 'escreva'
                 AP
                 ID {
                      verifySymbolId(_input.LT(-1).getText());
	                  writeId = _input.LT(-1).getText();
                          if(!variableTable.exists(writeId)){
                                        throw new LumenSemanticException("Simbolo " + writeId + " nao tem valor");

                          }

                     }
                 FP
                 SC
               {
               	  WriteCommand cmd = new WriteCommand(writeId);
               	  commandStack.peek().add(cmd);
               }
			;

declarationCommand	:  ID {
                    verifySymbolId(_input.LT(-1).getText());
                    expressionId = _input.LT(-1).getText();
                    symbol = symbolTable.get(_input.LT(-1).getText());
                   }
               ATTR { expressionContent = ""; }
               expression
               SC
               {
                 verifySymbolValue();
                 variable = new LumenVariable(expressionId, type, expressionContent);
                 variableTable.add(variable);

               	 DeclarationCommand cmd = new DeclarationCommand(expressionId, expressionContent);
               	 commandStack.peek().add(cmd);
               }
			;


decisionCommand  :  'se' AP
                    ID    { expressionDecision = _input.LT(-1).getText(); }
                    OPREL { expressionDecision += _input.LT(-1).getText(); }
                    (ID | NUMBER | INTEGER) {expressionDecision += _input.LT(-1).getText(); }
                    FP
                    ACH
                    {
                      currentThread = new ArrayList<AbstractCommand>();
                      commandStack.push(currentThread);
                    }
                    (command)+

                    FCH
                    {
                       requiredStructureCommands = commandStack.pop();
                    }
                   ('senao'
                   	 ACH
                   	 {
                   	 	currentThread = new ArrayList<AbstractCommand>();
                   	 	commandStack.push(currentThread);
                   	 }
                   	(command+)
                   	FCH
                   	{
                   		optionalStructureCommands = commandStack.pop();

                   	}
                   )?
                   {
                   DecisionCommand cmd = new DecisionCommand(expressionDecision, requiredStructureCommands, optionalStructureCommands);
                                      		commandStack.peek().add(cmd);
                   }
            ;

expression		:  termo (
	             OP  { expressionContent += _input.LT(-1).getText();}
	            termo
	            )*
			;

termo		: ID {
                   verifySymbolId(_input.LT(-1).getText());
	               expressionContent += _input.LT(-1).getText();
                 }
            |
              NUMBER
              {
              	expressionContent += _input.LT(-1).getText();
              }
              |
                            INTEGER
                            {
                            	expressionContent += _input.LT(-1).getText();
                            }
			;
loopWhileCommand: 'enquanto' AP
                                 ID    { expressionDecision = _input.LT(-1).getText(); }
                                 OPREL { expressionDecision += _input.LT(-1).getText(); }
                                 (ID | NUMBER | INTEGER) {expressionDecision += _input.LT(-1).getText(); }
                                 FP
                                 ACH
                                 {
                                   currentThread = new ArrayList<AbstractCommand>();
                                   commandStack.push(currentThread);
                                 }
                                 (command)+
                                 FCH
                                 {
                                    whileCommands = commandStack.pop();
                                    LoopWhileCommand cmd = new LoopWhileCommand(expressionDecision, whileCommands);
                   		            commandStack.peek().add(cmd);
                                 }
                                 SC
                         ;

loopDoWhileCommand : 'faça' ACH
                                {
                                  currentThread = new ArrayList<AbstractCommand>();
                                  commandStack.push(currentThread);
                                }
                                (command)+
                                FCH
                                'enquanto'
                                AP
                                ID    { expressionDecision = _input.LT(-1).getText(); }
                                OPREL { expressionDecision += _input.LT(-1).getText(); }
                                (ID | NUMBER | INTEGER) {expressionDecision += _input.LT(-1).getText(); }
                                FP
                                SC {
                                    expressionRecursion = _input.LT(-1).getText();
                                    whileCommands = commandStack.pop();
                                    LoopDoWhileCommand cmd = new LoopDoWhileCommand(expressionDecision, whileCommands);
                                    commandStack.peek().add(cmd);
                                   };
AP	: '('
	;

FP	: ')'
	;

SC	: ';'
	;

OP	: '+' | '-' | '*' | '/'
	;

ATTR : '='
	 ;

VIR  : ','
     ;

ACH  : '{'
     ;

FCH  : '}'
     ;


OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;

ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;

NUMBER	: [0-9]+ ('.' [0-9]+)+
		;

INTEGER	: [0-9]+
		;


WS	: (' ' | '\t' | '\n' | '\r') -> skip;