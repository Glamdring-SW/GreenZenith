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
    int age = 0;
    BufferedImage profilePicture = null;
    String mimeType = null;
    boolean defaultFlag = true;

    for (FileItem item : formItems) {
        if (item.isFormField()) {
            String fieldName = item.getFieldName();
            String fieldValue = item.getString();
            if (fieldName.equals("userName")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    username = fieldValue;
                }
            }
            if (fieldName.equals("email")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    email = fieldValue;
                }
            }
            if (fieldName.equals("password")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    password = fieldValue;
                }
            }
            if (fieldName.equals("age")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    age = Integer.parseInt(fieldValue);
                } else {
                    age = 0;
                }
            }
        } else {
            profilePicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
            if (!mimeType.equals("application/octet-stream")) {
                defaultFlag = false;
            }
        }
    }

    if (defaultFlag || (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/gif"))) {
        try {
            User user;
            if (defaultFlag) {
                user = new User(username, email, password, age, null);
            } else {
                user = new User(username, email, password, age, profilePicture);
            }
            session.setAttribute("User", user);
            response.sendRedirect("../homepage.jsp");
        } catch (InvalidUserException e) {
            request.setAttribute("jspErrorRegister", e.getMessage());
            RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
            dispatch.forward(request, response);
        } catch (GZDBResultException e) {
            request.setAttribute("jspErrorRegister", e.getMessage());
            RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
            dispatch.forward(request, response);
        }
    } else {
        request.setAttribute("jspErrorRegister", "Se ocasiono un error, intentalo de nuevo, recuerda ingresar una imagen valida, aceptamos PNGs, JPGs y JPEGs");
        RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
        dispatch.forward(request, response);
    }
%>