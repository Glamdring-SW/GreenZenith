<%@page import="java.math.BigDecimal"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.util.Date"%>
<%@page import="com.glamdring.greenZenith.userInteractions.plants.Plant"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.fileupload2.javax.JavaxServletFileUpload"%>
<%@page import="org.apache.commons.fileupload2.core.FileItem"%>
<%@page import="org.apache.commons.fileupload2.core.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="com.glamdring.greenZenith.exceptions.database.GZDBResultException"%>
<%@page import="com.glamdring.greenZenith.exceptions.application.user.InvalidUserException"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
    JavaxServletFileUpload upload = new JavaxServletFileUpload(factory);
    List<FileItem> formItems = upload.parseRequest((javax.servlet.http.HttpServletRequest) request);
    
    int id = 0;
    String oldTitle = null;
    String newTitle = null;
    String newDescription = "";
    BigDecimal newPrice = null;
    int newQuantity = 0;
    BufferedImage newProductPicture = null;
    String mimeType = null;
    boolean defaultFlag = true;
    for (FileItem item : formItems) {
        if (item.isFormField()) {
            String fieldtitle = item.getFieldName();
            String fieldValue = item.getString();
            if (fieldtitle.equals("id")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    id = Integer.parseInt(fieldValue);
                }
            }
            if (fieldtitle.equals("oldTitle")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    oldTitle = fieldValue;
                }
            }
            if (fieldtitle.equals("title")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newTitle = fieldValue;
                }
            }
            if (fieldtitle.equals("description")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newDescription = fieldValue;
                }
            }
            if (fieldtitle.equals("price")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newPrice = new BigDecimal(fieldValue);
                }
            }
            if (fieldtitle.equals("quantity")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newQuantity = Integer.parseInt(fieldValue);
                }
            }
        } else {
            newProductPicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
            if (!mimeType.equals("application/octet-stream")) {
                defaultFlag = false;
            }
        }
    }
    if (defaultFlag || (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/gif"))) {
        try {
            User user = (User) session.getAttribute("User");
            if (defaultFlag) {
                user.updateProductBatch(id, oldTitle, newTitle, newDescription, newPrice, newQuantity, null);
            } else {
                user.updateProductBatch(id, oldTitle, newTitle, newDescription, newPrice, newQuantity, newProductPicture);
            }
            response.sendRedirect("../productsexplore.jsp");
        } catch (InvalidUserException e) {
            request.setAttribute("jspErrorProduct", e.getMessage());
            RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
            dispatch.forward(request, response);
        }
    } else {
        request.setAttribute("jspErrorProduct", "Se ocasiono un error, intentalo de nuevo, recuerda ingresar una imagen valida, aceptamos PNGs, JPGs y JPEGs");
        RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
        dispatch.forward(request, response);
    }
%>
