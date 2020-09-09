package controller;

import dao.ProductDao;
import model.Category;
import model.Products;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductDao productDao = new ProductDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "addNewProduct":
                    insertProduct(request, response);
                    break;
                case "editProduct":
                    updateProduct(request, response);
                    break;
                case "search":
                    searchProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
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

    public void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Products> listProduct = productDao.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/listProduct.jsp");
        dispatcher.forward(request, response);
    }


    public void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Products product = new Products(name, price, quantity, color, description, category);
        productDao.insertProduct(product);
        response.sendRedirect("/products");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = productDao.getAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/addNewProduct.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Products product = new Products(id, name, price, quantity, color, description, category);
        productDao.updateProduct(product);
        response.sendRedirect("/products");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Category> categories = productDao.getAllCategory();
        Products existingUser = productDao.selectProduct(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/editProduct.jsp");
        request.setAttribute("product", existingUser);
        request.setAttribute("categories", categories);
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDao.deleteProduct(id);
        List<Products> listUser = productDao.selectAllProduct();
        request.setAttribute("listProduct", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/listProduct.jsp");
        dispatcher.forward(request, response);
    }

    public void searchProduct(HttpServletRequest request, HttpServletResponse response){
        String searchKey = request.getParameter("search").replace(" ","").toLowerCase();
        List<Products> products = productDao.searchItemByName(searchKey);
        try {
            request.setAttribute("listProduct", products);
            request.getRequestDispatcher("view/listProduct.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
