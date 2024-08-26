package datastructures;

import java.util.HashMap;


/*
 *
 * Classe para interagir com o mapa de variaveis da lumen language
 *
 */
public class LumenVariableTable {

    private final HashMap<String, LumenVariable> map;

    public LumenVariableTable() {
        this.map = new HashMap<>();

    }

    public void add(LumenVariable variable) {
        this.map.put(variable.getName(), variable);
    }

    public boolean exists(String variableNme) {
        return this.map.get(variableNme) != null;
    }

    public LumenVariable get(String variableName) {
        return this.map.get(variableName);
    }
}
