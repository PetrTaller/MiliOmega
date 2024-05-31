public class UIHandler { 
    
    public void start(){

        UserHandler userHandler = new UserHandler();
        if(userHandler.GetSession()==null){
        Login login = new Login();
        }else{
        Menu menu = new Menu();
        menu.isDarkMode=false;
        menu.MenuUI();

        }
        
    }
}