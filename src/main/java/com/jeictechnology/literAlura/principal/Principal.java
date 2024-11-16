package com.jeictechnology.literAlura.principal;

import com.jeictechnology.literAlura.model.*;
import com.jeictechnology.literAlura.repository.IAutorRepository;
import com.jeictechnology.literAlura.repository.ILibroRepository;
import com.jeictechnology.literAlura.service.ConsumoAPI;
import com.jeictechnology.literAlura.service.ConvierteDatos;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Principal {

    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private final ILibroRepository iLibroRepository;

    private final IAutorRepository iAutorRepository;

    public Principal(ILibroRepository iLibroRepository, IAutorRepository iAutorRepository) {
        this.iLibroRepository = iLibroRepository;
        this.iAutorRepository = iAutorRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    
                    --------SISTEMA BUSQUEDA DE LIBROS--------                    
                    1- Buscar libro por título
                    2- Libros disponibles
                    3- Autores 
                    4- Autores vivos en un determinado año
                    5- Libros por idioma
                    
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    autoresVivosPorAnio();
                    break;
                case 5:
                    librosPorIdioma();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

    }

    private void buscarLibro() {
        DatosLibro datosLibro = obtenerDatosLibro();
        Libro libro = new Libro(datosLibro);
        if (iLibroRepository.existsByTituloAndAutorName(libro.getTitulo(), libro.getAutor().getName())){
            System.out.println("******************** Datos Libro buscado ********************");
            System.out.println(libro.toString());
        }else {
            System.out.println("******************** Datos Libro buscado ********************");
            System.out.println(libro.toString());
            iLibroRepository.save(libro);
        }
    }

    private DatosLibro obtenerDatosLibro() {
        System.out.println("Escribe el nombre del libro que desea buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));

        RespuestaLibro respuestaLibro = convierteDatos.obtenerDatos(json, RespuestaLibro.class);

        if (respuestaLibro.getResultados() != null && !respuestaLibro.getResultados().isEmpty()){
            return respuestaLibro.getResultados().get(0);
        }else {
            System.out.println("No se encontraron resultados para el libro especificado.");
            return null;
        }
    }

    private void listarLibros() {
        List<Libro> listaLibros = iLibroRepository.findAll();
        System.out.println("-*-*-*-*-*-*-*-*-* Lista de libros disponibles *-*-*-*-*-*-*-*-"+"\n");
        listaLibros.stream()
                .forEach(libro -> System.out.println(libro.getTitulo()+" ("+libro.getAutor().getName()+")"));
    }

    private void listarAutores(){
        List<Autor> listaAutores = iAutorRepository.findAll();
        System.out.println("-*-*-*-*-*-*-*-*-* Lista de autores disponibles *-*-*-*-*-*-*-*-"+"\n");
        listaAutores.stream()
                .forEach(autor -> System.out.println(autor.getName()));
    }

    private void autoresVivosPorAnio(){
        System.out.println("Por favor ingresa el año inicial");
        var anioInicial = teclado.nextInt();

        System.out.println("Por favor ingresa el año final");
        var anioFinal = teclado.nextInt();

        List<Autor> listaAutores = iAutorRepository.findAll();

        List<Autor> listaAutoresVivos = listaAutores.stream()
                .filter(autor -> autor.getAnioNacimiento() >= anioInicial && autor.getAnioFallecimiento() <= anioFinal)
                .collect(Collectors.toList());
        listaAutoresVivos.stream()
                .forEach(a -> System.out.println("Autores vivos entre " + anioInicial + " - " + anioFinal + "\n"
                        + a.getName()));
    }

    private void librosPorIdioma(){
        System.out.println("Selecciona el idioma del libro (por ejemplo: 'es', 'en', 'fr', etc.): ");
        var idioma = teclado.nextLine();

        List<Libro> listaLibros = iLibroRepository.findByLenguaje(idioma);

        listaLibros.stream()
                .forEach(libro -> System.out.println("\n"+libro.getTitulo()+" ("+libro.getAutor().getName()+")"));
    }
}
