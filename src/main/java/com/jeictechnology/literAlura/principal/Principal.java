package com.jeictechnology.literAlura.principal;

import com.jeictechnology.literAlura.model.DatosLibro;
import com.jeictechnology.literAlura.model.Libro;
import com.jeictechnology.literAlura.model.RespuestaLibro;
import com.jeictechnology.literAlura.repository.ILibroRepository;
import com.jeictechnology.literAlura.service.ConsumoAPI;
import com.jeictechnology.literAlura.service.ConvierteDatos;


import java.util.List;
import java.util.Scanner;


public class Principal {

    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private final ILibroRepository iLibroRepository;

    public Principal(ILibroRepository iLibroRepository) {
        this.iLibroRepository = iLibroRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    
                    --------SISTEMA BUSQUEDA DE LIBROS--------                    
                    1- Buscar libro por título
                    2- Libros disponibles
                    
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
                default:
                    System.out.println("Opción no válida");
            }
        }

    }

    private void buscarLibro() {
        DatosLibro datosLibro = obtenerDatosLibro();
        Libro libro = new Libro(datosLibro);
        if (iLibroRepository.existsByTituloAndAutores(libro.getTitulo(), libro.getAutores())){
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
                .forEach(libro -> System.out.println(libro.getTitulo()+" ("+libro.getAutores()+")"));
    }
}
