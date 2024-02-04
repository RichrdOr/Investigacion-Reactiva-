/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.reactivo;

import io.reactivex.rxjava3.core.Observable;

/**
 *
 * @author Usuario
 */
public class Reactivo {

    public static void main(String[] args) {
        Observable<Persona> personasObservable = Observable.just(
                new Persona("Juan", 25),
                new Persona("María", 30),
                new Persona("Pedro", 22),
                new Persona("Laura", 35),
                new Persona("Carlos", 28)
        );

        // solo filtra a las personas mayores a 25 años
        Observable<Persona> personasMayoresObservable = personasObservable
                .filter(persona -> persona.getEdad() > 25);

        // se aplica un mapeo para obtener nombres concatenados
        Observable<String> nombresObservable = personasMayoresObservable
                .map(persona -> persona.getNombre() + " - " + persona.getEdad() + " años");

        // se utiliza flatMap para dividir y emitir cada nombre por separado
        Observable<String> nombresSeparadosObservable = nombresObservable
                .flatMap(nombre -> Observable.fromArray(nombre.split(" - ")));

        // Imprimir los nombres resultantes
        nombresSeparadosObservable.subscribe(
                nombre -> System.out.println("Nombre: " + nombre),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Proceso completado")
        );
    }

    static class Persona {

        private final String nombre;
        private final int edad;

        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        public String getNombre() {
            return nombre;
        }

        public int getEdad() {
            return edad;
        }
    }
}
