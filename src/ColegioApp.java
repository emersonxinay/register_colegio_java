package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ColegioApp extends JFrame {
  private JTextArea resultadosArea;

  public ColegioApp() {
    setTitle("Sistema de Registro de Colegio");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Crear un JTabbedPane para organizar las vistas
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Alumnos", crearPanelAlumnos());
    tabbedPane.addTab("Cursos", crearPanelCursos());
    tabbedPane.addTab("Docentes", crearPanelDocentes());

    add(tabbedPane, BorderLayout.CENTER);

    // Área de texto para mostrar resultados
    resultadosArea = new JTextArea();
    resultadosArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(resultadosArea);
    add(scrollPane, BorderLayout.SOUTH);
  }

  private JPanel crearPanelDocentes() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // Formulario para registrar docentes
    JPanel formulario = new JPanel();
    formulario.setLayout(new GridLayout(6, 2, 10, 10));
    formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JTextField idField = new JTextField(); // Para editar/eliminar
    JTextField nombreField = new JTextField();
    JTextField apellidoField = new JTextField();

    formulario.add(new JLabel("ID del Docente:"));
    formulario.add(idField);
    formulario.add(new JLabel("Nombre del Docente:"));
    formulario.add(nombreField);
    formulario.add(new JLabel("Apellido del Docente:"));
    formulario.add(apellidoField);

    JButton registrarButton = new JButton("Registrar Docente");
    registrarButton.addActionListener(e -> registrarDocente(nombreField, apellidoField));
    formulario.add(registrarButton);

    JButton consultarButton = new JButton("Consultar Docentes");
    consultarButton.addActionListener(e -> consultarDocentes());
    formulario.add(consultarButton);

    JButton editarButton = new JButton("Editar Docente");
    editarButton.addActionListener(e -> editarDocente(idField, nombreField, apellidoField));
    formulario.add(editarButton);

    JButton eliminarButton = new JButton("Eliminar Docente");
    eliminarButton.addActionListener(e -> eliminarDocente(idField));
    formulario.add(eliminarButton);

    JButton verDetallesButton = new JButton("Ver Detalles");
    verDetallesButton.addActionListener(e -> verDetallesDocente(idField));
    formulario.add(verDetallesButton);

    panel.add(formulario, BorderLayout.NORTH);
    return panel;
  }

  private JPanel crearPanelCursos() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // Formulario para registrar cursos
    JPanel formulario = new JPanel();
    formulario.setLayout(new GridLayout(6, 2, 10, 10));
    formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JTextField idField = new JTextField(); // Para editar/eliminar
    JTextField nombreCursoField = new JTextField();
    JTextField docenteIdField = new JTextField();

    formulario.add(new JLabel("ID del Curso:"));
    formulario.add(idField);
    formulario.add(new JLabel("Nombre del Curso:"));
    formulario.add(nombreCursoField);
    formulario.add(new JLabel("ID del Docente:"));
    formulario.add(docenteIdField);

    JButton registrarButton = new JButton("Registrar Curso");
    registrarButton.addActionListener(e -> registrarCurso(nombreCursoField, docenteIdField));
    formulario.add(registrarButton);

    JButton consultarButton = new JButton("Consultar Cursos");
    consultarButton.addActionListener(e -> consultarCursos());
    formulario.add(consultarButton);

    JButton editarButton = new JButton("Editar Curso");
    editarButton.addActionListener(e -> editarCurso(idField, nombreCursoField, docenteIdField));
    formulario.add(editarButton);

    JButton eliminarButton = new JButton("Eliminar Curso");
    eliminarButton.addActionListener(e -> eliminarCurso(idField));
    formulario.add(eliminarButton);

    JButton verDetallesButton = new JButton("Ver Detalles");
    verDetallesButton.addActionListener(e -> verDetallesCurso(idField));
    formulario.add(verDetallesButton);

    panel.add(formulario, BorderLayout.NORTH);
    return panel;
  }

  private JPanel crearPanelAlumnos() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // Formulario para registrar alumnos
    JPanel formulario = new JPanel();
    formulario.setLayout(new GridLayout(7, 2, 10, 10));
    formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JTextField nombreField = new JTextField();
    JTextField apellidoField = new JTextField();
    JTextField fechaNacimientoField = new JTextField();
    JTextField cursoIdField = new JTextField();
    JTextField idField = new JTextField(); // Para editar/eliminar

    formulario.add(new JLabel("ID del Alumno:"));
    formulario.add(idField);
    formulario.add(new JLabel("Nombre:"));
    formulario.add(nombreField);
    formulario.add(new JLabel("Apellido:"));
    formulario.add(apellidoField);
    formulario.add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
    formulario.add(fechaNacimientoField);
    formulario.add(new JLabel("ID del Curso:"));
    formulario.add(cursoIdField);

    JButton registrarButton = new JButton("Registrar Alumno");
    registrarButton
        .addActionListener(e -> registrarAlumno(nombreField, apellidoField, fechaNacimientoField, cursoIdField));
    formulario.add(registrarButton);

    JButton consultarButton = new JButton("Consultar Alumnos");
    consultarButton.addActionListener(e -> consultarAlumnos());
    formulario.add(consultarButton);

    JButton editarButton = new JButton("Editar Alumno");
    editarButton
        .addActionListener(e -> editarAlumno(idField, nombreField, apellidoField, fechaNacimientoField, cursoIdField));
    formulario.add(editarButton);

    JButton eliminarButton = new JButton("Eliminar Alumno");
    eliminarButton.addActionListener(e -> eliminarAlumno(idField));
    formulario.add(eliminarButton);

    JButton verDetallesButton = new JButton("Ver Detalles");
    verDetallesButton.addActionListener(e -> verDetallesAlumno(idField));
    formulario.add(verDetallesButton);

    panel.add(formulario, BorderLayout.NORTH);
    return panel;
  }

  private void registrarAlumno(JTextField nombreField, JTextField apellidoField, JTextField fechaNacimientoField,
      JTextField cursoIdField) {
    String nombre = nombreField.getText();
    String apellido = apellidoField.getText();
    String fechaNacimiento = fechaNacimientoField.getText();
    String cursoId = cursoIdField.getText();

    if (nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento.isEmpty() || cursoId.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "INSERT INTO alumnos (nombre, apellido, fecha_nacimiento, curso_id) VALUES (?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, nombre);
      stmt.setString(2, apellido);
      stmt.setDate(3, Date.valueOf(fechaNacimiento));
      stmt.setInt(4, Integer.parseInt(cursoId));
      stmt.executeUpdate();
      JOptionPane.showMessageDialog(this, "Alumno registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al registrar alumno: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void consultarAlumnos() {
    resultadosArea.setText("");
    try (Connection conn = DatabaseConnection.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
        int cursoId = rs.getInt("curso_id");
        resultadosArea.append(String.format("ID: %d, Nombre: %s %s, Fecha Nacimiento: %s, Curso ID: %d\n",
            id, nombre, apellido, fechaNacimiento, cursoId));
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al consultar alumnos: " + e.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void editarAlumno(JTextField idField, JTextField nombreField, JTextField apellidoField,
      JTextField fechaNacimientoField, JTextField cursoIdField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del alumno es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);
    String nombre = nombreField.getText();
    String apellido = apellidoField.getText();
    String fechaNacimiento = fechaNacimientoField.getText();
    String cursoId = cursoIdField.getText();

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "UPDATE alumnos SET nombre = ?, apellido = ?, fecha_nacimiento = ?, curso_id = ? WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, nombre);
      stmt.setString(2, apellido);
      stmt.setDate(3, Date.valueOf(fechaNacimiento));
      stmt.setInt(4, Integer.parseInt(cursoId));
      stmt.setInt(5, id);
      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated > 0) {
        JOptionPane.showMessageDialog(this, "Alumno actualizado correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún alumno con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al editar alumno: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void eliminarAlumno(JTextField idField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del alumno es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "DELETE FROM alumnos WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      int rowsDeleted = stmt.executeUpdate();
      if (rowsDeleted > 0) {
        JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún alumno con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al eliminar alumno: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void verDetallesAlumno(JTextField idField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del alumno es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "SELECT * FROM alumnos WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
        int cursoId = rs.getInt("curso_id");

        String detalles = String.format("ID: %d\nNombre: %s %s\nFecha de Nacimiento: %s\nCurso ID: %d",
            id, nombre, apellido, fechaNacimiento, cursoId);

        JOptionPane.showMessageDialog(this, detalles, "Detalles del Alumno", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún alumno con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al consultar detalles del alumno: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  // Métodos similares para Cursos y Docentes...
  private void registrarCurso(JTextField nombreCursoField, JTextField docenteIdField) {
    String nombreCurso = nombreCursoField.getText();
    String docenteId = docenteIdField.getText();

    if (nombreCurso.isEmpty() || docenteId.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "INSERT INTO cursos (nombre_curso, docente_id) VALUES (?, ?)";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, nombreCurso);
      stmt.setInt(2, Integer.parseInt(docenteId));
      stmt.executeUpdate();
      JOptionPane.showMessageDialog(this, "Curso registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al registrar curso: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void consultarCursos() {
    resultadosArea.setText("");
    try (Connection conn = DatabaseConnection.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM cursos");
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombreCurso = rs.getString("nombre_curso");
        int docenteId = rs.getInt("docente_id");
        resultadosArea
            .append(String.format("ID: %d, Nombre del Curso: %s, Docente ID: %d\n", id, nombreCurso, docenteId));
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al consultar cursos: " + e.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void editarCurso(JTextField idField, JTextField nombreCursoField, JTextField docenteIdField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del curso es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);
    String nombreCurso = nombreCursoField.getText();
    String docenteId = docenteIdField.getText();

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "UPDATE cursos SET nombre_curso = ?, docente_id = ? WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, nombreCurso);
      stmt.setInt(2, Integer.parseInt(docenteId));
      stmt.setInt(3, id);
      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated > 0) {
        JOptionPane.showMessageDialog(this, "Curso actualizado correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún curso con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al editar curso: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void eliminarCurso(JTextField idField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del curso es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "DELETE FROM cursos WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      int rowsDeleted = stmt.executeUpdate();
      if (rowsDeleted > 0) {
        JOptionPane.showMessageDialog(this, "Curso eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún curso con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al eliminar curso: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void verDetallesCurso(JTextField idField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del curso es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "SELECT * FROM cursos WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String nombreCurso = rs.getString("nombre_curso");
        int docenteId = rs.getInt("docente_id");

        String detalles = String.format("ID: %d\nNombre del Curso: %s\nDocente ID: %d", id, nombreCurso, docenteId);
        JOptionPane.showMessageDialog(this, detalles, "Detalles del Curso", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún curso con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al consultar detalles del curso: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void registrarDocente(JTextField nombreField, JTextField apellidoField) {
    String nombre = nombreField.getText();
    String apellido = apellidoField.getText();

    if (nombre.isEmpty() || apellido.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "INSERT INTO docentes (nombre, apellido) VALUES (?, ?)";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, nombre);
      stmt.setString(2, apellido);
      stmt.executeUpdate();
      JOptionPane.showMessageDialog(this, "Docente registrado correctamente.", "Éxito",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al registrar docente: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void consultarDocentes() {
    resultadosArea.setText("");
    try (Connection conn = DatabaseConnection.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM docentes");
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        resultadosArea.append(String.format("ID: %d, Nombre: %s %s\n", id, nombre, apellido));
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al consultar docentes: " + e.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void editarDocente(JTextField idField, JTextField nombreField, JTextField apellidoField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del docente es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);
    String nombre = nombreField.getText();
    String apellido = apellidoField.getText();

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "UPDATE docentes SET nombre = ?, apellido = ? WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, nombre);
      stmt.setString(2, apellido);
      stmt.setInt(3, id);
      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated > 0) {
        JOptionPane.showMessageDialog(this, "Docente actualizado correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún docente con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al editar docente: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void eliminarDocente(JTextField idField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del docente es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "DELETE FROM docentes WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      int rowsDeleted = stmt.executeUpdate();
      if (rowsDeleted > 0) {
        JOptionPane.showMessageDialog(this, "Docente eliminado correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún docente con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al eliminar docente: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void verDetallesDocente(JTextField idField) {
    String idText = idField.getText();
    if (idText.isEmpty()) {
      JOptionPane.showMessageDialog(this, "El ID del docente es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int id = Integer.parseInt(idText);

    try (Connection conn = DatabaseConnection.getConnection()) {
      String query = "SELECT * FROM docentes WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");

        String detalles = String.format("ID: %d\nNombre: %s\nApellido: %s", id, nombre, apellido);
        JOptionPane.showMessageDialog(this, detalles, "Detalles del Docente", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "No se encontró ningún docente con el ID proporcionado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Error al consultar detalles del docente: " + ex.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      ColegioApp app = new ColegioApp();
      app.setVisible(true);
    });
  }
}