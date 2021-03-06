package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSP <V, D>{

    //Partes del CSP, variables, dominio y restricciones
    private List<V> variables;
    private Map<V, List<D>> domains; //una asignación donde cada variable puede tener compatibilidad con distintos valores para un dominio D
    private Map<V, List<Constraint<V,D>>> constraints = new HashMap<>(); //una restricción para cada variable


    //1er paso. se reciben las variables y dominios, se asignan y recorro las variables para crear en blanco las restricciones

    public CSP (List<V> variables, Map<V, List<D>> domains){
        this.variables = variables;
        this.domains = domains;

        for (V variable: variables) {
            constraints.put(variable, new ArrayList<Constraint<V, D>>());//inicializar como vacío cada constraint
            //cada variable debe tener un dominio asignado
            //si por alguna razón esto no es así se indica el error.
            if (!domains.containsKey(variable)){
                throw new IllegalArgumentException("La variable " + variable + " no contiene un dominio");
            }
        }
    }

    //método utilitario para añadir restricciones al listado
    public void addConstraint(Constraint<V, D> constraint){
        for (V variable: constraint.variables) {
            //variable que se encuentra en el constraint también sea parte del CSP
            if (!this.variables.contains(variable)){
                throw new IllegalArgumentException("La variable " + variable + " no se encuentra en el CSP");
            }

            //obtener los constraints y añadir el que se adjuntó al problema
            constraints.get(variable).add(constraint);

        }
    }

    //chequear para una variable que se pueda tener completa la asignación
    //revisar todas las restricciones de la variable
    public boolean consistent(V variable, Map<V, D> assignment){
        for (Constraint<V, D> constraint:this.constraints.get(variable)) {
            if (!constraint.satisfied(assignment)){
                return false;
            }
        }

        return true;
    }

    public Map<V, D> backtrack(){
        return backtrack(new HashMap<>());
    }

    public Map<V, D> backtrack(Map<V, D> assignment){
        //if assignment is complete, línea 1
        if (variables.size() == assignment.size()){
            return  assignment;
        }

        //seleccionar una variable no asignada, línea 2
        V unassigned = variables.stream()
                .filter(v -> !assignment.containsKey(v))
                .findFirst().get();

        for (D value: domains.get(unassigned)) {//línea 3

            System.out.println("Variable: " + unassigned + " valor: " + value);

            //probar una asignación
            //1- Crear una copia de la asignación anterior, para tener área de trabajo y no alterar los datos
            Map<V, D> localAssignment = new HashMap<>(assignment);

            //2- Probar (asignar un valor)
            localAssignment.put(unassigned, value);

            //3- Verificar la consistencia de la asignación

            if (consistent(unassigned, localAssignment)){
                Map<V, D> result = backtrack(localAssignment);

                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }


}
