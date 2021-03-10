package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConvertisseurController implements Initializable{

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt1;
    
    @FXML
    private TextField txtL1;
    @FXML
    private TextField txtL2;
    
    @FXML
    private ComboBox<String> cboL1;
    
    @FXML
    private ComboBox<String> cboL2;
    
    

    @FXML
    private ComboBox<String> cbo2;

    @FXML
    private ComboBox<String> cbo1;

  // monnaies
    private ObservableList<String> liste=(ObservableList<String>)FXCollections.observableArrayList("CAD","USD","EUR");

   double []monnaie= {1.0,0.75,0.69};
 // longueurs
   private ObservableList<String> listL=(ObservableList<String>)FXCollections.observableArrayList("Kilomètre","Mètre","Décimètre","Centimètre","Millimètre");

  double []tabLong= {0.001,1.0,10.0,100.0,1000.0};
   
   
@Override
public void initialize(URL location, ResourceBundle resources)
{
	cbo1.setItems(liste);
	cbo2.setItems(liste);
	cbo1.getSelectionModel().selectFirst();
	cbo2.getSelectionModel().selectFirst();
	//longueurs
	cboL1.setItems(listL);
	cboL2.setItems(listL);
	cboL1.getSelectionModel().selectFirst();
	cboL2.getSelectionModel().selectFirst();
	
}
  



// POur les monnaies
@FXML
void calculer1()
{
	convertir(txt1,txt2,cbo1,cbo2,monnaie);	
}
 

@FXML
void calculer2()
{
	convertir(txt2,txt1,cbo2,cbo1,monnaie);	
}
// pour les longueurs

@FXML
void calculerL1()
{
	convertir(txtL1,txtL2,cboL1,cboL2,tabLong);	
}
 

@FXML
void calculerL2()
{
	convertir(txtL2,txtL1,cboL2,cboL1,tabLong);	
}





public void convertir(TextField txtA,TextField txtB,ComboBox boxA,ComboBox boxB,double [] tab)
{
	verifNum(txtA);
	
	int item1=boxA.getSelectionModel().getSelectedIndex();
	int item2=boxB.getSelectionModel().getSelectedIndex();
	try
	{
		double taux=tab[item2]/tab[item1];
		double res=taux*(Double.parseDouble(txtA.getText()));
		///txtB.setText(String.format("%.2f", res));
		txtB.setText(Double.toString(res));
	} catch (NumberFormatException e)
	{
		txtA.setText("0");
	}	
}

//Gestion numérique - accepter des caractères numériques seulement
public void verifNum(TextField a)
{
	if(a.getText().equals("")) 
	a.setText("0");

	
	a.textProperty().addListener((observable,oldValue,newValue)->
	{
		if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
		{
			a.setText(newValue.replaceAll("[^\\d*\\.]", ""));
		}
		
	});
	
	
}
    
    
}
