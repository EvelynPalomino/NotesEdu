<%@ page language="java" contentType="application/pdf" pageEncoding="UTF-8"%>
<%@ page import="com.itextpdf.text.Document"%>
<%@ page import="com.itextpdf.text.Element"%>
<%@ page import="com.itextpdf.text.PageSize"%>
<%@ page import="com.itextpdf.text.Paragraph"%>
<%@ page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@ page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@ page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>

<%
  // Establecer la conexión a la base de datos
  Connection con = null;
  try {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url = "jdbc:sqlserver://localhost:1433;databaseName=NOTASREGISTRO;encrypt=true;TrustServerCertificate=True;user=sa;password=61582592lale";
    con = DriverManager.getConnection(url);

    // Obtener los datos de la tabla "student"
    String selectQuery = "SELECT * FROM users";
    Statement selectStmt = con.createStatement();
    ResultSet rs = selectStmt.executeQuery(selectQuery);

    // Crear un nuevo documento PDF
    Document document = new Document(PageSize.A4);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter.getInstance(document, baos);

    // Abrir el documento
    document.open();

    // Crear la tabla para los datos de la tabla "users"
    PdfPTable table = new PdfPTable(8); // Número de columnas
    table.setWidthPercentage(100);

    // Agregar encabezados de columna
    table.addCell("ID");
    table.addCell("Nombre");
    table.addCell("Apellido");
    table.addCell("Tipo de Documento");
    table.addCell("N° Documento");
    table.addCell("Tipo Usuario");
    table.addCell("Correo Electronico");
    table.addCell("N° Celular");
    



    // Agregar los datos de la tabla "users" a la tabla del PDF
    while (rs.next()) {
      String usersId = rs.getString("id");
      String name = rs.getString("names");
      String lastName = rs.getString("last_name");
      String documentType = rs.getString("type_document");
      String documentNumber = rs.getString("number_document");
      String userType = rs.getString("type_user");
      String email = rs.getString("email");
      String cellphone = rs.getString("cellphone");
      
    

      // Agregar una fila a la tabla con los datos del usuario
      table.addCell(usersId);
      table.addCell(name);
      table.addCell(lastName);
      table.addCell(documentType);
      table.addCell(documentNumber);
      table.addCell(userType);
      table.addCell(email);
      table.addCell(cellphone);
      
    }

    // Agregar la tabla al documento
    document.add(table);

    // Cerrar el documento
    document.close();

    // Establecer la respuesta HTTP para descargar el archivo PDF
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment;filename=users_data.pdf");
    response.setContentLength(baos.size());

    OutputStream os = response.getOutputStream();
    baos.writeTo(os);
    os.flush();
    os.close();
  } catch (Exception e) {
    out.println("Error: " + e.getMessage());
  } finally {
    if (con != null) {
      try {
        con.close();
      } catch (Exception e) {
        // Ignorar error al cerrar la conexión
      }
    }
  }
%>