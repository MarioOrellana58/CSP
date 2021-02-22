package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexConstraint  extends Constraint<String, String>{

    private String node1, node2;

    public ComplexConstraint(String node1, String node2){
        super(List.of(node1, node2));
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public boolean satisfied(Map<String, String> assignment) {

        //Debo chequear que
        //la variable no esté asignada
        if (!assignment.containsKey(node1) || !assignment.containsKey(node2)){//como está vacío entonces es consistente, solo no está asignado
            return true;
        }

        // lugar 1 <> lugar 2
        return !assignment.get(node1).equals(assignment.get(node2));
    }

    public static void main( String[] args )
    {
        //CSP
        //VARIABLES
        List<String> variables = List.of("A", "B", "C", "D", "E",
                                         "F", "G", "H", "I", "J",
                                         "K", "L", "M", "N", "O",
                                         "P", "Q", "R", "S", "T",
                                         "U", "V", "W", "X", "Y",
                                         "Z", "AA"
                                        );
        //DOMINIOS
        Map<String, List<String>> domains = new HashMap<>();
        for (var variable:variables) {
            domains.put(variable, List.of("C", "M", "Y", "K"));
        }

        //RESTRICCIONES
        CSP_AC3<String,String> problem = new CSP_AC3<>(variables, domains);
        problem.addConstraint(new ComplexConstraint("A", "B"));
        problem.addConstraint(new ComplexConstraint("A", "V"));
        problem.addConstraint(new ComplexConstraint("B", "V"));
        problem.addConstraint(new ComplexConstraint("C", "F"));
        problem.addConstraint(new ComplexConstraint("C", "H"));
        problem.addConstraint(new ComplexConstraint("C", "D"));
        problem.addConstraint(new ComplexConstraint("D", "G"));
        problem.addConstraint(new ComplexConstraint("D", "H"));
        problem.addConstraint(new ComplexConstraint("E", "I"));
        problem.addConstraint(new ComplexConstraint("F", "K"));
        problem.addConstraint(new ComplexConstraint("G", "K"));
        problem.addConstraint(new ComplexConstraint("H", "V"));
        problem.addConstraint(new ComplexConstraint("H", "M"));
        problem.addConstraint(new ComplexConstraint("I", "M"));
        problem.addConstraint(new ComplexConstraint("J", "N"));
        problem.addConstraint(new ComplexConstraint("J", "K"));
        problem.addConstraint(new ComplexConstraint("K", "O"));
        problem.addConstraint(new ComplexConstraint("L", "P"));
        problem.addConstraint(new ComplexConstraint("L", "Q"));
        problem.addConstraint(new ComplexConstraint("M", "Q"));
        problem.addConstraint(new ComplexConstraint("P", "S"));
        problem.addConstraint(new ComplexConstraint("P", "T"));
        problem.addConstraint(new ComplexConstraint("P", "U"));
        problem.addConstraint(new ComplexConstraint("R", "W"));
        problem.addConstraint(new ComplexConstraint("R", "X"));
        problem.addConstraint(new ComplexConstraint("R", "S"));
        problem.addConstraint(new ComplexConstraint("S", "X"));
        problem.addConstraint(new ComplexConstraint("S", "Y"));
        problem.addConstraint(new ComplexConstraint("S", "Z"));
        problem.addConstraint(new ComplexConstraint("T", "Z"));
        problem.addConstraint(new ComplexConstraint("T", "AA"));
        problem.addConstraint(new ComplexConstraint("T", "U"));
        problem.addConstraint(new ComplexConstraint("U", "AA"));
        problem.addConstraint(new ComplexConstraint("V", "W"));
        problem.addConstraint(new ComplexConstraint("W", "X"));
        problem.addConstraint(new ComplexConstraint("X", "Y"));
        problem.addConstraint(new ComplexConstraint("Y", "Z"));
        problem.addConstraint(new ComplexConstraint("Z", "AA"));




        var solution = problem.backtrack();
        System.out.println(solution);
    }
}
