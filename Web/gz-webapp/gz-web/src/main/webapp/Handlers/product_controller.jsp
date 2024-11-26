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
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%
    DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
    JavaxServletFileUpload upload = new JavaxServletFileUpload(factory);
    List<FileItem> formItems = upload.parseRequest((javax.servlet.http.HttpServletRequest) request);

    int id = 0;
    String name = null;
    String title = null;
    String description = "";
    BigDecimal price = null;
    int quantity = 0;
    BufferedImage productPicture = null;
    String mimeType = null;
    boolean defaultFlag = true;

    for (FileItem item : formItems) {
        if (item.isFormField()) {
            String fieldtitle = item.getFieldName();
            String fieldValue = item.getString();
            if (fieldtitle.equals("title")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    title = fieldValue;
                }
            }
            if (fieldtitle.equals("description")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    description = fieldValue;
                }
            }
            if (fieldtitle.equals("price")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    price = new BigDecimal(fieldValue);
                }
            }
            if (fieldtitle.equals("quantity")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    quantity = Integer.parseInt(fieldValue);
                }
            }
            if (fieldtitle.equals("id")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    id = Integer.parseInt(fieldValue);
                }
            }
            if (fieldtitle.equals("name")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    name = fieldValue;
                }
            }
        } else {
            productPicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
            if (!mimeType.equals("application/octet-stream")) {
                defaultFlag = false;
            }
        }
    }

    if (defaultFlag || (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/gif"))) {
        try {
            User user = (User) session.getAttribute("User");
            if (productPicture == null) {
                user.addProduct(title, description, price, quantity, null, user.getPlant(id, name));
            } else {
                user.addProduct(title, description, price, quantity, productPicture, user.getPlant(id, name));
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