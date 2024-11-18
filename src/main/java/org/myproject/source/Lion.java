package org.myproject.source;


import org.myproject.annotations.ChildClass;

@ChildClass(superClass = "WildAnimal")
class Lion extends WildAnimal {
    double maneLength;  // Longueur de la crinière

    // Implémentation de la méthode abstraite de WildAnimal
    public void hunt() {
        System.out.println(name + " the lion is hunting.");
    }

    // Implémentation de l'interface Animal
    public void eat() {
        System.out.println(name + " the lion is eating.");
    }

    public void sleep() {
        System.out.println(name + " the lion is sleeping.");
    }

    // Méthode spécifique à Lion
    public void roar() {
        System.out.println(name + " the lion is roaring!");
    }
}
