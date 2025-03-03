package constructionEmissions;

import java.util.HashSet;
import java.util.Set;

public class constructionmethod {
     private Set<String> methods;

    public constructionmethod() {
        this.methods = new HashSet<>();
        methods.add("Traditional");
        methods.add("Green Building");
        methods.add("Prefabricated");
    }

    public Set<String> getMethods() {
        return methods;
    }

    public boolean addMethod(String method) {
        return methods.add(method);
    }

    public boolean removeMethod(String method) {
        return methods.remove(method);
    }

    public boolean containsMethod(String method) {
        return methods.contains(method);
    }
}
