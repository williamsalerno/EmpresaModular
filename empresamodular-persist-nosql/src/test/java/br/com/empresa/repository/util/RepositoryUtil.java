package br.com.empresa.repository.util;

import br.com.empresa.repository.EmpresaRepository;

public class RepositoryUtil {

    private static int valorPagina;
    public static final int PAGINA = valorPagina;

    private RepositoryUtil() {

    }

    public static void manageNumberPages(int pageNumber, int pageElements) {
        valorPagina = pageElements * pageNumber;
    }
}
