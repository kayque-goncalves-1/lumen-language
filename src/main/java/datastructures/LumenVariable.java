package datastructures;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LumenVariable extends LumenSymbol {

    public static final int DECIMAL = 0;
    public static final int INTEGER = 1;

    private int type;
    private String value;

    public LumenVariable(String name, int type, String value) {
        super(name, type);
        this.type = type;
        this.value = value;
    }

    public String generateJavaCode() {
        final String javaCode;

        if (type == DECIMAL) {
            javaCode = "double ";
        } else {
            javaCode = "int ";
        }

        return javaCode + " " + super.name + ";";
    }


}
