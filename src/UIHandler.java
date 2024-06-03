public class UIHandler { 
    
    public void start(){ // Starts the interface of the app

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