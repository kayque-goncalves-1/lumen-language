package datastructures;

import java.util.ArrayList;
import java.util.HashMap;

/*
 *
 * Classe para interagir com o mapa de simbolos da lumen language
 *
  */
public class LumenSymbolTable {
	
	private final HashMap<String, LumenSymbol> map;
	
	public LumenSymbolTable() {
		this.map = new HashMap<>();
	}
	
	public void add(LumenSymbol symbol) {
		this.map.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String symbolName) {
		return this.map.get(symbolName) != null;
	}
	
	public LumenSymbol get(String symbolName) {
		return this.map.get(symbolName);
	}
	
	public ArrayList<LumenSymbol> getAll(){

        return new ArrayList<>(this.map.values());
	}
	
}
