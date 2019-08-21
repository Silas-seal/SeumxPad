
package skypad;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.text.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.geometry.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.*;
import javafx.scene.web.*;
import javafx.print.*;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.*;
import javafx.util.Duration;
import javafx.animation.*;

/**
 *
 * @author Seumx Plus
 */
public class SkyPad extends Application {
    
    @Override
    public void start(Stage Stage) throws Exception {
        Stage.setResizable(true);Stage.initStyle(StageStyle.DECORATED);Stage.setOpacity(1);Stage.setHeight(555);Stage.setWidth(891);
        Image icon=new Image(new FileInputStream("icon.png"));Stage.getIcons().add(icon);
        
        BackgroundFill bf1=new BackgroundFill(Color.WHITE,new CornerRadii(2),new Insets(2,2,2,2)); Background bg1=new Background(bf1);
        BackgroundFill bf2=new BackgroundFill(Color.LIGHTSTEELBLUE,new CornerRadii(2),new Insets(2,2,2,2)); Background bg2=new Background(bf2);
        BackgroundFill bf3=new BackgroundFill(Color.GREY,new CornerRadii(2),new Insets(2,2,2,2)); Background bg3=new Background(bf3);
        BackgroundFill bf4=new BackgroundFill(Color.WHITESMOKE,new CornerRadii(2),new Insets(2,2,2,2)); Background bg4=new Background(bf4);
        
        Text pd=new Text("SeumX HTMLPad");pd.setStyle("-fx-font:bold 25px 'Cambria'");pd.setFill(Color.INDIGO);
        MenuBar skb=new MenuBar(); skb.setPadding(new Insets(1,1,1,1)); skb.setBackground(bg2); skb.setStyle("-fx-font:normal 15px 'Cambria'");
        Menu file=new Menu("File"); Menu edt=new Menu("Edit"); Menu view=new Menu("View"); Menu hlp=new Menu("Help");
        
        MenuItem neew=new MenuItem("New");MenuItem open=new MenuItem("Open");MenuItem save=new MenuItem("Save");MenuItem sas=new MenuItem("Save As");
        MenuItem undo=new MenuItem("Undo");MenuItem redo=new MenuItem("Re-do");MenuItem copy=new MenuItem("Copy");MenuItem cut=new MenuItem("Cut");
        MenuItem del=new MenuItem("Delete");Menu theme=new Menu("Theme");MenuItem fullsc=new MenuItem("Full Screen");
        MenuItem print=new MenuItem("Print");MenuItem norm=new MenuItem("Normal");MenuItem tra=new MenuItem("Transparent");
        MenuItem dk=new MenuItem("Dark");MenuItem pst=new MenuItem("Paste");MenuItem vhlp=new MenuItem("View help doc");MenuItem abt=new MenuItem("About");
        MenuItem esc=new MenuItem("Exit FullScreen");esc.setDisable(true);
        
        file.getItems().addAll(neew,open,save,sas,print); edt.getItems().addAll(undo,redo,copy,cut,pst,del);
        view.getItems().addAll(theme,fullsc,esc);hlp.getItems().addAll(vhlp,abt);
        theme.getItems().addAll(tra,norm,dk); skb.getMenus().addAll(file,edt,view,hlp);
        undo.setDisable(true);del.setDisable(true);copy.setDisable(true);cut.setDisable(true);pst.setDisable(true);redo.setDisable(true);
        
        HTMLEditor edit=new HTMLEditor();edit.setPrefWidth(1700);TextArea f=new TextArea(); f.setPrefWidth(700); f.setPrefHeight(700);
        edit.setStyle("-fx-control-inner-background:lightblue");
        HBox hb=new HBox();hb.setPadding(new Insets(1,1,1,1));hb.setBackground(bg1);hb.getChildren().addAll();
        
        GridPane pn=new GridPane();pn.setPadding(new Insets(0,0,0,0));pn.setVgap(0);pn.setHgap(0);pn.setAlignment(Pos.TOP_LEFT);
        pn.add(pd,0,0);pn.add(skb,0,1);pn.add(edit,0,2); //BorderPane bp=new BorderPane();bp.setCenter(pn);
        
        //reading app theme
        FileInputStream in=new FileInputStream("theme.dll");
        ObjectInputStream obj=new ObjectInputStream(in);
        String tm; tm=(String) obj.readObject();
        
        if(tm.equals("default")) {
            Stage.setOpacity(1);pn.setBackground(bg1);pd.setFill(Color.INDIGO);
        }
        if(tm.equals("dark")) {
            Stage.setOpacity(1);pn.setBackground(bg3);pd.setFill(Color.IVORY);
        }
        if(tm.equals("transparent")) {
            Stage.setOpacity(0.93); pn.setBackground(bg4); pd.setFill(Color.DARKRED);
        }
        
        Scene scene=new Scene(pn);Stage.setScene(scene);Stage.setTitle("SeumXPad");
        //app start animation
        Label pdd=new Label("       SeumXPad"); pdd.setStyle("-fx-font:bold 27px 'Cambria'; -fx-text-fill:azure");
        ImageView vwe=new ImageView(icon); vwe.setFitWidth(213); vwe.setFitHeight(213); 
        ProgressIndicator ind=new ProgressIndicator(); ind.setStyle("-fx-progress-color:lightsteelblue");
        ind.setPrefWidth(117); ind.setPrefHeight(117);
        GridPane pnn=new GridPane(); pnn.setPadding(new Insets(1,1,1,1)); pnn.setVgap(7); pnn.setHgap(7); pnn.setAlignment(Pos.CENTER);
        pnn.setStyle("-fx-background-color:blueviolet"); pnn.add(vwe,0,0); pnn.add(ind,0,1); pnn.add(pdd,0,3);
        Scene sn=new Scene(pnn); Stage stg=new Stage(); stg.setScene(sn); stg.setWidth(497); stg.setHeight(313); 
        stg.initStyle(StageStyle.UNDECORATED); stg.getIcons().add(icon); stg.show();
        
        FadeTransition fd=new FadeTransition(Duration.millis(7000)); fd.setFromValue(0); fd.setToValue(1); fd.setNode(vwe);
        fd.setAutoReverse(false); fd.setCycleCount(1); fd.play();
        fd.setOnFinished(er->{
            stg.close(); Stage.show();
        });
        
        Button k=new Button("OK"); k.setStyle("-fx-background-color:Navy;-fx-font:bold 25px 'Constantia';-fx-text-fill:azure");k.setPrefSize(700,50);
        k.setOnMouseMoved(e->k.setStyle("-fx-background-color:pink;-fx-font:bold 25px 'Constantia';-fx-text-fill:azure"));
        k.setOnMouseExited(e1->k.setStyle("-fx-background-color:Navy;-fx-font:bold 25px 'Constantia';-fx-text-fill:azure"));
        k.setOnAction(er->{pn.getChildren().clear(); pn.add(pd,0,0);pn.add(skb,0,1);pn.add(edit,0,2);});
        
        neew.setAccelerator(new KeyCodeCombination(KeyCode.N,KeyCombination.CONTROL_DOWN));
        open.setAccelerator(new KeyCodeCombination(KeyCode.O,KeyCombination.CONTROL_DOWN));
        save.setAccelerator(new KeyCodeCombination(KeyCode.S,KeyCombination.CONTROL_DOWN));
        sas.setAccelerator(new KeyCodeCombination(KeyCode.S,KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN));
        print.setAccelerator(new KeyCodeCombination(KeyCode.P,KeyCombination.CONTROL_DOWN));
        
        neew.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            @SuppressWarnings("ResultOfMethodCallIgnored") 
            public void handle(ActionEvent ev){
                pn.getChildren().remove(edit); edit.setHtmlText(""); pn.add(edit,0,2);
            }
        }));
        
        open.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                FileChooser ch=new FileChooser(); ch.setTitle("Choose Html file");
                ch.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML File","*.html"));
                File file=ch.showOpenDialog(Stage);
                if(file!=null){
                    try {
                        DataInputStream in =new DataInputStream(new FileInputStream(file.getAbsolutePath()));
                        while(in.available()>0){ String c=in.readLine();//System.out.println(c+"");
                        pn.getChildren().remove(edit); edit.setHtmlText(c); pn.add(edit,0,2);}
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SkyPad.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(SkyPad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }));
        
        save.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev1) {
                FileChooser ch=new FileChooser();ch.setTitle("Save Html Code");
            ch.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML File","*.html") );
            
            File ht=ch.showSaveDialog(Stage); if(ht!=null){try {
                try (  FileWriter wrt = new FileWriter(ht)) {
                    @SuppressWarnings("StringBufferMayBeStringBuilder")
                    StringBuffer sb=new StringBuffer(edit.getHtmlText()); sb.replace(52, 56, "false");sb.insert(58, "align=\"left\"");
                    wrt.write(sb.toString()); wrt.flush(); wrt.close();
                }
                    } catch (IOException ex) {
                        Logger.getLogger(SkyPad.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
        }));
        
        sas.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                FileChooser ch=new FileChooser();ch.setTitle("Save As");
                ch.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML File","*.html"),
                        new FileChooser.ExtensionFilter("Source Code","*.txt")); 
                File file=ch.showSaveDialog(Stage);
                if(file!=null){try {
                    FileWriter wr=new FileWriter(file);{ @SuppressWarnings("StringBufferMayBeStringBuilder")
                            StringBuffer sb=new StringBuffer(edit.getHtmlText()); sb.replace(52, 56, "false");sb.insert(58, "align=\"left\"");
                    wr.write(sb.toString());wr.flush();wr.close();}
                    } catch (IOException ex) {
                        Logger.getLogger(SkyPad.class.getName()).log(Level.SEVERE, null, ex);
                    }
}
            }
        }));
        
        print.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                PrinterJob job= PrinterJob.createPrinterJob();
                if(job!=null&&job.showPrintDialog(Stage)){
                    edit.print(job);job.endJob();
                }
            }
        }));
        
        copy.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                
            }
        }));
        
        cut.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                
            }
        }));
        
        pst.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                
            }
        }));
        
        del.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                
            }
        }));
        
        tra.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                try {
                Stage.setOpacity(0.93); pn.setBackground(bg4); pd.setFill(Color.DARKRED);
                String tm1="transparent";
                FileOutputStream out=new FileOutputStream("theme.dll");
                ObjectOutputStream obju=new ObjectOutputStream(out);
                obju.writeObject(tm1);
                
                } catch (IOException ex) {
                    System.out.println("Error writing theme");
                }
            }
        }));
        
        norm.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                try {
                Stage.setOpacity(1);pn.setBackground(bg1);pd.setFill(Color.INDIGO);
                String tm1="default";
                FileOutputStream out=new FileOutputStream("theme.dll");
                ObjectOutputStream obju=new ObjectOutputStream(out);
                obju.writeObject(tm1);
                } catch (IOException ex) {
                    System.out.println("Error writing theme");
                }
            }
        }));
        
        dk.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                try {
                Stage.setOpacity(1);pn.setBackground(bg3);pd.setFill(Color.IVORY);
                String tm1="dark";
                FileOutputStream out=new FileOutputStream("theme.dll");
                ObjectOutputStream obju=new ObjectOutputStream(out);
                obju.writeObject(tm1);
                } catch (IOException ex) {
                    System.out.println("Error writing theme");
                }
            }
        }));
        
        fullsc.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                Stage.setFullScreen(true); esc.setDisable(false); fullsc.setDisable(true);
            }
        }));
        
        esc.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                Stage.setFullScreen(false); esc.setDisable(true); fullsc.setDisable(false);
            }
        }));
        
        vhlp.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                File f=new File("Documentation.html"); try {
                    URL url=f.toURI().toURL(); WebView v=new WebView(); WebEngine e=v.getEngine();
                    pn.getChildren().clear(); pn.add(v,0,0); pn.add(k,0,1); e.load(url.toString());
                } catch (MalformedURLException ex) {
                    Logger.getLogger(SkyPad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
        
        abt.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                String about="This software was developed and published by the SeumX Plus team Uganda."
                        + "\nThis software is also available for download on SeumX Store"
                        + "\nFor more information about our products and updates visit our website : http://www.skylineD.com."
                        + "\nAnd for help you can send us an email at Syrus.sealline@gmail.com, we will be happy to help you."
                        + "\nSoftware Developer : Muwanguzi Silas."
                        + "\nSoftware Company  : SeumX Plus"
                        + "\nSoftware Publisher : SeumX Plus";
                Text aboutUs=new Text(about);aboutUs.setStyle("-fx-font:normal 17px 'Cambria'");
                
                ImageView view=new ImageView(icon);
                view.setFitHeight(300);view.setFitWidth(300); ScrollPane sr=new ScrollPane(); VBox vg=new VBox();vg.setBackground(bg1);
                pn.getChildren().clear(); vg.getChildren().addAll(view,aboutUs,k); sr.setContent(vg);pn.add(vg,0,0);
            }
        }));
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
