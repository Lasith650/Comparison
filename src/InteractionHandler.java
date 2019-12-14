import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InteractionHandler {
    //This method will select one Stride per one interaction
    public ArrayList<String> getSTRIDE (ArrayList<String> interaction){
        ArrayList<String> filteredSTRIDE = new ArrayList<String>();
        int size = interaction.size();
        int Spoofing = 0, Tampering = 0, Repudiation = 0, InfoDis = 0, DenSer = 0, ElePrev = 0;
        for (int i = 0; i < size; i++){
            if (Spoofing == 0){
                filteredSTRIDE.add(interaction.get(i));
                Spoofing++;
            }else if (Tampering == 0){
                filteredSTRIDE.add(interaction.get(i));
                Tampering++;
            }else if (Repudiation == 0){
                filteredSTRIDE.add(interaction.get(i));
                Repudiation++;
            }else if (InfoDis == 0){
                filteredSTRIDE.add(interaction.get(i));
                InfoDis++;
            }else if (DenSer == 0) {
                filteredSTRIDE.add(interaction.get(i));
                DenSer++;
            }else if (ElePrev == 0) {
                filteredSTRIDE.add(interaction.get(i));
                ElePrev++;
            }
        }
        return filteredSTRIDE;
    }

    //This method will out put the set of distinct interactions
    public ArrayList<String> distinctInteractions (){
        ArrayList<String> distinctInteractions = new ArrayList<String>();
        try {
            Connection con = DatabaseConnection.getDatabaseConnection_instance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct interaction from tmt_output_tbl");
            while (rs.next()) {
                distinctInteractions.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return distinctInteractions;
    }
}
