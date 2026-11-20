package bridge;

import java.io.File;
import java.util.List;

public interface FormatoReporte {
    File exportar(String titulo, String periodo, String[] columnas, List<String[]> filas, String pie) throws Exception;
}
