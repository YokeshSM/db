package Yokesh.views;

import java.util.Scanner;

import Yokesh.controller.Passcheck;
import Yokesh.controller.admin;
import Yokesh.controller.scanner;
import Yokesh.controller.user;
import Yokesh.model.DAO.admindao;
import Yokesh.model.DAO.billsdao;
import Yokesh.model.DAO.brandsdao;
import Yokesh.model.DAO.productsDAO;
import Yokesh.model.DAO.shopdao;
import Yokesh.model.DAO.userDAO;

public class usermenu {
    public static int sum=0;
    public static int id=0;
    public static user ad;
    public void enter() throws Exception{
        Passcheck p=display.display1();
        id=p.id;
         ad=userDAO.uscheck(p);
        String name=ad.getName();
        if(name == null)
        {
        System.out.print("Incorrect Details");
        //App.enter();
        System.exit(200);
        }
        else
        {
            menudis();
       // System.out.println("Welcome "+name ); 
        } 
    }
    public void menudis() throws Exception
    {
        Scanner sc=scanner.getScanner();
        System.out.println("Welcome to User Actions");
        System.out.println("Click 1 for View");
        System.out.println("Click 2 for buy");
        // System.out.println("Click 3 for Update");
        // System.out.println("Click 4 for delete");
       // Passcheck p1=new Passcheck();
        
       /// ad=userDAO.uscheck(p1);
        System.out.println("Click 3 for Exit");
        int t=sc.nextInt();
        if(t==1)
        {
           System.out.print("Welcome To our shop ");
           brandsdao bd=new brandsdao();
           productsDAO pd=new productsDAO();
           System.out.println("Welcome to See our brands");
           System.out.println(bd.brandisp());
           System.out.println("Welcome to See our products");
           System.out.println(pd.prodisp());
           menudis();
        }
        if(t==2)
        {
            System.out.println("Enter the Value of the brand ID and products ID you need");
            int v=sc.nextInt(); //brand id
            int p=sc.nextInt(); //product id
            String m=brandsdao.setget(v);
            String h=productsDAO.setget(p);
            if(m.equals("null")||h.equals("null"))
            {
                System.out.println("Enter the Data and id Correctly by seeing the console");
                menudis();
            }
            else{
                //System.out.println(h+"    "+m);
                boolean f=shopdao.ispre(v,p);
                if(f)
                {
                    System.out.print("Yes We can buy  ");
                    System.out.print("Enter how much of "+h+" "+m+" you need?");
                    int count=sc.nextInt();
                    shopdao sm=new shopdao();
                    int c=sm.checkcount(v, p);
                   // if(c==0)
                    //System.out.println("Inform to our Admin then he will update you can buy after some time");
                    int price=sm.price();
                    sum=sum+(price*count);
                    if(c<count||c==-1)
                    {
                    System.out.print("we have no appropriate stock so please come after few days");
                    }
                    else
                    {
                        System.out.println("Can we proceed for shopping?");
                         int tp=sm.shop(v, p, count,id);
                         if(tp>0)
                         {
                         System.out.print("Do you want to continue shopping enter Y or N?");
                         if(sc.next().equals("Y"))
                         menudis();
                         else{
                            System.out.println("Your Bill is getting loaded please wait for few minutes");
                            billsdao bd=new billsdao();
                            bd.generate(ad,p,count,sum,m+" "+h);
                            System.out.print("Enter total Amount to pay the bill ");
                             System.out.println(sum);
                             int amt=sc.nextInt();
                             if(amt==sum)
                             {
                                if(bd.update(ad.getId())>=1)
                                System.out.println("You have completed your payment");
                                System.out.println("Payment Successful");
                             }
                             else{
                                System.out.println("Enter the Appropriate Amount");
                               
                                System.out.println("Wait few minutes ,then you can pay");
                                int amt1=sc.nextInt();
                             if(amt1==sum)
                             {
                                System.out.println("Payment Successful");
                             }
                             
                             }

                             System.out.print("Thank You for Shopping");
                         }
                        }else
                        menudis();
                         
                    }
                    
                }
                else
                {
                    System.out.println("there is no such product here ,you can reach us after few days");
                    menudis();
                }
            }
        }
        if(t==3)
        {
        System.exit(200);
        }
    }
}
