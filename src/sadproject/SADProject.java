
package sadproject;

public class SADProject {
    public static String CurrentUsername="";
    public static int CurrentType=0;
    public static String ChosenPlace ="";
    public static int Transport = 0;
    public static int TransportBudget = 0;
    public static int Guide = 0;
    public static int GuideBudget = 0;
    public static int totalBudget =0;
    public static MainMenu mm = new MainMenu();
    public static LoginWindow lw = new LoginWindow();
    public static void main(String[] args) {
        lw.setVisible(true);

    }
}
