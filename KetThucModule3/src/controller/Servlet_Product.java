package controller;

import dao.AccDAO;
import dao.ProductDAO;
import model.Categoryhihi;
import model.Product;
import model.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "Servlet_Product", urlPatterns = "/demo")
public class Servlet_Product extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private AccDAO accDAO;

    public void init() {
        productDAO = new ProductDAO();
        accDAO = new AccDAO();
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action){
                case  "info" :
                    showINFO(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                case "register":
                    createNewAcc(request, response);
                    break;
                case "details" :
                    showDetails(request, response);
                    break;
                case "search":
                    searchProducts(request, response);
                    break;
                case "new_account":
                    createNewAcc(request, response);
                    break;
                case "addNewProduct":
                    insertProduct(request, response);
                    break;
                case "editProduct":
                    updateProduct(request, response);
                    break;
                case "find":
                    findProduct(request,response);
                   break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserAccount user1 = accDAO.selectUser(username, password);
        request.setAttribute("user1", user1);
        if (user1 == null) {
            System.out.println("Not admin");
            String message = "Wrong username or password. Please try again.";
            request.setAttribute("message",message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            dispatcher.forward(request, response);
        } else {
            if (user1.getRole_id() == 1) {
                List<Product> list = productDAO.selectALLProducts();
                request.setAttribute("list",list);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud.jsp");
                dispatcher.forward(request, response);
            } else {
                System.out.println("Not admin");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/list.jsp");
//                dispatcher.forward(request, response);
                listProduct(request, response);

            }
        }
    }

    private void createNewAcc(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String number = request.getParameter("phonenumber");
        accDAO.createNewAcc(name, password, email, number);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/register2.jsp");
        dispatcher.forward(request, response);
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        String s = request.getParameter("search");
        List<Product> list = productDAO.searchrProducts(s);
        request.setAttribute("list",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String action = request.getParameter("action");
      if (action == null){
          action = "";
      }
      try {
          switch (action){
              case  "info" :
                  showINFO(request, response);
                  break;
              case "checkout":
                  showCheckout(request, response);
                  break;
              case "details":
                  showDetails(request, response);
                  break;
              case "login":
                  showLoginForm(request, response);
                  break;
              case "register":
                  showRegForm(request, response);
                  break;
              case "addNewProduct":
                  showNewForm(request,response);
                  break;
              case "editProduct":
                  showEditForm(request,response);

                  break;
              case "deleteProduct":
                  deleteProduct(request, response);
                  break;
               default: 
                   listProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showINFO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserAccount user = accDAO.selectUserByID(id);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/info.jsp");
        dispatcher.forward(request, response);
    }

    private void showRegForm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
        dispatcher.forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(request, response);
    }

    private void showCheckout(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/checkout.jsp");
        dispatcher.forward(request, response);
    }

    private void showDetails(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.productselectID(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/product.jsp");
        dispatcher.forward(request, response);
    }


    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, ServletException, IOException {
        List<Product> list = productDAO.selectALLProducts();
        List<Categoryhihi> categories = productDAO.selectALLCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, ServletException, IOException {
        List<Product> list = productDAO.selectALLProducts();
        List<Categoryhihi> categories = productDAO.selectALLCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud.jsp");
        dispatcher.forward(request, response);
    }

    public void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        Product product = new Product( name, price, category, description, image);
        productDAO.insertProduct(product);
        //response.sendRedirect("/WEB-INF/views/crud.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/addNewProduct.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Categoryhihi> categories = productDAO.selectALLCategories();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/addNewProduct.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String img = request.getParameter("img");

        Product product = new Product(id, name, price, category, description, img);
        productDAO.updateProduct(product);
        //response.sendRedirect("/WEB-INF/views/crud.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editProduct.jsp");
        dispatcher.forward(request,response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Categoryhihi> categories = productDAO.selectALLCategories();
        Product existingUser = productDAO.productselectID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editProduct.jsp");
        request.setAttribute("product", existingUser);
        request.setAttribute("categories", categories);
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        List<Product> list = productDAO.selectALLProducts();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud.jsp");
        dispatcher.forward(request, response);
    }
    private void findProduct(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        String s = request.getParameter("search");
        List<Product> list = productDAO.searchrProducts(s);
        request.setAttribute("list",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud.jsp");
        dispatcher.forward(request, response);
    }

}
