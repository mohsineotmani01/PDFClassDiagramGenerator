package org.myproject.source;

import org.myproject.annotations.ChildClass;

@ChildClass(superClass = "WildAnimal")

class Elephant extends WildAnimal {
    double trunkLength;  // Longueur de la trompe

    // Implémentation de la méthode abstraite de WildAnimal
    public void hunt() {
        // Les éléphants ne chassent pas, donc cette méthode peut être vide ou faire autre chose.
        System.out.println(name + " the elephant is grazing.");
    }

    // Implémentation de l'interface Animal
    public void eat() {
        System.out.println(name + " the elephant is eating.");
    }

    public void sleep() {
        System.out.println(name + " the elephant is sleeping.");
    }

    // Méthode spécifique à Elephant
    public void trumpet() {
        System.out.println(name + " the elephant is trumpeting!");
    }
}