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
    String oldName = null;
    String newName = null;
    String description = null;
    LocalDate localPlantDate = null;
    int quantity = 0;
    BufferedImage plantPicture = null;
    String mimeType = null;
    ArrayList<LocalTime> schedule = new ArrayList<>();
    boolean defaultFlag = true;

    for (FileItem item : formItems) {
        if (item.isFormField()) {
            String fieldName = item.getFieldName();
            String fieldValue = item.getString();
            if (fieldName.equals("id")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    id = Integer.parseInt(fieldValue);
                }
            }
            if (fieldName.equals("oldName")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    oldName = fieldValue;
                }
            }
            if (fieldName.equals("name")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    newName = fieldValue;
                }
            }
            if (fieldName.equals("description")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    description = fieldValue;
                }
            }
            if (fieldName.equals("plantingDate")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    localPlantDate = LocalDate.parse(fieldValue);
                }
            }
            if (fieldName.equals("quantity")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    quantity = Integer.parseInt(fieldValue);
                }
            }
            if (fieldName.equals("waterTime")) {
                if (fieldValue != null && !fieldValue.isBlank()) {
                    schedule.add(LocalTime.parse(fieldValue));
                }
            }
        } else {
            plantPicture = ImageIO.read(item.getInputStream());
            mimeType = item.getContentType();
            if (!mimeType.equals("application/octet-stream")) {
                defaultFlag = false;
            }
        }
    }
    User user = (User) session.getAttribute("User");
    if (defaultFlag || (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/gif"))) {
        if (defaultFlag) {
            user.updatePlantBatch(id, oldName, newName, description, quantity, localPlantDate, schedule, null);
        } else {
            user.updatePlantBatch(id, oldName, newName, description, quantity, localPlantDate, schedule, plantPicture);
        }
    }
    response.sendRedirect("../plantsexplore.jsp");
%>
