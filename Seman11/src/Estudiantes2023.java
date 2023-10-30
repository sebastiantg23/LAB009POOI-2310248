import java.util.*;
import javax.swing.*;
import java.io.*;

public class Estudiantes2023 {
    private static ArrayList<Estudiante> estudiantes;

    public Estudiantes2023() {
        estudiantes = new ArrayList<>();
        cargarArchivo();
    }
    
    public double obtenerSumaPensiones(){
        double sumaPensiones = 0;
        for (Estudiante estudiante : estudiantes){
            sumaPensiones += estudiante.getPension();
        }
        return sumaPensiones;
    }
    
    public boolean existeEstudiante (int codigo){
        for (Estudiante estudiante : estudiantes){      
            if (estudiante.getCodigo() == codigo){
                return true;
            }
        }
        return false;
    }
    
    
    
    
    public void modificar (int codigo, Estudiante nuevoEstudiante){
        for (int i = 0; i < estudiantes.size(); i++){
            if (estudiantes.get(i).getCodigo() == codigo){
                estudiantes.set(i, nuevoEstudiante);
                break;
            }
        }
    }
    public void adicionar(Estudiante estudiante) {
        //Introduce c?digo
        estudiantes.add(estudiante);
    }
    
    public void registrar(Estudiante estudiante){
        Estudiante estudiante1 = estudiantes.get(tamano()-1);
        int codigoUltimoEst = estudiante1.getCodigo();
        estudiante.setCodigo(codigoUltimoEst+1);
        estudiantes.add(estudiante);
    }

    public void eliminar(Estudiante estudiante) {
        //Introduce c?digo
        estudiantes.remove(estudiante);
    }

    public Estudiante obtener(int pos) {
	//Introduce c?digo
        estudiantes.get(pos);
        return null;
    }

    public Estudiante buscar(int codigo) {
        for (int i = 0; i < estudiantes.size(); i++)
            if (codigo == estudiantes.get(i).getCodigo())
                return estudiantes.get(i);
	  return null;
    }

    public int tamano() {
        //Introduce c?digo 
        return estudiantes.size();
    }

    public void listarEstudiantes(){
        //Introduce c?digo  
        for(Estudiante estudiante:estudiantes){
            System.out.println(estudiante.toString());
        }
    }
    
    public void listarEstudiantesPorApellidos(){
        List<Estudiante> estudiantesOrdenadosPorPension = new ArrayList<>(estudiantes);
        Collections.sort(estudiantesOrdenadosPorPension, (e1, e2) -> Double.compare(e1.getPension(), e2.getPension()));
        for (Estudiante estudiante : estudiantesOrdenadosPorPension){
            System.out.println(estudiante);
        }
    }
    
    public void listarEstudiantePorPensionAscendente(){
        List<Estudiante> estudiantesOrdenadosPorPension = new ArrayList<>(estudiantes);
        Collections.sort(estudiantesOrdenadosPorPension, (e1, e2) -> Double.compare(e1.getPension(), e2.getPension()));
        for (Estudiante estudiante : estudiantesOrdenadosPorPension){
            System.err.println(estudiante);
        }
    }

    // M?todos para manipular el archivo de texto
    private void readFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String linea;
        while ((linea = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(linea, ",");
            createStudent(st);
        }
        br.close();
    }

	// M?todo que a?adir a los estudiantes del archivo .txt en el arrayList estudiantes.
    private void createStudent(StringTokenizer st){
        int codigo = Integer.parseInt(st.nextToken().trim());
        String nombre = st.nextToken().trim();
        int ciclo = Integer.parseInt(st.nextToken().trim());
        double pension = Double.parseDouble(st.nextToken().trim());
        Estudiante estudiante = new Estudiante(codigo, nombre, ciclo, pension);
        adicionar(estudiante);
    }

    // M?todos para manipular el archivo de texto
    private void cargarArchivo() {
        try {
            File file = new File("./src/estudiantes.txt");
            Class<Estudiantes2023> clazz = Estudiantes2023.class;
            InputStream inputStream = clazz.getResourceAsStream("/estudiantes.txt");
            if (file.exists()) {
                readFromInputStream(inputStream);
            } else
                JOptionPane.showMessageDialog(null,
                        "El archivo estudiantes.txt no existe");
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Se produjo un error= " + x);
        }
    }
}