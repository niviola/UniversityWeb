package servlets;

import entities.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@WebServlet(urlPatterns = "/main_servlet")
public class MainServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String format = request.getParameter("format");
        University university = buildUniversityInfo();
        request.setAttribute("university", university);
        request.setAttribute("xml_out", getXMLstring(university));
        
        RequestDispatcher dispatcher = null;

        switch (format) {
            case "html":
                dispatcher = getServletContext().getRequestDispatcher("/HtmlSupportServlet");
                break;
            case "text":
                dispatcher = getServletContext().getRequestDispatcher("/TextSupportServlet");
                break;
            case "jsp":
                dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                break;
            case "xml":
                dispatcher = getServletContext().getRequestDispatcher("/XmlSupportServlet");
                break;
            case "json":
                dispatcher = getServletContext().getRequestDispatcher("/JsonSupportServlet");
                break;
            case "sql":
                dispatcher = getServletContext().getRequestDispatcher("/SqlSupportServlet");
                break;
            default:
            //dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        }

        dispatcher.forward(request, response);

    }
    
private String getXMLstring(University university) {
        String xml = "";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(University.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter stringWriter = new StringWriter();
            jaxbMarshaller.marshal(university, stringWriter);
            xml = stringWriter.toString();

        } catch (JAXBException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xml;
    }

    private University buildUniversityInfo(){
        University university = new University();
        university.setAddress(new Address("Palo Alto", "CA", "Street", "95014"));
        Map<UUID, Student> listofstudents = university.getListofstudents();
        Student student = new Student("Dave", "Smith", 22);
        student.setAddress(new Address("Cincinnati", "Ohio", "Elm", "45211"));
        Student student1 = new Student("Jane", "Jones", 19);
        student1.setAddress(new Address("Columbus", "Ohio", "Elm", "45211"));
        Student student2 = new Student("Scott", "White", 23);
        student2.setAddress(new Address("Cleveland", "Ohio", "Elm", "45211"));
        
        listofstudents.put(student.getUserid(), student);
        listofstudents.put(student1.getUserid(), student1);
        listofstudents.put(student2.getUserid(), student2);
        return university;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
