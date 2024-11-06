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
    String oldPassword = null;
    String newPassword = null;
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
            if (fieldName.equals("oldPassword")) {
                oldPassword = fieldValue;
            }
            if (fieldName.equals("newPassword")) {
                newPassword = fieldValue;
            }
            if (fieldName.equals("age")) {
                if (!fieldValue.isEmpty() && !fieldValue.isBlank()) {
                    age = Integer.parseInt(fieldValue);
                } else {
                    age = 0;
                }
            }
        } else {
            profilePicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
        }
    }

    try {
        User user = (User) session.getAttribute("User");
        if (!username.isEmpty() && !username.isBlank()) {
            user.setName(username);
        }
        if (!email.isEmpty() && !email.isBlank()) {
            user.setEmail(email);
        }
        if (!oldPassword.isEmpty() && !oldPassword.isBlank() && !newPassword.isEmpty() && !newPassword.isBlank()) {
            user.setPassword(oldPassword, newPassword);
        }
        if (age != 0) {
            user.setAge(age);
        }
        if (profilePicture != null) {
            user.setPicture(profilePicture);
        }
        response.sendRedirect("../profile.jsp");
    } catch (InvalidUserException e) {
        session.setAttribute("jspErrorRegister", null);
        session.setAttribute("jspErrorLogin", null);
        session.setAttribute("jspErrorPlantCreate", null);
        session.setAttribute("jspErrorUserUpdate", "No se lograron actualizar los datos, vuelve a intenta." + e.getMessage());
        response.sendRedirect("../error_module.jsp");
    }
%>