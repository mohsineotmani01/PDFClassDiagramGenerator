package org.myproject.source;


import org.myproject.annotations.ChildClass;

@ChildClass(superClass = "Animal")
abstract class WildAnimal implements Animal {
    String name;
    int age;
    String habitat;

    // Méthode spécifique aux animaux sauvages
    abstract void hunt();  // Méthode abstraite pour la chasse
}