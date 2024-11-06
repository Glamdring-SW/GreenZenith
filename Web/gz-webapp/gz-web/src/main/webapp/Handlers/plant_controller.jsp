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

    String name = null;
    String description = null;
    LocalDate localPlantDate = null;
    java.sql.Date plantingDate = null;
    int quantity = -1;
    BufferedImage plantPicture = null;
    String mimeType = null;
    LocalTime waterTime = null;
    ArrayList<Time> schedule = new ArrayList<>();

    for (FileItem item : formItems) {
        if (item.isFormField()) {
            String fieldName = item.getFieldName();
            String fieldValue = item.getString();
            if (fieldName.equals("name")) {
                name = fieldValue;
            }
            if (fieldName.equals("description")) {
                description = fieldValue;
            }
            if (fieldName.equals("plantingDate")) {
                localPlantDate = LocalDate.parse(fieldValue);
                Date plantingDateNoSQL = Date.from(localPlantDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                plantingDate = new java.sql.Date(plantingDateNoSQL.getTime());
            }
            if (fieldName.equals("quantity")) {
                quantity = Integer.parseInt(fieldValue);
            }
            if (fieldName.equals("waterTime")) {
                waterTime = LocalTime.parse(fieldValue);
                schedule.add(Time.valueOf(waterTime));
            }
        } else {
            plantPicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
        }
    }

    if (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/gif")
            && name != null && description != null && plantingDate != null && quantity != -1 && plantPicture != null && schedule != null && mimeType != null) {
        try {
            User user = (User) session.getAttribute("User");
            if (plantPicture == null) {
                user.getPlants().add(new Plant(name, plantingDate, description, quantity, schedule, ImageIO.read(new File("../img/default_profilePicture.png")), user));
            } else {
                user.getPlants().add(new Plant(name, plantingDate, description, quantity, schedule, plantPicture, user));
            }
            response.sendRedirect("../plantsexplore.jsp");
        } catch (InvalidUserException e) {
            session.setAttribute("jspErrorPlantCreate", "Se ocasiono un error, intentalo de nuevo.");
            session.setAttribute("jspErrorRegister", null);
            session.setAttribute("jspErrorLogin", null);
            session.setAttribute("jspErrorUserUpdate", null);
            response.sendRedirect("../error_module.jsp");
        }
    } else {
        session.setAttribute("jspErrorPlantCreate", "Se ocasiono un error, intentalo de nuevo, recuerda ingresar una imagen valida, aceptamos PNGs, JPGs y JPEGs");
        session.setAttribute("jspErrorRegister", null);
        session.setAttribute("jspErrorUserUpdate", null);
        session.setAttribute("jspErrorLogin", null);
        response.sendRedirect("../error_module.jsp");
    }
%>