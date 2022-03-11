# Bad Code Galore

1. You **do not** have to import anything under `java.lang` to use it.

	1. ```java
		import java.lang.Math;
		```

2. Do not compare booleans.

	1. ```java
		if (something == true) { ... } // NANI?
		```

3. Use string concatentation sparingly.

	1. ```java
		@Override 
		public String toString() {
		    return plate + " (" + time + " mins away) NormalCab";
		}
		
		// BETTER AND MORE READABLE WAY!
		public String toString() {
		    return String.format("%s (%d mins away) NormalCab");
		}
		```

4. Don't abuse the `this` keyword when there are no name conflicts! Just the variable or function as it is! As long as it's within the class body, the class knows its calling itself! The rules of `super` keyword still applies if you are calling from super classes.