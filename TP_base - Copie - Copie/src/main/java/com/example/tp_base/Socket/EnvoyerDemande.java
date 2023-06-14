package com.example.tp_base.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EnvoyerDemande {
    public static Socket s;
    private static PrintWriter pw;
    private final String line;

    public EnvoyerDemande(){
        System.out.println("Client");
        try {
            //creéation de socket (connexion au serveur)
            s= new Socket("127.0.0.1",1234);
            System.out.println("Je suis client , je suis connécté");

            //pour recevoir un message
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            line = br.readLine();
            System.out.println("msg recu "+line);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void envoyerMsg(String msg)  {
        try {
            pw= new PrintWriter(s.getOutputStream());
            pw.println(msg);
            pw.flush();
            System.out.println("message envoyé!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public String getMsg(){
        return this.line;
    }

    }




