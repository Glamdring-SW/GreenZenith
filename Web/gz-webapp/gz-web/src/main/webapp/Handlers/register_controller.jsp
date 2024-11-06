<%@page import="org.apache.commons.fileupload2.javax.JavaxServletFileUpload"%>
<%@page import="org.apache.commons.fileupload2.core.FileItem"%>
<%@page import="org.apache.commons.fileupload2.core.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="com.glamdring.greenZenith.exceptions.database.GZDBResultException"%>
<%@page import="com.glamdring.greenZenith.exceptions.application.user.InvalidUserException"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%
    DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
    JavaxServletFileUpload upload = new JavaxServletFileUpload(factory);
    List<FileItem> formItems = upload.parseRequest((javax.servlet.http.HttpServletRequest) request);
    
    String username = null;
    String email = null;
    String password = null;
    int age = -1;
    BufferedImage profilePicture = null;
    String mimeType = null;
    
    for (FileItem item : formItems) {
        if (item.isFormField()) {
            String fieldName = item.getFieldName();
            String fieldValue = item.getString();
            if (fieldName.equals("userName")) {
                username = fieldValue;
            }
            if (fieldName.equals("email")) {
                email = fieldValue;
            }
            if (fieldName.equals("password")) {
                password = fieldValue;
            }
            if (fieldName.equals("age")) {
                age = Integer.parseInt(fieldValue);
            }
        } else {
            profilePicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
        }
    }
    
    if (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/gif")
            && username != null && email != null && password != null && age != -1 && mimeType != null) {
        try {
            User user;
            if (profilePicture == null) {
                user = new User(username, email, password, age, ImageIO.read(new File("../img/default_profilePicture.png")));
            } else {
                user = new User(username, email, password, age, profilePicture);
            }
            session.setAttribute("User", user);
            response.sendRedirect("../profile.jsp");
        } catch (InvalidUserException e) {
            session.setAttribute("jspErrorRegister", "Se ocasiono un error, intentalo de nuevo, recuerda ingresar tus datos personales de acuerdo a las instrucciones.");
            session.setAttribute("jspErrorLogin", null);
            session.setAttribute("jspErrorPlantCreate", null);
            session.setAttribute("jspErrorUserUpdate", null);
            response.sendRedirect("../error_module.jsp");
        } catch (GZDBResultException e) {
            session.setAttribute("jspErrorRegister", "Estamos teniendo problemas en nuestros servidores, intentalo de nuevo mas tarde.");
            session.setAttribute("jspErrorLogin", null);
            session.setAttribute("jspErrorPlantCreate", null);
            response.sendRedirect("../error_module.jsp");
        }
    } else {
        session.setAttribute("jspErrorRegister", "Se ocasiono un error, intentalo de nuevo, recuerda ingresar una imagen valida, aceptamos PNGs, JPGs y JPEGs");
        session.setAttribute("jspErrorLogin", null);
        session.setAttribute("jspErrorUserUpdate", null);
        session.setAttribute("jspErrorPlantCreate", null);
        response.sendRedirect("../emptyprofile.jsp");
    }
%>