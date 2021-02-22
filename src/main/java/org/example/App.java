package org.example;

import java.util.Formattable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //CSP
        //VARIABLES
        List<String> variables = List.of("Western Australia", "Northern Territory",
                "Queensland", "South Australia", "New South Wales", "Victoria", "Tasmania");
        //DOMINIOS
        Map<String, List<String>> domains = new HashMap<>();
        for (var variable:variables) {
            domains.put(variable, List.of("rojo", "verde", "azul"));
        }

        //RESTRICCIONES
        CSP_AC3<String,String> problem = new CSP_AC3<>(variables, domains);
        problem.addConstraint(new AustraliaColoringConstraint("Western Australia", "Northern Territory"));
        problem.addConstraint(new AustraliaColoringConstraint("Western Australia", "South Australia"));
        problem.addConstraint(new AustraliaColoringConstraint("Northern Territory", "South Australia"));
        problem.addConstraint(new AustraliaColoringConstraint("Northern Territory", "Queensland"));
        problem.addConstraint(new AustraliaColoringConstraint("South Australia", "Queensland"));
        problem.addConstraint(new AustraliaColoringConstraint("New South Wales", "Queensland"));
        problem.addConstraint(new AustraliaColoringConstraint("New South Wales", "South Australia"));
        problem.addConstraint(new AustraliaColoringConstraint("Victoria", "South Australia"));
        problem.addConstraint(new AustraliaColoringConstraint("New South Wales", "Victoria"));
        problem.addConstraint(new AustraliaColoringConstraint("Tasmania", "Victoria"));

        var solution = problem.backtrack();
        System.out.println(solution);
    }
}
