package datastructures;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class LumenSymbol {
	
	protected final String name;
    protected final  int type;
	
	public abstract String generateJavaCode();
}
