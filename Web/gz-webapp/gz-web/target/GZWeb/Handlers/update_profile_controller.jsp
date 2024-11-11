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

    String newName = null;
    String newEmail = null;
    String oldPassword = null;
    String newPassword = null;
    int newAge = 0;
    BufferedImage newPicture = null;
    String mimeType = null;
    boolean defaultFlag = true;

    for (FileItem item : formItems) {
        if (item.isFormField()) {
            String fieldName = item.getFieldName();
            String fieldValue = item.getString();
            if (fieldName.equals("userName")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newName = fieldValue;
                }
            }
            if (fieldName.equals("email")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newEmail = fieldValue;
                }
            }
            if (fieldName.equals("oldPassword")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    oldPassword = fieldValue;
                }
            }
            if (fieldName.equals("newPassword")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newPassword = fieldValue;
                }
            }
            if (fieldName.equals("age")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newAge = Integer.parseInt(fieldValue);
                }
            }
        } else {
            newPicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
            if (!mimeType.equals("application/octet-stream")) {
                defaultFlag = false;
            }
        }
    }
    User user = (User) session.getAttribute("User");
    if (defaultFlag || (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/gif"))) {
        if (defaultFlag) {
            user.updateUserBatch(newName, newEmail, newAge, null, oldPassword, newPassword);
        } else {
            user.updateUserBatch(newName, newEmail, newAge, newPicture, oldPassword, newPassword);
        }
    }
    response.sendRedirect("../profile.jsp");
%>