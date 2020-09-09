package org.pk.hotel.dao;

import java.util.ArrayList;

import org.pk.hotel.beans.BillDetails;
import org.pk.hotel.beans.Category;
import org.pk.hotel.beans.Item;
import org.pk.hotel.beans.Login;
import org.pk.hotel.beans.OrderDetails;
import org.pk.hotel.beans.OrderMaster;

public class UserService
{
	
	
DBConnect dbconnect = new DBConnect();
	
	public ArrayList<Category> getAllCategorys(){
		return dbconnect.getAllCategory();
	}
	public boolean checkLogin(Login login)
	{
		return  dbconnect.checkLogin(login);		
	}
	
	public ArrayList<Item> getAllItems(int catId)
	{
		return dbconnect.getAllItemsByCategory(catId);
	}
	
	public ArrayList<OrderMaster> getAllOrders(int tableNo){
		return dbconnect.getAllOrders(tableNo);
	}
	
	public ArrayList<BillDetails> getBill(int tableNo)
	{
		return dbconnect.getBill(tableNo);
	}
}